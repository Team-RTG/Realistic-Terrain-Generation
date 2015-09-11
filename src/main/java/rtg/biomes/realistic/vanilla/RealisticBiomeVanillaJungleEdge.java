package rtg.biomes.realistic.vanilla;

import rtg.api.RTGBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.coast.vanilla.CoastVanillaJungleEdge;
import rtg.surface.vanilla.SurfaceVanillaJungleEdge;
import rtg.terrain.vanilla.TerrainVanillaJungleEdge;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaJungleEdge extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.jungleEdge.topBlock;
	public static Block fillerBlock = BiomeGenBase.jungleEdge.fillerBlock;
	
	public RealisticBiomeVanillaJungleEdge()
	{
		super(
			BiomeGenBase.jungleEdge,
			RTGBiomes.baseRiverWet,
			new CoastVanillaJungleEdge(),
			new TerrainVanillaJungleEdge(),
			new SurfaceVanillaJungleEdge(topBlock, fillerBlock, false, null, 1.3f)
		);
	}	
}
