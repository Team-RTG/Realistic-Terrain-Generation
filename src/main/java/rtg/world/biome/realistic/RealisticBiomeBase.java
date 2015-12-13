package rtg.world.biome.realistic;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CLAY;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.COAL;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIAMOND;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIRT;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GOLD;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GRAVEL;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.IRON;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.LAPIS;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.REDSTONE;

import java.util.Random;

import org.apache.logging.log4j.Level;

import rtg.config.rtg.ConfigRTG;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.RandomUtil;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.gen.feature.WorldGenClay;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenMinable;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class RealisticBiomeBase extends BiomeBase {
    
    private static final RealisticBiomeBase[] arrRealisticBiomeIds = new RealisticBiomeBase[256];
    
    public final BiomeGenBase baseBiome;
    public final BiomeGenBase riverBiome;
    public String realisticBiomeName = null;
    public BiomeSize biomeSize;
    public int biomeWeight = 10;
    
    public TerrainBase terrain;
    
    public SurfaceBase[] surfaces;
    public int surfacesLength;
    
    public int waterSurfaceLakeChance; //Lower = more frequent
    public int lavaSurfaceLakeChance; //Lower = more frequent
    
    public int waterUndergroundLakeChance; //Lower = more frequent
    public int lavaUndergroundLakeChance; //Lower = more frequent
    
    public int clayPerChunk;
    public int dirtPerChunk;
    public int gravelPerChunk;
    public int coalPerChunk;
    public int ironPerChunk;
    public int goldPerChunk;
    public int redstonePerChunk;
    public int diamondPerChunk;
    public int lapisPerChunk;
    
    public boolean generateVillages;
    
    public Block emeraldEmeraldBlock;
    public byte emeraldEmeraldMeta;
    public Block emeraldStoneBlock;
    public byte emeraldStoneMeta;
    
    public RealisticBiomeBase(BiomeGenBase biome) {
    
        this(biome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE));
    }
    
    public RealisticBiomeBase(BiomeGenBase biome, BiomeGenBase river) {
    
        super(biome.biomeID);
        
        arrRealisticBiomeIds[biome.biomeID] = this;
                
        baseBiome = biome;
        riverBiome = river;
        
        waterSurfaceLakeChance = 10;
        lavaSurfaceLakeChance = 0; // Disabled.
        
        waterUndergroundLakeChance = 1;
        lavaUndergroundLakeChance = 1;
        
        clayPerChunk = 20;
        dirtPerChunk = 32;
        gravelPerChunk = 32;
        coalPerChunk = 16;
        ironPerChunk = 8;
        goldPerChunk = 8;
        redstonePerChunk = 7;
        diamondPerChunk = 7;
        lapisPerChunk = 6;
        
        generateVillages = true;
        
        emeraldEmeraldBlock = Blocks.emerald_ore;
        emeraldEmeraldMeta = (byte)0;
        emeraldStoneBlock = Blocks.stone;
        emeraldStoneMeta = (byte)0;
    }
    
    public static RealisticBiomeBase getBiome(int id) {
    
        return arrRealisticBiomeIds[id];
    }
    
    public RealisticBiomeBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase[] s) {
    
        this(b, riverbiome);
        
        terrain = t;
        
        surfaces = s;
        surfacesLength = s.length;
    }
    
    public RealisticBiomeBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s) {
    
        this(b, riverbiome, t, new SurfaceBase[] {s});
    }
    
    public void rPopulatePreDecorate(IChunkProvider ichunkprovider, World worldObj, Random rand, int chunkX, int chunkZ, boolean flag)
    {
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
                    
                    (new WorldGenLakes(Blocks.water)).generate(worldObj, rand, i2, l4, i8);
                }
            }
        }
        
        // Surface water lakes.
        if (ConfigRTG.enableWaterSurfaceLakes) {
            
            if (gen && (waterSurfaceLakeChance > 0)) {
                
                int i2 = worldX + rand.nextInt(16) + 8;
                int i8 = worldZ + rand.nextInt(16) + 8;
                int l4 = worldObj.getHeightValue(i2, i8);
                
                //Surface lakes.
                if (rand.nextInt(waterSurfaceLakeChance) == 0 && (RandomUtil.getRandomInt(rand, 1, ConfigRTG.waterSurfaceLakeChance) == 1)) {

                    if (l4 > 63) {
                        
                        (new WorldGenLakes(Blocks.water)).generate(worldObj, rand, i2, l4, i8);
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
                    
                    (new WorldGenLakes(Blocks.lava)).generate(worldObj, rand, i2, l4, i8);
                }
            }
        }
        
        // Surface lava lakes.
        if (ConfigRTG.enableLavaSurfaceLakes) {
            
            if (gen && (lavaSurfaceLakeChance > 0)) {
                
                int i2 = worldX + rand.nextInt(16) + 8;
                int i8 = worldZ + rand.nextInt(16) + 8;
                int l4 = worldObj.getHeightValue(i2, i8);
                
                //Surface lakes.
                if (rand.nextInt(lavaSurfaceLakeChance) == 0 && (RandomUtil.getRandomInt(rand, 1, ConfigRTG.lavaSurfaceLakeChance) == 1)) {

                    if (l4 > 63) {
                        
                        (new WorldGenLakes(Blocks.lava)).generate(worldObj, rand, i2, l4, i8);
                    }
                }
            }
        }
        
        if (ConfigRTG.generateDungeons) {
            
            gen = TerrainGen.populate(ichunkprovider, worldObj, rand, chunkX, chunkZ, flag, PopulateChunkEvent.Populate.EventType.DUNGEON);
            for(int k1 = 0; k1 < 8 && gen; k1++)
            {
                int j5 = worldX + rand.nextInt(16) + 8;
                int k8 = rand.nextInt(128);
                int j11 = worldZ + rand.nextInt(16) + 8;
                
                (new WorldGenDungeons()).generate(worldObj, rand, j5, k8, j11);
            }
        }
    }
    
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {
    
        if (strength > 0.3f) {
            baseBiome.decorate(world, rand, chunkX, chunkY);
        }
    }
    
    public void rPopulatePostDecorate()
    {
        
    }
    
    public static void rDecorateSeedBiome(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, BiomeGenBase seedBiome) {
        
        if (strength > 0.3f) {
            seedBiome.decorate(world, rand, chunkX, chunkY);
        }
    }
    
    public void generateMapGen(Block[] blocks, byte[] metadata, Long seed, World world, WorldChunkManagerRTG cmr, Random mapRand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float noise[]) {
    
        int k = 5;
        mapRand.setSeed(seed);
        long l = (mapRand.nextLong() / 2L) * 2L + 1L;
        long l1 = (mapRand.nextLong() / 2L) * 2L + 1L;
        for (int baseX = chunkX - k; baseX <= chunkX + k; baseX++) {
            for (int baseY = chunkY - k; baseY <= chunkY + k; baseY++) {
                mapRand.setSeed((long) baseX * l + (long) baseY * l1 ^ seed);
                rMapGen(blocks, metadata, world, cmr, mapRand, baseX, baseY, chunkX, chunkY, simplex, cell, noise);
            }
        }
    }
    
    public void rMapGen(Block[] blocks, byte[] metadata, World world, WorldChunkManagerRTG cmr, Random mapRand, int chunkX, int chunkY, int baseX, int baseY, OpenSimplexNoise simplex, CellNoise cell, float noise[]) {
    
    }
    
    public float rNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
    
        return terrain.generateNoise(simplex, cell, x, y, border, river);
    }
    
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {
    
        for (int s = 0; s < surfacesLength; s++) {
            surfaces[s].paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
        }
    }
    
    public float r3Dnoise(float z) {
    
        return 0f;
    }
    
    public String getRealisticBiomeName() {
    
        return this.realisticBiomeName;
    }
    
    public void setRealisticBiomeName(String n) {
    
        this.realisticBiomeName = n;
    }
    
    public void rGenerateOres(World worldObj, Random rand, int worldX, int worldZ)
    {
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(worldObj, rand, worldX, worldZ));
        
        rGenerateOreDirt(worldObj, rand, worldX, worldZ);
        rGenerateOreGravel(worldObj, rand, worldX, worldZ);
        rGenerateOreCoal(worldObj, rand, worldX, worldZ);
        rGenerateOreIron(worldObj, rand, worldX, worldZ);
        rGenerateOreGold(worldObj, rand, worldX, worldZ);
        rGenerateOreRedstone(worldObj, rand, worldX, worldZ);
        rGenerateOreDiamond(worldObj, rand, worldX, worldZ);
        rGenerateOreLapis(worldObj, rand, worldX, worldZ);
        
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(worldObj, rand, worldX, worldZ));
    }
    
    public void rGenerateOreDirt(World worldObj, Random rand, int worldX, int worldZ)
    {
        WorldGenMinable ore_dirt = new WorldGenMinable(Blocks.dirt, dirtPerChunk);
        
        if (TerrainGen.generateOre(worldObj, rand, ore_dirt, worldX, worldZ, DIRT)) {
            
            for (int j2 = 0; j2 < 10; j2++) {
                
                int l5 = worldX + rand.nextInt(16);
                int i9 = rand.nextInt(64);
                int l11 = worldZ + rand.nextInt(16);
                
                ore_dirt.generate(worldObj, rand, l5, i9, l11);
            }
        }
    }
    
    public void rGenerateOreGravel(World worldObj, Random rand, int worldX, int worldZ)
    {
        WorldGenMinable ore_gravel = new WorldGenMinable(Blocks.gravel, gravelPerChunk);
        
        if (TerrainGen.generateOre(worldObj, rand, ore_gravel, worldX, worldZ, GRAVEL)) {
            
            for (int k2 = 0; k2 < 5; k2++) {
                
                int i6 = worldX + rand.nextInt(16);
                int j9 = rand.nextInt(64);
                int i12 = worldZ + rand.nextInt(16);
                
                ore_gravel.generate(worldObj, rand, i6, j9, i12);
            }
        }
    }
    
    public void rGenerateOreCoal(World worldObj, Random rand, int worldX, int worldZ)
    {
        WorldGenMinable ore_coal = new WorldGenMinable(Blocks.coal_ore, coalPerChunk);
        
        if (ConfigRTG.generateOreCoal && TerrainGen.generateOre(worldObj, rand, ore_coal, worldX, worldZ, COAL)) {
            
            for (int i3 = 0; i3 < 20; i3++) {
                
                int j6 = worldX + rand.nextInt(16);
                int k9 = rand.nextInt(128);
                int j12 = worldZ + rand.nextInt(16);
                
                ore_coal.generate(worldObj, rand, j6, k9, j12);
            }
        }
    }
    
    public void rGenerateOreIron(World worldObj, Random rand, int worldX, int worldZ)
    {
        WorldGenMinable ore_iron = new WorldGenMinable(Blocks.iron_ore, ironPerChunk);
        
        if (ConfigRTG.generateOreIron && TerrainGen.generateOre(worldObj, rand, ore_iron, worldX, worldZ, IRON)) {
            
            for (int j3 = 0; j3 < 20; j3++) {
                
                int k6 = worldX + rand.nextInt(16);
                int l9 = rand.nextInt(64);
                int k12 = worldZ + rand.nextInt(16);
                
                ore_iron.generate(worldObj, rand, k6, l9, k12);
            }
        }
    }
    
    public void rGenerateOreGold(World worldObj, Random rand, int worldX, int worldZ)
    {
        WorldGenMinable ore_gold = new WorldGenMinable(Blocks.gold_ore, goldPerChunk);
        
        if (ConfigRTG.generateOreGold && TerrainGen.generateOre(worldObj, rand, ore_gold, worldX, worldZ, GOLD)) {
            
            for (int k3 = 0; k3 < 2; k3++) {
                
                int l6 = worldX + rand.nextInt(16);
                int i10 = rand.nextInt(32);
                int l12 = worldZ + rand.nextInt(16);
                
                ore_gold.generate(worldObj, rand, l6, i10, l12);
            }
        }
    }
    
    public void rGenerateOreRedstone(World worldObj, Random rand, int worldX, int worldZ)
    {
        WorldGenMinable ore_redstone = new WorldGenMinable(Blocks.redstone_ore, redstonePerChunk);
        
        if (ConfigRTG.generateOreRedstone && TerrainGen.generateOre(worldObj, rand, ore_redstone, worldX, worldZ, REDSTONE)) {
            
            for (int l3 = 0; l3 < 8; l3++) {
                
                int i7 = worldX + rand.nextInt(16);
                int j10 = rand.nextInt(16);
                int i13 = worldZ + rand.nextInt(16);
                
                ore_redstone.generate(worldObj, rand, i7, j10, i13);
            }
        }
    }
    
    public void rGenerateOreDiamond(World worldObj, Random rand, int worldX, int worldZ)
    {
        WorldGenMinable ore_diamond = new WorldGenMinable(Blocks.diamond_ore, diamondPerChunk);
        
        if (ConfigRTG.generateOreDiamond && TerrainGen.generateOre(worldObj, rand, ore_diamond, worldX, worldZ, DIAMOND)) {
            
            for (int i4 = 0; i4 < 1; i4++) {
                
                int j7 = worldX + rand.nextInt(16);
                int k10 = rand.nextInt(16);
                int j13 = worldZ + rand.nextInt(16);
                
                ore_diamond.generate(worldObj, rand, j7, k10, j13);
            }
        }
    }
    
    public void rGenerateOreLapis(World worldObj, Random rand, int worldX, int worldZ)
    {
        WorldGenMinable ore_lapis = new WorldGenMinable(Blocks.lapis_ore, lapisPerChunk);
        
        if (ConfigRTG.generateOreLapis && TerrainGen.generateOre(worldObj, rand, ore_lapis, worldX, worldZ, LAPIS)) {
            
            for (int j4 = 0; j4 < 1; j4++) {
                
                int k7 = worldX + rand.nextInt(16);
                int l10 = rand.nextInt(16) + rand.nextInt(16);
                int k13 = worldZ + rand.nextInt(16);
                
                ore_lapis.generate(worldObj, rand, k7, l10, k13);
            }
        }
    }
    
    public void rDecorateClay(World worldObj, Random rand, int chunkX, int chunkZ, float river, int worldX, int worldZ)
    {
        if (TerrainGen.decorate(worldObj, rand, chunkX, chunkZ, CLAY)) {
            
            if (river > 0.85f) {
                
                for (int j2 = 0; j2 < 3; j2++) {
                    
                    int l5 = worldX + rand.nextInt(16);
                    int i9 = 53 + rand.nextInt(15);
                    int l11 = worldZ + rand.nextInt(16);
                    
                    (new WorldGenClay(Blocks.clay, 0, clayPerChunk)).generate(worldObj, rand, l5, i9, l11);
                }
            }
        }
    }
    
    public void rGenerateEmeralds(World world, Random rand, int chunkX, int chunkZ, boolean forceGeneration)
    {
        if (ConfigRTG.generateOreEmerald || forceGeneration) {
            
            for (int g12 = 0; g12 < 1; ++g12) {
                
                int n1 = chunkX + rand.nextInt(16);
                int m1 = rand.nextInt(28) + 4;
                int p1 = chunkZ + rand.nextInt(16);

                if (world.getBlock(n1, m1, p1).isReplaceableOreGen(world, n1, m1, p1, emeraldStoneBlock)) {
                    
                    if (rand.nextInt(4) == 0) {
                        
                        world.setBlock(n1, m1, p1, emeraldEmeraldBlock, emeraldEmeraldMeta, 2);
                        
                        if (ConfigRTG.enableDebugging) {
                            FMLLog.log(Level.INFO, "Emerald generated at %d, %d, %d", n1, m1, p1);
                        }
                    }
                }
            }
        }
    }
    
    public void rRemoveEmeralds(World world, Random rand, int chunkX, int chunkZ, boolean forceRemoval)
    {
        if (!ConfigRTG.generateOreEmerald || forceRemoval) {

            int endX = (chunkX * 16) + 16;
            int endZ = (chunkZ * 16) + 16;
            boolean enableDebugging = ConfigRTG.enableDebugging;

            // Get the highest possible existing block location.
            int maxY = world.getHeightValue(chunkX, chunkZ);
            
            for (int x = chunkX * 16; x < endX; ++x)
            {
                for (int z = chunkZ * 16; z < endZ; ++z)
                {
                    for (int y = 0; y < maxY; ++y)
                    {   
                        if (world.getBlock(x, y, z).isReplaceableOreGen(world, x, y, z, emeraldEmeraldBlock)) {
                            
                            world.setBlock(x, y, z, emeraldStoneBlock, emeraldStoneMeta, 2);
                            
                            if (enableDebugging) {
                                FMLLog.log(Level.INFO, "Emerald replaced at %d, %d, %d", x, y, z);
                            }
                        }
                    }
                }
            }
        }
    }
}
