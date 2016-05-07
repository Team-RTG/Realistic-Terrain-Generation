package teamrtg.rtg.mods.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.biome.surface.part.BlockPart;
import teamrtg.rtg.world.biome.surface.part.HeightSelector;
import teamrtg.rtg.world.biome.surface.part.Selector;
import teamrtg.rtg.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.world.biome.terrain.TerrainBase;
import teamrtg.rtg.world.gen.ChunkProviderRTG;
import teamrtg.rtg.world.gen.deco.DecoBaseBiomeDecorations;

public class RealisticBiomeVanillaOcean extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaOcean(ChunkProviderRTG chunkProvider) {

        super(
                Biomes.OCEAN,
                Biomes.RIVER,
                chunkProvider
        );
        this.noLakes=true;
        this.noWaterFeatures = true;
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainOcean(x, y, simplex, river, 50f);
            }
        };
    }

    @Override
    protected SurfacePart initSurface() {
        SurfacePart surface = new SurfacePart();
        surface.add(PARTS.selectTop()
            .add(new HeightSelector(0, 63)
                .add(new Selector((x, y, z) -> simplex.noise2(x / 20f, z / 20f) > 0.1f)
                    .add(new BlockPart(config.MIX_BLOCK_TOP.get())))));
        surface.add(PARTS.surfaceGeneric());
        return surface;
    }

    @Override
    protected void initDecos() {
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    protected void initProperties() {
        config.addBlock(config.MIX_BLOCK_TOP).setDefault(Blocks.GRAVEL.getDefaultState());
        this.config.WATER_POND_CHANCE.setDefault(0);
        this.config.LAVA_POND_CHANCE.setDefault(0);
        this.config.SURFACE_BLEED_OUT.setDefault(false);
    }
}
