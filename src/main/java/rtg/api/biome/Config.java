package rtg.api.biome;

import net.minecraft.block.state.IBlockState;

import java.util.ArrayList;

/**
 * @author topisani
 */
public class Config {

    public ArrayList<ConfigProperty> properties;

    public Config() {
        this.properties = new ArrayList<>();
    }

    public void addProperties(ConfigProperty.IPropertyEnum[] props) {
        for (ConfigProperty.IPropertyEnum prop : props) {
            this.addProperty(prop.get());
        }
    }

    public void addProperty(ConfigProperty property) {
        for (int i = 0; i < this.properties.size(); i++) {

            if (this.properties.get(i).id.contentEquals(property.id)) {
                removeProperty(property.id);
                break;
            }
        }

        this.properties.add(property);
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

    public boolean _boolean(ConfigProperty.IPropertyEnum id) {
        try {
            return getPropertyById(id).valueBoolean;
        } catch (Exception e) {
            throw new RuntimeException("Config property " + id.name() + " could not be found. Reason: " + e.getMessage());
        }
    }

    public ConfigProperty getPropertyById(ConfigProperty.IPropertyEnum id) {
        for (int i = 0; i < this.properties.size(); i++) {

            if (this.properties.get(i).id.contentEquals(id.name())) {
                return this.properties.get(i);
            }
        }
        return null;
    }

    public int _int(ConfigProperty.IPropertyEnum id) {
        try {

            return getPropertyById(id).valueInt;
        } catch (Exception e) {

            throw new RuntimeException("Config property " + id.name() + " could not be found. Reason: " + e.getMessage());
        }
    }

    public String _string(ConfigProperty.IPropertyEnum id) {
        try {

            return getPropertyById(id).valueString;
        } catch (Exception e) {

            throw new RuntimeException("Config property " + id.name() + " could not be found. Reason: " + e.getMessage());
        }
    }

    public IBlockState _block(ConfigProperty.IPropertyEnum id) {
        try {

            return getPropertyById(id).valueBlock;
        } catch (Exception e) {

            throw new RuntimeException("Config property " + id.name() + " could not be found. Reason: " + e.getMessage());
        }
    }
}
