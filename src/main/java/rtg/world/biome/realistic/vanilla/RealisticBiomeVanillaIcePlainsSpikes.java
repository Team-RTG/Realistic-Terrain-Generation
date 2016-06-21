package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaIcePlainsSpikes;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGCupressusSempervirens;
import rtg.world.gen.feature.tree.rtg.TreeRTGPiceaSitchensis;
import rtg.world.gen.feature.tree.rtg.TreeRTGPinusPonderosa;
import rtg.world.gen.surface.vanilla.SurfaceVanillaIcePlainsSpikes;
import rtg.world.gen.terrain.vanilla.TerrainVanillaIcePlainsSpikes;

public class RealisticBiomeVanillaIcePlainsSpikes extends RealisticBiomeVanillaBase
{	
    public static BiomeGenBase standardBiome = BiomeGenBase.icePlains;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(standardBiome.biomeID + MUTATION_ADDEND);
    
    public static Block topBlock = mutationBiome.topBlock;
    public static Block fillerBlock = mutationBiome.fillerBlock;
	
	public RealisticBiomeVanillaIcePlainsSpikes(BiomeConfig config)
	{
		super(config, 
		    mutationBiome,
			BiomeGenBase.frozenRiver,
			new TerrainVanillaIcePlainsSpikes(),
			new SurfaceVanillaIcePlainsSpikes(config, topBlock, fillerBlock, topBlock, topBlock)
		);
        this.noLakes=true;
		
		TreeRTG ponderosaOakTree = new TreeRTGPinusPonderosa();
		ponderosaOakTree.logBlock = Blocks.packed_ice;
		ponderosaOakTree.logMeta = (byte)0;
		ponderosaOakTree.leavesBlock = Blocks.ice;
		ponderosaOakTree.leavesMeta = (byte)0;
		ponderosaOakTree.minTrunkSize = 11;
		ponderosaOakTree.maxTrunkSize = 21;
		ponderosaOakTree.minCrownSize = 15;
		ponderosaOakTree.maxCrownSize = 29;
		ponderosaOakTree.validGroundBlocks.clear();
		ponderosaOakTree.validGroundBlocks.add(Blocks.snow);
		ponderosaOakTree.validGroundBlocks.add(Blocks.snow_layer);
		
		DecoTree oakPines = new DecoTree(ponderosaOakTree);
		oakPines.strengthFactorForLoops = 6f;
		oakPines.treeType = TreeType.RTG_TREE;
		oakPines.distribution.noiseDivisor = 100f;
		oakPines.distribution.noiseFactor = 6f;
		oakPines.distribution.noiseAddend = 0.8f;
		oakPines.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		oakPines.treeConditionNoise = 0f;
		oakPines.treeConditionChance = 1;
		oakPines.maxY = 85;
		this.addDeco(oakPines, this.config._boolean(BiomeConfigVanillaIcePlainsSpikes.decorationIceTreesId));
		
		TreeRTG sempervirensOakTree = new TreeRTGCupressusSempervirens();
		sempervirensOakTree.logBlock = Blocks.packed_ice;
		sempervirensOakTree.logMeta = (byte)0;
		sempervirensOakTree.leavesBlock = Blocks.ice;
		sempervirensOakTree.leavesMeta = (byte)0;
		sempervirensOakTree.minTrunkSize = 3;
		sempervirensOakTree.maxTrunkSize = 4;
		sempervirensOakTree.minCrownSize = 6;
		sempervirensOakTree.maxCrownSize = 14;
		sempervirensOakTree.validGroundBlocks.clear();
		sempervirensOakTree.validGroundBlocks.add(Blocks.snow);
		sempervirensOakTree.validGroundBlocks.add(Blocks.snow_layer);
		
		DecoTree decoSempervirensTree = new DecoTree(sempervirensOakTree);
		decoSempervirensTree.treeType = DecoTree.TreeType.RTG_TREE;
		decoSempervirensTree.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoSempervirensTree.distribution = new DecoTree.Distribution(80f, 60f, -15f);
		decoSempervirensTree.treeConditionNoise = -0.1f;
		decoSempervirensTree.treeConditionChance = 1;
		decoSempervirensTree.loops = 1;
		this.addDeco(decoSempervirensTree, this.config._boolean(BiomeConfigVanillaIcePlainsSpikes.decorationIceTreesId));
		
		TreeRTG sitchensisTree = new TreeRTGPiceaSitchensis();
    	sitchensisTree.logBlock = Blocks.packed_ice;
    	sitchensisTree.logMeta = (byte)0;
    	sitchensisTree.leavesBlock = Blocks.ice;
    	sitchensisTree.leavesMeta = (byte)0;
    	sitchensisTree.minTrunkSize = 4;
    	sitchensisTree.maxTrunkSize = 10;
    	sitchensisTree.minCrownSize = 6;
    	sitchensisTree.maxCrownSize = 14;
		
		DecoTree oakPine = new DecoTree(sitchensisTree);
		oakPine.strengthFactorForLoops = 3f;
    	oakPine.treeType = TreeType.RTG_TREE;
    	oakPine.distribution = new DecoTree.Distribution(80f, 60f, -15f);
		oakPine.treeCondition = TreeCondition.RANDOM_CHANCE;
		oakPine.treeConditionChance = 2;
		oakPine.maxY = 100;
		this.addDeco(oakPine, this.config._boolean(BiomeConfigVanillaIcePlainsSpikes.decorationIceTreesId));
        
        DecoShrub decoShrubOak = new DecoShrub();
        decoShrubOak.maxY = 140;
        decoShrubOak.logBlock = Blocks.packed_ice;
        decoShrubOak.logMeta = (byte)0;
        decoShrubOak.leavesBlock = Blocks.ice;
        decoShrubOak.leavesMeta = (byte)0;
        decoShrubOak.strengthFactor = 4f;
        decoShrubOak.chance = 4;
		this.addDeco(decoShrubOak, this.config._boolean(BiomeConfigVanillaIcePlainsSpikes.decorationIceShrubsId));
        
		int iceSpikeChance = this.config._int(BiomeConfigVanillaIcePlainsSpikes.iceSpikeChanceId);
		if (iceSpikeChance > 0) {
			
			DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
			decoBaseBiomeDecorations.equalsZeroChance = iceSpikeChance;
			this.addDeco(decoBaseBiomeDecorations);
		}
	}
}
