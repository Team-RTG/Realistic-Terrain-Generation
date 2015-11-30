package rtg.world.biome.realistic.enhancedbiomes;

import enhancedbiomes.EnhancedBiomesMod;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBPineForestArchipelago;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBPineForestArchipelago;

public class RealisticBiomeEBPineForestArchipelago extends RealisticBiomeEBBase
{
    public static Block ebDominantStoneBlock;
    public static byte ebDominantStoneMeta;
    public static Block ebDominantCobblestoneBlock;
    public static byte ebDominantCobblestoneMeta;
    
	public RealisticBiomeEBPineForestArchipelago(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBPineForestArchipelago(200f, 100f, 0f),
			new SurfaceEBPineForestArchipelago(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("EB Pine Forest Archipelago");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBPineForestArchipelago;
		this.generateVillages = ConfigEB.villageEBPineForestArchipelago;
        
        this.ebDominantStoneBlock = EnhancedBiomesMod.getDominantStone(ebBiome.biomeID);
        this.ebDominantStoneMeta = EnhancedBiomesMod.getDominantStoneMeta(ebBiome.biomeID);
        this.ebDominantCobblestoneBlock = EnhancedBiomesMod.getCobbleFromStone(ebDominantStoneBlock);
        this.ebDominantCobblestoneMeta = ebDominantStoneMeta;
    }
}
