package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeProviderRTG;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenVolcano;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceRiverOasis;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPVolcanicIsland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPVolcanicIsland;

import java.util.Random;

public class RealisticBiomeBOPVolcanicIsland extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.volcanic_island.get();

    private static IBlockState bopTopBlock = getTopBlock();
    private static IBlockState bopFillBlock = getFillBlock();
    private static IBlockState bopMixTopBlock = getMixTopBlock();
    private static IBlockState bopMixFillBlock = getMixFillBlock();

    public RealisticBiomeBOPVolcanicIsland(BiomeConfig config) {
        super(config,
                bopBiome, Biomes.river,
                new TerrainBOPVolcanicIsland(),
                new SurfaceBOPVolcanicIsland(config,
                        bopTopBlock, //Block top
                        bopFillBlock, //Block filler,
                        bopMixTopBlock, //IBlockState mixTop,
                        bopMixFillBlock, //IBlockState mixFill,
                        80f, //float mixWidth,
                        -0.15f, //float mixHeight,
                        10f, //float smallWidth,
                        0.5f //float smallStrength
                )
        );

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 1;
    }

    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {

        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);

        for (int l14 = 0; l14 < 15; l14++) {
            int l19 = chunkX + rand.nextInt(16) + 8;
            int k22 = rand.nextInt(128);
            int j24 = chunkY + rand.nextInt(16) + 8;

            if (rand.nextInt(3) == 0) {
                (new WorldGenGrass(Blocks.double_plant, 2)).generate(world, rand, new BlockPos(l19, k22, j24));
            } else {
                (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, new BlockPos(l19, k22, j24));
            }
        }
    }

    @Override
    public void rMapGen(ChunkPrimer primer, World world, BiomeProviderRTG cmr, Random mapRand, int baseX, int baseY,
                        int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float noise[]) {

        if (baseX % 4 == 0 && baseY % 4 == 0 && mapRand.nextInt(6) == 0) {
            float river = cmr.getRiverStrength(baseX * 16, baseY * 16) + 1f;
            if (river > 0.98f && cmr.isBorderlessAt(baseX * 16, baseY * 16)) {
                long i1 = mapRand.nextLong() / 2L * 2L + 1L;
                long j1 = mapRand.nextLong() / 2L * 2L + 1L;
                mapRand.setSeed((long) chunkX * i1 + (long) chunkY * j1 ^ world.getSeed());

                WorldGenVolcano.build(primer, world, mapRand, baseX, baseY, chunkX, chunkY, simplex, cell, noise);
            }
        }
    }

    @Override
    public void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                         OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {

        this.getSurface().paintTerrain(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);

        SurfaceBase riverSurface = new SurfaceRiverOasis(this.config);
        riverSurface.paintTerrain(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
    }

    private static IBlockState getTopBlock() {
        //IBlockState topBlock = IBlockState.getBlockFromName("BiomesOPlenty:ashStone");
        IBlockState topBlock = Blocks.stone.getDefaultState(); // @TODO
        if (topBlock == null) throw new RuntimeException("BOP Volcano's topBlock is NULL!");
        return topBlock;
    }

    private static IBlockState getFillBlock() {
        //IBlockState fillBlock = IBlockState.getBlockFromName("BiomesOPlenty:ashStone");
        IBlockState fillBlock = Blocks.stone.getDefaultState(); // @TODO
        if (fillBlock == null) throw new RuntimeException("BOP Volcano's fillBlock is NULL!");
        return fillBlock;
    }

    private static IBlockState getMixTopBlock() {
        //IBlockState mixTopBlock = Block.getBlockFromName("BiomesOPlenty:ash");
        IBlockState mixTopBlock = Blocks.stone.getDefaultState(); // @TODO
        if (mixTopBlock == null) throw new RuntimeException("BOP Volcano's mixTopBlock is NULL!");
        return mixTopBlock;
    }

    private static IBlockState getMixFillBlock() {
        //IBlockState mixFillBlock = Block.getBlockFromName("BiomesOPlenty:ashStone");
        IBlockState mixFillBlock = Blocks.stone.getDefaultState(); // @TODO
        if (mixFillBlock == null) throw new RuntimeException("BOP Volcano's mixFillBlock is NULL!");
        return mixFillBlock;
    }
}
