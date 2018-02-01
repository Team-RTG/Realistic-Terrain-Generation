package rtg.api.world.biome;

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
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.*;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import rtg.api.util.noise.CellNoise;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.gen.feature.WorldGenPond;
import rtg.api.world.gen.ChunkProviderSettingsRTG;

// TODO: [Clean-up] Remove unused parameters from class members
public class BiomeDecoratorRTG
{
    private IRealisticBiome rbb;

    public BiomeDecoratorRTG(IRealisticBiome rbb) {

        this.rbb = rbb;
    }

    /*
     * This method should only be called by ChunkProviderRTG#generateOres.
     */
// TODO: [Clean-up] *CRITICAL* #decorateOres should be called from somewhere in the decoration process instead of from CPRTG, and checked against what biome decoration method is
//                  being called to prevent it from being called when the realistic biome is calling the base biomes native decoration method via #rDecorateSeedBiome
    public void decorateOres(World worldIn, Random random, ChunkProviderSettingsRTG settings, BlockPos pos) {

// TODO: [Clean-up][API] Explore ways of executing ore gen that doesn't involve creating a new instance of WorldGenMinable for every ore for every chunk generated
//                       This must be world-specific, which instances of BiomeDecoratorRTG are not. Perhaps store the instances in CPRTG and pass them during population
        WorldGenerator dirtGen     = new WorldGenMinable(Blocks.DIRT.getDefaultState(), settings.dirtSize);
        WorldGenerator gravelGen   = new WorldGenMinable(Blocks.GRAVEL.getDefaultState(), settings.gravelSize);
        WorldGenerator graniteGen  = new WorldGenMinable(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE), settings.graniteSize);
        WorldGenerator dioriteGen  = new WorldGenMinable(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE), settings.dioriteSize);
        WorldGenerator andesiteGen = new WorldGenMinable(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE), settings.andesiteSize);
        WorldGenerator coalGen     = new WorldGenMinable(Blocks.COAL_ORE.getDefaultState(), settings.coalSize);
        WorldGenerator ironGen     = new WorldGenMinable(Blocks.IRON_ORE.getDefaultState(), settings.ironSize);
        WorldGenerator goldGen     = new WorldGenMinable(Blocks.GOLD_ORE.getDefaultState(), settings.goldSize);
        WorldGenerator redstoneGen = new WorldGenMinable(Blocks.REDSTONE_ORE.getDefaultState(), settings.redstoneSize);
        WorldGenerator diamondGen  = new WorldGenMinable(Blocks.DIAMOND_ORE.getDefaultState(), settings.diamondSize);
        WorldGenerator lapisGen    = new WorldGenMinable(Blocks.LAPIS_ORE.getDefaultState(), settings.lapisSize);

        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(worldIn, random, pos));

        if (TerrainGen.generateOre(worldIn, random, dirtGen, pos, EventType.DIRT)) {
            this.genStandardOre(worldIn, random, settings.dirtCount, dirtGen, settings.dirtMinHeight, settings.dirtMaxHeight, pos);
        }
        if (TerrainGen.generateOre(worldIn, random, gravelGen, pos, EventType.GRAVEL)) {
            this.genStandardOre(worldIn, random, settings.gravelCount, gravelGen, settings.gravelMinHeight, settings.gravelMaxHeight, pos);
        }
        if (TerrainGen.generateOre(worldIn, random, dioriteGen, pos, EventType.DIORITE)) {
            this.genStandardOre(worldIn, random, settings.dioriteCount, dioriteGen, settings.dioriteMinHeight, settings.dioriteMaxHeight, pos);
        }
        if (TerrainGen.generateOre(worldIn, random, graniteGen, pos, EventType.GRANITE)) {
            this.genStandardOre(worldIn, random, settings.graniteCount, graniteGen, settings.graniteMinHeight, settings.graniteMaxHeight, pos);
        }
        if (TerrainGen.generateOre(worldIn, random, andesiteGen, pos, EventType.ANDESITE)) {
            this.genStandardOre(worldIn, random, settings.andesiteCount, andesiteGen, settings.andesiteMinHeight, settings.andesiteMaxHeight, pos);
        }
        if (TerrainGen.generateOre(worldIn, random, coalGen, pos, EventType.COAL)) {
            this.genStandardOre(worldIn, random, settings.coalCount, coalGen, settings.coalMinHeight, settings.coalMaxHeight, pos);
        }
        if (TerrainGen.generateOre(worldIn, random, ironGen, pos, EventType.IRON)) {
            this.genStandardOre(worldIn, random, settings.ironCount, ironGen, settings.ironMinHeight, settings.ironMaxHeight, pos);
        }
        if (TerrainGen.generateOre(worldIn, random, goldGen, pos, EventType.GOLD)) {
            this.genStandardOre(worldIn, random, settings.goldCount, goldGen, settings.goldMinHeight, settings.goldMaxHeight, pos);
        }
        if (TerrainGen.generateOre(worldIn, random, redstoneGen, pos, EventType.REDSTONE)) {
            this.genStandardOre(worldIn, random, settings.redstoneCount, redstoneGen, settings.redstoneMinHeight, settings.redstoneMaxHeight, pos);
        }
        if (TerrainGen.generateOre(worldIn, random, diamondGen, pos, EventType.DIAMOND)) {
            this.genStandardOre(worldIn, random, settings.diamondCount, diamondGen, settings.diamondMinHeight, settings.diamondMaxHeight, pos);
        }
        if (TerrainGen.generateOre(worldIn, random, lapisGen, pos, EventType.LAPIS)) {
            this.genLapisOre(worldIn, random, settings.lapisCount, lapisGen, settings.lapisCenterHeight, settings.lapisSpread, pos);
        }
        if (rbb.generatesEmeralds()) {
            this.genEmeraldOre(worldIn, random, pos);
        }
        if (rbb.generatesSilverfish()) {
            this.genSilverfishOre(worldIn, random, pos);
        }
        if (rbb.getExtraGoldGenCount() > 0) {
            this.genStandardOre(worldIn, random, rbb.getExtraGoldGenCount(), goldGen, rbb.getExtraGoldGenMinHeight(), rbb.getExtraGoldGenMaxHeight(), pos);
        }
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(worldIn, random, pos));
    }

    private void genStandardOre(World worldIn, Random random, int blockCount, WorldGenerator generator, int minHeight, int maxHeight, BlockPos pos)
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
            BlockPos blockpos = pos.add(random.nextInt(16), random.nextInt(maxHeight - minHeight) + minHeight, random.nextInt(16));
            generator.generate(worldIn, random, blockpos);
        }
    }

    private void genLapisOre(World worldIn, Random random, int blockCount, WorldGenerator generator, int centerHeight, int spread, BlockPos pos)
    {
        for (int i = 0; i < blockCount; ++i)
        {
            BlockPos blockpos = pos.add(random.nextInt(16), random.nextInt(spread) + random.nextInt(spread) + centerHeight - spread, random.nextInt(16));
            generator.generate(worldIn, random, blockpos);
        }
    }

    /**
     * @see net.minecraft.world.biome.BiomeHills
     */
    private void genEmeraldOre(World worldIn, Random rand, BlockPos pos) {
        WorldGenerator emeralds = new EmeraldGenerator();
        if (TerrainGen.generateOre(worldIn, rand, emeralds, pos, EventType.EMERALD))
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
            if (TerrainGen.generateOre(worldIn, rand, generator, pos.add(j1, k1, l1), EventType.SILVERFISH))
                generator.generate(worldIn, rand, pos.add(j1, k1, l1));
        }
    }

    /**
     * Standard emerald ore generator.
     *
     * @see net.minecraft.world.biome.BiomeHills
     */
    public static class EmeraldGenerator extends WorldGenerator {

        @Override
        public boolean generate(@Nonnull World worldIn, @Nonnull Random rand, @Nonnull BlockPos pos) {

            int count = 3 + rand.nextInt(6);
            for (int i = 0; i < count; i++) {

                BlockPos blockpos = pos.add(rand.nextInt(16), rand.nextInt(28) + 4, rand.nextInt(16));
                IBlockState state = worldIn.getBlockState(blockpos);

                if (state.getBlock().isReplaceableOreGen(state, worldIn, blockpos, BlockMatcher.forBlock(Blocks.STONE))) {
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
// TODO: [Clean-up] This call to a base biomes `decorate` method is the source of extra ore generation.
//                  This ore gen happens in addition to the ore gen that takes place when CPRTG#generateOres is called from CPRTG#doPopulate
    public void rDecorateSeedBiome(Biome biome, World world, Random rand, int worldX, int worldZ, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {

        if (strength > 0.3f) {
            biome.decorate(world, rand, new BlockPos(worldX, 0, worldZ));
        }
    }

    public void rPopulatePreDecorate(IChunkGenerator ichunkgenerator, World worldObj, Random rand, ChunkProviderSettingsRTG settings, int chunkX, int chunkZ, boolean villageBuilding) {

        int worldX = chunkX * 16;
        int worldZ = chunkZ * 16;
        boolean gen;

        // Underground water lakes.
        if (settings.useWaterUndLakes) {

            gen = TerrainGen.populate(ichunkgenerator, worldObj, rand, chunkX, chunkZ, villageBuilding, PopulateChunkEvent.Populate.EventType.LAKE);

            if (gen && (rbb.waterUndergroundLakeChance() > 0)) {

                int x = worldX + rand.nextInt(16);
                int z = worldZ + rand.nextInt(16);
                int maxy = (settings.seaLevel - 8 > 0) ? settings.seaLevel - 8 : 1;
                int y = rand.nextInt(maxy) + 4;

                if (rand.nextInt(settings.waterUndLakeChance) == 0 && rand.nextInt(rbb.waterUndergroundLakeChance()) == 0) {

                    (new WorldGenLakes(Blocks.WATER)).generate(worldObj, rand, new BlockPos(new BlockPos(x, y, z)));
                }
            }
        }

        // Surface water lakes.
        if (settings.useWaterLakes && !villageBuilding) {

            gen = TerrainGen.populate(ichunkgenerator, worldObj, rand, chunkX, chunkZ, false, PopulateChunkEvent.Populate.EventType.LAKE);

            if (gen && (settings.waterLakeChance > 0) && (rbb.waterSurfaceLakeChance() > 0)) {

                int x = worldX + rand.nextInt(16);
                int z = worldZ + rand.nextInt(16);
                int y = worldObj.getHeight(new BlockPos(x, 0, z)).getY();

                //Surface lakes.
                if (rand.nextInt(settings.waterLakeChance) == 0 && rand.nextInt(rbb.waterSurfaceLakeChance()) == 0) {

                    if (y > settings.seaLevel) {

                        (new WorldGenPond(Blocks.WATER.getDefaultState())).generate(worldObj, rand, new BlockPos(x, y, z));
                    }
                }
            }
        }


        // Underground lava lakes.
        if (settings.useLavaUndLakes) {

            gen = TerrainGen.populate(ichunkgenerator, worldObj, rand, chunkX, chunkZ, villageBuilding, PopulateChunkEvent.Populate.EventType.LAVA);

            if (gen && (rbb.lavaUndergroundLakeChance() > 0)) {

                int x = worldX + rand.nextInt(16);
                int z = worldZ + rand.nextInt(16);
                int maxy = (settings.seaLevel - 8 > 0) ? settings.seaLevel - 8 : 1;
                int y = rand.nextInt(maxy) + 4;

                if (rand.nextInt(settings.lavaUndLakeChance) == 0 && rand.nextInt(rbb.lavaUndergroundLakeChance()) == 0) {

                    (new WorldGenLakes(Blocks.LAVA)).generate(worldObj, rand, new BlockPos(x, y, z));
                }
            }
        }

        // Surface lava lakes.
        if (settings.useLavaLakes && !villageBuilding) {

            gen = TerrainGen.populate(ichunkgenerator, worldObj, rand, chunkX, chunkZ, false, PopulateChunkEvent.Populate.EventType.LAVA);

            if (gen && (rbb.lavaSurfaceLakeChance() > 0)) {

                int x = worldX + rand.nextInt(16);
                int z = worldZ + rand.nextInt(16);
                int y = worldObj.getHeight(new BlockPos(x, 0, z)).getY();

                if (rand.nextInt(settings.lavaLakeChance) == 0 && rand.nextInt(rbb.lavaSurfaceLakeChance()) == 0) {

                    if (y > settings.seaLevel) {

                        (new WorldGenPond(Blocks.LAVA.getDefaultState())).generate(worldObj, rand, new BlockPos(x, y, z));
                    }
                }
            }
        }

        if (settings.useDungeons) {

            gen = TerrainGen.populate(ichunkgenerator, worldObj, rand, chunkX, chunkZ, villageBuilding, PopulateChunkEvent.Populate.EventType.DUNGEON);

            if (gen) {

                for(int i = 0; i < settings.dungeonChance; i++) {

                    int x = worldX + rand.nextInt(16);
                    int z = worldZ + rand.nextInt(16);
                    int y = rand.nextInt(128);

                    (new WorldGenDungeons()).generate(worldObj, rand, new BlockPos(x, y, z));
                }
            }
        }
    }

    public void rPopulatePostDecorate(World worldObj, Random rand, ChunkProviderSettingsRTG settings, int chunkX, int chunkZ, boolean flag) {

        int worldX = chunkX * 16;
        int worldZ = chunkZ * 16;
        int worldHeight = worldObj.provider.getActualHeight();

        //Flowing water.
        if (settings.waterSpoutChance > 0) {

            if (rand.nextInt(settings.waterSpoutChance) == 0) {

                for(int i = 0; i < 50; i++) {

                    int x = worldX + rand.nextInt(16);
                    int z = worldZ + rand.nextInt(16);
                    int y = rand.nextInt(rand.nextInt(worldHeight - 16) + 10);

                    (new WorldGenLiquids(Blocks.FLOWING_WATER)).generate(worldObj, rand, new BlockPos(x, y, z));
                }
            }
        }

        //Flowing lava.
        if (settings.lavaSpoutChance > 0) {

            if (rand.nextInt(settings.lavaSpoutChance) == 0) {

                for(int i = 0; i < 20; i++) {

                    int x = worldX + rand.nextInt(16);
                    int z = worldZ + rand.nextInt(16);
                    int y = rand.nextInt(worldHeight / 2);

                    (new WorldGenLiquids(Blocks.FLOWING_LAVA)).generate(worldObj, rand, new BlockPos(x, y, z));
                }
            }
        }
    }

}