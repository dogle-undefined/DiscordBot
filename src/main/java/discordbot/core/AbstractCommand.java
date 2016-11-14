package discordbot.core;

import discordbot.command.CommandCategory;
import discordbot.command.CommandVisibility;
import discordbot.main.DiscordBot;
import net.dv8tion.jda.entities.MessageChannel;
import net.dv8tion.jda.entities.User;

public abstract class AbstractCommand {

	private CommandCategory commandCategory = CommandCategory.UNKNOWN;

	public AbstractCommand() {

	}

	/**
	 * A short discription of the method
	 *
	 * @return description
	 */
	public abstract String getDescription();

	/**
	 * What should be typed to trigger this command (Without prefix)
	 *
	 * @return command
	 */
	public abstract String getCommand();

	/**
	 * How to use the command?
	 *
	 * @return command usage
	 */
	public abstract String[] getUsage();

	/**
	 * aliases to call the command
	 *
	 * @return array of aliases
	 */
	public abstract String[] getAliases();

	public CommandCategory getCommandCategory() {
		return commandCategory;
	}

	/**
	 * The command will be set to the category matching the last part of the package name.
	 *
	 * @param newCategory category of the command
	 */
	public void setCommandCategory(CommandCategory newCategory) {
		commandCategory = newCategory;
	}

	/**
	 * where can the command be used?
	 *
	 * @return private, public, both
	 */
	public CommandVisibility getVisibility() {
		return CommandVisibility.BOTH;
	}

	/**
	 * is a command enabled? it is by default
	 *
	 * @return command is enabled?
	 */
	public boolean isEnabled() {
		return true;
	}

	/**
	 * Is a command listed? it is by default
	 *
	 * @return shows up in the !help list?
	 */
	public boolean isListed() {
		return true;
	}

	public abstract String execute(DiscordBot bot, String[] args, MessageChannel channel, User author);
}