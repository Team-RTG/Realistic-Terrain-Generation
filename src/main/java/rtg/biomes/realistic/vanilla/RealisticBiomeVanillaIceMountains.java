package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.coast.vanilla.CoastVanillaIceMountains;
import rtg.surface.vanilla.SurfaceVanillaIceMountains;
import rtg.terrain.vanilla.TerrainVanillaIceMountains;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaIceMountains extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.iceMountains.topBlock;
	public static Block fillerBlock = BiomeGenBase.iceMountains.fillerBlock;
	
	public RealisticBiomeVanillaIceMountains()
	{
		super(
			BiomeGenBase.iceMountains,
			VanillaBiomes.vanillaRiverIce,
			new CoastVanillaIceMountains(),
			new TerrainVanillaIceMountains(),
			new SurfaceVanillaIceMountains(topBlock, fillerBlock, Blocks.packed_ice, Blocks.ice)
		);
	}	
}
