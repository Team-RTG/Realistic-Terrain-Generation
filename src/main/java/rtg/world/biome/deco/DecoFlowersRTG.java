package rtg.world.biome.deco;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenFlowersRTG;

public class DecoFlowersRTG extends DecoBase
{
    
	public int[] flowers;
	public float strengthFactor;
	public int maxY;
	public int chance;
	
	public DecoFlowersRTG()
	{
		super();
		
		/**
		 * Default values.
		 * These can be overridden when configuring the Deco object in the realistic biome.
		 */
		this.flowers = new int[] {0, 9}; // Only roses and dandelions by default.
		this.chance = 1; // 100% chance of generating by default.
		this.maxY = 255; // No height limit by default.
		this.strengthFactor = 2f; // Not sure why it was done like this, but... the higher the value, the more flowers there will be.
	}
	
	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
	{
		if (this.allowed) {
			
	        if (TerrainGen.decorate(world, rand, chunkX, chunkY, FLOWERS)) {
	            
	            for (int f23 = 0; f23 < this.strengthFactor * strength; f23++)
	            {
	                int j15 = chunkX + rand.nextInt(16) + 8;
	                int j20 = chunkY + rand.nextInt(16) + 8;
	                int j17 = world.getHeightValue(j15, j20);
	                
	                if (rand.nextInt(this.chance) == 0 && j17 <= this.maxY) {
	                    
	                    (new WorldGenFlowersRTG(this.flowers)).generate(world, rand, j15, j17, j20);
	                }
	            }
	        }
		}
	}
}
