package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.deco.DecoCactus;
import rtg.world.biome.deco.DecoDeadBush;
import rtg.world.biome.deco.DecoReed;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMesaPlateauM;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaMesaPlateauM extends RealisticBiomeVanillaBase {
    public static BiomeGenBase standardBiome = Biomes.mesaPlateau;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(RealisticBiomeBase.getIdForBiome(standardBiome) + MUTATION_ADDEND);

    public RealisticBiomeVanillaMesaPlateauM() {

        super(
                mutationBiome,
                Biomes.river
        );
        this.noLakes = true;
    }

    @Override
        protected SurfaceBase initSurface() {
        return new SurfaceVanillaMesaPlateauM(this, 0);
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            private float[] height = new float[] {18.5f, 0.4f};
            private int heightLength = height.length;
            private float strength = 20f;

            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                river *= 0.5f;
                return terrainPlateau(x, y, simplex, river, height, border, strength, heightLength, 50f, true);
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
        decoTree.treeType = TreeType.VANILLA_OAK;
        decoTree.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoTree.distribution = new DecoTree.Distribution(24f, 1f, 0f);
        decoTree.treeConditionChance = 0;
        decoTree.treeConditionNoise = 0f;
        decoTree.minY = 74;
        addDeco(decoTree);
    }
}
