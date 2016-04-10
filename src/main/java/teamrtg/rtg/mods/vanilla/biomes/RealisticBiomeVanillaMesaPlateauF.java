package teamrtg.rtg.mods.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.util.math.CanyonColour;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.biome.surface.part.*;
import teamrtg.rtg.world.biome.terrain.TerrainBase;
import teamrtg.rtg.world.gen.deco.*;

import static teamrtg.rtg.world.gen.deco.DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
import static teamrtg.rtg.world.gen.deco.DecoTree.TreeType.VANILLA_OAK;

public class RealisticBiomeVanillaMesaPlateauF extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaMesaPlateauF() {
        super(
                Biomes.mesaPlateau_F,
                Biomes.river
        );
        this.noLakes = true;
    }

    @Override
    protected void initProperties() {

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
    protected void initNewSurfaces() {
        this.surfacePart.addSubPart(new DepthSelector(this, 0, 11)
                .addSubPart(new CliffSelector(this, 1.3f)
                        .addSubPart(new BlockPart(this, CanyonColour.MESA)))
                .addSubPart(new DepthSelector(this, 4, 256)
                        .addSubPart(new BlockPart(this, CanyonColour.MESA)))
                .addSubPart(new HeightSelector(this, 0, 62)
                        .addSubPart(new DepthSelector(this, 0, 0)
                                .addSubPart(new BlockPart(this, Blocks.grass.getDefaultState())))
                        .addSubPart(new BlockPart(this, Blocks.dirt.getDefaultState())))
                .addSubPart(new DepthSelector(this, 0, 0)
                        .addSubPart(new RandomPart(this, 444L, 5)
                                .addSubPart(new BlockPart(this, Blocks.grass.getDefaultState())))
                        .addSubPart(new RandomPart(this, 444L, 3)
                                .addSubPart(new BlockPart(this, Blocks.dirt.getStateFromMeta(1))))
                        .addSubPart(new BlockPart(this, this.config.TOP_BLOCK.get())))
                .addSubPart(new BlockPart(this, this.config.FILL_BLOCK.get()))
        );
        this.surfacePart.addSubPart(
                new HeightSelector(this, 64, 256)
                        .addSubPart(new BlockPart(this, CanyonColour.MESA))
        );
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
