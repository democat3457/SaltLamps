package net.goldelf123.salt_lamps;

import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;



public class Config
{
  public static boolean enableDiffusion = true;
  public static int timeMultiplier = 5;
  public static boolean enableTimeLimit = true;
  public static int areaOfEffect = 8;
  public static int minimumDuration = 8;
  
  public static boolean allowNegativePlayerEffects = true;
  public static int pureLampTimeDuration = 30;
  public static int pureLampAreaOfEffect = 4;
  
  public static int mobLampTimeMultiplier = 5;
  public static boolean mobLampEnableTimeLimit = true;
  public static int mobLampAreaOfEffect = 8;
  public static int mobLampMinimumDuration = 8;
  
  public static boolean mobLampAffectsTameableMobs = false;
  public static boolean canGenerateSalt = true;
  public static int saltPerChunk = 1;
  public static int saltClusterSize = 2;
  
  private static final String CATEGORY_BALANCE = "Balance";
  
  private static final String CATEGORY_WORLDGEN = "WorldGen";
  private static final String CATEGORY_CRAFTING = "Crafting";
  
  public static void readConfig() {
    Configuration cfg = SaltLampsMod.config;
    
    try {
      cfg.load();
      initBalanceConfig(cfg);
    }
    catch (Exception exception) {
      
      SaltLampsMod.logger.log(Level.ERROR, "Problem loading config file!", exception);
    }
    finally {
      
      if (cfg.hasChanged())
      {
        cfg.save();
      }
    } 
  }

  
  private static void initBalanceConfig(Configuration cfg) {
    cfg.addCustomCategoryComment("Balance", "Makes sure that the effects of salt lamps aren't too OP (unless you want them to be)");
    cfg.addCustomCategoryComment("WorldGen", "Controls the worldgen for this mod");
    cfg.addCustomCategoryComment("Crafting", "Enable or disable crafting recipes");
    
    enableDiffusion = cfg.getBoolean("EnablePotionDiffusion", "Balance", enableDiffusion, "Enable or disable potion effect diffusion from the lamps. If set to false,\nthe lamps will be purely decorational.");
    
    timeMultiplier = cfg.getInt("TimeMultiplier", "Balance", timeMultiplier, 1, 10, "Controls the amount of time that a salt lamp can produce effects after consuming a potion.\nThis time limit is based on the amount of time a potion would be active for if drunk\n(e.g., if a potion lasts for 1 minute when drunk and this value is set to 5,\nthen the same potion poured on a salt lamp will produce an area of effect for 5 minutes).");
    areaOfEffect = cfg.getInt("AreaOfEffect", "Balance", areaOfEffect, 1, 256, "Controls the area of effect block radius for salt lamp effects.");
    enableTimeLimit = cfg.getBoolean("EnableTimeLimit", "Balance", enableTimeLimit, "Enables or disables the time multiplier. If this is set to false,\neffects poured on salt lamps will last forever, or until a new potion is poured on the lamp.");
    minimumDuration = cfg.getInt("MinimumDuration", "Balance", minimumDuration, 1, 60, "Controls the minimum amount of time that a salt lamp can produce efforts for in seconds.\nThis will be the duration for instant potions, like Potion of Healing.");
    allowNegativePlayerEffects = cfg.getBoolean("AllowNegativePlayerEffects", "Balance", allowNegativePlayerEffects, "Controls whether or not a salt lamp can produce negative effects for players.");
    
    pureLampTimeDuration = cfg.getInt("PureLampTimeDuration", "Balance", pureLampTimeDuration, 1, 60, "Controls the amount of time that a pure salt lamp can produce effects in seconds.");
    pureLampAreaOfEffect = cfg.getInt("PureLampAreaOfEffect", "Balance", pureLampAreaOfEffect, 1, 256, "Controls the area of effect block radius for pure salt lamp effects.");
    
    mobLampTimeMultiplier = cfg.getInt("MobLampTimeMultiplier", "Balance", mobLampTimeMultiplier, 1, 10, "Controls the amount of time that a mob salt lamp can produce effects after consuming a potion.\nThis time limit is based on the amount of time a potion would be active for if drunk\n(e.g., if a potion lasts for 1 minute when drunk and this value is set to 5,\nthen the same potion poured on a mob salt lamp will produce an area of effect for 5 minutes).");
    mobLampAreaOfEffect = cfg.getInt("MobLampAreaOfEffect", "Balance", mobLampAreaOfEffect, 1, 256, "Controls the area of effect block radius for mob salt lamp effects.");
    mobLampEnableTimeLimit = cfg.getBoolean("MobLampEnableTimeLimit", "Balance", mobLampEnableTimeLimit, "Enables or disables the time multiplier. If this is set to false,\neffects poured on mob salt lamps will last forever, or until a new potion is poured on the lamp.");
    mobLampMinimumDuration = cfg.getInt("MobLampMinimumDuration", "Balance", mobLampMinimumDuration, 1, 60, "Controls the minimum amount of time that a mob salt lamp can produce efforts for in seconds.\nThis will be the duration for instant potions, like Potion of Healing.");
    mobLampAffectsTameableMobs = cfg.getBoolean("MobLampAffectsTameableMobs", "Balance", mobLampAffectsTameableMobs, "Controls whether or not tameable mobs (e.g., wolves, cats, etc.) can be affected by mob salt lamps.\nIMPORTANT DISCLAIMER NOTE: Out of caution, only BENEFICIAL effects will apply to tameable mobs.\nThe mod author is NOT RESPONSIBLE for tameable mobs being harmed, lost,\nor KILLED due to the use of this mod!");
    
    canGenerateSalt = cfg.getBoolean("CanGenerateSaltOre", "WorldGen", canGenerateSalt, "Enable or disable generation of salt blocks.");
    saltPerChunk = cfg.getInt("SaltPerChunk", "WorldGen", saltPerChunk, 0, 16, "Controls the maximum salt clusters that can be generated per chunk.");
    saltClusterSize = cfg.getInt("SaltClusterSize", "WorldGen", saltClusterSize, 1, 16, "Controls the size of salt clusters.");
  }
}
