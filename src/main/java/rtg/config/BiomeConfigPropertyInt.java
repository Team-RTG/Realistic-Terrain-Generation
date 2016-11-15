package rtg.config;


public class BiomeConfigPropertyInt extends BiomeConfigProperty {

    public int minValueInt;
    public int maxValueInt;
    public int valueInt;

    public BiomeConfigPropertyInt(Type type, String name, String description, int defaultValue, int minValueInt, int maxValueInt) {

        super(type, name, description);

        this.valueInt = defaultValue;
        this.minValueInt = minValueInt;
        this.maxValueInt = maxValueInt;
    }

    public int get() {
        return this.valueInt;
    }

    public void set(int value) {
        this.valueInt = value;
    }
}
