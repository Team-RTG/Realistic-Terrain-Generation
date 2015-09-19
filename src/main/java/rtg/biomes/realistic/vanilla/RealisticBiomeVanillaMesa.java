package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.vanilla.VanillaBiomes.Climate;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.surface.vanilla.SurfaceVanillaMesa;
import rtg.terrain.vanilla.TerrainVanillaMesa;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaMesa extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.mesa.topBlock;
	public static Block fillerBlock = BiomeGenBase.mesa.fillerBlock;
	
	public RealisticBiomeVanillaMesa()
	{
		super(
			BiomeGenBase.mesa,
			VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
			new TerrainVanillaMesa(),
			//new SurfaceVanillaMesa(Blocks.sand, Blocks.sand, (byte)1)
			new SurfaceVanillaMesa(topBlock, fillerBlock, (byte)1)
		);
	}	
}
