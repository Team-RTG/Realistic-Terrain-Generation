package rtg.api.config.property;


public class ConfigPropertyString extends ConfigProperty {

    public String valueString;

    public ConfigPropertyString(String name, String category, String description, String defaultValue) {

        super(Type.STRING, name, category, description);

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
