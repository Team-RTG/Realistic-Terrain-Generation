package teamrtg.rtg.world.gen;

import net.minecraft.block.BlockStone;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.api.mods.Mods;
import teamrtg.rtg.util.math.RandomUtil;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.util.noise.SimplexOctave;
import teamrtg.rtg.world.biome.surface.SurfaceBase;
import teamrtg.rtg.world.biome.surface.SurfaceGeneric;
import teamrtg.rtg.world.gen.deco.DecoBase;

import java.util.Random;

/**
 * @author topisani
 */
public class RealisticBiomeGenerator {
    private static final RealisticBiomeGenerator[] biomeGenerators = new RealisticBiomeGenerator[256];
    private static float actualRiverProportion = 300f / 1600f;
    public SurfaceBase surfaceGeneric;
    public final RealisticBiomeBase biome;

    public RealisticBiomeGenerator(RealisticBiomeBase biome) {
        this.biome = biome;
        biomeGenerators[RealisticBiomeBase.getIdForBiome(biome)] = this;
        surfaceGeneric = new SurfaceGeneric(this.biome);
    }

    public static RealisticBiomeBase getBiome(int id) {
        return biomeGenerators[id].biome;
    }

    public static RealisticBiomeGenerator forBiome(BiomeGenBase biome) {
        return biomeGenerators[RealisticBiomeBase.getIdForBiome(biome)];
    }

    public static RealisticBiomeGenerator forBiome(int id) {
        return biomeGenerators[id];
    }

    public void populatePreDecorate(IChunkGenerator iChunkGenerator, World worldObj, Random rand, int chunkX, int chunkZ, boolean flag) {
        int worldX = chunkX * 16;
        int worldZ = chunkZ * 16;
        boolean gen = true;

        gen = TerrainGen.populate(iChunkGenerator, worldObj, rand, chunkX, chunkZ, flag, PopulateChunkEvent.Populate.EventType.LAKE);

        // Underground water lakes.
        if (Mods.RTG.config.ENABLE_UNDERGROUND_WATER_LAKES.get()) {

            if (gen && (Mods.RTG.config.UNDERGROUND_WATER_LAKE_CHANCE.get() > 0)) {

                int i2 = worldX + rand.nextInt(16) + 8;
                int l4 = RandomUtil.getRandomInt(rand, 1, 50);
                int i8 = worldZ + rand.nextInt(16) + 8;

                if (rand.nextInt(Mods.RTG.config.UNDERGROUND_WATER_LAKE_CHANCE.get()) == 0 && (RandomUtil.getRandomInt(rand, 1, Mods.RTG.config.UNDERGROUND_WATER_LAKE_CHANCE.get()) == 1)) {

                    (new WorldGenLakes(Blocks.water)).generate(worldObj, rand, new BlockPos(new BlockPos(i2, l4, i8)));
                }
            }
        }

        // Surface water lakes.
        if (Mods.RTG.config.ENABLE_SURFACE_WATER_LAKES.get()) {

            if (gen && (this.biome.config.SURFACE_WATER_LAKE_CHANCE.get() > 0)) {

                int i2 = worldX + rand.nextInt(16) + 8;
                int i8 = worldZ + rand.nextInt(16) + 8;
                int l4 = worldObj.getHeight(new BlockPos(i2, 0, i8)).getY();

                //Surface lakes.
                if (rand.nextInt(this.biome.config.SURFACE_WATER_LAKE_CHANCE.get()) == 0 && (RandomUtil.getRandomInt(rand, 1, this.biome.config.SURFACE_WATER_LAKE_CHANCE.get()) == 1)) {

                    if (l4 > 63) {

                        (new WorldGenLakes(Blocks.water)).generate(worldObj, rand, new BlockPos(i2, l4, i8));
                    }
                }
            }
        }

        gen = TerrainGen.populate(iChunkGenerator, worldObj, rand, chunkX, chunkZ, flag, PopulateChunkEvent.Populate.EventType.LAVA);

        // Underground lava lakes.
        if (Mods.RTG.config.ENABLE_UNDERGROUND_LAVA_LAKES.get()) {

            if (gen && (Mods.RTG.config.UNDERGROUND_LAVA_LAKE_CHANCE.get() > 0)) {

                int i2 = worldX + rand.nextInt(16) + 8;
                int l4 = RandomUtil.getRandomInt(rand, 1, 50);
                int i8 = worldZ + rand.nextInt(16) + 8;

                if (rand.nextInt(Mods.RTG.config.UNDERGROUND_LAVA_LAKE_CHANCE.get()) == 0 && (RandomUtil.getRandomInt(rand, 1, Mods.RTG.config.UNDERGROUND_LAVA_LAKE_CHANCE.get()) == 1)) {

                    (new WorldGenLakes(Blocks.lava)).generate(worldObj, rand, new BlockPos(i2, l4, i8));
                }
            }
        }

        // Surface lava lakes.
        if (Mods.RTG.config.ENABLE_SURFACE_LAVA_LAKES.get()) {

            if (gen && (this.biome.config.SURFACE_LAVA_LAKE_CHANCE.get() > 0)) {

                int i2 = worldX + rand.nextInt(16) + 8;
                int i8 = worldZ + rand.nextInt(16) + 8;
                int l4 = worldObj.getHeight(new BlockPos(i2, 0, i8)).getY();

                //Surface lakes.
                if (rand.nextInt(this.biome.config.SURFACE_LAVA_LAKE_CHANCE.get()) == 0 && (RandomUtil.getRandomInt(rand, 1, this.biome.config.SURFACE_LAVA_LAKE_CHANCE.get()) == 1)) {

                    if (l4 > 63) {

                        (new WorldGenLakes(Blocks.lava)).generate(worldObj, rand, new BlockPos(i2, l4, i8));
                    }
                }
            }
        }

        if (Mods.RTG.config.GENERATE_DUNGEONS.get()) {

            gen = TerrainGen.populate(iChunkGenerator, worldObj, rand, chunkX, chunkZ, flag, PopulateChunkEvent.Populate.EventType.DUNGEON);
            for (int k1 = 0; k1 < 8 && gen; k1++) {
                int j5 = worldX + rand.nextInt(16) + 8;
                int k8 = rand.nextInt(128);
                int j11 = worldZ + rand.nextInt(16) + 8;

                (new WorldGenDungeons()).generate(worldObj, rand, new BlockPos(j5, k8, j11));
            }
        }
    }

    /**
     * This method should be called if both of the following conditions are true:
     * <p/>
     * 1) You are manually decorating a biome by overrding rDecorate().
     * 2) You are NOT calling decorateSeedBiome() within rDecorate().
     */
    public void rOreGenSeedBiome(World world, Random rand, BlockPos blockPos, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, BiomeGenBase seedBiome) {

        if (seedBiome.theBiomeDecorator.chunkProviderSettings == null) {

            String s = world.getWorldInfo().getGeneratorOptions();

            if (s != null) {
                seedBiome.theBiomeDecorator.chunkProviderSettings = ChunkProviderSettings.Factory.jsonToFactory(s).func_177864_b();
            } else {
                seedBiome.theBiomeDecorator.chunkProviderSettings = ChunkProviderSettings.Factory.jsonToFactory("").func_177864_b();
            }
        }

        seedBiome.theBiomeDecorator.dirtGen = new WorldGenMinable(Blocks.dirt.getDefaultState(), seedBiome.theBiomeDecorator.chunkProviderSettings.dirtSize);
        seedBiome.theBiomeDecorator.gravelGen = new WorldGenMinable(Blocks.gravel.getDefaultState(), seedBiome.theBiomeDecorator.chunkProviderSettings.gravelSize);
        seedBiome.theBiomeDecorator.graniteGen = new WorldGenMinable(Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE), seedBiome.theBiomeDecorator.chunkProviderSettings.graniteSize);
        seedBiome.theBiomeDecorator.dioriteGen = new WorldGenMinable(Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE), seedBiome.theBiomeDecorator.chunkProviderSettings.dioriteSize);
        seedBiome.theBiomeDecorator.andesiteGen = new WorldGenMinable(Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE), seedBiome.theBiomeDecorator.chunkProviderSettings.andesiteSize);
        seedBiome.theBiomeDecorator.coalGen = new WorldGenMinable(Blocks.coal_ore.getDefaultState(), seedBiome.theBiomeDecorator.chunkProviderSettings.coalSize);
        seedBiome.theBiomeDecorator.ironGen = new WorldGenMinable(Blocks.iron_ore.getDefaultState(), seedBiome.theBiomeDecorator.chunkProviderSettings.ironSize);
        seedBiome.theBiomeDecorator.goldGen = new WorldGenMinable(Blocks.gold_ore.getDefaultState(), seedBiome.theBiomeDecorator.chunkProviderSettings.goldSize);
        seedBiome.theBiomeDecorator.redstoneGen = new WorldGenMinable(Blocks.redstone_ore.getDefaultState(), seedBiome.theBiomeDecorator.chunkProviderSettings.redstoneSize);
        seedBiome.theBiomeDecorator.diamondGen = new WorldGenMinable(Blocks.diamond_ore.getDefaultState(), seedBiome.theBiomeDecorator.chunkProviderSettings.diamondSize);
        seedBiome.theBiomeDecorator.lapisGen = new WorldGenMinable(Blocks.lapis_ore.getDefaultState(), seedBiome.theBiomeDecorator.chunkProviderSettings.lapisSize);

        net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(world, rand, blockPos));
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.dirtGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIRT))
            genStandardOre1(world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.dirtCount, seedBiome.theBiomeDecorator.dirtGen, seedBiome.theBiomeDecorator.chunkProviderSettings.dirtMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.dirtMaxHeight);
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.gravelGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GRAVEL))
            genStandardOre1(world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.gravelCount, seedBiome.theBiomeDecorator.gravelGen, seedBiome.theBiomeDecorator.chunkProviderSettings.gravelMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.gravelMaxHeight);
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.dioriteGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIORITE))
            genStandardOre1(world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.dioriteCount, seedBiome.theBiomeDecorator.dioriteGen, seedBiome.theBiomeDecorator.chunkProviderSettings.dioriteMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.dioriteMaxHeight);
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.graniteGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GRANITE))
            genStandardOre1(world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.graniteCount, seedBiome.theBiomeDecorator.graniteGen, seedBiome.theBiomeDecorator.chunkProviderSettings.graniteMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.graniteMaxHeight);
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.andesiteGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.ANDESITE))
            genStandardOre1(world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.andesiteCount, seedBiome.theBiomeDecorator.andesiteGen, seedBiome.theBiomeDecorator.chunkProviderSettings.andesiteMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.andesiteMaxHeight);
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.coalGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.COAL))
            genStandardOre1(world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.coalCount, seedBiome.theBiomeDecorator.coalGen, seedBiome.theBiomeDecorator.chunkProviderSettings.coalMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.coalMaxHeight);
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.ironGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.IRON))
            genStandardOre1(world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.ironCount, seedBiome.theBiomeDecorator.ironGen, seedBiome.theBiomeDecorator.chunkProviderSettings.ironMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.ironMaxHeight);
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.goldGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GOLD))
            genStandardOre1(world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.goldCount, seedBiome.theBiomeDecorator.goldGen, seedBiome.theBiomeDecorator.chunkProviderSettings.goldMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.goldMaxHeight);
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.redstoneGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.REDSTONE))
            genStandardOre1(world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.redstoneCount, seedBiome.theBiomeDecorator.redstoneGen, seedBiome.theBiomeDecorator.chunkProviderSettings.redstoneMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.redstoneMaxHeight);
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.diamondGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIAMOND))
            genStandardOre1(world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.diamondCount, seedBiome.theBiomeDecorator.diamondGen, seedBiome.theBiomeDecorator.chunkProviderSettings.diamondMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.diamondMaxHeight);
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.lapisGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.LAPIS))
            genStandardOre2(world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.lapisCount, seedBiome.theBiomeDecorator.lapisGen, seedBiome.theBiomeDecorator.chunkProviderSettings.lapisCenterHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.lapisSpread);

        // Emeralds.
        if (Mods.RTG.config.GENERATE_ORE_EMERALD.get() && this.biome.config.GENERATE_EMERALDS.get()) {
            rGenerateEmeralds(world, rand, blockPos);
        }

        net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(world, rand, blockPos));
    }

    // lake calculations

    /**
     * Standard ore generation helper. Generates most ores.
     * @see net.minecraft.world.biome.BiomeDecorator
     */
    protected void genStandardOre1(World worldObj, Random rand, BlockPos blockPos, int blockCount, WorldGenerator generator, int minHeight, int maxHeight) {
        if (maxHeight < minHeight) {
            int i = minHeight;
            minHeight = maxHeight;
            maxHeight = i;
        } else if (maxHeight == minHeight) {
            if (minHeight < 255) {
                ++maxHeight;
            } else {
                --minHeight;
            }
        }

        for (int j = 0; j < blockCount; ++j) {
            BlockPos blockpos = blockPos.add(rand.nextInt(16), rand.nextInt(maxHeight - minHeight) + minHeight, rand.nextInt(16));
            generator.generate(worldObj, rand, blockpos);
        }
    }

    /**
     * Standard ore generation helper. Generates Lapis Lazuli.
     * @see net.minecraft.world.biome.BiomeDecorator
     */
    protected void genStandardOre2(World worldObj, Random rand, BlockPos blockPos, int blockCount, WorldGenerator generator, int centerHeight, int spread) {
        for (int i = 0; i < blockCount; ++i) {
            BlockPos blockpos = blockPos.add(rand.nextInt(16), rand.nextInt(spread) + rand.nextInt(spread) + centerHeight - spread, rand.nextInt(16));
            generator.generate(worldObj, rand, blockpos);
        }
    }

    public void rGenerateEmeralds(World world, Random rand, BlockPos blockPos) {
        int k = 3 + rand.nextInt(6);
        BlockPos.MutableBlockPos mbp = new BlockPos.MutableBlockPos();

        for (int l = 0; l < k; ++l) {
            mbp.set(blockPos.getX() + rand.nextInt(16),
                    rand.nextInt(28) + 4,
                    blockPos.getZ() + rand.nextInt(16));

            //TODO: How to get that last argument???
            if (world.getBlockState(mbp).getBlock().isReplaceableOreGen(world.getBlockState(mbp), world, mbp, BlockMatcher.forBlock(Blocks.stone))) {
                world.setBlockState(mbp, Blocks.emerald_ore.getDefaultState(), 2);
            }
        }
    }

    /**
     * When manually decorating biomes by overriding rDecorate(), sometimes you want the biome
     * to partially decorate itself. That's what this method does... it calls the biome's decorate() method.
     */
    public void decorateSeedBiome(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, BiomeGenBase seedBiome) {
        if (strength > 0.3f) {
            seedBiome.decorate(world, rand, new BlockPos(chunkX, 0, chunkY));
        } else {
            rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, seedBiome);
        }
    }

    public float rNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        if (this.biome.noWaterFeatures) {
            border = border * 2;
            if (border > 1f) border = 1;
            river = 1f - border * (1f - river);
            return this.biome.getTerrain().generateNoise(simplex, cell, x, y, border, river);
        }
        float lakeStrength = lakePressure(simplex, cell, x, y, border);
        double lakeFlattening = this.lakeFlattening(lakeStrength, this.biome.lakeWaterLevel, this.biome.lakeDepressionLevel);
        // we add some flattening to the rivers but not to the lakes. This gives the rivers flatter
        // banks and the lakes steeper ones, which seems to be better aesthetically
        float riverFlattening = river * 1.25f - 0.25f;
        if (riverFlattening < 0) riverFlattening = 0;
        if (lakeFlattening < river) river = (float) lakeFlattening;
        float terrainNoise = this.biome.getTerrain().generateNoise(simplex, cell, x, y, border, river);
        return this.erodedNoise(simplex, cell, x, y, river, border, terrainNoise, lakeFlattening);
    }

    private float lakePressure(OpenSimplexNoise simplex, CellNoise simplexCell, int x, int y, float border) {
        if (this.biome.noLakes) return 1f;
        SimplexOctave.Disk jitter = new SimplexOctave.Disk();
        simplex.riverJitter().evaluateNoise(x / 240.0, y / 240.0, jitter);
        double pX = x + jitter.deltax() * 90f;
        double pY = y + jitter.deltay() * 90f;
        simplex.mountain().evaluateNoise(x / 80.0, y / 80.0, jitter);
        pX += jitter.deltax() * 25f;
        pY += jitter.deltay() * 25f;
        simplex.mountain().evaluateNoise(x / 30.0, y / 30.0, jitter);
        pX += jitter.deltax() * 7f;
        pY += jitter.deltay() * 7f;
        //double results =simplexCell.river().noise(pX / lakeInterval, pY / lakeInterval,1.0);
        double[] lakeResults = simplexCell.river().eval((float) pX / this.biome.lakeInterval, (float) pY / this.biome.lakeInterval);
        float results = 1f - (float) ((lakeResults[1] - lakeResults[0]) / lakeResults[1]);
        if (results > 1.01) throw new RuntimeException("" + lakeResults[0] + " , " + lakeResults[1]);
        if (results < -.01) throw new RuntimeException("" + lakeResults[0] + " , " + lakeResults[1]);
        //return simplexCell.river().noise((float)x/ this.biome.lakeInterval, (float)y/ this.biome.lakeInterval,1.0);
        return results;
    }

    private double lakeFlattening(double pressure, double bottomLevel, double topLevel) {
        // this number indicates a multiplier to height
        if (pressure > topLevel) return 1;
        if (pressure < bottomLevel) return 0;
        return (pressure - bottomLevel) / (topLevel - bottomLevel);
    }

    public float erodedNoise(OpenSimplexNoise simplex, CellNoise simplexCell, int x, int y, float river, float border, float biomeHeight, double lakeFlattening) {

        float r = 1f;

        // put a flat spot in the middle of the river
        float riverFlattening = river * 1.25f - 0.25f;
        if (riverFlattening < 0) riverFlattening = 0;

        // check if rivers need lowering
        if (riverFlattening < actualRiverProportion) {
            r = riverFlattening / actualRiverProportion;
        }

        //if (1>0) return 62f+r*10f;
        if ((r < 1f && biomeHeight > 57f)) {
            return (biomeHeight * (r))
                    + ((57f + simplex.noise2(x / 12f, y / 12f) * 2f + simplex.noise2(x / 8f, y / 8f) * 1.5f) * (1f - r));
        } else {
            return biomeHeight;
        }
    }

    public void paintSurface(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {
        if (Mods.RTG.config.ENABLE_RTG_BIOME_DECORATIONS.get() && this.biome.config.USE_RTG_SURFACES.get()) {
            this.biome.paintSurface(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
        } else {
            this.surfaceGeneric.paintSurface(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
        }
    }

    public void decorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {
        for (DecoBase deco : this.biome.decos) {
            if (deco.preGenerate(this.biome, world, rand, chunkX, chunkY, simplex, cell, strength, river)) {
                deco.generate(this, world, rand, chunkX, chunkY, simplex, cell, strength, river);
            }
        }
    }

}
