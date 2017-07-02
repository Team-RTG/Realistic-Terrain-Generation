package rtg.world.biome.realistic;

import net.minecraft.world.biome.Biome;

import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.deco.DecoBase;
import rtg.api.world.deco.collection.DecoCollectionBase;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

/**
 * Created by WhichOnesPink on 08/02/2017.
 */
public class RealisticBiomeCreator extends RealisticBiomeBase {

    private IRealisticBiome iRealisticBiome;

    private RealisticBiomeCreator(Biome biome, Biome river) {
        super(biome, river);
    }

    public RealisticBiomeCreator(IRealisticBiome iRealisticBiome) {

        this(iRealisticBiome.baseBiome(), iRealisticBiome.riverBiome());

        this.iRealisticBiome = iRealisticBiome;

        super.init();
    }

    @Override
    protected void init() {
        ; // Do nothing here so that we can super.init() after the iRealisticBiome variable has been initialized.
    }

    @Override
    public Biome baseBiome() {
        return this.iRealisticBiome.baseBiome();
    }

    @Override
    public Biome riverBiome() {
        return this.iRealisticBiome.riverBiome();
    }

    @Override
    public void initConfig() {
        this.iRealisticBiome.initConfig();
    }

    @Override
    public TerrainBase initTerrain() {
        return this.iRealisticBiome.initTerrain();
    }

    @Override
    public SurfaceBase initSurface() {
        return this.iRealisticBiome.initSurface();
    }

    @Override
    public void initDecos() {
        this.iRealisticBiome.initDecos();
    }

    @Override
    public String modSlug() {
        return this.iRealisticBiome.modSlug();
    }

    @Override
    public boolean generatesEmeralds() {
        return this.iRealisticBiome.generatesEmeralds();
    }

    @Override
    public boolean generatesSilverfish() {
        return this.iRealisticBiome.generatesSilverfish();
    }

    @Override
    public int waterUndergroundLakeChance() {
        return this.iRealisticBiome.waterUndergroundLakeChance();
    }

    @Override
    public int lavaUndergroundLakeChance() {
        return this.iRealisticBiome.lavaUndergroundLakeChance();
    }

    @Override
    public int waterSurfaceLakeChance() {
        return this.iRealisticBiome.waterSurfaceLakeChance();
    }

    @Override
    public int lavaSurfaceLakeChance() {
        return this.iRealisticBiome.lavaSurfaceLakeChance();
    }

    @Override
    public float lakePressure(IRTGWorld rtgWorld, int x, int y, float border, float lakeInterval, float largeBendSize, float mediumBendSize, float smallBendSize) {
        return this.iRealisticBiome.lakePressure(rtgWorld, x, y, border, lakeInterval, largeBendSize, mediumBendSize, smallBendSize);
    }

    @Override
    public int getExtraGoldGenCount() {
        return this.iRealisticBiome.getExtraGoldGenCount();
    }

    @Override
    public int getExtraGoldGenMinHeight() {
        return this.iRealisticBiome.getExtraGoldGenMinHeight();
    }

    @Override
    public int getExtraGoldGenMaxHeight() {
        return this.iRealisticBiome.getExtraGoldGenMaxHeight();
    }

    @Override
    public void addDeco(DecoBase deco, boolean allowed) {
        this.iRealisticBiome.addDeco(deco, allowed);
    }

    @Override
    public void addDeco(DecoBase deco) {

        /*
         * Temporary hack to get around the call to addDeco() in RBB constructor.
         * TODO: Remove this hack.
         */

        try {
            this.iRealisticBiome.addDeco(deco);
        }
        catch (Exception e) {
            ;
        }
    }

    @Override
    public void addDecoCollection(DecoCollectionBase decoCollection) {
        this.iRealisticBiome.addDecoCollection(decoCollection);
    }

    @Override
    public void addTree(TreeRTG tree, boolean allowed) {
        this.iRealisticBiome.addTree(tree, allowed);
    }

    @Override
    public void addTree(TreeRTG tree) {
        this.iRealisticBiome.addTree(tree);
    }
}
