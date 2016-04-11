package teamrtg.rtg.mods.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.util.math.CanyonColour;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.biome.surface.part.*;
import teamrtg.rtg.world.biome.terrain.TerrainBase;
import teamrtg.rtg.world.gen.ChunkProviderRTG;
import teamrtg.rtg.world.gen.deco.*;

import java.util.Random;

import static teamrtg.rtg.world.gen.deco.DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
import static teamrtg.rtg.world.gen.deco.DecoTree.TreeType.VANILLA_OAK;

public class RealisticBiomeVanillaMesaPlateauF extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaMesaPlateauF(ChunkProviderRTG chunkProvider) {
        super(
                Biomes.MESA_ROCK,
                Biomes.RIVER,
                chunkProvider
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
        decoCactus.soil = Blocks.SAND.getStateFromMeta(1);
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
        this.surfacePart.add(new DepthSelector(0, 11)
                .add(new CliffSelector(1.3f)
                        .add(new BlockPart(CanyonColour.MESA)))
                .add(new DepthSelector(4, 256)
                        .add(new BlockPart(CanyonColour.MESA)))
                .add(new HeightSelector(0, 62)
                        .add(new DepthSelector(0, 0)
                                .add(new BlockPart(Blocks.GRASS.getDefaultState())))
                        .add(new BlockPart(Blocks.DIRT.getDefaultState())))
                .add(new DepthSelector(0, 0)
                        .add(new RandomPart(new Random(3), 5)
                                .add(new BlockPart(Blocks.GRASS.getDefaultState())))
                        .add(new RandomPart(new Random(3), 3)
                                .add(new BlockPart(Blocks.DIRT.getStateFromMeta(1))))
                        .add(new BlockPart(this.config.TOP_BLOCK.get())))
                .add(new BlockPart(this.config.FILL_BLOCK.get()))
        );
        this.surfacePart.add(
                new HeightSelector(64, 256)
                        .add(new BlockPart(CanyonColour.MESA))
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
