package rtg.world.biome.realistic.vanilla;

import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaSavannaPlateau;
import rtg.world.gen.terrain.vanilla.TerrainVanillaSavannaPlateau;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaSavannaPlateau extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.savannaPlateau.topBlock;
	public static Block fillerBlock = BiomeGenBase.savannaPlateau.fillerBlock;
	
	public RealisticBiomeVanillaSavannaPlateau()
	{
		super(
			BiomeGenBase.savannaPlateau,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
			new TerrainVanillaSavannaPlateau(0f, 120f, 68f, 200f),
			new SurfaceVanillaSavannaPlateau(topBlock, fillerBlock, 300f, true, true)
		);
	}	
}
