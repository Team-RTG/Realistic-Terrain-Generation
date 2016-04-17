package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaIceMountains;
import rtg.world.gen.terrain.vanilla.TerrainVanillaIceMountains;

public class RealisticBiomeVanillaIceMountains extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.iceMountains.topBlock;
    public static Block fillerBlock = BiomeGenBase.iceMountains.fillerBlock;
    
    public RealisticBiomeVanillaIceMountains(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.iceMountains,
            BiomeGenBase.frozenRiver,
            new TerrainVanillaIceMountains(230f, 80f, 0f),
            new SurfaceVanillaIceMountains(config, topBlock, fillerBlock, Blocks.snow, Blocks.snow, Blocks.packed_ice, Blocks.ice, 60f, -0.14f, 14f, 0.25f)
        );
        this.noLakes=true;
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
