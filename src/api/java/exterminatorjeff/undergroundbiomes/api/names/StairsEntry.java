package exterminatorjeff.undergroundbiomes.api.names;

import exterminatorjeff.undergroundbiomes.api.common.UBStairs;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * 
 * @author LouisDB
 *
 */
public class StairsEntry extends Entry<UBStairs> {

	public StairsEntry(StoneEntry baseStoneEntry) {
		super(baseStoneEntry.internalName + "_stairs");
		baseStoneEntry.stairs = this;
	}

	public Item getItemBlock() {
		return getThing().toItem();
	}

	public Block getBlock(EnumFacing facing) {
		if (facing != EnumFacing.UP && facing != EnumFacing.DOWN)
			return getThing().getBlock(facing);
		throw new RuntimeException("Stairs cannot be facing " + facing + "!");
	}

	protected UBStairs stairs() {
		return getThing();
	}

	@Override
	protected void doRegister() {
		getItemBlock().setUnlocalizedName(internalName);
		GameRegistry.register(getItemBlock().setRegistryName(internalName));
		for (EnumFacing facing : EnumFacing.HORIZONTALS) {
			String name = internalName + "_" + facing;
			Block block = getBlock(facing);
			block.setUnlocalizedName(name);
			GameRegistry.register(block.setRegistryName(name));
		}
	}

	@Override
	protected void doRegisterModel(IStateMapper stateMapper) {
		for (EnumFacing facing : EnumFacing.HORIZONTALS)
			ModelLoader.setCustomStateMapper(getBlock(facing), stateMapper);
		for (int meta = 0; meta < stairs().getNbVariants(); ++meta) {
			ModelResourceLocation location = new ModelResourceLocation(externalName(internalName), stairs().getVariantName(meta));
			ModelLoader.setCustomModelResourceLocation(getItemBlock(), meta, location);
			LOGGER.debug("Model location: " + location);
		}
	}

}
