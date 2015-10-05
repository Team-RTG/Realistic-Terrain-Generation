package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigRTG;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMegaTaigaHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMegaTaigaHills;

public class RealisticBiomeVanillaMegaTaigaHills extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.megaTaigaHills.topBlock;
	public static Block fillerBlock = BiomeGenBase.megaTaigaHills.fillerBlock;
	
	public RealisticBiomeVanillaMegaTaigaHills()
	{
		super(
			BiomeGenBase.megaTaigaHills,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainVanillaMegaTaigaHills(230f, 120f, 90f),
			new SurfaceVanillaMegaTaigaHills(topBlock, fillerBlock, true, Blocks.sand, 0.2f)
		);
		
		this.setRealisticBiomeName("Vanilla Mega Taiga Hills");
		this.biomeCategory = BiomeCategory.COLD;
		this.biomeWeight = ConfigRTG.weightVanillaMegaTaigaHills;
	}	
}
