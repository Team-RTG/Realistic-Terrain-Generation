package rtg.world.biome.realistic;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomePatcher
{
    
    private int patchBiomeId;
    private RealisticBiomeBase realisticBiome;
    private BiomeGenBase baseBiome;
    
    public RealisticBiomePatcher()
    {
        this.patchBiomeId = 1;
        
        try {
            this.realisticBiome = RealisticBiomeBase.getBiome(this.patchBiomeId);
        }
        catch (Exception e) {
            throw new RuntimeException("Realistic patch biome " + this.patchBiomeId + " not found.");
        }
        
        try {
            this.baseBiome = realisticBiome.baseBiome;
        }
        catch (Exception e) {
            throw new RuntimeException("Base patch biome " + this.patchBiomeId + " not found.");
        }
    }
    
    public RealisticBiomeBase getPatchedRealisticBiome()
    {
        return this.realisticBiome;
    }
    
    public BiomeGenBase getPatchedBaseBiome()
    {
        return this.baseBiome;
    }
}
