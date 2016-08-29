package rtg.world.biome;

import java.util.Random;

import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.feature.*;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import rtg.config.rtg.ConfigRTG;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.RandomUtil;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenClayRTG;
import static rtg.config.rtg.ConfigRTG.*;

public class BiomeDecoratorRTG
{
    public BlockPos chunkPos;
    public ChunkProviderSettings chunkProviderSettings;

    public WorldGenerator clayGen;
    public int clayPerChunk;

    public WorldGenerator dirtGen;
    public WorldGenerator gravelGen;
    public WorldGenerator graniteGen;
    public WorldGenerator dioriteGen;
    public WorldGenerator andesiteGen;
    public WorldGenerator coalGen;
    public WorldGenerator ironGen;
    public WorldGenerator goldGen;
    public WorldGenerator redstoneGen;
    public WorldGenerator diamondGen;
    public WorldGenerator lapisGen;

    public RealisticBiomeBase rbb;
    public Biome biome;

    public BiomeDecoratorRTG(RealisticBiomeBase rbb) {

        this.rbb = rbb;
        this.biome = rbb.baseBiome;

        this.clayGen = new WorldGenClayRTG(20); // Vanilla = 4
        this.clayPerChunk = 3; // Vanilla = 1
    }

    public void decorateClay(World worldIn, Random random, int worldX, int worldZ, OpenSimplexNoise simplex, CellNoise cell, float border, float river, boolean hasPlacedVillageBlocks)
    {
        if (TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.CLAY)) {

            if (river > 0.85f) {

                for (int j2 = 0; j2 < this.clayPerChunk; j2++) {

                    int l5 = worldX + random.nextInt(16);
                    int i9 = 53 + random.nextInt(15);
                    int l11 = worldZ + random.nextInt(16);

                    this.clayGen.generate(worldIn, random, new BlockPos(l5, i9, l11));
                }
            }
        }
    }

    /*
     * This method should only be called by DecoBaseBiomeDecorations or rDecorateSeedBiome().
     */
    public void decorateOres(World worldIn, Random random, int worldX, int worldZ, OpenSimplexNoise simplex, CellNoise cell, float border, float river, boolean hasPlacedVillageBlocks)
    {
        if (biome.theBiomeDecorator.chunkProviderSettings == null) {

            String generatorOptions = worldIn.getWorldInfo().getGeneratorOptions();
            generatorOptions = (generatorOptions != null) ? generatorOptions : "";

            biome.theBiomeDecorator.chunkProviderSettings = ChunkProviderSettings.Factory.jsonToFactory(generatorOptions).build();
        }

        chunkPos = new BlockPos(worldX, 0, worldZ);

        biome.theBiomeDecorator.dirtGen = new WorldGenMinable(Blocks.DIRT.getDefaultState(), biome.theBiomeDecorator.chunkProviderSettings.dirtSize);
        biome.theBiomeDecorator.gravelGen = new WorldGenMinable(Blocks.GRAVEL.getDefaultState(), biome.theBiomeDecorator.chunkProviderSettings.gravelSize);
        biome.theBiomeDecorator.graniteGen = new WorldGenMinable(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE), biome.theBiomeDecorator.chunkProviderSettings.graniteSize);
        biome.theBiomeDecorator.dioriteGen = new WorldGenMinable(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE), biome.theBiomeDecorator.chunkProviderSettings.dioriteSize);
        biome.theBiomeDecorator.andesiteGen = new WorldGenMinable(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE), biome.theBiomeDecorator.chunkProviderSettings.andesiteSize);
        biome.theBiomeDecorator.coalGen = new WorldGenMinable(Blocks.COAL_ORE.getDefaultState(), biome.theBiomeDecorator.chunkProviderSettings.coalSize);
        biome.theBiomeDecorator.ironGen = new WorldGenMinable(Blocks.IRON_ORE.getDefaultState(), biome.theBiomeDecorator.chunkProviderSettings.ironSize);
        biome.theBiomeDecorator.goldGen = new WorldGenMinable(Blocks.GOLD_ORE.getDefaultState(), biome.theBiomeDecorator.chunkProviderSettings.goldSize);
        biome.theBiomeDecorator.redstoneGen = new WorldGenMinable(Blocks.REDSTONE_ORE.getDefaultState(), biome.theBiomeDecorator.chunkProviderSettings.redstoneSize);
        biome.theBiomeDecorator.diamondGen = new WorldGenMinable(Blocks.DIAMOND_ORE.getDefaultState(), biome.theBiomeDecorator.chunkProviderSettings.diamondSize);
        biome.theBiomeDecorator.lapisGen = new WorldGenMinable(Blocks.LAPIS_ORE.getDefaultState(), biome.theBiomeDecorator.chunkProviderSettings.lapisSize);

        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(worldIn, random, chunkPos));
        if (TerrainGen.generateOre(worldIn, random, biome.theBiomeDecorator.dirtGen, chunkPos, OreGenEvent.GenerateMinable.EventType.DIRT)) {
            this.genStandardOre1(worldIn, random, biome.theBiomeDecorator.chunkProviderSettings.dirtCount, biome.theBiomeDecorator.dirtGen, biome.theBiomeDecorator.chunkProviderSettings.dirtMinHeight, biome.theBiomeDecorator.chunkProviderSettings.dirtMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random, biome.theBiomeDecorator.gravelGen, chunkPos, OreGenEvent.GenerateMinable.EventType.GRAVEL)) {
            this.genStandardOre1(worldIn, random, biome.theBiomeDecorator.chunkProviderSettings.gravelCount, biome.theBiomeDecorator.gravelGen, biome.theBiomeDecorator.chunkProviderSettings.gravelMinHeight, biome.theBiomeDecorator.chunkProviderSettings.gravelMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random, biome.theBiomeDecorator.dioriteGen, chunkPos, OreGenEvent.GenerateMinable.EventType.DIORITE)) {
            this.genStandardOre1(worldIn, random, biome.theBiomeDecorator.chunkProviderSettings.dioriteCount, biome.theBiomeDecorator.dioriteGen, biome.theBiomeDecorator.chunkProviderSettings.dioriteMinHeight, biome.theBiomeDecorator.chunkProviderSettings.dioriteMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random, biome.theBiomeDecorator.graniteGen, chunkPos, OreGenEvent.GenerateMinable.EventType.GRANITE)) {
            this.genStandardOre1(worldIn, random, biome.theBiomeDecorator.chunkProviderSettings.graniteCount, biome.theBiomeDecorator.graniteGen, biome.theBiomeDecorator.chunkProviderSettings.graniteMinHeight, biome.theBiomeDecorator.chunkProviderSettings.graniteMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random, biome.theBiomeDecorator.andesiteGen, chunkPos, OreGenEvent.GenerateMinable.EventType.ANDESITE)) {
            this.genStandardOre1(worldIn, random, biome.theBiomeDecorator.chunkProviderSettings.andesiteCount, biome.theBiomeDecorator.andesiteGen, biome.theBiomeDecorator.chunkProviderSettings.andesiteMinHeight, biome.theBiomeDecorator.chunkProviderSettings.andesiteMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random, biome.theBiomeDecorator.coalGen, chunkPos, OreGenEvent.GenerateMinable.EventType.COAL)) {
            this.genStandardOre1(worldIn, random, biome.theBiomeDecorator.chunkProviderSettings.coalCount, biome.theBiomeDecorator.coalGen, biome.theBiomeDecorator.chunkProviderSettings.coalMinHeight, biome.theBiomeDecorator.chunkProviderSettings.coalMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random, biome.theBiomeDecorator.ironGen, chunkPos, OreGenEvent.GenerateMinable.EventType.IRON)) {
            this.genStandardOre1(worldIn, random, biome.theBiomeDecorator.chunkProviderSettings.ironCount, biome.theBiomeDecorator.ironGen, biome.theBiomeDecorator.chunkProviderSettings.ironMinHeight, biome.theBiomeDecorator.chunkProviderSettings.ironMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random, biome.theBiomeDecorator.goldGen, chunkPos, OreGenEvent.GenerateMinable.EventType.GOLD)) {
            this.genStandardOre1(worldIn, random, biome.theBiomeDecorator.chunkProviderSettings.goldCount, biome.theBiomeDecorator.goldGen, biome.theBiomeDecorator.chunkProviderSettings.goldMinHeight, biome.theBiomeDecorator.chunkProviderSettings.goldMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random, biome.theBiomeDecorator.redstoneGen, chunkPos, OreGenEvent.GenerateMinable.EventType.REDSTONE)) {
            this.genStandardOre1(worldIn, random, biome.theBiomeDecorator.chunkProviderSettings.redstoneCount, biome.theBiomeDecorator.redstoneGen, biome.theBiomeDecorator.chunkProviderSettings.redstoneMinHeight, biome.theBiomeDecorator.chunkProviderSettings.redstoneMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random, biome.theBiomeDecorator.diamondGen, chunkPos, OreGenEvent.GenerateMinable.EventType.DIAMOND)) {
            this.genStandardOre1(worldIn, random, biome.theBiomeDecorator.chunkProviderSettings.diamondCount, biome.theBiomeDecorator.diamondGen, biome.theBiomeDecorator.chunkProviderSettings.diamondMinHeight, biome.theBiomeDecorator.chunkProviderSettings.diamondMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random, biome.theBiomeDecorator.lapisGen, chunkPos, OreGenEvent.GenerateMinable.EventType.LAPIS)) {
            this.genStandardOre2(worldIn, random, biome.theBiomeDecorator.chunkProviderSettings.lapisCount, biome.theBiomeDecorator.lapisGen, biome.theBiomeDecorator.chunkProviderSettings.lapisCenterHeight, biome.theBiomeDecorator.chunkProviderSettings.lapisSpread);
        }
        if (rbb.generatesEmeralds) {
            this.genEmeraldOre(worldIn, random, chunkPos);
        }
        if (rbb.generatesSilverfish) {
            this.genSilverfishOre(worldIn, random, chunkPos);
        }
        if (rbb.getExtraGoldGenCount() > 0) {
            this.genStandardOre1(worldIn, random, rbb.getExtraGoldGenCount(), biome.theBiomeDecorator.goldGen, rbb.getExtraGoldGenMinHeight(), rbb.getExtraGoldGenMaxHeight());
        }
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(worldIn, random, chunkPos));
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
            BlockPos blockpos = this.chunkPos.add(random.nextInt(16), random.nextInt(maxHeight - minHeight) + minHeight, random.nextInt(16));
            generator.generate(worldIn, random, blockpos);
        }
    }

    public void genStandardOre2(World worldIn, Random random, int blockCount, WorldGenerator generator, int centerHeight, int spread)
    {
        for (int i = 0; i < blockCount; ++i)
        {
            BlockPos blockpos = this.chunkPos.add(random.nextInt(16), random.nextInt(spread) + random.nextInt(spread) + centerHeight - spread, random.nextInt(16));
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
     * If the conditions for decoration aren't met, we still need to generate ores.
     */
    public void rDecorateSeedBiome(World world, Random rand, int worldX, int worldZ, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {

        if (strength > 0.3f) {
            this.biome.decorate(world, rand, new BlockPos(worldX, 0, worldZ));
        }
        else {
            this.decorateOres(world, rand, worldX, worldZ, simplex, cell, strength, river, false);
        }
    }

    public void rPopulatePreDecorate(IChunkGenerator ichunkgenerator, World worldObj, Random rand, int chunkX, int chunkZ, boolean villageBuilding) {

        int worldX = chunkX * 16;
        int worldZ = chunkZ * 16;
        boolean gen = true;

        gen = TerrainGen.populate(ichunkgenerator, worldObj, rand, chunkX, chunkZ, villageBuilding, PopulateChunkEvent.Populate.EventType.LAKE);

        // Underground water lakes.
        if (ConfigRTG.enableWaterUndergroundLakes) {

            if (gen && (ConfigRTG.waterUndergroundLakeChance > 0) && (rbb.waterUndergroundLakeChance > 0)) {

                int i2 = worldX + rand.nextInt(16);// + 8;
                int l4 = RandomUtil.getRandomInt(rand, 1, 50);
                int i8 = worldZ + rand.nextInt(16);// + 8;

                if (rand.nextInt(waterUndergroundLakeChance) == 0 && (RandomUtil.getRandomInt(rand, 1, waterUndergroundLakeChance) == 1)) {

                    (new WorldGenLakes(Blocks.WATER)).generate(worldObj, rand, new BlockPos(new BlockPos(i2, l4, i8)));
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
                if (rand.nextInt(waterSurfaceLakeChance) == 0 && (RandomUtil.getRandomInt(rand, 1, waterSurfaceLakeChance) == 1)) {

                    if (l4 > 63) {

                        (new WorldGenLakes(Blocks.WATER)).generate(worldObj, rand, new BlockPos(i2, l4, i8));
                    }
                }
            }
        }

        gen = TerrainGen.populate(ichunkgenerator, worldObj, rand, chunkX, chunkZ, villageBuilding, PopulateChunkEvent.Populate.EventType.LAVA);

        // Underground lava lakes.
        if (ConfigRTG.enableLavaUndergroundLakes) {

            if (gen && (lavaUndergroundLakeChance > 0)) {

                int i2 = worldX + rand.nextInt(16);// + 8;
                int l4 = RandomUtil.getRandomInt(rand, 1, 50);
                int i8 = worldZ + rand.nextInt(16);// + 8;

                if (rand.nextInt(lavaUndergroundLakeChance) == 0 && (RandomUtil.getRandomInt(rand, 1, lavaUndergroundLakeChance) == 1)) {

                    (new WorldGenLakes(Blocks.LAVA)).generate(worldObj, rand, new BlockPos(i2, l4, i8));
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
                if (rand.nextInt(lavaSurfaceLakeChance) == 0 && (RandomUtil.getRandomInt(rand, 1, lavaSurfaceLakeChance) == 1)) {

                    if (l4 > 63) {

                        (new WorldGenLakes(Blocks.LAVA)).generate(worldObj, rand, new BlockPos(i2, l4, i8));
                    }
                }
            }
        }

        if (ConfigRTG.generateDungeons) {

            gen = TerrainGen.populate(ichunkgenerator, worldObj, rand, chunkX, chunkZ, villageBuilding, PopulateChunkEvent.Populate.EventType.DUNGEON);

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

    public void rPopulatePostDecorate(World worldObj, Random rand, int chunkX, int chunkZ, boolean flag) {

        // Are flowing liquid modifications enabled?
        if (!ConfigRTG.enableFlowingLiquidModifications) {
            return;
        }

        int worldX = chunkX * 16;
        int worldZ = chunkZ * 16;
        int worldHeight = worldObj.provider.getActualHeight();

        //Flowing water.
        if (ConfigRTG.flowingWaterChance > 0) {
            if (rand.nextInt(ConfigRTG.flowingWaterChance) == 0) {
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
        if (ConfigRTG.flowingLavaChance > 0) {
            if (rand.nextInt(ConfigRTG.flowingLavaChance) == 0) {
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
}