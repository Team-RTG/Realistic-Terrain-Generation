package rtg.api.config;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraftforge.common.config.Configuration;

import rtg.api.config.property.ConfigProperty;
import rtg.api.config.property.ConfigPropertyArray.ConfigPropertyArrayDouble;
import rtg.api.config.property.ConfigPropertyArray.ConfigPropertyArrayInteger;
import rtg.api.config.property.ConfigPropertyArray.ConfigPropertyArrayString;
import rtg.api.config.property.ConfigPropertyBoolean;
import rtg.api.config.property.ConfigPropertyFloat;
import rtg.api.config.property.ConfigPropertyInteger;
import rtg.api.config.property.ConfigPropertyString;
import rtg.api.util.Logger;


public abstract class Config {

    private final File configFile;
    private Configuration config;
    protected List<ConfigProperty> properties = new ArrayList<>();

    protected Config(@Nonnull File configFile) {
        this.configFile = configFile;
    }

    private void addProp(ConfigProperty property) {

        for (ConfigProperty prop : this.properties) {

            if (prop.getName().contentEquals(property.getName())) {
                removeProp(property.getName());
                break;
            }
        }

        this.properties.add(property);
    }

    private void removeProp(String name) {

        for (int i = 0; i < this.properties.size(); i++) {

            if (this.properties.get(i).getName().contentEquals(name)) {
                this.properties.remove(i);
                return;
            }
        }
    }

    public boolean hasProperty(ConfigProperty prop) {
        return this.properties.stream().anyMatch(property -> property.getCategory().contentEquals(prop.getCategory()) && property.getName().contentEquals(prop.getName()));
    }

    public final ConfigPropertyBoolean addProperty(ConfigPropertyBoolean property) {
        this.addProp(property);
        return property;
    }

    public final ConfigPropertyFloat addProperty(ConfigPropertyFloat property) {
        this.addProp(property);
        return property;
    }

    public final ConfigPropertyInteger addProperty(ConfigPropertyInteger property) {
        this.addProp(property);
        return property;
    }

    public final ConfigPropertyString addProperty(ConfigPropertyString property) {
        this.addProp(property);
        return property;
    }

    public final ConfigPropertyArrayInteger addProperty(ConfigPropertyArrayInteger property) {
        this.addProp(property);
        return property;
    }

    public final ConfigPropertyArrayDouble addProperty(ConfigPropertyArrayDouble property) {
        this.addProp(property);
        return property;
    }

    public final ConfigPropertyArrayString addProperty(ConfigPropertyArrayString property) {
        this.addProp(property);
        return property;
    }

    public void loadConfig() {

        config = new Configuration(this.configFile);

        try {
            config.load();

            for (ConfigProperty prop : this.properties) {

                switch (prop.getType()) {

                    case INTEGER:
                        ConfigPropertyInteger propInt = (ConfigPropertyInteger) prop;
                        propInt.set(config.getInt(
                            propInt.getName(),
                            propInt.getCategory(),
                            propInt.valueInt,
                            propInt.minValueInt,
                            propInt.maxValueInt,
                            propInt.getDescription()
                        ));
                        break;

                    case INTEGER_ARRAY:
                        ConfigPropertyArrayInteger propIntArray = (ConfigPropertyArrayInteger) prop;
                        propIntArray.set(ArrayUtils.toObject(
                            config.get(
                                propIntArray.getName(),
                                propIntArray.getCategory(),
                                propIntArray.getPrimitives(),
                                propIntArray.getDescription(),
                                propIntArray.getMinValue(),
                                propIntArray.getMaxValue()
                            ).getIntList()
                        ));
                        break;

                    case FLOAT:
                        ConfigPropertyFloat propFloat = (ConfigPropertyFloat) prop;
                        propFloat.set(config.getFloat(
                            propFloat.getName(),
                            propFloat.getCategory(),
                            propFloat.valueFloat,
                            propFloat.minValueFloat,
                            propFloat.maxValueFloat,
                            propFloat.getDescription()
                        ));
                        break;

                    case DOUBLE_ARRAY:
                        ConfigPropertyArrayDouble propDoubleArray = (ConfigPropertyArrayDouble) prop;
                        propDoubleArray.set(ArrayUtils.toObject(
                            config.get(
                                propDoubleArray.getName(),
                                propDoubleArray.getCategory(),
                                propDoubleArray.getPrimitives(),
                                propDoubleArray.getDescription(),
                                propDoubleArray.getMinValue(),
                                propDoubleArray.getMaxValue()
                            ).getDoubleList()
                        ));
                        break;

                    case BOOLEAN:
                        ConfigPropertyBoolean propBool = (ConfigPropertyBoolean) prop;
                        propBool.set(config.getBoolean(
                            propBool.getName(),
                            propBool.getCategory(),
                            propBool.valueBoolean,
                            propBool.getDescription()
                        ));
                        break;

                    case STRING:
                        ConfigPropertyString propString = (ConfigPropertyString) prop;
                        propString.set(config.getString(
                            propString.getName(),
                            propString.getCategory(),
                            propString.valueString,
                            propString.getDescription()
                        ));
                        break;

                    case STRING_ARRAY:
                        ConfigPropertyArrayString propStringArray = (ConfigPropertyArrayString) prop;
                        propStringArray.set(config.get(
                            propStringArray.getName(),
                            propStringArray.getCategory(),
                            propStringArray.getValues(),
                            propStringArray.getDescription()
                        ).getStringList());
                        break;
                }
            }
        }
        catch (Exception ignored) { Logger.error("RTG had a problem loading config: {}", configFile); }
        finally { if (config.hasChanged()) { config.save(); } }
    }

    public Configuration getConfig() { return config; }
}
