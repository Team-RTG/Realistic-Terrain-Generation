package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.highlands.config.BiomeConfigHLFlyingMountains;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoFlowersRTG;
import rtg.world.biome.deco.DecoFlowersRTG.HeightType;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.gen.surface.highlands.SurfaceHLFlyingMountains;
import rtg.world.gen.terrain.highlands.TerrainHLFlyingMountains;

public class RealisticBiomeHLFlyingMountains extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.flyingMountains;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLFlyingMountains(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLFlyingMountains(350f, 100f, 63f,70f),
            //new SurfaceVanillaForest(config, Blocks.grass, Blocks.dirt, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.10f));
            new SurfaceHLFlyingMountains(config, topBlock, fillerBlock, false, null, 0f, 2.5f, 80f, 65f, 2.5f)
        );
        {
		DecoTree bigPines = new DecoTree();
		bigPines.strengthFactorForLoops = 3f;
		bigPines.treeType = TreeType.TAIGA_SPRUCE_TALL;
		bigPines.distribution.noiseDivisor = 80f;
		bigPines.distribution.noiseFactor = 60f;
		bigPines.distribution.noiseAddend = -15f;
		bigPines.treeCondition = TreeCondition.ALWAYS_GENERATE;
		bigPines.minY = 63;
		bigPines.maxY = 90;
        DecoShrub decoShrub2 = new DecoShrub();
        decoShrub2.minY = 90;
        decoShrub2.maxY = 150;
        decoShrub2.strengthFactor = 6f;
        decoShrub2.minSize = 11;
        decoShrub2.maxSize = 21;
        DecoHelper5050 decoHelper5050 = new DecoHelper5050(bigPines, decoShrub2);
		this.addDeco(decoHelper5050);
        }

        DecoTree secondaryTree = new DecoTree();
		secondaryTree.strengthFactorForLoops = 3f;
		secondaryTree.treeType = TreeType.BIRCH_TREES_FOREST;
		secondaryTree.distribution.noiseDivisor = 80f;
		secondaryTree.distribution.noiseFactor = 60f;
		secondaryTree.distribution.noiseAddend = -15f;
		secondaryTree.treeCondition = TreeCondition.ALWAYS_GENERATE;
		secondaryTree.minY = 63;
		secondaryTree.maxY = 90;
        this.addDeco(secondaryTree);
        
        DecoFallenTree decoFallenTreeBirch = new DecoFallenTree();
        decoFallenTreeBirch.distribution.noiseDivisor = 80f;
        decoFallenTreeBirch.distribution.noiseFactor = 60f;
        decoFallenTreeBirch.distribution.noiseAddend = -15f;
        decoFallenTreeBirch.logCondition = LogCondition.X_DIVIDED_BY_STRENGTH;
        decoFallenTreeBirch.logConditionNoise = 8f;
        decoFallenTreeBirch.maxY = 100;
        decoFallenTreeBirch.logBlock = Blocks.log;
        decoFallenTreeBirch.logMeta = (byte)2;
        decoFallenTreeBirch.leavesBlock = Blocks.leaves;
        decoFallenTreeBirch.leavesMeta = (byte)-1;
        decoFallenTreeBirch.minSize = 3;
        decoFallenTreeBirch.maxSize = 6;
        
        DecoFallenTree decoFallenTreeSpruce = new DecoFallenTree();
        decoFallenTreeSpruce.distribution.noiseDivisor = 80f;
        decoFallenTreeSpruce.distribution.noiseFactor = 60f;
        decoFallenTreeSpruce.distribution.noiseAddend = -15f;
        decoFallenTreeSpruce.logCondition = LogCondition.X_DIVIDED_BY_STRENGTH;
        decoFallenTreeSpruce.logConditionNoise = 8f;
        decoFallenTreeSpruce.maxY = 100;
        decoFallenTreeSpruce.logBlock = Blocks.log;
        decoFallenTreeSpruce.logMeta = (byte)1;
        decoFallenTreeSpruce.leavesBlock = Blocks.leaves;
        decoFallenTreeSpruce.leavesMeta = (byte)-1;
        decoFallenTreeSpruce.minSize = 3;
        decoFallenTreeSpruce.maxSize = 6;
        
        DecoHelper5050 DecoHelper5050 = new DecoHelper5050(decoFallenTreeBirch, decoFallenTreeSpruce);
		this.addDeco(DecoHelper5050, this.config._boolean(BiomeConfigHLFlyingMountains.decorationLogsId));
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 180;
        decoShrub.strengthFactor = 8f;
		this.addDeco(decoShrub);
        
		DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
		decoFlowersRTG.flowers = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		decoFlowersRTG.maxY = 128;
		decoFlowersRTG.heightType = HeightType.NEXT_INT;
		decoFlowersRTG.strengthFactor = 8f;
        this.addDeco(decoFlowersRTG);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 12f;
        this.addDeco(decoGrass);
    }
}
