package rtg.world.biome.realistic.vanilla;

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
	
	public RealisticBiomeVanillaMushroomIslandShore()
	{
		super(
			BiomeGenBase.mushroomIslandShore,
			BiomeBase.climatizedBiome(BiomeGenBase.deepOcean, Climate.TEMPERATE),
			new TerrainVanillaMushroomIslandShore(),
			new SurfaceVanillaMushroomIslandShore(topBlock, fillerBlock, 67, topBlock, 0f)
		);
		
		this.setRealisticBiomeName("Vanilla Mushroom Island Shore");
		this.biomeCategory = BiomeCategory.WET;
		this.biomeWeight = ConfigVanilla.weightVanillaMushroomIslandShore;
	}	
}
