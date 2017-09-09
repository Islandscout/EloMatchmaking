package me.Islandscout.elo.handlers;

import me.Islandscout.elo.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class ListenerHandler implements Listener {

    private Main plugin;

    public ListenerHandler(Main main) {
        plugin = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        plugin.getEloManager().onJoin(e);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        plugin.getEloManager().onDeath(e);
    }

}
