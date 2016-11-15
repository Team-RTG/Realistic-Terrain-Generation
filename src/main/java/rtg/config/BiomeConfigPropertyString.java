package rtg.config;


public class BiomeConfigPropertyString extends BiomeConfigProperty {

    public String valueString;

    public BiomeConfigPropertyString(Type type, String name, String description, String defaultValue) {

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
