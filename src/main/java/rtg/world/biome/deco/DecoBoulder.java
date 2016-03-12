package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenBlob;

public class DecoBoulder extends DecoBase
{
    
	public Block boulder;
	public float strengthFactor;
	public int maxY;
	public int chance;
	
	public DecoBoulder()
	{
		super();
		
		this.boulder = Blocks.cobblestone;
		this.chance = 10;
		this.maxY = 90;
		this.strengthFactor = 2f;
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
                
                if (k1 < this.maxY && rand.nextInt(this.chance) == 0) {
                    (new WorldGenBlob(boulder, 0, rand)).generate(world, rand, i1, k1, j1);
                }
            }
		}
	}
}
