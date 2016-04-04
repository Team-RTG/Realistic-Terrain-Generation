package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.gen.structure.MapGenScatteredFeatureRTG;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaIcePlains;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaIcePlains extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaIcePlains() {
        super(
                Biomes.icePlains,
                Biomes.frozenRiver
        );

        initProperties();
        initDecos();
    }

    @Override
    protected SurfaceBase initSurface() {
        return new SurfaceVanillaIcePlains(this);
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                float base = 62;
                float b = simplex.noise2(x / 24f, y / 24f) * 0.25f;
                b *= river;
                float n = simplex.noise2(x / 16f, y / 16f) * 10f - 9f;
                n = (n < 0) ? 0f : n;
                b += n;
                return base + b;
            }
        };
    }

    @Override
    protected void initProperties() {
        this.config.SCATTERED_FEATURE.setDefault(MapGenScatteredFeatureRTG.Type.IGLOO.name());
    }

    @Override
    protected void initDecos()
    {
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.checkRiver = true;
        decoBoulder.minRiver = 0.87f;
        decoBoulder.boulderBlock = Blocks.cobblestone;
        decoBoulder.chance = 16;
        decoBoulder.maxY = 95;
        decoBoulder.strengthFactor = 5f;
        this.addDeco(decoBoulder);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionChance = 24;
        decoFallenTree.maxY = 90;
        decoFallenTree.logBlock = Blocks.log;
        decoFallenTree.logMeta = (byte)1;
        decoFallenTree.leavesBlock = Blocks.leaves;
        decoFallenTree.leavesMeta = (byte)-1;
        decoFallenTree.minSize = 1;
        decoFallenTree.maxSize = 5;
        this.addDeco(decoFallenTree);
    }
}
