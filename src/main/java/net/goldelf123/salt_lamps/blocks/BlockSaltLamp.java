package net.goldelf123.salt_lamps.blocks;

import java.util.Random;
import net.goldelf123.salt_lamps.Config;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;





public class BlockSaltLamp
  extends Block
  implements ITileEntityProvider
{
  private String blockName = "";
  
  protected boolean wasLampExtinguished = false;
  
  public BlockSaltLamp(String nameIn) {
    super(Material.GLASS);
    setTranslationKey("salt_lamps." + nameIn);
    setRegistryName(nameIn);
    setLightLevel(0.75F);
    setHardness(1.0F);
    setResistance(3.0F);
    setCreativeTab(CreativeTabs.DECORATIONS);
    setTickRandomly(true);
    this.blockName = nameIn;
  }

  
  public boolean isFullCube(IBlockState state) {
    return false;
  }

  
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return new AxisAlignedBB(0.3125D, 0.0D, 0.3125D, 0.6875D, 0.625D, 0.6875D);
  }

  
  public void initModel() {
    ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
  }

  
  public void initItemModel() {
    Item itemBlock = (Item)Item.REGISTRY.getObject(new ResourceLocation("salt_lamps", this.blockName));
    ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(getRegistryName(), "inventory");
    int DEFAULT_ITEM_SUBTYPE = 0;
    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlock, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
  }

  
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    if (worldIn.isRemote || !Config.enableDiffusion) {
      
      if (playerIn.inventory.getCurrentItem().getItem() instanceof net.minecraft.item.ItemPotion || playerIn.inventory
        .getCurrentItem().getItem() instanceof net.minecraft.item.ItemSplashPotion || playerIn.inventory
        .getCurrentItem().getItem() instanceof net.minecraft.item.ItemLingeringPotion) {
        
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TileEntitySaltLamp)
        {
          if (!playerIn.inventory.getCurrentItem().getItem().hasEffect(playerIn.inventory.getCurrentItem()) && ((TileEntitySaltLamp)tileentity).isDiffusing())
          {
            for (int i = 0; i < 5; i++) {
              
              double d0 = pos.getX() + worldIn.rand.nextDouble() * 0.6D + 0.2D;
              double d1 = pos.getY() + worldIn.rand.nextDouble() * 0.6D + 0.2D;
              double d2 = pos.getZ() + worldIn.rand.nextDouble() * 0.6D + 0.2D;
              worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
            } 
          }
        }
      } 
      
      return true;
    } 

    
    if (playerIn.inventory.getCurrentItem().getItem() instanceof net.minecraft.item.ItemPotion || playerIn.inventory
      .getCurrentItem().getItem() instanceof net.minecraft.item.ItemSplashPotion || playerIn.inventory
      .getCurrentItem().getItem() instanceof net.minecraft.item.ItemLingeringPotion) {
      
      TileEntity tileentity = worldIn.getTileEntity(pos);
      if (tileentity instanceof TileEntitySaltLamp)
      {
        if (!playerIn.inventory.getCurrentItem().getItem().hasEffect(playerIn.inventory.getCurrentItem()) && ((TileEntitySaltLamp)tileentity).isDiffusing()) {
          
          if (!playerIn.capabilities.isCreativeMode) {
            
            playerIn.inventory.getCurrentItem().shrink(1);
            playerIn.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
          } 
          
          ((TileEntitySaltLamp)tileentity).clearEffects();
          worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
        }
        else if (playerIn.inventory.getCurrentItem().getItem().hasEffect(playerIn.inventory.getCurrentItem())) {
          
          if (!playerIn.capabilities.isCreativeMode) {
            
            playerIn.inventory.getCurrentItem().shrink(1);
            playerIn.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
          } 
          
          ((TileEntitySaltLamp)tileentity).setEffects(PotionUtils.getEffectsFromStack(playerIn.inventory.getCurrentItem()));
          ((TileEntitySaltLamp)tileentity).diffuseEffects();
          
          worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
        } 
      }
      
      return true;
    } 
    
    return false;
  }


  
  public String getBlockName() {
    return this.blockName;
  }

  
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntitySaltLamp();
  }

  
  public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
    switch (face) {
      
      case UP:
        return BlockFaceShape.CENTER_SMALL;
      case DOWN:
        return BlockFaceShape.CENTER;
      case NORTH:
      case SOUTH:
      case EAST:
      case WEST:
        return BlockFaceShape.CENTER_SMALL;
    } 
    return BlockFaceShape.UNDEFINED;
  }


  
  public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
    IBlockState downState = worldIn.getBlockState(pos.down());
    BlockFaceShape blockFaceShape = downState.getBlockFaceShape((IBlockAccess)worldIn, pos.down(), EnumFacing.UP);
    return (super.canPlaceBlockAt(worldIn, pos) && (downState
      .isSideSolid((IBlockAccess)worldIn, pos, EnumFacing.UP) || blockFaceShape == BlockFaceShape.SOLID || blockFaceShape == BlockFaceShape.CENTER || blockFaceShape == BlockFaceShape.MIDDLE_POLE || blockFaceShape == BlockFaceShape.CENTER_BIG || blockFaceShape == BlockFaceShape.MIDDLE_POLE_THICK));
  }






  
  public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
    IBlockState downState = worldIn.getBlockState(pos.down());
    BlockFaceShape blockFaceShape = downState.getBlockFaceShape((IBlockAccess)worldIn, pos.down(), EnumFacing.UP);
    if (!downState.isSideSolid((IBlockAccess)worldIn, pos, EnumFacing.UP) && blockFaceShape != BlockFaceShape.SOLID && blockFaceShape != BlockFaceShape.CENTER && blockFaceShape != BlockFaceShape.MIDDLE_POLE && blockFaceShape != BlockFaceShape.CENTER_BIG && blockFaceShape != BlockFaceShape.MIDDLE_POLE_THICK) {





      
      dropBlockAsItem(worldIn, pos, state, 0);
      worldIn.setBlockToAir(pos);
    } 
  }

  
  @SideOnly(Side.CLIENT)
  public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
    TileEntity tileentity = worldIn.getTileEntity(pos);
    if (tileentity instanceof TileEntitySaltLamp)
    {
      if (((TileEntitySaltLamp)tileentity).isDiffusing())
      {
        for (int i = 0; i < 1; i++) {
          
          double d0 = (pos.getX() + 0.4F + rand.nextFloat() * 0.2F);
          double d1 = (pos.getY() + 0.7F + rand.nextFloat() * 0.3F);
          double d2 = (pos.getZ() + 0.4F + rand.nextFloat() * 0.2F);
          int particleColor = ((TileEntitySaltLamp)tileentity).getParticleColor();
          int colorRed = particleColor >> 16 & 0xFF;
          int colorBlue = particleColor >> 8 & 0xFF;
          int colorGreen = particleColor & 0xFF;
          worldIn.spawnParticle(EnumParticleTypes.SPELL_MOB, d0, d1, d2, (colorRed / 255.0F), (colorBlue / 255.0F), (colorGreen / 255.0F), new int[0]);
        } 
      }
    }
  }
}
