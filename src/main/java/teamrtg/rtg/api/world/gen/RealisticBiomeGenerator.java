package teamrtg.rtg.api.world.gen;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
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

import java.util.ArrayList;
import java.util.Random;

/**
 * @author topisani
 */
public class RealisticBiomeGenerator {
    private static final RealisticBiomeGenerator[] biomeGenerators = new RealisticBiomeGenerator[256];
    private static float actualRiverProportion = 300f / 1600f;
    public final RTGBiome realistic;
    public SurfacePart genericPart;

    public RealisticBiomeGenerator(RTGBiome realistic) {
        this.realistic = realistic;
        biomeGenerators[realistic.getID()] = this;
        genericPart = this.realistic.PARTS.surfaceGeneric();
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

                        (new WorldGenPond(Blocks.WATER)).generate(worldObj, rand, i2, l4, i8);
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

                        (new WorldGenPond(Blocks.LAVA)).generate(worldObj, rand, i2, l4, i8);
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
     * When manually decorating biomes by overriding rDecorate(), sometimes you want the biome
     * to partially decorate itself. That's what this method does... it calls the biome's decorate() method.
     */
    public void decorateBaseBiome(RTGWorld rtgWorld, int chunkX, int chunkY, float strength, float river) {
        if (strength > 0.3f) {
            this.realistic.getBiome().decorate(rtgWorld.world, rtgWorld.rand, new BlockPos(chunkX, 0, chunkY));
        } else {
            generateOres(rtgWorld, new BlockPos(chunkX, 0, chunkY), strength, river);
        }
    }

    // lake calculations

    /**
     * This method should be called if both of the following conditions are true:
     * <p/>
     * 1) You are manually decorating a biome by overrding rDecorate().
     * 2) You are NOT calling decorateBaseBiome() within rDecorate().
     */
    public void generateOres(RTGWorld rtgWorld, BlockPos blockPos, float strength, float river) {
        Biome seedBiome = this.realistic.getBiome();
        Random rand = rtgWorld.rand;

        if (seedBiome.theBiomeDecorator.chunkProviderSettings == null) {

            String s = rtgWorld.world.getWorldInfo().getGeneratorOptions();

            if (s != null) {
                seedBiome.theBiomeDecorator.chunkProviderSettings = ChunkProviderSettings.Factory.jsonToFactory(s).build();
            } else {
                seedBiome.theBiomeDecorator.chunkProviderSettings = ChunkProviderSettings.Factory.jsonToFactory("").build();
            }
        }
        if (Mods.RTG.config.GENERATE_ORES.get()) {
            seedBiome.theBiomeDecorator.dirtGen = new WorldGenMinable(Blocks.DIRT.getDefaultState(), seedBiome.theBiomeDecorator.chunkProviderSettings.dirtSize);
            seedBiome.theBiomeDecorator.gravelGen = new WorldGenMinable(Blocks.GRAVEL.getDefaultState(), seedBiome.theBiomeDecorator.chunkProviderSettings.gravelSize);
            seedBiome.theBiomeDecorator.graniteGen = new WorldGenMinable(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE), seedBiome.theBiomeDecorator.chunkProviderSettings.graniteSize);
            seedBiome.theBiomeDecorator.dioriteGen = new WorldGenMinable(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE), seedBiome.theBiomeDecorator.chunkProviderSettings.dioriteSize);
            seedBiome.theBiomeDecorator.andesiteGen = new WorldGenMinable(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE), seedBiome.theBiomeDecorator.chunkProviderSettings.andesiteSize);
            seedBiome.theBiomeDecorator.coalGen = new WorldGenMinable(Blocks.COAL_ORE.getDefaultState(), seedBiome.theBiomeDecorator.chunkProviderSettings.coalSize);
            seedBiome.theBiomeDecorator.ironGen = new WorldGenMinable(Blocks.IRON_ORE.getDefaultState(), seedBiome.theBiomeDecorator.chunkProviderSettings.ironSize);
            seedBiome.theBiomeDecorator.goldGen = new WorldGenMinable(Blocks.GOLD_ORE.getDefaultState(), seedBiome.theBiomeDecorator.chunkProviderSettings.goldSize);
            seedBiome.theBiomeDecorator.redstoneGen = new WorldGenMinable(Blocks.REDSTONE_ORE.getDefaultState(), seedBiome.theBiomeDecorator.chunkProviderSettings.redstoneSize);
            seedBiome.theBiomeDecorator.diamondGen = new WorldGenMinable(Blocks.DIAMOND_ORE.getDefaultState(), seedBiome.theBiomeDecorator.chunkProviderSettings.diamondSize);
            seedBiome.theBiomeDecorator.lapisGen = new WorldGenMinable(Blocks.LAPIS_ORE.getDefaultState(), seedBiome.theBiomeDecorator.chunkProviderSettings.lapisSize);

            net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(rtgWorld.world, rand, blockPos));
            if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(rtgWorld.world, rand, seedBiome.theBiomeDecorator.dirtGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIRT))
                oreGenHelper1(rtgWorld.world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.dirtCount, seedBiome.theBiomeDecorator.dirtGen, seedBiome.theBiomeDecorator.chunkProviderSettings.dirtMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.dirtMaxHeight);
            if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(rtgWorld.world, rand, seedBiome.theBiomeDecorator.gravelGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GRAVEL))
                oreGenHelper1(rtgWorld.world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.gravelCount, seedBiome.theBiomeDecorator.gravelGen, seedBiome.theBiomeDecorator.chunkProviderSettings.gravelMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.gravelMaxHeight);
            if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(rtgWorld.world, rand, seedBiome.theBiomeDecorator.dioriteGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIORITE))
                oreGenHelper1(rtgWorld.world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.dioriteCount, seedBiome.theBiomeDecorator.dioriteGen, seedBiome.theBiomeDecorator.chunkProviderSettings.dioriteMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.dioriteMaxHeight);
            if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(rtgWorld.world, rand, seedBiome.theBiomeDecorator.graniteGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GRANITE))
                oreGenHelper1(rtgWorld.world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.graniteCount, seedBiome.theBiomeDecorator.graniteGen, seedBiome.theBiomeDecorator.chunkProviderSettings.graniteMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.graniteMaxHeight);
            if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(rtgWorld.world, rand, seedBiome.theBiomeDecorator.andesiteGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.ANDESITE))
                oreGenHelper1(rtgWorld.world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.andesiteCount, seedBiome.theBiomeDecorator.andesiteGen, seedBiome.theBiomeDecorator.chunkProviderSettings.andesiteMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.andesiteMaxHeight);
            if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(rtgWorld.world, rand, seedBiome.theBiomeDecorator.coalGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.COAL))
                oreGenHelper1(rtgWorld.world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.coalCount, seedBiome.theBiomeDecorator.coalGen, seedBiome.theBiomeDecorator.chunkProviderSettings.coalMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.coalMaxHeight);
            if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(rtgWorld.world, rand, seedBiome.theBiomeDecorator.ironGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.IRON))
                oreGenHelper1(rtgWorld.world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.ironCount, seedBiome.theBiomeDecorator.ironGen, seedBiome.theBiomeDecorator.chunkProviderSettings.ironMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.ironMaxHeight);
            if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(rtgWorld.world, rand, seedBiome.theBiomeDecorator.goldGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GOLD))
                oreGenHelper1(rtgWorld.world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.goldCount, seedBiome.theBiomeDecorator.goldGen, seedBiome.theBiomeDecorator.chunkProviderSettings.goldMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.goldMaxHeight);
            if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(rtgWorld.world, rand, seedBiome.theBiomeDecorator.redstoneGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.REDSTONE))
                oreGenHelper1(rtgWorld.world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.redstoneCount, seedBiome.theBiomeDecorator.redstoneGen, seedBiome.theBiomeDecorator.chunkProviderSettings.redstoneMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.redstoneMaxHeight);
            if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(rtgWorld.world, rand, seedBiome.theBiomeDecorator.diamondGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIAMOND))
                oreGenHelper1(rtgWorld.world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.diamondCount, seedBiome.theBiomeDecorator.diamondGen, seedBiome.theBiomeDecorator.chunkProviderSettings.diamondMinHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.diamondMaxHeight);
            if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(rtgWorld.world, rand, seedBiome.theBiomeDecorator.lapisGen, blockPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.LAPIS))
                oreGenHelper2(rtgWorld.world, rand, blockPos, seedBiome.theBiomeDecorator.chunkProviderSettings.lapisCount, seedBiome.theBiomeDecorator.lapisGen, seedBiome.theBiomeDecorator.chunkProviderSettings.lapisCenterHeight, seedBiome.theBiomeDecorator.chunkProviderSettings.lapisSpread);

            net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(rtgWorld.world, rand, blockPos));
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

    public float terrainHeight(RTGWorld rtgWorld, int x, int z, float biomeWeight, float border, float river) {
        // we now have both lakes and rivers lowering land
        if (realistic.noWaterFeatures) {
            float borderForRiver = biomeWeight * 2;
            if (borderForRiver > 1f) borderForRiver = 1;
            river = 1f - (1f - borderForRiver) * (1f - river);
        }
        float height = realistic.terrain.generateNoise(rtgWorld, x, z, biomeWeight, border, river);
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

    public void decorate(RTGWorld rtgWorld, Random rand, int chunkY, int chunkX, float strength, float river) {
        boolean baseDecorated = false;
        ArrayList<DecoBase> decos = this.realistic.getDecos();
        for (int i = decos.size() - 1; i >= 0; i--) {
            DecoBase deco = decos.get(i);
            if (deco instanceof DecoBaseBiomeDecorations) {
                if (baseDecorated) continue;
                baseDecorated = true;
            }
            if (deco.preGenerate(rtgWorld, rand, chunkX, chunkY, strength, river, this)) {
                deco.generate(rtgWorld, rand, chunkX, chunkY, strength, river, this);
            }
        }
        // Generate ores
        if (!baseDecorated) {
            DecoBaseBiomeDecorations deco = new DecoBaseBiomeDecorations();
            deco.allowed = false;
            if (deco.preGenerate(rtgWorld, rand, chunkX, chunkY, strength, river, this)) {
                deco.generate(rtgWorld, rand, chunkX, chunkY, strength, river, this);
            }
        }
    }

}
