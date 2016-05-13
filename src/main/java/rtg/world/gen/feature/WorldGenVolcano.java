package rtg.world.gen.feature;

import cpw.mods.fml.common.registry.GameData;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import rtg.config.rtg.ConfigRTG;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.TerrainMath;

import java.util.Random;

public class WorldGenVolcano 
{
    protected static Block volcanoBlock = GameData.getBlockRegistry().getObject(ConfigRTG.volcanoBlockId);
    protected static byte volcanoByte = (byte) ConfigRTG.volcanoBlockByte;
    protected static Block lavaBlock = ConfigRTG.enableVolcanoEruptions ? Blocks.flowing_lava : Blocks.lava;

	// How much stretched the vent/mouth is
	private static final float ventEccentricity = 8f;
	private static final float ventRadius = 6f;

	private static final int lavaHeight = 142 + (ConfigRTG.enableVolcanoEruptions ? 5 : 0);
	private static final int baseVolcanoHeight = 142 + 8;


	public static void build(Block[] blocks, byte[] metadata, World world, Random mapRand, int baseX, int baseY, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float[] noise)
	{
		int i, j;
		float distanceEll, height, obsidian;
		Block b;
		byte meta;

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
				obsidian = 5f + distanceEll;
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
							if(blocks[cta(x, y, z)] != Blocks.air)
							{
								blocks[cta(x, y, z)] = Blocks.air;
							}
						}
						// Below lava and above obsidian
						else if(y > obsidian && y < (lavaHeight - 9) + height)
						{
						    blocks[cta(x, y, z)] = volcanoBlock;
						    metadata[cta(x, y, z)] = volcanoByte;
						}
						// In lava
						else if(y < lavaHeight + 1)
						{
							blocks[cta(x, y, z)] = lavaBlock;
						}
						// Below obsidian
						else if(y < obsidian + 1)
						{
							if(blocks[cta(x, y, z)] == Blocks.air)
							{
							    blocks[cta(x, y, z)] = Blocks.stone;
							    metadata[cta(x, y, z)] = (byte)0;
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
					height = baseVolcanoHeight - (float)Math.pow(distanceEll, 0.88f);
					height += simplex.octave(1).noise2(i / 112f, j / 112f) * 6f;
					height += simplex.octave(2).noise2(i / 46f, j / 46f) * 5f;
					height += simplex.octave(3).noise2(i / 16f, j / 16f) * 2.5f;
					height += simplex.octave(4).noise2(i / 5f, j / 5f) * 1f;

					if(height > noise[x * 16 + z])
					{
						noise[x * 16 + z] = height;
					}

					for (int y = 255; y > -1; y--)
					{
						if(y <= height)
						{
							b = blocks[cta(x, y, z)];
							//meta = metadata[cta(x, y, z)];

							if(b == Blocks.air)
							{
								if(y > obsidian)
								{
									b = volcanoBlock;
									meta = volcanoByte;
								}
								else
								{
									b = Blocks.stone;
									meta = (byte)0;
								}
							}
							else
							{
								break;
							}

							blocks[cta(x, y, z)] = b;
							metadata[cta(x, y, z)] = meta;
						}
					}
				}
			}
		}
	}
	
    public static int cta(int x, int y, int z)
    {
    	return (x * 16 + z) * 256 + y;
    }
}