package rtg.world.biome.realistic.vanilla;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.vanilla.SurfaceVanillaIcePlainsSpikes;
import rtg.world.gen.terrain.vanilla.TerrainVanillaIcePlainsSpikes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaIcePlainsSpikes extends RealisticBiomeVanillaBase
{	
    public static BiomeGenBase standardBiome = BiomeGenBase.icePlains;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(standardBiome.biomeID + MUTATION_ADDEND);
    
    public static Block topBlock = mutationBiome.topBlock.getBlock();
    public static Block fillerBlock = mutationBiome.fillerBlock.getBlock();
	
	public RealisticBiomeVanillaIcePlainsSpikes(BiomeConfig config)
	{
		super(config, 
		    mutationBiome,
			BiomeGenBase.frozenRiver,
			new TerrainVanillaIcePlainsSpikes(),
			new SurfaceVanillaIcePlainsSpikes(config, topBlock, fillerBlock, topBlock, topBlock)
		);
	}
}
