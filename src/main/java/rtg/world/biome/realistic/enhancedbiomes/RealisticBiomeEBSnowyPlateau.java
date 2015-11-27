package rtg.world.biome.realistic.enhancedbiomes;

import java.util.Random;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSnowyPlateau;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBSnowyPlateau;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBSnowyPlateau extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBSnowyPlateau(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBSnowyPlateau(),
			new SurfaceEBSnowyPlateau(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Snowy Plateau");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBSnowyPlateau;
		this.generateVillages = ConfigEB.villageEBSnowyPlateau;
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength,
        float river)
    {
        
        RealisticBiomeBase.rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        
        //Emeralds.
        rRemoveEmeralds(world, rand, chunkX, chunkY, false);
        rGenerateEmeralds(world, rand, chunkX, chunkY, false);
    }
}
