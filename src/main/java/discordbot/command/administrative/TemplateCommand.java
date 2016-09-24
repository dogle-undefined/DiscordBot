package discordbot.command.administrative;

import com.vdurmont.emoji.EmojiParser;
import discordbot.core.AbstractCommand;
import discordbot.main.Config;
import discordbot.main.DiscordBot;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;

/**
 * !template
 * manages the templates
 */
public class TemplateCommand extends AbstractCommand {
	public TemplateCommand(DiscordBot b) {
		super(b);
	}

	@Override
	public String getDescription() {
		return "adds/removes templates";
	}

	@Override
	public String getCommand() {
		return "template";
	}

	@Override
	public String[] getUsage() {
		return new String[]{
				"template list                        //lists all keyphrases",
				"template list <keyphrase>            //lists all options for keyphrase",
				"template remove <keyphrase> <index>  //removes selected template for keyphrase",
				"template add <keyphrase> <text...>   //adds a template for keyphrase",
				"template toggledebug                 //shows keyphrases instead of text"};
	}

	@Override
	public String[] getAliases() {
		return new String[]{};
	}

	@Override
	public String execute(String[] args, IChannel channel, IUser author) {
		if (bot.isOwner(channel, author)) {
			if (args.length == 0) {
				String usage = ":gear: **Options**:```php" + Config.EOL;
				for (String line : getUsage()) {
					usage += line + Config.EOL;
				}
				return usage + "```";
			}
			if (args.length >= 1) {
				switch (args[0]) {
					case "toggledebug":
						if (bot.isCreator(author)) {
							Config.SHOW_KEYPHRASE = !Config.SHOW_KEYPHRASE;
							if (Config.SHOW_KEYPHRASE) {
								return "Keyphrases shown ";
							} else {
								return "Keyphrases are being translated";
							}
						}
						return discordbot.handler.Template.get("no_permission");
					case "add":
						if (args.length >= 3) {
							String text = args[2];
							for (int i = 3; i < args.length; i++) {
								text += " " + args[i];
							}
							discordbot.handler.Template.getInstance().add(args[1], EmojiParser.parseToAliases(text));
							return discordbot.handler.Template.get("command_template_added");
						}
						return discordbot.handler.Template.get("command_template_added_failed");
					case "remove":
					case "list":
						return discordbot.handler.Template.get("not_yet_implemented");
					default:
						return discordbot.handler.Template.get("command_template_invalid_option");
				}
			}
			return discordbot.handler.Template.get("no_permission");
		}
		return discordbot.handler.Template.get("no_permission");
	}
}