package rtg.config.property;


public class ConfigPropertyBoolean extends ConfigProperty {

    public boolean valueBoolean;

    public ConfigPropertyBoolean(Type type, String name, String category, String description, boolean defaultValue) {

        super(type, name, category, description);

        this.valueBoolean = defaultValue;

        this.formatDescription();
    }

    public boolean get() {
        return this.valueBoolean;
    }

    public void set(boolean value) {
        this.valueBoolean = value;
    }
}
