package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaColdTaigaHills;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoPumpkin;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.gen.surface.vanilla.SurfaceVanillaColdTaigaHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaColdTaigaHills;

public class RealisticBiomeVanillaColdTaigaHills extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.coldTaigaHills.topBlock;
    public static Block fillerBlock = BiomeGenBase.coldTaigaHills.fillerBlock;
    
    public RealisticBiomeVanillaColdTaigaHills(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.coldTaigaHills,
            BiomeGenBase.frozenRiver,
            new TerrainVanillaColdTaigaHills(),
            new SurfaceVanillaColdTaigaHills(config, Blocks.grass, Blocks.dirt, true, Blocks.sand, 0.2f)
        );
        this.noLakes=true;
        
		/**
		 * ##################################################
		 * # DECORATIONS (ORDER MATTERS)
		 * ##################################################
		 */
        
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.mossy_cobblestone;
		decoBoulder.chance = 16;
		decoBoulder.maxY = 95;
		decoBoulder.strengthFactor = 3f;
		this.addDeco(decoBoulder);
        
		DecoTree decoTrees = new DecoTree();
		decoTrees.strengthFactorForLoops = 4f;
		decoTrees.strengthNoiseFactorForLoops = true;
		decoTrees.distribution.noiseDivisor = 100f;
		decoTrees.distribution.noiseFactor = 12f;
		decoTrees.distribution.noiseAddend = 4f;
		decoTrees.treeType = TreeType.VANILLA_COLD_TAIGA;
		decoTrees.treeCondition = TreeCondition.ALWAYS_GENERATE;
		decoTrees.maxY = 110;
		this.addDeco(decoTrees);
        
		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.distribution.noiseDivisor = 100f;
		decoFallenTree.distribution.noiseFactor = 12f;
		decoFallenTree.distribution.noiseAddend = 4f;
		decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoFallenTree.logConditionNoise = 0f;
		decoFallenTree.logConditionChance = 6;
		decoFallenTree.maxY = 100;
		decoFallenTree.logBlock = Blocks.log;
		decoFallenTree.logMeta = (byte)1;
		decoFallenTree.leavesBlock = Blocks.leaves;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 3;
		decoFallenTree.maxSize = 6;
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaColdTaigaHills.decorationLogsId));
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 100;
        decoShrub.strengthFactor = 2f;
		this.addDeco(decoShrub);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		decoBaseBiomeDecorations.maxY = 110;
		decoBaseBiomeDecorations.equalsZeroChance = 3;
		this.addDeco(decoBaseBiomeDecorations);
        
		DecoPumpkin decoPumpkin = new DecoPumpkin();
		decoPumpkin.maxY = 90;
		decoPumpkin.randomType = rtg.world.biome.deco.DecoPumpkin.RandomType.X_DIVIDED_BY_STRENGTH;
		decoPumpkin.randomFloat = 20f;
        this.addDeco(decoPumpkin);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 10f;
        this.addDeco(decoGrass);
    }
}
