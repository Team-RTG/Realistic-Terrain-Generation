package rtg.world.biome.realistic.vanilla;

import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaDesert;
import rtg.world.gen.terrain.vanilla.TerrainVanillaDesert;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaDesert extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.desert.topBlock;
	public static Block fillerBlock = BiomeGenBase.desert.fillerBlock;
	
	public RealisticBiomeVanillaDesert()
	{
		super(
			BiomeGenBase.desert,
			BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
			new TerrainVanillaDesert(),
			new SurfaceVanillaDesert(topBlock, fillerBlock)
		);
		
		this.setRealisticBiomeName("Vanilla Desert");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigVanilla.weightVanillaDesert;
	}
}
