package rtg.world.biome.realistic.vanilla;

import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaTaigaHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaTaigaHills;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaTaigaHills extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.taigaHills.topBlock;
	public static Block fillerBlock = BiomeGenBase.taigaHills.fillerBlock;
	
	public RealisticBiomeVanillaTaigaHills()
	{
		super(
			BiomeGenBase.taigaHills,
			BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainVanillaTaigaHills(),
			new SurfaceVanillaTaigaHills(topBlock, fillerBlock, true, Blocks.sand, 0.2f)
		);
		
		this.setRealisticBiomeName("Vanilla Taiga Hills");
		this.biomeCategory = BiomeCategory.COLD;
		this.biomeWeight = ConfigVanilla.weightVanillaTaigaHills;
	}	
}
