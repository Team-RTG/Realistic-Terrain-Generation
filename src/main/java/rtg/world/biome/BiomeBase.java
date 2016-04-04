package rtg.world.biome;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeBase extends BiomeGenBase {

    public BiomeBase(int biomeID) {

        super((new BiomeProperties(BiomeGenBase.getBiomeForId(biomeID).getBiomeName()))
                .setRainfall(BiomeGenBase.getBiomeForId(biomeID).getRainfall())
                .setTemperature(BiomeGenBase.getBiomeForId(biomeID).getTemperature()));

    }
}
