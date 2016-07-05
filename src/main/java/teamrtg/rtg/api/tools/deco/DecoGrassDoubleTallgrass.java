package teamrtg.rtg.api.tools.deco;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import teamrtg.rtg.api.tools.feature.WorldGenGrass;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoGrassDoubleTallgrass extends DecoBase
{
	
	public float strengthFactor;
	public int maxY;
	public int loops;
	public int grassChance;
	public int doubleGrassChance;

	public DecoGrassDoubleTallgrass()
	{
		super();
		
		/**
		 * Default values.
		 * These can be overridden when configuring the Deco object in the realistic biome.
		 */
		this.maxY = 255; // No height limit by default.
		this.strengthFactor = 0f; // Not sure why it was done like this, but... the higher the value, the more there will be.
		this.loops = 1;
		this.grassChance = 0; // 50% chance for both grass & double grass by default.
		this.doubleGrassChance = 0; // 50% chance for both grass & double grass by default. (If set, overrides grass chance.)
		
		this.addDecoTypes(DecoType.GRASS, DecoType.GRASS_DOUBLE);
	}
	
	@Override
	public void generate(RTGWorld rtgWorld, Random rand, int chunkX, int chunkY, float strength, float river, RealisticBiomeGenerator realisticBiomeGenerator, boolean hasPlacedVillageBlocks)
	{
		if (this.allowed) {
			
			if (TerrainGen.decorate(rtgWorld.world, rand, new BlockPos(chunkX, 0, chunkY), GRASS)) {
	            
				WorldGenerator worldGenerator = null;
            	if (this.doubleGrassChance > 0) {
            		
                	if (rand.nextInt(this.doubleGrassChance) == 0) {
                		
                		worldGenerator = new WorldGenGrass(Blocks.DOUBLE_PLANT.getDefaultState(), 2);
                	}
                	else {
                		
                		worldGenerator = new WorldGenGrass(Blocks.TALLGRASS.getDefaultState(), 1);
                	}
            	}
            	else if (this.grassChance > 0) {
            		
                	if (rand.nextInt(this.grassChance) == 0) {
                		
                		worldGenerator = new WorldGenGrass(Blocks.TALLGRASS.getDefaultState(), 1);
                	}
                	else {
                		
                		worldGenerator = new WorldGenGrass(Blocks.DOUBLE_PLANT.getDefaultState(), 2);
                	}
            	}
            	else {
            		
                	if (rand.nextBoolean()) {
                		
                		worldGenerator = new WorldGenGrass(Blocks.TALLGRASS.getDefaultState(), 1);
                	}
                	else {
                		
                		worldGenerator = new WorldGenGrass(Blocks.DOUBLE_PLANT.getDefaultState(), 2);
                	}
            	}
				
				this.loops = (this.strengthFactor > 0f) ? (int)(this.strengthFactor * strength) : this.loops;
	            for (int i = 0; i < this.loops; i++)
	            {
	                int intX = chunkX + rand.nextInt(16) + 8;
	                int intY = rand.nextInt(this.maxY);
	                int intZ = chunkY + rand.nextInt(16) + 8;

	                if (intY <= this.maxY) {
	                	
	                	worldGenerator.generate(rtgWorld.world, rand, new BlockPos(intX, intY, intZ));
	                }
	            }
	        }
		}
	}
}
