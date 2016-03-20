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

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.api.biome.BiomeConfig;
import rtg.config.rtg.ConfigRTG;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.RandomUtil;
import rtg.util.SimplexOctave;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.RTGBiomeProvider;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.feature.WorldGenClay;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceGeneric;
import rtg.world.gen.terrain.TerrainBase;

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
    
    public ArrayList<DecoBase> decos;
    public boolean useNewDecorationSystem = false;
    
    public RealisticBiomeBase(BiomeConfig config, BiomeGenBase biome) {
    
        this(config, biome, BiomeGenBase.river);
    }
    
    public RealisticBiomeBase(BiomeConfig config, BiomeGenBase biome, BiomeGenBase river) {
    
        super(biome.biomeID);
        
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
        
        decos = new ArrayList<DecoBase>();
        
        /**
         * By default, it is assumed that all realistic biomes will be decorated manually and not by the biome.
         * This includes ore generation since it's part of the decoration process.
         * We're adding this deco here in order to avoid having to explicitly add it
         * in every singe realistic biome.
         * If it does get added manually to let the base biome handle some or all of the decoration process,
         * this deco will get replaced with the new one.
         */
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		decoBaseBiomeDecorations.allowed = false;
		this.decos.add(decoBaseBiomeDecorations);
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
        else {
            rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
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
            seedBiome.decorate(world, rand, chunkX, chunkY);
        }
        else {
            rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, seedBiome);
        }
    }
    
    /**
     * This method should be called if both of the following conditions are true:
     * 
     * 1) You are manually decorating a biome by overrding rDecorate().
     * 2) You are NOT calling rDecorateSeedBiome() within rDecorate().
     */
    public void rOreGenSeedBiome(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, BiomeGenBase seedBiome) {

        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(world, rand, chunkX, chunkY));
        
        if (TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.dirtGen, chunkX, chunkY, DIRT))
        genStandardOre1(20, seedBiome.theBiomeDecorator.dirtGen, 0, 256, world, rand, chunkX, chunkY);
        if (TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.gravelGen, chunkX, chunkY, GRAVEL))
        genStandardOre1(10, seedBiome.theBiomeDecorator.gravelGen, 0, 256, world, rand, chunkX, chunkY);
        if (TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.coalGen, chunkX, chunkY, COAL))
        genStandardOre1(20, seedBiome.theBiomeDecorator.coalGen, 0, 128, world, rand, chunkX, chunkY);
        if (TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.ironGen, chunkX, chunkY, IRON))
        genStandardOre1(20, seedBiome.theBiomeDecorator.ironGen, 0, 64, world, rand, chunkX, chunkY);
        if (TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.goldGen, chunkX, chunkY, GOLD))
        genStandardOre1(2, seedBiome.theBiomeDecorator.goldGen, 0, 32, world, rand, chunkX, chunkY);
        if (TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.redstoneGen, chunkX, chunkY, REDSTONE))
        genStandardOre1(8, seedBiome.theBiomeDecorator.redstoneGen, 0, 16, world, rand, chunkX, chunkY);
        if (TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.diamondGen, chunkX, chunkY, DIAMOND))
        genStandardOre1(1, seedBiome.theBiomeDecorator.diamondGen, 0, 16, world, rand, chunkX, chunkY);
        if (TerrainGen.generateOre(world, rand, seedBiome.theBiomeDecorator.lapisGen, chunkX, chunkY, LAPIS))
        genStandardOre2(1, seedBiome.theBiomeDecorator.lapisGen, 16, 16, world, rand, chunkX, chunkY);
        
        if (ConfigRTG.generateOreEmerald && generatesEmeralds) {
            rGenerateEmeralds(world, rand, chunkX, chunkY);
        }
        
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(world, rand, chunkX, chunkY));
    }
    
    public void generateMapGen(Block[] blocks, byte[] metadata, Long seed, World world, RTGBiomeProvider cmr, Random mapRand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float noise[]) {
    
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
    
    public void rMapGen(Block[] blocks, byte[] metadata, World world, RTGBiomeProvider cmr, Random mapRand, int chunkX, int chunkY, int baseX, int baseY, OpenSimplexNoise simplex, CellNoise cell, float noise[]) {
    
    }
    
    public float rNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        // we now have both lakes and rivers lowering land

        if (noWaterFeatures) {
            return terrain.generateNoise(simplex, cell, x, y, border, 1f);
        }
        float lakeStrength = lakePressure(simplex,cell,x,y,border);
        //if (1>0) return 62f+lakeStrength*20;
        double lakeFlattening = this.lakeFlattening(lakeStrength, lakeWaterLevel, lakeDepressionLevel);
        if (lakeFlattening < river) river = (float)lakeFlattening;
        float riverFlattening = river*1.33f-0.33f;
        if (riverFlattening <0) riverFlattening = 0;
        float terrainNoise = terrain.generateNoise(simplex, cell, x, y, border, riverFlattening);
        //if (river<1.0) terrainNoise = 64f;
        return this.erodedNoise(simplex, cell, x, y, river, border, terrainNoise,lakeFlattening);
    }

    private static float actualRiverProportion = 300f/1300f;
    public float erodedNoise(OpenSimplexNoise simplex, CellNoise simplexCell,int x, int y, float river, float border, float biomeHeight, double lakeFlattening)
    {

        float r = 1f;
        // check if rivers need lowering
        if (river < actualRiverProportion) {
            r = river/actualRiverProportion;
        }
        //if (1>0) return 62f+r*10f;
        if ((r < 1f && biomeHeight > 57f))
        {
        	//New river curve function. No longer creates worldwide curve correlations along cardinal axes.
            //SimplexOctave.Disk jitter = new SimplexOctave.Disk();
            //simplex.riverJitter().evaluateNoise(x / 240.0, y / 240.0, jitter);
            //double pX = x + jitter.deltax() * 220f;
            //double pY = y + jitter.deltay() * 220f;

            //New cellular noise.
            //TODO move the initialization of the results in a way that's more efficient but still thread safe.
            //double[] results =simplexCell.river().eval(pX / 1875.0, pY / 1875.0);
            //float r =
            //float r = (float) cellBorder(results, 30.0 / 1300.0, 1.0);

            return (biomeHeight * (r))
                + ((57f + simplex.noise2(x / 12f, y / 12f) * 2f + simplex.noise2(x / 8f, y / 8f) * 1.5f) * (1f-r));
        }
        else
        {
            return biomeHeight;
        }
    }

    // lake calculations

    private float lakeInterval = 1470.0f;
    private double lakeWaterLevel = 0.0;// the lakeStrenght below which things should be below ater
    private double lakeDepressionLevel = 0.27;// the lakeStrength below which land should start to be lowered
    public boolean noLakes = false;
    public boolean noWaterFeatures = false;

    public float lakePressure(OpenSimplexNoise simplex, CellNoise simplexCell,int x, int y, float border) {
        if (noLakes) return 1f;
        SimplexOctave.Derivative jitter = new SimplexOctave.Derivative();
        simplex.riverJitter().evaluateNoise(x / 240.0, y / 240.0, jitter);
        double pX = x + jitter.deltax() * 110f;
        double pY = y + jitter.deltay() * 110f;
        simplex.mountain().evaluateNoise(x / 80.0, y / 80.0, jitter);
        pX += jitter.deltax() * 30f;
        pY += jitter.deltay() * 30f;
        simplex.mountain().evaluateNoise(x / 30.0, y / 30.0, jitter);
        pX += jitter.deltax() * 10f;
        pY += jitter.deltay() * 10f;
        //double results =simplexCell.river().noise(pX / lakeInterval, pY / lakeInterval,1.0);
        double [] lakeResults = simplexCell.river().eval((float)x/ lakeInterval, (float)y/ lakeInterval);
        float results = 1f-(float)((lakeResults[1]-lakeResults[0])/lakeResults[1]);
        if (results >1.01) throw new RuntimeException("" + lakeResults[0]+ " , "+lakeResults[1]);
        if (results<-.01) throw new RuntimeException("" + lakeResults[0]+ " , "+lakeResults[1]);
        //return simplexCell.river().noise((float)x/ lakeInterval, (float)y/ lakeInterval,1.0);
        return results;
    }

    public double lakeFlattening(double pressure, double bottomLevel, double topLevel) {
        // this number indicates a multiplier to height
        if (pressure > topLevel) return 1;
        if (pressure<bottomLevel) return 0;
        return (pressure-bottomLevel)/(topLevel-bottomLevel);
    }

    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {

        if (ConfigRTG.enableRTGBiomeSurfaces && this.config.getPropertyById(BiomeConfig.useRTGSurfacesId).valueBoolean) {
            
            for (int s = 0; s < surfacesLength; s++) {
                
                surfaces[s].paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
            }
        }
        else {
            
            this.surfaceGeneric.paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
        }
    }
    
    public float r3Dnoise(float z) {
    
        return 0f;
    }
    
    public void rDecorateClay(World worldObj, Random rand, int chunkX, int chunkZ, float river, int worldX, int worldZ)
    {
        if (TerrainGen.decorate(worldObj, rand, chunkX, chunkZ, CLAY)) {
            
            if (river > 0.85f) {
                
                for (int j2 = 0; j2 < 3; j2++) {
                    
                    int l5 = worldX + rand.nextInt(16);
                    int i9 = 53 + rand.nextInt(15);
                    int l11 = worldZ + rand.nextInt(16);
                    
                    (new WorldGenClay(Blocks.clay, 0, clayPerVein)).generate(worldObj, rand, l5, i9, l11);
                }
            }
        }
    }
    
    /**
     * Standard ore generation helper. Generates most ores.
     * @see net.minecraft.world.biome.BiomeDecorator
     */
    protected void genStandardOre1(int numBlocks, WorldGenerator oreGen, int minY, int maxY, World worldObj, Random rand, int chunkX, int chunkZ)
    {
        for (int l = 0; l < numBlocks; ++l)
        {
            int i1 = chunkX + rand.nextInt(16);
            int j1 = rand.nextInt(maxY - minY) + minY;
            int k1 = chunkZ + rand.nextInt(16);
            oreGen.generate(worldObj, rand, i1, j1, k1);
        }
    }
    
    /**
     * Standard ore generation helper. Generates Lapis Lazuli.
     * @see net.minecraft.world.biome.BiomeDecorator
     */
    protected void genStandardOre2(int numBlocks, WorldGenerator oreGen, int minY, int maxY, World worldObj, Random rand, int chunkX, int chunkZ)
    {
        for (int l = 0; l < numBlocks; ++l)
        {
            int i1 = chunkX + rand.nextInt(16);
            int j1 = rand.nextInt(maxY) + rand.nextInt(maxY) + (minY - maxY);
            int k1 = chunkZ + rand.nextInt(16);
            oreGen.generate(worldObj, rand, i1, j1, k1);
        }
    }

    public void rGenerateEmeralds(World world, Random rand, int chunkX, int chunkZ)
    {
        int k = 3 + rand.nextInt(6);
        int l;
        int i1;
        int j1;

        for (l = 0; l < k; ++l)
        {
            i1 = chunkX + rand.nextInt(16);
            j1 = rand.nextInt(28) + 4;
            int k1 = chunkZ + rand.nextInt(16);

            if (world.getBlock(i1, j1, k1).isReplaceableOreGen(world, i1, j1, k1, emeraldStoneBlock))
            {
                world.setBlock(i1, j1, k1, emeraldEmeraldBlock, emeraldEmeraldMeta, 2);
            }
        }
    }
    
    public void rRemoveEmeralds(World world, Random rand, int chunkX, int chunkZ)
    {
        int endX = (chunkX * 16) + 16;
        int endZ = (chunkZ * 16) + 16;

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
    
    public void decorateInAnOrderlyFashion(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
    	for (int i = 0; i < this.decos.size(); i++) {
    		this.decos.get(i).generate(this, world, rand, chunkX, chunkY, simplex, cell, strength, river);
    	}
    }
    
    /**
     * Adds a deco object to the list of biome decos.
     * The 'allowed' parameter allows us to pass biome config booleans dynamically when configuring the decos in the biome.
     * 
     * @param deco
     * @param allowed
     */
    public void addDeco(DecoBase deco, boolean allowed)
    {
    	if (allowed) {
    		
	    	if (deco instanceof DecoBaseBiomeDecorations) {
	    		
	        	for (int i = 0; i < this.decos.size(); i++) {
	        		
	        		if (this.decos.get(i) instanceof DecoBaseBiomeDecorations) {
	        			
	        			this.decos.remove(i);
	        			break;
	        		}
	        	}
	    	}
	    	
	    	this.decos.add(deco);
	    	this.useNewDecorationSystem = true;
    	}
    }
    
    /**
     * Convenience method for addDeco() where 'allowed' is assumed to be true.
     * 
     * @param deco
     */
    public void addDeco(DecoBase deco)
    {
    	this.addDeco(deco, true);
    }
}
