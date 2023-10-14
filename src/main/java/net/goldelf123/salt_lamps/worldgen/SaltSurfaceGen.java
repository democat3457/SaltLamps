package net.goldelf123.salt_lamps.worldgen;

import java.util.Random;
import net.goldelf123.salt_lamps.Config;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeOcean;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class SaltSurfaceGen implements IWorldGenerator {

  private final WorldGenSalt saltGen = new WorldGenSalt(Config.saltClusterSize);

  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
    BlockPos blockPos = new BlockPos(chunkX * 16 + 8 + random.nextInt(16), 0, chunkZ * 16 + 8 + random.nextInt(16));
    if (world.provider.getDimension() == 0 && world.getBiomeForCoordsBody(blockPos).getClass() == BiomeOcean.class && Config.canGenerateSalt) {
      for (int i = 0; i < Config.saltPerChunk; i++) {
        this.saltGen.generate(world, random, world.getTopSolidOrLiquidBlock(blockPos));
      }
    }
  }
}
