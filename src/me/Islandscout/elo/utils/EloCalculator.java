package me.Islandscout.elo.utils;

import me.Islandscout.elo.Main;

import org.bukkit.entity.Player;

public class EloCalculator {
	
	private Main plugin;
	public EloCalculator(Main main) {
		this.plugin = main;
	}
	
	public double calculateEloWinner(Player winner, Player looser) {
		double step1_1;
		double step1_2;
		step1_1 = Math.pow(10, (plugin.getEloManager().getElo(winner) / 400.0));
		step1_2 = Math.pow(10, (plugin.getEloManager().getElo(looser) / 400.0));
		
		double step2;
		step2 = (step1_1 / (step1_1 + step1_2));
		
		return (plugin.getEloManager().getElo(winner) + (plugin.getConfigManager().getKFactor() * (1 - step2)));
	}
	
	public double calculateEloLooser(Player winner, Player looser) {
		double step1_1;
		double step1_2;
		step1_1 = Math.pow(10, (plugin.getEloManager().getElo(winner) / 400.0));
		step1_2 = Math.pow(10, (plugin.getEloManager().getElo(looser) / 400.0));
		
		double step2;
		step2 = (step1_2 / (step1_1 + step1_2));
		
		return (plugin.getEloManager().getElo(looser) + (plugin.getConfigManager().getKFactor() * (0 - step2)));
	}

}
