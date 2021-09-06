package at.sudo200.ugandaknucklesbot.Commands.Classes.Chat;

import at.sudo200.ugandaknucklesbot.Commands.Core.BotCommand;
import at.sudo200.ugandaknucklesbot.Commands.Core.CommandCategories;
import at.sudo200.ugandaknucklesbot.Commands.Core.CommandParameter;
import at.sudo200.ugandaknucklesbot.Util.UtilsChat;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.exceptions.HierarchyException;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChatCommandKick extends BotCommand {
    private final UtilsChat utilsChat = new UtilsChat();

    @Override
    protected String @NotNull [] getCategories() {
        return new String[]{
                CommandCategories.UTIL,
                CommandCategories.CHAT
        };
    }

    @Override
    protected @NotNull String getName() {
        return "kick";
    }

    @Override
    protected @NotNull String getHelp() {
        return "Yes, it kicks a member of your choice\n\n(not in the balls)";
    }

    @Override
    protected String @Nullable [] getAliases() {
        return null;
    }

    @Override
    protected void execute(@NotNull CommandParameter param) {
        if(param.args.length == 0 || !utilsChat.isMention(param.args[0])) {
            utilsChat.sendInfo(param.message.getChannel(), "You have to say who you want to kick.");
            return;
        }
        try {
            AuditableRestAction<Void> kick = utilsChat.getMemberByMention(param.args[0], param.message.getGuild()).kick();
            if (param.args.length > 1) {
                param.args[0] = "";
                kick = kick.reason(String.join(" ", param.args));
            }
            kick.queue();
        }
        catch (HierarchyException e) {
            EmbedBuilder builder = utilsChat.getDefaultEmbed();
            builder.setDescription("**That dude is more pog than me, sry** ");
            builder.setImage("https://i1.sndcdn.com/avatars-ypCd5dE5YbGkyF0p-Y59d9w-t500x500.jpg");
            utilsChat.send(param.message.getChannel(), builder.build());
        }


    }
}