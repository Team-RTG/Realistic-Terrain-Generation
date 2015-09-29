package rtg.world.biome.realistic.vanilla;

import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaRoofedForest;
import rtg.world.gen.terrain.vanilla.TerrainVanillaRoofedForest;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaRoofedForest extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.roofedForest.topBlock;
	public static Block fillerBlock = BiomeGenBase.roofedForest.fillerBlock;
	
	public RealisticBiomeVanillaRoofedForest()
	{
		super(
			BiomeGenBase.roofedForest,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
			new TerrainVanillaRoofedForest(),
			new SurfaceVanillaRoofedForest(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("Vanilla Roofed Forest");
	}	
}
