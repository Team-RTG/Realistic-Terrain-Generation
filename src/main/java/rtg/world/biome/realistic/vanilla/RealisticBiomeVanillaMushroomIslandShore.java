package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigRTG;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMushroomIslandShore;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMushroomIslandShore;

public class RealisticBiomeVanillaMushroomIslandShore extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.mushroomIslandShore.topBlock;
	public static Block fillerBlock = BiomeGenBase.mushroomIslandShore.fillerBlock;
	
	public RealisticBiomeVanillaMushroomIslandShore()
	{
		super(
			BiomeGenBase.mushroomIslandShore,
			BiomeBase.climatizedBiome(BiomeGenBase.deepOcean, BiomeBase.Climate.TEMPERATE),
			new TerrainVanillaMushroomIslandShore(),
			new SurfaceVanillaMushroomIslandShore(topBlock, fillerBlock, 67, topBlock, 0f)
		);
		
		this.setRealisticBiomeName("Vanilla Muchroom Island Shore");
		BiomeGenManager.addWarmBiome(this, ConfigRTG.weightVanillaMushroomIslandShore);
	}	
}
