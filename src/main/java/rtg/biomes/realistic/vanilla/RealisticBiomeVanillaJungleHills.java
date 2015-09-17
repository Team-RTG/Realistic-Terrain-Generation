package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.surface.vanilla.SurfaceVanillaJungleHills;
import rtg.terrain.vanilla.TerrainVanillaJungleHills;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaJungleHills extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.jungleHills.topBlock;
	public static Block fillerBlock = BiomeGenBase.jungleHills.fillerBlock;
	
	public RealisticBiomeVanillaJungleHills()
	{
		super(
			BiomeGenBase.jungleHills,
			VanillaBiomes.vanillaRiverWet,
			new TerrainVanillaJungleHills(),
			new SurfaceVanillaJungleHills(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
	}	
}
