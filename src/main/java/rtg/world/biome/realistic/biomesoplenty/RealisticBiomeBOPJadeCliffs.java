package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPJadeCliffs;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPJadeCliffs;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPJadeCliffs extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.jadeCliffs;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPJadeCliffs()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainBOPJadeCliffs(200f, 100f, 10f),
			new SurfaceBOPJadeCliffs(topBlock, fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("BOP Jade Cliffs");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPJadeCliffs;
		this.generateVillages = ConfigBOP.villageBOPJadeCliffs;
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength,
        float river)
    {
        
        RealisticBiomeBase.rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        
        //Emeralds.
        rRemoveEmeralds(world, rand, chunkX, chunkY, false);
        //rGenerateEmeralds(world, rand, chunkX, chunkY, false);
    }
}
