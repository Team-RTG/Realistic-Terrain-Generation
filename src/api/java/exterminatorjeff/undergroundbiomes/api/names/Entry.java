package exterminatorjeff.undergroundbiomes.api.names;

import org.apache.logging.log4j.Level;

import exterminatorjeff.undergroundbiomes.api.ModInfo;
import exterminatorjeff.undergroundbiomes.api.common.UBLogger;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

/**
 * Common superclass for all type of entries.<br>
 * Allows a {@link Block} or {@link Item} to be registered, as well as its
 * model, and provides and access for other mods.
 * 
 * @author LouisDB
 *
 * @param <T>
 */
public abstract class Entry<T extends IForgeRegistryEntry<?>> {

	protected static final UBLogger LOGGER = new UBLogger(Entry.class, Level.INFO);

	protected final String internalName;
	/** Can be a {@link Block}, an {@link Item}... */
	private T thing;

	public Entry(String internalName) {
		this.internalName = internalName;
	}

	/**
	 * 
	 * @return <code>true</code> if this {@link Entry} is registered,
	 *         <code>false</code> otherwise.
	 */
	public boolean isRegistered() {
		return thing != null;
	}

	/**
	 * 
	 * @return The thing or <code>null</code>.
	 */
	protected final T getThing() {
		if (thing == null)
			LOGGER.warn("This entry '" + internalName + "' is not registered!");
		return thing;
	}

	/**
	 * Register item/block
	 * 
	 * @param thing
	 */
	public final void register(T thing) {
		if (this.thing == null) {
			this.thing = thing;
			doRegister();
			LOGGER.debug("Registering '" + thing.getRegistryName() + "' for entry '" + internalName + "'");
		} else
			throw new RuntimeException("This entry is already registered! (" + internalName + ")");
	}

	protected abstract void doRegister();

	/**
	 * Register item(s)/block(s) model.
	 * 
	 * @param stateMapper
	 */
	public final void registerModel(IStateMapper stateMapper) {
		if (this.thing != null) {
			if (stateMapper != null)
				LOGGER.debug("Registering models for entry '" + internalName + "' with custom state mapper");
			else
				LOGGER.debug("Registering models for entry '" + internalName + "'");
			doRegisterModel(stateMapper);
		}
	}

	protected abstract void doRegisterModel(IStateMapper stateMapper);

	/**
	 * Prepend the mod ID to the internal name.
	 * 
	 * @param internalName
	 * @return MODID:internalName
	 */
	protected static String externalName(String internalName) {
		return ModInfo.MODID + ":" + internalName;
	}

}
