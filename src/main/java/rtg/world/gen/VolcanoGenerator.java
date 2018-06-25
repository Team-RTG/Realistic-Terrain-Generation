package rtg.world.gen;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.RTGAPI;
import rtg.api.util.BlockUtil;
import rtg.api.util.WorldUtil.Terrain;
import rtg.api.util.storage.LimitedArrayCacheSet;
import rtg.api.world.biome.IBiomeProviderRTG;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.RTGChunkGenSettings;
import rtg.world.RTGWorld;
import rtg.world.biome.realistic.RealisticBiomePatcher;

/**
 *
 * @author Zeno410
 */
// TODO: [1.12] Clean this up. #generate should *ONLY* be called *AFTER* it is determined that a volcano should generate and the site is viable.
public class VolcanoGenerator
{
    private static final IBlockState DEFAULT_VOLCANO_MAIN_BLOCK = Blocks.OBSIDIAN.getDefaultState();
    private static final IBlockState DEFAULT_VOLCANO_MIX1_BLOCK = Blocks.COBBLESTONE.getDefaultState();
    private static final IBlockState DEFAULT_VOLCANO_MIX2_BLOCK = Blocks.GRAVEL.getDefaultState();
    private static final IBlockState DEFAULT_VOLCANO_MIX3_BLOCK = Blocks.COAL_BLOCK.getDefaultState();
    private static final int         VOLCANO_GEN_RADIUS = 15;

    private static final LimitedArrayCacheSet<ChunkPos> noVolcano = new LimitedArrayCacheSet<>((VOLCANO_GEN_RADIUS * 2 + 1)*(VOLCANO_GEN_RADIUS * 2 + 1));

    private final RTGWorld            rtgWorld;
    private final RTGChunkGenSettings settings;

    private final double  ventEccentricity;
    private final double  ventRadius;
    private final int     lavaHeight;
    private final int     baseVolcanoHeight;
    private final boolean enableConduits;
    private final int     conduitDepth;

    private IBlockState lavaBlock;
    private IBlockState volcanoMainBlock;
    private IBlockState volcanoMixBlock1;
    private IBlockState volcanoMixBlock2;
    private IBlockState volcanoMixBlock3;

    VolcanoGenerator(RTGWorld rtgWorld) {
        this.rtgWorld          = rtgWorld;
        this.settings          = rtgWorld.getGeneratorSettings();
        this.ventEccentricity  = 8d * settings.volcanosCalderaMult;
        this.ventRadius        = 7d * settings.volcanosCalderaMult;
        this.lavaHeight        = 138 + 3 + (settings.volcanosErupt ? 5 : 0);// + 3 to account for lava cone tip
        this.baseVolcanoHeight = 142 + 8;
        this.enableConduits    = settings.volcanoConduits;
        this.conduitDepth      = settings.volcanoConduitDepth;
        this.lavaBlock         = settings.volcanosErupt ? Blocks.FLOWING_LAVA.getDefaultState() : Blocks.LAVA.getDefaultState();
        this.volcanoMainBlock  = BlockUtil.getBlockStateFromCfgString(RTGAPI.config().VOLCANO_MAIN_BLOCK.get(), DEFAULT_VOLCANO_MAIN_BLOCK);
        this.volcanoMixBlock1  = BlockUtil.getBlockStateFromCfgString(RTGAPI.config().VOLCANO_MIX1_BLOCK.get(), DEFAULT_VOLCANO_MIX1_BLOCK);
        this.volcanoMixBlock2  = BlockUtil.getBlockStateFromCfgString(RTGAPI.config().VOLCANO_MIX2_BLOCK.get(), DEFAULT_VOLCANO_MIX2_BLOCK);
        this.volcanoMixBlock3  = BlockUtil.getBlockStateFromCfgString(RTGAPI.config().VOLCANO_MIX3_BLOCK.get(), DEFAULT_VOLCANO_MIX3_BLOCK);
    }

    public void generate(ChunkPrimer primer, IBiomeProviderRTG cmr, final ChunkPos origin, float[] noise) {

        for (int baseX = origin.x - VOLCANO_GEN_RADIUS; baseX <= origin.x + VOLCANO_GEN_RADIUS; baseX++) {
            for (int baseZ = origin.z - VOLCANO_GEN_RADIUS; baseZ <= origin.z + VOLCANO_GEN_RADIUS; baseZ++) {
                ChunkPos probe = new ChunkPos(baseX, baseZ);
                if (noVolcano.contains(probe)) continue;
                noVolcano.add(probe);

                // Let's go ahead and generate the volcano. Exciting!!! :D
                if (baseX % 4 == 0 && baseZ % 4 == 0) {
// TODO: [1.12] This call should be fixed later with the new IBiomeProvider
                    IRealisticBiome realisticBiome = RTGAPI.getRTGBiome(((BiomeProvider)cmr).getBiome(new BlockPos(baseX * 16, 0, baseZ * 16)));
                    // Do we need to patch the biome?
                    if (realisticBiome == null) { realisticBiome = new RealisticBiomePatcher().getPatchedRealisticBiome("NULL biome found when mapping volcanoes."); }
                    if (!realisticBiome.getConfig().ALLOW_VOLCANOES.get()) return;

                    // Have volcanoes been disabled via frequency?
                    // Use the global frequency unless the biome frequency has been explicitly set.
// TODO: [1.12] This has to be reworked as the biome config allows OOB values.
                    int chance = realisticBiome.getConfig().VOLCANO_CHANCE.get() == -1 ? settings.volcanosChance : realisticBiome.getConfig().VOLCANO_CHANCE.get();
                    if (chance < 1) return;

                    Random rand = new Random(rtgWorld.getChunkSeed(baseX, baseZ));
                    if (rand.nextInt(chance)>0) return;

                    if (cmr.getRiverStrength(baseX * 16, baseZ * 16) + 1f > 0.98f &&
// TODO: [1.12] This call should be fixed later with the new IBiomeProvider
                        isBorderlessAt((BiomeProvider)cmr, baseX * 16, baseZ * 16)) {

                         // we have to pull it out of noVolcano. We do it this way to avoid having to make a ChunkPos twice
                        noVolcano.remove(new ChunkPos(baseX, baseZ));

                        buildChunk(primer, baseX, baseZ, origin, noise);
                    }
                }
            }
        }
    }

    private void buildChunk(ChunkPrimer primer, int baseX, int baseZ, final ChunkPos origin, float[] noise) {

        int worldX, worldZ;
        double height, ventNoise, terrainHeight, obsidian, distanceEll;
        IBlockState blockState;
        Random mapRand = new Random(rtgWorld.getChunkSeed(baseX, baseZ));

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                worldX = (origin.x * 16) + x;
                worldZ = (origin.z * 16) + z;

                // Distance in blocks from the center of the volcano
                distanceEll = Terrain.dis2Elliptic(worldX, worldZ, baseX * 16, baseZ * 16,
                    rtgWorld.simplexInstance(0).noise2d(worldX / 250d, worldZ / 250d) * ventEccentricity,
                    rtgWorld.simplexInstance(1).noise2d(worldX / 250d, worldZ / 250d) * ventEccentricity
                );

                // Height above which obsidian is placed
                obsidian  = -5d + distanceEll;
                obsidian += rtgWorld.simplexInstance(1).noise2d(worldX / 55d, worldZ / 55d) * 12d;
                obsidian += rtgWorld.simplexInstance(2).noise2d(worldX / 25d, worldZ / 25d) *  5d;
                obsidian += rtgWorld.simplexInstance(3).noise2d(worldX /  9d, worldZ /  9d) *  3d;

                // Make the volcanoes "mouth" more interesting
                ventNoise  = rtgWorld.simplexInstance(0).noise2d(worldX / 12d, worldZ / 12d) * 3d;
                ventNoise += rtgWorld.simplexInstance(1).noise2d(worldX /  4d, worldZ /  4d) * 1.5d;

                // Are we in the volcano's throat/conduit?
                if (distanceEll < ventRadius + ventNoise) {
                    height = rtgWorld.simplexInstance(0).noise2d(worldX / 5d, worldZ / 5d) * 2d;
                    for (int y = 255; y > -1; y--) {
                        // Above lava
                        if (y > lavaHeight) {
// TODO: [1.12] If it's air set it to air? wat.
                            if (primer.getBlockState(x, y, z) == Blocks.AIR.getDefaultState()) {
                                primer.setBlockState(x, y, z, Blocks.AIR.getDefaultState());
                            }
                        }
                        // Below lava and above obsidian
                        else if (y > obsidian && y < (lavaHeight - 9) + height) {
                            if (enableConduits && y >= conduitDepth) { primer.setBlockState(x, y, z, lavaBlock); }
                            else { primer.setBlockState(x, y, z, volcanoMainBlock); }
                        }
                        // In lava
                        else if (y < lavaHeight + 1) {
                            // + 3 to cut the tip of the lava
                            if (distanceEll + y < lavaHeight + 3) { primer.setBlockState(x, y, z, lavaBlock); }
                        }
                        // Below obsidian
                        else if (y < obsidian + 1) {
                            if (primer.getBlockState(x, y, z) == Blocks.AIR.getDefaultState()) {
                                if (enableConduits && y >= conduitDepth) { primer.setBlockState(x, y, z, lavaBlock); }
                                else { primer.setBlockState(x, y, z, Blocks.STONE.getDefaultState()); }
                            }
                            else { break; }
                        }
                    }
                }
                else {
                    terrainHeight = baseVolcanoHeight - Math.pow(distanceEll, 0.89d);
                    terrainHeight += rtgWorld.simplexInstance(1).noise2d(worldX / 112d, worldZ / 112d) * 5.5d;
                    terrainHeight += rtgWorld.simplexInstance(2).noise2d(worldX /  46d, worldZ /  46d) * 4.5d;
                    terrainHeight += rtgWorld.simplexInstance(3).noise2d(worldX /  16d, worldZ /  16d) * 2.5d;
                    terrainHeight += rtgWorld.simplexInstance(4).noise2d(worldX /   5d, worldZ /   5d) * 1d;

                    if (terrainHeight > noise[x * 16 + z]) { noise[x * 16 + z] = (float)terrainHeight; }

                    for (int y = 255; y > -1; y--) {
                        if (y <= terrainHeight) {
                            blockState = primer.getBlockState(x, y, z);

                            if (blockState == Blocks.AIR.getDefaultState() || blockState == Blocks.WATER.getDefaultState()) {

                                if (y > obsidian) {
                                    if (distanceEll > 10) {

                                        double patchNoise;

                                        // Patches
                                        if (distanceEll < 50 && primer.getBlockState(x, y + 1, z) == Blocks.AIR.getDefaultState()) {
                                            patchNoise  = rtgWorld.simplexInstance(0).noise2d(worldX / 10d, worldZ / 10d) * 1.3d;
                                            patchNoise += rtgWorld.simplexInstance(2).noise2d(worldX / 30d, worldZ / 30d) * 0.9d;
                                            patchNoise += rtgWorld.simplexInstance(3).noise2d(worldX /  5d, worldZ /  5d) * 0.6d;
                                            if (patchNoise > .85) {
                                                primer.setBlockState(x, y, z, volcanoMixBlock1); // Cobble
                                                continue;
                                            }
                                        }

                                        if (distanceEll < 75 && primer.getBlockState(x, y + 1, z) == Blocks.AIR.getDefaultState()) {
                                            patchNoise  = rtgWorld.simplexInstance(0).noise2d(worldX / 10d, worldZ / 10d) * 1.3d;
                                            patchNoise += rtgWorld.simplexInstance(4).noise2d(worldX / 30d, worldZ / 30d) * 0.9d;
                                            patchNoise += rtgWorld.simplexInstance(5).noise2d(worldX /  5d, worldZ /  5d) * 0.5d;
                                            if (patchNoise > .92) {
                                                primer.setBlockState(x, y, z, volcanoMixBlock2); // Gravel
                                                continue;
                                            }
                                        }
                                        if (distanceEll < 75 && primer.getBlockState(x, y + 1, z) == Blocks.AIR.getDefaultState()) {
                                            patchNoise  = rtgWorld.simplexInstance(0).noise2d(worldX / 10d, worldZ / 10d) * 1.3d;
                                            patchNoise += rtgWorld.simplexInstance(6).noise2d(worldX / 30d, worldZ / 30d) * 0.7d;
                                            patchNoise += rtgWorld.simplexInstance(7).noise2d(worldX /  5d, worldZ /  5d) * 0.7d;
                                            if (patchNoise > .93) {
                                                primer.setBlockState(x, y, z, volcanoMixBlock3); // Coal block
                                                continue;
                                            }
                                        }
                                    }

                                    // Surfacing
                                    // TODO: [1.12] Why is this passing the height as the second parameter?
                                    if (distanceEll < 70d + rtgWorld.simplexInstance(0).noise2d(x / 26d, y / 26d) * 5d) {
                                        if (mapRand.nextInt(20) == 0) { blockState = volcanoMixBlock3; }
                                        else { blockState = volcanoMainBlock; }
                                    }

                                    // TODO: [1.12] Why is this passing the height as the second parameter?
                                    else if (distanceEll < 75d + rtgWorld.simplexInstance(0).noise2d(x / 26d, y / 26d) * 5d) {
                                        // Jittering in the base, to smooth the transition
                                        double powerNoise = rtgWorld.simplexInstance(3).noise2d(worldX / 40d, worldZ / 40d) * 2d;

                                        // TODO: [1.12] Why is this passing the height as the second parameter?
                                        if (mapRand.nextInt(1 + (int) Math.pow(Math.abs(distanceEll - (75d + rtgWorld.simplexInstance(0).noise2d(x / 26d, y / 26d) * 5d)), 1.5d + powerNoise) + 1) == 0) {

                                            if (mapRand.nextInt(20) == 0) { blockState = volcanoMixBlock2; }
                                            else { blockState = Blocks.STONE.getDefaultState(); }// Stone so that surfacing will run (so this usually becomes grass)
                                        }
                                        else { blockState = volcanoMainBlock; }
                                    }
                                    else { blockState = Blocks.STONE.getDefaultState(); }// Stone so that surfacing will run (so this usually becomes grass)
                                }
                                else { blockState = Blocks.STONE.getDefaultState(); }
                            }
                            else { break; }

                            primer.setBlockState(x, y, z, blockState);
                        }
                    }
                }
            }
        }
    }

    private boolean isBorderlessAt(BiomeProvider provider, int worldX, int worldZ) {

        MutableBlockPos mpos = new MutableBlockPos(worldX, 0, worldZ);
        Biome biome = provider.getBiome(mpos);
// TODO: [1.12] Why is this sampling such a small area?
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                if (provider.getBiome(mpos.setPos(mpos.getX() + x, 0, mpos.getZ() + z)) == biome) {
                    return false;
                }
            }
        }
        return true;
    }
}
