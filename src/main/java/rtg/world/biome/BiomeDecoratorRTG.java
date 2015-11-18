package rtg.world.biome;

import net.minecraft.world.biome.BiomeDecorator;

public class BiomeDecoratorRTG extends BiomeDecorator
{
    
    public BiomeDecoratorRTG()
    {
        super();
    }
    
    protected int nextInt(int i) {
        if (i <= 1)
            return 0;
        return this.randomGenerator.nextInt(i);
    }    
}