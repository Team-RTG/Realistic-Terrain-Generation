package rtg.config.property;


public class ConfigPropertyString extends ConfigProperty {

    public String valueString;

    public ConfigPropertyString(Type type, String name, String category, String description, String defaultValue) {

        super(type, name, category, description);

        this.valueString = defaultValue;

        this.formatDescription();
    }

    public String get() {
        return this.valueString;
    }

    public void set(String value) {
        this.valueString = value;
    }
}
