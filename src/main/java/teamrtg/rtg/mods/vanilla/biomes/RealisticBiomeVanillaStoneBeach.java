package teamrtg.rtg.mods.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.biome.surface.part.BlockPart;
import teamrtg.rtg.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.world.biome.surface.part.TopPosSelector;
import teamrtg.rtg.world.biome.terrain.TerrainBase;
import teamrtg.rtg.world.gen.ChunkProviderRTG;
import teamrtg.rtg.world.gen.deco.DecoBaseBiomeDecorations;

public class RealisticBiomeVanillaStoneBeach extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaStoneBeach() {
        super(
            Biomes.STONE_BEACH,
            Biomes.RIVER
        );
    }

    @Override
    protected void initProperties() {
        config.addBlock(config.BEACH_BLOCK).setDefault(Blocks.GRAVEL.getDefaultState());
    }

    @Override
    protected void initDecos() {
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    protected SurfacePart initSurface() {
        SurfacePart surface = new SurfacePart();
        surface.add(new CliffSelector(1.5f)
            .add(PARTS.selectTopAndFill()
                .add(this.PARTS.SHADOW_STONE)));
        surface.add(new CliffSelector((x, y, z, provider) -> 1.5f - ((y - 60f) / 65f) + provider.simplex.noise3(x / 8f, y / 8f, z / 8f) * 0.5f)
            .add(PARTS.selectTop()
                .add(PARTS.STONE_OR_COBBLE)))
            .add(PARTS.selectFill()
                .add(PARTS.STONE));
        surface.add(PARTS.selectTopAndFill()
            .add(new TopPosSelector(0, 63)
                .add(new BlockPart(Blocks.GRAVEL.getDefaultState()))));
        surface.add(PARTS.surfaceGeneric());
        return surface;
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(ChunkProviderRTG provider, int x, int y, float border, float river) {
                return terrainBeach(x, y, provider.simplex, river, 180f, 35f, 63f);
            }
        };
    }
}
