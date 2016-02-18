package rtg.world.biome.realistic.vanilla;

import rtg.api.biome.BiomeConfig;
import rtg.config.vanilla.ConfigVanilla;
import rtg.world.gen.surface.vanilla.SurfaceVanillaFrozenRiver;
import rtg.world.gen.terrain.vanilla.TerrainVanillaFrozenRiver;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaFrozenRiver extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.frozenRiver.topBlock.getBlock();
	public static Block fillerBlock = BiomeGenBase.frozenRiver.fillerBlock.getBlock();
	
	public RealisticBiomeVanillaFrozenRiver(BiomeConfig config)
	{
		super(
			BiomeGenBase.frozenRiver,
			BiomeGenBase.frozenRiver,
			new TerrainVanillaFrozenRiver(),
			new SurfaceVanillaFrozenRiver()
		);
		
		this.config = config;
		
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
	}	
}
