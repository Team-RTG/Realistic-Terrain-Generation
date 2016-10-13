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
package com.shinoow.abyssalcraft.api.energy;

import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Collection of Enums used by various parts of the Potential Energy system.<br>
 * Also contains useful methods related to said Enums.
 * @author shinoow
 *
 * @since 1.5
 */
public class EnergyEnum {

	/**
	 * Amplifier Types<br>
	 * RANGE - Range at which the amplified thing can transfer<br>
	 * DURATION - Amount of time it takes to transfer<br>
	 * POWER - Boost in amount of power transferred
	 * 
	 * @since 1.5
	 */
	public enum AmplifierType{

		RANGE("range"), DURATION("duration"), POWER("power");

		private String unlocalizedName;

		private AmplifierType(String name){
			unlocalizedName = name;
		}

		/**
		 * Getter for the Amplifier's unlocalized name
		 */
		public String getUnlocalizedName(){
			return "ac.amplifier." + unlocalizedName;
		}

		/**
		 * Getter for the Amplifier's localized name
		 */
		public String getLocalizedName(){
			return I18n.translateToLocal(getUnlocalizedName());
		}
	}

	/**
	 * Deity Types<br>
	 * Allows limiting certain actions to images/statues of a certain deity
	 * 
	 * @since 1.5
	 */
	public enum DeityType{

		CTHULHU("Cthulhu"), HASTUR("Hastur"), JZAHAR("J'zahar"),
		AZATHOTH("Azathoth"), NYARLATHOTEP("Nyarlathotep"),
		SHUBNIGGURATH("Shub-Niggurath"), YOGSOTHOTH("Yog-Sothoth");

		private String name;

		private DeityType(String name){
			this.name = name;
		}

		/**
		 * Getter for the Deity's name
		 */
		public String getName(){
			return name;
		}
	}

	/**
	 * Fetches the localized name of a AmplifierType
	 * @param type Type to fetch the name from
	 * @return A localized name if found, otherwise "None"
	 * 
	 * @since 1.5
	 */
	public static String getAmplifierName(AmplifierType type){
		if(type == null) return I18n.translateToLocal("ac.text.none");
		return type.getLocalizedName();
	}

	/**
	 * Fetches the localized name of a DeityType
	 * @param type Type to fetch the name from
	 * @return A localized name if found, otherwise "None"
	 * 
	 * @since 1.5
	 */
	public static String getDeityName(DeityType type){
		if(type == null) return I18n.translateToLocal("ac.text.none");
		return type.getName();
	}

	/**
	 * Adds a Amplifier Type
	 * @param enumName Name for the enum (eg. Amplifier name in caps with only letters)
	 * @param name Name of the amplifier (will be prefixed with "ac.amplifier")
	 * @return A new Amplifier Type
	 * 
	 * @since 1.5
	 */
	public AmplifierType addAmplifierType(String enumName, String name){
		return EnumHelper.addEnum(AmplifierType.class, enumName, new Class[]{String.class}, name);
	}

	/**
	 * Adds a Deity Type
	 * @param enumName Name for the enum (eg. Deity name in caps with only letters)
	 * @param name Name of the Deity
	 * @return A new Deity Type
	 * 
	 * @since 1.5
	 */
	public DeityType addDeityType(String enumName, String name){
		return EnumHelper.addEnum(DeityType.class, enumName, new Class[]{String.class}, name);
	}
}
