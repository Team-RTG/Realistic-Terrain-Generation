package rtg.world.biome.realistic.vanilla;

import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaSwampland;
import rtg.world.gen.terrain.vanilla.TerrainVanillaSwampland;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaSwampland extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.swampland.topBlock;
	public static Block fillerBlock = BiomeGenBase.swampland.fillerBlock;
	
	public RealisticBiomeVanillaSwampland()
	{
		super(
			BiomeGenBase.swampland,
			BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainVanillaSwampland(),
			new SurfaceVanillaSwampland(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("Vanilla Swampland");
		this.biomeCategory = BiomeCategory.WET;
		this.biomeWeight = ConfigVanilla.weightVanillaSwampland;
	}	
}
