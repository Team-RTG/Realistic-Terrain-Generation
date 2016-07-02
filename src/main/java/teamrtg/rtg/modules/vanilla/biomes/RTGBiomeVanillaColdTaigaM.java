package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import teamrtg.rtg.api.tools.deco.collection.DecoCollectionTaiga;
import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.DepthSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaColdTaigaM extends RTGBiomeVanilla {
    public static Biome standardBiome = Biomes.COLD_TAIGA;
    public static Biome mutationBiome = Biome.getBiome(BiomeUtils.getId(standardBiome) + MUTATION_ADDEND);

    public RTGBiomeVanillaColdTaigaM() {

        super(
                mutationBiome,
            Biomes.FROZEN_RIVER
        );
        this.noLakes = true;
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainGrasslandMountains(x, y, rtgWorld.simplex, rtgWorld.cell, river, 4f, 80f, 68f);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        return new DepthSelector(0, 10)
            .add(new CliffSelector(1.4f)
                .add(new DepthSelector(0, 1)
                    .add(PARTS.STONE_OR_COBBLE))
                .add(PARTS.STONE))
            .add(PARTS.surfaceGeneric());
    }

    @Override
    public void initDecos() {
    	this.addDecoCollection(new DecoCollectionTaiga(8f));
    }

    @Override
    public void initConfig() {

    }
}
