package teamrtg.rtg.api.world.biome;

import static net.minecraft.init.Biomes.RIVER;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import org.apache.commons.lang3.ArrayUtils;

import teamrtg.rtg.api.config.BiomeConfig;
import teamrtg.rtg.api.module.RTGModule;
import teamrtg.rtg.api.tools.deco.collection.DecoCollectionBase;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTG;
import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.GenericPart;
import teamrtg.rtg.api.world.biome.surface.part.PresetParts;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;
import teamrtg.rtg.modules.rtg.terrainfeature.WaterFeatures;

public abstract class RTGBiome implements IWorldFeature {

    public static final float actualRiverProportion = 300f / 1600f;
    public final Biome riverBiome;
    public final RTGModule mod;
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
    protected final Biome baseBiome;
    protected final BiomeConfig config;
    public PresetParts PARTS;
    public TerrainBase terrain;
    public SurfacePart surface;
    public ArrayList<DecoBase> decos = new ArrayList<>();
    public ArrayList<TreeRTG> rtgTrees = new ArrayList<>();
    private List<WorldFeature> worldFeatures = new ArrayList<>();
    public boolean noLakes = false;
    public boolean noWaterFeatures = false;

    public RTGBiome(RTGModule mod, Biome biome) {
        this(mod, biome, RIVER);
    }

    public RTGBiome(RTGModule mod, Biome biome, Biome river) {
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
        this.terrain = initTerrain();
        initDecos();
        initWorldFeatures();
        this.surface = new SurfacePart();
        // WorldFeature surfaces take precedence
        for (WorldFeature feature : worldFeatures) {
            this.surface.add(feature.getSurface());
        }
        this.surface.add(initSurface());
    }

    /**
     * This should set the defaults for all properties
     */
    @Override
    public void initConfig() {}

    public abstract TerrainBase initTerrain();

    @Override
    public void initDecos() {
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.allowed = true;
        this.decos.add(decoBaseBiomeDecorations);
    }

    public void initWorldFeatures() {
        addWorldFeature(new WaterFeatures());
    }

    @Override
    public SurfacePart initSurface() {
        return new GenericPart(config.TOP_BLOCK.get(), config.FILL_BLOCK.get());
    }

    @Override
    public SurfacePart getSurface() {
        return this.surface;
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
    
    /**
     * Adds a collection of deco objects to the biome.
     * @param decoCollection
     */
    public void addDecoCollection(DecoCollectionBase decoCollection)
    {
    	if (decoCollection.decos.size() > 0) {
    		for (int i = 0; i < decoCollection.decos.size(); i++) {
    			this.addDeco(decoCollection.decos.get(i));
    		}
    	}
    	
    	if (decoCollection.rtgTrees.size() > 0) {
    		for (int i = 0; i < decoCollection.rtgTrees.size(); i++) {
    			this.addTree(decoCollection.rtgTrees.get(i));
    		}
    	}
    }
    
    /**
     * Adds a tree to the list of RTG trees associated with this biome.
     * The 'allowed' parameter allows us to pass biome config booleans dynamically when configuring the trees in the biome.
     * 
     * @param tree
     * @param allowed
     */
    public void addTree(TreeRTG tree, boolean allowed)
    {
    	if (allowed) {

        	// Set the sapling data for this tree before we add it to the list.
        	
        	for (int i = 0; i < 4; i++) {
            	if (tree.leavesBlock == Blocks.LEAVES.getStateFromMeta(i)) {
            		tree.saplingBlock = Blocks.SAPLING.getStateFromMeta(i);
            		break;
            	}
        	}
        	
        	for (int i2 = 0; i2 < 2; i2++) {
            	if (tree.leavesBlock == Blocks.LEAVES2.getStateFromMeta(i2)) {
            		tree.saplingBlock = Blocks.SAPLING.getStateFromMeta(i2 + 4);
            		break;
            	}
        	}
        	
	    	this.rtgTrees.add(tree);
    	}
    }
    
    /**
     * Convenience method for addTree() where 'allowed' is assumed to be true.
     * 
     * @param tree
     */
    public void addTree(TreeRTG tree)
    {    	
    	this.addTree(tree, true);
    }

    /**
     * Adds a WorldFeature to the biome
     */
    public void addWorldFeature(WorldFeature feature) {
        this.worldFeatures.add(feature);
        this.config.WORLD_FEATURES.setOptions(ArrayUtils.add(this.config.WORLD_FEATURES.getOptions(), feature.getName()));
        this.config.WORLD_FEATURES.setDefault(ArrayUtils.add(this.config.WORLD_FEATURES.getDefault(), feature.getName()));
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
        return baseBiome.getBiomeName();
    }

    public static RTGBiome forBiome(Biome biome) {
        return RTGBiome.forBiome(BiomeUtils.getId(biome));
    }

    public static RTGBiome forBiome(int id) {
        return RealisticBiomeGenerator.getRealistic(id);
    }

    public List<WorldFeature> getWorldFeatures() {
        return worldFeatures;
    }

    public TerrainBase getTerrain() {
        return this.terrain;
    }

    public Biome getBiome() {
        return baseBiome;
    }

    public int getID() {
        return BiomeUtils.getId(baseBiome);
    }
}
