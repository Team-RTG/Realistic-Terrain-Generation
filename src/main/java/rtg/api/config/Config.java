package rtg.api.config;

import java.io.File;
import java.util.ArrayList;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLLog;

import org.apache.logging.log4j.Level;

import rtg.api.config.property.*;
import rtg.api.util.BlockStringUtil;


public abstract class Config {

    protected ArrayList<ConfigProperty> properties = new ArrayList<ConfigProperty>();
    private Configuration config;

    public ArrayList<ConfigProperty> getProperties() {

        return this.properties;
    }

    protected void addProp(ConfigProperty property) {

        for (ConfigProperty prop : this.properties) {

            if (prop.name.contentEquals(property.name)) {
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

    public boolean hasProperty(ConfigProperty prop) {
        for (int i = 0; i < this.properties.size(); i++) {
            if (this.properties.get(i).category.contentEquals(prop.category) && this.properties.get(i).name.contentEquals(prop.name)) {
                return true;
            }
        }
        return false;
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

    public ConfigPropertyBlockstate addProperty(ConfigPropertyBlockstate property) {
        this.addProp(property);
        return property;
    }

    public void load(String configFile) {

        config = new Configuration(new File(configFile));

        try {
            config.load();

            ArrayList<ConfigProperty> properties = this.getProperties();

            for (ConfigProperty prop : properties) {

                if (prop.restricted) {
                    continue;
                }

                if (prop instanceof ConfigPropertyInt) {

                    ConfigPropertyInt propInt = (ConfigPropertyInt)prop;

                    propInt.set(config.getInt(
                        propInt.name,
                        propInt.category,
                        propInt.valueInt,
                        propInt.minValueInt,
                        propInt.maxValueInt,
                        prop.description
                    ));
                }
                else if (prop instanceof ConfigPropertyFloat) {

                    ConfigPropertyFloat propFloat = (ConfigPropertyFloat)prop;

                    propFloat.set(config.getFloat(
                        propFloat.name,
                        propFloat.category,
                        propFloat.valueFloat,
                        propFloat.minValueFloat,
                        propFloat.maxValueFloat,
                        propFloat.description
                    ));

                }
                else if (prop instanceof ConfigPropertyBoolean) {

                    ConfigPropertyBoolean propBool = (ConfigPropertyBoolean)prop;

                    propBool.set(config.getBoolean(
                        propBool.name,
                        propBool.category,
                        propBool.valueBoolean,
                        propBool.description
                    ));

                }
                else if (prop instanceof ConfigPropertyString) {

                    ConfigPropertyString propString = (ConfigPropertyString)prop;

                    propString.set(config.getString(
                        propString.name,
                        propString.category,
                        propString.valueString,
                        propString.description
                    ));
                }
                else if (prop instanceof ConfigPropertyBlockstate) {

                    ConfigPropertyBlockstate propBlockstate = (ConfigPropertyBlockstate)prop;

                    propBlockstate.set(BlockStringUtil.stringToState(config.getString(
                        propBlockstate.name,
                        propBlockstate.category,
                        BlockStringUtil.stateToString(propBlockstate.valueBlockstate),
                        propBlockstate.description
                    )));
                }
                else {
                    throw new RuntimeException("ConfigProperty type not supported.");
                }
            }

        }
        catch (Exception e) {
            FMLLog.log(Level.ERROR, "[RTG-ERROR] RTG had a problem loading config: %s", configFile);
        }
        finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }

    public Configuration getConfig() {
        return config;
    }
}
