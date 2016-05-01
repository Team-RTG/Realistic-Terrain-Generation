package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaRoofedForestM;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoDeadBush;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoGrassDoubleTallgrass;
import rtg.world.biome.deco.DecoMushrooms;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.gen.feature.tree.rtg.TreeRTGRhizophoraMucronata;
import rtg.world.gen.surface.vanilla.SurfaceVanillaRoofedForestM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaRoofedForestM;

public class RealisticBiomeVanillaRoofedForestM extends RealisticBiomeVanillaBase
{
    public static BiomeGenBase standardBiome = BiomeGenBase.roofedForest;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(standardBiome.biomeID + MUTATION_ADDEND);
    
    public static Block topBlock = mutationBiome.topBlock;
    public static Block fillerBlock = mutationBiome.fillerBlock;
    
    public RealisticBiomeVanillaRoofedForestM(BiomeConfig config)
    {
    
        super(config, 
            mutationBiome,
            BiomeGenBase.river,
            new TerrainVanillaRoofedForestM(),
            new SurfaceVanillaRoofedForestM(config, topBlock, fillerBlock)
        );
        this.noLakes=true;
        
		/**
		 * ##################################################
		 * # DECORATIONS (ORDER MATTERS)
		 * ##################################################
		 */
        
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.mossy_cobblestone;
		decoBoulder.chance = 20;
		decoBoulder.maxY = 80;
		decoBoulder.strengthFactor = 2f;
		this.addDeco(decoBoulder);

		DecoTree decoTrees = new DecoTree(new TreeRTGRhizophoraMucronata(3, 4, 13f, 0.32f, 0.1f));
		decoTrees.logBlock = Blocks.log2;
		decoTrees.logMeta = (byte)1;
		decoTrees.leavesBlock = Blocks.leaves2;
		decoTrees.leavesMeta = (byte)1;
		decoTrees.minTrunkSize = 3;
		decoTrees.maxTrunkSize = 4;
		decoTrees.minCrownSize = 7;
		decoTrees.maxCrownSize = 12;
		decoTrees.strengthFactorForLoops = 24f;
		decoTrees.distribution.noiseDivisor = 80f;
		decoTrees.distribution.noiseFactor = 60f;
		decoTrees.distribution.noiseAddend = -15f;
		decoTrees.treeType = TreeType.RTG_TREE;
		decoTrees.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoTrees.treeConditionNoise = 0f;
		decoTrees.treeConditionChance = 1;
		decoTrees.maxY = 120;
		this.addDeco(decoTrees);
        
		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.logCondition = LogCondition.ALWAYS_GENERATE;
		decoFallenTree.logConditionChance = 1;
		decoFallenTree.loops = 4;
		decoFallenTree.maxY = 100;
		decoFallenTree.logBlock = Blocks.log2;
		decoFallenTree.logMeta = (byte)1;
		decoFallenTree.leavesBlock = Blocks.leaves2;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 4;
		decoFallenTree.maxSize = 9;
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaRoofedForestM.decorationLogsId));
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 1f;
		this.addDeco(decoShrub);
        
        DecoGrassDoubleTallgrass decoGrassDoubleTallgrass = new DecoGrassDoubleTallgrass();
        decoGrassDoubleTallgrass.maxY = 128;
        decoGrassDoubleTallgrass.strengthFactor = 8f;
        decoGrassDoubleTallgrass.doubleGrassChance = 6;
        this.addDeco(decoGrassDoubleTallgrass);
        
        DecoDeadBush decoDeadBush = new DecoDeadBush();
		decoDeadBush.maxY = 128;
		decoDeadBush.chance = 16;
		decoDeadBush.strengthFactor = 1f;
        this.addDeco(decoDeadBush);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 4f;
		decoGrass.chance = 2;
        this.addDeco(decoGrass);
        
		DecoGrass decoFern = new DecoGrass();
		decoFern.maxY = 128;
		decoFern.strengthFactor = 4f;
		decoFern.chance = 2;
		decoFern.meta = 2;
        this.addDeco(decoFern);

		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
        
        DecoMushrooms decoMushrooms = new DecoMushrooms();
        decoMushrooms.maxY = 90;
        decoMushrooms.randomType = rtg.world.biome.deco.DecoMushrooms.RandomType.ALWAYS_GENERATE;
        this.addDeco(decoMushrooms);
    }
}
