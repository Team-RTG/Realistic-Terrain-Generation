package rtg.world.biome.realistic.vanilla;


import rtg.surface.vanilla.SurfaceVanillaMushroomIsland;
import rtg.terrain.vanilla.TerrainVanillaMushroomIsland;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaMushroomIsland extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.mushroomIsland.topBlock;
	public static Block fillerBlock = BiomeGenBase.mushroomIsland.fillerBlock;
	
	public RealisticBiomeVanillaMushroomIsland()
	{
		super(
			BiomeGenBase.mushroomIsland,
			BiomeBase.climatizedBiome(BiomeGenBase.ocean, BiomeBase.Climate.COLD),
			new TerrainVanillaMushroomIsland(),
			new SurfaceVanillaMushroomIsland(topBlock, fillerBlock, 67, topBlock, 0f)
		);
	}	
}
