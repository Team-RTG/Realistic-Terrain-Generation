package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigRTG;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaJungleHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaJungleHills;

public class RealisticBiomeVanillaJungleHills extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.jungleHills.topBlock;
	public static Block fillerBlock = BiomeGenBase.jungleHills.fillerBlock;
	
	public RealisticBiomeVanillaJungleHills()
	{
		super(
			BiomeGenBase.jungleHills,
			BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainVanillaJungleHills(),
			new SurfaceVanillaJungleHills(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("Vanilla Jungle Hills");
		this.biomeCategory = BiomeCategory.WET;
		this.biomeWeight = ConfigRTG.weightVanillaJungleHills;
	}	
}
