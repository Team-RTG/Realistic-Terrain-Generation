package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.BlockPart;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.api.world.biome.surface.part.TopPosSelector;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaStoneBeach extends RTGBiomeVanilla {

    public RTGBiomeVanillaStoneBeach() {
        super(
            Biomes.STONE_BEACH,
            Biomes.RIVER
        );
    }

    @Override
    public void initConfig() {
        config.addBlock(config.BEACH_BLOCK).setDefault(Blocks.GRAVEL.getDefaultState());
    }

    @Override
    public void initDecos() {
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public SurfacePart initSurface() {
        SurfacePart surface = new SurfacePart();
        surface.add(new CliffSelector(1.5f)
            .add(PARTS.selectTopAndFill()
                .add(this.PARTS.SHADOW_STONE)));
        surface.add(new CliffSelector((x, y, z, rtgWorld) -> 1.5f - ((y - 60f) / 65f) + rtgWorld.simplex.noise3(x / 8f, y / 8f, z / 8f) * 0.5f)
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
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainBeach(x, y, rtgWorld.simplex, river, 180f, 35f, 63f);
            }
        };
    }
}
