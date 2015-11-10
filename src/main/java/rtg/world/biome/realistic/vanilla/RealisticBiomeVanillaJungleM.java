package rtg.world.biome.realistic.vanilla;

import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaJungle;
import rtg.world.gen.terrain.vanilla.TerrainVanillaJungle;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaJungleM extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.jungle.topBlock;
	public static Block fillerBlock = BiomeGenBase.jungle.fillerBlock;
	
	public RealisticBiomeVanillaJungleM()
	{
		super(
			BiomeGenBase.jungle,
			BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainVanillaJungle(),
			new SurfaceVanillaJungle(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("Vanilla Jungle");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigVanilla.weightVanillaJungle;
	}	
}
