package rtg.world.biome.realistic;

import net.minecraft.block.Block;
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
import rtg.api.config.BiomeConfig;
import rtg.api.config.ConfigProperty;
import rtg.api.util.ISupportedMod;
import rtg.config.ConfigRTG;
import rtg.util.math.RandomUtil;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.util.noise.SimplexOctave;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeProviderRTG;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.feature.WorldGenClay;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceGeneric;
import rtg.world.gen.terrain.TerrainBase;

import java.util.ArrayList;
import java.util.Random;

import static net.minecraft.init.Biomes.river;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CLAY;

public abstract class RealisticBiomeBase extends BiomeBase {

    private static final RealisticBiomeBase[] arrRealisticBiomeIds = new RealisticBiomeBase[256];
    private static int[] incidences = new int[200];
    private static int references = 0;
    private static float actualRiverProportion = 300f / 1600f;
    public final BiomeGenBase baseBiome;
    public final BiomeGenBase riverBiome;
    public BiomeConfig config;
    public final ISupportedMod mod;
    public TerrainBase terrain;
    public SurfaceBase surface;
    public SurfaceBase surfaceGeneric;
    public int waterSurfaceLakeChance; //Lower = more frequent
    public int lavaSurfaceLakeChance; //Lower = more frequent
    public int waterUndergroundLakeChance; //Lower = more frequent
    public int lavaUndergroundLakeChance; //Lower = more frequent
    public int clayPerVein;
    public boolean generateVillages;
    public boolean generatesEmeralds;
    public Block emeraldEmeraldBlock;
    public byte emeraldEmeraldMeta;
    public Block emeraldStoneBlock;
    public byte emeraldStoneMeta;
    public ArrayList<DecoBase> decos;
    public boolean useNewDecorationSystem = false;
    public boolean noLakes = false;
    public boolean noWaterFeatures = false;
    private float lakeInterval = 1470.0f;
    private double lakeWaterLevel = 0.0;// the lakeStrenght below which things should be below ater
    private double lakeDepressionLevel = 0.16;// the lakeStrength below which land should start to be lowered

    public RealisticBiomeBase(ISupportedMod mod, BiomeGenBase biome) {

        this(mod, biome, river);
    }

    public RealisticBiomeBase(ISupportedMod mod, BiomeGenBase biome, BiomeGenBase river) {

        super(RealisticBiomeBase.getIdForBiome(biome));

        this.mod = mod;

        arrRealisticBiomeIds[RealisticBiomeBase.getIdForBiome(biome)] = this;

        this.config = mod.getConfig().setBiomeConfig(this.getClass(), initProperties());

        baseBiome = biome;
        riverBiome = river;

        waterSurfaceLakeChance = 10;
        lavaSurfaceLakeChance = 0; // Disabled.

        waterUndergroundLakeChance = 1;
        lavaUndergroundLakeChance = 1;

        clayPerVein = 20;

        generateVillages = config.get(BiomeConfigProperty.ALLOW_VILLAGES, true);

        generatesEmeralds = false;
        emeraldEmeraldBlock = Blocks.emerald_ore;
        emeraldEmeraldMeta = (byte) 0;
        emeraldStoneBlock = Blocks.stone;
        emeraldStoneMeta = (byte) 0;

        decos = new ArrayList<DecoBase>();

        /**
         * By default, it is assumed that all realistic biomes will be decorated manually and not by the biome.
         * This includes ore generation since it's part of the decoration process.
         * We're adding this deco here in order to avoid having to explicitly add it
         * in every singe realistic biome.
         * If it does getProp added manually to let the base biome handle some or all of the decoration process,
         * this deco will get replaced with the new one.
         */
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.allowed = false;
        this.decos.add(decoBaseBiomeDecorations);

        this.config = mod.getConfig().setBiomeConfig(this.getClass(), initProperties());
        this.surface = initSurface();
        surfaceGeneric = new SurfaceGeneric(config, surface.getTopBlock(), surface.getFillerBlock());
        this.terrain = initTerrain();
    }

    protected abstract SurfaceBase initSurface();
    protected abstract TerrainBase initTerrain();

    public static RealisticBiomeBase getBiome(int id) {

        return arrRealisticBiomeIds[id];
    }

    public static int getIdForBiome(BiomeGenBase biome) {
        if (biome instanceof RealisticBiomeBase)
            return BiomeGenBase.getIdForBiome(((RealisticBiomeBase) biome).baseBiome);
        return BiomeGenBase.getIdForBiome(biome);
    }

    public void rPopulatePreDecorate(IChunkGenerator ichunkprovider, World worldObj, Random rand, int chunkX, int chunkZ, boolean flag) {
        int worldX = chunkX * 16;
        int worldZ = chunkZ * 16;
        boolean gen = true;

        gen = TerrainGen.populate(ichunkprovider, worldObj, rand, chunkX, chunkZ, flag, PopulateChunkEvent.Populate.EventType.LAKE);

        // Underground water lakes.
        if (ConfigRTG.enableWaterUndergroundLakes) {

            if (gen && (waterUndergroundLakeChance > 0)) {

                int i2 = worldX + rand.nextInt(16) + 8;
                int l4 = RandomUtil.getRandomInt(rand, 1, 50);
                int i8 = worldZ + rand.nextInt(16) + 8;

                if (rand.nextInt(waterUndergroundLakeChance) == 0 && (RandomUtil.getRandomInt(rand, 1, ConfigRTG.waterUndergroundLakeChance) == 1)) {

                    (new WorldGenLakes(Blocks.water)).generate(worldObj, rand, new BlockPos(new BlockPos(i2, l4, i8)));
                }
            }
        }

        // Surface water lakes.
        if (ConfigRTG.enableWaterSurfaceLakes) {

            if (gen && (waterSurfaceLakeChance > 0)) {

                int i2 = worldX + rand.nextInt(16) + 8;
                int i8 = worldZ + rand.nextInt(16) + 8;
                int l4 = worldObj.getHeight(new BlockPos(i2, 0, i8)).getY();

                //Surface lakes.
                if (rand.nextInt(waterSurfaceLakeChance) == 0 && (RandomUtil.getRandomInt(rand, 1, ConfigRTG.waterSurfaceLakeChance) == 1)) {

                    if (l4 > 63) {

                        (new WorldGenLakes(Blocks.water)).generate(worldObj, rand, new BlockPos(i2, l4, i8));
                    }
                }
            }
        }

        gen = TerrainGen.populate(ichunkprovider, worldObj, rand, chunkX, chunkZ, flag, PopulateChunkEvent.Populate.EventType.LAVA);

        // Underground lava lakes.
        if (ConfigRTG.enableLavaUndergroundLakes) {

            if (gen && (lavaUndergroundLakeChance > 0)) {

                int i2 = worldX + rand.nextInt(16) + 8;
                int l4 = RandomUtil.getRandomInt(rand, 1, 50);
                int i8 = worldZ + rand.nextInt(16) + 8;

                if (rand.nextInt(lavaUndergroundLakeChance) == 0 && (RandomUtil.getRandomInt(rand, 1, ConfigRTG.lavaUndergroundLakeChance) == 1)) {

                    (new WorldGenLakes(Blocks.lava)).generate(worldObj, rand, new BlockPos(i2, l4, i8));
                }
            }
        }

        // Surface lava lakes.
        if (ConfigRTG.enableLavaSurfaceLakes) {

            if (gen && (lavaSurfaceLakeChance > 0)) {

                int i2 = worldX + rand.nextInt(16) + 8;
                int i8 = worldZ + rand.nextInt(16) + 8;
                int l4 = worldObj.getHeight(new BlockPos(i2, 0, i8)).getY();

                //Surface lakes.
                if (rand.nextInt(lavaSurfaceLakeChance) == 0 && (RandomUtil.getRandomInt(rand, 1, ConfigRTG.lavaSurfaceLakeChance) == 1)) {

                    if (l4 > 63) {

                        (new WorldGenLakes(Blocks.lava)).generate(worldObj, rand, new BlockPos(i2, l4, i8));
                    }
                }
            }
        }

        if (ConfigRTG.generateDungeons) {

            gen = TerrainGen.populate(ichunkprovider, worldObj, rand, chunkX, chunkZ, flag, PopulateChunkEvent.Populate.EventType.DUNGEON);
            for (int k1 = 0; k1 < 8 && gen; k1++) {
                int j5 = worldX + rand.nextInt(16) + 8;
                int k8 = rand.nextInt(128);
                int j11 = worldZ + rand.nextInt(16) + 8;

                (new WorldGenDungeons()).generate(worldObj, rand, new BlockPos(j5, k8, j11));
            }
        }
    }

    @Deprecated
    public void rDecorate(World world, Random rand, BlockPos blockPos, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {
        this.rDecorate(world, rand, blockPos.getX(), blockPos.getZ(), simplex, cell, strength, river);
    }

    @Deprecated
    public void rDecorate(World world, Random rand, int x, int z, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {

        if (strength > 0.3f) {
            baseBiome.decorate(world, rand, new BlockPos(x, 0, z));
        } else {
            rOreGenSeedBiome(world, rand, new BlockPos(x, 0, z), simplex, cell, strength, river, baseBiome);
        }
    }

    /**
     * This method should be called if both of the following conditions are true:
     * <p/>
     * 1) You are manually decorating a biome by overrding rDecorate().
     * 2) You are NOT calling rDecorateSeedBiome() within rDecorate().
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
        if (ConfigRTG.generateOreEmerald && generatesEmeralds) {
            rGenerateEmeralds(world, rand, blockPos);
        }

        net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(world, rand, blockPos));
    }

    /**
     * Standard ore generation helper. Generates most ores.
     *
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
     *
     * @see net.minecraft.world.biome.BiomeDecorator
     */
    protected void genStandardOre2(World worldObj, Random rand, BlockPos blockPos, int blockCount, WorldGenerator generator, int centerHeight, int spread) {
        for (int i = 0; i < blockCount; ++i) {
            BlockPos blockpos = blockPos.add(rand.nextInt(16), rand.nextInt(spread) + rand.nextInt(spread) + centerHeight - spread, rand.nextInt(16));
            generator.generate(worldObj, rand, blockpos);
        }
    }

    // lake calculations

    public void rGenerateEmeralds(World world, Random rand, BlockPos blockPos) {
        int k = 3 + rand.nextInt(6);
        BlockPos.MutableBlockPos mbp = new BlockPos.MutableBlockPos();

        for (int l = 0; l < k; ++l) {
            mbp.set(blockPos.getX() + rand.nextInt(16),
                    rand.nextInt(28) + 4,
                    blockPos.getZ() + rand.nextInt(16));

            //TODO: How to get that last argument???
            if (world.getBlockState(mbp).getBlock().isReplaceableOreGen(world.getBlockState(mbp), world, mbp, BlockMatcher.forBlock(emeraldStoneBlock))) {
                world.setBlockState(mbp, emeraldEmeraldBlock.getStateFromMeta(emeraldEmeraldMeta), 2);
            }
        }
    }

    public void rPopulatePostDecorate(IChunkGenerator ichunkprovider, World worldObj, Random rand, int chunkX, int chunkZ, boolean flag) {
        /**
         * Has emerald gen been disabled in the configs?
         * If so, check to see if this biome generated emeralds & remove them if necessary.
         */
        if (!ConfigRTG.generateOreEmerald && generatesEmeralds) {
            rRemoveEmeralds(worldObj, rand, chunkX, chunkZ);
        }
    }

    public void rRemoveEmeralds(World world, Random rand, int chunkX, int chunkZ) {
        int endX = (chunkX * 16) + 16;
        int endZ = (chunkZ * 16) + 16;

        // Get the highest possible existing block location.
        int maxY = world.getHeight(new BlockPos(chunkX, 0, chunkZ)).getY();
        BlockPos.MutableBlockPos mbp = new BlockPos.MutableBlockPos();
        for (int x = chunkX * 16; x < endX; ++x) {
            for (int z = chunkZ * 16; z < endZ; ++z) {
                for (int y = 0; y < maxY; ++y) {
                    if (world.getBlockState(mbp.set(x, y, z)).getBlock().isReplaceableOreGen(world.getBlockState(mbp), world, mbp, BlockMatcher.forBlock(emeraldEmeraldBlock))) {

                        world.setBlockState(mbp, emeraldStoneBlock.getStateFromMeta(emeraldStoneMeta), 2);
                    }
                }
            }
        }
    }

    /**
     * When manually decorating biomes by overriding rDecorate(), sometimes you want the biome
     * to partially decorate itself. That's what this method does... it calls the biome's decorate() method.
     */
    public void rDecorateSeedBiome(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, BiomeGenBase seedBiome) {

        if (strength > 0.3f) {
            seedBiome.decorate(world, rand, new BlockPos(chunkX, 0, chunkY));
        } else {
            rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, seedBiome);
        }
    }

    public void generateMapGen(ChunkPrimer primer, Long seed, World world, BiomeProviderRTG cmr, Random mapRand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float noise[]) {

        int k = 5;
        mapRand.setSeed(seed);
        long l = (mapRand.nextLong() / 2L) * 2L + 1L;
        long l1 = (mapRand.nextLong() / 2L) * 2L + 1L;
        for (int baseX = chunkX - k; baseX <= chunkX + k; baseX++) {
            for (int baseY = chunkY - k; baseY <= chunkY + k; baseY++) {
                mapRand.setSeed((long) baseX * l + (long) baseY * l1 ^ seed);
                rMapGen(primer, world, cmr, mapRand, baseX, baseY, chunkX, chunkY, simplex, cell, noise);
            }
        }
    }

    public void rMapGen(ChunkPrimer primer, World world, BiomeProviderRTG cmr, Random mapRand, int chunkX, int chunkY, int baseX, int baseY, OpenSimplexNoise simplex, CellNoise cell, float noise[]) {

    }

    private static double cellBorder(double[] results, double width, double depth) {
        double c = (results[1] - results[0]);
        //int slot = (int)Math.floor(c*100.0);
        //incidences[slot] += 1;
        //references ++;
        if (references > 40000) {
            String result = "";
            for (int i = 0; i < 100; i++) {
                result += " " + incidences[i];
            }
            throw new RuntimeException(result);
        }
        if (c < width) {
            return ((c / width) - 1f) * depth;
        } else {

            return 0;
        }
    }

    public float rNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        // we now have both lakes and rivers lowering land
        /*for (int testX =0 ;testX<100; testX++) {
            for (int testZ =0 ;testZ<100; testZ++) {
                int slot = (int)Math.floor(
                        getRiverStrength(simplex,cell,4000+testX*10,4000+testZ*10)*100.0+100.0);
                incidences[slot] += 1;
                references ++;
            }
        }
        if (references>1000) {
            String result = "";
            for (int i = 0; i< 200; i ++) {
                result += " " + incidences[i];
            }
            throw new RuntimeException(result);
        }*/
        if (noWaterFeatures) {
            border = border * 2;
            if (border > 1f) border = 1;
            river = 1f - border * (1f - river);
            return terrain.generateNoise(simplex, cell, x, y, border, river);
        }
        float lakeStrength = lakePressure(simplex, cell, x, y, border);
        double lakeFlattening = this.lakeFlattening(lakeStrength, lakeWaterLevel, lakeDepressionLevel);
        // we add some flattening to the rivers but not to the lakes. This gives the rivers flatter
        // banks and the lakes steeper ones, which seems to be better aesthetically
        float riverFlattening = river * 1.25f - 0.25f;
        if (riverFlattening < 0) riverFlattening = 0;
        if (lakeFlattening < river) river = (float) lakeFlattening;
        float terrainNoise = terrain.generateNoise(simplex, cell, x, y, border, river);
        return this.erodedNoise(simplex, cell, x, y, river, border, terrainNoise, lakeFlattening);
    }

    public float lakePressure(OpenSimplexNoise simplex, CellNoise simplexCell, int x, int y, float border) {
        if (noLakes) return 1f;
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
        double[] lakeResults = simplexCell.river().eval((float) pX / lakeInterval, (float) pY / lakeInterval);
        float results = 1f - (float) ((lakeResults[1] - lakeResults[0]) / lakeResults[1]);
        if (results > 1.01) throw new RuntimeException("" + lakeResults[0] + " , " + lakeResults[1]);
        if (results < -.01) throw new RuntimeException("" + lakeResults[0] + " , " + lakeResults[1]);
        //return simplexCell.river().noise((float)x/ lakeInterval, (float)y/ lakeInterval,1.0);
        return results;
    }

    public double lakeFlattening(double pressure, double bottomLevel, double topLevel) {
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

    public void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {

        if (ConfigRTG.ENABLE_RTG_BIOME_DECORATIONS._bool() && this.config._boolean(BiomeConfigProperty.USE_RTG_SURFACES)) {

            surface.paintTerrain(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);

        } else {

            this.surfaceGeneric.paintTerrain(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
        }
    }

    public float r3Dnoise(float z) {

        return 0f;
    }

    public void rDecorateClay(World worldObj, Random rand, int chunkX, int chunkZ, float river, int worldX, int worldZ) {
        if (TerrainGen.decorate(worldObj, rand, new BlockPos(chunkX, 0, chunkZ), CLAY)) {

            if (river > 0.85f) {

                for (int j2 = 0; j2 < 3; j2++) {

                    int l5 = worldX + rand.nextInt(16);
                    int i9 = 53 + rand.nextInt(15);
                    int l11 = worldZ + rand.nextInt(16);

                    (new WorldGenClay(Blocks.clay, 0, clayPerVein)).generate(worldObj, rand, new BlockPos(l5, i9, l11));
                }
            }
        }
    }

    public TerrainBase getTerrain() {
        return this.terrain;
    }

    public SurfaceBase getSurface() {
        return this.surface;
    }

    public int getId() {
        return RealisticBiomeBase.getIdForBiome(this);
    }

    public void decorateInAnOrderlyFashion(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {
        for (DecoBase deco : this.decos) {

            if (deco.preGenerate(this, world, rand, chunkX, chunkY, simplex, cell, strength, river)) {

                deco.generate(this, world, rand, chunkX, chunkY, simplex, cell, strength, river);
            }
        }
    }

    /**
     * Convenience method for addDeco() where 'allowed' is assumed to be true.
     *
     * @param deco
     */
    public void addDeco(DecoBase deco) {
        this.addDeco(deco, true);
    }

    /**
     * Adds a deco object to the list of biome decos.
     * The 'allowed' parameter allows us to pass biome config booleans dynamically when configuring the decos in the biome.
     *
     * @param deco
     * @param allowed
     */
    public void addDeco(DecoBase deco, boolean allowed) {
        if (allowed) {

            if (deco instanceof DecoBaseBiomeDecorations) {

                for (int i = 0; i < this.decos.size(); i++) {

                    if (this.decos.get(i) instanceof DecoBaseBiomeDecorations) {

                        this.decos.remove(i);
                        break;
                    }
                }
            }

            this.decos.add(deco);
            this.useNewDecorationSystem = true;
        }
    }

    /**
     * This is how you choose which properties the biome uses.
     * Should be overridden if a biome uses anything other than these defaults
     * Is called from the constructor
     * @return An array of ConfigProperties with defaults
     */
    public ConfigProperty[] initProperties() {
        return new ConfigProperty[0];
    }
}
