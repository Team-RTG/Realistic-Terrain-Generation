package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPShield;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.SimplexOctave;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPShield;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPShield;
import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.api.content.BOPCBlocks;

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
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
                
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.cobblestone;
		decoBoulder.maxY = 80;
		decoBoulder.chance = 16;
		decoBoulder.strengthFactor = 1f;
		this.addDeco(decoBoulder);        
        
		DecoFallenTree decoFallenTree1 = new DecoFallenTree();
		decoFallenTree1.distribution.noiseDivisor = 80f;
		decoFallenTree1.distribution.noiseFactor = 60f;
		decoFallenTree1.distribution.noiseAddend = -15f;
		decoFallenTree1.logCondition = LogCondition.RANDOM_CHANCE;
		decoFallenTree1.logConditionNoise = 0f;
		decoFallenTree1.logConditionChance = 6;
		decoFallenTree1.maxY = 100;
		decoFallenTree1.logBlock = BOPCBlocks.logs4;
		decoFallenTree1.logMeta = (byte)0;
		decoFallenTree1.leavesBlock = Blocks.leaves;
		decoFallenTree1.leavesMeta = (byte)-1;
		decoFallenTree1.minSize = 3;
		decoFallenTree1.maxSize = 4;
		
		DecoFallenTree decoFallenTree2 = new DecoFallenTree();
		decoFallenTree2.distribution.noiseDivisor = 80f;
		decoFallenTree2.distribution.noiseFactor = 60f;
		decoFallenTree2.distribution.noiseAddend = -15f;
		decoFallenTree2.logCondition = LogCondition.RANDOM_CHANCE;
		decoFallenTree2.logConditionNoise = 0f;
		decoFallenTree2.logConditionChance = 6;
		decoFallenTree2.maxY = 100;
		decoFallenTree2.logBlock = Blocks.log;
		decoFallenTree2.logMeta = (byte)1;
		decoFallenTree2.leavesBlock = Blocks.leaves;
		decoFallenTree2.leavesMeta = (byte)-1;
		decoFallenTree2.minSize = 3;
		decoFallenTree2.maxSize = 4;
		
		DecoHelper5050 decoHelperHelper5050 = new DecoHelper5050(decoFallenTree1, decoFallenTree2);
		this.addDeco(decoHelperHelper5050, this.config._boolean(BiomeConfigBOPShield.decorationLogsId)); 
	}
	
    private float lakeInterval = 80;

    public float lakePressure(OpenSimplexNoise simplex, CellNoise simplexCell,int x, int y, float border) {
        float baseLakes = super.lakePressure(simplex, simplexCell, x, y, border);
        SimplexOctave.Derivative jitter = new SimplexOctave.Derivative();
        simplex.riverJitter().evaluateNoise((float)x / 30.0, (float)y / 30.0, jitter);
        double pX = x + jitter.deltax() * 15f;
        double pY = y + jitter.deltay() * 15f;
        simplex.mountain().evaluateNoise((float)x / 10.0, (float)y / 10.0, jitter);
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
