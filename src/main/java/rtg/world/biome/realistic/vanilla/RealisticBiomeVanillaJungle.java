package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigRTG;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.vanilla.SurfaceVanillaJungle;
import rtg.world.gen.terrain.vanilla.TerrainVanillaJungle;

public class RealisticBiomeVanillaJungle extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.jungle.topBlock;
	public static Block fillerBlock = BiomeGenBase.jungle.fillerBlock;
	
	public RealisticBiomeVanillaJungle()
	{
		super(
			BiomeGenBase.jungle,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
			new TerrainVanillaJungle(),
			new SurfaceVanillaJungle(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("Vanilla Jungle");
		BiomeGenManager.addWarmBiome(this, ConfigRTG.weightVanillaJungle);
	}	
}
