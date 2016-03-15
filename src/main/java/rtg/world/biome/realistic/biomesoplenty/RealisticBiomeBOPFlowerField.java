package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
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

        // First, let's get a few shrubs in to break things up a bit.
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 4f;
        decoShrub.chance = 3;
		this.addDeco(decoShrub);
		
		// Flowers are the most aesthetically important feature of this biome, so let's add those next.
		DecoFlowersRTG decoFlowers1 = new DecoFlowersRTG();
		decoFlowers1.flowers = new int[] {0, 1, 2, 3, 8, 9}; //Only colourful 1-block-tall flowers. No tulips as BOP has those covered.
		decoFlowers1.strengthFactor = 12f; // Lots and lots of flowers!
		decoFlowers1.heightType = HeightType.GET_HEIGHT_VALUE; // We're only bothered about surface flowers here.
        this.addDeco(decoFlowers1);
        
		DecoFlowersRTG decoFlowers2 = new DecoFlowersRTG();
		decoFlowers2.flowers = new int[] {10, 11, 14, 15}; //Only 2-block-tall flowers.
		decoFlowers2.strengthFactor = 2f; // Not as many of these.
		decoFlowers2.chance = 3;
		decoFlowers2.heightType = HeightType.GET_HEIGHT_VALUE; // We're only bothered about surface flowers here.
        this.addDeco(decoFlowers2);

        // Not much free space left, so let's give some space to the base biome.
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		decoBaseBiomeDecorations.notEqualsZeroChance = 4;
		this.addDeco(decoBaseBiomeDecorations);
		
		// Grass filler.
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 24f;
        this.addDeco(decoGrass);
	}
}
