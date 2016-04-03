package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.deco.*;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMesaPlateauF;
import rtg.world.gen.terrain.TerrainBase;

import static rtg.world.biome.deco.DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
import static rtg.world.biome.deco.DecoTree.TreeType.VANILLA_OAK;

public class RealisticBiomeVanillaMesaPlateauF extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaMesaPlateauF() {
        super(
                Biomes.mesaPlateau_F,
                Biomes.river
        );
        this.noLakes = true;

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
    protected SurfaceBase initSurface() {
        return new SurfaceVanillaMesaPlateauF(config, Blocks.sand.getStateFromMeta(1), Blocks.sand.getStateFromMeta(1), 0);
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
}
