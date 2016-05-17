package teamrtg.rtg.mods.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.biome.surface.part.BlockPart;
import teamrtg.rtg.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.world.biome.surface.part.DepthSelector;
import teamrtg.rtg.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.world.biome.terrain.TerrainBase;
import teamrtg.rtg.world.gen.ChunkProviderRTG;
import teamrtg.rtg.world.gen.deco.DecoBaseBiomeDecorations;

public class RealisticBiomeVanillaIcePlainsSpikes extends RealisticBiomeVanillaBase {
    public static BiomeGenBase standardBiome = Biomes.ICE_PLAINS;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(BiomeUtils.getId(standardBiome) + MUTATION_ADDEND);

    public RealisticBiomeVanillaIcePlainsSpikes(ChunkProviderRTG chunkProvider) {
        super(
                mutationBiome,
                Biomes.FROZEN_RIVER,
                chunkProvider
        );
        this.noLakes = true;
    }

    @Override
    protected SurfacePart initSurface() {
        SurfacePart surface = PARTS.selectTopAndFill();
        surface.add(new CliffSelector(1.4f)
            .add(new DepthSelector(0, 1)
                .add(PARTS.rand(3)
                    .add(new BlockPart(config.CLIFF_BLOCK_2.get()))))
            .add(new BlockPart(config.CLIFF_BLOCK_1.get())));
        surface.add(PARTS.surfaceMix((x, y, z) -> simplex.noise2(x / 60f, z / 60f) + simplex.noise2(x / 14f, z / 14f) * 0.25f > -0.14f));
        surface.add(PARTS.surfaceGeneric());
        return surface;
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 200f, 65f);
            }
        };
    }


    @Override
    protected void initDecos() {
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    protected void initProperties() {
        config.addBlock(config.CLIFF_BLOCK_1).setDefault(Blocks.PACKED_ICE.getDefaultState());
        config.addBlock(config.CLIFF_BLOCK_2).setDefault(Blocks.ICE.getDefaultState());
    }
}
