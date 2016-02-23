package rtg.api.biome;

import java.util.ArrayList;

/**
 * Created by topisani on 2/23/16.
 */
public class VillageConfig {

    public String modSlug;
    public String biomeSlug;

    public ArrayList<BiomeConfigProperty> properties;

    public static final String pathBlockId = "pathBlock";
    public static final String pathBlockName = "Path Block (Gravel)";

    public static final String pathMetaId = "pathMeta";
    public static final String pathMetaName = "Path Meta (Gravel)";

    public static final String roofBlockId = "roofBlock";
    public static final String roofBlockName = "Roof Block (Wooden Stairs)";

    public static final String roofMetaId = "roofMeta";
    public static final String roofMetaName = "Roof Meta (Wooden Stairs)";

    public static final String stairBlockId = "stairBlock";
    public static final String stairBlockName = "Stairs Block (Cobblestone Stairs)";

    public static final String stairMetaId = "stairMeta";
    public static final String stairMetaName = "Stairs Meta (Cobblestone Stairs)";

    public static final String floorBlockId = "floorBlock";
    public static final String floorBlockName = "Floor block (Cobblestone)";

    public static final String floorMetaId = "floorMeta";
    public static final String floorMetaName = "Floor Meta (Cobblestone)";

    public static final String wallBlockId = "wallBlock";
    public static final String wallBlockName = "Wall Block (Planks)";

    public static final String wallMetaId = "wallMeta";
    public static final String wallMetaName = "Wall Meta (Planks)";

    public static final String cornerBlockId = "cornerBlock";
    public static final String cornerBlockName = "Corner Block (Logs)";

    public static final String cornerMetaId = "cornerMeta";
    public static final String cornerMetaName = "Corner Meta (Logs)";

    public static final String fenceBlockId = "fenceBlock";
    public static final String fenceBlockName = "Fence Block (Wooden Fencepost)";

    public static final String fenceMetaId = "fenceMeta";
    public static final String fenceMetaName = "Fence Meta (Wooden Fencepost)";

    public static final String blacksmithRoofBlockId = "blacksmithRoofBlock";
    public static final String blacksmithRoofBlockName = "Blacksmith Roof Block (Stone Slabs)";

    public static final String blacksmithRoofMetaId = "blacksmithRoofMeta";
    public static final String blacksmithRoofMetaName = "Blacksmith Roof Meta (Stone Slabs)";

    public VillageConfig(String modSlug, String biomeSlug)
    {
        this.modSlug = modSlug;
        this.biomeSlug = biomeSlug;

        this.properties = new ArrayList<BiomeConfigProperty>();

        this.addProperty(new BiomeConfigProperty(pathBlockId, BiomeConfigProperty.Type.STRING, pathBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(pathMetaId, BiomeConfigProperty.Type.STRING, pathMetaName, "", ""));
        this.addProperty(new BiomeConfigProperty(roofBlockId, BiomeConfigProperty.Type.STRING, roofBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(roofMetaId, BiomeConfigProperty.Type.STRING, roofMetaName, "", ""));
        this.addProperty(new BiomeConfigProperty(stairBlockId, BiomeConfigProperty.Type.STRING, stairBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(stairMetaId, BiomeConfigProperty.Type.STRING, stairMetaName, "", ""));
        this.addProperty(new BiomeConfigProperty(floorBlockId, BiomeConfigProperty.Type.STRING, floorBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(floorMetaId, BiomeConfigProperty.Type.STRING, floorMetaName, "", ""));
        this.addProperty(new BiomeConfigProperty(wallBlockId, BiomeConfigProperty.Type.STRING, wallBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(wallMetaId, BiomeConfigProperty.Type.STRING, wallMetaName, "", ""));
        this.addProperty(new BiomeConfigProperty(cornerBlockId, BiomeConfigProperty.Type.STRING, cornerBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(cornerMetaId, BiomeConfigProperty.Type.STRING, cornerMetaName, "", ""));
        this.addProperty(new BiomeConfigProperty(fenceBlockId, BiomeConfigProperty.Type.STRING, fenceBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(fenceMetaId, BiomeConfigProperty.Type.STRING, fenceMetaName, "", ""));
        this.addProperty(new BiomeConfigProperty(blacksmithRoofBlockId, BiomeConfigProperty.Type.STRING, blacksmithRoofBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(blacksmithRoofMetaId, BiomeConfigProperty.Type.STRING, blacksmithRoofMetaName, "", ""));
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
