package teamrtg.rtg.client;

import java.io.IOException;
import java.util.Random;

import com.google.common.base.Predicate;
import com.google.common.primitives.Floats;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiListButton;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlider;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author topisani
 */
@SideOnly(Side.CLIENT)
public class GuiCustomizeWorldScreenRTG extends GuiScreen implements GuiSlider.FormatHelper, GuiPageButtonList.GuiResponder {
    protected String title = "Customize World Settings";
    protected String subtitle = "Page 1 of 3";
    protected String pageTitle = "Basic Settings";
    protected String[] pageNames = new String[4];
    private GuiCreateWorld parent;
    private GuiPageButtonList list;
    private GuiButton done;
    private GuiButton randomize;
    private GuiButton defaults;
    private GuiButton previousPage;
    private GuiButton nextPage;
    private GuiButton confirm;
    private GuiButton cancel;
    private GuiButton presets;
    private boolean settingsModified = false;
    private int confirmMode = 0;
    private boolean confirmDismissed = false;
    private Predicate<String> numberFilter = new Predicate<String>() {
        public boolean apply(String p_apply_1_) {
            Float f = Floats.tryParse(p_apply_1_);
            return p_apply_1_.isEmpty() || f != null && Floats.isFinite(f.floatValue()) && f.floatValue() >= 0.0F;
        }
    };
    private ChunkProviderSettings.Factory defaultSettings = new ChunkProviderSettings.Factory();
    private ChunkProviderSettings.Factory settings;
    private Random random = new Random();

    public GuiCustomizeWorldScreenRTG(GuiScreen p_i45521_1_, String p_i45521_2_) {
        this.parent = (GuiCreateWorld) p_i45521_1_;
        this.loadValues(p_i45521_2_);
    }

    public void loadValues(String p_175324_1_) {
        if (p_175324_1_ != null && !p_175324_1_.isEmpty()) {
            this.settings = ChunkProviderSettings.Factory.jsonToFactory(p_175324_1_);
        } else {
            this.settings = new ChunkProviderSettings.Factory();
        }
    }

    public String saveValues() {
        return this.settings.toString().replace("\n", "");
    }

    public String getText(int id, String name, float value) {
        return name + ": " + this.getFormattedValue(id, value);
    }

    private String getFormattedValue(int p_175330_1_, float p_175330_2_) {
        switch (p_175330_1_) {
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 107:
            case 108:
            case 110:
            case 111:
            case 132:
            case 133:
            case 134:
            case 135:
            case 136:
            case 139:
            case 140:
            case 142:
            case 143:
                return String.format("%5.3f", Float.valueOf(p_175330_2_));
            case 105:
            case 106:
            case 109:
            case 112:
            case 113:
            case 114:
            case 115:
            case 137:
            case 138:
            case 141:
            case 144:
            case 145:
            case 146:
            case 147:
                return String.format("%2.3f", Float.valueOf(p_175330_2_));
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 148:
            case 149:
            case 150:
            case 151:
            case 152:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:
            case 160:
            case 161:
            default:
                return String.format("%d", Integer.valueOf((int) p_175330_2_));
            case 162:

                if (p_175330_2_ < 0.0F) {
                    return I18n.format("gui.all");
                } else if ((int) p_175330_2_ >= Biome.getIdForBiome(Biomes.HELL)) {
                    Biome biome1 = Biome.getBiomeForId((int) p_175330_2_ + 2);
                    return biome1 != null ? biome1.getBiomeName() : "?";
                } else {
                    Biome biome = Biome.getBiomeForId((int) p_175330_2_);
                    return biome != null ? biome.getBiomeName() : "?";
                }
        }
    }

    public void setEntryValue(int id, boolean value) {
        switch (id) {
            case 148:
                this.settings.useCaves = value;
                break;
            case 149:
                this.settings.useDungeons = value;
                break;
            case 150:
                this.settings.useStrongholds = value;
                break;
            case 151:
                this.settings.useVillages = value;
                break;
            case 152:
                this.settings.useMineShafts = value;
                break;
            case 153:
                this.settings.useTemples = value;
                break;
            case 154:
                this.settings.useRavines = value;
                break;
            case 155:
                this.settings.useWaterLakes = value;
                break;
            case 156:
                this.settings.useLavaLakes = value;
                break;
            case 161:
                this.settings.useLavaOceans = value;
                break;
            case 210:
                this.settings.useMonuments = value;
        }

        if (!this.settings.equals(this.defaultSettings)) {
            this.setSettingsModified(true);
        }
    }

    private void setSettingsModified(boolean p_181031_1_) {
        this.settingsModified = p_181031_1_;
        this.defaults.enabled = p_181031_1_;
    }

    public void setEntryValue(int id, float value) {
        switch (id) {
            case 100:
                this.settings.mainNoiseScaleX = value;
                break;
            case 101:
                this.settings.mainNoiseScaleY = value;
                break;
            case 102:
                this.settings.mainNoiseScaleZ = value;
                break;
            case 103:
                this.settings.depthNoiseScaleX = value;
                break;
            case 104:
                this.settings.depthNoiseScaleZ = value;
                break;
            case 105:
                this.settings.depthNoiseScaleExponent = value;
                break;
            case 106:
                this.settings.baseSize = value;
                break;
            case 107:
                this.settings.coordinateScale = value;
                break;
            case 108:
                this.settings.heightScale = value;
                break;
            case 109:
                this.settings.stretchY = value;
                break;
            case 110:
                this.settings.upperLimitScale = value;
                break;
            case 111:
                this.settings.lowerLimitScale = value;
                break;
            case 112:
                this.settings.biomeDepthWeight = value;
                break;
            case 113:
                this.settings.biomeDepthOffset = value;
                break;
            case 114:
                this.settings.biomeScaleWeight = value;
                break;
            case 115:
                this.settings.biomeScaleOffset = value;
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
            case 138:
            case 139:
            case 140:
            case 141:
            case 142:
            case 143:
            case 144:
            case 145:
            case 146:
            case 147:
            case 148:
            case 149:
            case 150:
            case 151:
            case 152:
            case 153:
            case 154:
            case 155:
            case 156:
            case 161:
            case 188:
            default:
                break;
            case 157:
                this.settings.dungeonChance = (int) value;
                break;
            case 158:
                this.settings.waterLakeChance = (int) value;
                break;
            case 159:
                this.settings.lavaLakeChance = (int) value;
                break;
            case 160:
                this.settings.seaLevel = (int) value;
                break;
            case 162:
                this.settings.fixedBiome = (int) value;
                break;
            case 163:
                this.settings.biomeSize = (int) value;
                break;
            case 164:
                this.settings.riverSize = (int) value;
                break;
            case 165:
                this.settings.dirtSize = (int) value;
                break;
            case 166:
                this.settings.dirtCount = (int) value;
                break;
            case 167:
                this.settings.dirtMinHeight = (int) value;
                break;
            case 168:
                this.settings.dirtMaxHeight = (int) value;
                break;
            case 169:
                this.settings.gravelSize = (int) value;
                break;
            case 170:
                this.settings.gravelCount = (int) value;
                break;
            case 171:
                this.settings.gravelMinHeight = (int) value;
                break;
            case 172:
                this.settings.gravelMaxHeight = (int) value;
                break;
            case 173:
                this.settings.graniteSize = (int) value;
                break;
            case 174:
                this.settings.graniteCount = (int) value;
                break;
            case 175:
                this.settings.graniteMinHeight = (int) value;
                break;
            case 176:
                this.settings.graniteMaxHeight = (int) value;
                break;
            case 177:
                this.settings.dioriteSize = (int) value;
                break;
            case 178:
                this.settings.dioriteCount = (int) value;
                break;
            case 179:
                this.settings.dioriteMinHeight = (int) value;
                break;
            case 180:
                this.settings.dioriteMaxHeight = (int) value;
                break;
            case 181:
                this.settings.andesiteSize = (int) value;
                break;
            case 182:
                this.settings.andesiteCount = (int) value;
                break;
            case 183:
                this.settings.andesiteMinHeight = (int) value;
                break;
            case 184:
                this.settings.andesiteMaxHeight = (int) value;
                break;
            case 185:
                this.settings.coalSize = (int) value;
                break;
            case 186:
                this.settings.coalCount = (int) value;
                break;
            case 187:
                this.settings.coalMinHeight = (int) value;
                break;
            case 189:
                this.settings.coalMaxHeight = (int) value;
                break;
            case 190:
                this.settings.ironSize = (int) value;
                break;
            case 191:
                this.settings.ironCount = (int) value;
                break;
            case 192:
                this.settings.ironMinHeight = (int) value;
                break;
            case 193:
                this.settings.ironMaxHeight = (int) value;
                break;
            case 194:
                this.settings.goldSize = (int) value;
                break;
            case 195:
                this.settings.goldCount = (int) value;
                break;
            case 196:
                this.settings.goldMinHeight = (int) value;
                break;
            case 197:
                this.settings.goldMaxHeight = (int) value;
                break;
            case 198:
                this.settings.redstoneSize = (int) value;
                break;
            case 199:
                this.settings.redstoneCount = (int) value;
                break;
            case 200:
                this.settings.redstoneMinHeight = (int) value;
                break;
            case 201:
                this.settings.redstoneMaxHeight = (int) value;
                break;
            case 202:
                this.settings.diamondSize = (int) value;
                break;
            case 203:
                this.settings.diamondCount = (int) value;
                break;
            case 204:
                this.settings.diamondMinHeight = (int) value;
                break;
            case 205:
                this.settings.diamondMaxHeight = (int) value;
                break;
            case 206:
                this.settings.lapisSize = (int) value;
                break;
            case 207:
                this.settings.lapisCount = (int) value;
                break;
            case 208:
                this.settings.lapisCenterHeight = (int) value;
                break;
            case 209:
                this.settings.lapisSpread = (int) value;
        }

        if (id >= 100 && id < 116) {
            Gui gui = this.list.getComponent(id - 100 + 132);

            if (gui != null) {
                ((GuiTextField) gui).setText(this.getFormattedValue(id, value));
            }
        }

        if (!this.settings.equals(this.defaultSettings)) {
            this.setSettingsModified(true);
        }
    }

    public void setEntryValue(int id, String value) {
        float f = 0.0F;

        try {
            f = Float.parseFloat(value);
        } catch (NumberFormatException var5) {
        }

        float f1 = 0.0F;

        switch (id) {
            case 132:
                f1 = this.settings.mainNoiseScaleX = MathHelper.clamp_float(f, 1.0F, 5000.0F);
                break;
            case 133:
                f1 = this.settings.mainNoiseScaleY = MathHelper.clamp_float(f, 1.0F, 5000.0F);
                break;
            case 134:
                f1 = this.settings.mainNoiseScaleZ = MathHelper.clamp_float(f, 1.0F, 5000.0F);
                break;
            case 135:
                f1 = this.settings.depthNoiseScaleX = MathHelper.clamp_float(f, 1.0F, 2000.0F);
                break;
            case 136:
                f1 = this.settings.depthNoiseScaleZ = MathHelper.clamp_float(f, 1.0F, 2000.0F);
                break;
            case 137:
                f1 = this.settings.depthNoiseScaleExponent = MathHelper.clamp_float(f, 0.01F, 20.0F);
                break;
            case 138:
                f1 = this.settings.baseSize = MathHelper.clamp_float(f, 1.0F, 25.0F);
                break;
            case 139:
                f1 = this.settings.coordinateScale = MathHelper.clamp_float(f, 1.0F, 6000.0F);
                break;
            case 140:
                f1 = this.settings.heightScale = MathHelper.clamp_float(f, 1.0F, 6000.0F);
                break;
            case 141:
                f1 = this.settings.stretchY = MathHelper.clamp_float(f, 0.01F, 50.0F);
                break;
            case 142:
                f1 = this.settings.upperLimitScale = MathHelper.clamp_float(f, 1.0F, 5000.0F);
                break;
            case 143:
                f1 = this.settings.lowerLimitScale = MathHelper.clamp_float(f, 1.0F, 5000.0F);
                break;
            case 144:
                f1 = this.settings.biomeDepthWeight = MathHelper.clamp_float(f, 1.0F, 20.0F);
                break;
            case 145:
                f1 = this.settings.biomeDepthOffset = MathHelper.clamp_float(f, 0.0F, 20.0F);
                break;
            case 146:
                f1 = this.settings.biomeScaleWeight = MathHelper.clamp_float(f, 1.0F, 20.0F);
                break;
            case 147:
                f1 = this.settings.biomeScaleOffset = MathHelper.clamp_float(f, 0.0F, 20.0F);
        }

        if (f1 != f && f != 0.0F) {
            ((GuiTextField) this.list.getComponent(id)).setText(this.getFormattedValue(id, f1));
        }

        ((GuiSlider) this.list.getComponent(id - 132 + 100)).setSliderValue(f1, false);

        if (!this.settings.equals(this.defaultSettings)) {
            this.setSettingsModified(true);
        }
    }

    private void restoreDefaults() {
        this.settings.setDefaults();
        this.createPagedList();
        this.setSettingsModified(false);
    }

    private void enterConfirmation(int p_175322_1_) {
        this.confirmMode = p_175322_1_;
        this.setConfirmationControls(true);
    }

    private void exitConfirmation() throws IOException {
        switch (this.confirmMode) {
            case 300:
                this.actionPerformed((GuiListButton) this.list.getComponent(300));
                break;
            case 304:
                this.restoreDefaults();
        }

        this.confirmMode = 0;
        this.confirmDismissed = true;
        this.setConfirmationControls(false);
    }

    private void setConfirmationControls(boolean p_175329_1_) {
        this.confirm.visible = p_175329_1_;
        this.cancel.visible = p_175329_1_;
        this.randomize.enabled = !p_175329_1_;
        this.done.enabled = !p_175329_1_;
        this.previousPage.enabled = !p_175329_1_;
        this.nextPage.enabled = !p_175329_1_;
        this.defaults.enabled = this.settingsModified && !p_175329_1_;
        this.presets.enabled = !p_175329_1_;
        this.list.setActive(!p_175329_1_);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.list.drawScreen(mouseX, mouseY, partialTicks);
        this.drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 2, 16777215);
        this.drawCenteredString(this.fontRendererObj, this.subtitle, this.width / 2, 12, 16777215);
        this.drawCenteredString(this.fontRendererObj, this.pageTitle, this.width / 2, 22, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);

        if (this.confirmMode != 0) {
            drawRect(0, 0, this.width, this.height, Integer.MIN_VALUE);
            this.drawHorizontalLine(this.width / 2 - 91, this.width / 2 + 90, 99, -2039584);
            this.drawHorizontalLine(this.width / 2 - 91, this.width / 2 + 90, 185, -6250336);
            this.drawVerticalLine(this.width / 2 - 91, 99, 185, -2039584);
            this.drawVerticalLine(this.width / 2 + 90, 99, 185, -6250336);
            float f = 85.0F;
            float f1 = 180.0F;
            GlStateManager.disableLighting();
            GlStateManager.disableFog();
            Tessellator tessellator = Tessellator.getInstance();
            VertexBuffer vertexbuffer = tessellator.getBuffer();
            this.mc.getTextureManager().bindTexture(OPTIONS_BACKGROUND);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            float f2 = 32.0F;
            vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            vertexbuffer.pos((double) (this.width / 2 - 90), 185.0D, 0.0D).tex(0.0D, 2.65625D).color(64, 64, 64, 64).endVertex();
            vertexbuffer.pos((double) (this.width / 2 + 90), 185.0D, 0.0D).tex(5.625D, 2.65625D).color(64, 64, 64, 64).endVertex();
            vertexbuffer.pos((double) (this.width / 2 + 90), 100.0D, 0.0D).tex(5.625D, 0.0D).color(64, 64, 64, 64).endVertex();
            vertexbuffer.pos((double) (this.width / 2 - 90), 100.0D, 0.0D).tex(0.0D, 0.0D).color(64, 64, 64, 64).endVertex();
            tessellator.draw();
            this.drawCenteredString(this.fontRendererObj, I18n.format("createWorld.customize.custom.confirmTitle"), this.width / 2, 105, 16777215);
            this.drawCenteredString(this.fontRendererObj, I18n.format("createWorld.customize.custom.confirm1"), this.width / 2, 125, 16777215);
            this.drawCenteredString(this.fontRendererObj, I18n.format("createWorld.customize.custom.confirm2"), this.width / 2, 135, 16777215);
            this.confirm.drawButton(this.mc, mouseX, mouseY);
            this.cancel.drawButton(this.mc, mouseX, mouseY);
        }
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);

        if (this.confirmMode == 0) {
            switch (keyCode) {
                case 200:
                    this.modifyFocusValue(1.0F);
                    break;
                case 208:
                    this.modifyFocusValue(-1.0F);
                    break;
                default:
                    this.list.onKeyPressed(typedChar, keyCode);
            }
        }
    }

    private void modifyFocusValue(float p_175327_1_) {
        Gui gui = this.list.getFocusedControl();

        if (gui instanceof GuiTextField) {
            float f = p_175327_1_;

            if (GuiScreen.isShiftKeyDown()) {
                f = p_175327_1_ * 0.1F;

                if (GuiScreen.isCtrlKeyDown()) {
                    f *= 0.1F;
                }
            } else if (GuiScreen.isCtrlKeyDown()) {
                f = p_175327_1_ * 10.0F;

                if (GuiScreen.isAltKeyDown()) {
                    f *= 10.0F;
                }
            }

            GuiTextField guitextfield = (GuiTextField) gui;
            Float f1 = Floats.tryParse(guitextfield.getText());

            if (f1 != null) {
                f1 = Float.valueOf(f1.floatValue() + f);
                int i = guitextfield.getId();
                String s = this.getFormattedValue(guitextfield.getId(), f1.floatValue());
                guitextfield.setText(s);
                this.setEntryValue(i, s);
            }
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        if (this.confirmMode == 0 && !this.confirmDismissed) {
            this.list.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);

        if (this.confirmDismissed) {
            this.confirmDismissed = false;
        } else if (this.confirmMode == 0) {
            this.list.mouseReleased(mouseX, mouseY, state);
        }
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.enabled) {
            switch (button.id) {
                case 300:
                    this.parent.chunkProviderSettingsJson = this.settings.toString();
                    this.mc.displayGuiScreen(this.parent);
                    break;
                case 301:

                    for (int i = 0; i < this.list.getSize(); ++i) {
                        GuiPageButtonList.GuiEntry guipagebuttonlist$guientry = this.list.getListEntry(i);
                        Gui gui = guipagebuttonlist$guientry.getComponent1();

                        if (gui instanceof GuiButton) {
                            GuiButton guibutton = (GuiButton) gui;

                            if (guibutton instanceof GuiSlider) {
                                float f = ((GuiSlider) guibutton).getSliderPosition() * (0.75F + this.random.nextFloat() * 0.5F) + (this.random.nextFloat() * 0.1F - 0.05F);
                                ((GuiSlider) guibutton).setSliderPosition(MathHelper.clamp_float(f, 0.0F, 1.0F));
                            } else if (guibutton instanceof GuiListButton) {
                                ((GuiListButton) guibutton).setValue(this.random.nextBoolean());
                            }
                        }

                        Gui gui1 = guipagebuttonlist$guientry.getComponent2();

                        if (gui1 instanceof GuiButton) {
                            GuiButton guibutton1 = (GuiButton) gui1;

                            if (guibutton1 instanceof GuiSlider) {
                                float f1 = ((GuiSlider) guibutton1).getSliderPosition() * (0.75F + this.random.nextFloat() * 0.5F) + (this.random.nextFloat() * 0.1F - 0.05F);
                                ((GuiSlider) guibutton1).setSliderPosition(MathHelper.clamp_float(f1, 0.0F, 1.0F));
                            } else if (guibutton1 instanceof GuiListButton) {
                                ((GuiListButton) guibutton1).setValue(this.random.nextBoolean());
                            }
                        }
                    }

                    return;
                case 302:
                    this.list.previousPage();
                    this.updatePageControls();
                    break;
                case 303:
                    this.list.nextPage();
                    this.updatePageControls();
                    break;
                case 304:

                    if (this.settingsModified) {
                        this.enterConfirmation(304);
                    }

                    break;
                //case 305:
                //this.mc.displayGuiScreen(new GuiScreenCustomizePresets(this));
                //break;
                case 306:
                    this.exitConfirmation();
                    break;
                case 307:
                    this.confirmMode = 0;
                    this.exitConfirmation();
            }
        }
    }

    public void initGui() {
        int i = 0;
        int j = 0;

        if (this.list != null) {
            i = this.list.getPage();
            j = this.list.getAmountScrolled();
        }

        this.title = I18n.format("options.customizeTitle");
        this.buttonList.clear();
        this.buttonList.add(this.previousPage = new GuiButton(302, 20, 5, 80, 20, I18n.format("createWorld.customize.custom.prev")));
        this.buttonList.add(this.nextPage = new GuiButton(303, this.width - 100, 5, 80, 20, I18n.format("createWorld.customize.custom.next")));
        this.buttonList.add(this.defaults = new GuiButton(304, this.width / 2 - 187, this.height - 27, 90, 20, I18n.format("createWorld.customize.custom.defaults")));
        this.buttonList.add(this.randomize = new GuiButton(301, this.width / 2 - 92, this.height - 27, 90, 20, I18n.format("createWorld.customize.custom.randomize")));
        //this.buttonList.add(this.presets = new GuiButton(305, this.width / 2 + 3, this.height - 27, 90, 20, I18n.format("createWorld.customize.custom.presets", new Object[0])));
        this.buttonList.add(this.done = new GuiButton(300, this.width / 2 + 98, this.height - 27, 90, 20, I18n.format("gui.done")));
        this.defaults.enabled = this.settingsModified;
        this.confirm = new GuiButton(306, this.width / 2 - 55, 160, 50, 20, I18n.format("gui.yes"));
        this.confirm.visible = false;
        this.buttonList.add(this.confirm);
        this.cancel = new GuiButton(307, this.width / 2 + 5, 160, 50, 20, I18n.format("gui.no"));
        this.cancel.visible = false;
        this.buttonList.add(this.cancel);

        if (this.confirmMode != 0) {
            this.confirm.visible = true;
            this.cancel.visible = true;
        }

        this.createPagedList();

        if (i != 0) {
            this.list.setPage(i);
            this.list.scrollBy(j);
            this.updatePageControls();
        }
    }

    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        this.list.handleMouseInput();
    }

    private void createPagedList() {
        GuiPageButtonList.GuiListEntry[] aguipagebuttonlist$guilistentry =
        new GuiPageButtonList.GuiListEntry[]{
        new GuiPageButtonList.GuiSlideEntry(160, I18n.format("createWorld.customize.custom.seaLevel"), true, this, 1.0F, 255.0F, (float) this.settings.seaLevel),
        new GuiPageButtonList.GuiButtonEntry(148, I18n.format("createWorld.customize.custom.useCaves"), true, this.settings.useCaves),
        new GuiPageButtonList.GuiButtonEntry(150, I18n.format("createWorld.customize.custom.useStrongholds"), true, this.settings.useStrongholds),
        new GuiPageButtonList.GuiButtonEntry(151, I18n.format("createWorld.customize.custom.useVillages"), true, this.settings.useVillages),
        new GuiPageButtonList.GuiButtonEntry(152, I18n.format("createWorld.customize.custom.useMineShafts"), true, this.settings.useMineShafts),
        new GuiPageButtonList.GuiButtonEntry(153, I18n.format("createWorld.customize.custom.useTemples"), true, this.settings.useTemples),
        new GuiPageButtonList.GuiButtonEntry(210, I18n.format("createWorld.customize.custom.useMonuments"), true, this.settings.useMonuments),
        new GuiPageButtonList.GuiButtonEntry(154, I18n.format("createWorld.customize.custom.useRavines"), true, this.settings.useRavines),
        new GuiPageButtonList.GuiButtonEntry(149, I18n.format("createWorld.customize.custom.useDungeons"), true, this.settings.useDungeons),
        new GuiPageButtonList.GuiSlideEntry(157, I18n.format("createWorld.customize.custom.dungeonChance"), true, this, 1.0F, 100.0F, (float) this.settings.dungeonChance),
        new GuiPageButtonList.GuiButtonEntry(155, I18n.format("createWorld.customize.custom.useWaterLakes"), true, this.settings.useWaterLakes),
        new GuiPageButtonList.GuiSlideEntry(158, I18n.format("createWorld.customize.custom.waterLakeChance"), true, this, 1.0F, 100.0F, (float) this.settings.waterLakeChance),
        new GuiPageButtonList.GuiButtonEntry(156, I18n.format("createWorld.customize.custom.useLavaLakes"), true, this.settings.useLavaLakes),
        new GuiPageButtonList.GuiSlideEntry(159, I18n.format("createWorld.customize.custom.lavaLakeChance"), true, this, 10.0F, 100.0F, (float) this.settings.lavaLakeChance),
        new GuiPageButtonList.GuiButtonEntry(161, I18n.format("createWorld.customize.custom.useLavaOceans"), true, this.settings.useLavaOceans),
        new GuiPageButtonList.GuiSlideEntry(162, I18n.format("createWorld.customize.custom.fixedBiome"), true, this, -1.0F, 37.0F, (float) this.settings.fixedBiome),
        new GuiPageButtonList.GuiSlideEntry(163, I18n.format("createWorld.customize.custom.biomeSize"), true, this, 1.0F, 8.0F, (float) this.settings.biomeSize),
        new GuiPageButtonList.GuiSlideEntry(164, I18n.format("createWorld.customize.custom.riverSize"), true, this, 1.0F, 5.0F, (float) this.settings.riverSize)};
                GuiPageButtonList.GuiListEntry[] aguipagebuttonlist$guilistentry1 = new GuiPageButtonList.GuiListEntry[]{new GuiPageButtonList.GuiLabelEntry(416, I18n.format("tile.dirt.name"), false), null,
        new GuiPageButtonList.GuiSlideEntry(165, I18n.format("createWorld.customize.custom.size"), false, this, 1.0F, 50.0F, (float) this.settings.dirtSize),
        new GuiPageButtonList.GuiSlideEntry(166, I18n.format("createWorld.customize.custom.count"), false, this, 0.0F, 40.0F, (float) this.settings.dirtCount),
        new GuiPageButtonList.GuiSlideEntry(167, I18n.format("createWorld.customize.custom.minHeight"), false, this, 0.0F, 255.0F, (float) this.settings.dirtMinHeight),
        new GuiPageButtonList.GuiSlideEntry(168, I18n.format("createWorld.customize.custom.maxHeight"), false, this, 0.0F, 255.0F, (float) this.settings.dirtMaxHeight),
        new GuiPageButtonList.GuiLabelEntry(417, I18n.format("tile.gravel.name"), false), null,
        new GuiPageButtonList.GuiSlideEntry(169, I18n.format("createWorld.customize.custom.size"), false, this, 1.0F, 50.0F, (float) this.settings.gravelSize),
        new GuiPageButtonList.GuiSlideEntry(170, I18n.format("createWorld.customize.custom.count"), false, this, 0.0F, 40.0F, (float) this.settings.gravelCount),
        new GuiPageButtonList.GuiSlideEntry(171, I18n.format("createWorld.customize.custom.minHeight"), false, this, 0.0F, 255.0F, (float) this.settings.gravelMinHeight),
        new GuiPageButtonList.GuiSlideEntry(172, I18n.format("createWorld.customize.custom.maxHeight"), false, this, 0.0F, 255.0F, (float) this.settings.gravelMaxHeight),
        new GuiPageButtonList.GuiLabelEntry(418, I18n.format("tile.stone.granite.name"), false), null,
        new GuiPageButtonList.GuiSlideEntry(173, I18n.format("createWorld.customize.custom.size"), false, this, 1.0F, 50.0F, (float) this.settings.graniteSize),
        new GuiPageButtonList.GuiSlideEntry(174, I18n.format("createWorld.customize.custom.count"), false, this, 0.0F, 40.0F, (float) this.settings.graniteCount),
        new GuiPageButtonList.GuiSlideEntry(175, I18n.format("createWorld.customize.custom.minHeight"), false, this, 0.0F, 255.0F, (float) this.settings.graniteMinHeight),
        new GuiPageButtonList.GuiSlideEntry(176, I18n.format("createWorld.customize.custom.maxHeight"), false, this, 0.0F, 255.0F, (float) this.settings.graniteMaxHeight),
        new GuiPageButtonList.GuiLabelEntry(419, I18n.format("tile.stone.diorite.name"), false), null,
        new GuiPageButtonList.GuiSlideEntry(177, I18n.format("createWorld.customize.custom.size"), false, this, 1.0F, 50.0F, (float) this.settings.dioriteSize),
        new GuiPageButtonList.GuiSlideEntry(178, I18n.format("createWorld.customize.custom.count"), false, this, 0.0F, 40.0F, (float) this.settings.dioriteCount),
        new GuiPageButtonList.GuiSlideEntry(179, I18n.format("createWorld.customize.custom.minHeight"), false, this, 0.0F, 255.0F, (float) this.settings.dioriteMinHeight),
        new GuiPageButtonList.GuiSlideEntry(180, I18n.format("createWorld.customize.custom.maxHeight"), false, this, 0.0F, 255.0F, (float) this.settings.dioriteMaxHeight),
        new GuiPageButtonList.GuiLabelEntry(420, I18n.format("tile.stone.andesite.name"), false), null,
        new GuiPageButtonList.GuiSlideEntry(181, I18n.format("createWorld.customize.custom.size"), false, this, 1.0F, 50.0F, (float) this.settings.andesiteSize),
        new GuiPageButtonList.GuiSlideEntry(182, I18n.format("createWorld.customize.custom.count"), false, this, 0.0F, 40.0F, (float) this.settings.andesiteCount),
        new GuiPageButtonList.GuiSlideEntry(183, I18n.format("createWorld.customize.custom.minHeight"), false, this, 0.0F, 255.0F, (float) this.settings.andesiteMinHeight),
        new GuiPageButtonList.GuiSlideEntry(184, I18n.format("createWorld.customize.custom.maxHeight"), false, this, 0.0F, 255.0F, (float) this.settings.andesiteMaxHeight),
        new GuiPageButtonList.GuiLabelEntry(421, I18n.format("tile.oreCoal.name"), false), null,
        new GuiPageButtonList.GuiSlideEntry(185, I18n.format("createWorld.customize.custom.size"), false, this, 1.0F, 50.0F, (float) this.settings.coalSize),
        new GuiPageButtonList.GuiSlideEntry(186, I18n.format("createWorld.customize.custom.count"), false, this, 0.0F, 40.0F, (float) this.settings.coalCount),
        new GuiPageButtonList.GuiSlideEntry(187, I18n.format("createWorld.customize.custom.minHeight"), false, this, 0.0F, 255.0F, (float) this.settings.coalMinHeight),
        new GuiPageButtonList.GuiSlideEntry(189, I18n.format("createWorld.customize.custom.maxHeight"), false, this, 0.0F, 255.0F, (float) this.settings.coalMaxHeight),
        new GuiPageButtonList.GuiLabelEntry(422, I18n.format("tile.oreIron.name"), false), null,
        new GuiPageButtonList.GuiSlideEntry(190, I18n.format("createWorld.customize.custom.size"), false, this, 1.0F, 50.0F, (float) this.settings.ironSize),
        new GuiPageButtonList.GuiSlideEntry(191, I18n.format("createWorld.customize.custom.count"), false, this, 0.0F, 40.0F, (float) this.settings.ironCount),
        new GuiPageButtonList.GuiSlideEntry(192, I18n.format("createWorld.customize.custom.minHeight"), false, this, 0.0F, 255.0F, (float) this.settings.ironMinHeight),
        new GuiPageButtonList.GuiSlideEntry(193, I18n.format("createWorld.customize.custom.maxHeight"), false, this, 0.0F, 255.0F, (float) this.settings.ironMaxHeight),
        new GuiPageButtonList.GuiLabelEntry(423, I18n.format("tile.oreGold.name"), false), null,
        new GuiPageButtonList.GuiSlideEntry(194, I18n.format("createWorld.customize.custom.size"), false, this, 1.0F, 50.0F, (float) this.settings.goldSize),
        new GuiPageButtonList.GuiSlideEntry(195, I18n.format("createWorld.customize.custom.count"), false, this, 0.0F, 40.0F, (float) this.settings.goldCount),
        new GuiPageButtonList.GuiSlideEntry(196, I18n.format("createWorld.customize.custom.minHeight"), false, this, 0.0F, 255.0F, (float) this.settings.goldMinHeight),
        new GuiPageButtonList.GuiSlideEntry(197, I18n.format("createWorld.customize.custom.maxHeight"), false, this, 0.0F, 255.0F, (float) this.settings.goldMaxHeight),
        new GuiPageButtonList.GuiLabelEntry(424, I18n.format("tile.oreRedstone.name"), false), null,
        new GuiPageButtonList.GuiSlideEntry(198, I18n.format("createWorld.customize.custom.size"), false, this, 1.0F, 50.0F, (float) this.settings.redstoneSize),
        new GuiPageButtonList.GuiSlideEntry(199, I18n.format("createWorld.customize.custom.count"), false, this, 0.0F, 40.0F, (float) this.settings.redstoneCount),
        new GuiPageButtonList.GuiSlideEntry(200, I18n.format("createWorld.customize.custom.minHeight"), false, this, 0.0F, 255.0F, (float) this.settings.redstoneMinHeight),
        new GuiPageButtonList.GuiSlideEntry(201, I18n.format("createWorld.customize.custom.maxHeight"), false, this, 0.0F, 255.0F, (float) this.settings.redstoneMaxHeight),
        new GuiPageButtonList.GuiLabelEntry(425, I18n.format("tile.oreDiamond.name"), false), null,
        new GuiPageButtonList.GuiSlideEntry(202, I18n.format("createWorld.customize.custom.size"), false, this, 1.0F, 50.0F, (float) this.settings.diamondSize),
        new GuiPageButtonList.GuiSlideEntry(203, I18n.format("createWorld.customize.custom.count"), false, this, 0.0F, 40.0F, (float) this.settings.diamondCount),
        new GuiPageButtonList.GuiSlideEntry(204, I18n.format("createWorld.customize.custom.minHeight"), false, this, 0.0F, 255.0F, (float) this.settings.diamondMinHeight),
        new GuiPageButtonList.GuiSlideEntry(205, I18n.format("createWorld.customize.custom.maxHeight"), false, this, 0.0F, 255.0F, (float) this.settings.diamondMaxHeight),
        new GuiPageButtonList.GuiLabelEntry(426, I18n.format("tile.oreLapis.name"), false), null,
        new GuiPageButtonList.GuiSlideEntry(206, I18n.format("createWorld.customize.custom.size"), false, this, 1.0F, 50.0F, (float) this.settings.lapisSize),
        new GuiPageButtonList.GuiSlideEntry(207, I18n.format("createWorld.customize.custom.count"), false, this, 0.0F, 40.0F, (float) this.settings.lapisCount),
        new GuiPageButtonList.GuiSlideEntry(208, I18n.format("createWorld.customize.custom.center"), false, this, 0.0F, 255.0F, (float) this.settings.lapisCenterHeight),
        new GuiPageButtonList.GuiSlideEntry(209, I18n.format("createWorld.customize.custom.spread"), false, this, 0.0F, 255.0F, (float) this.settings.lapisSpread)};
                GuiPageButtonList.GuiListEntry[] aguipagebuttonlist$guilistentry2 = new GuiPageButtonList.GuiListEntry[]{new GuiPageButtonList.GuiSlideEntry(100, I18n.format("createWorld.customize.custom.mainNoiseScaleX"), false, this, 1.0F, 5000.0F, this.settings.mainNoiseScaleX),
        new GuiPageButtonList.GuiSlideEntry(101, I18n.format("createWorld.customize.custom.mainNoiseScaleY"), false, this, 1.0F, 5000.0F, this.settings.mainNoiseScaleY),
        new GuiPageButtonList.GuiSlideEntry(102, I18n.format("createWorld.customize.custom.mainNoiseScaleZ"), false, this, 1.0F, 5000.0F, this.settings.mainNoiseScaleZ),
        new GuiPageButtonList.GuiSlideEntry(103, I18n.format("createWorld.customize.custom.depthNoiseScaleX"), false, this, 1.0F, 2000.0F, this.settings.depthNoiseScaleX),
        new GuiPageButtonList.GuiSlideEntry(104, I18n.format("createWorld.customize.custom.depthNoiseScaleZ"), false, this, 1.0F, 2000.0F, this.settings.depthNoiseScaleZ),
        new GuiPageButtonList.GuiSlideEntry(105, I18n.format("createWorld.customize.custom.depthNoiseScaleExponent"), false, this, 0.01F, 20.0F, this.settings.depthNoiseScaleExponent),
        new GuiPageButtonList.GuiSlideEntry(106, I18n.format("createWorld.customize.custom.baseSize"), false, this, 1.0F, 25.0F, this.settings.baseSize),
        new GuiPageButtonList.GuiSlideEntry(107, I18n.format("createWorld.customize.custom.coordinateScale"), false, this, 1.0F, 6000.0F, this.settings.coordinateScale),
        new GuiPageButtonList.GuiSlideEntry(108, I18n.format("createWorld.customize.custom.heightScale"), false, this, 1.0F, 6000.0F, this.settings.heightScale),
        new GuiPageButtonList.GuiSlideEntry(109, I18n.format("createWorld.customize.custom.stretchY"), false, this, 0.01F, 50.0F, this.settings.stretchY),
        new GuiPageButtonList.GuiSlideEntry(110, I18n.format("createWorld.customize.custom.upperLimitScale"), false, this, 1.0F, 5000.0F, this.settings.upperLimitScale),
        new GuiPageButtonList.GuiSlideEntry(111, I18n.format("createWorld.customize.custom.lowerLimitScale"), false, this, 1.0F, 5000.0F, this.settings.lowerLimitScale),
        new GuiPageButtonList.GuiSlideEntry(112, I18n.format("createWorld.customize.custom.biomeDepthWeight"), false, this, 1.0F, 20.0F, this.settings.biomeDepthWeight),
        new GuiPageButtonList.GuiSlideEntry(113, I18n.format("createWorld.customize.custom.biomeDepthOffset"), false, this, 0.0F, 20.0F, this.settings.biomeDepthOffset),
        new GuiPageButtonList.GuiSlideEntry(114, I18n.format("createWorld.customize.custom.biomeScaleWeight"), false, this, 1.0F, 20.0F, this.settings.biomeScaleWeight),
        new GuiPageButtonList.GuiSlideEntry(115, I18n.format("createWorld.customize.custom.biomeScaleOffset"), false, this, 0.0F, 20.0F, this.settings.biomeScaleOffset)};
                GuiPageButtonList.GuiListEntry[] aguipagebuttonlist$guilistentry3 = new GuiPageButtonList.GuiListEntry[]{new GuiPageButtonList.GuiLabelEntry(400, I18n.format("createWorld.customize.custom.mainNoiseScaleX") + ":", false),
        new GuiPageButtonList.EditBoxEntry(132, String.format("%5.3f", this.settings.mainNoiseScaleX), false, this.numberFilter),
        new GuiPageButtonList.GuiLabelEntry(401, I18n.format("createWorld.customize.custom.mainNoiseScaleY") + ":", false),
        new GuiPageButtonList.EditBoxEntry(133, String.format("%5.3f", this.settings.mainNoiseScaleY), false, this.numberFilter),
        new GuiPageButtonList.GuiLabelEntry(402, I18n.format("createWorld.customize.custom.mainNoiseScaleZ") + ":", false),
        new GuiPageButtonList.EditBoxEntry(134, String.format("%5.3f", this.settings.mainNoiseScaleZ), false, this.numberFilter),
        new GuiPageButtonList.GuiLabelEntry(403, I18n.format("createWorld.customize.custom.depthNoiseScaleX") + ":", false),
        new GuiPageButtonList.EditBoxEntry(135, String.format("%5.3f", this.settings.depthNoiseScaleX), false, this.numberFilter),
        new GuiPageButtonList.GuiLabelEntry(404, I18n.format("createWorld.customize.custom.depthNoiseScaleZ") + ":", false),
        new GuiPageButtonList.EditBoxEntry(136, String.format("%5.3f", this.settings.depthNoiseScaleZ), false, this.numberFilter),
        new GuiPageButtonList.GuiLabelEntry(405, I18n.format("createWorld.customize.custom.depthNoiseScaleExponent") + ":", false),
        new GuiPageButtonList.EditBoxEntry(137, String.format("%2.3f", this.settings.depthNoiseScaleExponent), false, this.numberFilter),
        new GuiPageButtonList.GuiLabelEntry(406, I18n.format("createWorld.customize.custom.baseSize") + ":", false),
        new GuiPageButtonList.EditBoxEntry(138, String.format("%2.3f", this.settings.baseSize), false, this.numberFilter),
        new GuiPageButtonList.GuiLabelEntry(407, I18n.format("createWorld.customize.custom.coordinateScale") + ":", false),
        new GuiPageButtonList.EditBoxEntry(139, String.format("%5.3f", this.settings.coordinateScale), false, this.numberFilter),
        new GuiPageButtonList.GuiLabelEntry(408, I18n.format("createWorld.customize.custom.heightScale") + ":", false),
        new GuiPageButtonList.EditBoxEntry(140, String.format("%5.3f", this.settings.heightScale), false, this.numberFilter),
        new GuiPageButtonList.GuiLabelEntry(409, I18n.format("createWorld.customize.custom.stretchY") + ":", false),
        new GuiPageButtonList.EditBoxEntry(141, String.format("%2.3f", this.settings.stretchY), false, this.numberFilter),
        new GuiPageButtonList.GuiLabelEntry(410, I18n.format("createWorld.customize.custom.upperLimitScale") + ":", false),
        new GuiPageButtonList.EditBoxEntry(142, String.format("%5.3f", this.settings.upperLimitScale), false, this.numberFilter),
        new GuiPageButtonList.GuiLabelEntry(411, I18n.format("createWorld.customize.custom.lowerLimitScale") + ":", false),
        new GuiPageButtonList.EditBoxEntry(143, String.format("%5.3f", this.settings.lowerLimitScale), false, this.numberFilter),
        new GuiPageButtonList.GuiLabelEntry(412, I18n.format("createWorld.customize.custom.biomeDepthWeight") + ":", false),
        new GuiPageButtonList.EditBoxEntry(144, String.format("%2.3f", this.settings.biomeDepthWeight), false, this.numberFilter),
        new GuiPageButtonList.GuiLabelEntry(413, I18n.format("createWorld.customize.custom.biomeDepthOffset") + ":", false),
        new GuiPageButtonList.EditBoxEntry(145, String.format("%2.3f", this.settings.biomeDepthOffset), false, this.numberFilter),
        new GuiPageButtonList.GuiLabelEntry(414, I18n.format("createWorld.customize.custom.biomeScaleWeight") + ":", false),
        new GuiPageButtonList.EditBoxEntry(146, String.format("%2.3f", this.settings.biomeScaleWeight), false, this.numberFilter),
        new GuiPageButtonList.GuiLabelEntry(415, I18n.format("createWorld.customize.custom.biomeScaleOffset") + ":", false),
        new GuiPageButtonList.EditBoxEntry(147, String.format("%2.3f", this.settings.biomeScaleOffset), false, this.numberFilter)};

        this.list = new GuiPageButtonList(this.mc, this.width, this.height, 32, this.height - 32, 25, this, aguipagebuttonlist$guilistentry, aguipagebuttonlist$guilistentry1, aguipagebuttonlist$guilistentry2, aguipagebuttonlist$guilistentry3);

        for (int i = 0; i < 4; ++i) {
            this.pageNames[i] = I18n.format("createWorld.customize.custom.page" + i);
        }

        this.updatePageControls();
    }

    private void updatePageControls() {
        this.previousPage.enabled = this.list.getPage() != 0;
        this.nextPage.enabled = this.list.getPage() != this.list.getPageCount() - 1;
        this.subtitle = I18n.format("book.pageIndicator", this.list.getPage() + 1, this.list.getPageCount());
        this.pageTitle = this.pageNames[this.list.getPage()];
        this.randomize.enabled = this.list.getPage() != this.list.getPageCount() - 1;
    }
}