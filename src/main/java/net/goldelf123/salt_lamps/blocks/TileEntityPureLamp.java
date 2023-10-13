package net.goldelf123.salt_lamps.blocks;

import java.util.Collection;
import java.util.List;
import net.goldelf123.salt_lamps.Config;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityPureLamp
  extends TileEntitySaltLamp
  implements ITickable
{
  public void update() {
    if ((this.world.getTotalWorldTime() - this.timeSinceStart) % 10L == 0L && Config.enableDiffusion)
    {
      diffuseEffects();
    }
    
    if (this.ticksLeft > 0 && Config.enableTimeLimit) {
      
      this.ticksLeft--;
      
      if (this.ticksLeft == 0)
      {
        clearEffects();
      }
    } 
  }

  
  public void setEffects() {
    this.ticksLeft = Config.pureLampTimeDuration * 20;
    
    this.timeSinceStart = this.world.getTotalWorldTime();
    
    IBlockState state = this.world.getBlockState(getPos());
    this.world.notifyBlockUpdate(this.pos, state, state, 3);
    
    markDirty();
  }

  
  public void diffuseEffects() {
    if (!this.world.isRemote && this.ticksLeft > 0) {
      
      int posX = this.pos.getX();
      int posY = this.pos.getY();
      int posZ = this.pos.getZ();
      int effectRadius = Config.pureLampAreaOfEffect;
      AxisAlignedBB axisalignedbb = (new AxisAlignedBB(posX, posY, posZ, (posX + 1), (posY + 1), (posZ + 1))).grow(effectRadius);
      List<EntityPlayer> listPlayers = this.world.getEntitiesWithinAABB(EntityPlayer.class, axisalignedbb);
      
      for (EntityPlayer entityPlayer : listPlayers) {
        
        Collection<PotionEffect> listPotions = entityPlayer.getActivePotionEffects();
        for (PotionEffect potionEffect : listPotions) {
          
          if (potionEffect.getPotion().isBadEffect())
          {
            entityPlayer.removePotionEffect(potionEffect.getPotion());
          }
        } 
      } 
    } 
  }

  
  public boolean isDiffusing() {
    if (this.ticksLeft > 0)
    {
      return true;
    }
    
    return false;
  }

  
  public void readFromNBT(NBTTagCompound compound) {
    this.potionEffects.clear();
    
    super.readFromNBT(compound);
    this.ticksLeft = compound.getInteger("TicksLeft");
  }

  
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);
    compound.setInteger("TicksLeft", this.ticksLeft);
    return compound;
  }
}
