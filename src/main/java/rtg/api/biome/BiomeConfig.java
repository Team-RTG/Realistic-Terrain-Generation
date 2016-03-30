package rtg.api.biome;

import java.util.ArrayList;

public class BiomeConfig extends Config{

    public String modSlug;
    public String biomeSlug;

    public BiomeConfig(String modSlug, String biomeSlug) {
        this.modSlug = modSlug;
        this.biomeSlug = biomeSlug;

        this.properties = new ArrayList<>();
    }

    public BiomeConfig(String modSlug, String biomeSlug, ConfigProperty[] props) {
        this(modSlug, biomeSlug);
        for (ConfigProperty prop : props) {
            this.addProperty(prop);
        }
    }
}
