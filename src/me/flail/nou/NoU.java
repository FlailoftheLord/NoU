package me.flail.nou;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.flail.slashplayer.DataFile;
import me.flail.slashplayer.tools.Logger;

public class NoU extends JavaPlugin implements Listener {

	public Server server;
	public DataFile settings;
	private Logger tools;
	public List<String> adj = new ArrayList<>(8);

	@Override
	public void onLoad() {
		tools = new Logger();

		server = this.getServer();

		new Settings(this).load();
	}

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		getCommand("nou").setExecutor(this);

		defaultWords();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("nou")) {
			if (sender.hasPermission(settings.getValue("CommandPermission"))) {
				new Settings(this).load();

				sender.sendMessage(tools
						.chat(settings.getValue("Color") + "No U &7v" + getDescription().getVersion() + " &2by FlailoftheLord."));
				sender.sendMessage(tools.chat("&aUpdated Settings!"));
			}
		}
		return true;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void playerSayNoU(AsyncPlayerChatEvent event) {
		String message = event.getMessage();
		Player player = event.getPlayer();
		adj = settings.getList("Phrases");

		for (String phrase : adj) {
			if (tools.msgCheck(message, phrase, "contains")) {
				String nou = tools.chat(settings.getValue("Color") + "no u");

				if (player.hasPermission(settings.getValue("Permission"))
						&& !player.hasPermission(settings.getValue("ExemptPermission"))) {

					server.getScheduler().scheduleSyncDelayedTask(this, () -> {
						player.sendMessage(nou);

						if (settings.getBoolean("LogChat")) {
							String log = "Player: " + player.getName() + ". Said \"" + message + "\". And was told no u";

							tools.log(log);
						}

					}, 10L);

				}

				return;
			}
		}

	}

	public List<String> defaultWords() {
		adj.clear();

		adj.add("you're");
		adj.add("youre");
		adj.add("you");
		adj.add("ur ");
		adj.add("you are");
		adj.add("u r");
		adj.add("u are");
		adj.add("why are u");
		adj.add("y r u");
		adj.add("why r u");
		adj.add("why are you");

		return adj;
	}

}
