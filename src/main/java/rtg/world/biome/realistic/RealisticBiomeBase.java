package rtg.world.biome.realistic;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CLAY;

import rtg.api.biome.BiomeConfig;
import rtg.config.rtg.ConfigRTG;
import rtg.util.*;
import rtg.world.biome.RTGBiomeProvider;
import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.collection.DecoCollectionBase;
import rtg.world.biome.deco.collection.DecoCollectionDesertRiver;
import rtg.world.gen.feature.WorldGenClayRTG;
import rtg.world.gen.feature.WorldGenVolcano;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceGeneric;
import rtg.world.gen.surface.SurfaceRiverOasis;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBase {

    private static final RealisticBiomeBase[] arrRealisticBiomeIds = new RealisticBiomeBase[BiomeGenBase.getBiomeGenArray().length];

    public final BiomeGenBase baseBiome;
    public final BiomeGenBase riverBiome;
    public BiomeConfig config;

    public TerrainBase terrain;

    public SurfaceBase[] surfaces;
    public int surfacesLength;
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
    public ArrayList<TreeRTG> rtgTrees;

    // lake calculations

    private float lakeInterval = 989.0f;
    private float lakeShoreLevel = 0.15f;
    private float lakeWaterLevel = 0.11f;// the lakeStrength below which things should be below water
    private float lakeDepressionLevel = 0.30f;// the lakeStrength below which land should start to be lowered
    public boolean noLakes = false;
    public boolean noWaterFeatures = false;

    private float largeBendSize = 100;
    private float mediumBendSize = 40;
    private float smallBendSize = 15;

    public boolean disallowStoneBeaches = false; // this is for rugged biomes that should have sand beaches
    public boolean disallowAllBeaches = false;

    public RealisticBiomeBase(BiomeConfig config, BiomeGenBase biome) {

        this(config, biome, BiomeGenBase.river);
    }

    public RealisticBiomeBase(BiomeConfig config, BiomeGenBase biome, BiomeGenBase river) {

        if (config == null) {
            throw new RuntimeException("Biome config cannot be NULL when instantiating a realistic biome.");
        }

        this.config = config;

        if (biome.biomeID == 160 && this instanceof rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaRedwoodTaigaHills) {

            arrRealisticBiomeIds[161] = this;

        }
        else {

            arrRealisticBiomeIds[biome.biomeID] = this;
        }

        baseBiome = biome;
        riverBiome = river;

        waterSurfaceLakeChance = 10;
        lavaSurfaceLakeChance = 0; // Disabled.

        waterUndergroundLakeChance = 1;
        lavaUndergroundLakeChance = 1;

        clayPerVein = 20;

        generateVillages = true;

        generatesEmeralds = false;
        emeraldEmeraldBlock = Blocks.emerald_ore;
        emeraldEmeraldMeta = (byte) 0;
        emeraldStoneBlock = Blocks.stone;
        emeraldStoneMeta = (byte) 0;

        decos = new ArrayList<DecoBase>();
        rtgTrees = new ArrayList<TreeRTG>();

        /**
         *  Disable base biome decorations by default.
         *  This also needs to be here so that ores get generated.
         */
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.allowed = false;
        this.addDeco(decoBaseBiomeDecorations);
        // set the water feature constants with the config changes
        lakeInterval *= ConfigRTG.lakeFrequencyMultiplier;
        this.lakeWaterLevel *= ConfigRTG.lakeSizeMultiplier();
        this.lakeShoreLevel *= ConfigRTG.lakeSizeMultiplier();
        this.lakeDepressionLevel *= ConfigRTG.lakeSizeMultiplier();

        this.largeBendSize *= ConfigRTG.lakeFrequencyMultiplier;
        this.mediumBendSize *= ConfigRTG.lakeFrequencyMultiplier;
        this.smallBendSize *= ConfigRTG.lakeFrequencyMultiplier;
    }

    public static RealisticBiomeBase getBiome(int id) {

        return arrRealisticBiomeIds[id];
    }

    public RealisticBiomeBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase[] s) {

        this(config, b, riverbiome);

        terrain = t;

        surfaces = s;
        surfacesLength = s.length;
    }

    public RealisticBiomeBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s) {

        this(config, b, riverbiome, t, new SurfaceBase[]{s});

        surfaceGeneric = new SurfaceGeneric(config, s.getTopBlock(), s.getFillerBlock());
    }

    public void rPopulatePreDecorate(IChunkProvider ichunkprovider, World worldObj, Random rand, int chunkX, int chunkZ, boolean villageBuilding) {

        int worldX = chunkX * 16;
        int worldZ = chunkZ * 16;
        boolean gen = true;

        gen = TerrainGen.populate(ichunkprovider, worldObj, rand, chunkX, chunkZ, villageBuilding, PopulateChunkEvent.Populate.EventType.LAKE);

        // Underground water lakes.
        if (ConfigRTG.enableWaterUndergroundLakes) {

            if (gen && (waterUndergroundLakeChance > 0)) {

                int i2 = worldX + rand.nextInt(16);// + 8;
                int l4 = RandomUtil.getRandomInt(rand, 1, 50);
                int i8 = worldZ + rand.nextInt(16);// + 8;

                if (rand.nextInt(waterUndergroundLakeChance) == 0 && (RandomUtil.getRandomInt(rand, 1, ConfigRTG.waterUndergroundLakeChance) == 1)) {

                    (new WorldGenLakes(Blocks.water)).generate(worldObj, rand, new BlockPos(new BlockPos(i2, l4, i8)));
                }
            }
        }

        // Surface water lakes.
        if (ConfigRTG.enableWaterSurfaceLakes && !villageBuilding) {

            if (gen && (waterSurfaceLakeChance > 0)) {

                int i2 = worldX + rand.nextInt(16);// + 8;
                int i8 = worldZ + rand.nextInt(16);// + 8;
                int l4 = worldObj.getHeight(new BlockPos(i2, 0, i8)).getY();

                //Surface lakes.
                if (rand.nextInt(waterSurfaceLakeChance) == 0 && (RandomUtil.getRandomInt(rand, 1, ConfigRTG.waterSurfaceLakeChance) == 1)) {

                    if (l4 > 63) {

                        (new WorldGenLakes(Blocks.water)).generate(worldObj, rand, new BlockPos(i2, l4, i8));
                    }
                }
            }
        }

        gen = TerrainGen.populate(ichunkprovider, worldObj, rand, chunkX, chunkZ, villageBuilding, PopulateChunkEvent.Populate.EventType.LAVA);

        // Underground lava lakes.
        if (ConfigRTG.enableLavaUndergroundLakes) {

            if (gen && (lavaUndergroundLakeChance > 0)) {

                int i2 = worldX + rand.nextInt(16);// + 8;
                int l4 = RandomUtil.getRandomInt(rand, 1, 50);
                int i8 = worldZ + rand.nextInt(16);// + 8;

                if (rand.nextInt(lavaUndergroundLakeChance) == 0 && (RandomUtil.getRandomInt(rand, 1, ConfigRTG.lavaUndergroundLakeChance) == 1)) {

                    (new WorldGenLakes(Blocks.lava)).generate(worldObj, rand, new BlockPos(i2, l4, i8));
                }
            }
        }

        // Surface lava lakes.
        if (ConfigRTG.enableLavaSurfaceLakes && !villageBuilding) {

            if (gen && (lavaSurfaceLakeChance > 0)) {

                int i2 = worldX + rand.nextInt(16);// + 8;
                int i8 = worldZ + rand.nextInt(16);// + 8;
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
            
            gen = TerrainGen.populate(ichunkprovider, worldObj, rand, chunkX, chunkZ, villageBuilding, PopulateChunkEvent.Populate.EventType.DUNGEON);
            
            if (gen) {
            	
	            for(int k1 = 0; k1 < ConfigRTG.dungeonFrequency; k1++) {
	            	
	                int j5 = worldX + rand.nextInt(16);// + 8;
	                int k8 = rand.nextInt(128);
	                int j11 = worldZ + rand.nextInt(16);// + 8;
	                
	                (new WorldGenDungeons()).generate(worldObj, rand, new BlockPos(j5, k8, j11));
	            }
            }
        }
    }

    public void rPopulatePostDecorate(IChunkProvider ichunkprovider, World worldObj, Random rand, int chunkX, int chunkZ, boolean flag) {
        /**
         * Has emerald gen been disabled in the configs?
         * If so, check to see if this biome generated emeralds & remove them if necessary.
         */
        if (!ConfigRTG.generateOreEmerald && generatesEmeralds) {
            rRemoveEmeralds(worldObj, rand, chunkX, chunkZ);
        }
    }

    /**
     * When manually decorating biomes, sometimes you want the biome to partially decorate itself.
     * That's what this method does... it calls the biome's decorate() method.
     */
    public void rDecorateSeedBiome(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, BiomeGenBase seedBiome) {

        if (strength > 0.3f) {
            seedBiome.decorate(world, rand, new BlockPos(chunkX, 0, chunkY));
        }
        else {
            rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, seedBiome);
        }
    }

    /**
     * This method should only be called by DecoBaseBiomeDecorations.
     */
    public void rOreGenSeedBiome(World world, Random rand, BlockPos blockPos, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, BiomeGenBase seedBiome) {

        if (seedBiome.theBiomeDecorator.chunkProviderSettings == null) {

            String s = world.getWorldInfo().getGeneratorOptions();

            if (s != null) {
                seedBiome.theBiomeDecorator.chunkProviderSettings = ChunkProviderSettings.Factory.jsonToFactory(s).func_177864_b();
            }
            else {
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
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.dirtGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIRT)) {
            genStandardOre1(world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.dirtCount, seedBiome.theBiomeDecorator.dirtGen, seedBiome.theBiomeDecorator.chunkProviderSettings.dirtMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.dirtMaxHeight);
        }
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.gravelGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GRAVEL)) {
            genStandardOre1(world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.gravelCount, seedBiome.theBiomeDecorator.gravelGen, seedBiome.theBiomeDecorator.chunkProviderSettings.gravelMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.gravelMaxHeight);
        }
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.dioriteGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIORITE)) {
            genStandardOre1(world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.dioriteCount, seedBiome.theBiomeDecorator.dioriteGen, seedBiome.theBiomeDecorator.chunkProviderSettings.dioriteMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.dioriteMaxHeight);
        }
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.graniteGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GRANITE)) {
            genStandardOre1(world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.graniteCount, seedBiome.theBiomeDecorator.graniteGen, seedBiome.theBiomeDecorator.chunkProviderSettings.graniteMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.graniteMaxHeight);
        }
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.andesiteGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.ANDESITE)) {
            genStandardOre1(world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.andesiteCount, seedBiome.theBiomeDecorator.andesiteGen, seedBiome.theBiomeDecorator.chunkProviderSettings.andesiteMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.andesiteMaxHeight);
        }
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.coalGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.COAL)) {
            genStandardOre1(world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.coalCount, seedBiome.theBiomeDecorator.coalGen, seedBiome.theBiomeDecorator.chunkProviderSettings.coalMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.coalMaxHeight);
        }
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.ironGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.IRON)) {
            genStandardOre1(world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.ironCount, seedBiome.theBiomeDecorator.ironGen, seedBiome.theBiomeDecorator.chunkProviderSettings.ironMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.ironMaxHeight);
        }
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.goldGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GOLD)) {
            genStandardOre1(world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.goldCount, seedBiome.theBiomeDecorator.goldGen, seedBiome.theBiomeDecorator.chunkProviderSettings.goldMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.goldMaxHeight);
        }
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.redstoneGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.REDSTONE)) {
            genStandardOre1(world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.redstoneCount, seedBiome.theBiomeDecorator.redstoneGen, seedBiome.theBiomeDecorator.chunkProviderSettings.redstoneMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.redstoneMaxHeight);
        }
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.diamondGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIAMOND)) {
            genStandardOre1(world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.diamondCount, seedBiome.theBiomeDecorator.diamondGen, seedBiome.theBiomeDecorator.chunkProviderSettings.diamondMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.diamondMaxHeight);
        }
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.lapisGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.LAPIS)) {
            genStandardOre2(world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.lapisCount, seedBiome.theBiomeDecorator.lapisGen, seedBiome.theBiomeDecorator.chunkProviderSettings.lapisCenterHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.lapisSpread);
        }

        // Emeralds.
        if (ConfigRTG.generateOreEmerald && generatesEmeralds) {
            rGenerateEmeralds(world, rand, blockPos);
        }

        net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(world, rand, blockPos));
    }

    public void rMapVolcanoes(ChunkPrimer primer, World world, RTGBiomeProvider cmr, Random mapRand, int baseX, int baseY, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float noise[]) {

        // Have volcanoes been disabled in the global config?
        if (!ConfigRTG.enableVolcanoes) {
            return;
        }

        // Have volcanoes been disabled in the biome config?
    	RealisticBiomeBase neighbourBiome = getBiome((((WorldChunkManagerRTG) cmr).getBiomeGenAt(baseX * 16, baseY * 16).biomeID));
        if (!neighbourBiome.config._boolean(BiomeConfig.allowVolcanoesId)) {
            return;
        }

        // Have volcanoes been disabled via frequency?
        // Use the global frequency unless the biome frequency has been explicitly set.
        int chance = neighbourBiome.config._int(BiomeConfig.volcanoChanceId) == -1 ? ConfigRTG.volcanoChance : neighbourBiome.config._int(BiomeConfig.volcanoChanceId);
        if (chance < 1) {
            return;
        }

        // If we've made it this far, let's go ahead and generate the volcano. Exciting!!! :D
        if (baseX % 4 == 0 && baseY % 4 == 0 && mapRand.nextInt(chance) == 0) {

            float river = cmr.getRiverStrength(baseX * 16, baseY * 16) + 1f;
            if (river > 0.98f && cmr.isBorderlessAt(baseX * 16, baseY * 16)) {
                long i1 = mapRand.nextLong() / 2L * 2L + 1L;
                long j1 = mapRand.nextLong() / 2L * 2L + 1L;
                mapRand.setSeed((long) chunkX * i1 + (long) chunkY * j1 ^ world.getSeed());

                WorldGenVolcano.build(primer, world, mapRand, baseX, baseY, chunkX, chunkY, simplex, cell, noise);
            }
        }
    }

    public void generateMapGen(ChunkPrimer primer, Long seed, World world, RTGBiomeProvider cmr, Random mapRand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float noise[]) {

        final int mapGenRadius = 5;
        final int volcanoGenRadius = 15;

        mapRand.setSeed(seed);
        long l = (mapRand.nextLong() / 2L) * 2L + 1L;
        long l1 = (mapRand.nextLong() / 2L) * 2L + 1L;

        // Structures generation
        for (int baseX = chunkX - mapGenRadius; baseX <= chunkX + mapGenRadius; baseX++) {
            for (int baseY = chunkY - mapGenRadius; baseY <= chunkY + mapGenRadius; baseY++) {
                mapRand.setSeed((long) baseX * l + (long) baseY * l1 ^ seed);
                rMapGen(primer, world, cmr, mapRand, baseX, baseY, chunkX, chunkY, simplex, cell, noise);
            }
        }

        // Volcanoes generation
        for (int baseX = chunkX - volcanoGenRadius; baseX <= chunkX + volcanoGenRadius; baseX++) {
            for (int baseY = chunkY - volcanoGenRadius; baseY <= chunkY + volcanoGenRadius; baseY++) {
                mapRand.setSeed((long) baseX * l + (long) baseY * l1 ^ seed);
                rMapVolcanoes(primer, world, cmr, mapRand, baseX, baseY, chunkX, chunkY, simplex, cell, noise);
            }
        }
    }

    public void rMapGen(ChunkPrimer primer, World world, RTGBiomeProvider cmr, Random mapRand, int chunkX, int chunkY, int baseX, int baseY, OpenSimplexNoise simplex, CellNoise cell, float noise[]) {

    }

    public float rNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        // we now have both lakes and rivers lowering land
        if (noWaterFeatures) {
            float borderForRiver = border*2;
            if (borderForRiver >1f) borderForRiver = 1;
            river = 1f - (1f-borderForRiver)*(1f-river);
            return terrain.generateNoise(simplex, cell, x, y, border, river);
        }
        float lakeStrength = lakePressure(simplex,cell,x,y,border);
        float lakeFlattening = (float)lakeFlattening(lakeStrength, lakeShoreLevel, lakeDepressionLevel);
        // we add some flattening to the rivers. The lakes are pre-flattened.
        float riverFlattening = river*1.25f-0.25f;
        if (riverFlattening <0) riverFlattening = 0;
        if ((river<1)&&(lakeFlattening<1)) {
            riverFlattening = (float)((1f-riverFlattening)/riverFlattening+(1f-lakeFlattening)/lakeFlattening);
            riverFlattening = (1f/(riverFlattening+1f));
        } else {
            if (lakeFlattening < riverFlattening) riverFlattening = (float)lakeFlattening;
        }
        // the lakes have to have a little less flattening to avoid the rocky edges
        lakeFlattening = lakeFlattening(lakeStrength, lakeWaterLevel, lakeDepressionLevel);

        if ((river<1)&&(lakeFlattening<1)) {
            river = (float)((1f-river)/river+(1f-lakeFlattening)/lakeFlattening);
            river = (1f/(river+1f));
        } else {
            if (lakeFlattening < river) river = (float)lakeFlattening;
        }
        // flatten terrain to set up for the water features
        float terrainNoise = terrain.generateNoise(simplex, cell, x, y, border, riverFlattening);
        // place water features
        return this.erodedNoise(simplex, cell, x, y, river, border, terrainNoise,lakeFlattening);
    }

    public static final float actualRiverProportion = 300f/1600f;
    public float erodedNoise(OpenSimplexNoise simplex, CellNoise simplexCell,int x, int y, float river, float border, float biomeHeight, double lakeFlattening)
    {

        float r = 1f;

        // put a flat spot in the middle of the river
        float riverFlattening = river; // moved the flattening to terrain stage
        if (riverFlattening <0) riverFlattening = 0;

        // check if rivers need lowering
        //if (riverFlattening < actualRiverProportion) {
            r = riverFlattening/actualRiverProportion;
        //}

        //if (1>0) return 62f+r*10f;
        if ((r < 1f && biomeHeight > 57f))
        {
            return (biomeHeight * (r))
                + ((57f + simplex.noise2(x / 12f, y / 12f) * 2f + simplex.noise2(x / 8f, y / 8f) * 1.5f) * (1f-r));
        }
        else
        {
            return biomeHeight;
        }
    }

    public float lakeFlattening(OpenSimplexNoise simplex, CellNoise simplexCell,int x, int y, float border) {
        return lakeFlattening(lakePressure(simplex, simplexCell, x, y, border), lakeWaterLevel, lakeDepressionLevel);
    }

    public float lakePressure(OpenSimplexNoise simplex, CellNoise simplexCell,int x, int y, float border) {
        if (noLakes) return 1f;
        SimplexOctave.Disk jitter = new SimplexOctave.Disk();
        simplex.riverJitter().evaluateNoise((float)x / 240.0, (float)y / 240.0, jitter);
        double pX = x + jitter.deltax() * largeBendSize;
        double pY = y + jitter.deltay() * largeBendSize;
        simplex.mountain().evaluateNoise((float)x / 80.0, (float)y / 80.0, jitter);
        pX += jitter.deltax() * mediumBendSize;
        pY += jitter.deltay() * mediumBendSize;
        simplex.octave(4).evaluateNoise((float)x / 30.0, (float)y / 30.0, jitter);
        pX += jitter.deltax() * smallBendSize;
        pY += jitter.deltay() * smallBendSize;
        //double results =simplexCell.river().noise(pX / lakeInterval, pY / lakeInterval,1.0);
        double [] lakeResults = simplexCell.river().eval((float)pX/ lakeInterval, (float)pY/ lakeInterval);
        float results = 1f-(float)((lakeResults[1]-lakeResults[0])/lakeResults[1]);
        if (results >1.01) throw new RuntimeException("" + lakeResults[0]+ " , "+lakeResults[1]);
        if (results<-.01) throw new RuntimeException("" + lakeResults[0]+ " , "+lakeResults[1]);
        //return simplexCell.river().noise((float)x/ lakeInterval, (float)y/ lakeInterval,1.0);
        return results;
    }

    public float lakeFlattening(float pressure, float bottomLevel, float topLevel) {
        // this number indicates a multiplier to height
        if (pressure > topLevel) return 1;
        if (pressure<bottomLevel) return 0;
        return (float)Math.pow((pressure-bottomLevel)/(topLevel-bottomLevel),1.0);
    }

    public void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {

        float riverRegion = this.noWaterFeatures ? 0f : river;

        if (ConfigRTG.enableRTGBiomeSurfaces && this.config._boolean(BiomeConfig.useRTGSurfacesId)) {

            for (int s = 0; s < surfacesLength; s++) {
                surfaces[s].paintTerrain(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, riverRegion, base);
            }
        }
        else {

            this.surfaceGeneric.paintTerrain(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, riverRegion, base);
        }
    }

    protected void rReplaceRiverSurface(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {

        float riverRegion = this.noWaterFeatures ? 0f : river;

        if (ConfigRTG.enableRTGBiomeSurfaces && this.config._boolean(BiomeConfig.useRTGSurfacesId)) {

            for (int s = 0; s < surfacesLength; s++) {
                surfaces[s].paintTerrain(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, riverRegion, base);
            }

            if (ConfigRTG.enableLushRiverBankSurfacesInHotBiomes) {

                SurfaceBase riverSurface = new SurfaceRiverOasis(this.config);
                riverSurface.paintTerrain(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, riverRegion, base);
            }
        }
        else {

            this.surfaceGeneric.paintTerrain(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, riverRegion, base);
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

                    (new WorldGenClayRTG(clayPerVein)).generate(worldObj, rand, new BlockPos(l5, i9, l11));
                }
            }
        }
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
        }
        else if (maxHeight == minHeight) {
            if (minHeight < 255) {
                ++maxHeight;
            }
            else {
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

    public void rGenerateEmeralds(World world, Random rand, BlockPos blockPos) {

        int k = 3 + rand.nextInt(6);
        BlockPos.MutableBlockPos mbp = new BlockPos.MutableBlockPos();

        for (int l = 0; l < k; ++l) {
            mbp.set(blockPos.getX() + rand.nextInt(16),
                rand.nextInt(28) + 4,
                blockPos.getZ() + rand.nextInt(16));

            if (world.getBlockState(mbp).getBlock().isReplaceableOreGen(world, mbp, BlockHelper.forBlock(emeraldStoneBlock))) {
                world.setBlockState(mbp, emeraldEmeraldBlock.getStateFromMeta(emeraldEmeraldMeta), 2);
            }
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
                    if (world.getBlockState(mbp.set(x, y, z)).getBlock().isReplaceableOreGen(world, mbp, BlockHelper.forBlock(emeraldEmeraldBlock))) {

                        world.setBlockState(mbp, emeraldStoneBlock.getStateFromMeta(emeraldStoneMeta), 2);
                    }
                }
            }
        }
    }

    public TerrainBase getTerrain() {

        return this.terrain;
    }

    public SurfaceBase getSurface() {

        if (this.surfacesLength == 0) {

            throw new RuntimeException(
                "No realistic surfaces found for " + this.baseBiome.biomeName + " (" + this.baseBiome.biomeID + ")."
            );
        }

        return this.surfaces[0];
    }

    public SurfaceBase[] getSurfaces() {

        return this.surfaces;
    }

    private class ChunkDecoration {
        PlaneLocation chunkLocation;
        DecoBase decoration;
        ChunkDecoration(PlaneLocation chunkLocation,DecoBase decoration) {
            this.chunkLocation = chunkLocation;
            this.decoration = decoration;
        }
    }

    public static ArrayList<ChunkDecoration> decoStack = new ArrayList<ChunkDecoration>();

    public void decorateInAnOrderlyFashion(World world, Random rand, int worldX, int worldY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, boolean hasPlacedVillageBlocks)
    {

    	for (int i = 0; i < this.decos.size(); i++) {
    	    decoStack.add(new ChunkDecoration(new PlaneLocation.Invariant(worldX,worldY),decos.get(i)));
            if (decoStack.size()>20) {
                String problem = "" ;
                for (ChunkDecoration inStack: decoStack) {
                    problem += "" + inStack.chunkLocation.toString() + " " + inStack.decoration.getClass().getSimpleName();
                }
                throw new RuntimeException(problem);
            }
    		if (this.decos.get(i).preGenerate(this, world, rand, worldX, worldY, simplex, cell, strength, river, hasPlacedVillageBlocks)) {

    			this.decos.get(i).generate(this, world, rand, worldX, worldY, simplex, cell, strength, river, hasPlacedVillageBlocks);
    		}
            decoStack.remove(decoStack.size()-1);
    	}
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
            if (!deco.properlyDefined()) {
                throw new RuntimeException(deco.toString());
            }

            if (deco instanceof DecoBaseBiomeDecorations) {

                for (int i = 0; i < this.decos.size(); i++) {

                    if (this.decos.get(i) instanceof DecoBaseBiomeDecorations) {

                        this.decos.remove(i);
                        break;
                    }
                }
            }

            this.decos.add(deco);
        }
    }

    /**
     * Convenience method for addDeco() where 'allowed' is assumed to be true.
     *
     * @param deco
     */
    public void addDeco(DecoBase deco) {

        if (!deco.properlyDefined()) {
            throw new RuntimeException(deco.toString());
        }
        this.addDeco(deco, true);
    }

    public void addDecoCollection(DecoCollectionBase decoCollection) {

        // Don't add the desert river deco collection if the user has disabled it.
        if (decoCollection instanceof DecoCollectionDesertRiver) {
            if (!ConfigRTG.enableLushRiverBankDecorationsInHotBiomes) {
                return;
            }
        }

        if (decoCollection.decos.size() > 0) {
            for (int i = 0; i < decoCollection.decos.size(); i++) {
                this.addDeco(decoCollection.decos.get(i));
            }
        }

        if (decoCollection.rtgTrees.size() > 0) {
            for (int i = 0; i < decoCollection.rtgTrees.size(); i++) {
                this.addTree(decoCollection.rtgTrees.get(i));
            }
        }
    }

    /**
     * Adds a tree to the list of RTG trees associated with this biome.
     * The 'allowed' parameter allows us to pass biome config booleans dynamically when configuring the trees in the biome.
     *
     * @param tree
     * @param allowed
     */
    public void addTree(TreeRTG tree, boolean allowed) {

        if (allowed) {

            // Set the sapling data for this tree before we add it to the list.
            tree.saplingBlock = SaplingUtil.getSaplingFromLeaves(tree.leavesBlock);

            this.rtgTrees.add(tree);
        }
    }

    /**
     * Convenience method for addTree() where 'allowed' is assumed to be true.
     *
     * @param tree
     */
    public void addTree(TreeRTG tree) {

        this.addTree(tree, true);
    }
}
