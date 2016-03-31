package rtg.api.config;

import net.minecraft.block.state.IBlockState;

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

    public void addProperties(ConfigProperty.IPropertyID[] props) {
        for (ConfigProperty.IPropertyID prop : props) {
            this.addProperty(prop.get());
        }
    }

    public ConfigProperty addProperty(ConfigProperty property) {
        for (int i = 0; i < this.properties.size(); i++) {

            if (this.properties.get(i).id.contentEquals(property.id)) {
                removeProperty(property.id);
                break;
            }
        }

        this.properties.add(property);
        return property;
    }

    public void removeProperty(String id) {
        for (int i = 0; i < this.properties.size(); i++) {

            if (this.properties.get(i).id.contentEquals(id)) {
                this.properties.remove(i);
                return;
            }
        }
    }

    public ArrayList<ConfigProperty> getProperties() {
        return this.properties;
    }

    public ConfigProperty getPropertyById(ConfigProperty.IPropertyID id) {
        for (int i = 0; i < this.properties.size(); i++) {

            if (this.properties.get(i).id.contentEquals(id.name())) {
                return this.properties.get(i);
            }
        }
        return null;
    }

    public boolean _boolean(ConfigProperty.IPropertyID id) {
        try {
            return getPropertyById(id).valueBoolean;
        } catch (Exception e) {
            throw new RuntimeException("Config property " + id.name() + " could not be found. Reason: " + e.getMessage());
        }
    }

    public int _int(ConfigProperty.IPropertyID id) {
        try {

            return getPropertyById(id).valueInt;
        } catch (Exception e) {

            throw new RuntimeException("Config property " + id.name() + " could not be found. Reason: " + e.getMessage());
        }
    }

    public String _string(ConfigProperty.IPropertyID id) {
        try {

            return getPropertyById(id).valueString;
        } catch (Exception e) {

            throw new RuntimeException("Config property " + id.name() + " could not be found. Reason: " + e.getMessage());
        }
    }

    public IBlockState _block(ConfigProperty.IPropertyID id) {
        try {
            return getPropertyById(id).valueBlock;
        } catch (Exception e) {

            throw new RuntimeException("Config property " + id.name() + " could not be found. Reason: " + e.getMessage());
        }
    }

    /**
     * Will get a property, and if it doesn't exist add it with a default value
     */
    public boolean get(ConfigProperty.IPropertyID id, boolean defaultBoolean) {
        try {
            return this.getPropertyById(id).valueBoolean;
        } catch (Exception e) {
            return this.addProperty(id.get().setDefault(defaultBoolean)).valueBoolean;
        }
    }

    /**
     * Will get a property, and if it doesn't exist add it with a default value
     */
    public int get(ConfigProperty.IPropertyID id, int defaultInt) {
        try {
            return this.getPropertyById(id).valueInt;
        } catch (Exception e) {
            return this.addProperty(id.get().setDefault(defaultInt)).valueInt;
        }
    }

    /**
     * Will get a property, and if it doesn't exist add it with a default value
     */
    public String get(ConfigProperty.IPropertyID id, String defaultString) {
        try {
            return this.getPropertyById(id).valueString;
        } catch (Exception e) {
            return this.addProperty(id.get().setDefault(defaultString)).valueString;
        }
    }

    /**
     * Will get a property, and if it doesn't exist add it with a default value
     */
    public IBlockState get(ConfigProperty.IPropertyID id, IBlockState defaultBlock) {
        try {
            return this.getPropertyById(id).valueBlock;
        } catch (Exception e) {
            return this.addProperty(id.get().setDefault(defaultBlock)).valueBlock;
        }
    }
}
