package rtg.world.biome.realistic.tofucraft;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.RTGBiomeProvider;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoGrassDoubleTallgrass;
import rtg.world.gen.feature.WorldGenVolcano;
import rtg.world.gen.surface.tofucraft.SurfaceTOFUTofuExtremeHillsEdge;
import rtg.world.gen.terrain.tofucraft.TerrainTOFUTofuExtremeHillsEdge;

public class RealisticBiomeTOFUTofuExtremeHillsEdge extends RealisticBiomeTOFUBase
{	
	public RealisticBiomeTOFUTofuExtremeHillsEdge(BiomeGenBase tofuBiome, BiomeConfig config)
	{
		super(config, 
			tofuBiome, BiomeGenBase.river,
			new TerrainTOFUTofuExtremeHillsEdge(10f, 120f, 68f, 200f),
			new SurfaceTOFUTofuExtremeHillsEdge(config, tofuBiome.topBlock, tofuBiome.fillerBlock, tofuBiome.topBlock, tofuBiome.fillerBlock, 60f, -0.14f, 14f, 0.25f)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
        
        DecoGrassDoubleTallgrass decoGrassDoubleTallgrass = new DecoGrassDoubleTallgrass();
        decoGrassDoubleTallgrass.doubleGrassChance = 3;
        decoGrassDoubleTallgrass.loops = 15;
        decoGrassDoubleTallgrass.maxY = 128;
        this.addDeco(decoGrassDoubleTallgrass);
	}
    
    @Override
    public void rMapGen(Block[] blocks, byte[] metadata, World world, RTGBiomeProvider cmr, Random mapRand, int baseX, int baseY,
        int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float noise[])
    {
    
        if (baseX % 4 == 0 && baseY % 4 == 0 && mapRand.nextInt(6) == 0)
        {
            float river = cmr.getRiverStrength(baseX * 16, baseY * 16) + 1f;
            if (river > 0.98f && cmr.isBorderlessAt(baseX * 16, baseY * 16))
            {
                long i1 = mapRand.nextLong() / 2L * 2L + 1L;
                long j1 = mapRand.nextLong() / 2L * 2L + 1L;
                mapRand.setSeed((long) chunkX * i1 + (long) chunkY * j1 ^ world.getSeed());
                
                WorldGenVolcano.build(blocks, metadata, world, mapRand, baseX, baseY, chunkX, chunkY, simplex, cell, noise);
            }
        }
    }
}
