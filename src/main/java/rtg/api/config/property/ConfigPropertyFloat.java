package rtg.api.config.property;


public class ConfigPropertyFloat extends ConfigProperty {

    public float minValueFloat;
    public float maxValueFloat;
    public float valueFloat;

    public ConfigPropertyFloat(String name, String category, String description, float defaultValue, float minValueFloat, float maxValueFloat) {

        super(Type.FLOAT, name, category, description);

        this.valueFloat = defaultValue;
        this.minValueFloat = minValueFloat;
        this.maxValueFloat = maxValueFloat;

        this.formatDescription();
    }

    public float get() {
        return this.valueFloat;
    }

    public void set(float value) {
        this.valueFloat = value;
    }
}
