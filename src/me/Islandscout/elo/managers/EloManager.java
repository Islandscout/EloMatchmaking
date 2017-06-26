package me.Islandscout.elo.managers;

import me.Islandscout.elo.Main;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class EloManager implements Listener {
	
	private Main plugin;
	public EloManager(Main main) {
		this.plugin = main;
	}


	@EventHandler(priority = EventPriority.LOWEST)
	public void onDeath(PlayerDeathEvent e) {
		if(e.getEntity().getKiller() != null && e.getEntity().getKiller() instanceof Player && e.getEntity() instanceof Player) {
			Player winner = e.getEntity().getKiller();
			Player looser = e.getEntity();
			Double winnerElo = plugin.getEloCalculator().calculateEloWinner(winner, looser);
			Double looserElo = plugin.getEloCalculator().calculateEloLooser(winner, looser);
			plugin.getEloCalculator().setElo(winner, winnerElo);
			plugin.getEloCalculator().setElo(looser, looserElo);
			winner.sendMessage(ChatColor.GOLD + "New Elo: " + Math.round(plugin.getEloCalculator().getElo(winner)));
			looser.sendMessage(ChatColor.GOLD + "New Elo: " + Math.round(plugin.getEloCalculator().getElo(looser)));
		}
	}
	@EventHandler(priority = EventPriority.LOWEST)
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(plugin.getEloCalculator().getElo(p) == -1) {
			plugin.getEloCalculator().setElo(p, plugin.getConfigManager().getDefaultElo());
		}
	}
		
}
