package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoDeadBush;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoGrassDoubleTallgrass;
import rtg.world.biome.deco.DecoMushrooms;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaRoofedForest;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaRoofedForest extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaRoofedForest() {

        super(
                Biomes.roofedForest,
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
        return new SurfaceVanillaRoofedForest(this, false, 0f, 1.5f, 60f, 65f, 1.5f, 0.08f);
    }

    @Override
    protected void initDecos()
    {
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.mossy_cobblestone;
		decoBoulder.chance = 20;
		decoBoulder.maxY = 80;
		decoBoulder.strengthFactor = 2f;
		this.addDeco(decoBoulder);
		
		DecoBoulder decoCobwebBoulder = new DecoBoulder();
		decoCobwebBoulder.boulderBlock = Blocks.web;
		decoCobwebBoulder.chance = 32;
		decoCobwebBoulder.maxY = 80;
		decoCobwebBoulder.strengthFactor = 2f;
		this.addDeco(decoCobwebBoulder);
        
		DecoTree decoTrees = new DecoTree();
		decoTrees.strengthFactorForLoops = 24f;
		decoTrees.distribution.noiseDivisor = 80f;
		decoTrees.distribution.noiseFactor = 60f;
		decoTrees.distribution.noiseAddend = -15f;
		decoTrees.treeType = TreeType.MANGROVE;
		decoTrees.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoTrees.treeConditionNoise = 0f;
		decoTrees.treeConditionChance = 1;
		decoTrees.maxY = 120;
		this.addDeco(decoTrees);
        
		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.logCondition = LogCondition.ALWAYS_GENERATE;
		decoFallenTree.logConditionChance = 1;
		decoFallenTree.loops = 4;
		decoFallenTree.maxY = 100;
		decoFallenTree.logBlock = Blocks.log2;
		decoFallenTree.logMeta = (byte)1;
		decoFallenTree.leavesBlock = Blocks.leaves2;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 4;
		decoFallenTree.maxSize = 9;
		this.addDeco(decoFallenTree);
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 1f;
		this.addDeco(decoShrub);
        
        DecoGrassDoubleTallgrass decoGrassDoubleTallgrass = new DecoGrassDoubleTallgrass();
        decoGrassDoubleTallgrass.maxY = 128;
        decoGrassDoubleTallgrass.strengthFactor = 8f;
        decoGrassDoubleTallgrass.doubleGrassChance = 6;
        this.addDeco(decoGrassDoubleTallgrass);
        
        DecoDeadBush decoDeadBush = new DecoDeadBush();
		decoDeadBush.maxY = 128;
		decoDeadBush.chance = 16;
		decoDeadBush.strengthFactor = 1f;
        this.addDeco(decoDeadBush);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 4f;
		decoGrass.chance = 2;
        this.addDeco(decoGrass);
        
		DecoGrass decoFern = new DecoGrass();
		decoFern.maxY = 128;
		decoFern.strengthFactor = 4f;
		decoFern.chance = 2;
		decoFern.meta = 2;
        this.addDeco(decoFern);

		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
        
        DecoMushrooms decoMushrooms = new DecoMushrooms();
        decoMushrooms.maxY = 90;
        decoMushrooms.randomType = rtg.world.biome.deco.DecoMushrooms.RandomType.ALWAYS_GENERATE;
        this.addDeco(decoMushrooms);
    }
    
    @Override
    protected void initProperties()
    {
        config.addBlock(config.MIX_BLOCK).setDefault(Blocks.dirt.getStateFromMeta(2));
    	config.addBlock(config.BEACH_BLOCK).setDefault(Blocks.sand.getDefaultState());
    }
}
