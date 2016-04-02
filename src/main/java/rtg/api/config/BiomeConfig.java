package rtg.api.config;

public class BiomeConfig extends Config {

    public String modSlug;
    public String biomeSlug;

    public BiomeConfig(String modSlug, String biomeSlug) {
        super();
        this.modSlug = modSlug;
        this.biomeSlug = biomeSlug;
    }

    public ConfigProperty.PropertyBool addBool(String id) {
        ConfigProperty.PropertyBool property = new ConfigProperty.PropertyBool(id, "");
        this.properties.add(property);
        return property;
    }

    public ConfigProperty.PropertyInt addInt(String id) {
        ConfigProperty.PropertyInt property = new ConfigProperty.PropertyInt(id, "");
        this.properties.add(property);
        return property;
    }

    public ConfigProperty.PropertyString addString(String id) {
        ConfigProperty.PropertyString property = new ConfigProperty.PropertyString(id, "");
        this.properties.add(property);
        return property;
    }

    public ConfigProperty.PropertyBlock addBlock(String id) {
        ConfigProperty.PropertyBlock property = new ConfigProperty.PropertyBlock(id, "");
        this.properties.add(property);
        return property;
    }

}
