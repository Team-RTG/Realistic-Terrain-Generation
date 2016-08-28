package rtg.world.biome;

import java.util.Random;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.feature.*;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenClayRTG;

public class BiomeDecoratorRTG
{
    public boolean decorating;
    public BlockPos chunkPos;
    public ChunkProviderSettings chunkProviderSettings;
    public WorldGenerator clayGen = new WorldGenClayRTG(20); // Vanilla = 4
    public WorldGenerator sandGen = new WorldGenSand(Blocks.SAND, 7);
    public WorldGenerator gravelAsSandGen = new WorldGenSand(Blocks.GRAVEL, 6);
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
    public WorldGenFlowers yellowFlowerGen = new WorldGenFlowers(Blocks.YELLOW_FLOWER, BlockFlower.EnumFlowerType.DANDELION);
    public WorldGenerator mushroomBrownGen = new WorldGenBush(Blocks.BROWN_MUSHROOM);
    public WorldGenerator mushroomRedGen = new WorldGenBush(Blocks.RED_MUSHROOM);
    public WorldGenerator bigMushroomGen = new WorldGenBigMushroom();
    public WorldGenerator reedGen = new WorldGenReed();
    public WorldGenerator cactusGen = new WorldGenCactus();
    public WorldGenerator waterlilyGen = new WorldGenWaterlily();
    public int waterlilyPerChunk;
    public int treesPerChunk;
    public float field_189870_A = 0.1F;
    public int flowersPerChunk = 2;
    public int grassPerChunk = 1;
    public int deadBushPerChunk;
    public int mushroomsPerChunk;
    public int reedsPerChunk;
    public int cactiPerChunk;
    public int sandPerChunk = 1;
    public int sandPerChunk2 = 3;
    public int clayPerChunk = 3; // Vanilla = 1
    public int bigMushroomsPerChunk;
    public boolean generateLakes = true;

    public RealisticBiomeBase rbb;
    public Biome biome;

    public BiomeDecoratorRTG(RealisticBiomeBase rbb) {

        this.rbb = rbb;
        this.biome = rbb.baseBiome;
    }

    /*
     * This method is FOR REFERENCE ONLY.
     * Trying to call this method will result in a RuntimeException.
     */
    public void genDecorations(World worldIn, Random random, int worldX, int worldZ, OpenSimplexNoise simplex, CellNoise cell, float border, float river, boolean hasPlacedVillageBlocks)
    {
        if (true) {
            throw new RuntimeException("genDecorations() is for reference only.");
        }

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(worldIn, random, chunkPos));

        this.decorateOres(worldIn, random, worldX, worldZ, simplex, cell, border, river, hasPlacedVillageBlocks);
        this.decorateSand(worldIn, random, worldX, worldZ, simplex, cell, border, river, hasPlacedVillageBlocks);
        this.decorateClay(worldIn, random, worldX, worldZ, simplex, cell, border, river, hasPlacedVillageBlocks);
        this.decorateSandPass2(worldIn, random, worldX, worldZ, simplex, cell, border, river, hasPlacedVillageBlocks);
        this.decorateTrees(worldIn, random, worldX, worldZ, simplex, cell, border, river, hasPlacedVillageBlocks);
        this.decorateBigMushrooms(worldIn, random, worldX, worldZ, simplex, cell, border, river, hasPlacedVillageBlocks);
        this.decorateFlowers(worldIn, random, worldX, worldZ, simplex, cell, border, river, hasPlacedVillageBlocks);
        this.decorateGrass(worldIn, random, worldX, worldZ, simplex, cell, border, river, hasPlacedVillageBlocks);
        this.decorateDeadBushes(worldIn, random, worldX, worldZ, simplex, cell, border, river, hasPlacedVillageBlocks);
        this.decorateLilypads(worldIn, random, worldX, worldZ, simplex, cell, border, river, hasPlacedVillageBlocks);
        this.decorateMushrooms(worldIn, random, worldX, worldZ, simplex, cell, border, river, hasPlacedVillageBlocks);
        this.decorateReeds(worldIn, random, worldX, worldZ, simplex, cell, border, river, hasPlacedVillageBlocks);
        this.decoratePumpkins(worldIn, random, worldX, worldZ, simplex, cell, border, river, hasPlacedVillageBlocks);
        this.decorateCacti(worldIn, random, worldX, worldZ, simplex, cell, border, river, hasPlacedVillageBlocks);
        this.decoratePonds(worldIn, random, worldX, worldZ, simplex, cell, border, river, hasPlacedVillageBlocks);

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(worldIn, random, chunkPos));
    }

    public void decorateSand(World worldIn, Random random, int worldX, int worldZ, OpenSimplexNoise simplex, CellNoise cell, float border, float river, boolean hasPlacedVillageBlocks)
    {
        if (TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.SAND)) {
            for (int i = 0; i < this.sandPerChunk2; ++i) {
                int j = random.nextInt(16) + 8;
                int k = random.nextInt(16) + 8;
                this.sandGen.generate(worldIn, random, worldIn.getTopSolidOrLiquidBlock(this.chunkPos.add(j, 0, k)));
            }
        }
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

    public void decorateSandPass2(World worldIn, Random random, int worldX, int worldZ, OpenSimplexNoise simplex, CellNoise cell, float border, float river, boolean hasPlacedVillageBlocks)
    {
        if (TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.SAND_PASS2)) {
            for (int j1 = 0; j1 < this.sandPerChunk; ++j1) {
                int i2 = random.nextInt(16) + 8;
                int j6 = random.nextInt(16) + 8;
                this.gravelAsSandGen.generate(worldIn, random, worldIn.getTopSolidOrLiquidBlock(this.chunkPos.add(i2, 0, j6)));
            }
        }
    }

    public void decorateTrees(World worldIn, Random random, int worldX, int worldZ, OpenSimplexNoise simplex, CellNoise cell, float border, float river, boolean hasPlacedVillageBlocks)
    {
        int k1 = this.treesPerChunk;

        if (random.nextFloat() < this.field_189870_A)
        {
            ++k1;
        }

        if (TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.TREE)) {
            for (int j2 = 0; j2 < k1; ++j2) {
                int k6 = random.nextInt(16) + 8;
                int l = random.nextInt(16) + 8;
                WorldGenAbstractTree worldgenabstracttree = this.biome.genBigTreeChance(random);
                worldgenabstracttree.setDecorationDefaults();
                BlockPos blockpos = worldIn.getHeight(this.chunkPos.add(k6, 0, l));

                if (worldgenabstracttree.generate(worldIn, random, blockpos)) {
                    worldgenabstracttree.generateSaplings(worldIn, random, blockpos);
                }
            }
        }
    }

    public void decorateBigMushrooms(World worldIn, Random random, int worldX, int worldZ, OpenSimplexNoise simplex, CellNoise cell, float border, float river, boolean hasPlacedVillageBlocks)
    {
        if (TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.BIG_SHROOM)) {
            for (int k2 = 0; k2 < this.bigMushroomsPerChunk; ++k2) {
                int l6 = random.nextInt(16) + 8;
                int k10 = random.nextInt(16) + 8;
                this.bigMushroomGen.generate(worldIn, random, worldIn.getHeight(this.chunkPos.add(l6, 0, k10)));
            }
        }
    }

    public void decorateFlowers(World worldIn, Random random, int worldX, int worldZ, OpenSimplexNoise simplex, CellNoise cell, float border, float river, boolean hasPlacedVillageBlocks)
    {
        if (TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.FLOWERS)) {
            for (int l2 = 0; l2 < this.flowersPerChunk; ++l2) {
                int i7 = random.nextInt(16) + 8;
                int l10 = random.nextInt(16) + 8;
                int j14 = worldIn.getHeight(this.chunkPos.add(i7, 0, l10)).getY() + 32;

                if (j14 > 0) {
                    int k17 = random.nextInt(j14);
                    BlockPos blockpos1 = this.chunkPos.add(i7, k17, l10);
                    BlockFlower.EnumFlowerType blockflower$enumflowertype = this.biome.pickRandomFlower(random, blockpos1);
                    BlockFlower blockflower = blockflower$enumflowertype.getBlockType().getBlock();

                    if (blockflower.getDefaultState().getMaterial() != Material.AIR) {
                        this.yellowFlowerGen.setGeneratedBlock(blockflower, blockflower$enumflowertype);
                        this.yellowFlowerGen.generate(worldIn, random, blockpos1);
                    }
                }
            }
        }
    }

    public void decorateGrass(World worldIn, Random random, int worldX, int worldZ, OpenSimplexNoise simplex, CellNoise cell, float border, float river, boolean hasPlacedVillageBlocks)
    {
        if (TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.GRASS)) {
            for (int i3 = 0; i3 < this.grassPerChunk; ++i3) {
                int j7 = random.nextInt(16) + 8;
                int i11 = random.nextInt(16) + 8;
                int k14 = worldIn.getHeight(this.chunkPos.add(j7, 0, i11)).getY() * 2;

                if (k14 > 0) {
                    int l17 = random.nextInt(k14);
                    this.biome.getRandomWorldGenForGrass(random).generate(worldIn, random, this.chunkPos.add(j7, l17, i11));
                }
            }
        }
    }

    public void decorateDeadBushes(World worldIn, Random random, int worldX, int worldZ, OpenSimplexNoise simplex, CellNoise cell, float border, float river, boolean hasPlacedVillageBlocks)
    {
        if (TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.DEAD_BUSH)) {
            for (int j3 = 0; j3 < this.deadBushPerChunk; ++j3) {
                int k7 = random.nextInt(16) + 8;
                int j11 = random.nextInt(16) + 8;
                int l14 = worldIn.getHeight(this.chunkPos.add(k7, 0, j11)).getY() * 2;

                if (l14 > 0) {
                    int i18 = random.nextInt(l14);
                    (new WorldGenDeadBush()).generate(worldIn, random, this.chunkPos.add(k7, i18, j11));
                }
            }
        }
    }

    public void decorateLilypads(World worldIn, Random random, int worldX, int worldZ, OpenSimplexNoise simplex, CellNoise cell, float border, float river, boolean hasPlacedVillageBlocks)
    {
        if (TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.LILYPAD)) {
            for (int k3 = 0; k3 < this.waterlilyPerChunk; ++k3) {
                int l7 = random.nextInt(16) + 8;
                int k11 = random.nextInt(16) + 8;
                int i15 = worldIn.getHeight(this.chunkPos.add(l7, 0, k11)).getY() * 2;

                if (i15 > 0) {
                    int j18 = random.nextInt(i15);
                    BlockPos blockpos4;
                    BlockPos blockpos7;

                    for (blockpos4 = this.chunkPos.add(l7, j18, k11); blockpos4.getY() > 0; blockpos4 = blockpos7) {
                        blockpos7 = blockpos4.down();

                        if (!worldIn.isAirBlock(blockpos7)) {
                            break;
                        }
                    }

                    this.waterlilyGen.generate(worldIn, random, blockpos4);
                }
            }
        }
    }

    public void decorateMushrooms(World worldIn, Random random, int worldX, int worldZ, OpenSimplexNoise simplex, CellNoise cell, float border, float river, boolean hasPlacedVillageBlocks)
    {
        if (TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.SHROOM)) {

            for (int l3 = 0; l3 < this.mushroomsPerChunk; ++l3)
            {
                if (random.nextInt(4) == 0)
                {
                    int i8 = random.nextInt(16) + 8;
                    int l11 = random.nextInt(16) + 8;
                    BlockPos blockpos2 = worldIn.getHeight(this.chunkPos.add(i8, 0, l11));
                    this.mushroomBrownGen.generate(worldIn, random, blockpos2);
                }

                if (random.nextInt(8) == 0)
                {
                    int j8 = random.nextInt(16) + 8;
                    int i12 = random.nextInt(16) + 8;
                    int j15 = worldIn.getHeight(this.chunkPos.add(j8, 0, i12)).getY() * 2;

                    if (j15 > 0)
                    {
                        int k18 = random.nextInt(j15);
                        BlockPos blockpos5 = this.chunkPos.add(j8, k18, i12);
                        this.mushroomRedGen.generate(worldIn, random, blockpos5);
                    }
                }
            }

            if (random.nextInt(4) == 0)
            {
                int i4 = random.nextInt(16) + 8;
                int k8 = random.nextInt(16) + 8;
                int j12 = worldIn.getHeight(this.chunkPos.add(i4, 0, k8)).getY() * 2;

                if (j12 > 0)
                {
                    int k15 = random.nextInt(j12);
                    this.mushroomBrownGen.generate(worldIn, random, this.chunkPos.add(i4, k15, k8));
                }
            }

            if (random.nextInt(8) == 0)
            {
                int j4 = random.nextInt(16) + 8;
                int l8 = random.nextInt(16) + 8;
                int k12 = worldIn.getHeight(this.chunkPos.add(j4, 0, l8)).getY() * 2;

                if (k12 > 0)
                {
                    int l15 = random.nextInt(k12);
                    this.mushroomRedGen.generate(worldIn, random, this.chunkPos.add(j4, l15, l8));
                }
            }
        }
    }

    public void decorateReeds(World worldIn, Random random, int worldX, int worldZ, OpenSimplexNoise simplex, CellNoise cell, float border, float river, boolean hasPlacedVillageBlocks)
    {
        if (TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.REED)) {

            for (int k4 = 0; k4 < this.reedsPerChunk; ++k4)
            {
                int i9 = random.nextInt(16) + 8;
                int l12 = random.nextInt(16) + 8;
                int i16 = worldIn.getHeight(this.chunkPos.add(i9, 0, l12)).getY() * 2;

                if (i16 > 0)
                {
                    int l18 = random.nextInt(i16);
                    this.reedGen.generate(worldIn, random, this.chunkPos.add(i9, l18, l12));
                }
            }

            for (int l4 = 0; l4 < 10; ++l4)
            {
                int j9 = random.nextInt(16) + 8;
                int i13 = random.nextInt(16) + 8;
                int j16 = worldIn.getHeight(this.chunkPos.add(j9, 0, i13)).getY() * 2;

                if (j16 > 0)
                {
                    int i19 = random.nextInt(j16);
                    this.reedGen.generate(worldIn, random, this.chunkPos.add(j9, i19, i13));
                }
            }
        }
    }

    public void decoratePumpkins(World worldIn, Random random, int worldX, int worldZ, OpenSimplexNoise simplex, CellNoise cell, float border, float river, boolean hasPlacedVillageBlocks)
    {
        if (TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.PUMPKIN)) {
            if (random.nextInt(32) == 0) {
                int i5 = random.nextInt(16) + 8;
                int k9 = random.nextInt(16) + 8;
                int j13 = worldIn.getHeight(this.chunkPos.add(i5, 0, k9)).getY() * 2;

                if (j13 > 0) {
                    int k16 = random.nextInt(j13);
                    (new WorldGenPumpkin()).generate(worldIn, random, this.chunkPos.add(i5, k16, k9));
                }
            }
        }
    }

    public void decorateCacti(World worldIn, Random random, int worldX, int worldZ, OpenSimplexNoise simplex, CellNoise cell, float border, float river, boolean hasPlacedVillageBlocks)
    {
        if (TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.CACTUS)) {
            for (int j5 = 0; j5 < this.cactiPerChunk; ++j5) {
                int l9 = random.nextInt(16) + 8;
                int k13 = random.nextInt(16) + 8;
                int l16 = worldIn.getHeight(this.chunkPos.add(l9, 0, k13)).getY() * 2;

                if (l16 > 0) {
                    int j19 = random.nextInt(l16);
                    this.cactusGen.generate(worldIn, random, this.chunkPos.add(l9, j19, k13));
                }
            }
        }
    }

    public void decoratePonds(World worldIn, Random random, int worldX, int worldZ, OpenSimplexNoise simplex, CellNoise cell, float border, float river, boolean hasPlacedVillageBlocks)
    {
        if (this.generateLakes)
        {
            if (TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.LAKE_WATER)) {
                for (int k5 = 0; k5 < 50; ++k5) {
                    int i10 = random.nextInt(16) + 8;
                    int l13 = random.nextInt(16) + 8;
                    int i17 = random.nextInt(248) + 8;

                    if (i17 > 0) {
                        int k19 = random.nextInt(i17);
                        BlockPos blockpos6 = this.chunkPos.add(i10, k19, l13);
                        (new WorldGenLiquids(Blocks.FLOWING_WATER)).generate(worldIn, random, blockpos6);
                    }
                }
            }

            if (TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.LAKE_LAVA)) {
                for (int l5 = 0; l5 < 20; ++l5) {
                    int j10 = random.nextInt(16) + 8;
                    int i14 = random.nextInt(16) + 8;
                    int j17 = random.nextInt(random.nextInt(random.nextInt(240) + 8) + 8);
                    BlockPos blockpos3 = this.chunkPos.add(j10, j17, i14);
                    (new WorldGenLiquids(Blocks.FLOWING_LAVA)).generate(worldIn, random, blockpos3);
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
}