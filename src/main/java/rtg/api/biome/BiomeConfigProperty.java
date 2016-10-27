package rtg.api.biome;


public class BiomeConfigProperty {

    public String id;
    public Type type;
    public String name;
    public String description;

    public int minValueInt;
    public int maxValueInt;
    public int valueInt;

    public float minValueFloat;
    public float maxValueFloat;
    public float valueFloat;

    public boolean valueBoolean;

    public String valueString;

    public BiomeConfigProperty(String id, Type type, String name, String description) {

        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
    }

    public BiomeConfigProperty(String id, Type type, String name, String description, boolean defaultValue) {

        this(id, type, name, description);

        this.valueBoolean = defaultValue;
    }

    public BiomeConfigProperty(String id, Type type, String name, String description, String defaultValue) {

        this(id, type, name, description);

        this.valueString = defaultValue;
    }

    public BiomeConfigProperty(String id, Type type, String name, String description, int defaultValue, int minValueInt, int maxValueInt) {

        this(id, type, name, description);

        this.valueInt = defaultValue;
        this.minValueInt = minValueInt;
        this.maxValueInt = maxValueInt;
    }

    public BiomeConfigProperty(String id, Type type, String name, String description, float defaultValue, float minValueFloat, float maxValueFloat) {

        this(id, type, name, description);

        this.valueFloat = defaultValue;
        this.minValueFloat = minValueFloat;
        this.maxValueFloat = maxValueFloat;
    }

    public enum Type {
        INTEGER,
        FLOAT,
        BOOLEAN,
        STRING
    }
}
