package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.vanilla.VanillaBiomes.Climate;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.surface.vanilla.SurfaceVanillaJungle;
import rtg.terrain.vanilla.TerrainVanillaJungle;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaJungle extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.jungle.topBlock;
	public static Block fillerBlock = BiomeGenBase.jungle.fillerBlock;
	
	public RealisticBiomeVanillaJungle()
	{
		super(
			BiomeGenBase.jungle,
			VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainVanillaJungle(),
			new SurfaceVanillaJungle(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
	}	
}
