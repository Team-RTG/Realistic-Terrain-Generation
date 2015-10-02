package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigRTG;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.vanilla.SurfaceVanillaDeepOcean;
import rtg.world.gen.terrain.vanilla.TerrainVanillaDeepOcean;

public class RealisticBiomeVanillaDeepOcean extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.deepOcean.topBlock;
	public static Block fillerBlock = BiomeGenBase.deepOcean.fillerBlock;
	
	public RealisticBiomeVanillaDeepOcean()
	{
		super(
			BiomeGenBase.deepOcean,
			BiomeBase.climatizedBiome(BiomeGenBase.ocean, BiomeBase.Climate.TEMPERATE),
			new TerrainVanillaDeepOcean(),
			new SurfaceVanillaDeepOcean(Blocks.sand, Blocks.sand, (byte)0, 0)
		);
		
		this.setRealisticBiomeName("Vanilla Deep Ocean");
		BiomeGenManager.addCoolBiome(this, ConfigRTG.weightVanillaDeepOcean);
	}	
}
