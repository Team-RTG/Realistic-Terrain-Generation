package rtg.api.biome;

import java.util.ArrayList;

import rtg.api.biome.BiomeConfigProperty.Type;


public class BiomeConfig {

    public String modSlug;
    public String biomeSlug;
    
    public ArrayList<BiomeConfigProperty> properties;
    
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
        
        this.properties = new ArrayList<BiomeConfigProperty>();

        this.addProperty(new BiomeConfigProperty(enableBiomeId, Type.BOOLEAN, enableBiomeName, "", true));
        this.addProperty(new BiomeConfigProperty(weightId, Type.INTEGER, weightName, "", 10, 1, 100));
        this.addProperty(new BiomeConfigProperty(allowVillagesId, Type.BOOLEAN, allowVillagesName, "", true));
        this.addProperty(new BiomeConfigProperty(enableRTGDecorationsId, Type.BOOLEAN, enableRTGDecorationsName, "", true));
    }
    
    public void addProperty(BiomeConfigProperty property)
    {
        for (int i = 0; i < this.properties.size(); i++) {
            
            if (this.properties.get(i).id.contentEquals(property.id)) {
                removeProperty(property.id);
                break;
            }
        }
        
        this.properties.add(property);
    }
    
    public void removeProperty(String id)
    {
        for (int i = 0; i < this.properties.size(); i++) {
            
            if (this.properties.get(i).id.contentEquals(id)) {
                this.properties.remove(i);
                return;
            }
        }
    }
    
    public ArrayList<BiomeConfigProperty> getProperties()
    {
        return this.properties;
    }
    
    public BiomeConfigProperty getPropertyById(String id)
    {
        for (int i = 0; i < this.properties.size(); i++) {
            
            if (this.properties.get(i).id.contentEquals(id)) {
                return this.properties.get(i);
            }
        }
        return null;
    }
    
    public boolean _boolean(String id)
    {
        try {
            
            return getPropertyById(id).valueBoolean;
        }
        catch (Exception e) {
            
            throw new RuntimeException("Biome config (" + id + ") could not be found. Reason: " + e.getMessage());
        }
    }
    
    public int _int(String id)
    {
        try {
            
            return getPropertyById(id).valueInt;
        }
        catch (Exception e) {
            
            throw new RuntimeException("Biome config (" + id + ") could not be found. Reason: " + e.getMessage());
        }
    }
    
    public String _string(String id)
    {
        try {
            
            return getPropertyById(id).valueString;
        }
        catch (Exception e) {
            
            throw new RuntimeException("Biome config (" + id + ") could not be found. Reason: " + e.getMessage());
        }
    }
}
