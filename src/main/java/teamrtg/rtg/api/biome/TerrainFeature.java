package teamrtg.rtg.api.biome;

import org.apache.commons.lang3.ArrayUtils;
import teamrtg.rtg.api.config.BiomeConfig;
import teamrtg.rtg.api.mods.RTGSupport;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.biome.surface.part.GenericPart;
import teamrtg.rtg.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.world.biome.terrain.TerrainBase;
import teamrtg.rtg.world.gen.ChunkProviderRTG;
import teamrtg.rtg.world.gen.deco.DecoBase;
import teamrtg.rtg.world.gen.deco.DecoBaseBiomeDecorations;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author topisani
 */
public abstract class TerrainFeature implements IGenArea {

    public final RTGSupport mod;
    public final BiomeConfig config;
    public final ChunkProviderRTG chunkProvider;
    public final OpenSimplexNoise simplex;
    public final Random rand;
    //public final PresetParts PARTS;
    public TerrainBase terrain;
    public SurfacePart surface;
    public final String name;

    public ArrayList<DecoBase> decos;

    public TerrainFeature(RTGSupport mod, String name, ChunkProviderRTG chunkProvider) {

        this.mod = mod;
        this.chunkProvider = chunkProvider;
        this.name = name;
        this.rand = chunkProvider.rand;
        this.simplex = chunkProvider.simplex;

        config = new BiomeConfig(getMod().getID(), name);

        initConfig();
        //this.PARTS = new PresetParts(this);

        this.surface = initSurface();
        initDecos();
        this.terrain = initTerrain();
        initDecos();
    }

    /**
     * This should set the defaults for all properties
     */
    public void initConfig() {}

    public void initDecos() {
        ArrayList<DecoBase> decos = new ArrayList<>();
        this.decos = decos;
    }

    ;

    public SurfacePart initSurface() {
        return new GenericPart(config.TOP_BLOCK.get(), config.FILL_BLOCK.get());
    }

    public abstract TerrainBase initTerrain();

    public TerrainBase getTerrain() {
        return this.terrain;
    }

    public SurfacePart getSurface() {
        return this.surface;
    }

    /**
     * Convenience method for addDeco() where 'allowed' is assumed to be true.
     * @param deco
     */
    public void addDeco(DecoBase deco) {
        this.addDeco(deco, true);
    }

    /**
     * Adds a deco object to the list of biome decos.
     * The 'allowed' parameter allows us to pass biome forgeConfig booleans dynamically when configuring the decos in the biome.
     * @param deco
     * @param allowed
     */
    public void addDeco(DecoBase deco, boolean allowed) {
        if (allowed) {

            if (deco instanceof DecoBaseBiomeDecorations) {

                for (int i = 0; i < this.decos.size(); i++) {

                    if (this.decos.get(i) instanceof DecoBaseBiomeDecorations) {

                        this.decos.remove(i);
                        break;
                    }
                }
            }

            this.decos.add(deco);
            this.config.DECORATIONS.setOptions(ArrayUtils.add(this.config.DECORATIONS.getOptions(), deco.getName()));
            this.config.DECORATIONS.setDefault(ArrayUtils.add(this.config.DECORATIONS.getDefault(), deco.getName()));
        }
    }

    public RTGSupport getMod() {
        return mod;
    }
}
