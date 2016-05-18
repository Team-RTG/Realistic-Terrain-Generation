package teamrtg.rtg.api.world.biome;

import net.minecraft.world.biome.BiomeGenBase;
import org.apache.commons.lang3.ArrayUtils;
import teamrtg.rtg.api.config.BiomeConfig;
import teamrtg.rtg.api.mods.RTGSupport;
import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.GenericPart;
import teamrtg.rtg.api.world.biome.surface.part.PresetParts;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

import java.util.ArrayList;

import static net.minecraft.init.Biomes.RIVER;

public abstract class RealisticBiomeBase implements IMapGen {

    public static final float actualRiverProportion = 300f / 1600f;
    public final BiomeGenBase riverBiome;
    public final RTGSupport mod;
    public final float lakeInterval = 989.0f;
    public final float lakeShoreLevel = 0.15f;
    public final float lakeWaterLevel = 0.11f;// the lakeStrength below which things should be below water
    public final float lakeDepressionLevel = 0.30f;// the lakeStrength below which land should start to be lowered
    public final float largeBendSize = 100;
    // lake calculations
    public final float mediumBendSize = 40;
    public final float smallBendSize = 15;
    public final boolean disallowAllBeaches = false;
    public final boolean disallowStoneBeaches = false;
    protected final BiomeGenBase baseBiome;
    protected final BiomeConfig config;
    public PresetParts PARTS;
    public TerrainBase terrain;
    public SurfacePart surface;
    public ArrayList<DecoBase> decos = new ArrayList<>();
    public boolean noLakes = false;
    public boolean noWaterFeatures = false;

    public RealisticBiomeBase(RTGSupport mod, BiomeGenBase biome) {
        this(mod, biome, RIVER);
    }

    public RealisticBiomeBase(RTGSupport mod, BiomeGenBase biome, BiomeGenBase river) {


        this.mod = mod;

        baseBiome = biome;
        riverBiome = river;

        this.config = new BiomeConfig(getMod().getID(), this.getName());
        this.config.TOP_BLOCK.setDefault(biome.topBlock);
        this.config.FILL_BLOCK.setDefault(biome.fillerBlock);
        init();
    }

    private void init() {
        initConfig();
        this.PARTS = new PresetParts(this);
        this.surface = initSurface();
        this.terrain = initTerrain();
        initDecos();
    }

    /**
     * This should set the defaults for all properties
     */
    @Override
    public void initConfig() {}

    @Override
    public SurfacePart initSurface() {
        return new GenericPart(config.TOP_BLOCK.get(), config.FILL_BLOCK.get());
    }

    @Override
    public abstract TerrainBase initTerrain();

    @Override
    public void initDecos() {
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.allowed = true;
        this.decos.add(decoBaseBiomeDecorations);
    }

    @Override
    public ArrayList<DecoBase> getDecos() {
        return this.decos;
    }

    /**
     * Adds a deco object to the list of biome decos.
     * @param deco
     */
    @Override
    public void addDeco(DecoBase deco) {
        this.decos.add(deco);
        this.config.DECORATIONS.setOptions(ArrayUtils.add(this.config.DECORATIONS.getOptions(), deco.getName()));
        this.config.DECORATIONS.setDefault(ArrayUtils.add(this.config.DECORATIONS.getDefault(), deco.getName()));
    }

    @Override
    public TerrainBase getTerrain() {
        return this.terrain;
    }

    @Override
    public SurfacePart getSurface() {
        return this.surface;
    }

    @Override
    public BiomeConfig getConfig() {
        return config;
    }

    @Override
    public RTGSupport getMod() {
        return mod;
    }

    @Override
    public String getName() {
        return baseBiome.getBiomeName();
    }

    public static RealisticBiomeBase forBiome(BiomeGenBase biome) {
        return RealisticBiomeBase.forBiome(BiomeUtils.getId(biome));
    }

    public static RealisticBiomeBase forBiome(int id) {
        return RealisticBiomeGenerator.getRealistic(id);
    }

    public BiomeGenBase getBiome() {
        return baseBiome;
    }

    public int getID() {
        return BiomeUtils.getId(baseBiome);
    }
}
