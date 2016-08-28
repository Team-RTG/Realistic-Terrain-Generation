package rtg.world.biome;

import java.util.Random;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.feature.*;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import rtg.world.biome.realistic.RealisticBiomeBase;

public class BiomeDecoratorRTG extends BiomeDecorator
{
    public boolean decorating;
    public BlockPos chunkPos;
    public ChunkProviderSettings chunkProviderSettings;
    public WorldGenerator clayGen = new WorldGenClay(4);
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
    public int clayPerChunk = 1;
    public int bigMushroomsPerChunk;
    public boolean generateLakes = true;

    public RealisticBiomeBase rbb;
    public Biome biome;

    public BiomeDecoratorRTG(RealisticBiomeBase rbb) {

        this.rbb = rbb;
        this.biome = rbb.baseBiome;
    }

    public void decorate(World worldIn, Random random, BlockPos pos)
    {
        if (this.decorating)
        {
            throw new RuntimeException("Already decorating");
        }
        else
        {
            this.chunkProviderSettings = ChunkProviderSettings.Factory.jsonToFactory(worldIn.getWorldInfo().getGeneratorOptions()).build();
            this.chunkPos = pos;
            this.dirtGen = new WorldGenMinable(Blocks.DIRT.getDefaultState(), this.chunkProviderSettings.dirtSize);
            this.gravelGen = new WorldGenMinable(Blocks.GRAVEL.getDefaultState(), this.chunkProviderSettings.gravelSize);
            this.graniteGen = new WorldGenMinable(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE), this.chunkProviderSettings.graniteSize);
            this.dioriteGen = new WorldGenMinable(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE), this.chunkProviderSettings.dioriteSize);
            this.andesiteGen = new WorldGenMinable(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE), this.chunkProviderSettings.andesiteSize);
            this.coalGen = new WorldGenMinable(Blocks.COAL_ORE.getDefaultState(), this.chunkProviderSettings.coalSize);
            this.ironGen = new WorldGenMinable(Blocks.IRON_ORE.getDefaultState(), this.chunkProviderSettings.ironSize);
            this.goldGen = new WorldGenMinable(Blocks.GOLD_ORE.getDefaultState(), this.chunkProviderSettings.goldSize);
            this.redstoneGen = new WorldGenMinable(Blocks.REDSTONE_ORE.getDefaultState(), this.chunkProviderSettings.redstoneSize);
            this.diamondGen = new WorldGenMinable(Blocks.DIAMOND_ORE.getDefaultState(), this.chunkProviderSettings.diamondSize);
            this.lapisGen = new WorldGenMinable(Blocks.LAPIS_ORE.getDefaultState(), this.chunkProviderSettings.lapisSize);
            this.genDecorations(worldIn, random);
            this.decorating = false;
        }
    }

    protected void genDecorations(World worldIn, Random random)
    {
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(worldIn, random, chunkPos));

        this.decorateOres(worldIn, random);
        this.decorateSand(worldIn, random);
        this.decorateClay(worldIn, random);
        this.decorateSandPass2(worldIn, random);
        this.decorateTrees(worldIn, random);
        this.decorateBigMushrooms(worldIn, random);
        this.decorateFlowers(worldIn, random);
        this.decorateGrass(worldIn, random);
        this.decorateDeadBushes(worldIn, random);
        this.decorateLilypads(worldIn, random);
        this.decorateMushrooms(worldIn, random);
        this.decorateReeds(worldIn, random);
        this.decoratePumpkins(worldIn, random);
        this.decorateCacti(worldIn, random);
        this.decoratePonds(worldIn, random);

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(worldIn, random, chunkPos));
    }

    protected void decorateSand(World worldIn, Random random)
    {
        if(TerrainGen.decorate(worldIn, random, chunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SAND))
            for (int i = 0; i < this.sandPerChunk2; ++i)
            {
                int j = random.nextInt(16) + 8;
                int k = random.nextInt(16) + 8;
                this.sandGen.generate(worldIn, random, worldIn.getTopSolidOrLiquidBlock(this.chunkPos.add(j, 0, k)));
            }
    }

    protected void decorateClay(World worldIn, Random random)
    {
        if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.CLAY))
            for (int i1 = 0; i1 < this.clayPerChunk; ++i1)
            {
                int l1 = random.nextInt(16) + 8;
                int i6 = random.nextInt(16) + 8;
                this.clayGen.generate(worldIn, random, worldIn.getTopSolidOrLiquidBlock(this.chunkPos.add(l1, 0, i6)));
            }
    }

    protected void decorateSandPass2(World worldIn, Random random)
    {
        if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.SAND_PASS2))
            for (int j1 = 0; j1 < this.sandPerChunk; ++j1)
            {
                int i2 = random.nextInt(16) + 8;
                int j6 = random.nextInt(16) + 8;
                this.gravelAsSandGen.generate(worldIn, random, worldIn.getTopSolidOrLiquidBlock(this.chunkPos.add(i2, 0, j6)));
            }
    }

    protected void decorateTrees(World worldIn, Random random)
    {
        int k1 = this.treesPerChunk;

        if (random.nextFloat() < this.field_189870_A)
        {
            ++k1;
        }

        if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.TREE))
            for (int j2 = 0; j2 < k1; ++j2)
            {
                int k6 = random.nextInt(16) + 8;
                int l = random.nextInt(16) + 8;
                WorldGenAbstractTree worldgenabstracttree = this.biome.genBigTreeChance(random);
                worldgenabstracttree.setDecorationDefaults();
                BlockPos blockpos = worldIn.getHeight(this.chunkPos.add(k6, 0, l));

                if (worldgenabstracttree.generate(worldIn, random, blockpos))
                {
                    worldgenabstracttree.generateSaplings(worldIn, random, blockpos);
                }
            }
    }

    protected void decorateBigMushrooms(World worldIn, Random random)
    {
        if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.BIG_SHROOM))
            for (int k2 = 0; k2 < this.bigMushroomsPerChunk; ++k2)
            {
                int l6 = random.nextInt(16) + 8;
                int k10 = random.nextInt(16) + 8;
                this.bigMushroomGen.generate(worldIn, random, worldIn.getHeight(this.chunkPos.add(l6, 0, k10)));
            }
    }

    protected void decorateFlowers(World worldIn, Random random)
    {
        if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.FLOWERS))
            for (int l2 = 0; l2 < this.flowersPerChunk; ++l2)
            {
                int i7 = random.nextInt(16) + 8;
                int l10 = random.nextInt(16) + 8;
                int j14 = worldIn.getHeight(this.chunkPos.add(i7, 0, l10)).getY() + 32;

                if (j14 > 0)
                {
                    int k17 = random.nextInt(j14);
                    BlockPos blockpos1 = this.chunkPos.add(i7, k17, l10);
                    BlockFlower.EnumFlowerType blockflower$enumflowertype = this.biome.pickRandomFlower(random, blockpos1);
                    BlockFlower blockflower = blockflower$enumflowertype.getBlockType().getBlock();

                    if (blockflower.getDefaultState().getMaterial() != Material.AIR)
                    {
                        this.yellowFlowerGen.setGeneratedBlock(blockflower, blockflower$enumflowertype);
                        this.yellowFlowerGen.generate(worldIn, random, blockpos1);
                    }
                }
            }
    }

    protected void decorateGrass(World worldIn, Random random)
    {
        if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.GRASS))
            for (int i3 = 0; i3 < this.grassPerChunk; ++i3)
            {
                int j7 = random.nextInt(16) + 8;
                int i11 = random.nextInt(16) + 8;
                int k14 = worldIn.getHeight(this.chunkPos.add(j7, 0, i11)).getY() * 2;

                if (k14 > 0)
                {
                    int l17 = random.nextInt(k14);
                    this.biome.getRandomWorldGenForGrass(random).generate(worldIn, random, this.chunkPos.add(j7, l17, i11));
                }
            }
    }

    protected void decorateDeadBushes(World worldIn, Random random)
    {
        if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.DEAD_BUSH))
            for (int j3 = 0; j3 < this.deadBushPerChunk; ++j3)
            {
                int k7 = random.nextInt(16) + 8;
                int j11 = random.nextInt(16) + 8;
                int l14 = worldIn.getHeight(this.chunkPos.add(k7, 0, j11)).getY() * 2;

                if (l14 > 0)
                {
                    int i18 = random.nextInt(l14);
                    (new WorldGenDeadBush()).generate(worldIn, random, this.chunkPos.add(k7, i18, j11));
                }
            }
    }

    protected void decorateLilypads(World worldIn, Random random)
    {
        if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.LILYPAD))
            for (int k3 = 0; k3 < this.waterlilyPerChunk; ++k3)
            {
                int l7 = random.nextInt(16) + 8;
                int k11 = random.nextInt(16) + 8;
                int i15 = worldIn.getHeight(this.chunkPos.add(l7, 0, k11)).getY() * 2;

                if (i15 > 0)
                {
                    int j18 = random.nextInt(i15);
                    BlockPos blockpos4;
                    BlockPos blockpos7;

                    for (blockpos4 = this.chunkPos.add(l7, j18, k11); blockpos4.getY() > 0; blockpos4 = blockpos7)
                    {
                        blockpos7 = blockpos4.down();

                        if (!worldIn.isAirBlock(blockpos7))
                        {
                            break;
                        }
                    }

                    this.waterlilyGen.generate(worldIn, random, blockpos4);
                }
            }
    }

    protected void decorateMushrooms(World worldIn, Random random)
    {
        if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.SHROOM))
        {
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

    protected void decorateReeds(World worldIn, Random random)
    {
        if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.REED))
        {
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

    protected void decoratePumpkins(World worldIn, Random random)
    {
        if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.PUMPKIN))
            if (random.nextInt(32) == 0)
            {
                int i5 = random.nextInt(16) + 8;
                int k9 = random.nextInt(16) + 8;
                int j13 = worldIn.getHeight(this.chunkPos.add(i5, 0, k9)).getY() * 2;

                if (j13 > 0)
                {
                    int k16 = random.nextInt(j13);
                    (new WorldGenPumpkin()).generate(worldIn, random, this.chunkPos.add(i5, k16, k9));
                }
            }
    }

    protected void decorateCacti(World worldIn, Random random)
    {
        if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.CACTUS))
            for (int j5 = 0; j5 < this.cactiPerChunk; ++j5)
            {
                int l9 = random.nextInt(16) + 8;
                int k13 = random.nextInt(16) + 8;
                int l16 = worldIn.getHeight(this.chunkPos.add(l9, 0, k13)).getY() * 2;

                if (l16 > 0)
                {
                    int j19 = random.nextInt(l16);
                    this.cactusGen.generate(worldIn, random, this.chunkPos.add(l9, j19, k13));
                }
            }
    }

    protected void decoratePonds(World worldIn, Random random)
    {
        if (this.generateLakes)
        {
            if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.LAKE_WATER))
                for (int k5 = 0; k5 < 50; ++k5)
                {
                    int i10 = random.nextInt(16) + 8;
                    int l13 = random.nextInt(16) + 8;
                    int i17 = random.nextInt(248) + 8;

                    if (i17 > 0)
                    {
                        int k19 = random.nextInt(i17);
                        BlockPos blockpos6 = this.chunkPos.add(i10, k19, l13);
                        (new WorldGenLiquids(Blocks.FLOWING_WATER)).generate(worldIn, random, blockpos6);
                    }
                }

            if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.LAKE_LAVA))
                for (int l5 = 0; l5 < 20; ++l5)
                {
                    int j10 = random.nextInt(16) + 8;
                    int i14 = random.nextInt(16) + 8;
                    int j17 = random.nextInt(random.nextInt(random.nextInt(240) + 8) + 8);
                    BlockPos blockpos3 = this.chunkPos.add(j10, j17, i14);
                    (new WorldGenLiquids(Blocks.FLOWING_LAVA)).generate(worldIn, random, blockpos3);
                }
        }
    }

    protected void decorateOres(World worldIn, Random random)
    {
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(worldIn, random, chunkPos));
        if (TerrainGen.generateOre(worldIn, random, dirtGen, chunkPos, OreGenEvent.GenerateMinable.EventType.DIRT))
            this.genStandardOre1(worldIn, random, this.chunkProviderSettings.dirtCount, this.dirtGen, this.chunkProviderSettings.dirtMinHeight, this.chunkProviderSettings.dirtMaxHeight);
        if (TerrainGen.generateOre(worldIn, random, gravelGen, chunkPos, OreGenEvent.GenerateMinable.EventType.GRAVEL))
            this.genStandardOre1(worldIn, random, this.chunkProviderSettings.gravelCount, this.gravelGen, this.chunkProviderSettings.gravelMinHeight, this.chunkProviderSettings.gravelMaxHeight);
        if (TerrainGen.generateOre(worldIn, random, dioriteGen, chunkPos, OreGenEvent.GenerateMinable.EventType.DIORITE))
            this.genStandardOre1(worldIn, random, this.chunkProviderSettings.dioriteCount, this.dioriteGen, this.chunkProviderSettings.dioriteMinHeight, this.chunkProviderSettings.dioriteMaxHeight);
        if (TerrainGen.generateOre(worldIn, random, graniteGen, chunkPos, OreGenEvent.GenerateMinable.EventType.GRANITE))
            this.genStandardOre1(worldIn, random, this.chunkProviderSettings.graniteCount, this.graniteGen, this.chunkProviderSettings.graniteMinHeight, this.chunkProviderSettings.graniteMaxHeight);
        if (TerrainGen.generateOre(worldIn, random, andesiteGen, chunkPos, OreGenEvent.GenerateMinable.EventType.ANDESITE))
            this.genStandardOre1(worldIn, random, this.chunkProviderSettings.andesiteCount, this.andesiteGen, this.chunkProviderSettings.andesiteMinHeight, this.chunkProviderSettings.andesiteMaxHeight);
        if (TerrainGen.generateOre(worldIn, random, coalGen, chunkPos, OreGenEvent.GenerateMinable.EventType.COAL))
            this.genStandardOre1(worldIn, random, this.chunkProviderSettings.coalCount, this.coalGen, this.chunkProviderSettings.coalMinHeight, this.chunkProviderSettings.coalMaxHeight);
        if (TerrainGen.generateOre(worldIn, random, ironGen, chunkPos, OreGenEvent.GenerateMinable.EventType.IRON))
            this.genStandardOre1(worldIn, random, this.chunkProviderSettings.ironCount, this.ironGen, this.chunkProviderSettings.ironMinHeight, this.chunkProviderSettings.ironMaxHeight);
        if (TerrainGen.generateOre(worldIn, random, goldGen, chunkPos, OreGenEvent.GenerateMinable.EventType.GOLD))
            this.genStandardOre1(worldIn, random, this.chunkProviderSettings.goldCount, this.goldGen, this.chunkProviderSettings.goldMinHeight, this.chunkProviderSettings.goldMaxHeight);
        if (TerrainGen.generateOre(worldIn, random, redstoneGen, chunkPos, OreGenEvent.GenerateMinable.EventType.REDSTONE))
            this.genStandardOre1(worldIn, random, this.chunkProviderSettings.redstoneCount, this.redstoneGen, this.chunkProviderSettings.redstoneMinHeight, this.chunkProviderSettings.redstoneMaxHeight);
        if (TerrainGen.generateOre(worldIn, random, diamondGen, chunkPos, OreGenEvent.GenerateMinable.EventType.DIAMOND))
            this.genStandardOre1(worldIn, random, this.chunkProviderSettings.diamondCount, this.diamondGen, this.chunkProviderSettings.diamondMinHeight, this.chunkProviderSettings.diamondMaxHeight);
        if (TerrainGen.generateOre(worldIn, random, lapisGen, chunkPos, OreGenEvent.GenerateMinable.EventType.LAPIS))
            this.genStandardOre2(worldIn, random, this.chunkProviderSettings.lapisCount, this.lapisGen, this.chunkProviderSettings.lapisCenterHeight, this.chunkProviderSettings.lapisSpread);
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(worldIn, random, chunkPos));
    }

    protected void genStandardOre1(World worldIn, Random random, int blockCount, WorldGenerator generator, int minHeight, int maxHeight)
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

    protected void genStandardOre2(World worldIn, Random random, int blockCount, WorldGenerator generator, int centerHeight, int spread)
    {
        for (int i = 0; i < blockCount; ++i)
        {
            BlockPos blockpos = this.chunkPos.add(random.nextInt(16), random.nextInt(spread) + random.nextInt(spread) + centerHeight - spread, random.nextInt(16));
            generator.generate(worldIn, random, blockpos);
        }
    }
}