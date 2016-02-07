package rtg.world.biome;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeBase extends BiomeGenBase
{

	public BiomeBase(int intBiomeId) {
		super(intBiomeId, false);
		
		// And I won't tell no one your name... and I won't tell 'em your name.
		this.biomeName = getBiome(intBiomeId).biomeName;
	}
}
