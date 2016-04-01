package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.vanilla.SurfaceVanillaRiver;
import rtg.world.gen.terrain.vanilla.TerrainVanillaRiver;

public class RealisticBiomeVanillaRiver extends RealisticBiomeVanillaBase
{
	public static BiomeGenBase vanillaBiome = BiomeGenBase.river;
	public static Block topBlock = vanillaBiome.topBlock;
	public static Block fillerBlock = vanillaBiome.fillerBlock;

	public RealisticBiomeVanillaRiver(BiomeConfig config)
	{
		super(config, 
			vanillaBiome,
			BiomeGenBase.river,
			new TerrainVanillaRiver(),
			new SurfaceVanillaRiver(config)
		);

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
	}
}
