package teamrtg.rtg.api.tools.deco;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import teamrtg.rtg.api.tools.feature.WorldGenGrass;
import teamrtg.rtg.api.tools.feature.WorldGenVinesRTG;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoJungleGrassVines extends DecoBase
{

	protected WorldGenerator worldgeneratorGrass;
	protected WorldGenerator worldgeneratorDoubleTallgrass;
	protected WorldGenerator worldgeneratorFern;
	protected WorldGenerator worldgeneratorLargeFern;
	protected WorldGenerator worldgeneratorVines;
	
	public DecoJungleGrassVines()
	{
		super();

		this.worldgeneratorGrass = new WorldGenGrass(Blocks.TALLGRASS.getDefaultState(), 1);
		this.worldgeneratorDoubleTallgrass = new WorldGenGrass(Blocks.DOUBLE_PLANT.getDefaultState(), 2);
		this.worldgeneratorFern = new WorldGenGrass(Blocks.TALLGRASS.getDefaultState(), 2);
		this.worldgeneratorLargeFern = new WorldGenGrass(Blocks.DOUBLE_PLANT.getDefaultState(), 3);
		this.worldgeneratorVines = new WorldGenVinesRTG();
		
		this.addDecoTypes(DecoType.GRASS, DecoType.VINE);
	}
	
	@Override
	public void generate(RTGWorld rtgWorld, Random rand, int chunkX, int chunkY, float strength, float river, RealisticBiomeGenerator realisticBiomeGenerator, boolean hasPlacedVillageBlocks)
	{
		if (this.allowed) {
			
	        if (TerrainGen.decorate(rtgWorld.world, rand, new BlockPos(chunkX, 0, chunkY), GRASS)) {

	            for (int l14 = 0; l14 < 16f * strength; l14++)
	            {
	                int l19 = chunkX + rand.nextInt(16);// + 8;
	                int k22 = rand.nextInt(128);
	                int j24 = chunkY + rand.nextInt(16);// + 8;
	                
	                if (rand.nextInt(8) == 0) {
	                    if (rand.nextBoolean()) {
	                    	this.worldgeneratorGrass.generate(rtgWorld.world, rand, new BlockPos(l19, k22, j24));
	                    }
	                    else {
	                    	this.worldgeneratorFern.generate(rtgWorld.world, rand, new BlockPos(l19, k22, j24));
	                    }
	                }

	                for (int h44 = 0; h44 < 4 && k22 > 63; h44++) {
	                	worldgeneratorVines.generate(rtgWorld.world, rand, new BlockPos(l19, k22, j24));
	                }
	            }
	            
	            for (int l14 = 0; l14 < 12f * strength; l14++)
	            {
	                int l19 = chunkX + rand.nextInt(16);// + 8;
	                int k22 = rand.nextInt(128);
	                int j24 = chunkY + rand.nextInt(16);// + 8;
	                
	                if (rand.nextInt(5) == 0) {
	                    if (rand.nextBoolean()) {
	                    	this.worldgeneratorDoubleTallgrass.generate(rtgWorld.world, rand, new BlockPos(l19, k22, j24));
	                    }
	                    else {
	                    	this.worldgeneratorLargeFern.generate(rtgWorld.world, rand, new BlockPos(l19, k22, j24));
	                    }
	                }
	            }
	            
	            for (int l14 = 0; l14 < 16f * strength; l14++)
	            {
	                int l19 = chunkX + rand.nextInt(16);// + 8;
	                int k22 = rand.nextInt(128);
	                int j24 = chunkY + rand.nextInt(16);// + 8;
	                
	                if (rand.nextInt(8) == 0) {
	                    if (rand.nextBoolean()) {
	                    	this.worldgeneratorGrass.generate(rtgWorld.world, rand, new BlockPos(l19, k22, j24));
	                    }
	                    else {
	                    	this.worldgeneratorFern.generate(rtgWorld.world, rand, new BlockPos(l19, k22, j24));
	                    }
	                }
	                
	                if (k22 > 63) {
	                    
	                    for (int h44 = 0; h44 < 8; h44++) {
	                    	worldgeneratorVines.generate(rtgWorld.world, rand, new BlockPos(l19, k22, j24));
	                    }
	                }
	            }
	        }
		}
	}
}
