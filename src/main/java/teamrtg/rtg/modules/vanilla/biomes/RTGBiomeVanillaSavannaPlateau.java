package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.DecoBoulder;
import teamrtg.rtg.api.tools.deco.DecoCactus;
import teamrtg.rtg.api.tools.deco.DecoDoubleGrass;
import teamrtg.rtg.api.tools.deco.DecoGrass;
import teamrtg.rtg.api.tools.deco.DecoShrub;
import teamrtg.rtg.api.tools.deco.DecoTree;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeCondition;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeType;
import teamrtg.rtg.api.tools.deco.collection.DecoCollectionDesertRiver;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTG;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGAcaciaBucheri;
import teamrtg.rtg.api.tools.surface.SurfaceRiverOasis;
import teamrtg.rtg.api.util.math.CanyonColour;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.surface.part.BlockPart;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.DepthSelector;
import teamrtg.rtg.api.world.biome.surface.part.HeightSelector;
import teamrtg.rtg.api.world.biome.surface.part.OrSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaSavannaPlateau extends RTGBiomeVanilla {

    public RTGBiomeVanillaSavannaPlateau() {

        super(
                Biomes.SAVANNA_PLATEAU,
            Biomes.RIVER
        );
        this.noLakes = true;
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            private float[] height = new float[] {12.0f, 0.5f, 8f, 0.7f};
            private int heightLength = height.length;
            private float strength = 10f;

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainPlateau(x, y, rtgWorld.simplex, river, height, biomeWeight, border, strength, heightLength, 50f, true);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        SurfacePart surface = new SurfacePart();
        surface.add(new SurfaceRiverOasis(this));
        surface.add(new DepthSelector(0, 11)
            .add(new OrSelector()
                .or(new CliffSelector(1.3f))
                .or(new DepthSelector(4, 256))
                .add(new BlockPart(CanyonColour.SAVANNA)))
            .add(PARTS.selectTop()
                .add(PARTS.rand(5)
                    .add(new BlockPart(Blocks.GRASS.getDefaultState())))
                .add(PARTS.rand(3)
                    .add(new BlockPart(Blocks.DIRT.getStateFromMeta(1)))))
        );
        surface.add(PARTS.surfaceGeneric());
        surface.add(
            new HeightSelector(50, 256).setMinNoise(PARTS.DEPTH_NOISE)
                .add(new BlockPart(CanyonColour.SAVANNA))
        );
        return surface;
    }

    @Override
    public void initDecos() {
        this.addDecoCollection(new DecoCollectionDesertRiver());
        
		DecoBoulder decoBoulder1 = new DecoBoulder();
		decoBoulder1.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
		decoBoulder1.maxY = 80;
		decoBoulder1.chance = 24;
		this.addDeco(decoBoulder1);
		
		DecoBoulder decoBoulder2 = new DecoBoulder();
		decoBoulder2.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
		decoBoulder1.minY = 110;
		decoBoulder2.chance = 24;
		this.addDeco(decoBoulder2);
        
        DecoShrub acaciaShrub = new DecoShrub();
        acaciaShrub.logBlock = Blocks.LOG2.getDefaultState();
        acaciaShrub.leavesBlock = Blocks.LEAVES2.getDefaultState();
        acaciaShrub.maxY = 160;
        acaciaShrub.strengthFactor = 3f;
        acaciaShrub.chance = 9;
		this.addDeco(acaciaShrub);

		TreeRTG acaciaTree = new TreeRTGAcaciaBucheri();
		acaciaTree.logBlock = Blocks.LOG2.getDefaultState();
		acaciaTree.leavesBlock = Blocks.LEAVES2.getDefaultState();
		acaciaTree.minTrunkSize = 12;
		acaciaTree.maxTrunkSize = 16;
		this.addTree(acaciaTree);
		
		DecoTree acaciaTrees = new DecoTree(acaciaTree);
		acaciaTrees.strengthFactorForLoops = 2f;
		acaciaTrees.treeType = TreeType.RTG_TREE;
		acaciaTrees.treeCondition = TreeCondition.RANDOM_CHANCE;
		acaciaTrees.treeConditionChance = 12;
		acaciaTrees.maxY = 160;
		this.addDeco(acaciaTrees);
        
		DecoCactus decoCactus = new DecoCactus();
		decoCactus.maxY = 160;
		decoCactus.loops = 60;
		decoCactus.chance = 8;
        this.addDeco(decoCactus);
        
		DecoDoubleGrass decoDoubleGrass = new DecoDoubleGrass();
		decoDoubleGrass.maxY = 128;
		decoDoubleGrass.strengthFactor = 3f;
        this.addDeco(decoDoubleGrass);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 10f;
        this.addDeco(decoGrass);
    }

    @Override
    public void initConfig() {

    }
}
