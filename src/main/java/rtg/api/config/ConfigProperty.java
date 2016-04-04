package rtg.api.config;

import net.minecraft.block.state.IBlockState;
import net.minecraftforge.common.config.Property;
import rtg.api.util.BlockStringUtil;
import rtg.api.util.debug.Logger;
import rtg.api.util.debug.RTGException;

/**
 * Wrapper for all different kinds of config properties
 * @author topisani
 */
public abstract class ConfigProperty<T> {

    protected String id;
    protected String section;
    protected String comment;

    protected T defaultVal;
    protected T value;

    public ConfigProperty(String id, String section) {
        this.id = id;
        this.section = section;
    }

    public T get() {
        if (defaultVal == null) Logger.error("No default value set for option '" + getID() + "' in section '" + getSection() + "'. That's not good");
        return (this.value == null) ? this.defaultVal : this.value;
    }

    public T getDefault() {
        return defaultVal;
    }

    protected ConfigProperty<T> setDefault(T defaultVal) {
        this.defaultVal = defaultVal;
        return this;
    }

    public String getID() {
        return this.id;
    }

    public String getSection() {
        return this.section;
    }

    public String getComment() {
        return comment;
    }

    public ConfigProperty<T> setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public ConfigProperty<T> setSection(String section) {
        this.section = section;
        return this;
    }

    /**
     * Needed for writing to config files
     * @throws RTGException
     */
    public abstract Property toForgeProp() throws RTGException;

    /**
     * Needed for writing to config files
     * @param prop The property to read
     * @return this
     * @throws RTGException of type CONFIG_SYNTAX if failed.
     */
    public abstract ConfigProperty readForgeProperty(Property prop) throws RTGException;

    protected ConfigProperty<T> set(T value) {
        this.value = value;
        return this;
    }

    public static class PropertyBool extends ConfigProperty<Boolean> {

        public PropertyBool(String id, String section) {
            super(id, section);
        }

        public ConfigProperty.PropertyBool setDefault(boolean defaultValue) {
            super.setDefault(defaultValue);
            return this;
        }

        public ConfigProperty.PropertyBool set(boolean value) {
            super.set(value);
            return this;
        }

        public ConfigProperty.PropertyBool setComment(String comment) {
            super.setComment(comment);
            return this;
        }

        public ConfigProperty.PropertyBool setSection(String section) {
            this.setSection(section);
            return this;
        }

        /**
         * Needed for writing to config files
         * @throws RTGException
         */
        public Property toForgeProp() throws RTGException {
            Property prop = new Property(id, String.valueOf(value), Property.Type.BOOLEAN).setDefaultValue(defaultVal);
            prop.set(value);
            return prop;
        }

        /**
         * Needed for writing to config files
         * @param prop The property to read
         * @return this
         * @throws RTGException of type CONFIG_SYNTAX if failed.
         */
        public ConfigProperty readForgeProperty(Property prop) throws RTGException {
            try {
                this.set(prop.getBoolean());
                return this;
            } catch (Exception e) {
                throw new RTGException(RTGException.Type.CONFIG_SYNTAX,
                        "Tried to read property " + prop.getName() + " with value " + prop.getString() + " of type " + prop.getType().name() +
                                " into property " + id + " of type BOOLEAN",
                        "ConfigProperty.fromForgeProp()");
            }
        }
    }

    public static class PropertyInt extends ConfigProperty<Integer> {

        private int minValue;
        private int maxValue;

        public PropertyInt(String id, String section) {
            super(id, section);
        }

        public ConfigProperty.PropertyInt setDefault(int defaultValue) {
            super.setDefault(defaultValue);
            return this;
        }

        public ConfigProperty.PropertyInt setRange(int minValue, int maxValue) {
            this.minValue = minValue;
            this.maxValue = maxValue;
            return this;
        }

        public ConfigProperty.PropertyInt set(int value) {
            super.set(value);
            return this;
        }


        public ConfigProperty.PropertyInt setComment(String comment) {
            super.setComment(comment);
            return this;
        }

        public ConfigProperty.PropertyInt setSection(String section) {
            this.setSection(section);
            return this;
        }

        /**
         * Needed for writing to config files
         * @throws RTGException
         */
        public Property toForgeProp() throws RTGException {
            Property prop = new Property(id, String.valueOf(value), Property.Type.INTEGER).setDefaultValue(defaultVal).setMinValue(minValue).setMaxValue(maxValue);
            prop.set(value);
            return prop;
        }

        /**
         * Needed for writing to config files
         * @param prop The property to read
         * @return this
         * @throws RTGException of type CONFIG_SYNTAX if failed.
         */
        public ConfigProperty readForgeProperty(Property prop) throws RTGException {
            try {
                this.set(prop.getInt());
                return this;
            } catch (Exception e) {
                throw new RTGException(RTGException.Type.CONFIG_SYNTAX,
                        "Tried to read property " + prop.getName() + " with value " + prop.getString() + " of type " + prop.getType().name() +
                                " into property " + id + " of type INTEGER",
                        "ConfigProperty.fromForgeProp()");
            }
        }
    }

    public static class PropertyString extends ConfigProperty<String> {

        private String[] options = new String[0];

        public PropertyString(String id, String section) {
            super(id, section);
        }

        public ConfigProperty.PropertyString setDefault(String defaultValue) {
            super.setDefault(defaultValue);
            return this;
        }

        public String[] getOptions() {
            return options;
        }

        public ConfigProperty.PropertyString setOptions(String[] options) {
            this.options = options;
            return this;
        }

        public ConfigProperty.PropertyString set(String value) {
            super.set(value);
            return this;
        }


        public ConfigProperty.PropertyString setComment(String comment) {
            super.setComment(comment);
            return this;
        }

        public ConfigProperty.PropertyString setSection(String section) {
            this.setSection(section);
            return this;
        }

        /**
         * Needed for writing to config files
         * @throws RTGException
         */
        public Property toForgeProp() throws RTGException {
            Property prop = new Property(id, value, Property.Type.STRING).setDefaultValue(defaultVal);
            if (options.length > 0) prop.setValidValues(options);
            prop.set(value);
            return prop;
        }

        /**
         * Needed for writing to config files
         * @param prop The property to read
         * @return this
         * @throws RTGException of type CONFIG_SYNTAX if failed.
         */
        public ConfigProperty readForgeProperty(Property prop) throws RTGException {
            try {
                this.set(prop.getString());
                return this;
            } catch (Exception e) {
                throw new RTGException(RTGException.Type.CONFIG_SYNTAX,
                        "Tried to read property " + prop.getName() + " with value " + prop.getString() + " of type " + prop.getType().name() +
                                " into property " + id + " of type STRING",
                        "ConfigProperty.fromForgeProp()");
            }
        }


    }

    public static class PropertyBlock extends ConfigProperty<IBlockState> {

        public PropertyBlock(String id, String section) {
            super(id, section);
        }

        public ConfigProperty.PropertyBlock set(IBlockState value) {
            super.set(value);
            return this;
        }

        public ConfigProperty.PropertyBlock setDefault(IBlockState defaultValue) {
            super.setDefault(defaultValue);
            return this;
        }

        public ConfigProperty.PropertyBlock setComment(String comment) {
            super.setComment(comment);
            return this;
        }

        public ConfigProperty.PropertyBlock setSection(String section) {
            this.setSection(section);
            return this;
        }

        /**
         * Needed for writing to config files
         * @throws RTGException
         */
        public Property toForgeProp() throws RTGException {
            Property prop = new Property(id, BlockStringUtil.stateToString(value), Property.Type.STRING).setDefaultValue(BlockStringUtil.stateToString(defaultVal));
            prop.set(BlockStringUtil.stateToString(value));
            return prop;
        }

        /**
         * Needed for writing to config files
         * @param prop The property to read
         * @return this
         * @throws RTGException of type CONFIG_SYNTAX if failed.
         */
        public ConfigProperty readForgeProperty(Property prop) throws RTGException {
            try {
                this.set(BlockStringUtil.stringToState(prop.getString()));
                return this;
            } catch (Exception e) {
                throw new RTGException(RTGException.Type.CONFIG_SYNTAX,
                        "Tried to read property " + prop.getName() + " with value " + prop.getString() + " of type " + prop.getType().name() +
                                " into property " + id + " of type BLOCK",
                        "ConfigProperty.fromForgeProp()");
            }
        }
    }

    public static class PropertyStrings extends ConfigProperty<String[]> {

        public PropertyStrings(String id, String section) {
            super(id, section);
        }

        public ConfigProperty.PropertyStrings set(String[] value) {
            super.set(value);
            return this;
        }

        public ConfigProperty.PropertyStrings setDefault(String[] defaultValue) {
            super.setDefault(defaultValue);
            return this;
        }

        public ConfigProperty.PropertyStrings setComment(String comment) {
            super.setComment(comment);
            return this;
        }

        public ConfigProperty.PropertyStrings setSection(String section) {
            this.setSection(section);
            return this;
        }

        /**
         * Needed for writing to config files
         * @throws RTGException
         */
        public Property toForgeProp() throws RTGException {
            Property prop = new Property(id, value, Property.Type.STRING).setDefaultValues(defaultVal);
            prop.set(value);
            return prop;
        }

        /**
         * Needed for writing to config files
         * @param prop The property to read
         * @return this
         * @throws RTGException of type CONFIG_SYNTAX if failed.
         */
        public ConfigProperty readForgeProperty(Property prop) throws RTGException {
            try {
                this.set(prop.getStringList());
                return this;
            } catch (Exception e) {
                throw new RTGException(RTGException.Type.CONFIG_SYNTAX,
                        "Tried to read property " + prop.getName() + " with value " + prop.getString() + " of type " + prop.getType().name() +
                                " into property " + id + " of type STRINGS",
                        "ConfigProperty.fromForgeProp()");
            }
        }
    }
}
