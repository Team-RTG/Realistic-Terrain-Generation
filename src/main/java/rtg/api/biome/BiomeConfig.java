package rtg.api.biome;

import java.util.ArrayList;

import rtg.api.biome.BiomeConfigProperty.Type;


public class BiomeConfig {

    public String modSlug;
    public String biomeSlug;
    
    public ArrayList<BiomeConfigProperty> properties;
    
    public static final String allowVillagesId = "allowVillages";
    public static final String allowVillagesName = "Allow Villages";
    
    public static final String useRTGDecorationsId = "useRTGDecorations";
    public static final String useRTGDecorationsName = "Use RTG Decorations";

    public BiomeConfig(String modSlug, String biomeSlug)
    {
        this.modSlug = modSlug;
        this.biomeSlug = biomeSlug;
        
        this.properties = new ArrayList<BiomeConfigProperty>();

        this.addProperty(new BiomeConfigProperty(allowVillagesId, Type.BOOLEAN, allowVillagesName, "", true));
        this.addProperty(new BiomeConfigProperty(useRTGDecorationsId, Type.BOOLEAN, useRTGDecorationsName, "", true));
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
            
            throw new RuntimeException("Biome config property (" + modSlug + "." + biomeSlug + "." + id + ") could not be found. Reason: " + e.getMessage());
        }
    }
    
    public int _int(String id)
    {
        try {
            
            return getPropertyById(id).valueInt;
        }
        catch (Exception e) {
            
            throw new RuntimeException("Biome config property (" + modSlug + "." + biomeSlug + "." + id + ") could not be found. Reason: " + e.getMessage());
        }
    }
    
    public String _string(String id)
    {
        try {
            
            return getPropertyById(id).valueString;
        }
        catch (Exception e) {
            
            throw new RuntimeException("Biome config property (" + modSlug + "." + biomeSlug + "." + id + ") could not be found. Reason: " + e.getMessage());
        }
    }
}
