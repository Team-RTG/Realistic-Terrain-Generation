package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPGrove;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoFlowersRTG;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrubCustom;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPGrove;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPGrove;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPGrove extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.grove;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPGrove(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPGrove(),
			new SurfaceBOPGrove(config, topBlock, fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.15f)
		);
		
		DecoFallenTree decoFallenTree1 = new DecoFallenTree();
		decoFallenTree1.distribution.noiseDivisor = 80f;
		decoFallenTree1.distribution.noiseFactor = 60f;
		decoFallenTree1.distribution.noiseAddend = -15f;
		decoFallenTree1.logCondition = LogCondition.X_DIVIDED_BY_STRENGTH;
		decoFallenTree1.logConditionNoise = 8f;
		decoFallenTree1.logConditionChance = 1;
		decoFallenTree1.maxY = 100;
		decoFallenTree1.logBlock = Blocks.log;
		decoFallenTree1.logMeta = (byte)2;
		decoFallenTree1.leavesBlock = Blocks.leaves;
		decoFallenTree1.leavesMeta = (byte)-1;
		decoFallenTree1.minSize = 3;
		decoFallenTree1.maxSize = 6;
		
		DecoFallenTree decoFallenTree2 = new DecoFallenTree();
		decoFallenTree2.distribution.noiseDivisor = 80f;
		decoFallenTree2.distribution.noiseFactor = 60f;
		decoFallenTree2.distribution.noiseAddend = -15f;
		decoFallenTree2.logCondition = LogCondition.X_DIVIDED_BY_STRENGTH;
		decoFallenTree2.logConditionNoise = 8f;
		decoFallenTree2.logConditionChance = 1;
		decoFallenTree2.maxY = 100;
		decoFallenTree2.logBlock = Blocks.log2;
		decoFallenTree2.logMeta = (byte)1;
		decoFallenTree2.leavesBlock = Blocks.leaves;
		decoFallenTree2.leavesMeta = (byte)-1;
		decoFallenTree2.minSize = 3;
		decoFallenTree2.maxSize = 6;
		
		DecoHelper5050 decoHelperHelper5050 = new DecoHelper5050(decoFallenTree1, decoFallenTree2);
		this.addDeco(decoHelperHelper5050, this.config._boolean(BiomeConfigBOPGrove.decorationLogsId)); 
        
        DecoShrubCustom decoShrubCustom = new DecoShrubCustom();
        decoShrubCustom.logBlock = Blocks.log;
        decoShrubCustom.logMeta = (byte)2;
        decoShrubCustom.leavesBlock = Blocks.leaves;
        decoShrubCustom.leavesMeta = (byte)2;
        decoShrubCustom.maxY = 110;
        decoShrubCustom.strengthFactor = 2f;
        DecoShrubCustom decoShrubCustom2 = new DecoShrubCustom();
        decoShrubCustom2.logBlock = Blocks.log2;
        decoShrubCustom2.logMeta = (byte)1;
        decoShrubCustom2.leavesBlock = Blocks.leaves2;
        decoShrubCustom2.leavesMeta = (byte)1;
        decoShrubCustom2.maxY = 110;
        decoShrubCustom2.strengthFactor = 2f;
		DecoHelper5050 decoHelperHelper50502 = new DecoHelper5050(decoShrubCustom, decoShrubCustom2);
		this.addDeco(decoHelperHelper50502); 
        
		DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
		decoFlowersRTG.flowers = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		decoFlowersRTG.maxY = 128;
		decoFlowersRTG.strengthFactor = 2f;
        this.addDeco(decoFlowersRTG);        
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 12f;
        this.addDeco(decoGrass);
        
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
