package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.world.World;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;

public class DecoBaseBiomeDecorations extends DecoBase
{
	
	public int equalsZeroChance;
	public int notEqualsZeroChance;
	public int loops;
	
	public DecoBaseBiomeDecorations()
	{
		super();
		
		this.equalsZeroChance = 0;
		this.notEqualsZeroChance = 0;
		this.loops = 1;
	}
	
	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
	{
		if (this.allowed) {
			
			for (int i = 0; i < loops; i++) {
				
				if (this.equalsZeroChance > 0) {
					
			        if (rand.nextInt(this.equalsZeroChance) == 0) {
			        	biome.rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, biome.baseBiome);
			        }
			        else {
			        	biome.rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, biome.baseBiome);
			        }
				}
				else if (this.notEqualsZeroChance > 0) {
					
			        if (rand.nextInt(this.notEqualsZeroChance) != 0) {
			        	biome.rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, biome.baseBiome);
			        }
			        else {
			        	biome.rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, biome.baseBiome);
			        }
				}
				else {
					
					biome.rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, biome.baseBiome);
				}
			}
		}
		else {
			biome.rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, biome.baseBiome);
		}
	}
}
