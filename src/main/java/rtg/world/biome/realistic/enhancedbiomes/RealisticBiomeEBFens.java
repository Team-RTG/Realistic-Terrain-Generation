package rtg.world.biome.realistic.enhancedbiomes;

import java.util.Random;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeShrub;
import rtg.world.gen.feature.tree.WorldGenTreeWillow;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBFens;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBFens;
import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeEBFens extends RealisticBiomeEBBase
{
    public static Block ebDominantStoneBlock;
    public static byte ebDominantStoneMeta;
    public static Block ebDominantCobblestoneBlock;
    public static byte ebDominantCobblestoneMeta;
    
    public RealisticBiomeEBFens(BiomeGenBase ebBiome)
    {
    
        super(
            ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
            new TerrainEBFens(),
            new SurfaceEBFens(
                EnhancedBiomesMod.useNewGrass ? EnhancedBiomesBlocks.grassEB : Blocks.grass,
                EnhancedBiomesMod.useNewGrass ? EnhancedBiomesBlocks.dirtEB : Blocks.dirt,
                Blocks.stone,
                Blocks.cobblestone,
                EnhancedBiomesMod.useNewGrass ? (byte) 4 : (byte) 0,
                EnhancedBiomesMod.useNewGrass ? (byte) 4 : (byte) 0
            ));
        
        this.setRealisticBiomeName("EB Fens");
        this.biomeSize = BiomeSize.NORMAL;
        this.biomeWeight = ConfigEB.weightEBFens;
        this.generateVillages = ConfigEB.villageEBFens;
        
        this.ebDominantStoneBlock = EnhancedBiomesMod.getDominantStone(ebBiome.biomeID);
        this.ebDominantStoneMeta = EnhancedBiomesMod.getDominantStoneMeta(ebBiome.biomeID);
        this.ebDominantCobblestoneBlock = EnhancedBiomesMod.getCobbleFromStone(ebDominantStoneBlock);
        this.ebDominantCobblestoneMeta = ebDominantStoneMeta;
    }
    
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength,
        float river)
    {
        for (int l14 = 0; l14 < 24f * strength; l14++)
        {
            int l19 = chunkX + rand.nextInt(16) + 8;
            int k22 = rand.nextInt(128);
            int j24 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
        }
    }
}
