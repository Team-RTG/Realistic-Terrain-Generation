package rtg.config;


public class BiomeConfigProperty {

    public Type type;
    public String name;
    public String description;

    public BiomeConfigProperty(Type type, String name, String description) {

        this.type = type;
        this.name = name;
        this.description = description;
    }

    public enum Type {
        INTEGER,
        FLOAT,
        BOOLEAN,
        STRING
    }
}
