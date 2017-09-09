package me.Islandscout.elo.managers;

import me.Islandscout.elo.Main;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;

public class EloManager {
	
	private Main plugin;
	public EloManager(Main main) {
		this.plugin = main;
	}

	public HashMap<UUID, Double> elo = new HashMap<>();

	public void onDeath(PlayerDeathEvent e) {
		if(e.getEntity().getKiller() != null && e.getEntity().getKiller() instanceof Player && e.getEntity() instanceof Player) {
			Player winner = e.getEntity().getKiller();
			Player looser = e.getEntity();
			double winnerElo = plugin.getEloCalculator().calculateEloWinner(winner, looser);
			double looserElo = plugin.getEloCalculator().calculateEloLooser(winner, looser);
			elo.put(winner.getUniqueId(), winnerElo);
			elo.put(looser.getUniqueId(), looserElo);
			winner.sendMessage(ChatColor.GOLD + "New Elo: " + Math.round(elo.get(winner.getUniqueId())));
			looser.sendMessage(ChatColor.GOLD + "New Elo: " + Math.round(elo.get(looser.getUniqueId())));
		}
	}

	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(!elo.containsKey(p.getUniqueId())) {
			elo.put(p.getUniqueId(), plugin.getConfigManager().getDefaultElo());
		}
	}

	public double getElo(Player p) {
	    return elo.get(p.getUniqueId());
    }

    public void setElo(Player p, double value) {
	    elo.put(p.getUniqueId(), value);
    }
		
}
