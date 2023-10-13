package net.goldelf123.salt_lamps.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;



public class ItemSaltChunk
  extends Item
{
  public ItemSaltChunk() {
    setRegistryName("salt_chunk");
    setTranslationKey("salt_lamps.salt_chunk");
    setCreativeTab(CreativeTabs.MATERIALS);
  }
}
