package rtg.api.biome;

import java.util.ArrayList;

import net.minecraftforge.common.config.Configuration;
import rtg.api.biome.BiomeConfigProperty.Type;


public class BiomeConfig {

    public String modSlug;
    public String biomeSlug;
    
    public ArrayList<BiomeConfigProperty> properties;
    
    public static final String allowVillagesId = "allowVillages";
    public static final String allowVillagesName = "Allow Villages";
    
    public static final String allowVolcanoesId = "allowVolcanoes";
    public static final String allowVolcanoesName = "Allow Volcanoes";
    
    public static final String volcanoChanceId = "volcanoChance";
    public static final String volcanoChanceName = "Volcano Chance";
    
    public static final String useRTGDecorationsId = "useRTGDecorations";
    public static final String useRTGDecorationsName = "Use RTG Decorations";
    
    public static final String useRTGSurfacesId = "useRTGSurfaces";
    public static final String useRTGSurfacesName = "Use RTG Surfaces";
    
    public static final String surfaceTopBlockId = "surfaceTopBlock";
    public static final String surfaceTopBlockName = "RTG Surface: Top Block";
    
    public static final String surfaceTopBlockMetaId = "surfaceTopBlockMeta";
    public static final String surfaceTopBlockMetaName = "RTG Surface: Top Block Meta";
    
    public static final String surfaceFillerBlockId = "surfaceFillerBlock";
    public static final String surfaceFillerBlockName = "RTG Surface: Filler Block";
    
    public static final String surfaceFillerBlockMetaId = "surfaceFillerBlockMeta";
    public static final String surfaceFillerBlockMetaName = "RTG Surface: Filler Block Meta";
    
    public static final String caveDensityId = "caveDensity";
    public static final String caveDensityName = "Cave Density";
    
    public static final String caveFrequencyId = "caveFrequency";
    public static final String caveFrequencyName = "Cave Frequency";
    
    public static final String ravineFrequencyId = "ravineFrequency";
    public static final String ravineFrequencyName = "Ravine Frequency";

    public BiomeConfig(String modSlug, String biomeSlug)
    {
        this.modSlug = modSlug;
        this.biomeSlug = biomeSlug;
        
        this.properties = new ArrayList<BiomeConfigProperty>();

        this.addProperty(new BiomeConfigProperty(allowVillagesId, Type.BOOLEAN, allowVillagesName, "", true));
        
        this.addProperty(new BiomeConfigProperty(allowVolcanoesId, Type.BOOLEAN, allowVolcanoesName, "", false));
        this.addProperty(new BiomeConfigProperty(volcanoChanceId, Type.INTEGER, volcanoChanceName, "1/x chance that a volcano will generate if this biome has volcanoes enabled." + Configuration.NEW_LINE + "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance" + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable volcanoes for this biome." + Configuration.NEW_LINE, -1, -1, Integer.MAX_VALUE));
        
        this.addProperty(new BiomeConfigProperty(useRTGDecorationsId, Type.BOOLEAN, useRTGDecorationsName, "", true));

        this.addProperty(new BiomeConfigProperty(useRTGSurfacesId, Type.BOOLEAN, useRTGSurfacesName, "", true));
        this.addProperty(new BiomeConfigProperty(surfaceTopBlockId, Type.STRING, surfaceTopBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(surfaceTopBlockMetaId, Type.STRING, surfaceTopBlockMetaName, "", ""));
        this.addProperty(new BiomeConfigProperty(surfaceFillerBlockId, Type.STRING, surfaceFillerBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(surfaceFillerBlockMetaId, Type.STRING, surfaceFillerBlockMetaName, "", ""));
        this.addProperty(new BiomeConfigProperty(caveDensityId, Type.INTEGER, caveDensityName, "This setting controls the size of caves." + Configuration.NEW_LINE + "HIGHER values = BIGGER caves & MORE lag. (14 = vanilla cave density)" + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable caves for this biome." + Configuration.NEW_LINE, -1, -1, 40));
        this.addProperty(new BiomeConfigProperty(caveFrequencyId, Type.INTEGER, caveFrequencyName, "This setting controls the number of caves that generate." + Configuration.NEW_LINE + "LOWER values = MORE caves & MORE lag. (6 = vanilla cave frequency)" + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable caves for this biome." + Configuration.NEW_LINE, -1, -1, 40));
        this.addProperty(new BiomeConfigProperty(ravineFrequencyId, Type.INTEGER, ravineFrequencyName, "This setting controls the number of ravines that generate." + Configuration.NEW_LINE + "LOWER values = MORE ravines & MORE lag. (50 = vanilla ravine frequency)" + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable ravines for this biome." + Configuration.NEW_LINE, -1, -1, 100));
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
    
    public void setPropertyValueById(String id, boolean value)
    {
    	getPropertyById(id).valueBoolean = value;
    }
    
    public void setPropertyValueById(String id, int value)
    {
    	getPropertyById(id).valueInt = value;
    }
    
    public void setPropertyValueById(String id, String value)
    {
    	getPropertyById(id).valueString = value;
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
