package rtg.api.config;

import net.minecraftforge.common.config.Configuration;

import java.util.ArrayList;

/**
 * An object that holds config properties
 * Does not provide a way to store those properties in a file
 *
 * @author topisani
 * @see ModConfig for that functionality.
 */
public class Config {

    public static final String NEW_LINE = Configuration.NEW_LINE;

    private ArrayList<ConfigProperty> properties;

    public Config() {
        this.properties = new ArrayList<>();
    }

    public ConfigProperty getProp(String id) {
        for (ConfigProperty property : this.properties) {
            if (property.getID().contentEquals(id)) {
                return property;
            }
        }
        return null;
    }

    protected void addProperty(ConfigProperty property) {
        for (ConfigProperty property1 : this.properties) {
            if ((property1.getID().equalsIgnoreCase(property.getID())) && (property1.getSection().equalsIgnoreCase(property.getSection()))) {
                return;
            }
        }
        this.properties.add(property);
    }

    public ConfigProperty.PropertyBool addBool(ConfigProperty.PropertyBool property) {
        this.addProperty(property);
        return property;
    }

    public ConfigProperty.PropertyInt addInt(ConfigProperty.PropertyInt property) {
        this.addProperty(property);
        return property;
    }

    public ConfigProperty.PropertyString addString(ConfigProperty.PropertyString property) {
        this.addProperty(property);
        return property;
    }

    public ConfigProperty.PropertyBlock addBlock(ConfigProperty.PropertyBlock property) {
        this.addProperty(property);
        return property;
    }

    public ConfigProperty.PropertyBool addBool(String id, String section) {
        ConfigProperty.PropertyBool property = new ConfigProperty.PropertyBool(id, section);
        this.addProperty(property);
        return property;
    }

    public ConfigProperty.PropertyInt addInt(String id, String section) {
        ConfigProperty.PropertyInt property = new ConfigProperty.PropertyInt(id, section);
        this.addProperty(property);
        return property;
    }

    public ConfigProperty.PropertyString addString(String id, String section) {
        ConfigProperty.PropertyString property = new ConfigProperty.PropertyString(id, section);
        this.addProperty(property);
        return property;
    }

    public ConfigProperty.PropertyBlock addBlock(String id, String section) {
        ConfigProperty.PropertyBlock property = new ConfigProperty.PropertyBlock(id, section);
        this.addProperty(property);
        return property;
    }

    public ConfigProperty.PropertyStrings addStrings(String id, String section) {
        ConfigProperty.PropertyStrings property = new ConfigProperty.PropertyStrings(id, section);
        this.addProperty(property);
        return property;
    }

}
