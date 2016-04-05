package teamrtg.rtg.mods.vanilla.biomes;

import static teamrtg.rtg.world.gen.deco.DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
import static teamrtg.rtg.world.gen.deco.DecoTree.TreeType.VANILLA_OAK;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.gen.deco.DecoCactus;
import teamrtg.rtg.world.gen.deco.DecoDeadBush;
import teamrtg.rtg.world.gen.deco.DecoReed;
import teamrtg.rtg.world.gen.deco.DecoShrub;
import teamrtg.rtg.world.gen.deco.DecoTree;
import teamrtg.rtg.world.biome.surface.SurfaceBase;
import teamrtg.rtg.mods.vanilla.surfaces.SurfaceVanillaMesaPlateauF;
import teamrtg.rtg.world.biome.terrain.TerrainBase;

public class RealisticBiomeVanillaMesaPlateauF extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaMesaPlateauF() {
        super(
                Biomes.mesaPlateau_F,
                Biomes.river
        );
        this.noLakes = true;
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            private final float[] height = new float[] {24.0f, 0.4f};
            private final int heightLength = height.length;
            private final float strength = 10f;

            {
                /**
                 * Values come in pairs per layer. First is how high to step up.
                 * 	Second is a value between 0 and 1, signifying when to step up.
                 */
                base = 69f;
            }

            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainPlateau(x, y, simplex, river, height, border, strength, heightLength, 100f, false);
            }
        };
    }

    @Override
    protected SurfaceBase initSurface() {
        return new SurfaceVanillaMesaPlateauF(this, 0);
    }

    @Override
    protected void initDecos() {
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.chance = 10;
        addDeco(decoShrub);

        DecoCactus decoCactus = new DecoCactus();
        decoCactus.strengthFactor = 25f;
        decoCactus.soil = Blocks.sand.getStateFromMeta(1);
        decoCactus.sandOnly = false;
        decoCactus.maxRiver = 0.8f;
        addDeco(decoCactus);

        DecoReed decoReed = new DecoReed();
        decoReed.loops = 5;
        decoReed.maxRiver = 0.8f;
        addDeco(decoReed);

        DecoDeadBush decoDeadBush = new DecoDeadBush();
        decoDeadBush.strengthFactor = 5f;
        addDeco(decoDeadBush);

        DecoTree decoTree = new DecoTree();
        decoTree.loops = 20;
        decoTree.treeType = VANILLA_OAK;
        decoTree.treeCondition = NOISE_GREATER_AND_RANDOM_CHANCE;
        decoTree.distribution = new DecoTree.Distribution(24f, 1f, 0f);
        decoTree.treeConditionChance = 0;
        decoTree.treeConditionNoise = 0f;
        decoTree.minY = 74;
        addDeco(decoTree);
    }

    @Override
    protected void initProperties() {

    }
}
