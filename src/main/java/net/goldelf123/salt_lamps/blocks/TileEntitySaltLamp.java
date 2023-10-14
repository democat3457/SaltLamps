package net.goldelf123.salt_lamps.blocks;

import java.util.ArrayList;
import java.util.List;
import net.goldelf123.salt_lamps.Config;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntitySaltLamp extends TileEntity implements ITickable {

  protected List<PotionEffect> potionEffects = new ArrayList<>();
  protected int ticksLeft = 0;
  protected long timeSinceStart = 0L;

  public void onLoad() {
    this.timeSinceStart = this.world.getTotalWorldTime();
    if (Config.enableDiffusion) {
      diffuseEffects();
    }
  }

  public void update() {
    if ((this.world.getTotalWorldTime() - this.timeSinceStart) % 60L == 0L && Config.enableDiffusion) {
      diffuseEffects();
    }
    
    if (this.ticksLeft > 0 && Config.enableTimeLimit) {
      this.ticksLeft--;
      
      if (this.ticksLeft == 0) {
        clearEffects();
      }
    } 
  }

  public void clearEffects() {
    this.potionEffects.clear();
    this.ticksLeft = 0;
    
    IBlockState state = this.world.getBlockState(getPos());
    this.world.notifyBlockUpdate(this.pos, state, state, 3);
    
    markDirty();
  }

  public void setEffects(List<PotionEffect> potionsIn) {
    this.potionEffects = potionsIn;
    int minTime = Integer.MAX_VALUE;
    for (PotionEffect potionIn : potionsIn) {
      int potionDuration = potionIn.getDuration();
      if (potionDuration < minTime) {
        minTime = potionDuration;
      }
    } 
    this.ticksLeft = minTime;
    
    if (this.ticksLeft < Config.minimumDuration) {
      this.ticksLeft = Config.minimumDuration * 20;
    }
    
    this.ticksLeft *= Config.timeMultiplier;
    
    this.timeSinceStart = this.world.getTotalWorldTime();
    
    IBlockState state = this.world.getBlockState(getPos());
    this.world.notifyBlockUpdate(this.pos, state, state, 3);
    
    markDirty();
  }

  public void diffuseEffects() {
    if (!this.world.isRemote && !this.potionEffects.isEmpty() && this.ticksLeft > 0) {
      int posX = this.pos.getX();
      int posY = this.pos.getY();
      int posZ = this.pos.getZ();
      int effectRadius = Config.areaOfEffect;
      AxisAlignedBB axisalignedbb = (new AxisAlignedBB(posX, posY, posZ, (posX + 1), (posY + 1), (posZ + 1))).grow(effectRadius);
      List<EntityPlayer> listPlayers = this.world.getEntitiesWithinAABB(EntityPlayer.class, axisalignedbb);
      
      for (EntityPlayer entityPlayer : listPlayers) {
        for (int i = 0; i < this.potionEffects.size(); i++) {
          if (Config.allowNegativePlayerEffects || ((PotionEffect)this.potionEffects.get(i)).getPotion().isBeneficial()) {
            if (((PotionEffect)this.potionEffects.get(i)).getPotion().isInstant()) {
              ((PotionEffect)this.potionEffects.get(i)).getPotion().performEffect((EntityLivingBase)entityPlayer, ((PotionEffect)this.potionEffects.get(i)).getAmplifier());
            } else {
              entityPlayer.addPotionEffect(new PotionEffect(((PotionEffect)this.potionEffects.get(i)).getPotion(), 100, ((PotionEffect)this.potionEffects.get(i)).getAmplifier(), true, true));
            } 
          }
        } 
      } 
    } 
  }

  public boolean isDiffusing() {
    return this.ticksLeft > 0 && !this.potionEffects.isEmpty();
  }

  public List<PotionEffect> getEffects() {
    return this.potionEffects;
  }

  public int getParticleColor() {
    return PotionUtils.getPotionColorFromEffectList(this.potionEffects);
  }

  public int getTicksLeft() {
    return this.ticksLeft;
  }

  public void readFromNBT(NBTTagCompound compound) {
    this.potionEffects.clear();
    
    super.readFromNBT(compound);
    int numPotionEffects = compound.getInteger("NumPotionEffects");
    for (int i = 0; i < numPotionEffects; i++) {
      PotionEffect tempPotionEffect = new PotionEffect(Potion.getPotionById(compound.getByte("PotionId" + i)), 0, compound.getByte("PotionAmplifier" + i));
      this.potionEffects.add(tempPotionEffect);
    } 
    this.ticksLeft = compound.getInteger("TicksLeft");
  }

  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);
    compound.setInteger("NumPotionEffects", this.potionEffects.size());
    for (int i = 0; i < this.potionEffects.size(); i++) {
      compound.setByte("PotionId" + i, (byte)Potion.getIdFromPotion(((PotionEffect)this.potionEffects.get(i)).getPotion()));
      compound.setByte("PotionAmplifier" + i, (byte)((PotionEffect)this.potionEffects.get(i)).getAmplifier());
    } 
    compound.setInteger("TicksLeft", this.ticksLeft);
    return compound;
  }

  public SPacketUpdateTileEntity getUpdatePacket() {
    return new SPacketUpdateTileEntity(getPos(), 1, getUpdateTag());
  }

  public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
    readFromNBT(packet.getNbtCompound());
  }

  public NBTTagCompound getUpdateTag() {
    return writeToNBT(new NBTTagCompound());
  }
}
