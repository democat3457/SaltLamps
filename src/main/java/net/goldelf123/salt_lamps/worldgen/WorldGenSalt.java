package net.goldelf123.salt_lamps.worldgen;

import java.util.Random;
import net.goldelf123.salt_lamps.blocks.SaltLampBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSalt
  extends WorldGenerator
{
  private final Block block = (Block)SaltLampBlocks.saltBlock;
  
  private final int numberOfBlocks;
  
  public WorldGenSalt(int numBlocks) {
    this.numberOfBlocks = numBlocks;
  }

  
  public boolean generate(World worldIn, Random rand, BlockPos position) {
    if (worldIn.getBlockState(position).getMaterial() != Material.WATER)
    {
      return false;
    }

    
    int i = rand.nextInt(this.numberOfBlocks);
    
    for (int k = position.getX() - i; k <= position.getX() + i; k++) {
      
      for (int l = position.getZ() - i; l <= position.getZ() + i; l++) {
        
        int i1 = k - position.getX();
        int j1 = l - position.getZ();
        
        if (i1 * i1 + j1 * j1 <= i * i)
        {
          for (int k1 = position.getY() - 1; k1 <= position.getY() + 1; k1++) {
            
            BlockPos blockpos = new BlockPos(k, k1, l);
            Block block = worldIn.getBlockState(blockpos).getBlock();
            
            if (block == Blocks.GRAVEL || block == Blocks.SAND || block == Blocks.CLAY)
            {
              worldIn.setBlockState(blockpos, this.block.getDefaultState(), 2);
            }
          } 
        }
      } 
    } 
    
    return true;
  }
}
