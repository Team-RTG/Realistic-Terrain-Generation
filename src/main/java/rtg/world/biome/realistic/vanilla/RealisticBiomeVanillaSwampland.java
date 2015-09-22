package rtg.world.biome.realistic.vanilla;

import rtg.surface.vanilla.SurfaceVanillaSwampland;
import rtg.terrain.vanilla.TerrainVanillaSwampland;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaSwampland extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.swampland.topBlock;
	public static Block fillerBlock = BiomeGenBase.swampland.fillerBlock;
	
	public RealisticBiomeVanillaSwampland()
	{
		super(
			BiomeGenBase.swampland,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
			new TerrainVanillaSwampland(),
			new SurfaceVanillaSwampland(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
	}	
}
