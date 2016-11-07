package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaFrozenOcean;
import rtg.world.gen.terrain.vanilla.TerrainVanillaFrozenOcean;

public class RealisticBiomeVanillaFrozenOcean extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.frozenOcean.topBlock;
    public static Block fillerBlock = BiomeGenBase.frozenOcean.fillerBlock;
    
    public RealisticBiomeVanillaFrozenOcean(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.frozenOcean,
            BiomeGenBase.frozenRiver,
            new TerrainVanillaFrozenOcean(),
            new SurfaceVanillaFrozenOcean(config, Blocks.sand, Blocks.sand, Blocks.gravel, 20f, 0.2f));
        
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
        this.noLakes=true;
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
