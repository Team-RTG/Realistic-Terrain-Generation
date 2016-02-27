package rtg.api.biome;

import net.minecraft.block.Block;
import rtg.util.Logger;
import rtg.util.VillageMaterial;
import rtg.util.VillageMaterialSwap;

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

    public static final String foundationBlockId = "foundationBlock";
    public static final String foundationBlockName = "Foundation block (Cobblestone)";

    public static final String foundationMetaId = "foundationMeta";
    public static final String foundationMetaName = "Foundation Meta (Cobblestone)";

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

    public static final String doorBlockId = "doorBlock";
    public static final String doorBlockName = "Door Block (Oak door)";

    public static final String doorMetaId = "doorMeta";
    public static final String doorMetaName = "Door Meta (Oak door)";

    public VillageConfig(String modSlug, String biomeSlug)
    {
        this.modSlug = modSlug;
        this.biomeSlug = biomeSlug;

        this.properties = new ArrayList<BiomeConfigProperty>();

        this.addProperty(new BiomeConfigProperty(pathBlockId, BiomeConfigProperty.Type.STRING, pathBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(pathMetaId, BiomeConfigProperty.Type.STRING, pathMetaName, "",""));
        this.addProperty(new BiomeConfigProperty(roofBlockId, BiomeConfigProperty.Type.STRING, roofBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(roofMetaId, BiomeConfigProperty.Type.STRING, roofMetaName, "",  ""));
        this.addProperty(new BiomeConfigProperty(stairBlockId, BiomeConfigProperty.Type.STRING, stairBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(stairMetaId, BiomeConfigProperty.Type.STRING, stairMetaName, "",""));
        this.addProperty(new BiomeConfigProperty(foundationBlockId, BiomeConfigProperty.Type.STRING, foundationBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(foundationMetaId, BiomeConfigProperty.Type.STRING, foundationMetaName, "", ""));
        this.addProperty(new BiomeConfigProperty(wallBlockId, BiomeConfigProperty.Type.STRING, wallBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(wallMetaId, BiomeConfigProperty.Type.STRING, wallMetaName, "", ""));
        this.addProperty(new BiomeConfigProperty(cornerBlockId, BiomeConfigProperty.Type.STRING, cornerBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(cornerMetaId, BiomeConfigProperty.Type.STRING, cornerMetaName, "", ""));
        this.addProperty(new BiomeConfigProperty(fenceBlockId, BiomeConfigProperty.Type.STRING, fenceBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(fenceMetaId, BiomeConfigProperty.Type.STRING, fenceMetaName, "", ""));
        this.addProperty(new BiomeConfigProperty(blacksmithRoofBlockId, BiomeConfigProperty.Type.STRING, blacksmithRoofBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(blacksmithRoofMetaId, BiomeConfigProperty.Type.STRING, blacksmithRoofMetaName, "",  ""));
        this.addProperty(new BiomeConfigProperty(doorBlockId, BiomeConfigProperty.Type.STRING, doorBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(doorMetaId, BiomeConfigProperty.Type.STRING, doorMetaName, "", ""));
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

            throw new RuntimeException("Village config property (" + modSlug + "." + biomeSlug + "." + id + ") could not be found. Reason: " + e.getMessage());
        }
    }

    public int _int(String id)
    {
        try {
            String s = getPropertyById(id).valueString;
            if (!s.isEmpty()) return Integer.valueOf(getPropertyById(id).valueString);
            return 0;
        }
        catch (Exception e) {

            throw new RuntimeException("Village config property (" + modSlug + "." + biomeSlug + "." + id + ") could not be found. Reason: " + e.getMessage());
        }
    }

    public String _string(String id)
    {
        try {

            return getPropertyById(id).valueString;
        }
        catch (Exception e) {

            throw new RuntimeException("Village config property (" + modSlug + "." + biomeSlug + "." + id + ") could not be found. Reason: " + e.getMessage());
        }
    }

    /**
     * Updates the given VillageMaterial with the user configs
     * @param material will be changed
     * @return the same material as was input, to allow method chaining.
     */
    public VillageMaterial getMaterial(VillageMaterial material) {
        propertiesToSwap(material.path, pathBlockId, pathMetaId);
        propertiesToSwap(material.blacksmith_roof, blacksmithRoofBlockId, blacksmithRoofMetaId);
        propertiesToSwap(material.corner, cornerBlockId, cornerMetaId);
        propertiesToSwap(material.door, doorBlockId, doorMetaId);
        propertiesToSwap(material.fence, doorBlockId, doorMetaId);
        propertiesToSwap(material.foundation, foundationBlockId, foundationMetaId);
        propertiesToSwap(material.roof, roofBlockId, roofMetaId);
        propertiesToSwap(material.stairs, stairBlockId, stairMetaId);
        propertiesToSwap(material.wall, wallBlockId, wallMetaId);
        return material;
    }

    private void propertiesToSwap(VillageMaterialSwap swap, String blockId, String metaId) {
        String blockProp;
        int metaProp;
        try {
            blockProp = _string(blockId);
        } catch (RuntimeException e) {
            Logger.error(e.getMessage());
            Logger.warn("Villageconfig: %s for biome %s from %s is invalid. Using default.", blockId, biomeSlug, modSlug);
            return;
        }
        try {
            metaProp = _int(metaId);
        } catch (RuntimeException e) {
            Logger.error(e.getMessage());
            metaProp = 0;
        }
        if (blockProp.isEmpty()) {
            return;
        }
        if (metaProp == -1) {
            swap.setPreserveMeta(true);
            metaProp = 0;
        }
        Block replacement = Block.getBlockFromName(blockProp);
        if (replacement == null ) {
            Logger.warn("Villageconfig: %s for biome %s from %s is invalid. Using default.", blockId, biomeSlug, modSlug);
            return;
        }
        swap.setReplacement(replacement.getStateFromMeta(metaProp));
    }
}
