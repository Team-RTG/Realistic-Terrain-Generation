package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPShield;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.util.noise.SimplexOctave;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPShield;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPShield;

import java.util.Random;

public class RealisticBiomeBOPShield extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.shield.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;
    private float lakeInterval = 80;

    public RealisticBiomeBOPShield() {
        super(
                bopBiome, Biomes.river,
                new TerrainBOPShield(0f, 100f, 68f, 170f),
                new SurfaceBOPShield(config, topBlock, fillerBlock)
        );
    }

    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {

        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);

        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);

        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;

        for (int i23 = 0; i23 < 1; i23++) {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeight(new BlockPos(i1, 0, j1)).getY();

            if (rand.nextInt(16) == 0) {
                (new WorldGenBlockBlob(Blocks.cobblestone, 0)).generate(world, rand, new BlockPos(i1, k1, j1));
            }
        }

        if (this.config.getPropertyById(BiomeConfigBOPShield.decorationLogsId).valueBoolean) {

            if (rand.nextInt(6) == 0) {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeight(new BlockPos(x22, 0, z22)).getY();

                Block log;
                byte logMeta;
                int intLogLength;

                if (rand.nextBoolean()) {

                    log = BOPBlocks.log_4;
                    logMeta = (byte) 0;
                    intLogLength = 3 + rand.nextInt(2);
                } else {

                    log = Blocks.log;
                    logMeta = (byte) 1;
                    intLogLength = 3 + rand.nextInt(2);
                }

                (new WorldGenLog(log, logMeta, Blocks.leaves, -1, intLogLength)).generate(world, rand, new BlockPos(x22, y22, z22));
            }
        }
    }

    public float lakePressure(OpenSimplexNoise simplex, CellNoise simplexCell, int x, int y, float border) {
        float baseLakes = super.lakePressure(simplex, simplexCell, x, y, border);
        SimplexOctave.Derivative jitter = new SimplexOctave.Derivative();
        simplex.riverJitter().evaluateNoise(x / 30.0, y / 30.0, jitter);
        double pX = x + jitter.deltax() * 15f;
        double pY = y + jitter.deltay() * 15f;
        simplex.mountain().evaluateNoise(x / 10.0, y / 10.0, jitter);
        pX += jitter.deltax() * 4f;
        pY += jitter.deltay() * 4f;
        //double results =simplexCell.river().noise(pX / lakeInterval, pY / lakeInterval,1.0);
        double[] lakeResults = simplexCell.river().eval((float) x / lakeInterval, (float) y / lakeInterval);
        float results = 1f - (float) ((lakeResults[1] - lakeResults[0]) / lakeResults[1]);
        if (results > 1.01) throw new RuntimeException("" + lakeResults[0] + " , " + lakeResults[1]);
        if (results < -.01) throw new RuntimeException("" + lakeResults[0] + " , " + lakeResults[1]);
        //float result = simplexCell.river().noise((float)x/ lakeInterval, (float)y/ lakeInterval,1.0);
        return Math.min(baseLakes, results);
        //return results;
    }
}
