package rtg.world.biome.realistic.vanilla;

import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaDesertHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaDesertHills;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaDesertHills extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.desertHills.topBlock;
	public static Block fillerBlock = BiomeGenBase.desertHills.fillerBlock;
	
	public RealisticBiomeVanillaDesertHills()
	{
		super(
			BiomeGenBase.desertHills,
			BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
			new TerrainVanillaDesertHills(230f, 120f, 0f),
			new SurfaceVanillaDesertHills(topBlock, fillerBlock, Blocks.sandstone, topBlock, fillerBlock)
		);
		
		this.setRealisticBiomeName("Vanilla Desert Hills");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigVanilla.weightVanillaDesertHills;
	}	
}
