package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigRTG;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.vanilla.SurfaceVanillaRiver;
import rtg.world.gen.terrain.vanilla.TerrainVanillaRiver;

public class RealisticBiomeVanillaRiver extends RealisticBiomeVanillaBase
{	
	public static BiomeGenBase vanillaBiome = BiomeGenBase.river;
	public static Block topBlock = vanillaBiome.topBlock;
	public static Block fillerBlock = vanillaBiome.fillerBlock;
	
	public RealisticBiomeVanillaRiver()
	{
		super(
			vanillaBiome,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
			new TerrainVanillaRiver(),
			new SurfaceVanillaRiver(Blocks.sand, Blocks.sand, Blocks.grass, Blocks.grass, (byte)0, 0)
		);
		
		this.setRealisticBiomeName("Vanilla River");
		BiomeGenManager.addWarmBiome(this, ConfigRTG.weightVanillaRiver);
	}	
}
