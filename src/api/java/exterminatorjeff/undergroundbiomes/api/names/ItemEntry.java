package exterminatorjeff.undergroundbiomes.api.names;

import exterminatorjeff.undergroundbiomes.api.common.UBItem;
import exterminatorjeff.undergroundbiomes.api.common.Variable;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * 
 * @author LouisDB
 *
 */
public class ItemEntry extends Entry<UBItem> {

	public ItemEntry(String internalName) {
		super(internalName);
	}

	public Item getItem() {
		return getThing().toItem();
	}

	@Override
	protected void doRegister() {
		getItem().setUnlocalizedName(internalName);
		GameRegistry.register(getItem().setRegistryName(internalName));
	}

	public void registerModel() {
		super.registerModel(null);
	}

	@Override
	protected void doRegisterModel(IStateMapper stateMapper) {
		if (getItem() instanceof Variable) {
			Variable varItem = (Variable) getItem();
			for (int meta = 0; meta < varItem.getNbVariants(); ++meta) {
				ModelResourceLocation location = new ModelResourceLocation(externalName(varItem.getVariantName(meta)));
				ModelLoader.setCustomModelResourceLocation(getItem(), meta, location);
				LOGGER.debug("Model location: " + location);
			}
		} else {
			ModelResourceLocation location = new ModelResourceLocation(externalName(internalName));
			ModelLoader.setCustomModelResourceLocation(getItem(), 0, location);
			LOGGER.debug("Model location: " + location);
		}
	}

}
