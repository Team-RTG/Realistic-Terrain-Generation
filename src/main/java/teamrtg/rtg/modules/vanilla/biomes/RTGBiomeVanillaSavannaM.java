package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import teamrtg.rtg.api.tools.deco.collection.DecoCollectionDesertRiver;
import teamrtg.rtg.api.tools.deco.collection.DecoCollectionSavanna;
import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.DepthSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaSavannaM extends RTGBiomeVanilla {
    public static Biome standardBiome = Biomes.SAVANNA;
    public static Biome mutationBiome = Biome.getBiome(BiomeUtils.getId(standardBiome) + MUTATION_ADDEND);

    public RTGBiomeVanillaSavannaM() {

        super(
                mutationBiome,
            Biomes.RIVER
        );
        this.noLakes = true;
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainGrasslandMountains(x, y, rtgWorld.simplex, rtgWorld.cell, river, 4f, 90f, 67f);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        SurfacePart surface = new SurfacePart();
        surface.add(new CliffSelector(1.5f)
            .add(PARTS.selectTop()
                .add(PARTS.STONE_OR_COBBLE))
            .add(new DepthSelector(0, 10)
                .add(PARTS.STONE)));
        surface.add(PARTS.surfaceMix(PARTS.MIX_NOISE));
        surface.add(PARTS.surfaceGeneric());
        return surface;
    }

    @Override
    public void initDecos() {
        this.addDecoCollection(new DecoCollectionDesertRiver());
        this.addDecoCollection(new DecoCollectionSavanna());
    }

    @Override
    public void initConfig() {

    }
}
