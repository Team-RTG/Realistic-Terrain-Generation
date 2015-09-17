package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.surface.vanilla.SurfaceVanillaForest;
import rtg.surface.vanilla.SurfaceVanillaForestHills;
import rtg.terrain.vanilla.TerrainVanillaForest;
import rtg.terrain.vanilla.TerrainVanillaForestHills;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaForestHills extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.forestHills.topBlock;
	public static Block fillerBlock = BiomeGenBase.forestHills.fillerBlock;
	
	public RealisticBiomeVanillaForestHills()
	{
		super(
			BiomeGenBase.forestHills,
			VanillaBiomes.vanillaRiverTemperate,
			new TerrainVanillaForestHills(70f, 180f, 7f, 100f, 38f, 260f, 68f),
			new SurfaceVanillaForestHills(topBlock, fillerBlock, topBlock, topBlock)
		);
	}	
}
