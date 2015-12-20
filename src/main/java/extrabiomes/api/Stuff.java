package extrabiomes.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import com.google.common.base.Optional;

import extrabiomes.items.ItemCustomDye;

/**
 * This class contains all of the custom items and blocks.
 * 
 * @author ScottKillen
 * 
 */
public enum Stuff
{
    INSTANCE;
    
    public static Optional<? extends Item>  scarecrow               = Optional.absent();
    public static Optional<? extends Item>  paste                   = Optional.absent();
    public static Optional<? extends Item>  logTurner               = Optional.absent();
    public static Optional<? extends Item>	dye						          = Optional.absent();
    public static Optional<? extends Item>	crop					          = Optional.absent();
    public static Optional<? extends Item>	seed					          = Optional.absent();
    public static Optional<? extends Item>	food					          = Optional.absent();

    public static Optional<? extends Block> fence                   = Optional.absent();
    public static Optional<? extends Block> planks                  = Optional.absent();
    public static Optional<? extends Block> quickSand               = Optional.absent();
    public static Optional<? extends Block> slabRedRock             = Optional.absent();
    public static Optional<? extends Block> slabRedRockDouble       = Optional.absent();
    public static Optional<? extends Block> slabWood                = Optional.absent();
    public static Optional<? extends Block> slabWoodDouble          = Optional.absent();
    public static Optional<? extends Block> newslabWood             = Optional.absent();
    public static Optional<? extends Block> newslabWoodDouble       = Optional.absent();
    public static Optional<? extends Block> stairsAcacia            = Optional.absent();
    public static Optional<? extends Block> stairsFir               = Optional.absent();
    public static Optional<? extends Block> stairsRedCobble         = Optional.absent();
    public static Optional<? extends Block> stairsRedRockBrick      = Optional.absent();
    public static Optional<? extends Block> stairsRedwood           = Optional.absent();
    public static Optional<? extends Block> wall                    = Optional.absent();
    public static Optional<? extends Block> stairsCypress           = Optional.absent();
    public static Optional<? extends Block> stairsBaldCypress       = Optional.absent();
    public static Optional<? extends Block> stairsJapaneseMaple     = Optional.absent();
    public static Optional<? extends Block> stairsRainbowEucalyptus = Optional.absent();
    public static Optional<? extends Block> stairsAutumn            = Optional.absent();
    public static Optional<? extends Block> stairsSakuraBlossom     = Optional.absent();

    public static Optional<? extends Block> doorAcacia              = Optional.absent();
    public static Optional<? extends Block> doorAutumn              = Optional.absent();
    public static Optional<? extends Block> doorBaldcypress         = Optional.absent();
    public static Optional<? extends Block> doorCypress             = Optional.absent();
    public static Optional<? extends Block> doorFir                 = Optional.absent();
    public static Optional<? extends Block> doorJapaneseMaple       = Optional.absent();
    public static Optional<? extends Block> doorRainbowEucalyptus   = Optional.absent();
    public static Optional<? extends Block> doorRedwood             = Optional.absent();
    public static Optional<? extends Block> doorSakura              = Optional.absent();

    public static Optional<? extends Block> gateAcacia              = Optional.absent();
    public static Optional<? extends Block> gateAutumn              = Optional.absent();
    public static Optional<? extends Block> gateBaldcypress         = Optional.absent();
    public static Optional<? extends Block> gateCypress             = Optional.absent();
    public static Optional<? extends Block> gateFir                 = Optional.absent();
    public static Optional<? extends Block> gateJapaneseMaple       = Optional.absent();
    public static Optional<? extends Block> gateRainbowEucalyptus   = Optional.absent();
    public static Optional<? extends Block> gateRedwood             = Optional.absent();
    public static Optional<? extends Block> gateSakura              = Optional.absent();
}
