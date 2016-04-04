package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoDoubleGrass;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoReed;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaSavanna;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaSavanna extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaSavanna() {

        super(
                Biomes.savanna,
                Biomes.river
        );
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            private final GroundEffect groundEffect = new GroundEffect(4f);

            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return riverized(65f + groundEffect.added(simplex, cell, x, y), river);
            }
        };
    }
    
    @Override
        protected SurfaceBase initSurface() {
        return new SurfaceVanillaSavanna(this, 13f, 0.27f);
    }

    @Override
    protected void initDecos()
    {
		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.loops = 1;
		decoFallenTree.distribution.noiseDivisor = 100f;
		decoFallenTree.distribution.noiseFactor = 6f;
		decoFallenTree.distribution.noiseAddend = 0.8f;
		decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoFallenTree.logConditionNoise = 0f;
		decoFallenTree.logConditionChance = 24;
		decoFallenTree.maxY = 120;
		decoFallenTree.logBlock = Blocks.log2;
		decoFallenTree.logMeta = (byte)0;
		decoFallenTree.leavesBlock = Blocks.leaves2;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 3;
		decoFallenTree.maxSize = 6;
		this.addDeco(decoFallenTree);
        
		DecoTree riverTrees = new DecoTree();
		riverTrees.checkRiver = true;
		riverTrees.minRiver = 0.8f;
		riverTrees.strengthNoiseFactorForLoops = false;
		riverTrees.strengthFactorForLoops = 15f;
		riverTrees.treeType = TreeType.SAVANNA_RIVER;
		riverTrees.treeCondition = TreeCondition.ALWAYS_GENERATE;
		riverTrees.maxY = 100;
		this.addDeco(riverTrees);
        
        DecoReed decoReed = new DecoReed();
        decoReed.checkRiver = true;
        decoReed.minRiver = 0.8f;
		decoReed.maxY = 68;
		decoReed.strengthFactor = 2f;
        this.addDeco(decoReed);
        
		DecoTree savannaTrees = new DecoTree();
		savannaTrees.strengthFactorForLoops = 3f;
		savannaTrees.treeType = TreeType.SAVANNA;
		savannaTrees.distribution.noiseDivisor = 180f;
		savannaTrees.distribution.noiseFactor = 1f;
		savannaTrees.distribution.noiseAddend = 0f;
		savannaTrees.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		savannaTrees.treeConditionNoise = 0.20f;
		savannaTrees.maxY = 100;
		this.addDeco(savannaTrees);
        
		DecoTree savannaTrees2 = new DecoTree();
		savannaTrees2.strengthFactorForLoops = 2f;
		savannaTrees2.treeType = TreeType.SAVANNA;
		savannaTrees2.treeCondition = TreeCondition.RANDOM_CHANCE;
		savannaTrees2.treeConditionChance = 3;
		savannaTrees2.maxY = 100;
		this.addDeco(savannaTrees2);
		
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.cobblestone;
		decoBoulder.chance = 24;
		decoBoulder.maxY = 95;
		this.addDeco(decoBoulder);
        
		DecoDoubleGrass decoDoubleGrass = new DecoDoubleGrass();
		decoDoubleGrass.maxY = 128;
		decoDoubleGrass.strengthFactor = 3f;
        this.addDeco(decoDoubleGrass);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 10f;
        this.addDeco(decoGrass);
    }
    
    @Override
    protected void initProperties()
    {
        config.addBlock(config.MIX_BLOCK).setDefault(Blocks.grass.getDefaultState());
    }
}
