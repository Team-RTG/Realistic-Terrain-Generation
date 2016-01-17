package rtg.api.biome;


public class BiomeConfigProperty {

    public String id;
    public Type type;
    public String name;
    public String description;
    
    public int minValue;
    public int maxValue;
    public int valueInt;
    public boolean valueBoolean;
    public String valueString;
    
    public enum Type {
        INTEGER,
        BOOLEAN,
        STRING
    }
    
    public BiomeConfigProperty(String id, Type type, String name, String description)
    {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
    }

    public BiomeConfigProperty(String id, Type type, String name, String description, boolean defaultValue)
    {
        this(id, type, name, description);
        
        this.valueBoolean = defaultValue;
    }
    
    public BiomeConfigProperty(String id, Type type, String name, String description, String defaultValue)
    {
        this(id, type, name, description);
        
        this.valueString = defaultValue;
    }
    
    public BiomeConfigProperty(String id, Type type, String name, String description, int defaultValue, int minValue, int maxValue)
    {
        this(id, type, name, description);
        
        this.valueInt = defaultValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
}
