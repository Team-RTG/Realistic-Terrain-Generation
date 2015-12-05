package enhancedbiomes.api;

import enhancedbiomes.EnhancedBiomesMod;

import net.minecraft.block.Block;


public class EBAPI
{
    public static final boolean ebStoneEnabled = (EnhancedBiomesMod.useNewStone == 1) ? true : false;
    public static final boolean ebGrassEnabled = EnhancedBiomesMod.useNewGrass ? true : false;
    
    public static final byte BASALT = (byte)0;
    public static final byte SHALE = (byte)1;
    public static final byte HARDENED_SANDSTONE = (byte)2;
    public static final byte LIMESTONE = (byte)3;
    public static final byte SLATE = (byte)4;
    public static final byte RHYOLITE = (byte)5;
    public static final byte CHALK = (byte)6;
    public static final byte MARBLE = (byte)7;
    public static final byte DOLOMITE = (byte)8;
    public static final byte SCHIST = (byte)9;
    public static final byte CHERT = (byte)10;
    public static final byte GABBRO = (byte)11;
    public static final byte DACITE = (byte)12;
    
    public static final byte ALFISOL = (byte)0;
    public static final byte ANDISOL = (byte)1;
    public static final byte GELISOL = (byte)3;
    public static final byte HISTOSOL = (byte)4;
    public static final byte INCEPTISOL = (byte)5;
    public static final byte MOLLISOL = (byte)6;
    public static final byte OXISOL = (byte)7;
    
    public static Block ebStonify(Block ebBlock, Block vanillaBlock)
    {
        return ebStoneEnabled ? ebBlock : vanillaBlock;
    }
    
    public static byte ebStonify(byte ebByte, byte vanillaByte)
    {
        return ebStoneEnabled ? ebByte : vanillaByte;
    }
    
    public static Block ebGrassify(Block ebBlock, Block vanillaBlock)
    {
        return ebGrassEnabled ? ebBlock : vanillaBlock;
    }
    
    public static byte ebGrassify(byte ebByte, byte vanillaByte)
    {
        return ebGrassEnabled ? ebByte : vanillaByte;
    }
}
