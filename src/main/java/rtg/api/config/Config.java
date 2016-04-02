package rtg.api.config;

import java.util.ArrayList;

/**
 * An object that holds config properties
 * Does not provide a way to store those properties in a file
 * @see ModConfig for that functionality.
 * @author topisani
 */
public class Config {

    public ArrayList<ConfigProperty> properties;

    public Config() {
        this.properties = new ArrayList<>();
    }

    public ConfigProperty getProp(String id) {
        for (int i = 0; i < this.properties.size(); i++) {

            if (this.properties.get(i).getID().contentEquals(id)) {
                return this.properties.get(i);
            }
        }
        return null;
    }

    public ConfigProperty.PropertyBool addBool(ConfigProperty.PropertyBool property) {
        this.properties.add(property);
        return property;
    }

    public ConfigProperty.PropertyInt addInt(ConfigProperty.PropertyInt property) {
        this.properties.add(property);
        return property;
    }

    public ConfigProperty.PropertyString addString(ConfigProperty.PropertyString property) {
        this.properties.add(property);
        return property;
    }

    public ConfigProperty.PropertyBlock addBlock(ConfigProperty.PropertyBlock property) {
        this.properties.add(property);
        return property;
    }

    public ConfigProperty.PropertyBool addBool(String id, String section) {
        ConfigProperty.PropertyBool property = new ConfigProperty.PropertyBool(id, section);
        this.properties.add(property);
        return property;
    }

    public ConfigProperty.PropertyInt addInt(String id, String section) {
        ConfigProperty.PropertyInt property = new ConfigProperty.PropertyInt(id, section);
        this.properties.add(property);
        return property;
    }

    public ConfigProperty.PropertyString addString(String id, String section) {
        ConfigProperty.PropertyString property = new ConfigProperty.PropertyString(id, section);
        this.properties.add(property);
        return property;
    }

    public ConfigProperty.PropertyBlock addBlock(String id, String section) {
        ConfigProperty.PropertyBlock property = new ConfigProperty.PropertyBlock(id, section);
        this.properties.add(property);
        return property;
    }

}
