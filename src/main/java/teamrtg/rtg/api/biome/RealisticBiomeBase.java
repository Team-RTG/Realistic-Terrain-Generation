package teamrtg.rtg.api.biome;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import org.apache.commons.lang3.ArrayUtils;
import teamrtg.rtg.api.config.BiomeConfig;
import teamrtg.rtg.api.mods.RTGSupport;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.biome.surface.SurfaceBase;
import teamrtg.rtg.world.biome.terrain.TerrainBase;
import teamrtg.rtg.world.gen.RealisticBiomeGenerator;
import teamrtg.rtg.world.gen.deco.DecoBase;
import teamrtg.rtg.world.gen.deco.DecoBaseBiomeDecorations;

import java.util.ArrayList;
import java.util.Random;

import static net.minecraft.init.Biomes.river;

public abstract class RealisticBiomeBase extends BiomeBase {

    private static final RealisticBiomeBase[] arrRealisticBiomeIds = new RealisticBiomeBase[256];
    private static float actualRiverProportion = 300f / 1600f;
    public final BiomeGenBase baseBiome;
    public final BiomeGenBase riverBiome;
    public final RTGSupport mod;
    public final BiomeConfig config;
    public TerrainBase terrain;
    public SurfaceBase surface;
    public int clayPerVein;
    public ArrayList<DecoBase> decos;
    public boolean noLakes = false;
    public boolean noWaterFeatures = false;

    public final float lakeInterval = 1470.0f;
    public final double lakeWaterLevel = 0.0;// the lakeStrenght below which things should be below ater
    public final double lakeDepressionLevel = 0.16;// the lakeStrength below which land should start to be lowered

    public RealisticBiomeBase(RTGSupport mod, BiomeGenBase biome) {
        this(mod, biome, river);
    }

    public RealisticBiomeBase(RTGSupport mod, BiomeGenBase biome, BiomeGenBase river) {

        super(RealisticBiomeBase.getIdForBiome(biome));

        this.mod = mod;

        baseBiome = biome;
        riverBiome = river;

        config = new BiomeConfig(getMod().getID(), this.getBiomeName());
        config.TOP_BLOCK.setDefault(biome.topBlock);
        config.FILL_BLOCK.setDefault(biome.fillerBlock);

        initProperties();

        clayPerVein = 20;

        decos = new ArrayList<>();

        /**
         * By default, it is assumed that all realistic biomes will be decorated manually and not by the biome.
         * This includes ore generation since it's part of the decoration process.
         * We're adding this deco here in order to avoid having to explicitly add it
         * in every singe realistic biome.
         * If it does get added manually to let the base biome handle some or all of the decoration process,
         * this deco will get replaced with the new one.
         */
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.allowed = false;
        this.decos.add(decoBaseBiomeDecorations);
        this.surface = initSurface();
        this.terrain = initTerrain();
        initDecos();
    }

    public static int getIdForBiome(BiomeGenBase biome) {
        if (biome instanceof RealisticBiomeBase)
            return BiomeGenBase.getIdForBiome(((RealisticBiomeBase) biome).baseBiome);
        return BiomeGenBase.getIdForBiome(biome);
    }

    /**
     * This should set the defaults for all properties
     */
    protected void initProperties() {}

    protected void initDecos() {}

    protected abstract SurfaceBase initSurface();

    protected abstract TerrainBase initTerrain();

    public static RealisticBiomeBase getBiome(int id) {
        return RealisticBiomeGenerator.getBiome(id);
    }

    public TerrainBase getTerrain() {
        return this.terrain;
    }

    public SurfaceBase getSurface() {
        return this.surface;
    }

    public int getID() {
        return RealisticBiomeBase.getIdForBiome(this);
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

    public void paintSurface(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {
        this.surface.paintSurface(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
    }
}
