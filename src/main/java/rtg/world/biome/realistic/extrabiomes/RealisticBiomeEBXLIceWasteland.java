package rtg.world.biome.realistic.extrabiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLIceWasteland;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLIceWasteland;
import extrabiomes.api.BiomeManager;

public class RealisticBiomeEBXLIceWasteland extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.icewasteland.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLIceWasteland(BiomeConfig config)
	{
		super(config, 
			ebxlBiome, BiomeGenBase.frozenRiver,
			new TerrainEBXLIceWasteland(),
			new SurfaceEBXLIceWasteland(config, topBlock, fillerBlock)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
