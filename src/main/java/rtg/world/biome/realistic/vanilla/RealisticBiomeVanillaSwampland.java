package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoPumpkin;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaSwampland;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaSwampland extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaSwampland() {

        super(
                Biomes.swampland,
                Biomes.river
        );

        initProperties();
        initDecos();
    }

    @Override
    protected SurfaceBase initSurface() {
        return new SurfaceVanillaSwampland(this);
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainMarsh(x, y, simplex, 62f);
            }
        };
    }

    @Override
    protected void initProperties()
    {

    }

    @Override
    protected void initDecos()
    {
		DecoTree decoTrees = new DecoTree();
		decoTrees.strengthNoiseFactorXForLoops = true;
		decoTrees.strengthFactorForLoops = 1f;
		decoTrees.distribution.noiseDivisor = 80f;
		decoTrees.distribution.noiseFactor = 60f;
		decoTrees.distribution.noiseAddend = -15f;
		decoTrees.treeType = TreeType.WILLOW;
		decoTrees.treeCondition = TreeCondition.RANDOM_CHANCE;
		decoTrees.treeConditionChance = 4;
		decoTrees.maxY = 100;
		this.addDeco(decoTrees);
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 100;
        decoShrub.strengthFactor = 3f;
		this.addDeco(decoShrub);
		
		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.distribution.noiseDivisor = 80f;
		decoFallenTree.distribution.noiseFactor = 60f;
		decoFallenTree.distribution.noiseAddend = -15f;
		decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoFallenTree.logConditionNoise = 0f;
		decoFallenTree.logConditionChance = 6;
		decoFallenTree.maxY = 100;
		decoFallenTree.logBlock = Blocks.log2;
		decoFallenTree.logMeta = (byte)1;
		decoFallenTree.leavesBlock = Blocks.leaves2;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 3;
		decoFallenTree.maxSize = 6;
		this.addDeco(decoFallenTree);
        
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
		
		DecoPumpkin decoPumpkin = new DecoPumpkin();
		decoPumpkin.maxY = 90;
		decoPumpkin.randomType = rtg.world.biome.deco.DecoPumpkin.RandomType.X_DIVIDED_BY_STRENGTH;
		decoPumpkin.randomFloat = 50f;
        this.addDeco(decoPumpkin);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 12f;
        this.addDeco(decoGrass);
    }
}
