package rtg.api.config;

import net.minecraft.block.state.IBlockState;

/**
 * Wrapper for all different kinds of config properties
 * @author topisani
 */
public class ConfigProperty {

    public IPropertyEnum propertyEnum;
    public String name;
    public String id;
    public String description;
    public IPropertyEnum.Type type;

    public int minValue;
    public int maxValue;
    public int valueInt;
    public boolean valueBoolean = true;
    public String valueString = "";
    public IBlockState valueBlock;

    public ConfigProperty(IPropertyEnum propertyEnum, String name, String description) {
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

    public ConfigProperty setDefault(int valueInt) {
        this.valueInt = valueInt;
        return this;
    }

    public ConfigProperty setDefault(boolean valueBoolean) {
        this.valueBoolean = valueBoolean;
        return this;
    }

    public ConfigProperty setDefault(String valueString) {
        this.valueString = valueString;
        return this;
    }

    public ConfigProperty setDefault(IBlockState valueBlock) {
        this.valueBlock = valueBlock;
        return this;
    }

    /**
     * This interface is intended for use with Enums, but could be used as a class too
     * This serves as an identifier for properties
     */
    public interface IPropertyEnum {
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
