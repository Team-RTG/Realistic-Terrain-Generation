package rtg.api.biome;

import net.minecraft.block.state.IBlockState;

import java.util.ArrayList;

import static rtg.api.biome.BiomeConfigProperty.BiomeProperty.*;


public class BiomeConfig {

    public String modSlug;
    public String biomeSlug;
    public ArrayList<BiomeConfigProperty> properties;

    public BiomeConfig(String modSlug, String biomeSlug) {
        this.modSlug = modSlug;
        this.biomeSlug = biomeSlug;

        this.properties = new ArrayList<BiomeConfigProperty>();

        this.addProperty(ALLOW_VILLAGES.prop);
        this.addProperty(USE_RTG_DECORATIONS.prop);
        this.addProperty(USE_RTG_SURFACES.prop);
        this.addProperty(SURFACE_TOP_BLOCK.prop);
        this.addProperty(SURFACE_FILLER_BLOCK.prop);
    }

    public void addProperty(BiomeConfigProperty property) {
        for (int i = 0; i < this.properties.size(); i++) {

            if (this.properties.get(i).id.contentEquals(property.id)) {
                removeProperty(property.id);
                break;
            }
        }

        this.properties.add(property);
    }

    public void removeProperty(String id) {
        for (int i = 0; i < this.properties.size(); i++) {

            if (this.properties.get(i).id.contentEquals(id)) {
                this.properties.remove(i);
                return;
            }
        }
    }

    public ArrayList<BiomeConfigProperty> getProperties() {
        return this.properties;
    }

    public boolean _boolean(BiomeConfigProperty.IBiomePropertyEnum id) {
        try {

            return getPropertyById(id).valueBoolean;
        } catch (Exception e) {

            throw new RuntimeException("Biome config property (" + modSlug + "." + biomeSlug + "." + id + ") could not be found. Reason: " + e.getMessage());
        }
    }

    public BiomeConfigProperty getPropertyById(BiomeConfigProperty.IBiomePropertyEnum id) {
        for (int i = 0; i < this.properties.size(); i++) {

            if (this.properties.get(i).id.contentEquals(id.name())) {
                return this.properties.get(i);
            }
        }
        return null;
    }

    public int _int(BiomeConfigProperty.IBiomePropertyEnum id) {
        try {

            return getPropertyById(id).valueInt;
        } catch (Exception e) {

            throw new RuntimeException("Biome config property (" + modSlug + "." + biomeSlug + "." + id + ") could not be found. Reason: " + e.getMessage());
        }
    }

    public String _string(BiomeConfigProperty.IBiomePropertyEnum id) {
        try {

            return getPropertyById(id).valueString;
        } catch (Exception e) {

            throw new RuntimeException("Biome config property (" + modSlug + "." + biomeSlug + "." + id + ") could not be found. Reason: " + e.getMessage());
        }
    }

    public IBlockState _block(BiomeConfigProperty.IBiomePropertyEnum id) {
        try {

            return getPropertyById(id).valueBlock;
        } catch (Exception e) {

            throw new RuntimeException("Biome config property (" + modSlug + "." + biomeSlug + "." + id + ") could not be found. Reason: " + e.getMessage());
        }
    }
}
