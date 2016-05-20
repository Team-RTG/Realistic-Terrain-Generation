package rtg.world.biome.realistic.extrabiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLMiniJungle;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLMiniJungle;
import extrabiomes.api.BiomeManager;

public class RealisticBiomeEBXLMiniJungle extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.minijungle.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLMiniJungle(BiomeConfig config)
	{
		super(config, 
			ebxlBiome, BiomeGenBase.river,
			new TerrainEBXLMiniJungle(),
			new SurfaceEBXLMiniJungle(config, topBlock, fillerBlock)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
