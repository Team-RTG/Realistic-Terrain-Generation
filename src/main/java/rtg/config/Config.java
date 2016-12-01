package rtg.config;

import java.util.ArrayList;

import rtg.config.property.*;


public abstract class Config {

    protected ArrayList<ConfigProperty> properties = new ArrayList<ConfigProperty>();

    public ArrayList<ConfigProperty> getProperties() {

        return this.properties;
    }

    protected void addProp(ConfigProperty property) {

        for (int i = 0; i < this.properties.size(); i++) {

            if (this.properties.get(i).name.contentEquals(property.name)) {
                removeProp(property.name);
                break;
            }
        }

        this.properties.add(property);
    }

    protected void removeProp(String name) {

        for (int i = 0; i < this.properties.size(); i++) {

            if (this.properties.get(i).name.contentEquals(name)) {
                this.properties.remove(i);
                return;
            }
        }
    }

    public ConfigPropertyBoolean addProperty(ConfigPropertyBoolean property) {
        this.addProp(property);
        return property;
    }

    public ConfigPropertyFloat addProperty(ConfigPropertyFloat property) {
        this.addProp(property);
        return property;
    }

    public ConfigPropertyInt addProperty(ConfigPropertyInt property) {
        this.addProp(property);
        return property;
    }

    public ConfigPropertyString addProperty(ConfigPropertyString property) {
        this.addProp(property);
        return property;
    }
}
