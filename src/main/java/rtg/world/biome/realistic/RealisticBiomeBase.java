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
import rtg.util.PlaneLocation;
import rtg.util.RandomUtil;
import rtg.util.SimplexOctave;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.RTGBiomeProvider;
import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.collection.DecoCollectionBase;
import rtg.world.gen.feature.WorldGenClay;
import rtg.world.gen.feature.WorldGenPond;
import rtg.world.gen.feature.WorldGenVolcano;
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

    // lake calculations

    private float lakeInterval = 989.0f;
    private float lakeShoreLevel = 0.15f;
    private float lakeWaterLevel = 0.11f;// the lakeStrength below which things should be below water
    private float lakeDepressionLevel = 0.30f;// the lakeStrength below which land should start to be lowered
    public boolean noLakes = false;
    public boolean noWaterFeatures = false;

    private float largeBendSize = 100;
    private float mediumBendSize = 40;
    private float smallBendSize = 15;

    public boolean disallowStoneBeaches = false; // this is for rugged biomes that should have sand beaches
    public boolean disallowAllBeaches = false;

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
         *  Disable base biome decorations by default.
         *  This also needs to be here so that ores get generated.
         */
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		decoBaseBiomeDecorations.allowed = false;
		this.addDeco(decoBaseBiomeDecorations);

        // set the water feature constants with the config changes
        lakeInterval *= ConfigRTG.lakeFrequencyMultiplier;
        this.lakeWaterLevel *= ConfigRTG.lakeSizeMultiplier();
        this.lakeShoreLevel *= ConfigRTG.lakeSizeMultiplier();
        this.lakeDepressionLevel *= ConfigRTG.lakeSizeMultiplier();

        this.largeBendSize *= ConfigRTG.lakeFrequencyMultiplier;
        this.mediumBendSize *= ConfigRTG.lakeFrequencyMultiplier;
        this.smallBendSize *= ConfigRTG.lakeFrequencyMultiplier;
        
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
    
    public void rPopulatePreDecorate(IChunkProvider ichunkprovider, World worldObj, Random rand, int chunkX, int chunkZ, boolean villageBuilding)
    {
        int worldX = chunkX * 16;
        int worldZ = chunkZ * 16;
        boolean gen = true;
        
        gen = TerrainGen.populate(ichunkprovider, worldObj, rand, chunkX, chunkZ, villageBuilding, PopulateChunkEvent.Populate.EventType.LAKE);
        
        // Underground water lakes.
        if (ConfigRTG.enableWaterUndergroundLakes) {

            if (gen && (waterUndergroundLakeChance > 0)) {
                
                int i2 = worldX+ rand.nextInt(16);// + 8;
                int l4 = RandomUtil.getRandomInt(rand, 1, 50);
                int i8 = worldZ+ rand.nextInt(16);// + 8;
                
                if (rand.nextInt(waterUndergroundLakeChance) == 0 && (RandomUtil.getRandomInt(rand, 1, ConfigRTG.waterUndergroundLakeChance) == 1)) {
                    
                    (new WorldGenLakes(Blocks.water)).generate(worldObj, rand, i2, l4, i8);
                }
            }
        }
        
        // Surface water lakes.
        if (ConfigRTG.enableWaterSurfaceLakes&&!villageBuilding) {
            
            if (gen && (waterSurfaceLakeChance > 0)) {
                
                int i2 = worldX + rand.nextInt(16);// + 8;
                int i8 = worldZ+ rand.nextInt(16);// + 8;
                int l4 = worldObj.getHeightValue(i2, i8);
                
                //Surface lakes.
                if (rand.nextInt(waterSurfaceLakeChance) == 0 && (RandomUtil.getRandomInt(rand, 1, ConfigRTG.waterSurfaceLakeChance) == 1)) {

                    if (l4 > 63) {
                        
                        (new WorldGenPond(Blocks.water)).generate(worldObj, rand, i2, l4, i8);
                    }
                }
            }
        }

        gen = TerrainGen.populate(ichunkprovider, worldObj, rand, chunkX, chunkZ, villageBuilding, PopulateChunkEvent.Populate.EventType.LAVA);
        
        // Underground lava lakes.
        if (ConfigRTG.enableLavaUndergroundLakes) {

            if (gen && (lavaUndergroundLakeChance > 0)) {
                
                int i2 = worldX+ rand.nextInt(16);// + 8;
                int l4 = RandomUtil.getRandomInt(rand, 1, 50);
                int i8 = worldZ+ rand.nextInt(16);// + 8;
                
                if (rand.nextInt(lavaUndergroundLakeChance) == 0 && (RandomUtil.getRandomInt(rand, 1, ConfigRTG.lavaUndergroundLakeChance) == 1)) {
                    
                    (new WorldGenLakes(Blocks.lava)).generate(worldObj, rand, i2, l4, i8);
                }
            }
        }
        
        // Surface lava lakes.
        if (ConfigRTG.enableLavaSurfaceLakes&&!villageBuilding) {
            
            if (gen && (lavaSurfaceLakeChance > 0)) {
                
                int i2 = worldX+  rand.nextInt(16);// + 8;
                int i8 = worldZ+  rand.nextInt(16);// + 8;
                int l4 = worldObj.getHeightValue(i2, i8);
                
                //Surface lakes.
                if (rand.nextInt(lavaSurfaceLakeChance) == 0 && (RandomUtil.getRandomInt(rand, 1, ConfigRTG.lavaSurfaceLakeChance) == 1)) {

                    if (l4 > 63) {
                        
                        (new WorldGenPond(Blocks.lava)).generate(worldObj, rand, i2, l4, i8);
                    }
                }
            }
        }
        
        if (ConfigRTG.generateDungeons) {
            
            gen = TerrainGen.populate(ichunkprovider, worldObj, rand, chunkX, chunkZ, villageBuilding, PopulateChunkEvent.Populate.EventType.DUNGEON);
            
            if (gen) {
            	
	            for(int k1 = 0; k1 < ConfigRTG.dungeonFrequency; k1++) {
	            	
	                int j5 = worldX + rand.nextInt(16);// + 8;
	                int k8 = rand.nextInt(128);
	                int j11 = worldZ + rand.nextInt(16);// + 8;
	                
	                (new WorldGenDungeons()).generate(worldObj, rand, j5, k8, j11);
	            }
            }
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
     * This method is used by DecoBaseBiomeDecorations to allow the base biome to decorate itself.
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
     * This method is used by DecoBaseBiomeDecorations to generate ores when the base biome 
     * doesn't decorate itself.
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

    public void rMapVolcanoes(Block[] blocks, byte[] metadata, World world, RTGBiomeProvider cmr, Random mapRand, int baseX, int baseY, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float noise[]) {
    	
    	RealisticBiomeBase neighbourBiome = getBiome((((WorldChunkManagerRTG) cmr).getBiomeGenAt(baseX * 16, baseY * 16).biomeID));
    	
    	boolean allowed = !ConfigRTG.enableVolcanoes ? false : neighbourBiome.config._boolean(BiomeConfig.allowVolcanoesId);
    	if (!allowed) {
    		return;
    	}
    	
    	int chance = neighbourBiome.config._int(BiomeConfig.volcanoChanceId) == -1 ? ConfigRTG.volcanoChance : neighbourBiome.config._int(BiomeConfig.volcanoChanceId);
    	if (chance < 1) {
    		return;
    	}
    	
        if (baseX % 4 == 0 && baseY % 4 == 0 && mapRand.nextInt(chance) == 0) {

            float river = cmr.getRiverStrength(baseX * 16, baseY * 16) + 1f;
            if (river > 0.98f && cmr.isBorderlessAt(baseX * 16, baseY * 16)) {
                long i1 = mapRand.nextLong() / 2L * 2L + 1L;
                long j1 = mapRand.nextLong() / 2L * 2L + 1L;
                mapRand.setSeed((long) chunkX * i1 + (long) chunkY * j1 ^ world.getSeed());

                WorldGenVolcano.build(blocks, metadata, world, mapRand, baseX, baseY, chunkX, chunkY, simplex, cell, noise);
            }
        }
    }
    public void generateMapGen(Block[] blocks, byte[] metadata, Long seed, World world, RTGBiomeProvider cmr, Random mapRand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float noise[]) {
    
        final int mapGenRadius = 5;
        final int volcanoGenRadius = 15;

        mapRand.setSeed(seed);
        long l = (mapRand.nextLong() / 2L) * 2L + 1L;
        long l1 = (mapRand.nextLong() / 2L) * 2L + 1L;

        // Structures generation
        for (int baseX = chunkX - mapGenRadius; baseX <= chunkX + mapGenRadius; baseX++) {
            for (int baseY = chunkY - mapGenRadius; baseY <= chunkY + mapGenRadius; baseY++) {
                mapRand.setSeed((long) baseX * l + (long) baseY * l1 ^ seed);
                rMapGen(blocks, metadata, world, cmr, mapRand, baseX, baseY, chunkX, chunkY, simplex, cell, noise);
            }
        }

        // Volcanoes generation
        for (int baseX = chunkX - volcanoGenRadius; baseX <= chunkX + volcanoGenRadius; baseX++) {
            for (int baseY = chunkY - volcanoGenRadius; baseY <= chunkY + volcanoGenRadius; baseY++) {
                mapRand.setSeed((long) baseX * l + (long) baseY * l1 ^ seed);
                rMapVolcanoes(blocks, metadata, world, cmr, mapRand, baseX, baseY, chunkX, chunkY, simplex, cell, noise);
            }
        }
    }
    
    public void rMapGen(Block[] blocks, byte[] metadata, World world, RTGBiomeProvider cmr, Random mapRand, int chunkX, int chunkY, int baseX, int baseY, OpenSimplexNoise simplex, CellNoise cell, float noise[]) {
    
    }
    
    public float rNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        // we now have both lakes and rivers lowering land
        if (noWaterFeatures) {
            float borderForRiver = border*2;
            if (borderForRiver >1f) borderForRiver = 1;
            river = 1f - (1f-borderForRiver)*(1f-river);
            return terrain.generateNoise(simplex, cell, x, y, border, river);
        }
        float lakeStrength = lakePressure(simplex,cell,x,y,border);
        float lakeFlattening = (float)lakeFlattening(lakeStrength, lakeShoreLevel, lakeDepressionLevel);
        // we add some flattening to the rivers. The lakes are pre-flattened.
        float riverFlattening = river*1.25f-0.25f;
        if (riverFlattening <0) riverFlattening = 0;
        if ((river<1)&&(lakeFlattening<1)) {
            riverFlattening = (float)((1f-riverFlattening)/riverFlattening+(1f-lakeFlattening)/lakeFlattening);
            riverFlattening = (1f/(riverFlattening+1f));
        } else {
            if (lakeFlattening < riverFlattening) riverFlattening = (float)lakeFlattening;
        }
        // the lakes have to have a little less flattening to avoid the rocky edges
        lakeFlattening = lakeFlattening(lakeStrength, lakeWaterLevel, lakeDepressionLevel);

        if ((river<1)&&(lakeFlattening<1)) {
            river = (float)((1f-river)/river+(1f-lakeFlattening)/lakeFlattening);
            river = (1f/(river+1f));
        } else {
            if (lakeFlattening < river) river = (float)lakeFlattening;
        }
        // flatten terrain to set up for the water features
        float terrainNoise = terrain.generateNoise(simplex, cell, x, y, border, riverFlattening);
        // place water features
        return this.erodedNoise(simplex, cell, x, y, river, border, terrainNoise,lakeFlattening);
    }

    public static final float actualRiverProportion = 300f/1600f;
    public float erodedNoise(OpenSimplexNoise simplex, CellNoise simplexCell,int x, int y, float river, float border, float biomeHeight, double lakeFlattening)
    {

        float r = 1f;

        // put a flat spot in the middle of the river
        float riverFlattening = river; // moved the flattening to terrain stage
        if (riverFlattening <0) riverFlattening = 0;

        // check if rivers need lowering
        //if (riverFlattening < actualRiverProportion) {
            r = riverFlattening/actualRiverProportion;
        //}

        //if (1>0) return 62f+r*10f;
        if ((r < 1f && biomeHeight > 57f))
        {
            return (biomeHeight * (r))
                + ((57f + simplex.noise2(x / 12f, y / 12f) * 2f + simplex.noise2(x / 8f, y / 8f) * 1.5f) * (1f-r));
        }
        else
        {
            return biomeHeight;
        }
    }

    public float lakeFlattening(OpenSimplexNoise simplex, CellNoise simplexCell,int x, int y, float border) {
        return lakeFlattening(lakePressure(simplex, simplexCell, x, y, border), lakeWaterLevel, lakeDepressionLevel);
    }

    public float lakePressure(OpenSimplexNoise simplex, CellNoise simplexCell,int x, int y, float border) {
        if (noLakes) return 1f;
        SimplexOctave.Disk jitter = new SimplexOctave.Disk();
        simplex.riverJitter().evaluateNoise((float)x / 240.0, (float)y / 240.0, jitter);
        double pX = x + jitter.deltax() * largeBendSize;
        double pY = y + jitter.deltay() * largeBendSize;
        simplex.mountain().evaluateNoise((float)x / 80.0, (float)y / 80.0, jitter);
        pX += jitter.deltax() * mediumBendSize;
        pY += jitter.deltay() * mediumBendSize;
        simplex.octave(4).evaluateNoise((float)x / 30.0, (float)y / 30.0, jitter);
        pX += jitter.deltax() * smallBendSize;
        pY += jitter.deltay() * smallBendSize;
        //double results =simplexCell.river().noise(pX / lakeInterval, pY / lakeInterval,1.0);
        double [] lakeResults = simplexCell.river().eval((float)pX/ lakeInterval, (float)pY/ lakeInterval);
        float results = 1f-(float)((lakeResults[1]-lakeResults[0])/lakeResults[1]);
        if (results >1.01) throw new RuntimeException("" + lakeResults[0]+ " , "+lakeResults[1]);
        if (results<-.01) throw new RuntimeException("" + lakeResults[0]+ " , "+lakeResults[1]);
        //return simplexCell.river().noise((float)x/ lakeInterval, (float)y/ lakeInterval,1.0);
        return results;
    }

    public float lakeFlattening(float pressure, float bottomLevel, float topLevel) {
        // this number indicates a multiplier to height
        if (pressure > topLevel) return 1;
        if (pressure<bottomLevel) return 0;
        return (float)Math.pow((pressure-bottomLevel)/(topLevel-bottomLevel),1.0);
    }

    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {

        float riverRegion = this.noWaterFeatures ? 0: river;
        if (ConfigRTG.enableRTGBiomeSurfaces && this.config.getPropertyById(BiomeConfig.useRTGSurfacesId).valueBoolean) {
            
            for (int s = 0; s < surfacesLength; s++) {
                
                surfaces[s].paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, simplex, cell, noise, riverRegion, base);
            }
        }
        else {
            
            this.surfaceGeneric.paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, simplex, cell, noise, riverRegion, base);
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

    private class ChunkDecoration {
        PlaneLocation chunkLocation;
        DecoBase decoration;
        ChunkDecoration(PlaneLocation chunkLocation,DecoBase decoration) {
            this.chunkLocation = chunkLocation;
            this.decoration = decoration;
        }
    }

    public static ArrayList<ChunkDecoration> decoStack = new ArrayList<ChunkDecoration>();

    public void decorateInAnOrderlyFashion(World world, Random rand, int worldX, int worldY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {

    	for (int i = 0; i < this.decos.size(); i++) {
    	    decoStack.add(new ChunkDecoration(new PlaneLocation.Invariant(worldX,worldY),decos.get(i)));
            if (decoStack.size()>20) {
                String problem = "" ;
                for (ChunkDecoration inStack: decoStack) {
                    problem += "" + inStack.chunkLocation.toString() + " " + inStack.decoration.getClass().getSimpleName();
                }
                throw new RuntimeException(problem);
            }
    		if (this.decos.get(i).preGenerate(this, world, rand, worldX, worldY, simplex, cell, strength, river)) {

    			this.decos.get(i).generate(this, world, rand, worldX, worldY, simplex, cell, strength, river);
    		}
            decoStack.remove(decoStack.size()-1);
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
	    	if (!deco.properlyDefined()) throw new RuntimeException(deco.toString());
	    	
	    	if (deco instanceof DecoBaseBiomeDecorations) {
	    		
	        	for (int i = 0; i < this.decos.size(); i++) {
	        		
	        		if (this.decos.get(i) instanceof DecoBaseBiomeDecorations) {
	        			
	        			this.decos.remove(i);
	        			break;
	        		}
	        	}
	    	}
	    	
	    	this.decos.add(deco);
    	}
    }
    
    /**
     * Convenience method for addDeco() where 'allowed' is assumed to be true.
     * 
     * @param deco
     */
    public void addDeco(DecoBase deco)
    {
	    if (!deco.properlyDefined()) throw new RuntimeException(deco.toString());
    	this.addDeco(deco, true);
    }
    
    public void addDecoCollection(DecoCollectionBase decoCollection)
    {
    	if (decoCollection.decos.size() > 0) {
    		for (int i = 0; i < decoCollection.decos.size(); i++) {
    			this.addDeco(decoCollection.decos.get(i));
    		}
    	}
    }
}
