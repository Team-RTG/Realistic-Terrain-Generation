package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigRTG;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMesaPlateauF;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMesaPlateauF;

public class RealisticBiomeVanillaMesaPlateauF extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.mesaPlateau_F.topBlock;
	public static Block fillerBlock = BiomeGenBase.mesaPlateau_F.fillerBlock;
	
	public RealisticBiomeVanillaMesaPlateauF()
	{
		super(
			BiomeGenBase.mesaPlateau_F,
			BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
			new TerrainVanillaMesaPlateauF(true, 35f, 160f, 60f, 40f, 69f),
			new SurfaceVanillaMesaPlateauF(topBlock, fillerBlock, (byte)1, 0)
		);
		
		this.setRealisticBiomeName("Vanilla Mesa Plateau F");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigRTG.weightVanillaMesaPlateau_F;
	}	
}
