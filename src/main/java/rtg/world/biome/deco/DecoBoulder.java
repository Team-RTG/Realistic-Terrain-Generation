package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenBlob;

/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoBoulder extends DecoBase
{
    
	public Block boulderBlock; // This can be any block.
	public float strengthFactor; // Higher = more/bigger boulders.
	public int minY; // Lower height restriction.
	public int maxY; // Upper height restriction.
	public int chance; // Higher = more rare.
	
	public DecoBoulder()
	{
		super();
		
		/**
		 * Default values.
		 * These can be overridden when configuring the Deco object in the realistic biome.
		 */
		this.boulderBlock = Blocks.cobblestone;
		this.strengthFactor = 2f;
		this.minY = 1; // No lower height limit by default.
		this.maxY = 255; // No upper height limit by default.
		this.chance = 10;
		
		this.addDecoTypes(DecoType.BOULDER);
	}
	
	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
	{
		if (this.allowed) {
			
            for (int l1 = 0; l1 < this.strengthFactor * strength; ++l1)
            {
                int i1 = chunkX + rand.nextInt(16) + 8;
                int j1 = chunkY + rand.nextInt(16) + 8;
                int k1 = world.getHeightValue(i1, j1);
                
                if (k1 >= this.minY && k1 <= this.maxY && rand.nextInt(this.chance) == 0) {
                    (new WorldGenBlob(boulderBlock, 0, rand)).generate(world, rand, i1, k1, j1);
                }
            }
		}
	}
}
