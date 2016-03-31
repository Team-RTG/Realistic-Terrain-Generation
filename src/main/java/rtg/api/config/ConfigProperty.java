package rtg.api.config;

import net.minecraft.block.state.IBlockState;
import net.minecraftforge.common.config.Property;
import rtg.api.util.BlockStringUtil;
import rtg.api.util.debug.RTGException;

/**
 * Wrapper for all different kinds of config properties
 * @author topisani
 */
public class ConfigProperty {

    public IPropertyID propertyEnum;
    public String name;
    public String id;
    public String description;
    public IPropertyID.Type type;

    public int minValue;
    public int maxValue;

    public int defaultInt;
    public boolean defaultBoolean = true;
    public String defaultString = "";
    public IBlockState defaultBlock;

    public int valueInt;
    public boolean valueBoolean = true;
    public String valueString = "";
    public IBlockState valueBlock;

    public ConfigProperty(IPropertyID propertyEnum, String name, String description) {
        this.propertyEnum = propertyEnum;
        this.name = name;
        this.id = propertyEnum.name();
        this.type = propertyEnum.getType();
        this.description = description;
    }

    public ConfigProperty setMin(int minValue) {
        this.minValue = minValue;
        return this;
    }
    public ConfigProperty setMax(int maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    public ConfigProperty set(int valueInt) {
        this.valueInt = valueInt;
        return this;
    }

    public ConfigProperty set(boolean valueBoolean) {
        this.valueBoolean = valueBoolean;
        return this;
    }

    public ConfigProperty set(String valueString) {
        this.valueString = valueString;
        return this;
    }

    public ConfigProperty set(IBlockState valueBlock) {
        this.valueBlock = valueBlock;
        return this;
    }

    public ConfigProperty setDefault(int defaultInt) {
        this.defaultInt = defaultInt;
        return this;
    }

    public ConfigProperty setDefault(boolean defaultBoolean) {
        this.defaultBoolean = defaultBoolean;
        return this;
    }

    public ConfigProperty setDefault(String defaultString) {
        this.defaultString = defaultString;
        return this;
    }

    public ConfigProperty setDefault(IBlockState defaultBlock) {
        this.defaultBlock = defaultBlock;
        return this;
    }

    /**
     * Needed for writing to config files
     * @throws RTGException
     */
    public Property toForgeProp() throws RTGException{
        Property prop;
        switch (type) {
            case INTEGER:
                prop = new Property(this.name, String.valueOf(this.valueInt), Property.Type.INTEGER).setDefaultValue(this.defaultInt);
                break;
            case BOOLEAN:
                prop = new Property(name, String.valueOf(valueBoolean), Property.Type.BOOLEAN).setDefaultValue(defaultBoolean);
                break;
            case STRING:
                prop = new Property(name, valueString, Property.Type.STRING).setDefaultValue(defaultString);
                break;
            case BLOCK:
                prop = new Property(name, BlockStringUtil.stateToString(valueBlock), Property.Type.STRING).setDefaultValue(BlockStringUtil.stateToString(defaultBlock));
                break;
            default:
                throw new RTGException(RTGException.Type.CONFIG_SYNTAX, "Somehow RTG doesnt support this config type: " + type.name(), "ConfigProperty.toForgeProp()");
        }

        return prop;
    }

    /**
     * Needed for writing to config files
     * @param id An instance of IPropertyID created from prop.getName()
     * @param prop The property to read
     * @throws RTGException of type CONFIG_SYNTAX if failed.
     */
    public static ConfigProperty fromForgeProp(IPropertyID id, Property prop) throws RTGException {
        ConfigProperty cprop = new ConfigProperty(id, prop.getName(), prop.getComment());
        try {
            switch (id.getType()) {
                case INTEGER:
                    cprop.set(prop.getInt());
                    break;
                case BOOLEAN:
                    cprop.set(prop.getBoolean());
                    break;
                case STRING:
                    cprop.set(prop.getString());
                    break;
                case BLOCK:
                    cprop.set(BlockStringUtil.stringToState(prop.getString()));
            }
            return cprop;
        } catch (Exception e) {
            throw new RTGException(RTGException.Type.CONFIG_SYNTAX,
                    "Tried to read property " + prop.getName() + " with value " + prop.getString() + " of type " + prop.getType().name() +
                            " into property " + id.name() + " of type " + id.getType().name(),
                    "ConfigProperty.fromForgeProp()");
        }
    }
    /**
     * This interface is intended for use with Enums, but could be used as a class too
     * This serves as an identifier for properties
     */
    public interface IPropertyID {
        enum Type {
            INTEGER,
            BOOLEAN,
            STRING,
            BLOCK
        }
        Type getType();
        ConfigProperty get();
        String name();
    }
}
