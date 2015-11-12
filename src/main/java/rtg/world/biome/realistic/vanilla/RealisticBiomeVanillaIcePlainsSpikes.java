package rtg.world.biome.realistic.vanilla;

import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaIcePlainsSpikes;
import rtg.world.gen.terrain.vanilla.TerrainVanillaIcePlainsSpikes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaIcePlainsSpikes extends RealisticBiomeVanillaBase
{	
    public static BiomeGenBase standardBiome = BiomeGenBase.icePlains;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(standardBiome.biomeID + MUTATION_ADDEND);
    
    public static Block topBlock = mutationBiome.topBlock;
    public static Block fillerBlock = mutationBiome.fillerBlock;
	
	public RealisticBiomeVanillaIcePlainsSpikes()
	{
		super(
		    mutationBiome,
			BiomeBase.climatizedBiome(BiomeGenBase.frozenRiver, Climate.ICE),
			new TerrainVanillaIcePlainsSpikes(),
			new SurfaceVanillaIcePlainsSpikes(topBlock, fillerBlock, topBlock, topBlock)
		);
		
		this.setRealisticBiomeName("Vanilla Ice Plains Spikes");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigVanilla.weightVanillaIcePlainsSpikes;
	}
}
