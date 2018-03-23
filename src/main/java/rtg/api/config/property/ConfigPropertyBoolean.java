package rtg.api.config.property;


public class ConfigPropertyBoolean extends ConfigProperty {

    public boolean valueBoolean;

    public ConfigPropertyBoolean(String name, String category, String description, boolean defaultValue) {

        super(Type.BOOLEAN, name, category, description);

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
