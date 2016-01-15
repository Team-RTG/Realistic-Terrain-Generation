package rtg.api.biome;

import java.util.HashMap;

import rtg.api.biome.BiomeConfigProperty.Type;


public class BiomeConfig {

    public String modSlug;
    public String biomeSlug;
    
    public HashMap<String, BiomeConfigProperty> properties;
    
    public HashMap<String, Boolean> propBools;
    public HashMap<String, Integer> propInts;
    public HashMap<String, String> propStrings;
    
    public static final String enableBiomeId = "biomeEnabled";
    public static final String enableBiomeName = "Biome Enabled";
    
    public static final String weightId = "weight";
    public static final String weightName = "Biome Weight";
    
    public static final String allowVillagesId = "allowVillages";
    public static final String allowVillagesName = "Allow Villages";
    
    public static final String enableRTGDecorationsId = "enableRTGDecorations";
    public static final String enableRTGDecorationsName = "Use RTG Decorations";
    
    public BiomeConfig()
    {
        this.modSlug = null;
        this.biomeSlug = null;
        
        this.properties = new HashMap<String, BiomeConfigProperty>();
        
        this.propBools = new HashMap<String, Boolean>();
        this.propInts = new HashMap<String, Integer>();
        this.propStrings = new HashMap<String, String>();

        this.addProperty(new BiomeConfigProperty(enableBiomeId, Type.BOOLEAN, enableBiomeName, "", true));
        this.addProperty(new BiomeConfigProperty(weightId, Type.INTEGER, weightName, "", 10, 1, 100));
        this.addProperty(new BiomeConfigProperty(allowVillagesId, Type.BOOLEAN, allowVillagesName, "", true));
        this.addProperty(new BiomeConfigProperty(enableRTGDecorationsId, Type.BOOLEAN, enableRTGDecorationsName, "", true));
    }
    
    public void addProperty(BiomeConfigProperty property)
    {

        this.properties.put(property.id, property);
        
        switch (property.type) {
            
            case INTEGER: this.propInts.put(property.id, property.valueInt); break;
            case BOOLEAN: this.propBools.put(property.id, property.valueBoolean); break;
            case STRING: this.propStrings.put(property.id, property.valueString); break;
            default: throw new RuntimeException("BiomeConfigProperty type not supported.");
        }
    }
    
    public void removeProperty(String id)
    {
        
        if (this.properties.containsKey(id)) {
            
            BiomeConfigProperty property = this.properties.get(id);
 
            switch (property.type) {
                
                case INTEGER: this.propInts.remove(id); break;
                case BOOLEAN: this.propBools.remove(id); break;
                case STRING: this.propStrings.remove(id); break;
                default: throw new RuntimeException("BiomeConfigProperty type not supported.");
            }
            
            this.properties.remove(id);
        }
        else {
            
            throw new RuntimeException("Could not remove property ID (" + id + ").");
        }
    }
    
    public HashMap<String, BiomeConfigProperty> getProperties()
    {
        return this.properties;
    }
    
    public BiomeConfigProperty getPropertyById(String id)
    {
        if (this.properties.containsKey(id)) {
            
            return this.properties.get(id);
        }
        else {
            
            throw new RuntimeException("Could not get property by ID (" + id + ").");
        }
    }
}
