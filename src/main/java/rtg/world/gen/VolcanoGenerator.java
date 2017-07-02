package rtg.world.gen;

import java.util.Random;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.RTGAPI;
import rtg.api.config.RTGConfig;
import rtg.api.util.LimitedSet;
import rtg.api.util.noise.CellNoise;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.biome.IBiomeProviderRTG;
import rtg.world.biome.realistic.RealisticBiomeBase;
import static rtg.world.biome.realistic.RealisticBiomeBase.getBiome;
import rtg.world.biome.realistic.RealisticBiomePatcher;
import rtg.api.world.gen.feature.WorldGenVolcano;

/**
 *
 * @author Zeno410
 */
public class VolcanoGenerator {
    
    private Random mapRand;
    private long seed;
    protected RTGConfig rtgConfig = RTGAPI.config();
    private static long l;
    private static long l1;
    
    private static LimitedSet<ChunkPos> noVolcano = new LimitedSet<>(2000);
    
    public VolcanoGenerator(long seed) {
        this.seed = seed;
        mapRand = new Random(seed);
            mapRand.setSeed(seed);
            l = (mapRand.nextLong() / 2L) * 2L + 1L;
            l1 = (mapRand.nextLong() / 2L) * 2L + 1L;
    }
    public void generateMapGen(ChunkPrimer primer, Long unusedSeed, World world, IBiomeProviderRTG cmr, Random unusedMapRand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float noise[]) {

        // Have volcanoes been disabled in the global config?
        if (!rtgConfig.ENABLE_VOLCANOES.get()) return;

        final int mapGenRadius = 5;
        final int volcanoGenRadius = 15;

        //if (!seed.equals(currentSeed)) {
            //currentSeed = seed;
        //}


        // Volcanoes generation
        for (int baseX = chunkX - volcanoGenRadius; baseX <= chunkX + volcanoGenRadius; baseX++) {
            for (int baseY = chunkY - volcanoGenRadius; baseY <= chunkY + volcanoGenRadius; baseY++) {
                ChunkPos probe = new ChunkPos(baseX,baseY);
                if (noVolcano.contains(probe)) continue;
                noVolcano.add(probe);
                mapRand.setSeed((long) baseX * l + (long) baseY * l1 ^ seed);
                rMapVolcanoes(primer, world, cmr, baseX, baseY, chunkX, chunkY, simplex, cell, noise);
            }
        }
    }
    
    public void rMapVolcanoes(
        ChunkPrimer primer, World world, IBiomeProviderRTG cmr, 
            int baseX, int baseY, int chunkX, int chunkY,
        OpenSimplexNoise simplex, CellNoise cell, float noise[]) {

        // Have volcanoes been disabled in the global config?
        if (!rtgConfig.ENABLE_VOLCANOES.get()) return;

        // Let's go ahead and generate the volcano. Exciting!!! :D
        if (baseX % 4 == 0 && baseY % 4 == 0) {
            int biomeId = Biome.getIdForBiome(cmr.getBiomeGenAt(baseX * 16, baseY * 16));
            RealisticBiomeBase realisticBiome = getBiome(biomeId);

            // Do we need to patch the biome?
            if (realisticBiome == null) {
                RealisticBiomePatcher biomePatcher = new RealisticBiomePatcher();
                realisticBiome = biomePatcher.getPatchedRealisticBiome(
                    "NULL biome found when mapping volcanoes.");
            }
            if (!realisticBiome.getConfig().ALLOW_VOLCANOES.get()) return;
            // Have volcanoes been disabled via frequency?
            // Use the global frequency unless the biome frequency has been explicitly set.
            int chance = realisticBiome.getConfig().VOLCANO_CHANCE.get() == -1 ? rtgConfig.VOLCANO_CHANCE.get() : realisticBiome.getConfig().VOLCANO_CHANCE.get();
            if (chance < 1) return;
            if (mapRand.nextInt(chance)>0) return;

            float river = cmr.getRiverStrength(baseX * 16, baseY * 16) + 1f;
            if (river > 0.98f && cmr.isBorderlessAt(baseX * 16, baseY * 16)) {
                
                 // we have to pull it out of noVolcano. We do it this way to avoid having to make a ChunkPos twice
                ChunkPos probe = new ChunkPos(baseX,baseY);
                noVolcano.remove(probe);
                
                long i1 = mapRand.nextLong() / 2L * 2L + 1L;
                long j1 = mapRand.nextLong() / 2L * 2L + 1L;
                mapRand.setSeed((long) chunkX * i1 + (long) chunkY * j1 ^ world.getSeed());

                WorldGenVolcano.build(primer, world, mapRand, baseX, baseY, chunkX, chunkY, simplex, cell, noise);
            }
        }
    }
}