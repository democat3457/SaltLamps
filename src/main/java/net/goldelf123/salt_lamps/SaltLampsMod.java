package net.goldelf123.salt_lamps;

import java.io.File;
import net.goldelf123.salt_lamps.blocks.SaltLampBlocks;
import net.goldelf123.salt_lamps.blocks.TileEntityMobLamp;
import net.goldelf123.salt_lamps.blocks.TileEntityPureLamp;
import net.goldelf123.salt_lamps.blocks.TileEntitySaltLamp;
import net.goldelf123.salt_lamps.items.SaltLampItems;
import net.goldelf123.salt_lamps.worldgen.SaltSurfaceGen;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = "salt_lamps", name = "Salt Lamps", version = "1.12.2-1.1.0", dependencies = "required-after:forge@[14.23.5.2847,)", useMetadata = true)
@EventBusSubscriber
public class SaltLampsMod {

  public static final String MODID = "salt_lamps";
  public static final String MODNAME = "Salt Lamps";
  public static final String MODVERSION = "1.12.2-1.1.0";

  @Instance
  public static SaltLampsMod instance;
  public static Logger logger;
  public static Configuration config;
  
  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    logger = event.getModLog();
    
    File directory = event.getModConfigurationDirectory();
    config = new Configuration(new File(directory.getPath(), "salt_lamps.cfg"));
    Config.readConfig();
  }

  @EventHandler
  public void init(FMLInitializationEvent event) {}

  @EventHandler
  public void postInit(FMLPostInitializationEvent event) {
    if (Config.canGenerateSalt) {
      GameRegistry.registerWorldGenerator((IWorldGenerator)new SaltSurfaceGen(), 0);
    }
  }

  @SubscribeEvent
  public static void registerBlocks(RegistryEvent.Register<Block> event) {
    event.getRegistry().register(SaltLampBlocks.whiteSaltLamp);
    event.getRegistry().register(SaltLampBlocks.orangeSaltLamp);
    event.getRegistry().register(SaltLampBlocks.magentaSaltLamp);
    event.getRegistry().register(SaltLampBlocks.lightBlueSaltLamp);
    event.getRegistry().register(SaltLampBlocks.yellowSaltLamp);
    event.getRegistry().register(SaltLampBlocks.limeSaltLamp);
    event.getRegistry().register(SaltLampBlocks.pinkSaltLamp);
    event.getRegistry().register(SaltLampBlocks.graySaltLamp);
    event.getRegistry().register(SaltLampBlocks.lightGraySaltLamp);
    event.getRegistry().register(SaltLampBlocks.cyanSaltLamp);
    event.getRegistry().register(SaltLampBlocks.purpleSaltLamp);
    event.getRegistry().register(SaltLampBlocks.blueSaltLamp);
    event.getRegistry().register(SaltLampBlocks.brownSaltLamp);
    event.getRegistry().register(SaltLampBlocks.greenSaltLamp);
    event.getRegistry().register(SaltLampBlocks.redSaltLamp);
    event.getRegistry().register(SaltLampBlocks.blackSaltLamp);
    
    event.getRegistry().register(SaltLampBlocks.pureSaltLamp);
    
    event.getRegistry().register(SaltLampBlocks.hostileMobSaltLamp);
    event.getRegistry().register(SaltLampBlocks.passiveMobSaltLamp);
    
    event.getRegistry().register(SaltLampBlocks.saltBlock);
  }

  @SubscribeEvent
  public static void registerItems(RegistryEvent.Register<Item> event) {
    event.getRegistry().register((new ItemBlock(SaltLampBlocks.whiteSaltLamp)).setRegistryName(SaltLampBlocks.whiteSaltLamp.getRegistryName()));
    event.getRegistry().register((new ItemBlock(SaltLampBlocks.orangeSaltLamp)).setRegistryName(SaltLampBlocks.orangeSaltLamp.getRegistryName()));
    event.getRegistry().register((new ItemBlock(SaltLampBlocks.magentaSaltLamp)).setRegistryName(SaltLampBlocks.magentaSaltLamp.getRegistryName()));
    event.getRegistry().register((new ItemBlock(SaltLampBlocks.lightBlueSaltLamp)).setRegistryName(SaltLampBlocks.lightBlueSaltLamp.getRegistryName()));
    event.getRegistry().register((new ItemBlock(SaltLampBlocks.yellowSaltLamp)).setRegistryName(SaltLampBlocks.yellowSaltLamp.getRegistryName()));
    event.getRegistry().register((new ItemBlock(SaltLampBlocks.limeSaltLamp)).setRegistryName(SaltLampBlocks.limeSaltLamp.getRegistryName()));
    event.getRegistry().register((new ItemBlock(SaltLampBlocks.pinkSaltLamp)).setRegistryName(SaltLampBlocks.pinkSaltLamp.getRegistryName()));
    event.getRegistry().register((new ItemBlock(SaltLampBlocks.graySaltLamp)).setRegistryName(SaltLampBlocks.graySaltLamp.getRegistryName()));
    event.getRegistry().register((new ItemBlock(SaltLampBlocks.lightGraySaltLamp)).setRegistryName(SaltLampBlocks.lightGraySaltLamp.getRegistryName()));
    event.getRegistry().register((new ItemBlock(SaltLampBlocks.cyanSaltLamp)).setRegistryName(SaltLampBlocks.cyanSaltLamp.getRegistryName()));
    event.getRegistry().register((new ItemBlock(SaltLampBlocks.purpleSaltLamp)).setRegistryName(SaltLampBlocks.purpleSaltLamp.getRegistryName()));
    event.getRegistry().register((new ItemBlock(SaltLampBlocks.blueSaltLamp)).setRegistryName(SaltLampBlocks.blueSaltLamp.getRegistryName()));
    event.getRegistry().register((new ItemBlock(SaltLampBlocks.brownSaltLamp)).setRegistryName(SaltLampBlocks.brownSaltLamp.getRegistryName()));
    event.getRegistry().register((new ItemBlock(SaltLampBlocks.greenSaltLamp)).setRegistryName(SaltLampBlocks.greenSaltLamp.getRegistryName()));
    event.getRegistry().register((new ItemBlock(SaltLampBlocks.redSaltLamp)).setRegistryName(SaltLampBlocks.redSaltLamp.getRegistryName()));
    event.getRegistry().register((new ItemBlock(SaltLampBlocks.blackSaltLamp)).setRegistryName(SaltLampBlocks.blackSaltLamp.getRegistryName()));
    
    event.getRegistry().register((new ItemBlock(SaltLampBlocks.pureSaltLamp)).setRegistryName(SaltLampBlocks.pureSaltLamp.getRegistryName()));
    
    event.getRegistry().register((new ItemBlock(SaltLampBlocks.hostileMobSaltLamp)).setRegistryName(SaltLampBlocks.hostileMobSaltLamp.getRegistryName()));
    event.getRegistry().register((new ItemBlock(SaltLampBlocks.passiveMobSaltLamp)).setRegistryName(SaltLampBlocks.passiveMobSaltLamp.getRegistryName()));
    
    event.getRegistry().register((new ItemBlock(SaltLampBlocks.saltBlock)).setRegistryName(SaltLampBlocks.saltBlock.getRegistryName()));
    
    GameRegistry.registerTileEntity(TileEntitySaltLamp.class, new ResourceLocation("salt_lamps", "salt_lamp"));
    GameRegistry.registerTileEntity(TileEntityPureLamp.class, new ResourceLocation("salt_lamps", "pure_salt_lamp"));
    GameRegistry.registerTileEntity(TileEntityMobLamp.class, new ResourceLocation("salt_lamps", "mob_salt_lamp"));
    
    event.getRegistry().register(SaltLampItems.saltChunk);
  }

  @SubscribeEvent
  public static void registerModels(ModelRegistryEvent event) {
    SaltLampBlocks.initModels();
    SaltLampItems.initModels();
  }
}
