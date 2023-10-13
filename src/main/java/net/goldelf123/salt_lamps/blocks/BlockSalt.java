package net.goldelf123.salt_lamps.blocks;

import java.util.Random;
import net.goldelf123.salt_lamps.items.SaltLampItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;



public class BlockSalt
  extends Block
{
  public BlockSalt() {
    super(Material.SAND);
    setTranslationKey("salt_lamps.salt_block");
    setRegistryName("salt_block");
    setHardness(0.6F);
    setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    setSoundType(SoundType.SAND);
  }

  
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return SaltLampItems.saltChunk;
  }

  
  public int quantityDropped(Random random) {
    return 1;
  }

  
  public int quantityDroppedWithBonus(int fortune, Random random) {
    if (fortune > 0 && Item.getItemFromBlock(this) != getItemDropped((IBlockState)getBlockState().getValidStates().iterator().next(), random, fortune)) {
      
      if (fortune == 1)
      {
        return quantityDropped(random) + random.nextInt(1);
      }
      if (fortune == 2)
      {
        return quantityDropped(random) + random.nextInt(2);
      }

      
      return quantityDropped(random) + random.nextInt(3);
    } 


    
    return quantityDropped(random);
  }


  
  public void initModel() {
    ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
  }

  
  public void initItemModel() {
    Item itemBlock = (Item)Item.REGISTRY.getObject(new ResourceLocation("salt_lamps", "salt_block"));
    ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(getRegistryName(), "inventory");
    int DEFAULT_ITEM_SUBTYPE = 0;
    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlock, 0, itemModelResourceLocation);
  }
}
