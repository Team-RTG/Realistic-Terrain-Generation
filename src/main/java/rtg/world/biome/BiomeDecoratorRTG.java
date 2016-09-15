package rtg.world.biome;

import javax.annotation.Nonnull;
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

@SuppressWarnings("UnusedParameters")
public class BiomeDecoratorRTG
{
    private WorldGenerator clayGen;
    private int clayPerChunk;
    private RealisticBiomeBase rbb;
    public BlockPos pos;
    public Biome biome;

    public BiomeDecoratorRTG(RealisticBiomeBase rbb) {

        this.rbb = rbb;
        this.biome = rbb.baseBiome;

        this.clayGen = new WorldGenClayRTG(20); // Vanilla = 4
        this.clayPerChunk = 3; // Vanilla = 1
    }

    public void decorateClay(World worldIn, Random random, int worldX, int worldZ, OpenSimplexNoise simplex, CellNoise cell, float border, float river, boolean hasPlacedVillageBlocks)
    {
        if (TerrainGen.decorate(worldIn, random, new BlockPos(worldX, 0, worldZ), DecorateBiomeEvent.Decorate.EventType.CLAY)) {

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
            biome.theBiomeDecorator.chunkProviderSettings = ChunkProviderSettings.Factory.jsonToFactory(generatorOptions).build();
        }

        pos = new BlockPos(worldX, 0, worldZ);

        biome.theBiomeDecorator.dirtGen = new WorldGenMinable(Blocks.DIRT.getDefaultState(), biome.theBiomeDecorator.chunkProviderSettings.dirtSize);
        biome.theBiomeDecorator.gravelGen = new WorldGenMinable(Blocks.GRAVEL.getDefaultState(), biome.theBiomeDecorator.chunkProviderSettings.gravelSize);
        biome.theBiomeDecorator.graniteGen = new WorldGenMinable(Blocks.STONE.getDefaultState()
            .withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE), biome.theBiomeDecorator.chunkProviderSettings.graniteSize);
        biome.theBiomeDecorator.dioriteGen = new WorldGenMinable(Blocks.STONE.getDefaultState()
            .withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE), biome.theBiomeDecorator.chunkProviderSettings.dioriteSize);
        biome.theBiomeDecorator.andesiteGen = new WorldGenMinable(Blocks.STONE.getDefaultState()
            .withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE), biome.theBiomeDecorator.chunkProviderSettings.andesiteSize);
        biome.theBiomeDecorator.coalGen = new WorldGenMinable(Blocks.COAL_ORE.getDefaultState(), biome.theBiomeDecorator.chunkProviderSettings.coalSize);
        biome.theBiomeDecorator.ironGen = new WorldGenMinable(Blocks.IRON_ORE.getDefaultState(), biome.theBiomeDecorator.chunkProviderSettings.ironSize);
        biome.theBiomeDecorator.goldGen = new WorldGenMinable(Blocks.GOLD_ORE.getDefaultState(), biome.theBiomeDecorator.chunkProviderSettings.goldSize);
        biome.theBiomeDecorator.redstoneGen = new WorldGenMinable(Blocks.REDSTONE_ORE.getDefaultState(), biome.theBiomeDecorator.chunkProviderSettings.redstoneSize);
        biome.theBiomeDecorator.diamondGen = new WorldGenMinable(Blocks.DIAMOND_ORE.getDefaultState(), biome.theBiomeDecorator.chunkProviderSettings.diamondSize);
        biome.theBiomeDecorator.lapisGen = new WorldGenMinable(Blocks.LAPIS_ORE.getDefaultState(), biome.theBiomeDecorator.chunkProviderSettings.lapisSize);

        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(worldIn, random, pos));
        if (TerrainGen.generateOre(worldIn, random,
            biome.theBiomeDecorator.dirtGen, pos, OreGenEvent.GenerateMinable.EventType.DIRT)) {
            this.genStandardOre1(worldIn, random,
                biome.theBiomeDecorator.chunkProviderSettings.dirtCount, biome.theBiomeDecorator.dirtGen,
                biome.theBiomeDecorator.chunkProviderSettings.dirtMinHeight,
                biome.theBiomeDecorator.chunkProviderSettings.dirtMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random,
            biome.theBiomeDecorator.gravelGen, pos, OreGenEvent.GenerateMinable.EventType.GRAVEL)) {
            this.genStandardOre1(worldIn, random,
                biome.theBiomeDecorator.chunkProviderSettings.gravelCount, biome.theBiomeDecorator.gravelGen,
                biome.theBiomeDecorator.chunkProviderSettings.gravelMinHeight,
                biome.theBiomeDecorator.chunkProviderSettings.gravelMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random,
            biome.theBiomeDecorator.dioriteGen, pos, OreGenEvent.GenerateMinable.EventType.DIORITE)) {
            this.genStandardOre1(worldIn, random,
                biome.theBiomeDecorator.chunkProviderSettings.dioriteCount, biome.theBiomeDecorator.dioriteGen,
                biome.theBiomeDecorator.chunkProviderSettings.dioriteMinHeight,
                biome.theBiomeDecorator.chunkProviderSettings.dioriteMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random,
            biome.theBiomeDecorator.graniteGen, pos, OreGenEvent.GenerateMinable.EventType.GRANITE)) {
            this.genStandardOre1(worldIn, random,
                biome.theBiomeDecorator.chunkProviderSettings.graniteCount, biome.theBiomeDecorator.graniteGen,
                biome.theBiomeDecorator.chunkProviderSettings.graniteMinHeight,
                biome.theBiomeDecorator.chunkProviderSettings.graniteMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random,
            biome.theBiomeDecorator.andesiteGen, pos, OreGenEvent.GenerateMinable.EventType.ANDESITE)) {
            this.genStandardOre1(worldIn, random,
                biome.theBiomeDecorator.chunkProviderSettings.andesiteCount, biome.theBiomeDecorator.andesiteGen,
                biome.theBiomeDecorator.chunkProviderSettings.andesiteMinHeight,
                biome.theBiomeDecorator.chunkProviderSettings.andesiteMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random,
            biome.theBiomeDecorator.coalGen, pos, OreGenEvent.GenerateMinable.EventType.COAL)) {
            this.genStandardOre1(worldIn, random,
                biome.theBiomeDecorator.chunkProviderSettings.coalCount, biome.theBiomeDecorator.coalGen,
                biome.theBiomeDecorator.chunkProviderSettings.coalMinHeight,
                biome.theBiomeDecorator.chunkProviderSettings.coalMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random,
            biome.theBiomeDecorator.ironGen, pos, OreGenEvent.GenerateMinable.EventType.IRON)) {
            this.genStandardOre1(worldIn, random,
                biome.theBiomeDecorator.chunkProviderSettings.ironCount, biome.theBiomeDecorator.ironGen,
                biome.theBiomeDecorator.chunkProviderSettings.ironMinHeight,
                biome.theBiomeDecorator.chunkProviderSettings.ironMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random,
            biome.theBiomeDecorator.goldGen, pos, OreGenEvent.GenerateMinable.EventType.GOLD)) {
            this.genStandardOre1(worldIn, random,
                biome.theBiomeDecorator.chunkProviderSettings.goldCount, biome.theBiomeDecorator.goldGen,
                biome.theBiomeDecorator.chunkProviderSettings.goldMinHeight,
                biome.theBiomeDecorator.chunkProviderSettings.goldMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random,
            biome.theBiomeDecorator.redstoneGen, pos, OreGenEvent.GenerateMinable.EventType.REDSTONE)) {
            this.genStandardOre1(worldIn, random,
                biome.theBiomeDecorator.chunkProviderSettings.redstoneCount, biome.theBiomeDecorator.redstoneGen,
                biome.theBiomeDecorator.chunkProviderSettings.redstoneMinHeight,
                biome.theBiomeDecorator.chunkProviderSettings.redstoneMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random, biome.theBiomeDecorator.diamondGen, pos,
            OreGenEvent.GenerateMinable.EventType.DIAMOND)) {
            this.genStandardOre1(worldIn, random,
                biome.theBiomeDecorator.chunkProviderSettings.diamondCount, biome.theBiomeDecorator.diamondGen,
                biome.theBiomeDecorator.chunkProviderSettings.diamondMinHeight,
                biome.theBiomeDecorator.chunkProviderSettings.diamondMaxHeight);
        }
        if (TerrainGen.generateOre(worldIn, random,
            biome.theBiomeDecorator.lapisGen, pos, OreGenEvent.GenerateMinable.EventType.LAPIS)) {
            this.genStandardOre2(worldIn, random, biome.theBiomeDecorator.chunkProviderSettings.lapisCount,
                biome.theBiomeDecorator.lapisGen, biome.theBiomeDecorator.chunkProviderSettings.lapisCenterHeight,
                biome.theBiomeDecorator.chunkProviderSettings.lapisSpread);
        }
        if (rbb.generatesEmeralds) {
            this.genEmeraldOre(worldIn, random, pos);
        }
        if (rbb.generatesSilverfish) {
            this.genSilverfishOre(worldIn, random, pos);
        }
        if (rbb.getExtraGoldGenCount() > 0) {
            this.genStandardOre1(worldIn, random, rbb.getExtraGoldGenCount(), biome.theBiomeDecorator.goldGen, rbb.getExtraGoldGenMinHeight(), rbb.getExtraGoldGenMaxHeight());
        }
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(worldIn, random, pos));
    }

    private void genStandardOre1(World worldIn, Random random, int blockCount, WorldGenerator generator, int minHeight, int maxHeight)
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

    private void genStandardOre2(World worldIn, Random random, int blockCount, WorldGenerator generator, int centerHeight, int spread)
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
    private void genEmeraldOre(World worldIn, Random rand, BlockPos pos) {
        WorldGenerator emeralds = new EmeraldGenerator();
        if (TerrainGen.generateOre(worldIn, rand, emeralds, pos, OreGenEvent.GenerateMinable.EventType.EMERALD))
            emeralds.generate(worldIn, rand, pos);
    }

    /**
     * @see net.minecraft.world.biome.BiomeHills
     */
    private void genSilverfishOre(World worldIn, Random rand, BlockPos pos) {

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
    private static class EmeraldGenerator extends WorldGenerator
    {
        @Override
        public boolean generate(@Nonnull World worldIn, @Nonnull Random rand, @Nonnull BlockPos pos)
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
        boolean gen;

        gen = TerrainGen.populate(ichunkgenerator, worldObj, rand, chunkX, chunkZ, villageBuilding, PopulateChunkEvent.Populate.EventType.LAKE);

        // Underground water lakes.
        if (ConfigRTG.enableWaterUndergroundLakes) {

            if (gen && (ConfigRTG.waterUndergroundLakeChance > 0) && (rbb.waterUndergroundLakeChance > 0)) {

                int i2 = worldX + rand.nextInt(16);// + 8;
                int l4 = RandomUtil.getRandomInt(rand, 1, 50);
                int i8 = worldZ + rand.nextInt(16);// + 8;

                if (rand.nextInt(ConfigRTG.waterUndergroundLakeChance) == 0 && rand.nextInt(rbb.waterUndergroundLakeChance) == 0) {

                    (new WorldGenLakes(Blocks.WATER)).generate(worldObj, rand, new BlockPos(new BlockPos(i2, l4, i8)));
                }
            }
        }

        // Surface water lakes.
        if (ConfigRTG.enableWaterSurfaceLakes && !villageBuilding) {

            if (gen && (ConfigRTG.waterSurfaceLakeChance > 0) && (rbb.waterSurfaceLakeChance > 0)) {

                int i2 = worldX + rand.nextInt(16);// + 8;
                int i8 = worldZ + rand.nextInt(16);// + 8;
                int l4 = worldObj.getHeight(new BlockPos(i2, 0, i8)).getY();

                //Surface lakes.
                if (rand.nextInt(ConfigRTG.waterSurfaceLakeChance) == 0 && rand.nextInt(rbb.waterSurfaceLakeChance) == 0) {

                    if (l4 > 63) {

                        (new WorldGenLakes(Blocks.WATER)).generate(worldObj, rand, new BlockPos(i2, l4, i8));
                    }
                }
            }
        }

        gen = TerrainGen.populate(ichunkgenerator, worldObj, rand, chunkX, chunkZ, villageBuilding, PopulateChunkEvent.Populate.EventType.LAVA);

        // Underground lava lakes.
        if (ConfigRTG.enableLavaUndergroundLakes) {

            if (gen && (ConfigRTG.lavaUndergroundLakeChance > 0) && (rbb.lavaUndergroundLakeChance > 0)) {

                int i2 = worldX + rand.nextInt(16);// + 8;
                int l4 = RandomUtil.getRandomInt(rand, 1, 50);
                int i8 = worldZ + rand.nextInt(16);// + 8;

                if (rand.nextInt(ConfigRTG.lavaUndergroundLakeChance) == 0 && rand.nextInt(rbb.lavaUndergroundLakeChance) == 0) {

                    (new WorldGenLakes(Blocks.LAVA)).generate(worldObj, rand, new BlockPos(i2, l4, i8));
                }
            }
        }

        // Surface lava lakes.
        if (ConfigRTG.enableLavaSurfaceLakes && !villageBuilding) {

            if (gen && (ConfigRTG.lavaSurfaceLakeChance > 0) && (rbb.lavaSurfaceLakeChance > 0)) {

                int i2 = worldX + rand.nextInt(16);// + 8;
                int i8 = worldZ + rand.nextInt(16);// + 8;
                int l4 = worldObj.getHeight(new BlockPos(i2, 0, i8)).getY();

                //Surface lakes.
                if (rand.nextInt(ConfigRTG.lavaSurfaceLakeChance) == 0 && rand.nextInt(rbb.lavaSurfaceLakeChance) == 0) {

                    if (l4 > 63) {

                        (new WorldGenLakes(Blocks.LAVA)).generate(worldObj, rand, new BlockPos(i2, l4, i8));
                    }
                }
            }
        }

        if (ConfigRTG.generateDungeons) {

            gen = TerrainGen.populate(ichunkgenerator, worldObj, rand, chunkX, chunkZ, villageBuilding, PopulateChunkEvent.Populate.EventType.DUNGEON);

            if (gen) {

                for(int i = 0; i < ConfigRTG.dungeonFrequency; i++) {

                    int x = worldX + rand.nextInt(16);// + 8;
                    int y = rand.nextInt(128);
                    int z = worldZ + rand.nextInt(16);// + 8;

                    (new WorldGenDungeons()).generate(worldObj, rand, new BlockPos(x, y, z));
                }
            }
        }
    }

    public void rPopulatePostDecorate(World worldObj, Random rand, int chunkX, int chunkZ) {

        // Are flowing liquid modifications enabled?
        if (!ConfigRTG.enableFlowingLiquidModifications) return;

        int worldX = chunkX * 16;
        int worldZ = chunkZ * 16;
        int worldHeight = worldObj.provider.getActualHeight();

        //Flowing water.
        if (ConfigRTG.flowingWaterChance > 0) {
            if (rand.nextInt(ConfigRTG.flowingWaterChance) == 0) {
                for(int i = 0; i < 50; i++)
                {
                    int x = worldX + rand.nextInt(16);// + 8;
                    int y = rand.nextInt(rand.nextInt(worldHeight - 16) + 10);
                    int z = worldZ + rand.nextInt(16);// + 8;
                    (new WorldGenLiquids(Blocks.FLOWING_WATER)).generate(worldObj, rand, new BlockPos(x, y, z));
                }
            }
        }

        //Flowing lava.
        if (ConfigRTG.flowingLavaChance > 0) {
            if (rand.nextInt(ConfigRTG.flowingLavaChance) == 0) {
                for(int i = 0; i < 20; i++)
                {
                    int x = worldX + rand.nextInt(16);// + 8;
                    int y = rand.nextInt(worldHeight / 2);
                    int z = worldZ + rand.nextInt(16);// + 8;
                    (new WorldGenLiquids(Blocks.FLOWING_LAVA)).generate(worldObj, rand, new BlockPos(x, y, z));
                }
            }
        }
    }
}