package rtg.world.biome.deco;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LILYPAD;

import java.util.Random;

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

/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoJungleLilypadVines extends DecoBase
{
	private static final Block volcanoBlockId = Block.getBlockFromName(ConfigRTG.volcanoBlockId);
	private static final Block volcanoMix1BlockId = Block.getBlockFromName(ConfigRTG.volcanoMix1BlockId);
	private static final Block volcanoMix2BlockId = Block.getBlockFromName(ConfigRTG.volcanoMix2BlockId);
	private static final Block volcanoMix3BlockId = Block.getBlockFromName(ConfigRTG.volcanoMix3BlockId);
	
	public DecoJungleLilypadVines()
	{
		super();

		this.addDecoTypes(DecoType.LILYPAD, DecoType.VINE);


	}
	
	/**
	 * No config options for this one yet. Just ripped it directly from the old code.
	 */
	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int blockX, int blockY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, boolean hasPlacedVillageBlocks)
	{
		if (this.allowed) {
			
	        if (TerrainGen.decorate(world, rand, blockX, blockY, LILYPAD)) {
	            
	        	WorldGenerator worldgeneratorLilypads = new WorldGenWaterlily();
	        	WorldGenerator worldgeneratorVines = new WorldGenVines();
	        	Block vb;
	        	
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
	                	
	                	vb = world.getBlock(j6, h44, k10);
	                	
						if (vb == volcanoBlockId || vb == volcanoMix1BlockId || vb == volcanoMix2BlockId || vb == volcanoMix3BlockId) {
							return;
						}
						
	                	worldgeneratorVines.generate(world, rand, j6, z52, k10);
	                }
	            }
	        }
		}
	}
}
