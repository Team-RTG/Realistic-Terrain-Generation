package rtg.world.biome.deco;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.DEAD_BUSH;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
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
	
	public DecoDeadBush()
	{
		super();
		
		/**
		 * Default values.
		 * These can be overridden when configuring the Deco object in the realistic biome.
		 */
		this.maxY = 255; // No height limit by default.
		this.strengthFactor = 2f; // The higher the value, the more there will be.
		
		this.addDecoTypes(DecoType.DEAD_BUSH);
	}
	
	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
	{
		if (this.allowed) {
			
			if (TerrainGen.decorate(world, rand, chunkX, chunkY, DEAD_BUSH)) {
	            
	            for (int i = 0; i < this.strengthFactor * strength; i++)
	            {
	                int intX = chunkX + rand.nextInt(16) + 8;
	                int intY = rand.nextInt(this.maxY);
	                int intZ = chunkY + rand.nextInt(16) + 8;

	                if (intY <= this.maxY) {
	                	(new WorldGenDeadBush(Blocks.deadbush)).generate(world, rand, intX, intY, intZ);
	                }
	            }
	        }
		}
	}
}
