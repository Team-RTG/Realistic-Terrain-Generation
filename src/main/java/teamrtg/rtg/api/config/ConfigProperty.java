package teamrtg.rtg.api.config;

import net.minecraft.block.state.IBlockState;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import teamrtg.rtg.api.util.BlockStringUtil;
import teamrtg.rtg.api.util.debug.Logger;
import teamrtg.rtg.api.util.debug.RTGException;

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
        if (defaultVal == null)
            Logger.error("No default value set for option '" + getID() + "' in section '" + getSection() + "'. That's not good");
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
        return comment + Config.NEW_LINE + "[Default: " + this.getDefault().toString() + "]";
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
     * @param config The configuration to read from
     */
    public abstract void syncForgeProperty(Configuration config);

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
            super.setSection(section);
            return this;
        }

        /**
         * Needed for writing to config files
         * @param config The configuration to read from
         */
        public void syncForgeProperty(Configuration config) {
            Property prop;
            try {
                prop = config.get(this.section, this.id, this.defaultVal);
            } catch (Exception e) {
                Logger.fatal(e, "Something crashed while trying to sync property '%s.%s' with default value '%s'", section, id, defaultVal);
                return;
            }
            this.value = prop.getBoolean();
            prop.set(value);
            prop.setComment(this.getComment());
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

        public String getComment() {
            String range = (minValue < maxValue) ? "[Range: " + minValue + " ~ " + maxValue + "]" : "";
            String def = "[Default: " + this.getDefault().toString() + "]";
            return comment + Config.NEW_LINE + def + " " + range;
        }

        public ConfigProperty.PropertyInt setComment(String comment) {
            super.setComment(comment);
            return this;
        }

        public ConfigProperty.PropertyInt setSection(String section) {
            super.setSection(section);
            return this;
        }

        /**
         * Needed for writing to config files
         * @param config The configuration to read from
         */
        public void syncForgeProperty(Configuration config) {
            Property prop = config.get(this.section, this.id, this.defaultVal);
            this.value = prop.getInt();
            prop.set(value);
            prop.setComment(this.getComment());
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

        public String getComment() {
            String options = (getOptions().length > 0) ? "[Options: " + String.join(", ", (CharSequence[]) getOptions()) + "]" : "";
            String def = "[Default: " + this.getDefault() + "]";
            return comment + Config.NEW_LINE + def + " " + options;
        }

        public ConfigProperty.PropertyString setComment(String comment) {
            super.setComment(comment);
            return this;
        }

        public ConfigProperty.PropertyString setSection(String section) {
            super.setSection(section);
            return this;
        }

        /**
         * Needed for writing to config files
         * @param config The configuration to read from
         */
        public void syncForgeProperty(Configuration config) {
            Property prop = config.get(this.section, this.id, this.defaultVal);
            this.value = prop.getString();
            prop.set(value);
            prop.setValidValues(options);
            prop.setComment(this.getComment());
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

        public String getComment() {
            String syntax = "SYNTAX: 'mod:block', 'mod:block:meta' or 'mod:block[property=value,property2=value2]'";
            String def = "[Default: " + BlockStringUtil.stateToString(this.getDefault()) + "]";
            return comment + Config.NEW_LINE + def + Config.NEW_LINE + syntax;
        }

        public ConfigProperty.PropertyBlock setComment(String comment) {
            super.setComment(comment);
            return this;
        }

        public ConfigProperty.PropertyBlock setSection(String section) {
            super.setSection(section);
            return this;
        }

        /**
         * Needed for writing to config files
         * @param config The configuration to read from
         */
        public void syncForgeProperty(Configuration config) {
            Property prop = config.get(this.section, this.id, BlockStringUtil.stateToString(defaultVal));
            RTGException.error(() -> this.value = BlockStringUtil.stringToState(prop.getString()));
            prop.set(BlockStringUtil.stateToString(value));
            prop.setComment(this.getComment());
        }
    }

    public static class PropertyStrings extends ConfigProperty<String[]> {

        private String[] options = new String[0];

        public PropertyStrings(String id, String section) {
            super(id, section);
        }

        public ConfigProperty.PropertyStrings set(String[] value) {
            super.set(value);
            return this;
        }

        public String[] getOptions() {
            return options;
        }

        public ConfigProperty.PropertyStrings setOptions(String[] options) {
            this.options = options;
            return this;
        }

        public ConfigProperty.PropertyStrings setDefault(String[] defaultValue) {
            super.setDefault(defaultValue);
            return this;
        }

        public String getComment() {
            String options = (getOptions().length > 0) ? "[Options: " + String.join(", ", (CharSequence[]) getOptions()) + "]" : "";
            String def = "[Default: " + String.join(", ", (CharSequence[]) getDefault()) + "]";
            return comment + Config.NEW_LINE + def + " " + options;
        }

        public ConfigProperty.PropertyStrings setComment(String comment) {
            super.setComment(comment);
            return this;
        }

        public ConfigProperty.PropertyStrings setSection(String section) {
            super.setSection(section);
            return this;
        }

        /**
         * Needed for writing to config files
         * @param config The configuration to read from
         */
        public void syncForgeProperty(Configuration config) {
            Property prop = config.get(this.section, this.id, this.defaultVal);
            this.value = prop.getStringList();
            prop.set(value);
            prop.setValidValues(options);
            prop.setComment(this.getComment());
        }
    }

    public static class PropertyFloat extends ConfigProperty<Float> {

        private float minValue;
        private float maxValue;

        public PropertyFloat(String id, String section) {
            super(id, section);
        }

        public ConfigProperty.PropertyFloat setDefault(float defaultValue) {
            super.setDefault(defaultValue);
            return this;
        }

        public ConfigProperty.PropertyFloat setRange(float minValue, float maxValue) {
            this.minValue = minValue;
            this.maxValue = maxValue;
            return this;
        }

        public ConfigProperty.PropertyFloat set(float value) {
            super.set(value);
            return this;
        }

        public String getComment() {
            String range = (minValue < maxValue) ? "[Range: " + minValue + " ~ " + maxValue + "]" : "";
            String def = "[Default: " + this.getDefault().toString() + "]";
            return comment + Config.NEW_LINE + def + " " + range;
        }

        public ConfigProperty.PropertyFloat setComment(String comment) {
            super.setComment(comment);
            return this;
        }

        public ConfigProperty.PropertyFloat setSection(String section) {
            super.setSection(section);
            return this;
        }

        /**
         * Needed for writing to config files
         * @param config The configuration to read from
         */
        public void syncForgeProperty(Configuration config) {
            Property prop = config.get(this.section, this.id, this.defaultVal);
            this.value = (float) prop.getDouble();
            prop.set(value);
            prop.setComment(this.getComment());
        }
    }
}
