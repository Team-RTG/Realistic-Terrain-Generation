package rtg.world.biome.realistic.vanilla;

import rtg.api.biome.BiomeConfig;
import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMushroomIslandShore;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMushroomIslandShore;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaMushroomIslandShore extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.mushroomIslandShore.topBlock;
	public static Block fillerBlock = BiomeGenBase.mushroomIslandShore.fillerBlock;
	
	public RealisticBiomeVanillaMushroomIslandShore(BiomeConfig config)
	{
		super(
			BiomeGenBase.mushroomIslandShore,
			BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainVanillaMushroomIslandShore(),
			new SurfaceVanillaMushroomIslandShore(topBlock, fillerBlock, 67, topBlock, 0f)
		);
		
		this.config = config;
		this.biomeWeight = ConfigVanilla.weightVanillaMushroomIslandShore;
		this.generateVillages = ConfigVanilla.villageVanillaMushroomIslandShore;
	}	
}
