package me.flail.nou;

import java.util.HashMap;
import java.util.Map;

import me.flail.slashplayer.DataFile;
import me.flail.slashplayer.tools.Logger;

public class Settings extends Logger {

	private DataFile settings;

	public Settings(NoU plugin) {
		this.plugin = plugin;
		settings = new DataFile("Settings.yml");
	}

	public void load() {

		settings.setHeader("#-----------------------------------------------------------------\r\n" +
				"#==================================================================#\r\n" +
				"#                                                                  #\r\n" +
				"#                   NoU by FlailoftheLord.                         #\r\n" +
				"#         -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-                  #\r\n" +
				"#           If you have any Questions or feedback                  #\r\n" +
				"#              Join my discord server here:                        #\r\n" +
				"#               https://discord.gg/wuxW5PS                         #\r\n" +
				"#   ______               __        _____                           #\r\n" +
				"#   |       |           /  \\         |        |                    #\r\n" +
				"#   |__     |          /____\\        |        |                    #\r\n" +
				"#   |       |         /      \\       |        |                    #\r\n" +
				"#   |       |_____   /        \\    __|__      |______              #\r\n" +
				"#                                                                  #\r\n" +
				"#==================================================================#\r\n" +
				"#-----------------------------------------------------------------\r\n\n");

		Map<String, Object> values = new HashMap<>();
		values.put("Color", "&c&l");
		values.put("Permission", "nou.chat");
		values.put("CommandPermission", "nou.command");
		values.put("ExemptPermission", "nou.exempt");
		values.put("LogChat", Boolean.valueOf(true));
		values.put("Phrases", plugin.defaultWords());

		for (String key : values.keySet()) {
			if (!settings.hasValue(key)) {
				settings.setValue(key, values.get(key));
			}
		}


		plugin.settings = settings;
	}

	public String get(String key) {
		return settings.hasValue(key) ? settings.getValue(key) : "Invalid value: " + key;
	}

}
