package exterminatorjeff.undergroundbiomes.api.names;

import exterminatorjeff.undergroundbiomes.api.common.UBBlock;
import net.minecraft.block.Block;
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
public abstract class BlockEntry extends Entry<UBBlock> implements BlockAccess{

	public BlockEntry(String internalName) {
		super(internalName);
	}
        
        public BlockEntry(Block block) {
            super(block.getUnlocalizedName());
            // sanitize inputs
            if (getBlock() == null ) throw new RuntimeException();
        }

	public Block getBlock() {
		return getThing().toBlock();
	}

	public Item getItemBlock() {
		return getThing().getItemBlock();
	}

	protected UBBlock getUBBlock() {
		return getThing();
	}

	@Override
	protected void doRegister() {
		getBlock().setUnlocalizedName(internalName);
		GameRegistry.register(getBlock().setRegistryName(internalName));
		GameRegistry.register(getItemBlock(), getBlock().getRegistryName());
	}

	public void registerModel() {
		super.registerModel(null);
	}

	@Override
	protected void doRegisterModel(IStateMapper stateMapper) {
		for (int meta = 0; meta < getUBBlock().getNbVariants(); ++meta) {
			ModelResourceLocation location = new ModelResourceLocation(externalName(internalName), "type=" + getUBBlock().getVariantName(meta));
			ModelLoader.setCustomModelResourceLocation(getItemBlock(), meta, location);
			LOGGER.debug("Model location: " + location);
		}
	}

}
