package net.goldelf123.salt_lamps.blocks;

import java.util.Random;
import net.goldelf123.salt_lamps.Config;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;







public class BlockPureLamp
  extends BlockSaltLamp
{
  public BlockPureLamp(String nameIn) {
    super(nameIn);
    setLightLevel(0.9F);
  }

  
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    if (worldIn.isRemote || !Config.enableDiffusion) {
      
      if (playerIn.inventory.getCurrentItem().getItem() instanceof net.minecraft.item.ItemPotion || playerIn.inventory
        .getCurrentItem().getItem() instanceof net.minecraft.item.ItemSplashPotion || playerIn.inventory
        .getCurrentItem().getItem() instanceof net.minecraft.item.ItemLingeringPotion) {
        
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof TileEntityPureLamp)
        {
          if (!playerIn.inventory.getCurrentItem().getItem().hasEffect(playerIn.inventory.getCurrentItem()) && ((TileEntityPureLamp)tileEntity).isDiffusing())
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

    
    TileEntity tileentity = worldIn.getTileEntity(pos);
    if (tileentity instanceof TileEntityPureLamp) {
      
      if (playerIn.inventory.getCurrentItem().getItem() instanceof net.minecraft.item.ItemBucketMilk) {
        
        if (!playerIn.capabilities.isCreativeMode) {
          
          playerIn.inventory.getCurrentItem().shrink(1);
          playerIn.inventory.addItemStackToInventory(new ItemStack(Items.BUCKET));
        } 
        
        ((TileEntityPureLamp)tileentity).setEffects();
        ((TileEntityPureLamp)tileentity).diffuseEffects();
        
        worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
      }
      else if (playerIn.inventory.getCurrentItem().getItem() instanceof net.minecraft.item.ItemPotion || playerIn.inventory
        .getCurrentItem().getItem() instanceof net.minecraft.item.ItemSplashPotion || playerIn.inventory
        .getCurrentItem().getItem() instanceof net.minecraft.item.ItemLingeringPotion) {
        
        if (!playerIn.inventory.getCurrentItem().getItem().hasEffect(playerIn.inventory.getCurrentItem()) && ((TileEntityPureLamp)tileentity).isDiffusing()) {
          
          if (!playerIn.capabilities.isCreativeMode) {
            
            playerIn.inventory.getCurrentItem().shrink(1);
            playerIn.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
          } 
          
          ((TileEntityPureLamp)tileentity).clearEffects();
          worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
        } 
      } 
      
      return true;
    } 
    
    return false;
  }


  
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityPureLamp();
  }

  
  @SideOnly(Side.CLIENT)
  public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
    TileEntity tileentity = worldIn.getTileEntity(pos);
    if (tileentity instanceof TileEntityPureLamp)
    {
      if (((TileEntityPureLamp)tileentity).isDiffusing())
      {
        for (int i = 0; i < 1; i++) {
          
          double d0 = (pos.getX() + 0.4F + rand.nextFloat() * 0.2F);
          double d1 = (pos.getY() + 0.7F + rand.nextFloat() * 0.3F);
          double d2 = (pos.getZ() + 0.4F + rand.nextFloat() * 0.2F);
          worldIn.spawnParticle(EnumParticleTypes.SPELL_MOB, d0, d1, d2, 1.0D, 1.0D, 1.0D, new int[0]);
        } 
      }
    }
  }
}
