package rtg.api.biomes;


public class BiomeConfig {

    private String realisticBiomeName;
    
    public BiomeConfig()
    {
        this.setRealisticBiomeName(null);
    }

    public String getRealisticBiomeName()
    {
        return this.realisticBiomeName;
    }
    
    public void setRealisticBiomeName(String n)
    {
        this.realisticBiomeName = n;
    }
}
