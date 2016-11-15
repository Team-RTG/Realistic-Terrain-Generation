package rtg.config;


public class BiomeConfigPropertyBoolean extends BiomeConfigProperty {

    public boolean valueBoolean;

    public BiomeConfigPropertyBoolean(Type type, String name, String description, boolean defaultValue) {

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
