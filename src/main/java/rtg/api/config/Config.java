package rtg.api.config;

import net.minecraft.block.state.IBlockState;
import net.minecraftforge.common.config.Configuration;
import rtg.api.util.ISupportedMod;
import rtg.api.util.debug.Logger;
import rtg.util.SupportedMod;
import rtg.world.biome.realistic.RealisticBiomeBase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * An object that holds config properties
 * Does not provide a way to store those properties in a file
 * @see ModConfig for that functionality.
 * @author topisani
 */
public class Config {

    public ArrayList<ConfigProperty> properties;

    public Config() {
        this.properties = new ArrayList<>();
    }

    public void addProperties(ConfigProperty.IPropertyEnum[] props) {
        for (ConfigProperty.IPropertyEnum prop : props) {
            this.addProperty(prop.get());
        }
    }

    public ConfigProperty addProperty(ConfigProperty property) {
        for (int i = 0; i < this.properties.size(); i++) {

            if (this.properties.get(i).id.contentEquals(property.id)) {
                removeProperty(property.id);
                break;
            }
        }

        this.properties.add(property);
        return property;
    }

    public void removeProperty(String id) {
        for (int i = 0; i < this.properties.size(); i++) {

            if (this.properties.get(i).id.contentEquals(id)) {
                this.properties.remove(i);
                return;
            }
        }
    }

    public ArrayList<ConfigProperty> getProperties() {
        return this.properties;
    }

    public ConfigProperty getPropertyById(ConfigProperty.IPropertyEnum id) {
        for (int i = 0; i < this.properties.size(); i++) {

            if (this.properties.get(i).id.contentEquals(id.name())) {
                return this.properties.get(i);
            }
        }
        return null;
    }

    public boolean _boolean(ConfigProperty.IPropertyEnum id) {
        try {
            return getPropertyById(id).valueBoolean;
        } catch (Exception e) {
            throw new RuntimeException("Config property " + id.name() + " could not be found. Reason: " + e.getMessage());
        }
    }

    public int _int(ConfigProperty.IPropertyEnum id) {
        try {

            return getPropertyById(id).valueInt;
        } catch (Exception e) {

            throw new RuntimeException("Config property " + id.name() + " could not be found. Reason: " + e.getMessage());
        }
    }

    public String _string(ConfigProperty.IPropertyEnum id) {
        try {

            return getPropertyById(id).valueString;
        } catch (Exception e) {

            throw new RuntimeException("Config property " + id.name() + " could not be found. Reason: " + e.getMessage());
        }
    }

    public IBlockState _block(ConfigProperty.IPropertyEnum id) {
        try {
            return getPropertyById(id).valueBlock;
        } catch (Exception e) {

            throw new RuntimeException("Config property " + id.name() + " could not be found. Reason: " + e.getMessage());
        }
    }

    /**
     * Will get a property, and if it doesn't exist add it with a default value
     */
    public boolean get(ConfigProperty.IPropertyEnum id, boolean defaultBoolean) {
        try {
            return this.getPropertyById(id).valueBoolean;
        } catch (Exception e) {
            return this.addProperty(id.get().setDefault(defaultBoolean)).valueBoolean;
        }
    }

    /**
     * Will get a property, and if it doesn't exist add it with a default value
     */
    public int get(ConfigProperty.IPropertyEnum id, int defaultInt) {
        try {
            return this.getPropertyById(id).valueInt;
        } catch (Exception e) {
            return this.addProperty(id.get().setDefault(defaultInt)).valueInt;
        }
    }

    /**
     * Will get a property, and if it doesn't exist add it with a default value
     */
    public String get(ConfigProperty.IPropertyEnum id, String defaultString) {
        try {
            return this.getPropertyById(id).valueString;
        } catch (Exception e) {
            return this.addProperty(id.get().setDefault(defaultString)).valueString;
        }
    }

    /**
     * Will get a property, and if it doesn't exist add it with a default value
     */
    public IBlockState get(ConfigProperty.IPropertyEnum id, IBlockState defaultBlock) {
        try {
            return this.getPropertyById(id).valueBlock;
        } catch (Exception e) {
            return this.addProperty(id.get().setDefault(defaultBlock)).valueBlock;
        }
    }

    public static class ModConfig {
        public final Configuration config;
        public final ISupportedMod mod;
        private Map<Class<? extends RealisticBiomeBase>, BiomeConfig> biomeConfigMap = new HashMap<>();

        public ModConfig(SupportedMod mod) {
            this.config = new Configuration(new File("biomes/" + mod.getModId() + ".cfg"));
            this.mod = mod;

            try {
                config.load();

                this.setDefaults();

            } catch (Exception e) {
                Logger.error("RTG has had a problem loading " + mod.getModId() + " configuration.");
            } finally {
                if (config.hasChanged()) {
                    config.save();
                }
            }
        }

        public BiomeConfig setBiomeConfig(Class<? extends RealisticBiomeBase> biome, BiomeConfig config) {
            return this.biomeConfigMap.put(biome, config);
        }

        public BiomeConfig setBiomeConfig(Class<? extends RealisticBiomeBase> biome, ConfigProperty[] props) {
            String s = biome.getSimpleName();
            s = s.substring(s.indexOf("RealisticBiome") + 1);
            BiomeConfig config = new BiomeConfig(mod.getModId(), s, props);
            this.biomeConfigMap.put(biome, config );
            return config;
        }

        public BiomeConfig getBiomeConfig(Class<? extends RealisticBiomeBase> biome) {
            return this.biomeConfigMap.get(biome);
        }

        public void setDefaults() {}
    }
}
