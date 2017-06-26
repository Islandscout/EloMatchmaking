package me.Islandscout.elo.utils;

import java.util.HashMap;
import java.util.UUID;

import me.Islandscout.elo.Main;

import org.bukkit.entity.Player;

public class EloCalculator {
	
	private Main plugin;
	public EloCalculator(Main main) {
		this.plugin = main;
	}
	
	public HashMap<UUID, Double> elo = new HashMap<>();
	
	public double calculateEloWinner(Player winner, Player looser) {
		double step1_1;
		double step1_2;
		step1_1 = Math.pow(10, (elo.get(winner.getUniqueId()) / 400));
		step1_2 = Math.pow(10, (elo.get(looser.getUniqueId()) / 400));
		
		double step2;
		step2 = (step1_1 / (step1_1 + step1_2));
		
		return (elo.get(winner.getUniqueId()) + (plugin.getConfigManager().getKFactor() * (1 - step2)));
	}
	
	public double calculateEloLooser(Player winner, Player looser) {
		double step1_1;
		double step1_2;
		step1_1 = Math.pow(10, (elo.get(winner.getUniqueId()) / 400));
		step1_2 = Math.pow(10, (elo.get(looser.getUniqueId()) / 400));
		
		double step2;
		step2 = (step1_2 / (step1_1 + step1_2));
		
		return (elo.get(looser.getUniqueId()) + (plugin.getConfigManager().getKFactor() * (0 - step2)));
	}
	
	public void setElo(Player player, double setElo) {
		elo.put(player.getUniqueId(), setElo);
	}
	
	public double getElo(Player player) {
		if(!elo.containsKey(player.getUniqueId())) {
			return -1;
		}
		return elo.get(player.getUniqueId());
	}

}
