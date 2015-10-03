package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigRTG;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.vanilla.SurfaceVanillaOcean;
import rtg.world.gen.terrain.vanilla.TerrainVanillaOcean;

public class RealisticBiomeVanillaOcean extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.ocean.topBlock;
	public static Block fillerBlock = BiomeGenBase.ocean.fillerBlock;
	
	public RealisticBiomeVanillaOcean()
	{
		super(
			BiomeGenBase.ocean,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
			new TerrainVanillaOcean(),
			new SurfaceVanillaOcean(Blocks.sand, Blocks.sand, (byte)0, 0)
		);
		
		this.setRealisticBiomeName("Vanilla Ocean");
		BiomeGenManager.addCoolBiome(this, ConfigRTG.weightVanillaOcean);
	}
}
