package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import rtg.config.vanilla.ConfigVanilla;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.vanilla.SurfaceVanillaJungleEdgeM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaJungleEdgeM;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaJungleEdgeM extends RealisticBiomeVanillaBase
{
    public static BiomeGenBase standardBiome = BiomeGenBase.jungleEdge;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(standardBiome.biomeID + MUTATION_ADDEND);
    
    public static Block topBlock = mutationBiome.topBlock;
    public static Block fillerBlock = mutationBiome.fillerBlock;
    
    public RealisticBiomeVanillaJungleEdgeM()
    {
    
        super(
            mutationBiome,
            BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
            new TerrainVanillaJungleEdgeM(),
            new SurfaceVanillaJungleEdgeM(topBlock, fillerBlock));
        
        this.setRealisticBiomeName("Vanilla Jungle Edge M");
        this.biomeSize = BiomeSize.NORMAL;
        this.biomeWeight = ConfigVanilla.weightVanillaJungleEdgeM;
        this.generateVillages = ConfigVanilla.villageVanillaJungleEdgeM;
    }
    
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength,
        float river) {
    
        RealisticBiomeBase.rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        
        float l = simplex.noise2(chunkX / 100f, chunkY / 100f) * 6f + 0.8f;
        
        if (l > 0f && rand.nextInt(6) == 0)
        {
            int x22 = chunkX + rand.nextInt(16) + 8;
            int z22 = chunkY + rand.nextInt(16) + 8;
            int y22 = world.getHeightValue(x22, z22);
            (new WorldGenLog(Blocks.log, 3, Blocks.leaves, -1, 3 + rand.nextInt(4))).generate(world, rand, x22, y22, z22);
        }
    }
}
