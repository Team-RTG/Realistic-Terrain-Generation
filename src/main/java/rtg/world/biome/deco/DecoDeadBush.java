package rtg.world.biome.deco;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.DEAD_BUSH;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;

/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoDeadBush extends DecoBase
{
    
	public float strengthFactor;
	public int maxY;
	public int chance;
	public int loops;
	
	public DecoDeadBush()
	{
		super();
		
		/**
		 * Default values.
		 * These can be overridden when configuring the Deco object in the realistic biome.
		 */
		this.maxY = 255; // No height limit by default.
		this.strengthFactor = 0f; // The higher the value, the more there will be.
		this.chance = 1;
		this.loops = 1;
		
		this.addDecoTypes(DecoType.DEAD_BUSH);
	}
	
	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, boolean hasPlacedVillageBlocks)
	{
		if (this.allowed) {
			
			if (TerrainGen.decorate(world, rand, chunkX, chunkY, DEAD_BUSH)) {
	            
				WorldGenerator worldGenerator = new WorldGenDeadBush(Blocks.deadbush);
				
				int loopCount = this.loops;
				loopCount = (this.strengthFactor > 0f) ? (int)(this.strengthFactor * strength) : loopCount;
	            for (int i = 0; i < loopCount; i++)
	            {
	                int intX = chunkX + rand.nextInt(16);// + 8;
	                int intY = rand.nextInt(this.maxY);
	                int intZ = chunkY + rand.nextInt(16);// + 8;

	                if (intY <= this.maxY && rand.nextInt(this.chance) == 0) {
	                	worldGenerator.generate(world, rand, intX, intY, intZ);
	                }
	            }
	        }
		}
	}
}