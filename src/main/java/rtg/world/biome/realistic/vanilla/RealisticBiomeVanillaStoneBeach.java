package rtg.world.biome.realistic.vanilla;

import rtg.api.biome.BiomeConfig;
import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaStoneBeach;
import rtg.world.gen.terrain.vanilla.TerrainVanillaStoneBeach;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaStoneBeach extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.stoneBeach.topBlock;
	public static Block fillerBlock = BiomeGenBase.stoneBeach.fillerBlock;
	
	public RealisticBiomeVanillaStoneBeach(BiomeConfig config)
	{
		super(
			BiomeGenBase.stoneBeach,
			BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainVanillaStoneBeach(0f, 100f, 63f, 80f),
			new SurfaceVanillaStoneBeach(topBlock, fillerBlock, true, Blocks.gravel, 1f, 1.5f, 85f, 20f, 4f)
		);
		
		this.config = config;
		this.biomeWeight = ConfigVanilla.weightVanillaStoneBeach;
		this.generateVillages = ConfigVanilla.villageVanillaStoneBeach;
	}	
}
