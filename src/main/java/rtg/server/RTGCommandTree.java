package rtg.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.Chunk;

import net.minecraftforge.server.command.CommandTreeBase;

import rtg.RTG;
import rtg.api.util.Logger;

public final class RTGCommandTree extends CommandTreeBase
{
    private static final int    ACCESS_ALL           = 0; // everyone
    private static final int    ACCESS_ADMIN         = 4; // only admins
    private static final String LANGKEY_BASE         = RTG.MOD_ID+".command";
    private static final String LANGKEY_PREFIX_ERROR = LANGKEY_BASE +".prefix.error";
    private static final Style  STYLE_ERROR          = new Style().setColor(TextFormatting.RED);
    private static final Style  STYLE_GOLD           = new Style().setColor(TextFormatting.GOLD);
    private static final String CMD_ROOT             = "rtg";

    public RTGCommandTree() {
        Logger.debug("Created /{} command", CMD_ROOT);
        this.addSubcommand(new CommandGetWhereAmI(this.getName()));
    }
    @Override public int getRequiredPermissionLevel() { return ACCESS_ALL; }
    @Override public String getName() { return CMD_ROOT; }
    @Override public String getUsage(ICommandSender sender) {
        final ITextComponent ret = new TextComponentString("");
        getSubCommands().forEach(cmd -> {
            if (cmd instanceof CommandTreeBase) {
                ((CommandTreeBase)cmd).getSubCommands().forEach(rcmd -> ret.appendText("\n").appendText(rcmd.getUsage(sender)));
            } else {
                ret.appendText("\n").appendText(cmd.getUsage(sender));
            }
        });
        return ret.getFormattedText();
    }

    static final class CommandGetWhereAmI extends CommandBase
    {
        private static final String NAME = "whereami";
        private static final String LANGKEY_ERROR = LANGKEY_BASE + "." + NAME + ".error";
        private final String parentName;
        CommandGetWhereAmI(String parentName) { this.parentName = parentName; }
        @Override public int getRequiredPermissionLevel() { return ACCESS_ADMIN; }
        @Override public String getName() { return NAME; }
        @Override public String getUsage(ICommandSender sender) {
            return new TextComponentString("/").appendSibling(new TextComponentString(this.parentName+" "+this.getName())).getFormattedText();
        }
        @Override public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
            final EntityPlayerMP player;
            if (sender instanceof EntityPlayerMP) {
                player = (EntityPlayerMP)sender;
            } else {
                sender.sendMessage(new TextComponentString("  ")
                    .appendSibling(new TextComponentTranslation(LANGKEY_PREFIX_ERROR).setStyle(STYLE_ERROR))
                    .appendText(": ")
                    .appendSibling(new TextComponentTranslation(LANGKEY_ERROR).setStyle(STYLE_GOLD))
                );
                return;
            }

            final BlockPos      pos      = player.getPosition();
            final World         world    = player.getEntityWorld();
            final Chunk         chunk    = world.getChunk(pos);
            final BiomeProvider provider = world.getBiomeProvider();

            final Biome chunkBiome    = chunk.getBiome(pos, provider);
            final Biome providerBiome = provider.getBiome(pos);

            player.sendMessage(new TextComponentString(String.format("Biome @ %s:%s, Chunk: %s, BiomeProvider: %s",
                pos.getX(), pos.getZ(), chunkBiome.getRegistryName(), providerBiome.getRegistryName())));
        }
    }
}