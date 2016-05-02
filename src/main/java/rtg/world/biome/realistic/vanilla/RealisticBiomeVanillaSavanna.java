package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaSavanna;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoDoubleGrass;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.deco.collection.DecoCollectionDesertRiver;
import rtg.world.gen.feature.tree.rtg.TreeRTGAcaciaBucheri;
import rtg.world.gen.surface.vanilla.SurfaceVanillaSavanna;
import rtg.world.gen.terrain.vanilla.TerrainVanillaSavanna;

public class RealisticBiomeVanillaSavanna extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.savanna.topBlock;
    public static Block fillerBlock = BiomeGenBase.savanna.fillerBlock;
    
    public RealisticBiomeVanillaSavanna(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.savanna,
            BiomeGenBase.river,
            new TerrainVanillaSavanna(),
            new SurfaceVanillaSavanna(config, topBlock, fillerBlock, topBlock, 13f, 0.27f)
        );
        
		/**
		 * ##################################################
		 * # DECORATIONS (ORDER MATTERS)
		 * ##################################################
		 */

        this.addDecoCollection(new DecoCollectionDesertRiver());
        
		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.loops = 1;
		decoFallenTree.distribution.noiseDivisor = 100f;
		decoFallenTree.distribution.noiseFactor = 6f;
		decoFallenTree.distribution.noiseAddend = 0.8f;
		decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoFallenTree.logConditionNoise = 0f;
		decoFallenTree.logConditionChance = 24;
		decoFallenTree.maxY = 120;
		decoFallenTree.logBlock = Blocks.log2;
		decoFallenTree.logMeta = (byte)0;
		decoFallenTree.leavesBlock = Blocks.leaves2;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 3;
		decoFallenTree.maxSize = 6;
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaSavanna.decorationLogsId));
        
        DecoShrub acaciaShrub = new DecoShrub();
        acaciaShrub.logBlock = Blocks.log2;
        acaciaShrub.logMeta = (byte)0;
        acaciaShrub.leavesBlock = Blocks.leaves2;
        acaciaShrub.leavesMeta = (byte)0;
        acaciaShrub.maxY = 160;
        acaciaShrub.strengthFactor = 3f;
        acaciaShrub.chance = 9;
		this.addDeco(acaciaShrub);
		
		DecoTree acaciaTrees = new DecoTree(new TreeRTGAcaciaBucheri());
		acaciaTrees.logBlock = Blocks.log2;
		acaciaTrees.logMeta = (byte)0;
		acaciaTrees.leavesBlock = Blocks.leaves2;
		acaciaTrees.leavesMeta = (byte)0;
		acaciaTrees.minTrunkSize = 12;
		acaciaTrees.maxTrunkSize = 16;
		acaciaTrees.strengthFactorForLoops = 2f;
		acaciaTrees.treeType = TreeType.RTG_TREE;
		acaciaTrees.distribution.noiseDivisor = 180f;
		acaciaTrees.distribution.noiseFactor = 1f;
		acaciaTrees.distribution.noiseAddend = 0f;
		acaciaTrees.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		acaciaTrees.treeConditionNoise = 0.20f;
		this.addDeco(acaciaTrees);
		
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.cobblestone;
		decoBoulder.chance = 24;
		decoBoulder.maxY = 95;
		this.addDeco(decoBoulder);
        
		DecoDoubleGrass decoDoubleGrass = new DecoDoubleGrass();
		decoDoubleGrass.maxY = 128;
		decoDoubleGrass.strengthFactor = 3f;
        this.addDeco(decoDoubleGrass);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 10f;
        this.addDeco(decoGrass);
    }
}
