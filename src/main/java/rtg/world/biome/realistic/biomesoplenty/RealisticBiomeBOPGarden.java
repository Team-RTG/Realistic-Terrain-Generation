package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFlowersRTG;
import rtg.world.biome.deco.DecoFlowersRTG.HeightType;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPGarden;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPGarden;
import biomesoplenty.api.content.BOPCBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPGarden extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.garden;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPGarden(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPGarden(65f, 68f, 10f),
			new SurfaceBOPGarden(config, 
                topBlock, //Block top 
                (byte)0, //byte topByte
                fillerBlock, //Block filler, 
                (byte)0, //byte fillerByte
                topBlock, //Block mixTop, 
                (byte)0, //byte mixTopByte, 
                fillerBlock, //Block mixFill, 
                (byte)0, //byte mixFillByte,
                80f, //float mixWidth, 
                -0.15f, //float mixHeight, 
                10f, //float smallWidth, 
                0.5f //float smallStrength
            )
		);
		
		DecoFlowersRTG decoFlowers1 = new DecoFlowersRTG();
		decoFlowers1.flowers = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}; //Only 1-block-tall flowers.
		decoFlowers1.strengthFactor = 2f;
		decoFlowers1.heightType = HeightType.GET_HEIGHT_VALUE;
        this.addDeco(decoFlowers1);
        
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
