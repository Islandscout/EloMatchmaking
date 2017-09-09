package me.Islandscout.elo;

import me.Islandscout.elo.handlers.ListenerHandler;
import me.Islandscout.elo.utils.EloCalculator;
import me.Islandscout.elo.commands.EloCommand;
import me.Islandscout.elo.managers.EloManager;
import me.Islandscout.elo.managers.ConfigManager;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private Main plugin;

	private EloManager eloManager;
	private EloCalculator eloCalculator;
	private ConfigManager configManager;

	@Override
	public void onEnable() {
	    plugin = this;
	    eloManager = new EloManager(plugin);
	    configManager = new ConfigManager(plugin);
        eloCalculator = new EloCalculator(plugin);
		plugin.getCommand("elo").setExecutor(new EloCommand(this));
		plugin.registerListeners();
		plugin.saveDefaultConfig();
		plugin.getConfig().options().copyDefaults(true);
		plugin.getLogger().info("Elo ranking system enabled");
	}

	@Override
	public void onDisable() {
	    plugin = null;
		getLogger().info("Elo ranking system disabled");
	}
	
	private void registerListeners(){
		PluginManager manager = getServer().getPluginManager();
		manager.registerEvents(new ListenerHandler(plugin), plugin);
	}

	public EloCalculator getEloCalculator() { return eloCalculator; }

	public ConfigManager getConfigManager() { return configManager; }

	public EloManager getEloManager() { return eloManager; }
}
