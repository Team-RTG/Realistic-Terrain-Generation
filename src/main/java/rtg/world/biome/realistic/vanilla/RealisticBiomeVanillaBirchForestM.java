package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaBirchForestM;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoLargeFernDoubleTallgrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGBetulaPapyrifera;
import rtg.world.gen.surface.vanilla.SurfaceVanillaBirchForestM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaBirchForestM;

public class RealisticBiomeVanillaBirchForestM extends RealisticBiomeVanillaBase
{
    
    public static BiomeGenBase standardBiome = BiomeGenBase.birchForest;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(standardBiome.biomeID + MUTATION_ADDEND);
    
    public static Block topBlock = mutationBiome.topBlock;
    public static Block fillerBlock = mutationBiome.fillerBlock;
    
    public RealisticBiomeVanillaBirchForestM(BiomeConfig config)
    {
        
        super(config, 
            mutationBiome,
            BiomeGenBase.river,
            new TerrainVanillaBirchForestM(),
            new SurfaceVanillaBirchForestM(config, topBlock, fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.15f)
        );
        this.noLakes=true;
        
		/**
		 * ##################################################
		 * # DECORATIONS (ORDER MATTERS)
		 * ##################################################
		 */
        
        TreeRTG tallBirch = new TreeRTGBetulaPapyrifera();
		tallBirch.logBlock = Blocks.log;
		tallBirch.logMeta = (byte)2;
		tallBirch.leavesBlock = Blocks.leaves;
		tallBirch.leavesMeta = (byte)2;
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
		decoFallenTree.logBlock = Blocks.log;
		decoFallenTree.logMeta = (byte)2;
		decoFallenTree.leavesBlock = Blocks.leaves;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 3;
		decoFallenTree.maxSize = 6;        
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaBirchForestM.decorationLogsId));
		
		DecoShrub decoShrub = new DecoShrub();
		decoShrub.maxY = 110;
		decoShrub.strengthFactor = 2f;
		this.addDeco(decoShrub);
    }
}
