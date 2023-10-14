package net.goldelf123.salt_lamps.blocks;

import java.util.ArrayList;
import java.util.List;
import net.goldelf123.salt_lamps.Config;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityMobLamp
  extends TileEntitySaltLamp
{
  private boolean affectsHostileMobs = false;
  private boolean affectsPassiveMobs = false;
  
  public void diffuseEffects() {
    if (!this.world.isRemote && !this.potionEffects.isEmpty() && this.ticksLeft > 0) {
      
      int posX = this.pos.getX();
      int posY = this.pos.getY();
      int posZ = this.pos.getZ();
      int effectRadius = Config.mobLampAreaOfEffect;
      AxisAlignedBB axisalignedbb = (new AxisAlignedBB(posX, posY, posZ, (posX + 1), (posY + 1), (posZ + 1))).grow(effectRadius);
      
      List<EntityLivingBase> listMobs = this.world.getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
      List<EntityLivingBase> listHostile = new ArrayList<>();
      List<EntityLivingBase> listPassive = new ArrayList<>();
      List<EntityTameable> listTameable = new ArrayList<>();
      for (EntityLivingBase entity : listMobs) {
        
        if (entity.isCreatureType(EnumCreatureType.MONSTER, false)) {
          
          listHostile.add(entity); continue;
        } 
        if (entity.isCreatureType(EnumCreatureType.CREATURE, false) || entity
          .isCreatureType(EnumCreatureType.AMBIENT, false) || entity
          .isCreatureType(EnumCreatureType.WATER_CREATURE, false)) {
          
          listPassive.add(entity); continue;
        } 
        if (entity instanceof EntityTameable)
        {
          listTameable.add((EntityTameable)entity);
        }
      } 
      listTameable.removeAll(listTameable);
      
      if (this.affectsHostileMobs)
      {
        for (EntityLivingBase entityHostile : listHostile) {
          
          for (int i = 0; i < this.potionEffects.size(); i++) {
            
            if (((PotionEffect)this.potionEffects.get(i)).getPotion().isInstant()) {
              
              ((PotionEffect)this.potionEffects.get(i)).getPotion().performEffect(entityHostile, ((PotionEffect)this.potionEffects.get(i)).getAmplifier());
            }
            else {
              
              entityHostile.addPotionEffect(new PotionEffect(((PotionEffect)this.potionEffects.get(i)).getPotion(), 100, ((PotionEffect)this.potionEffects.get(i)).getAmplifier(), true, true));
            } 
          } 
        } 
      }
      
      if (this.affectsPassiveMobs)
      {
        for (EntityLivingBase entityPassive : listPassive) {
          
          for (int i = 0; i < this.potionEffects.size(); i++) {
            
            if (((PotionEffect)this.potionEffects.get(i)).getPotion().isBeneficial())
            {
              if (((PotionEffect)this.potionEffects.get(i)).getPotion().isInstant()) {
                
                ((PotionEffect)this.potionEffects.get(i)).getPotion().performEffect(entityPassive, ((PotionEffect)this.potionEffects.get(i)).getAmplifier());
              }
              else {
                
                entityPassive.addPotionEffect(new PotionEffect(((PotionEffect)this.potionEffects.get(i)).getPotion(), 100, ((PotionEffect)this.potionEffects.get(i)).getAmplifier(), true, true));
              } 
            }
          } 
        } 
      }
      
      if (Config.mobLampAffectsTameableMobs && this.affectsPassiveMobs)
      {
        for (EntityTameable entityTameable : listTameable) {
          
          for (int i = 0; i < this.potionEffects.size(); i++) {
            
            if (((PotionEffect)this.potionEffects.get(i)).getPotion().isInstant()) {
              
              ((PotionEffect)this.potionEffects.get(i)).getPotion().performEffect((EntityLivingBase)entityTameable, ((PotionEffect)this.potionEffects.get(i)).getAmplifier());
            }
            else {
              
              entityTameable.addPotionEffect(new PotionEffect(((PotionEffect)this.potionEffects.get(i)).getPotion(), 100, ((PotionEffect)this.potionEffects.get(i)).getAmplifier(), true, true));
            } 
          } 
        } 
      }
    } 
  }

  
  public TileEntityMobLamp setAffectedEntities(boolean affectsHostileMobsIn, boolean affectsPassiveMobsIn) {
    this.affectsHostileMobs = affectsHostileMobsIn;
    this.affectsPassiveMobs = affectsPassiveMobsIn;
    
    return this;
  }
}
