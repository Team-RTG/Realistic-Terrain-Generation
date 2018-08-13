package rtg.client.gui;

import javax.annotation.Nullable;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiListButton;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.gui.GuiPageButtonList.GuiButtonEntry;
import net.minecraft.client.gui.GuiPageButtonList.GuiLabelEntry;
import net.minecraft.client.gui.GuiPageButtonList.GuiListEntry;
import net.minecraft.client.gui.GuiPageButtonList.GuiResponder;
import net.minecraft.client.gui.GuiPageButtonList.GuiSlideEntry;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlider.FormatHelper;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rtg.api.util.Logger;
import rtg.api.world.gen.RTGChunkGenSettings;


@SideOnly(Side.CLIENT)
public class GuiCustomizeWorldScreenRTG extends GuiScreen implements FormatHelper, GuiResponder {

    private static final int ID_OFFSET = Byte.MAX_VALUE;
    private static final String LANG_KEY_PREFIX = "gui.createWorld.customize.";
    private static final String LANG_KEY_PREFIX_TITLE = LANG_KEY_PREFIX + "title.";
    private static final String LANG_KEY_PREFIX_CATEGORY = LANG_KEY_PREFIX + "category.";
    private static final String LANG_KEY_PREFIX_SETTING = LANG_KEY_PREFIX + "setting.";
    private static final int BUTTON_DONE = 0x40;
    private static final int BUTTON_CLIPBOARD = 0x41;
    private static final int BUTTON_DEFAULTS = 0x42;
    private static final int BUTTON_PREVPAGE = 0x43;
    private static final int BUTTON_NEXTPAGE = 0x44;
    private static final int BUTTON_YES = 0x45;
    private static final int BUTTON_NO = 0x46;

    private static final RTGChunkGenSettings defaults = new RTGChunkGenSettings.Factory().build();
// TODO: [Generator settings] Disable fixedBiome for now as it requires modification to the GenLayer classes to work.
//  private static final List<Biome> biomeList              = createBiomeList();

    private final GuiCreateWorld parent;

    private String title = "";
    private String subtitle = "";
    private String pageTitle = "";
    private String[] pageNames = new String[Page.values().length];

    private GuiPageButtonList list;
    private GuiButton done;
    private GuiButton clipboard;
    private GuiButton reset;
    private GuiButton prevPage;
    private GuiButton nextPage;
    private GuiButton confirm;
    private GuiButton cancel;

    private boolean settingsModified;
    private boolean confirmDismissed;
    private int confirmMode;


    public GuiCustomizeWorldScreenRTG(GuiScreen parentScreen, String generatorSettings) {

        this.parent = (GuiCreateWorld) parentScreen;

        // Process the generator settings that are passed when recreating an existing world or
        // from re-entering the customisation screen after having changed settings and exited.
        if (!generatorSettings.isEmpty()) {
            Setting.parseSettings(generatorSettings);
            this.settingsModified = true;
        }
        else {
            // Reset to defaults for a new world
            Setting.resetToDefaults();
        }
    }

    @Override
    public void initGui() {

        int page = 0;
        int scrolled = 0;

        if (this.list != null) {
            page = this.list.getPage();
            scrolled = this.list.getAmountScrolled();
        }

        this.title = I18n.format(LANG_KEY_PREFIX_TITLE + "maintitle");
        this.buttonList.clear();

        this.done = this.addButton(new GuiButton(BUTTON_DONE, this.width / 2 + 98, this.height - 27, 90, 20, I18n.format("gui.done")));
        this.clipboard = this.addButton(new GuiButton(BUTTON_CLIPBOARD, this.width / 2 - 80, this.height - 27, 160, 20, I18n.format(LANG_KEY_PREFIX + "clipboard")));
        this.reset = this.addButton(new GuiButton(BUTTON_DEFAULTS, this.width / 2 - 187, this.height - 27, 90, 20, I18n.format("createWorld.customize.custom.defaults")));
        this.prevPage = this.addButton(new GuiButton(BUTTON_PREVPAGE, 20, 5, 80, 20, I18n.format("createWorld.customize.custom.prev")));
        this.nextPage = this.addButton(new GuiButton(BUTTON_NEXTPAGE, this.width - 100, 5, 80, 20, I18n.format("createWorld.customize.custom.next")));
        this.confirm = new GuiButton(BUTTON_YES, this.width / 2 - 55, 160, 50, 20, I18n.format("gui.yes"));
        this.cancel = new GuiButton(BUTTON_NO, this.width / 2 + 5, 160, 50, 20, I18n.format("gui.no"));

        this.clipboard.enabled = this.settingsModified;
        this.reset.enabled = this.settingsModified;
        this.confirm.visible = false;
        this.cancel.visible = false;
        this.buttonList.add(this.confirm);
        this.buttonList.add(this.cancel);

        if (this.confirmMode != 0) {
            this.confirm.visible = true;
            this.cancel.visible = true;
        }

        this.createPagedList();

        if (page != 0) {
            this.list.setPage(page);
            this.list.scrollBy(scrolled);
            this.updatePageControls();
        }
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        this.list.handleMouseInput();
    }

    // This method is never called since GuiPagedButtonList.EditBoxEntry is never used.
    @Override
    public void setEntryValue(int id, String value) {
        this.setSettingsModified(Setting.isModified());
    }

    @Override
    public void setEntryValue(int id, boolean value) {

        Setting setting = Setting.getByID(id);
        if (setting != null) {
            setting.setCurValue(value);
            this.setSettingsModified(Setting.isModified());
        }
    }

    @Override
    public void setEntryValue(int id, float value) {

        Setting setting = Setting.getByID(id);
        if (setting != null) {
            if (setting.getSettingType().equals(SettingType.INTEGER)) {
                setting.setCurValue((int) value);
            }
            else {
                setting.setCurValue(value);
            }
            this.setSettingsModified(Setting.isModified());
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {

        if (button.enabled) {
            switch (button.id) {

                case BUTTON_DONE:
                    RTGChunkGenSettings.Factory factory = RTGChunkGenSettings.Factory.jsonToFactory(Setting.buildJson().toString());
                    if (factory != null) {
                        this.parent.chunkProviderSettingsJson = factory.toString();
                    }
                    else {
                        Logger.error("Error parsing RTGChunkGenSettings settings from string: {}", Setting.buildJson().toString());
                    }
                    this.mc.displayGuiScreen(this.parent);
                    break;

                case BUTTON_CLIPBOARD:
                    this.copyToClipboard();
                    break;

                case BUTTON_DEFAULTS:
                    if (this.settingsModified) {
                        this.enterConfirmation(BUTTON_DEFAULTS);
                    }
                    break;

                case BUTTON_PREVPAGE:
                    this.list.previousPage();
                    this.updatePageControls();
                    break;

                case BUTTON_NEXTPAGE:
                    this.list.nextPage();
                    this.updatePageControls();
                    break;

                case BUTTON_YES:
                    this.exitConfirmation();
                    break;

                case BUTTON_NO:
                    this.confirmMode = 0;
                    this.exitConfirmation();
                    break;
            }
        }
    }

    private void createPagedList() {

        GuiListEntry[][] pages = new GuiListEntry[Page.values().length][];

        Lists.newArrayList(Page.values()).forEach(page -> {

            int pg = page.ordinal();
            Id id = new Id();

            this.pageNames[pg] = page.getTitle();

            pages[pg] = new GuiListEntry[page.getPageSize()];

            page.CATEGORIES.forEach(cat -> {

                pages[pg][id.next()] = new GuiLabelEntry(ID_OFFSET + 1000 + cat.ordinal(), cat.getTitle(), page.isPage1());
                id.next(); // bump the ID to ensure a null value after the label so that it is displayed centered and doesn't throw an exception

                List<Setting> sets = Setting.getSettingsForCategory(cat); // get the settings for this category

                sets.forEach(setting -> {

                    if (setting.getSettingType().equals(SettingType.BOOLEAN)) {
                        pages[pg][id.next()] = new GuiButtonEntry(
                            setting.getID(),
                            setting.getLangKey(),
                            setting.isPage1(),
                            setting.curValue().getBool()
                        );
                    }
                    if (setting.getSettingType().equals(SettingType.INTEGER)) {
                        pages[pg][id.next()] = new GuiSlideEntry(
                            setting.getID(),
                            setting.getLangKey(),
                            setting.isPage1(),
                            (fid, fname, fval) -> {
// TODO: [Generator settings] Disable fixedBiome for now as it requires modification to the GenLayer classes to work.
                            /*if (setting.equals(Setting.fixedBiome)) { // special handling for fixedBiome
                                  return ((int)fval == -1) ? "Biome: All Biomes" : "Biome: " + biomeList.get((int)fval).getBiomeName();
                              }
                              else*/
                                if (setting.equals(Setting.waterSpoutChance) || setting.equals(Setting.lavaSpoutChance)) { // special handling for waterSpoutChance/lavaSpoutChance
                                    return fname + (((int) fval == 0) ? ": Disabled" : ": " + String.format("%d", (int) fval));
                                }
                                return fname + ": " + String.format("%d", (int) fval);
                            },
                            setting.minValue().getInt(),
// TODO: [Generator settings] Disable fixedBiome for now as it requires modification to the GenLayer classes to work.
                            /*setting.equals(Setting.fixedBiome) ? biomeList.size() - 1 :*/ setting.maxValue().getInt(), // special handling for fixedBiome
                            setting.curValue().getInt()
                        );
                    }

                    if (setting.getSettingType().equals(SettingType.FLOAT)) {
                        pages[pg][id.next()] = new GuiSlideEntry(
                            setting.getID(),
                            setting.getLangKey(),
                            setting.isPage1(),
                            (fid, fname, fval) -> setting.equals(Setting.riverCutOffScale) // special handling for riverCutOffScale
                                ? fname + ": " + String.format("%3.3f", fval)
                                : fname + ": " + String.format("%1.3f", fval),
                            setting.minValue().getFloat(),
                            setting.maxValue().getFloat(),
                            setting.curValue().getFloat()
                        );
                    }
                }); // SETTINGS END
                if (sets.size() % 2 != 0) {
                    id.next();
                } // if the current category contains an odd number of settings, bump the ID to pad it to prevent an exception
            }); // CATEGORIES END
        }); // PAGES END

        this.list = new GuiPageButtonList(this.mc, this.width, this.height, 32, this.height - 32, 25, this, pages);

        this.updatePageControls();
    }

    private void updatePageControls() {
        this.prevPage.enabled = this.list.getPage() != 0;
        this.nextPage.enabled = this.list.getPage() != this.list.getPageCount() - 1;
        this.subtitle = I18n.format("book.pageIndicator", this.list.getPage() + 1, this.list.getPageCount());
        this.pageTitle = this.pageNames[this.list.getPage()];
    }

    private void setSettingsModified(boolean settingsModified) {
        this.settingsModified = settingsModified;
        this.clipboard.enabled = settingsModified;
        this.reset.enabled = settingsModified;
    }

    private void setConfirmationControls(boolean confirm) {
        this.confirm.visible = confirm;
        this.cancel.visible = confirm;
        this.done.enabled = !confirm;
        this.prevPage.enabled = !confirm;
        this.nextPage.enabled = !confirm;
        this.reset.enabled = this.settingsModified && !confirm;
        this.list.setActive(!confirm);
    }

    private void enterConfirmation(int confirmMode) {
        this.confirmMode = confirmMode;
        this.setConfirmationControls(true);
    }

    private void exitConfirmation() throws IOException {

        switch (this.confirmMode) {

            case BUTTON_DONE:
                this.actionPerformed((GuiListButton) this.list.getComponent(BUTTON_DONE));
                break;

            case BUTTON_DEFAULTS:
                Setting.resetToDefaults();
                this.createPagedList();
                this.setSettingsModified(false);
        }

        this.confirmMode = 0;
        this.confirmDismissed = true;
        this.setConfirmationControls(false);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

        super.mouseClicked(mouseX, mouseY, mouseButton);

        if (this.confirmMode == 0 && !this.confirmDismissed) {
            this.list.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {

        super.mouseReleased(mouseX, mouseY, state);

        if (this.confirmDismissed) {
            this.confirmDismissed = false;
        }
        else if (this.confirmMode == 0) {
            this.list.mouseReleased(mouseX, mouseY, state);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        this.drawDefaultBackground();
        this.list.drawScreen(mouseX, mouseY, partialTicks);
        this.drawCenteredString(this.fontRenderer, this.title, this.width / 2, 2, 16777215);
        this.drawCenteredString(this.fontRenderer, this.subtitle, this.width / 2, 12, 16777215);
        this.drawCenteredString(this.fontRenderer, this.pageTitle, this.width / 2, 22, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);

        if (this.confirmMode != 0) {
            drawRect(0, 0, this.width, this.height, Integer.MIN_VALUE);
            this.drawHorizontalLine(this.width / 2 - 91, this.width / 2 + 90, 99, -2039584);
            this.drawHorizontalLine(this.width / 2 - 91, this.width / 2 + 90, 185, -6250336);
            this.drawVerticalLine(this.width / 2 - 91, 99, 185, -2039584);
            this.drawVerticalLine(this.width / 2 + 90, 99, 185, -6250336);
            GlStateManager.disableLighting();
            GlStateManager.disableFog();
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder vertexbuffer = tessellator.getBuffer();
            this.mc.getTextureManager().bindTexture(OPTIONS_BACKGROUND);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            vertexbuffer.pos((this.width / 2 - 90), 185.0D, 0.0D).tex(0.0D, 2.65625D).color(64, 64, 64, 64).endVertex();
            vertexbuffer.pos((this.width / 2 + 90), 185.0D, 0.0D).tex(5.625D, 2.65625D).color(64, 64, 64, 64).endVertex();
            vertexbuffer.pos((this.width / 2 + 90), 100.0D, 0.0D).tex(5.625D, 0.0D).color(64, 64, 64, 64).endVertex();
            vertexbuffer.pos((this.width / 2 - 90), 100.0D, 0.0D).tex(0.0D, 0.0D).color(64, 64, 64, 64).endVertex();
            tessellator.draw();
            this.drawCenteredString(this.fontRenderer, I18n.format("createWorld.customize.custom.confirmTitle"), this.width / 2, 105, 16777215);
            this.drawCenteredString(this.fontRenderer, I18n.format("createWorld.customize.custom.confirm1"), this.width / 2, 125, 16777215);
            this.drawCenteredString(this.fontRenderer, I18n.format("createWorld.customize.custom.confirm2"), this.width / 2, 135, 16777215);
            this.confirm.drawButton(this.mc, mouseX, mouseY, partialTicks);
            this.cancel.drawButton(this.mc, mouseX, mouseY, partialTicks);
        }
    }

    private void copyToClipboard() {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(Setting.buildJson().toString()), null);
    }

// TODO: [Generator settings] Disable fixedBiome for now as they require modification to the GenLayer classes to work.
/*
    private static List<Biome> createBiomeList() {

        List<Biome> ret   = Lists.newLinkedList();
        List<Type>  types = Lists.newArrayList();

        Biome.REGISTRY.forEach(biome -> {
            types.clear();
            types.addAll(Lists.newArrayList(BiomeDictionary.getTypesForBiome(biome)));
// TODO: [Generator settings] Add a biome blacklist to the main config and also exclude those biomes
            if (!(types.contains(Type.NETHER) || types.contains(Type.END) || types.contains(Type.BEACH) || types.contains(Type.OCEAN) || types.contains(Type.RIVER))) {
                ret.add(biome);
            }
        });
        return ret;
    }
*/

    // TODO: [Clean-up] This method never gets called but is required by the FormatHelper interface. Check for possible removal.
    @Override
    public String getText(int id, String name, float value) {
        return name + ": " + value;
    }

    enum SettingType {
        BOOLEAN,
        INTEGER,
        FLOAT
    }

    enum Page {
        PAGE1(Category.WORLD, Category.SURFACE, Category.UNDERGROUND),
        PAGE2(Category.RIVERS, Category.RTGLAKES, Category.VANILLASURFACELAKES, Category.VANILLAUNDERGROUNDLAKES, Category.VANILLASPOUTS),
        PAGE3(Category.VILLAGES, Category.MINESHAFTS, Category.DUNGEONS, Category.TEMPLES, Category.MONUMENTS, Category.MANSIONS, Category.STRONGHOLDS),
        PAGE4(Category.DIRT, Category.GRAVEL, Category.GRANITE, Category.DIORITE, Category.ANDESITE,
            Category.COAL, Category.IRON, Category.GOLD, Category.REDSTONE, Category.DIAMOND, Category.LAPIS);

        public final List<Category> CATEGORIES = new ArrayList<>();

        Page(Category... cats) {

            Collections.addAll(this.CATEGORIES, cats);

            if (this.ordinal() == 0) {
                for (Setting setting : Setting.values()) {
                    if (CATEGORIES.contains(setting.getCategory())) {
                        setting.setPage1(); // configure the settings that should be displayed on page 1
                    }
                }
            }
        }

        public int getPageSize() {
            int total = this.CATEGORIES.size() * 2; // Multiply by 2 to accomodate a null object after each GuiLabelEntry for each category (centers text/prevents exception)
            int tmp;
            for (Category category : CATEGORIES) {
                tmp = Setting.getSettingsForCategory(category).size();
                total += tmp % 2 == 0 ? tmp : tmp + 1; // add 1 for categories that have an odd number of entries that need to be padded with a null object
            }
            return total;
        }

        public String getTitle() {
            return I18n.format(LANG_KEY_PREFIX_TITLE + name());
        }

        public boolean isPage1() {
            return this.equals(PAGE1);
        }
    }

    enum Category {
        WORLD,
        SURFACE,
        UNDERGROUND,
        RIVERS,
        RTGLAKES,
        VANILLASURFACELAKES,
        VANILLAUNDERGROUNDLAKES,
        VANILLASPOUTS,
        VILLAGES,
        MINESHAFTS,
        DUNGEONS,
        TEMPLES,
        MONUMENTS,
        MANSIONS,
        STRONGHOLDS,
        DIRT,
        GRAVEL,
        GRANITE,
        DIORITE,
        ANDESITE,
        COAL,
        IRON,
        GOLD,
        REDSTONE,
        DIAMOND,
        LAPIS;

        public String getTitle() {
            return I18n.format(LANG_KEY_PREFIX_CATEGORY + name());
        }
    }

    enum Setting {

        //      Setting name         Type                 Default value                   Min   Max  Category
// TODO: [Generator settings] Disable fixedBiome and biomeSize for now as they require modification to the GenLayer classes to work.
//      fixedBiome          (SettingType.INTEGER, defaults.fixedBiome,             -1,  255, Category.WORLD), // Max value is replaced during createPagedList() to match the size of the biome list
//      biomeSize           (SettingType.INTEGER, defaults.biomeSize,               2,    8, Category.WORLD),
        seaLevel(SettingType.INTEGER, defaults.seaLevel, 31, 95, Category.WORLD),

        useBoulders(SettingType.BOOLEAN, defaults.useBoulders, null, null, Category.SURFACE),
        boulderMult(SettingType.FLOAT, defaults.boulderMult, 0.2f, 5.0f, Category.SURFACE),
        sandDuneHeight(SettingType.INTEGER, defaults.sandDuneHeight, 1, 10, Category.SURFACE),
        //      snowDuneHeight      (SettingType.INTEGER, defaults.snowDuneHeight,          1,   10, Category.SURFACE), // Disabled, no current use
        useSnowLayers(SettingType.BOOLEAN, defaults.useSnowLayers, null, null, Category.SURFACE),

        bedrockLayers(SettingType.INTEGER, defaults.bedrockLayers, 1, 10, Category.UNDERGROUND),
        // value passed to Random, minimum can not be < 1
        useCaves(SettingType.BOOLEAN, defaults.useCaves, null, null, Category.UNDERGROUND),
        caveChance(SettingType.INTEGER, defaults.caveChance, 4, 10, Category.UNDERGROUND),
        caveDensity(SettingType.INTEGER, defaults.caveDensity, 12, 20, Category.UNDERGROUND),
        useRavines(SettingType.BOOLEAN, defaults.useRavines, null, null, Category.UNDERGROUND),
        ravineChance(SettingType.INTEGER, defaults.ravineChance, 25, 100, Category.UNDERGROUND),

        riverSize(SettingType.FLOAT, defaults.riverSizeMult, 0.5f, 2.0f, Category.RIVERS),
        riverFrequency(SettingType.FLOAT, defaults.riverFrequency, 0.0f, 8.0f, Category.RIVERS),
        riverBendMult(SettingType.FLOAT, defaults.riverBendMult, 0.5f, 2.0f, Category.RIVERS),
        riverCutOffAmpl(SettingType.FLOAT, defaults.riverCutOffAmpl, 0.0f, 2.0f, Category.RIVERS),
        riverCutOffScale(SettingType.FLOAT, defaults.riverCutOffScale, 50.0f, 750.0f, Category.RIVERS),

        RTGlakeSizeMult(SettingType.FLOAT, defaults.RTGlakeSizeMult, 0.0f, 2.0f, Category.RTGLAKES),
        RTGlakeFreqMult(SettingType.FLOAT, defaults.RTGlakeFreqMult, 0.0f, 2.0f, Category.RTGLAKES),
        RTGlakeShoreBend(SettingType.FLOAT, defaults.RTGlakeShoreBend, 0.0f, 2.0f, Category.RTGLAKES),

        useWaterLakes(SettingType.BOOLEAN, defaults.useWaterLakes, null, null, Category.VANILLASURFACELAKES),
        waterLakeChance(SettingType.INTEGER, defaults.waterLakeChance, 1, 100, Category.VANILLASURFACELAKES),
        // value passed to Random, minimum can not be < 1
        useLavaLakes(SettingType.BOOLEAN, defaults.useLavaLakes, null, null, Category.VANILLASURFACELAKES),
        lavaLakeChance(SettingType.INTEGER, defaults.lavaLakeChance, 1, 100, Category.VANILLASURFACELAKES),
        // value passed to Random, minimum can not be < 1

        useWaterUndLakes(SettingType.BOOLEAN, defaults.useWaterUndLakes, null, null, Category.VANILLAUNDERGROUNDLAKES),
        waterUndLakeChance(SettingType.INTEGER, defaults.waterUndLakeChance, 1, 100, Category.VANILLAUNDERGROUNDLAKES),
        // value passed to Random, minimum can not be < 1
        useLavaUndLakes(SettingType.BOOLEAN, defaults.useLavaUndLakes, null, null, Category.VANILLAUNDERGROUNDLAKES),
        lavaUndLakeChance(SettingType.INTEGER, defaults.lavaUndLakeChance, 1, 100, Category.VANILLAUNDERGROUNDLAKES),
        // value passed to Random, minimum can not be < 1

        waterSpoutChance(SettingType.INTEGER, defaults.waterSpoutChance, 0, 200, Category.VANILLASPOUTS),
        lavaSpoutChance(SettingType.INTEGER, defaults.lavaSpoutChance, 0, 200, Category.VANILLASPOUTS),


        useVillages(SettingType.BOOLEAN, defaults.useVillages, null, null, Category.VILLAGES),
        villageSize(SettingType.INTEGER, defaults.villageSize, 0, 5, Category.VILLAGES),
        villageDistance(SettingType.INTEGER, defaults.villageDistance, 24, 1024, Category.VILLAGES),

        useMineShafts(SettingType.BOOLEAN, defaults.useMineShafts, null, null, Category.MINESHAFTS),
        mineShaftChance(SettingType.FLOAT, defaults.mineShaftChance, 0.001f, 0.01f, Category.MINESHAFTS),

        useDungeons(SettingType.BOOLEAN, defaults.useDungeons, null, null, Category.DUNGEONS),
        dungeonChance(SettingType.INTEGER, defaults.dungeonChance, 5, 20, Category.DUNGEONS),

        useTemples(SettingType.BOOLEAN, defaults.useTemples, null, null, Category.TEMPLES),
        templeDistance(SettingType.INTEGER, defaults.templeDistance, 16, 64, Category.TEMPLES),

        useMonuments(SettingType.BOOLEAN, defaults.useMonuments, null, null, Category.MONUMENTS),
        monumentSpacing(SettingType.INTEGER, defaults.monumentSpacing, 16, 64, Category.MONUMENTS),
        monumentSeparation(SettingType.INTEGER, defaults.monumentSeparation, 3, 8, Category.MONUMENTS),

        useMansions(SettingType.BOOLEAN, defaults.useMansions, null, null, Category.MANSIONS),
        mansionSpacing(SettingType.INTEGER, defaults.mansionSpacing, 40, 160, Category.MANSIONS),
        mansionSeparation(SettingType.INTEGER, defaults.mansionSeparation, 10, 40, Category.MANSIONS),

        useStrongholds(SettingType.BOOLEAN, defaults.useStrongholds, null, null, Category.STRONGHOLDS),
        strongholdCount(SettingType.INTEGER, defaults.strongholdCount, 0, 256, Category.STRONGHOLDS),
        strongholdDistance(SettingType.INTEGER, defaults.strongholdDistance, 16, 64, Category.STRONGHOLDS),
        strongholdSpread(SettingType.INTEGER, defaults.strongholdSpread, 2, 4, Category.STRONGHOLDS),


        dirtSize(SettingType.INTEGER, defaults.dirtSize, 1, 50, Category.DIRT),
        dirtCount(SettingType.INTEGER, defaults.dirtCount, 0, 40, Category.DIRT),
        dirtMinHeight(SettingType.INTEGER, defaults.dirtMinHeight, 0, 255, Category.DIRT),
        dirtMaxHeight(SettingType.INTEGER, defaults.dirtMaxHeight, 0, 255, Category.DIRT),

        gravelSize(SettingType.INTEGER, defaults.gravelSize, 1, 50, Category.GRAVEL),
        gravelCount(SettingType.INTEGER, defaults.gravelCount, 0, 40, Category.GRAVEL),
        gravelMinHeight(SettingType.INTEGER, defaults.gravelMinHeight, 0, 255, Category.GRAVEL),
        gravelMaxHeight(SettingType.INTEGER, defaults.gravelMaxHeight, 0, 255, Category.GRAVEL),

        graniteSize(SettingType.INTEGER, defaults.graniteSize, 1, 50, Category.GRANITE),
        graniteCount(SettingType.INTEGER, defaults.graniteCount, 0, 40, Category.GRANITE),
        graniteMinHeight(SettingType.INTEGER, defaults.graniteMinHeight, 0, 255, Category.GRANITE),
        graniteMaxHeight(SettingType.INTEGER, defaults.graniteMaxHeight, 0, 255, Category.GRANITE),

        dioriteSize(SettingType.INTEGER, defaults.dioriteSize, 1, 50, Category.DIORITE),
        dioriteCount(SettingType.INTEGER, defaults.dioriteCount, 0, 40, Category.DIORITE),
        dioriteMinHeight(SettingType.INTEGER, defaults.dioriteMinHeight, 0, 255, Category.DIORITE),
        dioriteMaxHeight(SettingType.INTEGER, defaults.dioriteMaxHeight, 0, 255, Category.DIORITE),

        andesiteSize(SettingType.INTEGER, defaults.andesiteSize, 1, 50, Category.ANDESITE),
        andesiteCount(SettingType.INTEGER, defaults.andesiteCount, 0, 40, Category.ANDESITE),
        andesiteMinHeight(SettingType.INTEGER, defaults.andesiteMinHeight, 0, 255, Category.ANDESITE),
        andesiteMaxHeight(SettingType.INTEGER, defaults.andesiteMaxHeight, 0, 255, Category.ANDESITE),

        coalSize(SettingType.INTEGER, defaults.coalSize, 1, 50, Category.COAL),
        coalCount(SettingType.INTEGER, defaults.coalCount, 0, 40, Category.COAL),
        coalMinHeight(SettingType.INTEGER, defaults.coalMinHeight, 0, 255, Category.COAL),
        coalMaxHeight(SettingType.INTEGER, defaults.coalMaxHeight, 0, 255, Category.COAL),

        ironSize(SettingType.INTEGER, defaults.ironSize, 1, 50, Category.IRON),
        ironCount(SettingType.INTEGER, defaults.ironCount, 0, 40, Category.IRON),
        ironMinHeight(SettingType.INTEGER, defaults.ironMinHeight, 0, 255, Category.IRON),
        ironMaxHeight(SettingType.INTEGER, defaults.ironMaxHeight, 0, 255, Category.IRON),

        goldSize(SettingType.INTEGER, defaults.goldSize, 1, 50, Category.GOLD),
        goldCount(SettingType.INTEGER, defaults.goldCount, 0, 40, Category.GOLD),
        goldMinHeight(SettingType.INTEGER, defaults.goldMinHeight, 0, 255, Category.GOLD),
        goldMaxHeight(SettingType.INTEGER, defaults.goldMaxHeight, 0, 255, Category.GOLD),

        redstoneSize(SettingType.INTEGER, defaults.redstoneSize, 1, 50, Category.REDSTONE),
        redstoneCount(SettingType.INTEGER, defaults.redstoneCount, 0, 40, Category.REDSTONE),
        redstoneMinHeight(SettingType.INTEGER, defaults.redstoneMinHeight, 0, 255, Category.REDSTONE),
        redstoneMaxHeight(SettingType.INTEGER, defaults.redstoneMaxHeight, 0, 255, Category.REDSTONE),

        diamondSize(SettingType.INTEGER, defaults.diamondSize, 1, 50, Category.DIAMOND),
        diamondCount(SettingType.INTEGER, defaults.diamondCount, 0, 40, Category.DIAMOND),
        diamondMinHeight(SettingType.INTEGER, defaults.diamondMinHeight, 0, 255, Category.DIAMOND),
        diamondMaxHeight(SettingType.INTEGER, defaults.diamondMaxHeight, 0, 255, Category.DIAMOND),

        lapisSize(SettingType.INTEGER, defaults.lapisSize, 1, 50, Category.LAPIS),
        lapisCount(SettingType.INTEGER, defaults.lapisCount, 0, 40, Category.LAPIS),
        lapisCenterHeight(SettingType.INTEGER, defaults.lapisCenterHeight, 0, 255, Category.LAPIS),
        lapisSpread(SettingType.INTEGER, defaults.lapisSpread, 0, 255, Category.LAPIS);


        private final SettingType settingType;
        private final int id;
        private final Category category;
        private final Object defValue;
        private final Object minValue;
        private final Object maxValue;
        private boolean isPage1;
        private Object curValue;
        private Object returnValue;

        Setting(SettingType settingType, Object defValue, @Nullable Object minValue, @Nullable Object maxValue, Category category) {

            this.settingType = settingType;
            this.id = ID_OFFSET + this.ordinal(); // Make IDs unique with no chance of collision
            this.category = category;

            this.defValue = defValue;
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.curValue = this.defValue;
        }

        public static List<Setting> getSettingsForCategory(Category category) {
            List<Setting> ret = Lists.newArrayList();
            for (Setting setting : values()) {
                if (setting.getCategory().equals(category)) {
                    ret.add(setting);
                }
            }
            return ret;
        }

        @Nullable
        public static Setting getByID(int id) {
            for (Setting setting : values()) {
                if (setting.getID() == id) {
                    return setting;
                }
            }
            return null;
        }

        public static boolean isModified() {

            for (Setting setting : values()) {

                if (setting.getSettingType().equals(SettingType.BOOLEAN)) {
                    if (setting.curValue().getBool() != setting.defValue().getBool()) {
                        return true;
                    }
                }

                if (setting.getSettingType().equals(SettingType.INTEGER)) {
                    if (setting.curValue().getInt() != setting.defValue().getInt()) {
                        return true;
                    }
                }

                if (setting.getSettingType().equals(SettingType.FLOAT)) {
                    if (setting.curValue().getFloat() != setting.defValue().getFloat()) {
                        return true;
                    }
                }
            }
            return false;
        }

        public static JsonObject buildJson() {
            JsonObject ret = new JsonObject();
            for (Setting setting : values()) {

                if (setting.getSettingType().equals(SettingType.BOOLEAN)) {
                    if (setting.curValue().getBool() != setting.defValue().getBool()) {
                        ret.addProperty(setting.name(), setting.curValue().getBool());
                    }
                }

                if (setting.getSettingType().equals(SettingType.INTEGER)) {
                    if (setting.curValue().getInt() != setting.defValue().getInt()) {

                        // translate the fixedBiome value (biomeList index) to an actual biome ID
// TODO: [Generator settings] Disable fixedBiome for now as they require modification to the GenLayer classes to work.
/*                        if (setting.equals(Setting.fixedBiome)) {
                            if (Setting.fixedBiome.curValue().getInt() == -1) {
                                ret.addProperty(setting.name(), -1);
                            }
                            else {
                                ret.addProperty(setting.name(), Biome.getIdForBiome(biomeList.get(Setting.fixedBiome.curValue().getInt())));
                            }
                        }

                        else*/
                        ret.addProperty(setting.name(), setting.curValue().getInt());
                    }
                }

                if (setting.getSettingType().equals(SettingType.FLOAT)) {
                    if (setting.curValue().getFloat() != setting.defValue().getFloat()) {
                        ret.addProperty(setting.name(), setting.curValue().getFloat());
                    }
                }

            }
            return ret;
        }

        public static void resetToDefaults() {
            for (Setting setting : values()) {
                setting.setCurValue(setting.defValue);
            }
        }

        public static void parseSettings(String generatorSettings) {

            RTGChunkGenSettings.Factory factory = RTGChunkGenSettings.Factory.jsonToFactory(generatorSettings);
            if (factory == null) {
                Logger.error("Error parsing RTGChunkGenSettings settings from string: {}", generatorSettings);
                return;
            }

            JsonObject json = new JsonParser().parse(factory.toString()).getAsJsonObject();

            final Setting[] setting = new Setting[1]; // final, to appease the lambda gods.
            json.entrySet().forEach(entry -> {

                // A defensive try-catch to prevent an IllegalArgumentException in the case that the
                // generator settings Factory has an option for which there is not an enum value.
                try {
                    setting[0] = Setting.valueOf(entry.getKey());
                }
                catch (IllegalArgumentException ignored) {
                    Logger.error("GuiCustomizeWorldScreenRTG$Setting#parseSettings: Illegal argument. No enum exists for the setting: " + entry.getKey());
                    setting[0] = null;
                }

                if (setting[0] != null) {

                    if (setting[0].getSettingType().equals(SettingType.BOOLEAN)) {
                        setting[0].setCurValue(entry.getValue().getAsBoolean());
                    }

                    if (setting[0].getSettingType().equals(SettingType.INTEGER)) {

                        // Special handling for fixedBiome to set and display the correct biome when parsing existing settings
// TODO: [Generator settings] Disable fixedBiome for now as they require modification to the GenLayer classes to work.
/*                        if (setting[0].equals(Setting.fixedBiome)) {
                            int biomeID = entry.getValue().getAsInt();
                            Biome biome = Biome.getBiome(biomeID);
                            setting[0].setCurValue(biome == null ? -1 : biomeList.indexOf(biome));
                        }
                        else*/
                        setting[0].setCurValue(entry.getValue().getAsInt());
                    }

                    if (setting[0].getSettingType().equals(SettingType.FLOAT)) {
                        setting[0].setCurValue(entry.getValue().getAsFloat());
                    }
                }
            });
        }

        public SettingType getSettingType() {
            return this.settingType;
        }

        public int getID() {
            return this.id;
        }

        public Category getCategory() {
            return this.category;
        }

        public String getLangKey() {
            return LANG_KEY_PREFIX_SETTING + name();
        }

        public boolean isPage1() {
            return isPage1;
        }

        public void setPage1() {
            isPage1 = true;
        }

        public Setting defValue() {
            this.returnValue = this.defValue;
            return this;
        }

        public Setting minValue() {
            this.returnValue = this.minValue;
            return this;
        }

        public Setting maxValue() {
            this.returnValue = this.maxValue;
            return this;
        }

        public Setting curValue() {
            this.returnValue = this.curValue;
            return this;
        }

        public boolean getBool() {
            return (boolean) this.returnValue;
        }

        public int getInt() {
            if (returnValue instanceof Float) {
                return ((Float) this.returnValue).intValue();
            }
            return (int) this.returnValue;
        }

        public float getFloat() {
            if (returnValue instanceof Integer) {
                return ((Integer) this.returnValue).floatValue();
            }
            return (float) this.returnValue;
        }

        public void setCurValue(boolean val) {
            this.curValue = val;
        }

        public void setCurValue(int val) {
            this.curValue = val;
        }

        public void setCurValue(float val) {
            this.curValue = val;
        }

        public void setCurValue(Object val) {
            this.curValue = val;
        }
    }

    private static class Id {

        private int count = 0;

        public int next() {
            return count++;
        }
    }
}
