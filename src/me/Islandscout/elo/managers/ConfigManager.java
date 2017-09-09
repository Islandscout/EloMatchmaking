package me.Islandscout.elo.managers;

import me.Islandscout.elo.Main;

public class ConfigManager {

    private double defaultElo;
    private double kFactor;

	public ConfigManager(Main main) {
        defaultElo = main.getConfig().getDouble("defaultElo");
        kFactor = main.getConfig().getDouble("kFactor");
	}

	public double getDefaultElo() {
		return defaultElo;
	}

	public double getKFactor() {
		return kFactor;
	}
}
