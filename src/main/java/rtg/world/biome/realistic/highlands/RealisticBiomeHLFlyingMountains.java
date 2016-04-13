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
        
		DecoTree bigPines = new DecoTree();
		bigPines.strengthFactorForLoops = 3f;
		bigPines.treeType = TreeType.BIG_PINES;
		bigPines.distribution.noiseDivisor = 80f;
		bigPines.distribution.noiseFactor = 60f;
		bigPines.distribution.noiseAddend = -15f;
		bigPines.treeCondition = TreeCondition.ALWAYS_GENERATE;
		bigPines.minY = 90;
		bigPines.maxY = 120;
        DecoShrub decoShrub2 = new DecoShrub();
        decoShrub2.minY = 90;
        decoShrub2.maxY = 120;
        decoShrub2.strengthFactor = 3f;
        decoShrub2.minSize = 11;
        decoShrub2.maxSize = 21;
        DecoHelper5050 decoHelper5050 = new DecoHelper5050(bigPines, decoShrub2);
		this.addDeco(decoHelper5050);
        
        DecoFallenTree decoFallenTreeOak = new DecoFallenTree();
        decoFallenTreeOak.distribution.noiseDivisor = 80f;
        decoFallenTreeOak.distribution.noiseFactor = 60f;
        decoFallenTreeOak.distribution.noiseAddend = -15f;
        decoFallenTreeOak.logCondition = LogCondition.X_DIVIDED_BY_STRENGTH;
        decoFallenTreeOak.logConditionNoise = 8f;
        decoFallenTreeOak.maxY = 100;
        decoFallenTreeOak.logBlock = Blocks.log;
        decoFallenTreeOak.logMeta = (byte)0;
        decoFallenTreeOak.leavesBlock = Blocks.leaves;
        decoFallenTreeOak.leavesMeta = (byte)-1;
        decoFallenTreeOak.minSize = 3;
        decoFallenTreeOak.maxSize = 6;
        
        DecoFallenTree decoFallenTreeSpruce = new DecoFallenTree();
        decoFallenTreeSpruce.distribution.noiseDivisor = 80f;
        decoFallenTreeSpruce.distribution.noiseFactor = 60f;
        decoFallenTreeSpruce.distribution.noiseAddend = -15f;
        decoFallenTreeSpruce.logCondition = LogCondition.X_DIVIDED_BY_STRENGTH;
        decoFallenTreeSpruce.logConditionNoise = 8f;
        decoFallenTreeSpruce.maxY = 100;
        decoFallenTreeSpruce.logBlock = Blocks.log;
        decoFallenTreeSpruce.logMeta = (byte)0;
        decoFallenTreeSpruce.leavesBlock = Blocks.leaves;
        decoFallenTreeSpruce.leavesMeta = (byte)-1;
        decoFallenTreeSpruce.minSize = 3;
        decoFallenTreeSpruce.maxSize = 6;
        
        DecoHelper5050 DecoHelper5050 = new DecoHelper5050(decoFallenTreeOak, decoFallenTreeSpruce);
		this.addDeco(decoFallenTreeOak, this.config._boolean(BiomeConfigHLFlyingMountains.decorationLogsId));
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 3f;
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
