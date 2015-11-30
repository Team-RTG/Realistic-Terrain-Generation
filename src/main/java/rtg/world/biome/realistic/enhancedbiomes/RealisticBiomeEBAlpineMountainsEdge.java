package rtg.world.biome.realistic.enhancedbiomes;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBAlpineMountainsEdge;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBAlpineMountainsEdge;

public class RealisticBiomeEBAlpineMountainsEdge extends RealisticBiomeEBBase
{
    public static Block ebDominantStoneBlock;
    public static byte ebDominantStoneMeta;
    public static Block ebDominantCobblestoneBlock;
    public static byte ebDominantCobblestoneMeta;
    
	public RealisticBiomeEBAlpineMountainsEdge(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBAlpineMountainsEdge(300f, 80f, 30f),
			new SurfaceEBAlpineMountainsEdge(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.45f)
		);
		
		this.setRealisticBiomeName("EB Alpine Mountains Edge");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBAlpineMountainsEdge;
		this.generateVillages = ConfigEB.villageEBAlpineMountainsEdge;
        
        this.ebDominantStoneBlock = EnhancedBiomesMod.getDominantStone(ebBiome.biomeID);
        this.ebDominantStoneMeta = EnhancedBiomesMod.getDominantStoneMeta(ebBiome.biomeID);
        this.ebDominantCobblestoneBlock = EnhancedBiomesMod.getCobbleFromStone(ebDominantStoneBlock);
        this.ebDominantCobblestoneMeta = ebDominantStoneMeta;
    }
}
