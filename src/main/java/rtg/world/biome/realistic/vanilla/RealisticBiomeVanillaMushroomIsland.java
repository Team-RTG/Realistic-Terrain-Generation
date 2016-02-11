package rtg.world.biome.realistic.vanilla;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMushroomIsland;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMushroomIsland;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaMushroomIsland extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.mushroomIsland.topBlock;
	public static Block fillerBlock = BiomeGenBase.mushroomIsland.fillerBlock;
	
	public RealisticBiomeVanillaMushroomIsland(BiomeConfig config)
	{
		super(
			BiomeGenBase.mushroomIsland,
			BiomeGenBase.river,
			new TerrainVanillaMushroomIsland(),
			new SurfaceVanillaMushroomIsland(config, topBlock, fillerBlock, 67, topBlock, 0f)
		);
		
		this.config = config;
	}	
}
