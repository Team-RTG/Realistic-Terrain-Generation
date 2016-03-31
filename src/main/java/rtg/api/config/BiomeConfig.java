package rtg.api.config;

public class BiomeConfig extends Config {

    public String modSlug;
    public String biomeSlug;

    public BiomeConfig(String modSlug, String biomeSlug) {
        super();
        this.modSlug = modSlug;
        this.biomeSlug = biomeSlug;
    }

    public BiomeConfig(String modSlug, String biomeSlug, ConfigProperty[] props) {
        this(modSlug, biomeSlug);
        for (ConfigProperty prop : props) {
            this.addProperty(prop);
        }
    }
}
