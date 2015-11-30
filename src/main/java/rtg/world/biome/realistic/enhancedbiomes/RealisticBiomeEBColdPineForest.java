package rtg.world.biome.realistic.enhancedbiomes;

import enhancedbiomes.EnhancedBiomesMod;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBColdPineForest;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBColdPineForest;

public class RealisticBiomeEBColdPineForest extends RealisticBiomeEBBase
{
    public static Block ebDominantStoneBlock;
    public static byte ebDominantStoneMeta;
    public static Block ebDominantCobblestoneBlock;
    public static byte ebDominantCobblestoneMeta;
    
	public RealisticBiomeEBColdPineForest(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBColdPineForest(160f, 80f, 60f),
			new SurfaceEBColdPineForest(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.45f)
		);
		
		this.setRealisticBiomeName("EB Cold Pine Forest");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBColdPineForest;
		this.generateVillages = ConfigEB.villageEBColdPineForest;
        
        this.ebDominantStoneBlock = EnhancedBiomesMod.getDominantStone(ebBiome.biomeID);
        this.ebDominantStoneMeta = EnhancedBiomesMod.getDominantStoneMeta(ebBiome.biomeID);
        this.ebDominantCobblestoneBlock = EnhancedBiomesMod.getCobbleFromStone(ebDominantStoneBlock);
        this.ebDominantCobblestoneMeta = ebDominantStoneMeta;
    }
}
