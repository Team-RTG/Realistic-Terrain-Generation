package rtg.world.biome.realistic.vanilla;

import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMesa;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMesa;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaMesa extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.mesa.topBlock;
	public static Block fillerBlock = BiomeGenBase.mesa.fillerBlock;
	
	public RealisticBiomeVanillaMesa()
	{
		super(
			BiomeGenBase.mesa,
			BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
			new TerrainVanillaMesa(),
			//new SurfaceVanillaMesa(Blocks.sand, Blocks.sand, (byte)1)
			new SurfaceVanillaMesa(topBlock, fillerBlock, (byte)1)
		);
		
		this.setRealisticBiomeName("Vanilla Mesa");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigVanilla.weightVanillaMesa;
	}	
}
