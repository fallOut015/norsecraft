package io.github.fallOut015.norsecraft.world.gen.surfacebuilders;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class NidavellirSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
    public NidavellirSurfaceBuilder(Codec<SurfaceBuilderConfig> codec) {
        super(codec);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void apply(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
//		BlockState blockstate = SurfaceBuilderTwo.REGOLITH;
//	    BlockState blockstate1 = SurfaceBuilderTwo.MYRKYLITE;
        BlockState blockstate = Blocks.AIR.defaultBlockState(), blockstate1 = Blocks.AIR.defaultBlockState();
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
        int i = -1;
        int j = (int)(noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
        int k = x & 15;
        int l = z & 15;
        int sealevel = 0;

        for(int i1 = startHeight; i1 >= 0; --i1) {
            blockpos$mutable.set(k, i1, l);
            BlockState blockstate2 = chunkIn.getBlockState(blockpos$mutable);
            if (blockstate2.isAir()) {
                i = -1;
            } else if (blockstate2.getBlock() == defaultBlock.getBlock()) {
                if (i == -1) {
                    if (j <= 0) {
                        blockstate = Blocks.AIR.defaultBlockState();
                        blockstate1 = defaultBlock;
                    } else if (i1 >= sealevel - 4 && i1 <= sealevel + 1) {
//	            		blockstate = SurfaceBuilderTwo.REGOLITH;
//	            		blockstate1 = SurfaceBuilderTwo.MYRKYLITE;
                    }

                    if (i1 < sealevel && (blockstate == null || blockstate.isAir())) {
                        if (biomeIn.getTemperature(blockpos$mutable.set(x, i1, z)) < 0.15F) {
                            //blockstate = SurfaceBuilderTwo.MYRKYLITE;
                        } else {
                            blockstate = defaultFluid;
                        }

                        blockpos$mutable.set(k, i1, l);
                    }

                    i = j;
                    if (i1 >= sealevel - 1) {
                        chunkIn.setBlockState(blockpos$mutable, blockstate, false);
                    } else if (i1 < sealevel - 7 - j) {
                        blockstate = Blocks.AIR.defaultBlockState();
                        blockstate1 = defaultBlock;
                        chunkIn.setBlockState(blockpos$mutable, Blocks.ICE.defaultBlockState(), false);
                    } else {
                        chunkIn.setBlockState(blockpos$mutable, blockstate1, false);
                    }
                } else if (i > 0) {
                    --i;
                    chunkIn.setBlockState(blockpos$mutable, blockstate1, false);
                    if (i == 0 && blockstate1.getBlock() == Blocks.SAND && j > 1) {
                        i = random.nextInt(4) + Math.max(0, i1 - 63);
                        blockstate1 = blockstate1.getBlock() == Blocks.RED_SAND ? Blocks.RED_SANDSTONE.defaultBlockState() : Blocks.SANDSTONE.defaultBlockState();
                    }
                }
            }
        }
    }
}