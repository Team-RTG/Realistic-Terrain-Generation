package rtg.world.biome.deco;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBase.DecoType;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.tree.WorldGenTreeRTGShrub;

/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoShrub extends DecoBase
{
    
	public int size; // Higher = bigger shrubs.
	public int log; // Yes, this is an integer, not a block. Voooodooooo.
	public int leaves; // Yes, this is an integer, not a block. Voooodooooo.
	public float strengthFactor; // Higher = more/bigger shrubs.
	public int maxY; // Height restriction.
	public int chance; // Higher = more rare.
	
	public DecoShrub()
	{
		super();
		
		/**
		 * Default values.
		 * These can be overridden when configuring the Deco object in the realistic biome.
		 */
		this.size = -1; // Voodoo by default. (See WorldGenTreeRTGShrub if you dare.)
		this.log = -1; // Voodoo by default. (See WorldGenTreeRTGShrub if you dare.)
		this.leaves = -1; // Voodoo by default. (See WorldGenTreeRTGShrub if you dare.)
		this.strengthFactor = 3f; // Not sure why it was done like this, but... the higher the value, the more there will be.
		this.maxY = 255; // No height limit by default.
		this.chance = 1; // 100% chance of generating by default.
		
		this.addDecoTypes(DecoType.SHRUB);
	}
	
	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
	{
		if (this.allowed) {
			
			if (TerrainGen.decorate(world, rand, chunkX, chunkY, TREE)) {
	            
				// Voodoo unless explicitly configured.
				this.size = (this.size == -1) ? rand.nextInt(4) + 1 : this.size;
				this.log = (this.log == -1) ? 0 : this.log;
				this.leaves = (this.leaves == -1) ? rand.nextInt(3) : this.leaves;
				
	            for (int i = 0; i < this.strengthFactor * strength; i++)
	            {
	                int intX = chunkX + rand.nextInt(16) + 8;
	                int intZ = chunkY + rand.nextInt(16) + 8;
	                int intY = world.getHeightValue(intX, intZ);
	                
	                if (intY <= this.maxY && rand.nextInt(this.chance) == 0) {
	                	(new WorldGenTreeRTGShrub(this.size, this.log, this.leaves)).generate(world, rand, intX, intY, intZ);
	                }
	            }
	        }
		}
	}
}
