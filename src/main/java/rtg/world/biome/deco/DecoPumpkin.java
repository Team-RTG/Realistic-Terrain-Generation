package rtg.world.biome.deco;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.PUMPKIN;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;

/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoPumpkin extends DecoBase
{
    
	public float strengthFactor;
	public int maxY;
	public float randomFloat;
	public RandomType randomType;
	public int chance;
	public int loops;
	
	public enum RandomType
	{
		ALWAYS_GENERATE,
		USE_CHANCE_VALUE,
		X_DIVIDED_BY_STRENGTH
	}
	
	public DecoPumpkin()
	{
		super();
		
		/**
		 * Default values.
		 * These can be overridden when configuring the Deco object in the realistic biome.
		 */
		this.maxY = 255; // No height limit by default.
		this.strengthFactor = 0f; // The higher the value, the more there will be. Disabled by default.
		this.randomType = RandomType.USE_CHANCE_VALUE;
		this.randomFloat = 1f;
		this.chance = 1;
		this.loops = 1;
		
		this.addDecoTypes(DecoType.PUMPKIN);
	}
	
	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
	{
		if (this.allowed) {
			
			if (TerrainGen.decorate(world, rand, chunkX, chunkY, PUMPKIN)) {
	            
				// Let's figure out what the rand.nextInt() argument should be.
				switch (this.randomType)
				{
					case ALWAYS_GENERATE:
						this.chance = 1;
						break;
						
					case USE_CHANCE_VALUE:
						break;
						
					case X_DIVIDED_BY_STRENGTH:
						this.chance = (int)(this.randomFloat / strength);
						break;
						
					default:
						break;
				}

				this.loops = (this.strengthFactor > 0f) ? (int)(this.strengthFactor * strength) : this.loops;
	            for (int i = 0; i < this.loops; i++)
	            {
	            	if (rand.nextInt(this.chance) == 0) {
	            		
		                int intX = chunkX + rand.nextInt(16) + 8;
		                int intY = rand.nextInt(this.maxY);
		                int intZ = chunkY + rand.nextInt(16) + 8;
	
		                if (intY <= this.maxY) {
		                	(new WorldGenPumpkin()).generate(world, rand, intX, intY, intZ);
		                }
	            	}
	            }
	        }
		}
	}
}
