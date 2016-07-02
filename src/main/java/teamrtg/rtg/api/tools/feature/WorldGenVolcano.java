package teamrtg.rtg.api.tools.feature;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import teamrtg.rtg.api.module.Mods;
import teamrtg.rtg.api.util.math.TerrainMath;
import teamrtg.rtg.api.util.noise.CellNoise;
import teamrtg.rtg.api.util.noise.OpenSimplexNoise;

public class WorldGenVolcano
{
    protected static IBlockState volcanoBlock = Mods.RTG.config.VOLCANO_BLOCK.get();
	protected static IBlockState volcanoPatchBlock = Mods.RTG.config.VOLCANO_BLOCK_MIX_1.get();
	protected static IBlockState volcanoPatchBlock2 = Mods.RTG.config.VOLCANO_BLOCK_MIX_2.get();
	protected static IBlockState volcanoPatchBlock3 = Mods.RTG.config.VOLCANO_BLOCK_MIX_3.get();
    protected static IBlockState lavaBlock = Mods.RTG.config.ENABLE_VOLCANO_ERUPTIONS.get() ? Blocks.FLOWING_LAVA.getDefaultState() : Blocks.LAVA.getDefaultState();

	// How much stretched the vent/mouth is
	private static final float ventEccentricity = 8f;
	private static final float ventRadius = 7f;

	private static final int lavaHeight = 138 + 3 + (Mods.RTG.config.ENABLE_VOLCANO_ERUPTIONS.get() ? 5 : 0);	// + 3 to account for lava cone tip
	private static final int baseVolcanoHeight = 142 + 8;

	public static void build(IBlockState[] blocks, World world, Random mapRand, int baseX, int baseY, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float[] noise)
	{
		int i, j;
		float distanceEll, height, terrainHeight, obsidian;
		IBlockState b;

		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				i = (chunkX * 16) + x;
				j = (chunkY * 16) + z;

				// Distance in blocks from the center of the volcano
				distanceEll = (float)TerrainMath.dis2Elliptic(i, j, baseX * 16, baseY * 16,
						simplex.noise2(i / 250f, j / 250f) * ventEccentricity,
						simplex.octave(1).noise2(i / 250f, j / 250f) * ventEccentricity);

				// Height above which obsidian is placed
				obsidian = -5f + distanceEll;
				obsidian += simplex.octave(1).noise2(i / 55f, j / 55f) * 12f;
				obsidian += simplex.octave(2).noise2(i / 25f, j / 25f) * 5f;
				obsidian += simplex.octave(3).noise2(i / 9f, j / 9f) * 3f;

				// Make the volcanoes "mouth" more interesting
				float ventNoise = simplex.noise2(i / 12f, j / 12f) * 3f;
				ventNoise += simplex.octave(1).noise2(i / 4f, j / 4f) * 1.5f;

				// Are we in the volcano's throat/conduit?
				if(distanceEll < ventRadius + ventNoise)
				{
					height = simplex.noise2(i / 5f, j / 5f) * 2f;
					for (int y = 255; y > -1; y--)
					{
						// Above lava
						if(y > lavaHeight)
						{
							if(blocks[cta(x, y, z)] != Blocks.AIR.getDefaultState())
							{
								blocks[cta(x, y, z)] = Blocks.AIR.getDefaultState();
							}
						}
						// Below lava and above obsidian
						else if(y > obsidian && y < (lavaHeight - 9) + height)
						{
						    blocks[cta(x, y, z)] = volcanoBlock;
						}
						// In lava
						else if(y < lavaHeight + 1)
						{
							if(distanceEll + y < lavaHeight + 3) // + 3 to cut the tip of the lava
							blocks[cta(x, y, z)] = lavaBlock;
						}
						// Below obsidian
						else if(y < obsidian + 1)
						{
							if(blocks[cta(x, y, z)] == Blocks.AIR.getDefaultState())
							{
							    blocks[cta(x, y, z)] = Blocks.STONE.getDefaultState();
							}
							else
							{
								break;
							}
						}
					}
				}
				else
				{
					terrainHeight = baseVolcanoHeight - (float)Math.pow(distanceEll, 0.89f);
					terrainHeight += simplex.octave(1).noise2(i / 112f, j / 112f) * 5.5f;
					terrainHeight += simplex.octave(2).noise2(i / 46f, j / 46f) * 4.5f;
					terrainHeight += simplex.octave(3).noise2(i / 16f, j / 16f) * 2.5f;
					terrainHeight += simplex.octave(4).noise2(i / 5f, j / 5f) * 1f;

					if(terrainHeight > noise[x * 16 + z])
					{
						noise[x * 16 + z] = terrainHeight;
					}

					for (int y = 255; y > -1; y--)
					{
						if(y <= terrainHeight)
						{
							b = blocks[cta(x, y, z)];

							if(b == Blocks.AIR.getDefaultState() || b == Blocks.WATER.getDefaultState())
							{
                                /*************************************
                                 * WARNING: Spaghetti surfacing code *
                                 *************************************/

								if(y > obsidian)
								{
                                    if(distanceEll > 10) {

                                        // Patches
                                        if (distanceEll < 50 && isOnSurface(x, y, z, blocks)) {
                                            float patchNoise = simplex.noise2(i / 10f, j / 10f) * 1.3f;
                                            patchNoise += simplex.octave(2).noise2(i / 30f, j / 30f) * .9;
                                            patchNoise += simplex.octave(3).noise2(i / 5f, j / 5f) * .6;
                                            if (patchNoise > .85) {
                                                blocks[cta(x, y, z)] = volcanoPatchBlock;   // Cobble
                                                continue;
                                            }
                                        }

                                        if (distanceEll < 75 && isOnSurface(x, y, z, blocks)) {
                                            float patchNoise = simplex.noise2(i / 10f, j / 10f) * 1.3f;
                                            patchNoise += simplex.octave(4).noise2(i / 30f, j / 30f) * .9;
                                            patchNoise += simplex.octave(5).noise2(i / 5f, j / 5f) * .5;
                                            if (patchNoise > .92) {
                                                blocks[cta(x, y, z)] = volcanoPatchBlock2;  // Gravel
                                                continue;
                                            }
                                        }
                                        if (distanceEll < 75 && isOnSurface(x, y, z, blocks)) {
                                            float patchNoise = simplex.noise2(i / 10f, j / 10f) * 1.3f;
                                            patchNoise += simplex.octave(6).noise2(i / 30f, j / 30f) * .7;
                                            patchNoise += simplex.octave(7).noise2(i / 5f, j / 5f) * .7;
                                            if (patchNoise > .93) {
                                                blocks[cta(x, y, z)] = volcanoPatchBlock3;  // Coal block
                                                continue;
                                            }
                                        }
                                    }

                                    // Surfacing
                                    if(distanceEll < 70 + simplex.noise2(x/26f, y/26f) * 5)
                                    {
                                    	if (mapRand.nextInt(20) == 0) {
                                    		
                                            b = volcanoPatchBlock3;
                                    	}
                                    	else {
                                    		
                                            b = volcanoBlock;
                                    	}
                                    }
                                    else if(distanceEll < 75 + simplex.noise2(x/26f, y/26f) * 5)
                                    {
                                        // Jittering in the base, to smooth the transition
                                        float powerNoise = simplex.octave(3).noise2(i / 40, j / 40f) * 2;
                                        if(mapRand.nextInt(1+(int)Math.pow(Math.abs(distanceEll-(75 + simplex.noise2(x/26f, y/26f) * 5)),1.5+powerNoise)+1) == 0)
                                        {
                                        	if (mapRand.nextInt(20) == 0) {
                                        		
                                                b = volcanoPatchBlock2;
                                        	}
                                        	else {
                                        		
                                                b = Blocks.STONE.getDefaultState(); // Stone so that surfacing will run (so this usually becomes grass)
                                        	}
                                        }
                                        else
                                        {
											b = volcanoBlock;
                                        }
                                    }
                                    else
                                    {
                                    	b = Blocks.STONE.getDefaultState(); // Stone so that surfacing will run (so this usually becomes grass)
                                    }
								}
								else
								{
									b = Blocks.STONE.getDefaultState();
								}
							}
							else
							{
								break;
							}

							blocks[cta(x, y, z)] = b;
						}
					}
				}
			}
		}
	}

    private static boolean isOnSurface(int x, int y, int z, IBlockState[] blocks) {
        return (blocks[cta(x, y+1, z)] == Blocks.AIR.getDefaultState());
    }

    public static int cta(int x, int y, int z)
    {
    	return (x * 16 + z) * 256 + y;
    }
}