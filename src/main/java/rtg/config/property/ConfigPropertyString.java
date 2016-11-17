package rtg.config.property;


public class ConfigPropertyString extends ConfigProperty {

    public String valueString;

    public ConfigPropertyString(Type type, String name, String description, String defaultValue) {

        super(type, name, description);

        this.valueString = defaultValue;
    }

    public String get() {
        return this.valueString;
    }

    public void set(String value) {
        this.valueString = value;
    }
}
