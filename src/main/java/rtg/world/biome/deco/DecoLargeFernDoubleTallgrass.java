package rtg.world.biome.deco;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenGrass;

/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoLargeFernDoubleTallgrass extends DecoBase
{
    
	private final int GRASS_META = 2;
	private final int FERN_META = 3;
	
	public float strengthFactor;
	public int maxY;
	public int loops;
	public int grassChance;
	public int fernChance;

	public DecoLargeFernDoubleTallgrass()
	{
		super();
		
		/**
		 * Default values.
		 * These can be overridden when configuring the Deco object in the realistic biome.
		 */
		this.maxY = 255; // No height limit by default.
		this.strengthFactor = 0f; // Not sure why it was done like this, but... the higher the value, the more there will be.
		this.loops = 1;
		this.grassChance = 0; // 50% chance for both grass & ferns by default.
		this.fernChance = 0; // 50% chance for both grass & ferns by default. (If set, overrides grass chance.)
		
		this.addDecoTypes(DecoType.GRASS_DOUBLE, DecoType.FERN_DOUBLE);
	}
	
	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, boolean hasPlacedVillageBlocks)
	{
		if (this.allowed) {
			
			if (TerrainGen.decorate(world, rand, chunkX, chunkY, GRASS)) {
	            
				WorldGenerator worldgeneratorDoubleTallgrass = new WorldGenGrass(Blocks.double_plant, GRASS_META);
				WorldGenerator worldgeneratorLargeFern = new WorldGenGrass(Blocks.double_plant, FERN_META);
				
				this.loops = (this.strengthFactor > 0f) ? (int)(this.strengthFactor * strength) : this.loops;
	            for (int i = 0; i < this.loops; i++)
	            {
	                int intX = chunkX + rand.nextInt(16) + 8;
	                int intY = rand.nextInt(this.maxY);
	                int intZ = chunkY + rand.nextInt(16) + 8;

	                if (intY <= this.maxY) {
	                	
	                	if (this.fernChance > 0) {
	                		
		                	if (rand.nextInt(this.fernChance) == 0) {
		                		
		                		worldgeneratorLargeFern.generate(world, rand, intX, intY, intZ);
		                	}
		                	else {
		                		
		                		worldgeneratorDoubleTallgrass.generate(world, rand, intX, intY, intZ);
		                	}
	                	}
	                	else if (this.grassChance > 0) {
	                		
		                	if (rand.nextInt(this.grassChance) == 0) {
		                		
		                		worldgeneratorDoubleTallgrass.generate(world, rand, intX, intY, intZ);
		                	}
		                	else {
		                		
		                		worldgeneratorLargeFern.generate(world, rand, intX, intY, intZ);
		                	}
	                	}
	                	else {
	                		
		                	if (rand.nextBoolean()) {
		                		
		                		worldgeneratorDoubleTallgrass.generate(world, rand, intX, intY, intZ);
		                	}
		                	else {
		                		
		                		worldgeneratorLargeFern.generate(world, rand, intX, intY, intZ);
		                	}
	                	}
	                }
	            }
	        }
		}
	}
}
