package rtg.world.biome.realistic.vanilla;

import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaIceMountains;
import rtg.world.gen.terrain.vanilla.TerrainVanillaIceMountains;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaIceMountains extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.iceMountains.topBlock;
	public static Block fillerBlock = BiomeGenBase.iceMountains.fillerBlock;
	
	public RealisticBiomeVanillaIceMountains()
	{
		super(
			BiomeGenBase.iceMountains,
			BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.ICE),
			new TerrainVanillaIceMountains(),
			new SurfaceVanillaIceMountains(topBlock, fillerBlock, Blocks.packed_ice, Blocks.ice)
		);
		
		this.setRealisticBiomeName("Vanilla Ice Mountains");
		this.biomeCategory = BiomeCategory.SNOW;
		this.biomeWeight = ConfigVanilla.weightVanillaIceMountains;
	}	
}
