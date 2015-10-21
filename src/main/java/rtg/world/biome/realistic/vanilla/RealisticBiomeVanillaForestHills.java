package rtg.world.biome.realistic.vanilla;

import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaForestHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaForestHills;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaForestHills extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.forestHills.topBlock;
	public static Block fillerBlock = BiomeGenBase.forestHills.fillerBlock;
	
	public RealisticBiomeVanillaForestHills()
	{
		super(
			BiomeGenBase.forestHills,
			BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainVanillaForestHills(70f, 180f, 7f, 100f, 38f, 260f, 68f),
			new SurfaceVanillaForestHills(topBlock, fillerBlock, topBlock, topBlock)
		);
		
		this.setRealisticBiomeName("Vanilla Forest Hills");
		this.biomeCategory = BiomeCategory.WET;
		this.biomeWeight = ConfigVanilla.weightVanillaForestHills;
	}	
}
