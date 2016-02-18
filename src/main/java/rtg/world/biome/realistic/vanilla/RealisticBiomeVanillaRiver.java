package rtg.world.biome.realistic.vanilla;

import rtg.api.biome.BiomeConfig;
import rtg.config.vanilla.ConfigVanilla;
import rtg.world.gen.surface.vanilla.SurfaceVanillaRiver;
import rtg.world.gen.terrain.vanilla.TerrainVanillaRiver;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaRiver extends RealisticBiomeVanillaBase
{	
	public static BiomeGenBase vanillaBiome = BiomeGenBase.river;
	public static Block topBlock = vanillaBiome.topBlock.getBlock();
	public static Block fillerBlock = vanillaBiome.fillerBlock.getBlock();
	
	public RealisticBiomeVanillaRiver(BiomeConfig config)
	{
		super(
			vanillaBiome,
			BiomeGenBase.river,
			new TerrainVanillaRiver(),
			new SurfaceVanillaRiver()
		);
		
		this.config = config;
		
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
	}	
}
