package teamrtg.rtg.modules.bop.biomes;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.DecoBoulder;
import teamrtg.rtg.api.tools.deco.DecoFallenTree;
import teamrtg.rtg.api.tools.deco.helper.DecoHelper5050;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.bop.RTGBiomeBOP;

public class RTGBiomeBOPShield extends RTGBiomeBOP {

    public RTGBiomeBOPShield() {
        super(BOPBiomes.shield.get(), Biomes.RIVER);
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase(68f) {

            private float start = 0f;
            private float height =100f;
            private float width = 170f;

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainPlains(x, y, rtgWorld.simplex, river, 160f, 10f, 60f, 200f, 64f);
            }

            /*
            private float lakeInterval = 80;

            public float lakePressure(OpenSimplexNoise simplex, CellNoise simplexCell, int x, int y, float border) {
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
            */
        };
    }

    @Override
    public SurfacePart initSurface() {
        return SurfaceBase.surfaceGenericCliffs(this);
    }

    @Override
    public void initDecos() {

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder.maxY = 80;
        decoBoulder.chance = 16;
        decoBoulder.strengthFactor = 1f;
        this.addDeco(decoBoulder);

        DecoFallenTree decoFallenTree1 = new DecoFallenTree();
        decoFallenTree1.distribution.noiseDivisor = 80f;
        decoFallenTree1.distribution.noiseFactor = 60f;
        decoFallenTree1.distribution.noiseAddend = -15f;
        decoFallenTree1.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenTree1.logConditionNoise = 0f;
        decoFallenTree1.logConditionChance = 6;
        decoFallenTree1.maxY = 100;
        decoFallenTree1.logBlock = BlockBOPLog.paging.getVariantState(BOPWoods.PINE);
        decoFallenTree1.leavesBlock = BlockBOPLeaves.paging.getVariantState(BOPTrees.PINE);
        decoFallenTree1.minSize = 3;
        decoFallenTree1.maxSize = 4;

        DecoFallenTree decoFallenTree2 = new DecoFallenTree();
        decoFallenTree2.distribution.noiseDivisor = 80f;
        decoFallenTree2.distribution.noiseFactor = 60f;
        decoFallenTree2.distribution.noiseAddend = -15f;
        decoFallenTree2.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenTree2.logConditionNoise = 0f;
        decoFallenTree2.logConditionChance = 6;
        decoFallenTree2.maxY = 100;
        decoFallenTree2.logBlock = Blocks.LOG.getStateFromMeta(1);
        decoFallenTree2.leavesBlock = Blocks.LEAVES.getStateFromMeta(1);
        decoFallenTree2.minSize = 3;
        decoFallenTree2.maxSize = 4;

        DecoHelper5050 decoHelperHelper5050 = new DecoHelper5050(decoFallenTree1, decoFallenTree2);
        this.addDeco(decoHelperHelper5050);
    }

    @Override
    public void initConfig() {

    }
}
