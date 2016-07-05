package teamrtg.rtg.api.tools.deco;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LILYPAD;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import teamrtg.rtg.api.module.Mods;
import teamrtg.rtg.api.tools.feature.WorldGenVinesRTG;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoJungleLilypadVines extends DecoBase
{
	private static final IBlockState volcanoBlock = Mods.RTG.config.VOLCANO_BLOCK.get();
	private static final IBlockState volcanoMix1Block = Mods.RTG.config.VOLCANO_BLOCK_MIX_1.get();
	private static final IBlockState volcanoMix2Block = Mods.RTG.config.VOLCANO_BLOCK_MIX_2.get();
	private static final IBlockState volcanoMix3Block = Mods.RTG.config.VOLCANO_BLOCK_MIX_3.get();
	
	public DecoJungleLilypadVines()
	{
		super();

		this.addDecoTypes(DecoType.LILYPAD, DecoType.VINE);


	}
	
	@Override
	public void generate(RTGWorld rtgWorld, Random rand, int chunkX, int chunkY, float strength, float river, RealisticBiomeGenerator realisticBiomeGenerator, boolean hasPlacedVillageBlocks)
	{
		if (this.allowed) {
			
			int blockX = chunkX;
			int blockY = chunkY;
	        if (TerrainGen.decorate(rtgWorld.world, rand, new BlockPos(blockX, 0, blockY), LILYPAD)) {
	            
	        	WorldGenerator worldgeneratorLilypads = new WorldGenWaterlily();
	        	WorldGenerator worldgeneratorVines = new WorldGenVinesRTG();
	        	IBlockState vb;
	        	
	            for (int b33 = 0; b33 < 5; b33++)
	            {
	                int j6 = blockX + rand.nextInt(16) + 8;
	                int k10 = blockY + rand.nextInt(16) + 8;
	                int z52 = rtgWorld.world.getHeight(new BlockPos(j6, 0, k10)).getY();
	 
	                for (int h44 = 0; h44 < 8; h44++) {
	                    
	                    if (z52 > 64) {
	                    	worldgeneratorLilypads.generate(rtgWorld.world, rand, new BlockPos(j6, z52, k10));
	                    }
	                }

	                for (int h44 = 100; h44 > 0; h44--) {
	                	
	                	vb = rtgWorld.world.getBlockState(new BlockPos(j6, h44, k10));
	                	
						if (vb == volcanoBlock || vb == volcanoMix1Block || vb == volcanoMix2Block || vb == volcanoMix3Block) {
							return;
						}
						
	                	worldgeneratorVines.generate(rtgWorld.world, rand, new BlockPos(j6, z52, k10));
	                }
	            }
	        }
		}
	}
}
