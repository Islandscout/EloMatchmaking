package me.Islandscout.elo;

import me.Islandscout.elo.utils.EloCalculator;
import me.Islandscout.elo.commands.EloCommand;
import me.Islandscout.elo.managers.EloManager;
import me.Islandscout.elo.managers.ConfigManager;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	
	
	private EloManager EloManager = new EloManager(this);
	public EloManager getEloManager() { return EloManager; }
	
	private EloCalculator EloCalculator = new EloCalculator(this);
	public EloCalculator getEloCalculator() { return EloCalculator; }
	
	private ConfigManager ConfigManager = new ConfigManager(this);
	public ConfigManager getConfigManager() { return ConfigManager; }
	
	@Override
	public void onEnable() {
		getCommand("elo").setExecutor(new EloCommand(this));
		registerListeners();
		saveDefaultConfig();
		getConfig().options().copyDefaults(true);
		getConfigManager().loadValues();
		getLogger().info("Elo ranking system enabled");
	}

	@Override
	public void onDisable() {
		getLogger().info("Elo ranking system disabled");
	}
	
	/*
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equals("elo") && args.length == 1) {
			Boolean win_1 = args[0].equals("1") ? true : false;
			
			//start calculation
			double step1_1;
			double step1_2;
			step1_1 = Math.pow(10, (player_1 / 400));
			step1_2 = Math.pow(10, (player_2 / 400));
			
			double step2_1;
			double step2_2;
			step2_1 = (step1_1 / (step1_1 + step1_2));
			step2_2 = (step1_2 / (step1_1 + step1_2));
			
			double step3_1 = win_1 ? 1 : 0;
			double step3_2 = win_1 ? 0 : 1;
			
			player_1 = (player_1 + (32 * (step3_1 - step2_1)));
			player_2 = (player_2 + (32 * (step3_2 - step2_2)));
			//end calculation
			
			getLogger().info("---------------------");
			getLogger().info("Player1: " + player_1);
			getLogger().info("Player2: " + player_2);
			return true;
			
		}
		getLogger().info("Argument must either be \"0\" or \"1\"");
		return true;
		
	}
	*/
	
	private void registerListeners(){
		PluginManager manager = getServer().getPluginManager();
		manager.registerEvents(new EloManager(this), this);
	}
}
