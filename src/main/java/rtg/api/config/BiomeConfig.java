package rtg.api.config;

public class BiomeConfig extends Config {

    public String modSlug;
    public String biomeSlug;

    public BiomeConfig(String modSlug, String biomeSlug) {
        super();
        this.modSlug = modSlug;
        this.biomeSlug = biomeSlug;
    }
}
