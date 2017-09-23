package rtg.api.world.biome;

import java.util.Random;

import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.feature.*;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import rtg.api.RTGAPI;
import rtg.api.config.RTGConfig;
import rtg.api.util.RandomUtil;
import rtg.api.util.noise.CellNoise;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.gen.GenSettingsRepo;
import rtg.api.world.gen.feature.WorldGenPond;
import rtg.world.gen.ChunkProviderSettingsRTG;

public class BiomeDecoratorRTG
{
    public BlockPos pos;
    public IRealisticBiome rbb;
// TODO: [Generator Settings] To be removed, no longer needed
      public Biome biome;
//    public WorldGenerator dirtGen;
//    public WorldGenerator gravelGen;
//    public WorldGenerator graniteGen;
//    public WorldGenerator dioriteGen;
//    public WorldGenerator andesiteGen;
//    public WorldGenerator coalGen;
//    public WorldGenerator ironGen;
//    public WorldGenerator goldGen;
//    public WorldGenerator redstoneGen;
//    public WorldGenerator diamondGen;
//    public WorldGenerator lapisGen;
// TODO: [Generator Settings] Revert back to the same as vanilla (Replace fields below with fields above)
    public int dirtSize = -1;
    public int gravelSize = -1;
    public int graniteSize = -1;
    public int dioriteSize = -1;
    public int andesiteSize = -1;
    public int coalSize = -1;
    public int ironSize = -1;
    public int goldSize = -1;
    public int redstoneSize = -1;
    public int diamondSize = -1;
    public int lapisSize = -1;

    private RTGConfig rtgConfig;

    public BiomeDecoratorRTG(IRealisticBiome rbb, Biome baseBiome) {

        this.rbb = rbb;
// TODO: [Generator Settings] To be removed, no longer needed
        this.biome = baseBiome;
        this.rtgConfig = RTGAPI.config();
    }

    /*
     * This method should only be called by ChunkProviderRTG#generateOres.
     */
    public void decorateOres(World worldIn, Random random, int worldX, int worldZ) {

        //Logger.debug("Started generating ores in %s (%d %d)", this.biome.getBiomeName(), worldX, worldZ);
        ChunkProviderSettingsRTG settings = GenSettingsRepo.getSettingsForWorld(worldIn);

// TODO: [Generator Settings] To be removed, no longer needed
        BiomeDecorator biomeDecorator = biome.theBiomeDecorator;

// TODO: [Generator Settings] To be removed, no longer needed...
        if (biomeDecorator.chunkProviderSettings == null) {

            String generatorOptions = worldIn.getWorldInfo().getGeneratorOptions();
            generatorOptions = (generatorOptions != null) ? generatorOptions : "";

            biomeDecorator.chunkProviderSettings = ChunkProviderSettings.Factory.jsonToFactory(generatorOptions).build();
        }

        // This local variable has to be declared/initialised AFTER the chunk provider settings have been built.
        ChunkProviderSettings chunkProviderSettings = biomeDecorator.chunkProviderSettings;
/// ...

        pos = new BlockPos(worldX, 0, worldZ);

// TODO: [Clean-up][API] Explore ways of executing ore gen that doesn't involve creating a new instance of WorldGenMinable for every ore for every chunk generated
// TODO: [Generator Settings] Update these to use the proper generator settings
//        this.dirtGen     = new WorldGenMinable(Blocks.DIRT.getDefaultState(), settings.dirtSize);
//        this.gravelGen   = new WorldGenMinable(Blocks.GRAVEL.getDefaultState(), settings.gravelSize);
//        this.graniteGen  = new WorldGenMinable(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE), settings.graniteSize);
//        this.dioriteGen  = new WorldGenMinable(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE), settings.dioriteSize);
//        this.andesiteGen = new WorldGenMinable(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE), settings.andesiteSize);
//        this.coalGen     = new WorldGenMinable(Blocks.COAL_ORE.getDefaultState(), settings.coalSize);
//        this.ironGen     = new WorldGenMinable(Blocks.IRON_ORE.getDefaultState(), settings.ironSize);
//        this.goldGen     = new WorldGenMinable(Blocks.GOLD_ORE.getDefaultState(), settings.goldSize);
//        this.redstoneGen = new WorldGenMinable(Blocks.REDSTONE_ORE.getDefaultState(), settings.redstoneSize);
//        this.diamondGen  = new WorldGenMinable(Blocks.DIAMOND_ORE.getDefaultState(), settings.diamondSize);
//        this.lapisGen    = new WorldGenMinable(Blocks.LAPIS_ORE.getDefaultState(), settings.lapisSize);
        biomeDecorator.dirtGen = new WorldGenMinable(Blocks.DIRT.getDefaultState(), getSize(chunkProviderSettings.dirtSize, dirtSize));
        biomeDecorator.gravelGen = new WorldGenMinable(Blocks.GRAVEL.getDefaultState(), getSize(chunkProviderSettings.gravelSize, gravelSize));
        biomeDecorator.graniteGen = new WorldGenMinable(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE), getSize(chunkProviderSettings.graniteSize, graniteSize));
        biomeDecorator.dioriteGen = new WorldGenMinable(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE), getSize(chunkProviderSettings.dioriteSize, dioriteSize));
        biomeDecorator.andesiteGen = new WorldGenMinable(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE), getSize(chunkProviderSettings.andesiteSize, andesiteSize));
        biomeDecorator.coalGen = new WorldGenMinable(Blocks.COAL_ORE.getDefaultState(), getSize(chunkProviderSettings.coalSize, coalSize));
        biomeDecorator.ironGen = new WorldGenMinable(Blocks.IRON_ORE.getDefaultState(), getSize(chunkProviderSettings.ironSize, ironSize));
        biomeDecorator.goldGen = new WorldGenMinable(Blocks.GOLD_ORE.getDefaultState(), getSize(chunkProviderSettings.goldSize, goldSize));
        biomeDecorator.redstoneGen = new WorldGenMinable(Blocks.REDSTONE_ORE.getDefaultState(), getSize(chunkProviderSettings.redstoneSize, redstoneSize));
        biomeDecorator.diamondGen = new WorldGenMinable(Blocks.DIAMOND_ORE.getDefaultState(), getSize(chunkProviderSettings.diamondSize, diamondSize));
        biomeDecorator.lapisGen = new WorldGenMinable(Blocks.LAPIS_ORE.getDefaultState(), getSize(chunkProviderSettings.lapisSize, lapisSize));

        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(worldIn, random, pos));
// TODO: [Generator Settings] Update these to use the proper generator settings / Revert back to the same as vanilla
//        if (TerrainGen.generateOre(worldIn, random, this.dirtGen, pos, OreGenEvent.GenerateMinable.EventType.DIRT)) {
//            this.genStandardOre1(worldIn, random, this.settings.dirtCount, this.dirtGen, this.settings.dirtMinHeight, this.settings.dirtMaxHeight);
//        }
//        if (TerrainGen.generateOre(worldIn, random, this.gravelGen, pos, OreGenEvent.GenerateMinable.EventType.GRAVEL)) {
//            this.genStandardOre1(worldIn, random, this.settings.gravelCount, this.gravelGen, this.settings.gravelMinHeight, this.settings.gravelMaxHeight);
//        }
//        if (TerrainGen.generateOre(worldIn, random, this.dioriteGen, pos, OreGenEvent.GenerateMinable.EventType.DIORITE)) {
//            this.genStandardOre1(worldIn, random, this.settings.dioriteCount, this.dioriteGen, this.settings.dioriteMinHeight, this.settings.dioriteMaxHeight);
//        }
//        if (TerrainGen.generateOre(worldIn, random, this.graniteGen, pos, OreGenEvent.GenerateMinable.EventType.GRANITE)) {
//            this.genStandardOre1(worldIn, random, this.settings.graniteCount, this.graniteGen, this.settings.graniteMinHeight, this.settings.graniteMaxHeight);
//        }
//        if (TerrainGen.generateOre(worldIn, random, this.andesiteGen, pos, OreGenEvent.GenerateMinable.EventType.ANDESITE)) {
//            this.genStandardOre1(worldIn, random, this.settings.andesiteCount, this.andesiteGen, this.settings.andesiteMinHeight, this.settings.andesiteMaxHeight);
//        }
//        if (TerrainGen.generateOre(worldIn, random, this.coalGen, pos, OreGenEvent.GenerateMinable.EventType.COAL)) {
//            this.genStandardOre1(worldIn, random, this.settings.coalCount, this.coalGen, this.settings.coalMinHeight, this.settings.coalMaxHeight);
//        }
//        if (TerrainGen.generateOre(worldIn, random, this.ironGen, pos, OreGenEvent.GenerateMinable.EventType.IRON)) {
//            this.genStandardOre1(worldIn, random, this.settings.ironCount, this.ironGen, this.settings.ironMinHeight, this.settings.ironMaxHeight);
//        }
//        if (TerrainGen.generateOre(worldIn, random, this.goldGen, pos, OreGenEvent.GenerateMinable.EventType.GOLD)) {
//            this.genStandardOre1(worldIn, random, this.settings.goldCount, this.goldGen, this.settings.goldMinHeight, this.settings.goldMaxHeight);
//        }
//        if (TerrainGen.generateOre(worldIn, random, this.redstoneGen, pos, OreGenEvent.GenerateMinable.EventType.REDSTONE)) {
//            this.genStandardOre1(worldIn, random, this.settings.redstoneCount, this.redstoneGen, this.settings.redstoneMinHeight, this.settings.redstoneMaxHeight);
//        }
//        if (TerrainGen.generateOre(worldIn, random, this.diamondGen, pos, OreGenEvent.GenerateMinable.EventType.DIAMOND)) {
//            this.genStandardOre1(worldIn, random, this.settings.diamondCount, this.diamondGen, this.settings.diamondMinHeight, this.settings.diamondMaxHeight);
//        }
//        if (TerrainGen.generateOre(worldIn, random, this.lapisGen, pos, OreGenEvent.GenerateMinable.EventType.LAPIS)) {
//            this.genStandardOre2(worldIn, random, this.settings.lapisCount, this.lapisGen, this.settings.lapisCenterHeight, this.settings.lapisSpread);
//        }
        if (TerrainGen.generateOre(worldIn, random, biomeDecorator.dirtGen, pos, OreGenEvent.GenerateMinable.EventType.DIRT)) {
            this.genStandardOre1(worldIn, random, chunkProviderSettings.dirtCount, biomeDecorator.dirtGen, chunkProviderSettings.dirtMinHeight, chunkProviderSettings.dirtMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random, biomeDecorator.gravelGen, pos, OreGenEvent.GenerateMinable.EventType.GRAVEL)) {
            this.genStandardOre1(worldIn, random, chunkProviderSettings.gravelCount, biomeDecorator.gravelGen, chunkProviderSettings.gravelMinHeight, chunkProviderSettings.gravelMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random, biomeDecorator.dioriteGen, pos, OreGenEvent.GenerateMinable.EventType.DIORITE)) {
            this.genStandardOre1(worldIn, random, chunkProviderSettings.dioriteCount, biomeDecorator.dioriteGen, chunkProviderSettings.dioriteMinHeight, chunkProviderSettings.dioriteMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random, biomeDecorator.graniteGen, pos, OreGenEvent.GenerateMinable.EventType.GRANITE)) {
            this.genStandardOre1(worldIn, random, chunkProviderSettings.graniteCount, biomeDecorator.graniteGen, chunkProviderSettings.graniteMinHeight, chunkProviderSettings.graniteMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random, biomeDecorator.andesiteGen, pos, OreGenEvent.GenerateMinable.EventType.ANDESITE)) {
            this.genStandardOre1(worldIn, random, chunkProviderSettings.andesiteCount, biomeDecorator.andesiteGen, chunkProviderSettings.andesiteMinHeight, chunkProviderSettings.andesiteMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random, biomeDecorator.coalGen, pos, OreGenEvent.GenerateMinable.EventType.COAL)) {
            this.genStandardOre1(worldIn, random, chunkProviderSettings.coalCount, biomeDecorator.coalGen, chunkProviderSettings.coalMinHeight, chunkProviderSettings.coalMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random, biomeDecorator.ironGen, pos, OreGenEvent.GenerateMinable.EventType.IRON)) {
            this.genStandardOre1(worldIn, random, chunkProviderSettings.ironCount, biomeDecorator.ironGen, chunkProviderSettings.ironMinHeight, chunkProviderSettings.ironMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random, biomeDecorator.goldGen, pos, OreGenEvent.GenerateMinable.EventType.GOLD)) {
            this.genStandardOre1(worldIn, random, chunkProviderSettings.goldCount, biomeDecorator.goldGen, chunkProviderSettings.goldMinHeight, chunkProviderSettings.goldMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random, biomeDecorator.redstoneGen, pos, OreGenEvent.GenerateMinable.EventType.REDSTONE)) {
            this.genStandardOre1(worldIn, random, chunkProviderSettings.redstoneCount, biomeDecorator.redstoneGen, chunkProviderSettings.redstoneMinHeight, chunkProviderSettings.redstoneMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random, biomeDecorator.diamondGen, pos, OreGenEvent.GenerateMinable.EventType.DIAMOND)) {
            this.genStandardOre1(worldIn, random, chunkProviderSettings.diamondCount, biomeDecorator.diamondGen, chunkProviderSettings.diamondMinHeight, chunkProviderSettings.diamondMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random, biomeDecorator.lapisGen, pos, OreGenEvent.GenerateMinable.EventType.LAPIS)) {
            this.genStandardOre2(worldIn, random, chunkProviderSettings.lapisCount, biomeDecorator.lapisGen, chunkProviderSettings.lapisCenterHeight, chunkProviderSettings.lapisSpread);
        }
        if (rbb.generatesEmeralds()) {
            this.genEmeraldOre(worldIn, random, pos);
        }
        if (rbb.generatesSilverfish()) {
            this.genSilverfishOre(worldIn, random, pos);
        }
        if (rbb.getExtraGoldGenCount() > 0) {
// TODO: [Generator Settings] Update these to use the proper generator settings
//          this.genStandardOre1(worldIn, random, rbb.getExtraGoldGenCount(), this.goldGen, rbb.getExtraGoldGenMinHeight(), rbb.getExtraGoldGenMaxHeight());
            this.genStandardOre1(worldIn, random, rbb.getExtraGoldGenCount(), biomeDecorator.goldGen, rbb.getExtraGoldGenMinHeight(), rbb.getExtraGoldGenMaxHeight());
        }
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(worldIn, random, pos));

        //Logger.debug("Finished generating ores in %s (%d %d)", this.biome.getBiomeName(), worldX, worldZ);
    }

    public void genStandardOre1(World worldIn, Random random, int blockCount, WorldGenerator generator, int minHeight, int maxHeight)
    {
        if (maxHeight < minHeight)
        {
            int i = minHeight;
            minHeight = maxHeight;
            maxHeight = i;
        }
        else if (maxHeight == minHeight)
        {
            if (minHeight < 255)
            {
                ++maxHeight;
            }
            else
            {
                --minHeight;
            }
        }

        for (int j = 0; j < blockCount; ++j)
        {
            BlockPos blockpos = this.pos.add(random.nextInt(16), random.nextInt(maxHeight - minHeight) + minHeight, random.nextInt(16));
            generator.generate(worldIn, random, blockpos);
        }
    }

    public void genStandardOre2(World worldIn, Random random, int blockCount, WorldGenerator generator, int centerHeight, int spread)
    {
        for (int i = 0; i < blockCount; ++i)
        {
            BlockPos blockpos = this.pos.add(random.nextInt(16), random.nextInt(spread) + random.nextInt(spread) + centerHeight - spread, random.nextInt(16));
            generator.generate(worldIn, random, blockpos);
        }
    }

    /**
     * @see net.minecraft.world.biome.BiomeHills
     */
    public void genEmeraldOre(World worldIn, Random rand, BlockPos pos) {
        WorldGenerator emeralds = new EmeraldGenerator();
        if (TerrainGen.generateOre(worldIn, rand, emeralds, pos, OreGenEvent.GenerateMinable.EventType.EMERALD))
            emeralds.generate(worldIn, rand, pos);
    }

    /**
     * @see net.minecraft.world.biome.BiomeHills
     */
    public void genSilverfishOre(World worldIn, Random rand, BlockPos pos) {

        WorldGenerator generator = new WorldGenMinable(Blocks.MONSTER_EGG.getDefaultState().withProperty(BlockSilverfish.VARIANT, BlockSilverfish.EnumType.STONE), 9);

        for (int i = 0; i < 7; ++i)
        {
            int j1 = rand.nextInt(16);
            int k1 = rand.nextInt(64);
            int l1 = rand.nextInt(16);
            if (TerrainGen.generateOre(worldIn, rand, generator, pos.add(j1, k1, l1), OreGenEvent.GenerateMinable.EventType.SILVERFISH))
                generator.generate(worldIn, rand, pos.add(j1, k1, l1));
        }
    }

    /**
     * Standard emerald ore generator.
     *
     * @see net.minecraft.world.biome.BiomeHills
     */
    public static class EmeraldGenerator extends WorldGenerator
    {
        @Override
        public boolean generate(World worldIn, Random rand, BlockPos pos)
        {
            int count = 3 + rand.nextInt(6);
            for (int i = 0; i < count; i++)
            {
                BlockPos blockpos = pos.add(rand.nextInt(16), rand.nextInt(28) + 4, rand.nextInt(16));

                IBlockState state = worldIn.getBlockState(blockpos);
                if (state.getBlock().isReplaceableOreGen(state, worldIn, blockpos, BlockMatcher.forBlock(Blocks.STONE)))
                {
                    worldIn.setBlockState(blockpos, Blocks.EMERALD_ORE.getDefaultState(), 2);
                }
            }
            return true;
        }
    }

    /**
     * When manually decorating biomes, sometimes you want the biome to partially decorate itself.
     * That's what this method does... it calls the biome's decorate() method.
     */
// TODO: [Generator settings] Also pass the base biome from DecoBaseBiomeDecorations when the 'biome' field is removed
//  public void rDecorateSeedBiome(Biome biome, World world, Random rand, int worldX, int worldZ, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {
    public void rDecorateSeedBiome(World world, Random rand, int worldX, int worldZ, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {

        if (strength > 0.3f) {
// TODO: [Clean-up] This call to a base biomes `decorate` method is the source of extra ore generation.
//                  This ore gen happens in addition to the ore gen that takes place when CPRTG#generateOres is called from CPRTG#doPopulate
// TODO: [Generator settings] To be replaced
//          biome.decorate(world, rand, new BlockPos(worldX, 0, worldZ));
            this.biome.decorate(world, rand, new BlockPos(worldX, 0, worldZ));
        }
    }

// TODO: [Clean-up] Use less ambiguous local vars
    public void rPopulatePreDecorate(IChunkGenerator ichunkgenerator, World worldObj, Random rand, int chunkX, int chunkZ, boolean villageBuilding) {

        int worldX = chunkX * 16;
        int worldZ = chunkZ * 16;
        boolean gen = true;
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
        ChunkProviderSettingsRTG settings = GenSettingsRepo.getSettingsForWorld(worldObj);

// TODO: [Clean-up] This should be set inside of the block after the check has been made if lakes are enabled/disabled so as not to send an event if lakes are disabled
        gen = TerrainGen.populate(ichunkgenerator, worldObj, rand, chunkX, chunkZ, villageBuilding, PopulateChunkEvent.Populate.EventType.LAKE);

        // Underground water lakes.
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//      if (settings.useWaterUndLakes) {
        if (rtgConfig.ENABLE_WATER_UNDERGROUND_LAKES.get()) {

// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//          if (gen && (rbb.waterUndergroundLakeChance() > 0)) {
            if (gen && (rtgConfig.WATER_UNDERGROUND_LAKE_CHANCE.get() > 0) && (rbb.waterUndergroundLakeChance() > 0)) {

                int i2 = worldX + rand.nextInt(16);// + 8;
                int l4 = RandomUtil.getRandomInt(rand, 1, 50);
                int i8 = worldZ + rand.nextInt(16);// + 8;

// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//              if (rand.nextInt(settings.waterUndLakeChance) == 0 && rand.nextInt(rbb.waterUndergroundLakeChance()) == 0) {
                if (rand.nextInt(rtgConfig.WATER_UNDERGROUND_LAKE_CHANCE.get()) == 0 && rand.nextInt(rbb.waterUndergroundLakeChance()) == 0) {

                    (new WorldGenLakes(Blocks.WATER)).generate(worldObj, rand, new BlockPos(new BlockPos(i2, l4, i8)));
                }
            }
        }

        // Surface water lakes.
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//      if (settings.useWaterLakes && !villageBuilding) {
        if (rtgConfig.ENABLE_WATER_SURFACE_LAKES.get() && !villageBuilding) {

            if (gen && (rtgConfig.WATER_SURFACE_LAKE_CHANCE.get() > 0) && (rbb.waterSurfaceLakeChance() > 0)) {

                int i2 = worldX + rand.nextInt(16);// + 8;
                int i8 = worldZ + rand.nextInt(16);// + 8;
                int l4 = worldObj.getHeight(new BlockPos(i2, 0, i8)).getY();

                //Surface lakes.
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//              if (rand.nextInt(settings.waterLakeChance) == 0 && rand.nextInt(rbb.waterSurfaceLakeChance()) == 0) {
                if (rand.nextInt(rtgConfig.WATER_SURFACE_LAKE_CHANCE.get()) == 0 && rand.nextInt(rbb.waterSurfaceLakeChance()) == 0) {

                    if (l4 > 63) {

                        (new WorldGenPond(Blocks.WATER.getDefaultState())).generate(worldObj, rand, new BlockPos(i2, l4, i8));
                    }
                }
            }
        }

// TODO: [Clean-up] This should be set inside of the block after the check has been made if lakes are enabled/disabled so as not to send an event if lakes are disabled
        gen = TerrainGen.populate(ichunkgenerator, worldObj, rand, chunkX, chunkZ, villageBuilding, PopulateChunkEvent.Populate.EventType.LAVA);

        // Underground lava lakes.
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//      if (settings.useLavaUndLakes) {
        if (rtgConfig.ENABLE_LAVA_UNDERGROUND_LAKES.get()) {

// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//          if (gen && (rbb.lavaUndergroundLakeChance() > 0)) {
            if (gen && (rtgConfig.LAVA_UNDERGROUND_LAKE_CHANCE.get() > 0) && (rbb.lavaUndergroundLakeChance() > 0)) {

                int i2 = worldX + rand.nextInt(16);// + 8;
                int l4 = RandomUtil.getRandomInt(rand, 1, 50);
                int i8 = worldZ + rand.nextInt(16);// + 8;

// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//              if (rand.nextInt(settings.lavaUndLakeChance) == 0 && rand.nextInt(rbb.lavaUndergroundLakeChance()) == 0) {
                if (rand.nextInt(rtgConfig.LAVA_UNDERGROUND_LAKE_CHANCE.get()) == 0 && rand.nextInt(rbb.lavaUndergroundLakeChance()) == 0) {

                    (new WorldGenLakes(Blocks.LAVA)).generate(worldObj, rand, new BlockPos(i2, l4, i8));
                }
            }
        }

        // Surface lava lakes.
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//      if (settings.useLavaLakes && !villageBuilding) {
        if (rtgConfig.ENABLE_LAVA_SURFACE_LAKES.get() && !villageBuilding) {

// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//          if (gen && (rbb.lavaSurfaceLakeChance() > 0)) {
            if (gen && (rtgConfig.LAVA_SURFACE_LAKE_CHANCE.get() > 0) && (rbb.lavaSurfaceLakeChance() > 0)) {

                int i2 = worldX + rand.nextInt(16);// + 8;
                int i8 = worldZ + rand.nextInt(16);// + 8;
                int l4 = worldObj.getHeight(new BlockPos(i2, 0, i8)).getY();

                //Surface lakes.
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//              if (rand.nextInt(settings.lavaLakeChance) == 0 && rand.nextInt(rbb.lavaSurfaceLakeChance()) == 0) {
                if (rand.nextInt(rtgConfig.LAVA_SURFACE_LAKE_CHANCE.get()) == 0 && rand.nextInt(rbb.lavaSurfaceLakeChance()) == 0) {

                    if (l4 > 63) {

                        (new WorldGenPond(Blocks.LAVA.getDefaultState())).generate(worldObj, rand, new BlockPos(i2, l4, i8));
                    }
                }
            }
        }

// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//      if (settings.useDungeons) {
        if (rtgConfig.GENERATE_DUNGEONS.get()) {

            gen = TerrainGen.populate(ichunkgenerator, worldObj, rand, chunkX, chunkZ, villageBuilding, PopulateChunkEvent.Populate.EventType.DUNGEON);

            if (gen) {

// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//              for(int k1 = 0; k1 < settings.dungeonChance; k1++) {
                for(int k1 = 0; k1 < rtgConfig.DUNGEON_FREQUENCY.get(); k1++) {

                    int j5 = worldX + rand.nextInt(16);// + 8;
                    int k8 = rand.nextInt(128);
                    int j11 = worldZ + rand.nextInt(16);// + 8;

                    (new WorldGenDungeons()).generate(worldObj, rand, new BlockPos(j5, k8, j11));
                }
            }
        }
    }

    public void rPopulatePostDecorate(World worldObj, Random rand, int chunkX, int chunkZ, boolean flag) {
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
        ChunkProviderSettingsRTG settings = GenSettingsRepo.getSettingsForWorld(worldObj);

        // Are flowing liquid modifications enabled?
// TODO: [Dimensions][Clean-up] Remove this check and config setting. The individual spouts can be disable separately, and this is not world-specific.
        if (!rtgConfig.ENABLE_FLOWING_LIQUID_MODIFICATIONS.get()) {
            return;
        }

        int worldX = chunkX * 16;
        int worldZ = chunkZ * 16;
        int worldHeight = worldObj.provider.getActualHeight();

        //Flowing water.
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//      if (settings.waterSpoutChance > 0) {
        if (rtgConfig.FLOWING_WATER_CHANCE.get() > 0) {
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//          if (rand.nextInt(settings.waterSpoutChance) == 0) {
            if (rand.nextInt(rtgConfig.FLOWING_WATER_CHANCE.get()) == 0) {
                for(int l18 = 0; l18 < 50; l18++)
                {
                    int l21 = worldX + rand.nextInt(16);// + 8;
                    int k23 = rand.nextInt(rand.nextInt(worldHeight - 16) + 10);
                    int l24 = worldZ + rand.nextInt(16);// + 8;
                    (new WorldGenLiquids(Blocks.FLOWING_WATER)).generate(worldObj, rand, new BlockPos(l21, k23, l24));
                }
            }
        }

        //Flowing lava.
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//      if (settings.lavaSpoutChance > 0) {
        if (rtgConfig.FLOWING_LAVA_CHANCE.get() > 0) {
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//          if (rand.nextInt(settings.lavaSpoutChance) == 0) {
            if (rand.nextInt(rtgConfig.FLOWING_LAVA_CHANCE.get()) == 0) {
                for(int i19 = 0; i19 < 20; i19++)
                {
                    int i22 = worldX + rand.nextInt(16);// + 8;
                    int l23 = rand.nextInt(worldHeight / 2);
                    int i25 = worldZ + rand.nextInt(16);// + 8;
                    (new WorldGenLiquids(Blocks.FLOWING_LAVA)).generate(worldObj, rand, new BlockPos(i22, l23, i25));
                }
            }
        }
    }

// TODO: [Generator settings] To be removed
    private int getSize(int originalSize, int newSize) {
        return (newSize == -1) ? originalSize : newSize;
    }
}