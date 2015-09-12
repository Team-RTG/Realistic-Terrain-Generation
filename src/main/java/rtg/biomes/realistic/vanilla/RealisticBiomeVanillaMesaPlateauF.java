package rtg.biomes.realistic.vanilla;

import rtg.api.RTGBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.coast.vanilla.CoastVanillaMesaPlateauF;
import rtg.surface.vanilla.SurfaceVanillaMesaPlateauF;
import rtg.terrain.vanilla.TerrainVanillaMesaPlateauF;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaMesaPlateauF extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.mesaPlateau_F.topBlock;
	public static Block fillerBlock = BiomeGenBase.mesaPlateau_F.fillerBlock;
	
	public RealisticBiomeVanillaMesaPlateauF()
	{
		super(
			BiomeGenBase.mesaPlateau_F,
			RTGBiomes.baseRiverOasis,
			new CoastVanillaMesaPlateauF(),
			new TerrainVanillaMesaPlateauF(true, 35f, 160f, 60f, 40f, 69f),
			new SurfaceVanillaMesaPlateauF(topBlock, fillerBlock, (byte)1, 0)
		);
	}	
}
