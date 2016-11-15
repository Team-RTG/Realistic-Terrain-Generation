package rtg.config.property;


public class ConfigPropertyBoolean extends ConfigProperty {

    public boolean valueBoolean;

    public ConfigPropertyBoolean(Type type, String name, String description, boolean defaultValue) {

        super(type, name, description);

        this.valueBoolean = defaultValue;
    }

    public boolean get() {
        return this.valueBoolean;
    }

    public void set(boolean value) {
        this.valueBoolean = value;
    }
}
