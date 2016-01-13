package rtg.api.biome;


public class BiomeConfig {

    public String modSlug;
    public String biomeSlug;
    public boolean enableBiome;
    public int biomeWeight;
    public boolean villageBiome;
    
    public BiomeConfig()
    {
        this.modSlug = null;
        this.biomeSlug = null;
        this.enableBiome = true;
        this.biomeWeight = 10;
        this.villageBiome = true;
    }
}
