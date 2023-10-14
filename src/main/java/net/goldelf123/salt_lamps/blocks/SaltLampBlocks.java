package net.goldelf123.salt_lamps.blocks;

import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

public class SaltLampBlocks {

  @ObjectHolder("salt_lamps:white_salt_lamp")
  public static BlockSaltLamp whiteSaltLamp = new BlockSaltLamp("white_salt_lamp");
  @ObjectHolder("salt_lamps:orange_salt_lamp")
  public static BlockSaltLamp orangeSaltLamp = new BlockSaltLamp("orange_salt_lamp");
  @ObjectHolder("salt_lamps:magenta_salt_lamp")
  public static BlockSaltLamp magentaSaltLamp = new BlockSaltLamp("magenta_salt_lamp");
  @ObjectHolder("salt_lamps:light_blue_salt_lamp")
  public static BlockSaltLamp lightBlueSaltLamp = new BlockSaltLamp("light_blue_salt_lamp");
  @ObjectHolder("salt_lamps:yellow_salt_lamp")
  public static BlockSaltLamp yellowSaltLamp = new BlockSaltLamp("yellow_salt_lamp");
  @ObjectHolder("salt_lamps:lime_salt_lamp")
  public static BlockSaltLamp limeSaltLamp = new BlockSaltLamp("lime_salt_lamp");
  @ObjectHolder("salt_lamps:pink_salt_lamp")
  public static BlockSaltLamp pinkSaltLamp = new BlockSaltLamp("pink_salt_lamp");
  @ObjectHolder("salt_lamps:gray_salt_lamp")
  public static BlockSaltLamp graySaltLamp = new BlockSaltLamp("gray_salt_lamp");
  @ObjectHolder("salt_lamps:light_gray_salt_lamp")
  public static BlockSaltLamp lightGraySaltLamp = new BlockSaltLamp("light_gray_salt_lamp");
  @ObjectHolder("salt_lamps:cyan_salt_lamp")
  public static BlockSaltLamp cyanSaltLamp = new BlockSaltLamp("cyan_salt_lamp");
  @ObjectHolder("salt_lamps:purple_salt_lamp")
  public static BlockSaltLamp purpleSaltLamp = new BlockSaltLamp("purple_salt_lamp");
  @ObjectHolder("salt_lamps:blue_salt_lamp")
  public static BlockSaltLamp blueSaltLamp = new BlockSaltLamp("blue_salt_lamp");
  @ObjectHolder("salt_lamps:brown_salt_lamp")
  public static BlockSaltLamp brownSaltLamp = new BlockSaltLamp("brown_salt_lamp");
  @ObjectHolder("salt_lamps:green_salt_lamp")
  public static BlockSaltLamp greenSaltLamp = new BlockSaltLamp("green_salt_lamp");
  @ObjectHolder("salt_lamps:red_salt_lamp")
  public static BlockSaltLamp redSaltLamp = new BlockSaltLamp("red_salt_lamp");
  @ObjectHolder("salt_lamps:black_salt_lamp")
  public static BlockSaltLamp blackSaltLamp = new BlockSaltLamp("black_salt_lamp");
  
  @ObjectHolder("salt_lamps:pure_salt_lamp")
  public static BlockPureLamp pureSaltLamp = new BlockPureLamp("pure_salt_lamp");
  
  @ObjectHolder("salt_lamps:hostile_mob_salt_lamp")
  public static BlockMobLamp hostileMobSaltLamp = new BlockMobLamp("hostile_mob_salt_lamp", true, false);
  @ObjectHolder("salt_lamps:passive_mob_salt_lamp")
  public static BlockMobLamp passiveMobSaltLamp = new BlockMobLamp("passive_mob_salt_lamp", false, true);
  
  @ObjectHolder("salt_lamps:salt_block")
  public static BlockSalt saltBlock = new BlockSalt();

  public static void initModels() {
    whiteSaltLamp.initModel();
    orangeSaltLamp.initModel();
    magentaSaltLamp.initModel();
    lightBlueSaltLamp.initModel();
    yellowSaltLamp.initModel();
    limeSaltLamp.initModel();
    pinkSaltLamp.initModel();
    graySaltLamp.initModel();
    lightGraySaltLamp.initModel();
    cyanSaltLamp.initModel();
    purpleSaltLamp.initModel();
    blueSaltLamp.initModel();
    brownSaltLamp.initModel();
    greenSaltLamp.initModel();
    redSaltLamp.initModel();
    blackSaltLamp.initModel();
    
    pureSaltLamp.initModel();
    
    hostileMobSaltLamp.initModel();
    passiveMobSaltLamp.initModel();
    
    saltBlock.initModel();
  }
}
