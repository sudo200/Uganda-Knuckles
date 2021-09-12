package at.sudo200.ugandaknucklesbot.Commands.Classes.Chat;

import at.sudo200.ugandaknucklesbot.Commands.Core.BotCommand;
import at.sudo200.ugandaknucklesbot.Commands.Core.CommandCategories;
import at.sudo200.ugandaknucklesbot.Commands.Core.CommandParameter;
import at.sudo200.ugandaknucklesbot.Util.UtilsChat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChatCommandVersion extends BotCommand {
    private final UtilsChat utilsChat = new UtilsChat();

    private final String
            botVersion = getClass().getPackage().getImplementationVersion() == null ? "Development Version" : "v" + getClass().getPackage().getImplementationVersion(),
            name = System.getProperty("java.runtime.name"),
            version = System.getProperty("java.runtime.version"),
            osName = System.getProperty("os.name"),
            osArch = System.getProperty("os.arch"),
            osVersion = System.getProperty("os.version");


    @Override
    protected String @Nullable [] getAliases() {
        return null;
    }

    @Override
    protected String @NotNull [] getCategories() {
        return new String[] {
                CommandCategories.MISC,
                CommandCategories.CHAT
        };
    }

    @Override
    protected @NotNull String getName() {
        return "version";
    }

    @Override
    protected @NotNull String getHelp() {
        return "Prints out information about the currently running backend, for those who are interested\n\nprobably nobody";
    }

    @Override
    protected void execute(@NotNull CommandParameter param) {
        utilsChat.sendInfo(
                param.message.getChannel(),
                String.format(
                            "**Uganda Knuckles** %s\n" +
                                "\n" +
                                "**JVM:**\n" +
                                "%s %s\n" +
                                "\n" +
                                "**OS:**\n" +
                                "%s %s (%s)",
                        botVersion,
                        name,
                        version,
                        osName,
                        osVersion,
                        osArch
                )
                );
    }
}