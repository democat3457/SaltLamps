package net.goldelf123.salt_lamps.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class SaltLampItems {

  public static Item saltChunk = new ItemSaltChunk();

  public static void initModels() {
    ModelLoader.setCustomModelResourceLocation(saltChunk, 0, new ModelResourceLocation(saltChunk.getRegistryName(), "inventory"));
  }
}
