package rtg.world.gen.surface.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.util.CanyonColour;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;

public class SurfaceVanillaMesa extends SurfaceBase {
	public SurfaceVanillaMesa(BiomeConfig config, Block top, byte topByte, Block fill, byte fillByte)
	{
		super(config, top, topByte, fill, fillByte);
    }

    @Override
    public void paintTerrain(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {

        float c = CliffCalculator.calc(x, y, noise);
        boolean cliff = c > 1.3f;
        float noiseHeight = noise[y*16+x];

        for (int k = 255; k > -1; k--) {
        	Block b = blocks[(y * 16 + x) * 256 + k];
            if (b == Blocks.air) {
                depth = -1;
            } else if (b == Blocks.stone) {
                depth++;
                float effectiveHeight = noiseHeight-(float)depth;

                if (depth > -1 && depth < 12) {
                    if (cliff) {
	        			blocks[(y * 16 + x) * 256 + k] = CanyonColour.MESA.getBlockForHeight(i, effectiveHeight,j);
	        		    metadata[(y * 16 + x) * 256 + k] = CanyonColour.MESA.getMetaForHeight(i, effectiveHeight,j);
                    } else {
                        if (depth > 4) {
    	        			blocks[(y * 16 + x) * 256 + k] = CanyonColour.MESA.getBlockForHeight(i, effectiveHeight,j);
    	        		    metadata[(y * 16 + x) * 256 + k] = CanyonColour.MESA.getMetaForHeight(i, effectiveHeight,j);
                        } else if (k > 77) {
                            if (rand.nextInt(5) == 0) {
        	        			blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
        	        		    metadata[(y * 16 + x) * 256 + k] = (byte)0;
                            } else {
                                if (depth == 0) {
		                            blocks[(y * 16 + x) * 256 + k] = topBlock;
		                            metadata[(y * 16 + x) * 256 + k] = topBlockMeta;
                                } else {
    	                            blocks[(y * 16 + x) * 256 + k] = fillerBlock;
    	                            metadata[(y * 16 + x) * 256 + k] = fillerBlockMeta;
                                }
                            }
                        } else if (k < 62) {
    	        			blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
    	        		    metadata[(y * 16 + x) * 256 + k] = (byte)0;
                        } else if (k < 78) {
                            if (depth == 0) {
                                if (k < 72 && rand.nextInt(k - 62 + 1) == 0) {
            	        			blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
            	        		    metadata[(y * 16 + x) * 256 + k] = (byte)0;
                                } else if (rand.nextInt(5) == 0) {
            	        			blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
            	        		    metadata[(y * 16 + x) * 256 + k] = (byte)0;
                                } else {
		                            blocks[(y * 16 + x) * 256 + k] = topBlock;
		                            metadata[(y * 16 + x) * 256 + k] = topBlockMeta;
                                }
                            } else {
	                            blocks[(y * 16 + x) * 256 + k] = fillerBlock;
	                            metadata[(y * 16 + x) * 256 + k] = fillerBlockMeta;
                            }
                        } else {
                            if (depth == 0) {
	                            blocks[(y * 16 + x) * 256 + k] = topBlock;
	                            metadata[(y * 16 + x) * 256 + k] = topBlockMeta;
                            } else {
	                            blocks[(y * 16 + x) * 256 + k] = fillerBlock;
	                            metadata[(y * 16 + x) * 256 + k] = fillerBlockMeta;
                            }
                        }
                    }
                } else if (k > 63) {
        			blocks[(y * 16 + x) * 256 + k] = CanyonColour.MESA.getBlockForHeight(i, effectiveHeight,j);
        		    metadata[(y * 16 + x) * 256 + k] = CanyonColour.MESA.getMetaForHeight(i, effectiveHeight,j);
                }
            }
        }
    }
}