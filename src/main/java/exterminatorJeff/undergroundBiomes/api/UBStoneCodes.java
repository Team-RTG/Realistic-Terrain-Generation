package exterminatorJeff.undergroundBiomes.api;
/**
 *
 * @author Zeno410
 */
public class UBStoneCodes extends BlockCodes {
    // constructor is protected as it's intended to make master blocks to be passed by reference
    // not fully implemented yet

    public UBStoneCodes(NamedBlock name, int _metadata) {
        super(name, _metadata,cobbleVersion(name, _metadata));
    }
    public final UBStoneCodes slabVersionEquivalent() {
        return new UBStoneCodes(UBIDs.slabVersionID(name),metadata);
    };
    public final UBStoneCodes brickVersionEquivalent() {
        return new UBStoneCodes(UBIDs.brickVersionID(name),metadata);
    };

    public static BlockCodes cobbleVersion(NamedBlock name, int metadata) {

        if (name == UBIDs.igneousStoneName) {
            return new BlockCodes(UBIDs.igneousCobblestoneName,metadata);
        }
        if (name == UBIDs.metamorphicStoneName) {
            return new BlockCodes(UBIDs.metamorphicCobblestoneName,metadata);
        }
        if (name == UBIDs.sedimentaryStoneName) {
           return new BlockCodes(UBIDs.sedimentaryStoneName, metadata);
        }

        if (name == NamedVanillaBlock.stone) {
           if (metadata == 0) {
               return new BlockCodes(NamedVanillaBlock.cobblestone, metadata);
           }
        }

        if (name == NamedVanillaBlock.sandstone) {
           if (metadata == 0) {
               return new BlockCodes(NamedVanillaBlock.sandstone, metadata);
           }
        }
        if (name == NamedVanillaBlock.sand) {
           if (metadata == 0) {
               return new BlockCodes(NamedVanillaBlock.sandstone, metadata);
           }
        }
        
        if (name == UBIDs.igneousCobblestoneName) {
            return new BlockCodes(UBIDs.igneousCobblestoneName,metadata);
        }
        if (name == UBIDs.metamorphicCobblestoneName) {
            return new BlockCodes(UBIDs.metamorphicCobblestoneName,metadata);
        }

        if (name == UBIDs.igneousStoneBrickName) {
            return new BlockCodes(UBIDs.igneousStoneBrickName,metadata);
        }
        if (name == UBIDs.metamorphicStoneBrickName) {
            return new BlockCodes(UBIDs.metamorphicStoneBrickName,metadata);
        }

        if (name == UBIDs.igneousStoneSlabName.half){
           return new BlockCodes(UBIDs.igneousCobblestoneSlabName.half, metadata);
        }
        if (name == UBIDs.metamorphicStoneSlabName.half){
           return new BlockCodes(UBIDs.metamorphicCobblestoneSlabName.half, metadata);
        }
        if (name == UBIDs.sedimentaryStoneSlabName.half){
           return new BlockCodes(UBIDs.sedimentaryStoneSlabName.half, metadata);
        }

        if (name == UBIDs.igneousCobblestoneSlabName.half){
           return new BlockCodes(UBIDs.igneousCobblestoneSlabName.half, metadata);
        }
        if (name == UBIDs.metamorphicCobblestoneSlabName.half){
           return new BlockCodes(UBIDs.metamorphicCobblestoneSlabName.half, metadata);
        }

        if (name == UBIDs.igneousBrickSlabName.half){
           return new BlockCodes(UBIDs.igneousBrickSlabName.half, metadata);
        }
        if (name == UBIDs.metamorphicBrickSlabName.half){
           return new BlockCodes(UBIDs.metamorphicBrickSlabName.half, metadata);
        }

        if (name == NamedVanillaBlock.stoneBrick){
           return new BlockCodes(NamedVanillaBlock.stoneBrick, metadata);
        }

        if (name == NamedVanillaBlock.stoneSingleSlab) {
            return new BlockCodes(NamedVanillaBlock.stoneSingleSlab,metadata);
        }

        return new BlockCodes(NamedVanillaBlock.cobblestone, metadata);
    }

    public final BlockCodes cobblestoneEquivalent() {return this.onDrop;}

}
