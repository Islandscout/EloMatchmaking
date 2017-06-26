package me.Islandscout.elo.managers;

import me.Islandscout.elo.Main;

public class ConfigManager {
	
	private Main plugin;
	public ConfigManager(Main main) {
		this.plugin = main;
	}

	Double defaultElo;
	Double kFactor;
	
	public double getDefaultElo() {
		return defaultElo;
	}
	
	public double getKFactor() {
		return kFactor;
	}
	
	public void loadValues() {
		defaultElo = plugin.getConfig().getDouble("defaultElo");
		kFactor = plugin.getConfig().getDouble("kFactor");
	}
}
