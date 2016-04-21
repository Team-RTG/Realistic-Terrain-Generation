package teamrtg.rtg.api.biome;

import net.minecraft.world.biome.BiomeGenBase;
import org.apache.commons.lang3.ArrayUtils;
import teamrtg.rtg.api.config.BiomeConfig;
import teamrtg.rtg.api.mods.RTGSupport;
import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.biome.surface.part.GenericPart;
import teamrtg.rtg.world.biome.surface.part.PresetParts;
import teamrtg.rtg.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.world.biome.terrain.TerrainBase;
import teamrtg.rtg.world.gen.ChunkProviderRTG;
import teamrtg.rtg.world.gen.RealisticBiomeGenerator;
import teamrtg.rtg.world.gen.deco.DecoBase;
import teamrtg.rtg.world.gen.deco.DecoBaseBiomeDecorations;

import java.util.ArrayList;
import java.util.Random;

import static net.minecraft.init.Biomes.RIVER;

public abstract class RealisticBiomeBase extends BiomeBase {

    public final BiomeGenBase baseBiome;
    public final BiomeGenBase riverBiome;
    public final RTGSupport mod;
    public final BiomeConfig config;
    public final ChunkProviderRTG chunkProvider;
    public final OpenSimplexNoise simplex;
    public final Random rand;
    public PresetParts PARTS;
    public TerrainBase terrain;
    public SurfacePart surface;
    public ArrayList<DecoBase> decos;
    public boolean noLakes = false;
    public boolean noWaterFeatures = false;

    public final float lakeInterval = 1470.0f;
    public final double lakeWaterLevel = 0.0;// the lakeStrenght below which things should be below ater
    public final double lakeDepressionLevel = 0.16;// the lakeStrength below which land should start to be lowered

    public RealisticBiomeBase(RTGSupport mod, BiomeGenBase biome, ChunkProviderRTG chunkProvider) {
        this(mod, biome, RIVER, chunkProvider);
    }

    public RealisticBiomeBase(RTGSupport mod, BiomeGenBase biome, BiomeGenBase river, ChunkProviderRTG chunkProvider) {

        super(BiomeUtils.getIdForBiome(biome));

        this.mod = mod;
        this.chunkProvider = chunkProvider;
        this.rand = chunkProvider.rand;
        this.simplex = chunkProvider.simplex;

        baseBiome = biome;
        riverBiome = river;

        this.config = new BiomeConfig(getMod().getID(), this.getBiomeName());
        this.config.TOP_BLOCK.setDefault(biome.topBlock);
        this.config.FILL_BLOCK.setDefault(biome.fillerBlock);

        initProperties();

        init();
        initDecos();
    }

    private void init() {
        this.PARTS = new PresetParts(this);

        decos = new ArrayList<>();
        this.surface = initSurface();
        this.terrain = initTerrain();
    }

    /**
     * This should set the defaults for all properties
     */
    protected void initProperties() {}

    protected void initDecos() {
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.allowed = false;
        this.decos.add(decoBaseBiomeDecorations);
    }

    protected SurfacePart initSurface() {
        return new GenericPart(config.TOP_BLOCK.get(), config.FILL_BLOCK.get());
    }

    protected abstract TerrainBase initTerrain();

    public static RealisticBiomeBase getBiome(int id) {
        return RealisticBiomeGenerator.getBiome(id);
    }

    public TerrainBase getTerrain() {
        return this.terrain;
    }

    public SurfacePart getSurface() {
        return this.surface;
    }

    public int getID() {
        return BiomeUtils.getIdForBiome(this);
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
