package rtg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;

/**
 * @author Zeno410
 */
public class WorldGenPond extends WorldGenerator {

    private IBlockState fill;

    /**
     * @param fill
     */
    public WorldGenPond(IBlockState fill) {

        this.fill = fill;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        x -= 8;

        for (z -= 8; y > 5 && world.isAirBlock(new BlockPos(x, y, z)); --y) {
            ;
        }

        if (y <= 4) {
            return false;
        }
        else {
            y -= 4;
            boolean[] aboolean = new boolean[2048];
            int l = rand.nextInt(4) + 4;
            int i1;

            for (i1 = 0; i1 < l; ++i1) {
                double d0 = rand.nextDouble() * 6.0D + 3.0D;
                double d1 = rand.nextDouble() * 4.0D + 2.0D;
                double d2 = rand.nextDouble() * 6.0D + 3.0D;
                double d3 = rand.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
                double d4 = rand.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
                double d5 = rand.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

                for (int k1 = 1; k1 < 15; ++k1) {
                    for (int l1 = 1; l1 < 15; ++l1) {
                        for (int i2 = 1; i2 < 7; ++i2) {
                            double d6 = ((double) k1 - d3) / (d0 / 2.0D);
                            double d7 = ((double) i2 - d4) / (d1 / 2.0D);
                            double d8 = ((double) l1 - d5) / (d2 / 2.0D);
                            double d9 = d6 * d6 + d7 * d7 + d8 * d8;

                            if (d9 < 1.0D) {
                                aboolean[(k1 * 16 + l1) * 8 + i2] = true;
                            }
                        }
                    }
                }
            }


            int j1;
            int j2;
            boolean flag;

            // this  algorithm can't put pond to the edge so we'll set all edges to not-pond
            for (j1 = 0; j1 < 8; j1++) {
                for (j2 = 0; j2 < 16; ++j2) {
                    i1 = 0;
                    aboolean[(i1 * 16 + j2) * 8 + j1] = false;
                    i1 = 15;
                    aboolean[(i1 * 16 + j2) * 8 + j1] = false;
                }
                for (i1 = 0; i1 < 16; ++i1) {
                    j2 = 0;
                    aboolean[(i1 * 16 + j2) * 8 + j1] = false;
                    j2 = 15;
                    aboolean[(i1 * 16 + j2) * 8 + j1] = false;
                }
            }

            // Zeno410
            // we're going to add some code to improve the sanity of the pond generation
            // first let's make an array of what columns will be in the pond

            boolean[] willBePond = new boolean[256];

            for (i1 = 1; i1 < 15; ++i1) {
                for (j2 = 1; j2 < 15; ++j2) {
                    for (j1 = 0; j1 < 4; ++j1) //up to 4 because that becomes the material
                    {
                        if (aboolean[(i1 * 16 + j2) * 8 + j1]) {
                            willBePond[(i1 * 16 + j2)] = true;
                        }
                    }
                }
            }

            // now let's get the lake edges
            boolean[] willBeShore = new boolean[256];

            for (i1 = 1; i1 < 14; ++i1) {
                for (j2 = 1; j2 < 14; ++j2) {
                    if (willBePond[(i1 * 16 + j2)]) {
                        continue;
                    }
                    if (j2 < 15 && willBePond[(i1 * 16 + j2 + 1)]) {
                        willBeShore[(i1 * 16 + j2)] = true;
                    }
                    if (j2 > 0 && willBePond[(i1 * 16 + j2 - 1)]) {
                        willBeShore[(i1 * 16 + j2)] = true;
                    }
                    if (i1 < 15 && willBePond[((i1 + 1) * 16 + j2)]) {
                        willBeShore[(i1 * 16 + j2)] = true;
                    }
                    if (i1 > 0 && willBePond[((i1 - 1) * 16 + j2)]) {
                        willBeShore[(i1 * 16 + j2)] = true;
                    }
                }
            }

            // now let's get the heights of the edges
            int[] heightCounts = new int[257];
            int shoreBlockCount = 0;
            for (i1 = 1; i1 < 15; ++i1) {
                for (j2 = 1; j2 < 15; ++j2) {
                    if (willBeShore[(i1 * 16 + j2)]) {
                        int topHeight = world.getTopSolidOrLiquidBlock(new BlockPos(x + i1, 0, z + j2)).getY();
                        if (topHeight < 1 || topHeight > 255) {
                            return false;//empty or too high column
                        }
                        heightCounts[topHeight] += 1;
                        shoreBlockCount++;
                    }
                }
            }

            // now get the median height and use for the lake level
            // unless it's more than 1 above the lowest shore level;
            int shoreSoFar = 0;
            int lakeLevel = 0;
            int bottomBlock = 257;
            for (int height = 0; height < 257; height++) {
                shoreSoFar += heightCounts[height];
                if (heightCounts[height] > 0) {
                    if (bottomBlock > height) {
                        bottomBlock = height;
                    }
                }
                if (shoreSoFar * 2 >= shoreBlockCount) {
                    lakeLevel = height;
                    //if (lakeLevel - bottomBlock > 2) return false; //it was going to be ugly
                    if (lakeLevel > bottomBlock) {
                        lakeLevel = bottomBlock;
                    }
                    continue;
                }
            }

            // set y to 3 below lake level so the top of the shore is the top of the lake level
            y = lakeLevel - 4;

            //not if anything will be left floating

            for (i1 = 0; i1 < 16; ++i1) {
                for (j2 = 0; j2 < 16; ++j2) {
                    if (willBePond[(i1 * 16 + j2)]) {
                        int top = world.getTopSolidOrLiquidBlock(new BlockPos(x + i1, 0, z + j2)).getY();
                        if (top > lakeLevel + 4) {
                            return false;
                        }
                    }
                }
            }

            // make sure the shore is at lake level
            for (i1 = 1; i1 < 15; ++i1) {
                for (j2 = 1; j2 < 15; ++j2) {
                    if (willBeShore[(i1 * 16 + j2)]) {
                        int shoreHeight = world.getTopSolidOrLiquidBlock(new BlockPos(x + i1, 0, z + j2)).getY();
                        if (shoreHeight < lakeLevel) {
                            Biome biomegenbase = world.getBiomeGenForCoords(new BlockPos(x + i1, 0, z + j2));
                            for (int height = shoreHeight; height < lakeLevel; height++) {


                                if (biomegenbase.topBlock == Blocks.MYCELIUM.getDefaultState()) {
                                    world.setBlockState(new BlockPos(x + i1, height, z + j2), Blocks.MYCELIUM.getDefaultState(), 2);
                                }
                                else {
                                    world.setBlockState(new BlockPos(x + i1, height, z + j2), Blocks.GRASS.getDefaultState(), 2);
                                }
                            }
                        }
                    }
                }
            }


            for (i1 = 0; i1 < 16; ++i1) {
                for (j2 = 0; j2 < 16; ++j2) {
                    for (j1 = 0; j1 < 8; ++j1) {
                        flag = !aboolean[(i1 * 16 + j2) * 8 + j1] && (i1 < 15 && aboolean[((i1 + 1) * 16 + j2) * 8 + j1] || i1 > 0 && aboolean[((i1 - 1) * 16 + j2) * 8 + j1] || j2 < 15 && aboolean[(i1 * 16 + j2 + 1) * 8 + j1] || j2 > 0 && aboolean[(i1 * 16 + (j2 - 1)) * 8 + j1] || j1 < 7 && aboolean[(i1 * 16 + j2) * 8 + j1 + 1] || j1 > 0 && aboolean[(i1 * 16 + j2) * 8 + (j1 - 1)]);

                        if (flag) {
                            Material material = world.getBlockState(new BlockPos(x + i1, y + j1, z + j2)).getBlock().getMaterial();

                            if (j1 >= 4 && material.isLiquid()) {
                                return false;
                            }

                            if (j1 < 4 && !material.isSolid() && world.getBlockState(new BlockPos(x + i1, y + j1, z + j2)) != this.fill) {
                                return false;
                            }
                        }
                    }
                }
            }

            for (i1 = 0; i1 < 16; ++i1) {
                for (j2 = 0; j2 < 16; ++j2) {
                    for (j1 = 0; j1 < 8; ++j1) {
                        if (j1 < 4) {
                            if (aboolean[(i1 * 16 + j2) * 8 + j1]) {
                                world.setBlockState(new BlockPos(x + i1, y + j1, z + j2), fill, 2);
                            }
                        }
                        else {
                            // air
                            if (willBePond[i1 * 16 + j2]) {
                                world.setBlockState(new BlockPos(x + i1, y + j1, z + j2), Blocks.AIR.getDefaultState(), 2);
                            }
                        }

                    }
                }
            }

            for (i1 = 0; i1 < 16; ++i1) {
                for (j2 = 0; j2 < 16; ++j2) {
                    for (j1 = 4; j1 < 8; ++j1) {
                        if (aboolean[(i1 * 16 + j2) * 8 + j1] && world.getBlockState(new BlockPos(x + i1, y + j1 - 1, z + j2)) == Blocks.DIRT.getDefaultState() && world.getLightFor(EnumSkyBlock.SKY, new BlockPos(x + i1, y + j1, z + j2)) > 0) {
                            Biome biomegenbase = world.getBiomeGenForCoords(new BlockPos(x + i1, 0, z + j2));

                            if (biomegenbase.topBlock == Blocks.MYCELIUM.getDefaultState()) {
                                world.setBlockState(new BlockPos(x + i1, y + j1 - 1, z + j2), Blocks.MYCELIUM.getDefaultState(), 2);
                            }
                            else {
                                world.setBlockState(new BlockPos(x + i1, y + j1 - 1, z + j2), biomegenbase.topBlock, 2);
                            }
                        }
                    }
                }
            }

            if (this.fill.getBlock().getMaterial() == Material.lava) {
                for (i1 = 0; i1 < 16; ++i1) {
                    for (j2 = 0; j2 < 16; ++j2) {
                        for (j1 = 0; j1 < 8; ++j1) {
                            flag = !aboolean[(i1 * 16 + j2) * 8 + j1] && (i1 < 15 && aboolean[((i1 + 1) * 16 + j2) * 8 + j1] || i1 > 0 && aboolean[((i1 - 1) * 16 + j2) * 8 + j1] || j2 < 15 && aboolean[(i1 * 16 + j2 + 1) * 8 + j1] || j2 > 0 && aboolean[(i1 * 16 + (j2 - 1)) * 8 + j1] || j1 < 7 && aboolean[(i1 * 16 + j2) * 8 + j1 + 1] || j1 > 0 && aboolean[(i1 * 16 + j2) * 8 + (j1 - 1)]);

                            if (flag && (j1 < 4 || rand.nextInt(2) != 0) && world.getBlockState(new BlockPos(x + i1, y + j1, z + j2)).getBlock().getMaterial().isSolid()) {
                                world.setBlockState(new BlockPos(x + i1, y + j1, z + j2), Blocks.STONE.getDefaultState(), 2);
                            }
                        }
                    }
                }
            }

            if (this.fill.getBlock().getMaterial() == Material.water) {
                for (i1 = 0; i1 < 16; ++i1) {
                    for (j2 = 0; j2 < 16; ++j2) {
                        byte b0 = 4;

                        if (world.canBlockFreezeWater(new BlockPos(x + i1, y + b0, z + j2))) {
                            world.setBlockState(new BlockPos(x + i1, y + b0, z + j2), Blocks.ICE.getDefaultState(), 2);
                        }
                    }
                }
            }

            // cut down shore above lake level
            for (i1 = 1; i1 < 15; ++i1) {
                for (j2 = 1; j2 < 15; ++j2) {
                    if (willBeShore[(i1 * 16 + j2)]) {
                        int shoreHeight = world.getTopSolidOrLiquidBlock(new BlockPos(x + i1, 0, z + j2)).getY();
                        if (shoreHeight > lakeLevel) {
                            shoreHeight--;
                            Biome biomegenbase = world.getBiomeGenForCoords(new BlockPos(x + i1, 0, z + j2));
                            world.setBlockState(new BlockPos(x + i1, shoreHeight, z + j2), Blocks.AIR.getDefaultState(), 2);
                            shoreHeight--;

                            if (biomegenbase.topBlock == Blocks.MYCELIUM.getDefaultState()) {
                                world.setBlockState(new BlockPos(x + i1, shoreHeight, z + j2), Blocks.MYCELIUM.getDefaultState(), 2);
                            }
                            else {
                                world.setBlockState(new BlockPos(x + i1, shoreHeight, z + j2), biomegenbase.topBlock, 2);
                            }
                        }
                    }
                }
            }
            return true;
        }
    }
}
