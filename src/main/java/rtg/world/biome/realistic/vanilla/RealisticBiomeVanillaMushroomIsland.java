package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigRTG;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMushroomIsland;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMushroomIsland;

public class RealisticBiomeVanillaMushroomIsland extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.mushroomIsland.topBlock;
	public static Block fillerBlock = BiomeGenBase.mushroomIsland.fillerBlock;
	
	public RealisticBiomeVanillaMushroomIsland()
	{
		super(
			BiomeGenBase.mushroomIsland,
			BiomeBase.climatizedBiome(BiomeGenBase.mushroomIslandShore, Climate.TEMPERATE),
			new TerrainVanillaMushroomIsland(),
			new SurfaceVanillaMushroomIsland(topBlock, fillerBlock, 67, topBlock, 0f)
		);
		
		this.setRealisticBiomeName("Vanilla Mushroom Island");
		this.biomeCategory = BiomeCategory.WET;
		this.biomeWeight = ConfigRTG.weightVanillaMushroomIsland;
	}	
}
