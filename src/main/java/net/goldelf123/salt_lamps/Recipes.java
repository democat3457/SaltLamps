package net.goldelf123.salt_lamps;

import net.goldelf123.salt_lamps.blocks.SaltLampBlocks;
import net.goldelf123.salt_lamps.items.SaltLampItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreIngredient;

public class Recipes {

  public static String OREDICT_SALT_LAMP = "saltLamp";
  public static String OREDICT_COLORED_SALT_LAMP = "saltLampColored";

  public static void init() {
    for (Block b : SaltLampBlocks.coloredSaltLamps) {
      OreDictionary.registerOre(OREDICT_SALT_LAMP, b);
      OreDictionary.registerOre(OREDICT_COLORED_SALT_LAMP, b);
    }
    OreDictionary.registerOre(OREDICT_SALT_LAMP, SaltLampBlocks.pureSaltLamp);
    OreDictionary.registerOre(OREDICT_SALT_LAMP, SaltLampBlocks.passiveMobSaltLamp);
    OreDictionary.registerOre(OREDICT_SALT_LAMP, SaltLampBlocks.hostileMobSaltLamp);
    OreDictionary.registerOre("blockSalt", SaltLampBlocks.saltBlock);
    OreDictionary.registerOre("dustSalt", SaltLampItems.saltChunk);

    Object saltItem = Config.saltChunkReplacementOre == "" ? SaltLampItems.saltChunk : Config.saltChunkReplacementOre;

    GameRegistry.addShapedRecipe(new ResourceLocation("salt_lamps:pure_salt_lamp"), null,
        new ItemStack(SaltLampBlocks.pureSaltLamp),
        " C ",
        "CGC",
        "CLC",
        'C', saltItem,
        'G', Items.NETHER_STAR,
        'L', "logWood");
    GameRegistry.addShapedRecipe(new ResourceLocation("salt_lamps:hostile_mob_salt_lamp"), null,
        new ItemStack(SaltLampBlocks.hostileMobSaltLamp),
        " C ",
        "CGC",
        "CLC",
        'C', saltItem,
        'G', new ItemStack(Items.SKULL, 1, 1),
        'L', "logWood");
    GameRegistry.addShapedRecipe(new ResourceLocation("salt_lamps:passive_mob_salt_lamp"), null,
        new ItemStack(SaltLampBlocks.passiveMobSaltLamp),
        " C ",
        "CGC",
        "CLC",
        'C', saltItem,
        'G', Items.GOLDEN_APPLE,
        'L', "logWood");
    GameRegistry.addShapedRecipe(new ResourceLocation("salt_lamps:white_salt_lamp"), null,
        new ItemStack(SaltLampBlocks.whiteSaltLamp),
        " C ",
        "CGC",
        "CLC",
        'C', saltItem,
        'G', Blocks.GLOWSTONE,
        'L', "logWood");
    GameRegistry.addShapelessRecipe(new ResourceLocation("salt_lamps:white_salt_lamp_redye"), null,
        new ItemStack(SaltLampBlocks.whiteSaltLamp),
        new OreIngredient(OREDICT_COLORED_SALT_LAMP), new OreIngredient("dyeWhite"));
  }
}
