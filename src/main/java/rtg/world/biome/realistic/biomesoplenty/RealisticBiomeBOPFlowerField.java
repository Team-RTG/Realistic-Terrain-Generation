package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFlowersRTG;
import rtg.world.biome.deco.DecoFlowersRTG.HeightType;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPFlowerField;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPFlowerField;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPFlowerField extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.flowerField;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPFlowerField(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPFlowerField(),
			new SurfaceBOPFlowerField(config, topBlock, fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, topBlock, 0.05f)
		);
		
		/**
		 * ##################################################
		 * # DECORATIONS (ORDER MATTERS)
		 * ##################################################
		 */

		DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
		decoFlowersRTG.flowers = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 14, 15}; //Only colourful flowers.
		decoFlowersRTG.strengthFactor = 16f;
		decoFlowersRTG.heightType = HeightType.GET_HEIGHT_VALUE;
        this.addDeco(decoFlowersRTG);
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 3f;
        decoShrub.chance = 12;
		this.addDeco(decoShrub);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 6f;
        this.addDeco(decoGrass);
        
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
