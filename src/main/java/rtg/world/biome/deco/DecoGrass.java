package rtg.world.biome.deco;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
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
public class DecoGrass extends DecoBase
{
    
	public float strengthFactor;
	public int maxY;
	public int loops;
	public int chance;
	public int meta;
	
	public DecoGrass()
	{
		super();
		
		/**
		 * Default values.
		 * These can be overridden when configuring the Deco object in the realistic biome.
		 */
		this.maxY = 255; // No height limit by default.
		this.strengthFactor = 0f; // Not sure why it was done like this, but... the higher the value, the more there will be.
		this.loops = 1;
		this.chance = 1;
		this.meta = 1;
		
		this.addDecoTypes(DecoType.GRASS);
	}
	
	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
	{
		if (this.allowed) {
			
			if (TerrainGen.decorate(world, rand, chunkX, chunkY, GRASS)) {
	            
				this.loops = (this.strengthFactor > 0f) ? (int)(this.strengthFactor * strength) : this.loops;
	            for (int i = 0; i < this.loops; i++)
	            {
	                int intX = chunkX + rand.nextInt(16) + 8;
	                int intY = rand.nextInt(this.maxY);
	                int intZ = chunkY + rand.nextInt(16) + 8;

	                if (intY <= this.maxY && rand.nextInt(this.chance) == 0) {
	                	(new WorldGenGrass(Blocks.tallgrass, this.meta)).generate(world, rand, intX, intY, intZ);
	                }
	            }
	        }
		}
	}
}
