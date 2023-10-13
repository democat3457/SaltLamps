package net.goldelf123.salt_lamps.blocks;

import net.goldelf123.salt_lamps.Config;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;




public class BlockMobLamp
  extends BlockSaltLamp
{
  private boolean affectsHostileMobs = false;
  private boolean affectsPassiveMobs = false;
  
  public BlockMobLamp(String nameIn, boolean affectsHostileMobsIn, boolean affectsPassiveMobsIn) {
    super(nameIn);
    this.affectsHostileMobs = affectsHostileMobsIn;
    this.affectsPassiveMobs = affectsPassiveMobsIn;
  }

  
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    if (worldIn.isRemote || !Config.enableDiffusion) {
      
      if (playerIn.inventory.getCurrentItem().getItem() instanceof net.minecraft.item.ItemPotion || playerIn.inventory
        .getCurrentItem().getItem() instanceof net.minecraft.item.ItemSplashPotion || playerIn.inventory
        .getCurrentItem().getItem() instanceof net.minecraft.item.ItemLingeringPotion) {
        
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TileEntityMobLamp)
        {
          if (!playerIn.inventory.getCurrentItem().getItem().hasEffect(playerIn.inventory.getCurrentItem()) && ((TileEntityMobLamp)tileentity).isDiffusing())
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
      if (tileentity instanceof TileEntityMobLamp)
      {
        if (!playerIn.inventory.getCurrentItem().getItem().hasEffect(playerIn.inventory.getCurrentItem()) && ((TileEntityMobLamp)tileentity).isDiffusing()) {
          
          if (!playerIn.capabilities.isCreativeMode) {
            
            playerIn.inventory.getCurrentItem().shrink(1);
            playerIn.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
          } 
          
          ((TileEntityMobLamp)tileentity).clearEffects();
          worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
        }
        else if (playerIn.inventory.getCurrentItem().getItem().hasEffect(playerIn.inventory.getCurrentItem())) {
          
          if (!playerIn.capabilities.isCreativeMode) {
            
            playerIn.inventory.getCurrentItem().shrink(1);
            playerIn.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
          } 
          
          ((TileEntityMobLamp)tileentity).setEffects(PotionUtils.getEffectsFromStack(playerIn.inventory.getCurrentItem()));
          ((TileEntityMobLamp)tileentity).diffuseEffects();
          
          worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
        } 
      }
      
      return true;
    } 
    
    return false;
  }


  
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return (new TileEntityMobLamp()).setAffectedEntities(this.affectsHostileMobs, this.affectsPassiveMobs);
  }
}
