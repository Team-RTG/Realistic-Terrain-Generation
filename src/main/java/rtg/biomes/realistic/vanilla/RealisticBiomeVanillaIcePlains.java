package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.surface.vanilla.SurfaceVanillaIcePlains;
import rtg.terrain.vanilla.TerrainVanillaIcePlains;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaIcePlains extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.icePlains.topBlock;
	public static Block fillerBlock = BiomeGenBase.icePlains.fillerBlock;
	
	public RealisticBiomeVanillaIcePlains()
	{
		super(
			BiomeGenBase.icePlains,
			VanillaBiomes.vanillaRiverIce,
			new TerrainVanillaIcePlains(),
			new SurfaceVanillaIcePlains(topBlock, fillerBlock, topBlock, topBlock)
		);
	}	
}
