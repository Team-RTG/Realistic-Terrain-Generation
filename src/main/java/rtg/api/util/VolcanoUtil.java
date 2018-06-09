package rtg.api.util;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.RTGAPI;
import rtg.api.util.WorldUtil.Terrain;
import rtg.api.world.IRTGWorld;

//TODO [1.12] Merge all of this functionality into the volvano generator.
@UtilityClass
public final class VolcanoUtil
{
    private VolcanoUtil() {}

    private static final IBlockState DEFAULT_VOLCANO_MAIN_BLOCK = Blocks.OBSIDIAN.getDefaultState();
    private static final IBlockState DEFAULT_VOLCANO_MIX1_BLOCK = Blocks.COBBLESTONE.getDefaultState();
    private static final IBlockState DEFAULT_VOLCANO_MIX2_BLOCK = Blocks.GRAVEL.getDefaultState();
    private static final IBlockState DEFAULT_VOLCANO_MIX3_BLOCK = Blocks.COAL_BLOCK.getDefaultState();

    // How much stretched the vent/mouth is
    private static final float ventEccentricity  = 8f * RTGAPI.config().VOLCANO_CALDERA_MULTIPLIER.get();
    private static final float ventRadius        = 7f * RTGAPI.config().VOLCANO_CALDERA_MULTIPLIER.get();
    private static final int lavaHeight          = 138 + 3 + (RTGAPI.config().ENABLE_VOLCANO_ERUPTIONS.get() ? 5 : 0);    // + 3 to account for lava cone tip
    private static final int baseVolcanoHeight   = 142 + 8;
    private static IBlockState volcanoBlock1     = getVolcanoBlock(RTGAPI.config().VOLCANO_MAIN_BLOCK.get(), DEFAULT_VOLCANO_MAIN_BLOCK);
    private static IBlockState volcanoBlock2     = getVolcanoBlock(RTGAPI.config().VOLCANO_MIX1_BLOCK.get(), DEFAULT_VOLCANO_MIX1_BLOCK);
    private static IBlockState volcanoBlock3     = getVolcanoBlock(RTGAPI.config().VOLCANO_MIX2_BLOCK.get(), DEFAULT_VOLCANO_MIX2_BLOCK);
    private static IBlockState volcanoBlock4     = getVolcanoBlock(RTGAPI.config().VOLCANO_MIX3_BLOCK.get(), DEFAULT_VOLCANO_MIX3_BLOCK);
    private static IBlockState lavaBlock         = RTGAPI.config().ENABLE_VOLCANO_ERUPTIONS.get() ? Blocks.FLOWING_LAVA.getDefaultState() : Blocks.LAVA.getDefaultState();
    private static boolean enableVolcanoConduits = RTGAPI.config().ENABLE_VOLCANO_CONDUITS.get();
    private static int volcanoConduitDepth       = RTGAPI.config().VOLCANO_CONDUIT_DEPTH.get();

    public static void build(ChunkPrimer primer, Random mapRand, int baseX, int baseY, int chunkX, int chunkY, IRTGWorld rtgWorld, float[] noise) {

        int i, j;
        float distanceEll, height, terrainHeight, obsidian;
        IBlockState b;

        //lavaBlock = Blocks.MAGMA.getDefaultState();

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                i = (chunkX * 16) + x;
                j = (chunkY * 16) + z;

                // Distance in blocks from the center of the volcano
                distanceEll = (float) Terrain.dis2Elliptic(i, j, baseX * 16, baseY * 16,
                    rtgWorld.simplexInstance(0).noise2f(i / 250f, j / 250f) * ventEccentricity,
                    rtgWorld.simplexInstance(1).noise2f(i / 250f, j / 250f) * ventEccentricity);

                // Height above which obsidian is placed
                obsidian = -5f + distanceEll;
                obsidian += rtgWorld.simplexInstance(1).noise2f(i / 55f, j / 55f) * 12f;
                obsidian += rtgWorld.simplexInstance(2).noise2f(i / 25f, j / 25f) * 5f;
                obsidian += rtgWorld.simplexInstance(3).noise2f(i / 9f, j / 9f) * 3f;

                // Make the volcanoes "mouth" more interesting
                float ventNoise = rtgWorld.simplexInstance(0).noise2f(i / 12f, j / 12f) * 3f;
                ventNoise += rtgWorld.simplexInstance(1).noise2f(i / 4f, j / 4f) * 1.5f;

                // Are we in the volcano's throat/conduit?
                if (distanceEll < ventRadius + ventNoise) {
                    height = rtgWorld.simplexInstance(0).noise2f(i / 5f, j / 5f) * 2f;
                    for (int y = 255; y > -1; y--) {
                        // Above lava
                        if (y > lavaHeight) {
                            if (primer.getBlockState(x, y, z) == Blocks.AIR.getDefaultState()) { primer.setBlockState(x, y, z, Blocks.AIR.getDefaultState()); }
                        }
                        // Below lava and above obsidian
                        else if (y > obsidian && y < (lavaHeight - 9) + height) {
                            if (enableVolcanoConduits && y >= volcanoConduitDepth) { primer.setBlockState(x, y, z, lavaBlock); }
                            else { primer.setBlockState(x, y, z, volcanoBlock1); }
                        }
                        // In lava
                        else if (y < lavaHeight + 1) {
                            // + 3 to cut the tip of the lava
                            if (distanceEll + y < lavaHeight + 3) { primer.setBlockState(x, y, z, lavaBlock); }
                        }
                        // Below obsidian
                        else if (y < obsidian + 1) {
                            if (primer.getBlockState(x, y, z) == Blocks.AIR.getDefaultState()) {
                                if (enableVolcanoConduits && y >= volcanoConduitDepth) { primer.setBlockState(x, y, z, lavaBlock); }
                                else { primer.setBlockState(x, y, z, Blocks.STONE.getDefaultState()); }
                            }
                            else { break; }
                        }
                    }
                }
                else {
                    terrainHeight = baseVolcanoHeight - (float) Math.pow(distanceEll, 0.89f);
                    terrainHeight += rtgWorld.simplexInstance(1).noise2f(i / 112f, j / 112f) * 5.5f;
                    terrainHeight += rtgWorld.simplexInstance(2).noise2f(i / 46f, j / 46f) * 4.5f;
                    terrainHeight += rtgWorld.simplexInstance(3).noise2f(i / 16f, j / 16f) * 2.5f;
                    terrainHeight += rtgWorld.simplexInstance(4).noise2f(i / 5f, j / 5f) * 1f;

                    if (terrainHeight > noise[x * 16 + z]) {
                        noise[x * 16 + z] = terrainHeight;
                    }

                    for (int y = 255; y > -1; y--) {
                        if (y <= terrainHeight) {
                            b = primer.getBlockState(x, y, z);

                            if (b == Blocks.AIR.getDefaultState() || b == Blocks.WATER.getDefaultState()) {
                                /* ************************************
                                 * WARNING: Spaghetti surfacing code *
                                 *************************************/

                                if (y > obsidian) {
                                    if (distanceEll > 10) {

                                        // Patches
                                        if (distanceEll < 50 && isOnSurface(primer, x, y, z)) {
                                            float patchNoise = rtgWorld.simplexInstance(0).noise2f(i / 10f, j / 10f) * 1.3f;
                                            patchNoise += rtgWorld.simplexInstance(2).noise2f(i / 30f, j / 30f) * .9;
                                            patchNoise += rtgWorld.simplexInstance(3).noise2f(i / 5f, j / 5f) * .6;
                                            if (patchNoise > .85) {
                                                primer.setBlockState(x, y, z, volcanoBlock2); // Cobble
                                                continue;
                                            }
                                        }

                                        if (distanceEll < 75 && isOnSurface(primer, x, y, z)) {
                                            float patchNoise = rtgWorld.simplexInstance(0).noise2f(i / 10f, j / 10f) * 1.3f;
                                            patchNoise += rtgWorld.simplexInstance(4).noise2f(i / 30f, j / 30f) * .9;
                                            patchNoise += rtgWorld.simplexInstance(5).noise2f(i / 5f, j / 5f) * .5;
                                            if (patchNoise > .92) {
                                                primer.setBlockState(x, y, z, volcanoBlock3); // Gravel
                                                continue;
                                            }
                                        }
                                        if (distanceEll < 75 && isOnSurface(primer, x, y, z)) {
                                            float patchNoise = rtgWorld.simplexInstance(0).noise2f(i / 10f, j / 10f) * 1.3f;
                                            patchNoise += rtgWorld.simplexInstance(6).noise2f(i / 30f, j / 30f) * .7;
                                            patchNoise += rtgWorld.simplexInstance(7).noise2f(i / 5f, j / 5f) * .7;
                                            if (patchNoise > .93) {
                                                primer.setBlockState(x, y, z, volcanoBlock4); // Coal block
                                                continue;
                                            }
                                        }
                                    }

                                    // Surfacing
                                    if (distanceEll < 70 + rtgWorld.simplexInstance(0).noise2f(x / 26f, y / 26f) * 5) {
                                        if (mapRand.nextInt(20) == 0) { b = volcanoBlock4; }
                                        else { b = volcanoBlock1; }
                                    }

                                    else if (distanceEll < 75 + rtgWorld.simplexInstance(0).noise2f(x / 26f, y / 26f) * 5) {
                                        // Jittering in the base, to smooth the transition
                                        float powerNoise = rtgWorld.simplexInstance(3).noise2f(i / 40, j / 40f) * 2;

                                        if (mapRand.nextInt(1 + (int) Math.pow(Math.abs(distanceEll - (75 + rtgWorld.simplexInstance(0).noise2f(x / 26f, y / 26f) * 5)), 1.5 + powerNoise) + 1) == 0) {

                                            if (mapRand.nextInt(20) == 0) { b = volcanoBlock3; }
                                            else { b = Blocks.STONE.getDefaultState(); }// Stone so that surfacing will run (so this usually becomes grass)
                                        }
                                        else { b = volcanoBlock1; }
                                    }
                                    else { b = Blocks.STONE.getDefaultState(); }// Stone so that surfacing will run (so this usually becomes grass)
                                }
                                else { b = Blocks.STONE.getDefaultState(); }
                            }
                            else { break; }

                            primer.setBlockState(x, y, z, b);
                        }
                    }
                }
            }
        }
    }

    private static boolean isOnSurface(ChunkPrimer primer, int x, int y, int z) {
        return primer.getBlockState(x, y + 1, z) == Blocks.AIR.getDefaultState();
    }

    private static IBlockState getVolcanoBlock(String blockID, IBlockState defaultBlock) {
        return BlockUtil.getBlockStateFromCfgString(blockID, defaultBlock);
    }
}