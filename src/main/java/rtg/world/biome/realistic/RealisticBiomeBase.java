package rtg.world.biome.realistic;

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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;
import rtg.api.biome.BiomeConfig;
import rtg.config.rtg.ConfigRTG;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.RandomUtil;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.RTGBiomeProvider;
import rtg.world.gen.feature.WorldGenClay;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceGeneric;
import rtg.world.gen.terrain.TerrainBase;

import java.util.Random;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CLAY;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.*;

public class RealisticBiomeBase extends BiomeBase {
    
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
    
    public RealisticBiomeBase(BiomeConfig config, BiomeGenBase biome) {
    
        this(config, biome, BiomeGenBase.river);
    }
    
    public RealisticBiomeBase(BiomeConfig config, BiomeGenBase biome, BiomeGenBase river) {
    
        super(biome.biomeID);
        
        if (config == null) throw new RuntimeException("Biome config cannot be NULL when instantiating a realistic biome.");

        this.config = config;

    	if (biome.biomeID == 160 && this instanceof rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaRedwoodTaigaHills) {

        	arrRealisticBiomeIds[161] = this;
        	this.biomeName = "Redwood Taiga Hills";

		} else {

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
        emeraldEmeraldMeta = (byte)0;
        emeraldStoneBlock = Blocks.stone;
        emeraldStoneMeta = (byte)0;
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
        
        this(config, b, riverbiome, t, new SurfaceBase[] {s});
        
        surfaceGeneric = new SurfaceGeneric(config, s.getTopBlock(), s.getFillerBlock());
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
                    
                    (new WorldGenLakes(Blocks.water)).generate(worldObj, rand, new BlockPos(new BlockPos(i2, l4, i8)));
                }
            }
        }
        
        // Surface water lakes.
        if (ConfigRTG.enableWaterSurfaceLakes) {
            
            if (gen && (waterSurfaceLakeChance > 0)) {
                
                int i2 = worldX + rand.nextInt(16) + 8;
                int i8 = worldZ + rand.nextInt(16) + 8;
                int l4 = worldObj.getHeight(new BlockPos(i2, 0, i8)).getY();
                
                //Surface lakes.
                if (rand.nextInt(waterSurfaceLakeChance) == 0 && (RandomUtil.getRandomInt(rand, 1, ConfigRTG.waterSurfaceLakeChance) == 1)) {

                    if (l4 > 63) {
                        
                        (new WorldGenLakes(Blocks.water)).generate(worldObj, rand, new BlockPos(i2, l4, i8));
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
                    
                    (new WorldGenLakes(Blocks.lava)).generate(worldObj, rand, new BlockPos(i2, l4, i8));
                }
            }
        }
        
        // Surface lava lakes.
        if (ConfigRTG.enableLavaSurfaceLakes) {
            
            if (gen && (lavaSurfaceLakeChance > 0)) {
                
                int i2 = worldX + rand.nextInt(16) + 8;
                int i8 = worldZ + rand.nextInt(16) + 8;
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
            
            gen = TerrainGen.populate(ichunkprovider, worldObj, rand, chunkX, chunkZ, flag, PopulateChunkEvent.Populate.EventType.DUNGEON);
            for(int k1 = 0; k1 < 8 && gen; k1++)
            {
                int j5 = worldX + rand.nextInt(16) + 8;
                int k8 = rand.nextInt(128);
                int j11 = worldZ + rand.nextInt(16) + 8;
                
                (new WorldGenDungeons()).generate(worldObj, rand, new BlockPos(j5, k8, j11));
            }
        }
    }

    public void rDecorate(World world, Random rand, BlockPos blockPos, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {
        this.rDecorate(world, rand, blockPos.getX(), blockPos.getZ(), simplex, cell, strength, river);
    }
    public void rDecorate(World world, Random rand, int x, int z, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {
    
        if (strength > 0.3f) {
            baseBiome.decorate(world, rand, new BlockPos(x, 0, z));
        }
        else {
            rOreGenSeedBiome(world, rand, new BlockPos(x, 0, z), simplex, cell, strength, river, baseBiome);
        }
    }
    
    public void rPopulatePostDecorate(IChunkProvider ichunkprovider, World worldObj, Random rand, int chunkX, int chunkZ, boolean flag)
    {
        /**
         * Has emerald gen been disabled in the configs?
         * If so, check to see if this biome generated emeralds & remove them if necessary.
         */
        if (!ConfigRTG.generateOreEmerald && generatesEmeralds) {
            rRemoveEmeralds(worldObj, rand, chunkX, chunkZ);
        }
    }
    
    /**
     * When manually decorating biomes by overriding rDecorate(), sometimes you want the biome
     * to partially decorate itself. That's what this method does... it calls the biome's decorate() method.
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
     * This method should be called if both of the following conditions are true:
     * 
     * 1) You are manually decorating a biome by overrding rDecorate().
     * 2) You are NOT calling rDecorateSeedBiome() within rDecorate().
     */
    public void rOreGenSeedBiome(World world, Random rand, BlockPos blockPos, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, BiomeGenBase seedBiome) {

        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(world, rand, blockPos));
        //there is probably a better way to do this
        String s = world.getWorldInfo().getGeneratorOptions();

        if (s != null)
        {
            seedBiome.theBiomeDecorator.chunkProviderSettings = ChunkProviderSettings.Factory.jsonToFactory(s).func_177864_b();
        }
        else
        {
            seedBiome.theBiomeDecorator.chunkProviderSettings = ChunkProviderSettings.Factory.jsonToFactory("").func_177864_b();
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

        if (TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.dirtGen, blockPos, DIRT))
        genStandardOre1(20, seedBiome.theBiomeDecorator.dirtGen, 0, 256, world, rand, blockPos);
        if (TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.gravelGen, blockPos, GRAVEL))
        genStandardOre1(10, seedBiome.theBiomeDecorator.gravelGen, 0, 256, world, rand, blockPos);
        if (TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.coalGen, blockPos, COAL))
        genStandardOre1(20, seedBiome.theBiomeDecorator.coalGen, 0, 128, world, rand, blockPos);
        if (TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.ironGen, blockPos, IRON))
        genStandardOre1(20, seedBiome.theBiomeDecorator.ironGen, 0, 64, world, rand, blockPos);
        if (TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.goldGen, blockPos, GOLD))
        genStandardOre1(2, seedBiome.theBiomeDecorator.goldGen, 0, 32, world, rand, blockPos);
        if (TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.redstoneGen, blockPos, REDSTONE))
        genStandardOre1(8, seedBiome.theBiomeDecorator.redstoneGen, 0, 16, world, rand, blockPos);
        if (TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.diamondGen, blockPos, DIAMOND))
        genStandardOre1(1, seedBiome.theBiomeDecorator.diamondGen, 0, 16, world, rand, blockPos);
        if (TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.lapisGen, blockPos, LAPIS))
        genStandardOre2(1, seedBiome.theBiomeDecorator.lapisGen, 16, 16, world, rand, blockPos);
        
        if (ConfigRTG.generateOreEmerald && generatesEmeralds) {
            rGenerateEmeralds(world, rand, blockPos);
        }
        
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(world, rand, blockPos));
    }
    
    public void generateMapGen(ChunkPrimer primer, Long seed, World world, RTGBiomeProvider cmr, Random mapRand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float noise[]) {
    
        int k = 5;
        mapRand.setSeed(seed);
        long l = (mapRand.nextLong() / 2L) * 2L + 1L;
        long l1 = (mapRand.nextLong() / 2L) * 2L + 1L;
        for (int baseX = chunkX - k; baseX <= chunkX + k; baseX++) {
            for (int baseY = chunkY - k; baseY <= chunkY + k; baseY++) {
                mapRand.setSeed((long) baseX * l + (long) baseY * l1 ^ seed);
                rMapGen(primer, world, cmr, mapRand, baseX, baseY, chunkX, chunkY, simplex, cell, noise);
            }
        }
    }
    
    public void rMapGen(ChunkPrimer primer, World world, RTGBiomeProvider cmr, Random mapRand, int chunkX, int chunkY, int baseX, int baseY, OpenSimplexNoise simplex, CellNoise cell, float noise[]) {
    
    }
    
    public float rNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
    
        return terrain.generateNoise(simplex, cell, x, y, border, river);
    }
    
    public void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {

        if (ConfigRTG.enableRTGBiomeSurfaces && this.config.getPropertyById(BiomeConfig.useRTGSurfacesId).valueBoolean) {
            
            for (int s = 0; s < surfacesLength; s++) {
                
                surfaces[s].paintTerrain(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
            }
        }
        else {
            
            this.surfaceGeneric.paintTerrain(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
        }
    }
    
    public float r3Dnoise(float z) {
    
        return 0f;
    }
    
    public void rDecorateClay(World worldObj, Random rand, int chunkX, int chunkZ, float river, int worldX, int worldZ)
    {
        if (TerrainGen.decorate(worldObj, rand, new BlockPos(chunkX, 0, chunkZ), CLAY)) {
            
            if (river > 0.85f) {
                
                for (int j2 = 0; j2 < 3; j2++) {
                    
                    int l5 = worldX + rand.nextInt(16);
                    int i9 = 53 + rand.nextInt(15);
                    int l11 = worldZ + rand.nextInt(16);
                    
                    (new WorldGenClay(Blocks.clay, 0, clayPerVein)).generate(worldObj, rand, new BlockPos(l5, i9, l11));
                }
            }
        }
    }
    
    /**
     * Standard ore generation helper. Generates most ores.
     * @see net.minecraft.world.biome.BiomeDecorator
     */
    protected void genStandardOre1(int numBlocks, WorldGenerator oreGen, int minY, int maxY, World worldObj, Random rand, BlockPos blockPos)
    {
        for (int l = 0; l < numBlocks; ++l)
        {
            int i1 = blockPos.getX() + rand.nextInt(16);
            int j1 = rand.nextInt(maxY - minY) + minY;
            int k1 = blockPos.getY() + rand.nextInt(16);
            oreGen.generate(worldObj, rand, new BlockPos(i1, j1, k1));
        }
    }
    
    /**
     * Standard ore generation helper. Generates Lapis Lazuli.
     * @see net.minecraft.world.biome.BiomeDecorator
     */
    protected void genStandardOre2(int numBlocks, WorldGenerator oreGen, int minY, int maxY, World worldObj, Random rand, BlockPos blockPos)
    {
        for (int l = 0; l < numBlocks; ++l)
        {
            int i1 = blockPos.getX() + rand.nextInt(16);
            int j1 = rand.nextInt(maxY) + rand.nextInt(maxY) + (minY - maxY);
            int k1 = blockPos.getY() + rand.nextInt(16);
            oreGen.generate(worldObj, rand, new BlockPos(new BlockPos(i1, j1, k1)));
        }
    }

    public void rGenerateEmeralds(World world, Random rand, BlockPos blockPos)
    {
        int k = 3 + rand.nextInt(6);
        BlockPos.MutableBlockPos mbp = new BlockPos.MutableBlockPos();

        for (int l = 0; l < k; ++l)
        {
            mbp.set(blockPos.getX() + rand.nextInt(16),
                    rand.nextInt(28) + 4,
                    blockPos.getZ() + rand.nextInt(16));

            if (world.getBlockState(mbp).getBlock().isReplaceableOreGen(world, mbp, BlockHelper.forBlock(emeraldStoneBlock)))
            {
                world.setBlockState(mbp, emeraldEmeraldBlock.getStateFromMeta(emeraldEmeraldMeta), 2);
            }
        }
    }
    
    public void rRemoveEmeralds(World world, Random rand, int chunkX, int chunkZ)
    {
        int endX = (chunkX * 16) + 16;
        int endZ = (chunkZ * 16) + 16;
        boolean enableDebugging = ConfigRTG.enableDebugging;

        // Get the highest possible existing block location.
        int maxY = world.getHeight(new BlockPos(chunkX, 0, chunkZ)).getY();
        BlockPos.MutableBlockPos mbp = new BlockPos.MutableBlockPos();
        for (int x = chunkX * 16; x < endX; ++x)
        {
            for (int z = chunkZ * 16; z < endZ; ++z)
            {
                for (int y = 0; y < maxY; ++y)
                {   
                    if (world.getBlockState(mbp.set(x,y,z)).getBlock().isReplaceableOreGen(world, mbp, BlockHelper.forBlock(emeraldEmeraldBlock))) {
                        
                        world.setBlockState(mbp, emeraldStoneBlock.getStateFromMeta(emeraldStoneMeta), 2);
                    }
                }
            }
        }
    }
    
    public TerrainBase getTerrain()
    {
        return this.terrain;
    }
    
    public SurfaceBase getSurface()
    {
        if (this.surfacesLength == 0) {
            
            throw new RuntimeException(
                "No realistic surfaces found for " + this.baseBiome.biomeName + " (" + this.baseBiome.biomeID + ")."
            );
        }
        
        return this.surfaces[0];
    }
    
    public SurfaceBase[] getSurfaces()
    {
        return this.surfaces;
    }
}
