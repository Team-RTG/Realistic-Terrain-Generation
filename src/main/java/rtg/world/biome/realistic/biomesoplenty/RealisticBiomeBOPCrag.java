package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPCrag;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPCrag;
import biomesoplenty.api.content.BOPCBiomes;
import rtg.world.biome.deco.DecoPond;
import rtg.world.biome.deco.helper.DecoHelperBorder;

public class RealisticBiomeBOPCrag extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.crag;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
		
	public RealisticBiomeBOPCrag(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPCrag(90f),
			new SurfaceBOPCrag(config, topBlock, fillerBlock, topBlock)
		);
		this.generatesEmeralds = true;
        this.noLakes = true;
        this.noWaterFeatures = true;

        DecoPond decoPond = new DecoPond();
        decoPond.chunksPerPond = 3;// very high because most are blocked by topography
        DecoHelperBorder borderedPond = new DecoHelperBorder(decoPond,0.8f,0.7f);
        this.addDeco(borderedPond);

		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
