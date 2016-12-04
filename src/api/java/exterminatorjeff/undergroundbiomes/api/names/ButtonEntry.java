package exterminatorjeff.undergroundbiomes.api.names;

import exterminatorjeff.undergroundbiomes.api.common.UBButton;
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
public final class ButtonEntry extends Entry<UBButton> {

	public ButtonEntry(StoneEntry baseStoneEntry) {
		super(baseStoneEntry.internalName + "_button");
		baseStoneEntry.button = this;
	}

	public Item getItemBlock() {
		return getThing().toItem();
	}

	public Block getBlock(EnumFacing facing) {
		return getThing().getBlock(facing);
	}

	protected UBButton button() {
		return getThing();
	}

	@Override
	protected void doRegister() {
		getItemBlock().setUnlocalizedName(internalName);
		GameRegistry.register(getItemBlock().setRegistryName(internalName));
		for (EnumFacing facing : EnumFacing.VALUES) {
			String name = internalName + "_" + facing;
			Block block = getBlock(facing);
			block.setUnlocalizedName(name);
			GameRegistry.register(block.setRegistryName(name));
		}
	}

	@Override
	protected void doRegisterModel(IStateMapper stateMapper) {
		for (EnumFacing facing : EnumFacing.VALUES)
			ModelLoader.setCustomStateMapper(getBlock(facing), stateMapper);
		for (int meta = 0; meta < button().getNbVariants(); ++meta) {
			String variants = "type=" + button().getVariantName(meta);
			ModelResourceLocation location = new ModelResourceLocation(externalName(internalName), variants);
			ModelLoader.setCustomModelResourceLocation(getItemBlock(), meta, location);
			LOGGER.debug("Model location: " + location);
		}
	}

}
