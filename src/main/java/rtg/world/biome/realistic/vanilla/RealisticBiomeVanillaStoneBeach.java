package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigRTG;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaStoneBeach;
import rtg.world.gen.terrain.vanilla.TerrainVanillaStoneBeach;

public class RealisticBiomeVanillaStoneBeach extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.stoneBeach.topBlock;
	public static Block fillerBlock = BiomeGenBase.stoneBeach.fillerBlock;
	
	public RealisticBiomeVanillaStoneBeach()
	{
		super(
			BiomeGenBase.stoneBeach,
			BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
			new TerrainVanillaStoneBeach(0f, 100f, 63f, 80f),
			new SurfaceVanillaStoneBeach(topBlock, fillerBlock, false, null, 1f, 1.5f, 85f, 20f, 4f)
		);
		
		this.setRealisticBiomeName("Vanilla Stone Beach");
		this.biomeCategory = BiomeCategory.WET;
		this.biomeWeight = ConfigRTG.weightVanillaStoneBeach;
	}	
}
