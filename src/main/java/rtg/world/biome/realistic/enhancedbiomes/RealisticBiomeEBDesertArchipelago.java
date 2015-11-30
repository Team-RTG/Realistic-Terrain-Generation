package rtg.world.biome.realistic.enhancedbiomes;

import enhancedbiomes.EnhancedBiomesMod;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBDesertArchipelago;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBDesertArchipelago;

public class RealisticBiomeEBDesertArchipelago extends RealisticBiomeEBBase
{
    public static Block ebDominantStoneBlock;
    public static byte ebDominantStoneMeta;
    public static Block ebDominantCobblestoneBlock;
    public static byte ebDominantCobblestoneMeta;
    
	public RealisticBiomeEBDesertArchipelago(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
			new TerrainEBDesertArchipelago(),
			new SurfaceEBDesertArchipelago(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.sand, Blocks.sand, Blocks.sand)
		);
		
		this.setRealisticBiomeName("EB Desert Archipelago");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBDesertArchipelago;
		this.generateVillages = ConfigEB.villageEBDesertArchipelago;
        
        this.ebDominantStoneBlock = EnhancedBiomesMod.getDominantStone(ebBiome.biomeID);
        this.ebDominantStoneMeta = EnhancedBiomesMod.getDominantStoneMeta(ebBiome.biomeID);
        this.ebDominantCobblestoneBlock = EnhancedBiomesMod.getCobbleFromStone(ebDominantStoneBlock);
        this.ebDominantCobblestoneMeta = ebDominantStoneMeta;
    }
}
