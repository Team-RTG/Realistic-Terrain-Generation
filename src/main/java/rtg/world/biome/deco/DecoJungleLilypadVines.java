package rtg.world.biome.deco;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.config.rtg.ConfigRTG;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenVines;

import java.util.Random;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LILYPAD;

/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoJungleLilypadVines extends DecoBase
{
	final static Block noPlaceBlock = Block.getBlockFromName(ConfigRTG.volcanoBlockId);
	public DecoJungleLilypadVines()
	{
		super();

		this.addDecoTypes(DecoType.LILYPAD, DecoType.VINE);


	}
	
	/**
	 * No config options for this one yet. Just ripped it directly from the old code.
	 */
	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int blockX, int blockY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
	{
		if (this.allowed) {
			
	        if (TerrainGen.decorate(world, rand, blockX, blockY, LILYPAD)) {
	            
	        	WorldGenerator worldgeneratorLilypads = new WorldGenWaterlily();
	        	WorldGenerator worldgeneratorVines = new WorldGenVines();
	        	
	            for (int b33 = 0; b33 < 5; b33++)
	            {
	                int j6 = blockX + rand.nextInt(16) + 8;
	                int k10 = blockY + rand.nextInt(16) + 8;
	                int z52 = world.getHeightValue(j6, k10);
	 
	                for (int h44 = 0; h44 < 8; h44++) {
	                    
	                    if (z52 > 64) {
	                    	worldgeneratorLilypads.generate(world, rand, j6, z52, k10);
	                    }
	                }
	                
	                for (int h44 = 100; h44 > 0; h44--) {
						if(world.getBlock(j6, h44, k10) == noPlaceBlock) return;
	                	worldgeneratorVines.generate(world, rand, j6, z52, k10);
	                }
	            }
	        }
		}
	}
}
