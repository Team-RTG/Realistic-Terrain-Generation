package teamrtg.rtg.api.world.biome;

import org.apache.commons.lang3.ArrayUtils;
import teamrtg.rtg.api.config.BiomeConfig;
import teamrtg.rtg.api.module.RTGModule;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.biome.surface.part.PresetParts;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;

import java.util.ArrayList;

/**
 * @author topisani
 */
public class WorldFeature implements IWorldFeature {

    public final RTGModule mod;
    public final String name;
    protected final BiomeConfig config;
    public PresetParts PARTS;
    public SurfacePart surface;
    public ArrayList<DecoBase> decos = new ArrayList<>();

    public WorldFeature(RTGModule mod, String name) {
        this.mod = mod;
        this.name = name;
        this.config = new BiomeConfig(getMod().getID(), "worldFeature." + this.getName());
        init();
    }

    /**
     * This should set the defaults for all properties
     */
    public void initConfig() {}

    private void init() {
        initConfig();
        this.PARTS = new PresetParts(this);
        this.surface = initSurface();
        initDecos();
    }

    @Override
    public SurfacePart initSurface() {
        return new SurfacePart();
    }

    public float modifyTerrain(RTGWorld rtgWorld, RTGBiome biome, float heightIn, int x, int z, float border, float river) {
        return heightIn;
    }

    @Override
    public void initDecos() {
    }

    @Override
    public ArrayList<DecoBase> getDecos() {
        return this.decos;
    }

    /**
     * Adds a deco object to the list of biome decos.
     * @param deco
     */
    public void addDeco(DecoBase deco) {
        this.decos.add(deco);
        this.config.DECORATIONS.setOptions(ArrayUtils.add(this.config.DECORATIONS.getOptions(), deco.getName()));
        this.config.DECORATIONS.setDefault(ArrayUtils.add(this.config.DECORATIONS.getDefault(), deco.getName()));
    }

    public SurfacePart getSurface() {
        return this.surface;
    }

    @Override
    public BiomeConfig getConfig() {
        return config;
    }

    @Override
    public RTGModule getMod() {
        return mod;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
