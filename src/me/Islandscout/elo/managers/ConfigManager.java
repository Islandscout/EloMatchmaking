package me.Islandscout.elo.managers;

import me.Islandscout.elo.Main;

public class ConfigManager {
	
	private Main plugin;

    private double defaultElo;
    private double kFactor;

	public ConfigManager(Main main) {
		this.plugin = main;
        defaultElo = plugin.getConfig().getDouble("defaultElo");
        kFactor = plugin.getConfig().getDouble("kFactor");
	}

	public double getDefaultElo() {
		return defaultElo;
	}

	public double getKFactor() {
		return kFactor;
	}
}
