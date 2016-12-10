package rtg.api.config.property;


public class ConfigPropertyInt extends ConfigProperty {

    public int minValueInt;
    public int maxValueInt;
    public int valueInt;

    public ConfigPropertyInt(Type type, String name, String category, String description, int defaultValue, int minValueInt, int maxValueInt) {

        super(type, name, category, description);

        this.valueInt = defaultValue;
        this.minValueInt = minValueInt;
        this.maxValueInt = maxValueInt;

        this.formatDescription();
    }

    public int get() {
        return this.valueInt;
    }

    public void set(int value) {
        this.valueInt = value;
    }
}
