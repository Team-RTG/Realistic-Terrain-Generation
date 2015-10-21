package rtg.world.biome.realistic.vanilla;

import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaJungleEdge;
import rtg.world.gen.terrain.vanilla.TerrainVanillaJungleEdge;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaJungleEdge extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.jungleEdge.topBlock;
	public static Block fillerBlock = BiomeGenBase.jungleEdge.fillerBlock;
	
	public RealisticBiomeVanillaJungleEdge()
	{
		super(
			BiomeGenBase.jungleEdge,
			BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainVanillaJungleEdge(),
			new SurfaceVanillaJungleEdge(topBlock, fillerBlock, false, null, 1.3f)
		);
		
		this.setRealisticBiomeName("Vanilla Jungle Edge");
		this.biomeCategory = BiomeCategory.WET;
		this.biomeWeight = ConfigVanilla.weightVanillaJungleEdge;
	}	
}
