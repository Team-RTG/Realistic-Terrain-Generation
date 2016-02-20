/*******************************************************************************
 * AbyssalCraft
 * Copyright (c) 2012 - 2016 Shinoow.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * 
 * Contributors:
 *     Shinoow -  implementation
 ******************************************************************************/
package com.shinoow.abyssalcraft.api;

/**
 * List of InterModComms messages you can use instead of the API registry methods
 * 
 * @author shinoow
 *
 */
public class IMCHelper {

	//SHOGGOTH FOOD ////////////////////////////////////////////////////////////////////////////
	/**
	 * This is a IMC version of AbyssalCraftAPI#addShoggothFood
	 * You can use the IMC message "shoggothFood" to add a Entity to the Shoggoth Food list
	 * The format for the message should be the string path to the entity class
	 * 
	 * Example of how it would look like if I added the Depths Ghoul to the food list:
	 * FMLInterModComms.sendMessage("abyssalcraft", "shoggothFood", "com.shinoow.abyssalcraft.common.entity.EntityDepthsGhoul");
	 */

	//TRANSMUTATOR FUEL HANDLER ///////////////////////////////////////////////////////////////
	/**
	 * This is a IMC version of AbyssalCraftAPI#registerFuelHandler made for registering Transmutator fuel handlers
	 * You can use the IMC message "registerTransmutatorFuel" to register a fuel handler
	 * The format for the message should be the string path to the handler class
	 * 
	 * Example of how it would look like if I added my Furnace fuel handler as a Transmutator fuel handler:
	 * FMLInterModComms.sendMessage("abyssalcraft", "registerTransmutatorFuel", "com.shinoow.abyssalcraft.common.handlers.FurnaceFuelHandler");
	 */

	//CRYSTALLIZER FUEL HANDLER ///////////////////////////////////////////////////////////////
	/**
	 * This is a IMC version of AbyssalCraftAPI#registerFuelHandler made for registering Crystallizer fuel handlers
	 * You can use the IMC message "registerCrystallizerFuel" to register a fuel handler
	 * The format for the message should be the string path to the handler class
	 * 
	 * Example of how it would look like if I added my Furnace fuel handler as a Crystallizer fuel handler:
	 * FMLInterModComms.sendMessage("abyssalcraft", "registerCrystallizerFuel", "com.shinoow.abyssalcraft.common.handlers.FurnaceFuelHandler");
	 */

	//CRYSTALS /////////////////////////////////////////////////////////////////////////////////

	/**
	 * This is a IMC version of AbyssalCraftAPI#addCrystal
	 * You can use the IMC message "addCrystal"
	 * 
	 * Example of how it would look like if I added my Abyssalnite Ingot as a crystal:
	 * FMLInterModComms.sendMessage("abyssalcraft", "addCrystal", new ItemStack(AbyssalCraft.abyingot));
	 */

	//CRYSTALLIZATION //////////////////////////////////////////////////////////////////////////

	/**
	 * This is a IMC version of AbyssalCraftAPI#addCrystallization
	 * It has the following IMC messages:
	 * "addCrystallization" - used for normal crystallization with 2 ItemStack outputs
	 * "addSingleCrystallization" - used for single-output crystallization with only 1 ItemStack output
	 * "addOredictCrystallization" - used for normal OreDictionary crystallization with 2 ItemStack outputs
	 * "addOredictSingleCrystallization" - used for single-output OreDictinoary crystallization with only 1 ItemStack output
	 * 
	 * The IMC message should contain a NBTTagCompound with the following tags:
	 * "input" - the input, should be a ItemStack unless it's a OreDictionary recipe, in which case it should be a String
	 * "output1" - the first input, same as above. Used for normal crystallizations
	 * "output2" - the second output, same as above. Used for normal crystallizations
	 * "output" - the output - same as above. Used for single-output crystallizations
	 * "quantity1" (optional) - quantity of the first output, will null any metadata if used without the "meta1" tag. Used for normal crystallization
	 * "quantity2" (optional) - quantity of the second output, will null any metadata if used without the "meta2" tag. Used for normal crystallization
	 * "quantity" (optional) - quantity of the output, will null any metadata if used without the "meta" tag. Used for single-output crystallization
	 * "meta1" (optional) - metadata for the first output. Used for normal crystallization
	 * "meta2" (optional) - metadata for the second output. Used for normal crystallization
	 * "meta" (optional) - metadata for the output. Used for single-output crystallization
	 * "xp" - experience points gained from the crystallization, used in all cases
	 * 
	 * The message is sent this way:
	 * FMLInterModComms.sendMessage("abyssalcraft", <whichever message you picked>, <a NBTTagCompound with the aforementioned tags>);
	 */

	//TRANSMUTATION ////////////////////////////////////////////////////////////////////////////

	/**
	 * This is a IMC version of AbyssalCraftAPI#addTransmutation
	 * It has the following IMC messages:
	 * "addTransmutation" - used for normal transmutatiion
	 * "attOredictTransmutation" - used for OreDictionary transmutation
	 * 
	 * The IMC message should contain a NBTTagCompound with the following tags:
	 * "input" - the input, either a ItemStack or a String
	 * "output" - the output, either a ItemStack or a String
	 * "quantity" (optional) - quantity of the output, will null any metadata if used without the "meta" tag
	 * "meta" (optional) - metadata for the output
	 * "xp" - experience points gained from the transmutation, used in both cases
	 * 
	 * The message is sent this way:
	 * FMLInterModComms.sendMessage("abyssalcraft", <whichever message you picked>, <a NBTTagCompound with the aforementioned tags>);
	 */

	//MATERIALIZATION //////////////////////////////////////////////////////////////////////////

	/**
	 * This is a IMC version of AbyssalCraftAPI#addMaterialization
	 * It has the following IMC message:
	 * "addMaterialization"
	 * 
	 * The IMC message should contain a NBTTagCompound with the following tags:
	 * "input1" - the first output, a ItemStack, can't be null
	 * "input2" - the second output, same as above. Can be null
	 * "input3" - the third output, same as above. Can be null
	 * "input4" - the fourth output, same as above. Can be null
	 * "input5" - the fifth output, same as above. Can be null
	 * "output" - the output, same as above, can't be null
	 * "level" - required Necronomicon level (Necronomicon = 0, Abyssal Wasteland Necronomicon = 1,
	 * Dreadlands Necronomicon = 2, Omothol Necronomicon = 3, Abyssalnomicon = 4)
	 * 
	 * The message is sent this way:
	 * FMLInterModComms.sendMessage("abyssalcraft", "addMaterialization", <a NBTTagCompound with the aforementioned tags>);
	 */

	//INTEGRATIONS /////////////////////////////////////////////////////////////////////////////

	/**
	 * This is a IMC version of AbyssalCraftAPI#registerACIntegration
	 * You can use the IMC message "registerACIntegration" to add your AbyssalCraft
	 * integration into the internal handler.
	 * The format for the message should be the string path to the integration class
	 * 
	 * Note: it is recommended to either send this in Pre-init or Init, as the handler will
	 * run two checks there (where a plugin registered in Init can't do anything in Pre-init)
	 * 
	 * The message is sent this way:
	 * FMLInterModComms.sendMessage("abyssalcraft", "registerACIntegration", "path.to.your.integration.plugin");
	 */

	//SHOGGOTH BLOCK BLACKLIST /////////////////////////////////////////////////////////////////
	/**
	 * This is a IMC version of AbyssalCraftAPI#addShoggothBlacklist
	 * You can use the IMC message "shoggothBlacklist" to add a Block to the Shoggoth Block Blacklist
	 * The format for the message should be a ItemStack containing a Block
	 * 
	 * Example of how it would look like if I added Darkstone Bricks to the food list:
	 * FMLInterModComms.sendMessage("abyssalcraft", "shoggothBlacklist", new ItemStack(AbyssalCraft.Darkstone_brick));
	 */
}
