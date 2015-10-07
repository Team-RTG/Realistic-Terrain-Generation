package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigRTG;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMesaPlateau;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMesaPlateau;

public class RealisticBiomeVanillaMesaPlateau extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.mesaPlateau.topBlock;
	public static Block fillerBlock = BiomeGenBase.mesaPlateau.fillerBlock;
	
	public RealisticBiomeVanillaMesaPlateau()
	{
		super(
			BiomeGenBase.mesaPlateau,
			BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
			new TerrainVanillaMesaPlateau(true, 35f, 160f, 60f, 40f, 69f),
			new SurfaceVanillaMesaPlateau(topBlock, fillerBlock, (byte)1, 0)
		);
		
		this.setRealisticBiomeName("Vanilla Mesa Plateau");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigRTG.weightVanillaMesaPlateau;
	}	
}
