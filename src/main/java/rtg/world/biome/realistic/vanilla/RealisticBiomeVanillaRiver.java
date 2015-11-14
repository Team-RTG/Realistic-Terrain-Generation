package rtg.world.biome.realistic.vanilla;

import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaRiver;
import rtg.world.gen.terrain.vanilla.TerrainVanillaRiver;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaRiver extends RealisticBiomeVanillaBase
{	
	public static BiomeGenBase vanillaBiome = BiomeGenBase.river;
	public static Block topBlock = vanillaBiome.topBlock;
	public static Block fillerBlock = vanillaBiome.fillerBlock;
	
	public RealisticBiomeVanillaRiver()
	{
		super(
			vanillaBiome,
			BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainVanillaRiver(),
			new SurfaceVanillaRiver(Blocks.sand, Blocks.sand, Blocks.grass, Blocks.grass, (byte)0, 0)
		);
		
		this.setRealisticBiomeName("Vanilla River");
		this.biomeWeight = ConfigVanilla.weightVanillaRiver;
        this.waterLakeFrequency = 0;
        this.lavaLakeFrequency = 0;
	}	
}
