package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import teamrtg.rtg.api.tools.deco.collection.DecoCollectionTaiga;
import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.api.util.noise.IFloatAt;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.surface.part.BlockPart;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.Selector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.api.world.biome.surface.part.TopPosSelector;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaTaigaM extends RTGBiomeVanilla {
    public static Biome standardBiome = Biomes.TAIGA;
    public static Biome mutationBiome = Biome.getBiome(BiomeUtils.getId(standardBiome) + MUTATION_ADDEND);

    public RTGBiomeVanillaTaigaM() {

        super(
                mutationBiome,
            Biomes.RIVER
        );
        this.noLakes = true;
        this.noWaterFeatures = true;
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainGrasslandHills(x, y, rtgWorld.simplex, rtgWorld.cell, river, 100f, 7f, 180f, 70f, 68f);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        SurfacePart surface = new SurfacePart();

        IFloatAt cliffNoise = (x, y, z, rtgWorld) -> rtgWorld.simplex.noise3(x / 8f, y / 8f, z / 8f) * 0.5f;

        surface.add(PARTS.selectTopAndFill()

            .add(new CliffSelector((x, y, z, rtgWorld) -> {
                float n = 1.5f - ((y - 60f) / 65f) + cliffNoise.getAt(x, y, z, rtgWorld);
                return (n > 0.2f) ? n : 0.2f;
            })
                .add(PARTS.selectTop()
                    .add(PARTS.STONE_OR_COBBLE))
                .add(PARTS.STONE))

            .add(new CliffSelector(1.5f)
                .add(this.PARTS.SHADOW_STONE))

            .add(new CliffSelector((x, y, z, rtgWorld) -> 0.3f + ((y - 100f) / 50f) + cliffNoise.getAt(x, y, z, rtgWorld))
                .add(new Selector((x, y, z, rtgWorld) -> y > 110 + (cliffNoise.getAt(x, y, z, rtgWorld) * 4))
                    .add(new BlockPart(Blocks.SNOW.getDefaultState()))))

            .add(PARTS.selectTop()
                .add(new Selector((x, y, z, rtgWorld) -> rtgWorld.simplex.noise2(x / 50f, z / 50f) + cliffNoise.getAt(x, y, z, rtgWorld) * 0.6f > 0.24f)
                    .add(new BlockPart(Blocks.DIRT.getStateFromMeta(2))))
                .add(new BlockPart(Blocks.GRASS.getDefaultState())))
            .add(new TopPosSelector(0, 63)
                .add(new BlockPart(Blocks.GRAVEL.getDefaultState())))
            .add(new BlockPart(Blocks.DIRT.getDefaultState()))
        );
        return surface;
    }

    @Override
    public void initDecos() {
    	this.addDecoCollection(new DecoCollectionTaiga(10f));
    }

    @Override
    public void initConfig() {

    }
}
