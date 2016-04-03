package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPShield;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPShield;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPShield;
import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.api.content.BOPCBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import rtg.util.SimplexOctave;

public class RealisticBiomeBOPShield extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.shield;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPShield(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPShield(0f, 100f, 68f, 170f),
			new SurfaceBOPShield(config, topBlock, fillerBlock)
		);
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    
        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        
        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;
        
        for (int i23 = 0; i23 < 1; i23++)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeightValue(i1, j1);
            
            if (rand.nextInt(16) == 0) {
                (new WorldGenBlockBlob(Blocks.cobblestone, 0)).generate(world, rand, i1, k1, j1);
            }
        }

        if (this.config.getPropertyById(BiomeConfigBOPShield.decorationLogsId).valueBoolean) {
        
            if (rand.nextInt(6) == 0)
            {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeightValue(x22, z22);
                
                Block log;
                byte logMeta;
                int intLogLength;
                
                if (rand.nextBoolean()) {
                    
                    log = BOPCBlocks.logs4;
                    logMeta = (byte)0;
                    intLogLength = 3 + rand.nextInt(2);
                }
                else {
                    
                    log = Blocks.log;
                    logMeta = (byte)1;
                    intLogLength = 3 + rand.nextInt(2);
                }
    
                (new WorldGenLog(log, logMeta, Blocks.leaves, -1, intLogLength)).generate(world, rand, x22, y22, z22);            
            }
        }
    }
    private float lakeInterval = 80;

    public float lakePressure(OpenSimplexNoise simplex, CellNoise simplexCell,int x, int y, float border) {
        float baseLakes = super.lakePressure(simplex, simplexCell, x, y, border);
        SimplexOctave.Derivative jitter = new SimplexOctave.Derivative();
        simplex.riverJitter().evaluateNoise(x / 30.0, y / 30.0, jitter);
        double pX = x + jitter.deltax() * 15f;
        double pY = y + jitter.deltay() * 15f;
        simplex.mountain().evaluateNoise(x / 10.0, y / 10.0, jitter);
        pX += jitter.deltax() * 4f;
        pY += jitter.deltay() * 4f;
        //double results =simplexCell.river().noise(pX / lakeInterval, pY / lakeInterval,1.0);
        double [] lakeResults = simplexCell.river().eval((float)x/ lakeInterval, (float)y/ lakeInterval);
        float results = 1f-(float)((lakeResults[1]-lakeResults[0])/lakeResults[1]);
        if (results >1.01) throw new RuntimeException("" + lakeResults[0]+ " , "+lakeResults[1]);
        if (results<-.01) throw new RuntimeException("" + lakeResults[0]+ " , "+lakeResults[1]);
        //float result = simplexCell.river().noise((float)x/ lakeInterval, (float)y/ lakeInterval,1.0);
        return Math.min(baseLakes, results);
        //return results;
    }
}
