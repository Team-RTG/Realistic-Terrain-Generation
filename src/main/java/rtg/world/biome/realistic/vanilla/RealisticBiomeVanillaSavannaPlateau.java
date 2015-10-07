package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigRTG;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaSavannaPlateau;
import rtg.world.gen.terrain.vanilla.TerrainVanillaSavannaPlateau;

public class RealisticBiomeVanillaSavannaPlateau extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.savannaPlateau.topBlock;
	public static Block fillerBlock = BiomeGenBase.savannaPlateau.fillerBlock;
	
	public RealisticBiomeVanillaSavannaPlateau()
	{
		super(
			BiomeGenBase.savannaPlateau,
			BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainVanillaSavannaPlateau(0f, 120f, 68f, 200f),
			new SurfaceVanillaSavannaPlateau(Blocks.grass, Blocks.dirt, 300f, true, true)
		);
		
		this.setRealisticBiomeName("Vanilla Savanna Plateau");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigRTG.weightVanillaSavannaPlateau;
	}	
}
