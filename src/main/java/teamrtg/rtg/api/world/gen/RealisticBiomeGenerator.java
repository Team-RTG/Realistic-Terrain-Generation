package teamrtg.rtg.api.world.gen;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.*;
import teamrtg.rtg.api.module.Mods;
import teamrtg.rtg.api.tools.feature.WorldGenPond;
import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.api.util.math.MathUtils;
import teamrtg.rtg.api.util.math.RandomUtil;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.RTGBiome;
import teamrtg.rtg.api.world.biome.WorldFeature;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;

/**
 * @author topisani
 */
public class RealisticBiomeGenerator {
    private static final RealisticBiomeGenerator[] biomeGenerators = new RealisticBiomeGenerator[256];
    private static float actualRiverProportion = 300f / 1600f;
    public final RTGBiome realistic;
    public SurfacePart genericPart;
    public DecoBaseBiomeDecorations baseBiomeDecorations;

    public RealisticBiomeGenerator(RTGBiome realistic) {
        this.realistic = realistic;
        biomeGenerators[realistic.getID()] = this;
        genericPart = this.realistic.PARTS.surfaceGeneric();
        baseBiomeDecorations = new DecoBaseBiomeDecorations();
    }

    public static RTGBiome getRealistic(Biome biome) {
        return RealisticBiomeGenerator.getRealistic(BiomeUtils.getId(biome));
    }

    public static RTGBiome getRealistic(int id) {
        try {
            return biomeGenerators[id].realistic;
        } catch (Exception e) {
            return null;
        }
    }

    public static RealisticBiomeGenerator forBiome(Biome biome) {
        return biomeGenerators[BiomeUtils.getId(biome)];
    }

    public static RealisticBiomeGenerator forBiome(int id) {
        return biomeGenerators[id];
    }

    public void populatePreDecorate(IChunkGenerator iChunkGenerator, World worldObj, Random rand, int chunkX, int chunkZ, boolean flag) {
        int worldX = chunkX * 16;
        int worldZ = chunkZ * 16;
        boolean gen = true;

        gen = TerrainGen.populate(iChunkGenerator, worldObj, rand, chunkX, chunkZ, flag, PopulateChunkEvent.Populate.EventType.LAKE);

        // Underground WATER lakes.
        if (Mods.RTG.config.ENABLE_UNDERGROUND_WATER_LAKES.get()) {

            if (gen && (Mods.RTG.config.UNDERGROUND_WATER_LAKE_CHANCE.get() > 0)) {

                int i2 = worldX + rand.nextInt(16) + 8;
                int l4 = RandomUtil.getRandomInt(rand, 1, 50);
                int i8 = worldZ + rand.nextInt(16) + 8;

                if (rand.nextInt(Mods.RTG.config.UNDERGROUND_WATER_LAKE_CHANCE.get()) == 0 && (RandomUtil.getRandomInt(rand, 1, Mods.RTG.config.UNDERGROUND_WATER_LAKE_CHANCE.get()) == 1)) {

                    (new WorldGenLakes(Blocks.WATER)).generate(worldObj, rand, new BlockPos(new BlockPos(i2, l4, i8)));
                }
            }
        }

        // Surface WATER lakes.
        if (Mods.RTG.config.ENABLE_SURFACE_WATER_LAKES.get()) {

            if (gen && (this.realistic.getConfig().WATER_POND_CHANCE.get() > 0)) {

                int i2 = worldX + rand.nextInt(16) + 8;
                int i8 = worldZ + rand.nextInt(16) + 8;
                int l4 = worldObj.getHeight(new BlockPos(i2, 0, i8)).getY();

                //Surface lakes.
                if (rand.nextInt(this.realistic.getConfig().WATER_POND_CHANCE.get()) == 0 && (RandomUtil.getRandomInt(rand, 1, this.realistic.getConfig().WATER_POND_CHANCE.get()) == 1)) {

                    if (l4 > 63) {

                        (new WorldGenPond(Blocks.WATER.getDefaultState())).generate(worldObj, rand, new BlockPos(i2, l4, i8));
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

                    (new WorldGenLakes(Blocks.LAVA)).generate(worldObj, rand, new BlockPos(i2, l4, i8));
                }
            }
        }

        // Surface lava lakes.
        if (Mods.RTG.config.ENABLE_SURFACE_LAVA_LAKES.get()) {

            if (gen && (this.realistic.getConfig().LAVA_POND_CHANCE.get() > 0)) {

                int i2 = worldX + rand.nextInt(16) + 8;
                int i8 = worldZ + rand.nextInt(16) + 8;
                int l4 = worldObj.getHeight(new BlockPos(i2, 0, i8)).getY();

                //Surface lakes.
                if (rand.nextInt(this.realistic.getConfig().LAVA_POND_CHANCE.get()) == 0 && (RandomUtil.getRandomInt(rand, 1, this.realistic.getConfig().LAVA_POND_CHANCE.get()) == 1)) {

                    if (l4 > 63) {

                        (new WorldGenPond(Blocks.LAVA.getDefaultState())).generate(worldObj, rand, new BlockPos(i2, l4, i8));
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
     * When realistically decorating biomes, sometimes you want the biome to partially decorate itself.
     * That's what this method does... it calls the biome's decorate() method.
     */
    public void decorateBaseBiome(RTGWorld rtgWorld, int chunkX, int chunkY, float strength, float river) {
        if (strength > 0.3f) {
            this.realistic.getBiome().decorate(rtgWorld.world, rtgWorld.rand, new BlockPos(chunkX, 0, chunkY));
        } else {
            generateOres(rtgWorld, new BlockPos(chunkX, 0, chunkY), strength, river);
        }
    }

    /**
     * This method generates ores in realistically-decorated biomes.
     */
    public void generateOres(RTGWorld rtgWorld, BlockPos blockPos, float strength, float river) {
        //if (true) return;
        Biome seedBiome = this.realistic.getBiome();
        final BiomeDecorator biomeDecorator = seedBiome.theBiomeDecorator;
        ChunkProviderSettings CPSettings = biomeDecorator.chunkProviderSettings;
        Random rand = rtgWorld.rand;

        if (CPSettings == null) {

            String s = rtgWorld.world.getWorldInfo().getGeneratorOptions();

            if (!Objects.equals(s, "")) {
                CPSettings = ChunkProviderSettings.Factory.jsonToFactory(s).build();
            } else {
                CPSettings = ChunkProviderSettings.Factory.jsonToFactory("").build();
            }
        }
        if (Mods.RTG.config.GENERATE_ORES.get()) {
            biomeDecorator.dirtGen = new WorldGenMinable(Blocks.DIRT.getDefaultState(), CPSettings.dirtSize);
            biomeDecorator.gravelGen = new WorldGenMinable(Blocks.GRAVEL.getDefaultState(), CPSettings.gravelSize);
            biomeDecorator.graniteGen = new WorldGenMinable(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE), CPSettings.graniteSize);
            biomeDecorator.dioriteGen = new WorldGenMinable(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE), CPSettings.dioriteSize);
            biomeDecorator.andesiteGen = new WorldGenMinable(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE), CPSettings.andesiteSize);
            biomeDecorator.coalGen = new WorldGenMinable(Blocks.COAL_ORE.getDefaultState(), CPSettings.coalSize);
            biomeDecorator.ironGen = new WorldGenMinable(Blocks.IRON_ORE.getDefaultState(), CPSettings.ironSize);
            biomeDecorator.goldGen = new WorldGenMinable(Blocks.GOLD_ORE.getDefaultState(), CPSettings.goldSize);
            biomeDecorator.redstoneGen = new WorldGenMinable(Blocks.REDSTONE_ORE.getDefaultState(), CPSettings.redstoneSize);
            biomeDecorator.diamondGen = new WorldGenMinable(Blocks.DIAMOND_ORE.getDefaultState(), CPSettings.diamondSize);
            biomeDecorator.lapisGen = new WorldGenMinable(Blocks.LAPIS_ORE.getDefaultState(), CPSettings.lapisSize);

            net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(rtgWorld.world, rand, blockPos));
            if (TerrainGen.generateOre(rtgWorld.world, rand, biomeDecorator.dirtGen, blockPos, OreGenEvent.GenerateMinable.EventType.DIRT))
                oreGenHelper1(rtgWorld.world, rand, blockPos, CPSettings.dirtCount, biomeDecorator.dirtGen, CPSettings.dirtMinHeight, CPSettings.dirtMaxHeight);
            if (TerrainGen.generateOre(rtgWorld.world, rand, biomeDecorator.gravelGen, blockPos, OreGenEvent.GenerateMinable.EventType.GRAVEL))
                oreGenHelper1(rtgWorld.world, rand, blockPos, CPSettings.gravelCount, biomeDecorator.gravelGen, CPSettings.gravelMinHeight, CPSettings.gravelMaxHeight);
            if (TerrainGen.generateOre(rtgWorld.world, rand, biomeDecorator.dioriteGen, blockPos, OreGenEvent.GenerateMinable.EventType.DIORITE))
                oreGenHelper1(rtgWorld.world, rand, blockPos, CPSettings.dioriteCount, biomeDecorator.dioriteGen, CPSettings.dioriteMinHeight, CPSettings.dioriteMaxHeight);
            if (TerrainGen.generateOre(rtgWorld.world, rand, biomeDecorator.graniteGen, blockPos, OreGenEvent.GenerateMinable.EventType.GRANITE))
                oreGenHelper1(rtgWorld.world, rand, blockPos, CPSettings.graniteCount, biomeDecorator.graniteGen, CPSettings.graniteMinHeight, CPSettings.graniteMaxHeight);
            if (TerrainGen.generateOre(rtgWorld.world, rand, biomeDecorator.andesiteGen, blockPos, OreGenEvent.GenerateMinable.EventType.ANDESITE))
                oreGenHelper1(rtgWorld.world, rand, blockPos, CPSettings.andesiteCount, biomeDecorator.andesiteGen, CPSettings.andesiteMinHeight, CPSettings.andesiteMaxHeight);
            if (TerrainGen.generateOre(rtgWorld.world, rand, biomeDecorator.coalGen, blockPos, OreGenEvent.GenerateMinable.EventType.COAL))
                oreGenHelper1(rtgWorld.world, rand, blockPos, CPSettings.coalCount, biomeDecorator.coalGen, CPSettings.coalMinHeight, CPSettings.coalMaxHeight);
            if (TerrainGen.generateOre(rtgWorld.world, rand, biomeDecorator.ironGen, blockPos, OreGenEvent.GenerateMinable.EventType.IRON))
                oreGenHelper1(rtgWorld.world, rand, blockPos, CPSettings.ironCount, biomeDecorator.ironGen, CPSettings.ironMinHeight, CPSettings.ironMaxHeight);
            if (TerrainGen.generateOre(rtgWorld.world, rand, biomeDecorator.goldGen, blockPos, OreGenEvent.GenerateMinable.EventType.GOLD))
                oreGenHelper1(rtgWorld.world, rand, blockPos, CPSettings.goldCount, biomeDecorator.goldGen, CPSettings.goldMinHeight, CPSettings.goldMaxHeight);
            if (TerrainGen.generateOre(rtgWorld.world, rand, biomeDecorator.redstoneGen, blockPos, OreGenEvent.GenerateMinable.EventType.REDSTONE))
                oreGenHelper1(rtgWorld.world, rand, blockPos, CPSettings.redstoneCount, biomeDecorator.redstoneGen, CPSettings.redstoneMinHeight, CPSettings.redstoneMaxHeight);
            if (TerrainGen.generateOre(rtgWorld.world, rand, biomeDecorator.diamondGen, blockPos, OreGenEvent.GenerateMinable.EventType.DIAMOND))
                oreGenHelper1(rtgWorld.world, rand, blockPos, CPSettings.diamondCount, biomeDecorator.diamondGen, CPSettings.diamondMinHeight, CPSettings.diamondMaxHeight);
            if (TerrainGen.generateOre(rtgWorld.world, rand, biomeDecorator.lapisGen, blockPos, OreGenEvent.GenerateMinable.EventType.LAPIS))
                oreGenHelper2(rtgWorld.world, rand, blockPos, CPSettings.lapisCount, biomeDecorator.lapisGen, CPSettings.lapisCenterHeight, CPSettings.lapisSpread);

            net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(rtgWorld.world, rand, blockPos));
        }
    }

    /**
     * Standard ore generation helper. Generates most ores.
     * @see net.minecraft.world.biome.BiomeDecorator
     */
    private void oreGenHelper1(World worldObj, Random rand, BlockPos blockPos, int blockCount, WorldGenerator generator, int minHeight, int maxHeight) {
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
    private void oreGenHelper2(World worldObj, Random rand, BlockPos blockPos, int blockCount, WorldGenerator generator, int centerHeight, int spread) {
        for (int i = 0; i < blockCount; ++i) {
            BlockPos blockpos = blockPos.add(rand.nextInt(16), rand.nextInt(spread) + rand.nextInt(spread) + centerHeight - spread, rand.nextInt(16));
            generator.generate(worldObj, rand, blockpos);
        }
    }

    public float terrainHeight(RTGWorld rtgWorld, int x, int z, float biomeWeight, float river) {
        // we now have both lakes and rivers lowering land
        if (realistic.noWaterFeatures) {
            float borderForRiver = biomeWeight * 2;
            if (borderForRiver > 1f) borderForRiver = 1;
            river = 1f - (1f - borderForRiver) * (1f - river);
        }
        // Border is temporarily disabled
        float height = realistic.terrain.generateNoise(rtgWorld, x, z, biomeWeight, 0f, river);
        for (WorldFeature feature : realistic.getWorldFeatures()) {
            height = feature.modifyTerrain(rtgWorld, realistic, height, x, z, biomeWeight, river);
        }
        return height;
    }

    public RTGBiome getBiome() {
        return this.realistic;
    }

    public void paintSurface(ChunkPrimer primer, int bx, int by, int bz, int depth, float[] noise, float river, RTGWorld rtgWorld) {
        Block b = primer.getBlockState(MathUtils.globalToLocal(bx), by, MathUtils.globalToLocal(bz)).getBlock();

        if (Mods.RTG.config.ENABLE_RTG_SURFACES.get() && this.realistic.getConfig().USE_RTG_SURFACES.get()) {
            this.realistic.surface.paintWithSubparts(primer, bx, by, bz, depth, noise, river, rtgWorld);
        } else {
            this.genericPart.paintWithSubparts(primer, bx, by, bz, depth, noise, river, rtgWorld);
        }
    }

    public void rDecorate(RTGWorld rtgWorld, Random rand, int chunkY, int chunkX, float strength, float river, boolean hasPlacedVillageBlocks) {
        boolean baseDecorated = false;
        ArrayList<DecoBase> decos = this.realistic.getDecos();
        for (int i = decos.size() - 1; i >= 0; i--) {
            DecoBase deco = decos.get(i);
            if (deco instanceof DecoBaseBiomeDecorations) {
                if (baseDecorated) continue;
                baseDecorated = true;
            }
            if (deco.preGenerate(rtgWorld, rand, chunkX, chunkY, strength, river, this, hasPlacedVillageBlocks)) {
                deco.generate(rtgWorld, rand, chunkX, chunkY, strength, river, this, hasPlacedVillageBlocks);
            }
        }
        // Generate ores
        if (!baseDecorated) {
            baseBiomeDecorations.allowed = false;
            if (baseBiomeDecorations.preGenerate(rtgWorld, rand, chunkX, chunkY, strength, river, this, hasPlacedVillageBlocks)) {
                baseBiomeDecorations.generate(rtgWorld, rand, chunkX, chunkY, strength, river, this, hasPlacedVillageBlocks);
            }
        }
    }

}
