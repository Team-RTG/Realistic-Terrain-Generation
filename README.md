# Realistic Terrain Generation
Realistic Terrain Generation (RTG) is a mod that adds a new world type which generates realistic terrain. It doesn't add new blocks. It doesn't add new mobs. It doesn't even add new biomes. It simply generates more realistic-looking terrain for existing Overworld biomes (including those added by other mods).

Minecraft Forum thread: http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/2524489

# Credits
This mod is a re-branded fork of the revolutionary [Realistic World Gen](http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/1281910-teds-world-gen-mods-realistic-world-gen-alpha-1-3) mod by Ted80. It was created as a new project because it will likely deviate from Ted80's original vision for RWG, and the author of this mod didn't want to interfere with that vision. It also uses KdotJPG's [OpenSimplex Noise](https://gist.github.com/KdotJPG/b1270127455a94ac5d19) rather than Perlin Noise to generate its terrain.

# Project roadmap
* ~~Get all vanilla biomes generating 'realistically' alongside custom biomes.~~ **[DONE]**
* ~~Remove custom biomes completely. This mod will eventually only generate realistic terrain for vanilla biomes and biomes that have been added by other mods.~~ **[DONE]**
* ~~Add inherent support for all popular biome-adding mods (Biomes O' Plenty, ExtraBiomesXL, Highlands, Enhanced Biomes, Thaumcraft, etc.)~~ **[DONE]**
* ~~Improve the aesthetic of vanilla biomes~~ **[DONE]**
* Improve the aesthetic of modded biomes
* Resolve major issues
* Create an API to allow other mods to customise terrain generation.

# Downloads
Latest beta release: [RTG-1.7.10-0.2.0.jar](http://curse.com/mc-mods/minecraft/237989-realistic-terrain-generation/2268572) (Beta)

All downloads can also be found in the [Releases section](https://github.com/Team-RTG/Realistic-Terrain-Generation/releases) on GitHub.

__(Please note that RTG is currently in beta, which means that it should primarily be used for testing purposes. Please backup your worlds before installing RTG if you plan on using it in your public or private mod pack.)__

# Installation
1. Download and install the latest recommended version of [Forge for 1.7.10](http://files.minecraftforge.net/).
2. [Download this mod](http://www.curse.com/mc-mods/minecraft/237989-realistic-terrain-generation) and place it in your 'mods' folder.
3. For singleplayer... create a new world, click the [More World Options] button, choose 'Realistic' from the [World Type] button, then click [Create New World]
4. For servers... use **level-type=RTG** in server.properties

#Configuration
Example configs:
https://github.com/Team-RTG/Realistic-Terrain-Generation/tree/master/config/RTG

**IMPORTANT:**
Biome enabling/disabling is handled exclusively through RTG's config files. For example, if you set `Bayou=false` in BOP's _biomegen.cfg_ file, and you set `generateBOPbayou=true` in RTG's _biomesoplenty.cfg_ file, the Bayou biome will still be enabled.

# Mod compatibility
Because RTG generates vanilla biomes, it is compatible with most mods, including (but not limited to) AE2, AgriCraft, Ancient Trees, Animals+, Ars Magica, ATG, Better Villages, Biomes O' Plenty, Blood Magic, Botania, BuildCraft, Butterfly Mania, Camping Mod, Chocolate Quest, ChromatiCraft, DoggyStyle, Ender Zoo, Enhanced Biomes, EnviroMine, Et Futurum, Ex Nihilo, ExtraBiomesXL, Galacticraft, Gany's Mods, Highlands, Just a Few Fish, Lycanite's Mobs, Magneticraft, Mariculture, Metallurgy, Millenaire, Mo' Creatures, Mutant Creatures, Nevermine, Oceancraft, OreSpawn, Pam's HarvestCraft, Plant Mega Pack, Primitive Mobs, RailCraft, Roguelike Dungeons, Ruins, Runic Dungeons, Streams, Thaumcraft, Tinkers' Construct, Wild Caves, Witchery, **and many more!**

In addition, RTG currently generates realistic versions of the biomes added by these mods:

* Biomes O' Plenty (supports version 2.1.0.1387 or later)
* ExtrabiomesXL (supports version 3.16.2 or later)
* Enhanced Biomes (supports version 2.5 or later)
* Highlands (supports version 2.2.3 or later)
* Thaumcraft (supports version 4.2.3.4 or later)
* BuildCraft (supports version 7.1.7 or later)
* Ars Magica 2 (supports version 1.4.0.008 or later)
* Alternate Terrain Generation (ATG) (supports version 0.11.0 or later)
* ChromatiCraft (supports version V9b or later)

# Known Issues
RTG's list of known issues can be found [here](https://github.com/Team-RTG/Realistic-Terrain-Generation/issues).

**Other things worth mentioning:**

* Mushroom Islands do not generate as actual islands. For this reason, they are disabled by default.
* Volcanic biomes sometimes contain partially-generated volcanoes. For this reason, they are disabled by default.
* Hot-to-cold biome transitions are still occurring.

# Screenshots
http://imgur.com/a/322dY
(Thanks to Tak and ThePlayX3 for the screenshots!)

# Videos
Video by 7ERr0r using RTG 0.0.13 (Alpha): https://www.youtube.com/watch?v=AtI1btLLk_Y

# How to report an issue
You may report issues in this thread, but please use spoiler tags or [pastebin](http://pastebin.com/) for crash reports, and - if possible - please describe what you were doing immediately before the crash. The more information you provide, the easier it will be to resolve the issue.

# Mod Packs
You may use RTG in your public or private mod pack as long the following conditions are met:

* You understand and accept that RTG is in beta, which means that it is currently under heavy development and could change radically from version to version, with no guarantee of backwards-compatibility.
* You understand and accept that only the latest version of RTG will be supported at any given time. If your mod pack is using an older version and you report an issue with that version, we will only try to resolve that issue if it is still present in the latest version.
* You must credit 'Team RTG' as the author of this mod and link back to this forum thread.
* Your mod pack must be made available to everyone, free of charge. If your modpack is private, it must be made available free of charge to whomever is given access to it.
* Optional - please post a link to your mod pack in this forum thread. If your mod pack is private, please PM me the link to your mod pack or a list of mods that your mod pack contains.

# Development
RTG is built on Forge 1.7.10-10.13.4.1448 and compiled with Gradle 2.0.

# License
Realistic Terrain Generation
Copyright (C) 2015 WhichOnesPink

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see [http://www.gnu.org/licenses/](http://www.gnu.org/licenses/).
