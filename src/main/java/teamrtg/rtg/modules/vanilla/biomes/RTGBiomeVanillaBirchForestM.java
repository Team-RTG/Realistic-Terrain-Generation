package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import teamrtg.rtg.api.tools.deco.*;
import teamrtg.rtg.api.tools.deco.DecoFallenTree.LogCondition;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeCondition;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeType;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTG;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGBetulaPapyrifera;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaBirchForestM extends RTGBiomeVanilla {

    public static Biome standardBiome = Biomes.BIRCH_FOREST;
    public static Biome mutationBiome = Biome.getBiome(BiomeUtils.getId(standardBiome) + MUTATION_ADDEND);

    public RTGBiomeVanillaBirchForestM() {

        super(
                mutationBiome,
            Biomes.RIVER

        );
        this.noLakes = true;
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainPlains(x, y, rtgWorld.simplex, river, 160f, 10f, 60f, 80f, 65f);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        return SurfaceBase.surfaceForest(this);
    }

    @Override
    public void initDecos() {
        TreeRTG tallBirch = new TreeRTGBetulaPapyrifera();
		tallBirch.logBlock = Blocks.LOG.getStateFromMeta(2);
		tallBirch.leavesBlock = Blocks.LEAVES.getStateFromMeta(2);;
		tallBirch.minTrunkSize = 16;
		tallBirch.maxTrunkSize = 23;
		tallBirch.minCrownSize = 4;
		tallBirch.maxCrownSize = 11;
		this.addTree(tallBirch);
        
		DecoTree superTallBirch = new DecoTree(tallBirch);
		superTallBirch.strengthFactorForLoops = 16f;
		superTallBirch.strengthNoiseFactorForLoops = true;
		superTallBirch.treeType = TreeType.RTG_TREE;
		superTallBirch.distribution.noiseDivisor = 80f;
		superTallBirch.distribution.noiseFactor = 60f;
		superTallBirch.distribution.noiseAddend = -15f;
		superTallBirch.treeCondition = TreeCondition.ALWAYS_GENERATE;
		superTallBirch.maxY = 100;
		this.addDeco(superTallBirch);
        
        DecoLargeFernDoubleTallgrass decoDoublePlants = new DecoLargeFernDoubleTallgrass();
        decoDoublePlants.maxY = 128;
        decoDoublePlants.strengthFactor = 8f;
        this.addDeco(decoDoublePlants);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 24f;
        this.addDeco(decoGrass);
        
		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.logCondition = LogCondition.RANDOM_CHANCE;
		decoFallenTree.logConditionChance = 20;
		decoFallenTree.logBlock = Blocks.LOG.getStateFromMeta(2);
		decoFallenTree.leavesBlock = Blocks.LEAVES.getStateFromMeta(2);
		decoFallenTree.minSize = 3;
		decoFallenTree.maxSize = 6;        
		this.addDeco(decoFallenTree);
		
		DecoShrub decoShrub = new DecoShrub();
		decoShrub.maxY = 110;
		decoShrub.strengthFactor = 2f;
		this.addDeco(decoShrub);
    }

    @Override
    public void initConfig() {

    }
}
