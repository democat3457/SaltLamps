package net.goldelf123.salt_lamps;

import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

public class Config {

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

  public static String saltChunkReplacementOre = "";
  
  private static final String CATEGORY_BALANCE = "Balance";
  private static final String CATEGORY_WORLDGEN = "WorldGen";
  private static final String CATEGORY_CRAFTING = "Crafting";
  
  public static void readConfig() {
    Configuration cfg = SaltLampsMod.config;
    
    try {
      cfg.load();
      initBalanceConfig(cfg);
    } catch (Exception exception) {
      SaltLampsMod.logger.log(Level.ERROR, "Problem loading config file!", exception);
    } finally {
      if (cfg.hasChanged()) {
        cfg.save();
      }
    } 
  }

  private static void initBalanceConfig(Configuration cfg) {
    cfg.addCustomCategoryComment(CATEGORY_BALANCE, "Makes sure that the effects of salt lamps aren't too OP (unless you want them to be)");
    cfg.addCustomCategoryComment(CATEGORY_WORLDGEN, "Controls the worldgen for this mod");
    cfg.addCustomCategoryComment(CATEGORY_CRAFTING, "Options related to crafting recipes");
    
    enableDiffusion = cfg.getBoolean("EnablePotionDiffusion", CATEGORY_BALANCE, enableDiffusion, "Enable or disable potion effect diffusion from the lamps. If set to false,\nthe lamps will be purely decorational.");
    
    timeMultiplier = cfg.getInt("TimeMultiplier", CATEGORY_BALANCE, timeMultiplier, 1, 10, "Controls the amount of time that a salt lamp can produce effects after consuming a potion.\nThis time limit is based on the amount of time a potion would be active for if drunk\n(e.g., if a potion lasts for 1 minute when drunk and this value is set to 5,\nthen the same potion poured on a salt lamp will produce an area of effect for 5 minutes).");
    areaOfEffect = cfg.getInt("AreaOfEffect", CATEGORY_BALANCE, areaOfEffect, 1, 256, "Controls the area of effect block radius for salt lamp effects.");
    enableTimeLimit = cfg.getBoolean("EnableTimeLimit", CATEGORY_BALANCE, enableTimeLimit, "Enables or disables the time multiplier. If this is set to false,\neffects poured on salt lamps will last forever, or until a new potion is poured on the lamp.");
    minimumDuration = cfg.getInt("MinimumDuration", CATEGORY_BALANCE, minimumDuration, 1, 60, "Controls the minimum amount of time that a salt lamp can produce efforts for in seconds.\nThis will be the duration for instant potions, like Potion of Healing.");
    allowNegativePlayerEffects = cfg.getBoolean("AllowNegativePlayerEffects", CATEGORY_BALANCE, allowNegativePlayerEffects, "Controls whether or not a salt lamp can produce negative effects for players.");
    
    pureLampTimeDuration = cfg.getInt("PureLampTimeDuration", CATEGORY_BALANCE, pureLampTimeDuration, 1, 60, "Controls the amount of time that a pure salt lamp can produce effects in seconds.");
    pureLampAreaOfEffect = cfg.getInt("PureLampAreaOfEffect", CATEGORY_BALANCE, pureLampAreaOfEffect, 1, 256, "Controls the area of effect block radius for pure salt lamp effects.");
    
    mobLampTimeMultiplier = cfg.getInt("MobLampTimeMultiplier", CATEGORY_BALANCE, mobLampTimeMultiplier, 1, 10, "Controls the amount of time that a mob salt lamp can produce effects after consuming a potion.\nThis time limit is based on the amount of time a potion would be active for if drunk\n(e.g., if a potion lasts for 1 minute when drunk and this value is set to 5,\nthen the same potion poured on a mob salt lamp will produce an area of effect for 5 minutes).");
    mobLampAreaOfEffect = cfg.getInt("MobLampAreaOfEffect", CATEGORY_BALANCE, mobLampAreaOfEffect, 1, 256, "Controls the area of effect block radius for mob salt lamp effects.");
    mobLampEnableTimeLimit = cfg.getBoolean("MobLampEnableTimeLimit", CATEGORY_BALANCE, mobLampEnableTimeLimit, "Enables or disables the time multiplier. If this is set to false,\neffects poured on mob salt lamps will last forever, or until a new potion is poured on the lamp.");
    mobLampMinimumDuration = cfg.getInt("MobLampMinimumDuration", CATEGORY_BALANCE, mobLampMinimumDuration, 1, 60, "Controls the minimum amount of time that a mob salt lamp can produce efforts for in seconds.\nThis will be the duration for instant potions, like Potion of Healing.");
    mobLampAffectsTameableMobs = cfg.getBoolean("MobLampAffectsTameableMobs", CATEGORY_BALANCE, mobLampAffectsTameableMobs, "Controls whether or not tameable mobs (e.g., wolves, cats, etc.) can be affected by mob salt lamps.\nIMPORTANT DISCLAIMER NOTE: Out of caution, only BENEFICIAL effects will apply to tameable mobs.\nThe mod author is NOT RESPONSIBLE for tameable mobs being harmed, lost,\nor KILLED due to the use of this mod!");
    
    canGenerateSalt = cfg.getBoolean("CanGenerateSaltOre", CATEGORY_WORLDGEN, canGenerateSalt, "Enable or disable generation of salt blocks.");
    saltPerChunk = cfg.getInt("SaltPerChunk", CATEGORY_WORLDGEN, saltPerChunk, 0, 16, "Controls the maximum salt clusters that can be generated per chunk.");
    saltClusterSize = cfg.getInt("SaltClusterSize", CATEGORY_WORLDGEN, saltClusterSize, 1, 16, "Controls the size of salt clusters.");
    
    saltChunkReplacementOre = cfg.getString("SaltChunkReplacementOre", CATEGORY_CRAFTING, saltChunkReplacementOre, "Replace salt chunks in salt lamps crafting recipes with this oredict.\nIf empty, keeps salt chunks as the only allowed ingredient for salt. Recommended to set to 'dustSalt' for larger packs.");
  }
}
