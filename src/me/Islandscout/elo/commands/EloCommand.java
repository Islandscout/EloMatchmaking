package me.Islandscout.elo.commands;

import me.Islandscout.elo.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EloCommand implements CommandExecutor {
	
	private Main plugin;
	public EloCommand(Main main) {
		this.plugin = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!sender.hasPermission("elo.admin")) {
			sender.sendMessage(ChatColor.RED + "You do not have permission to run this command.");
			return true;
		}
		if(args.length != 2 && args.length != 3) {
			sender.sendMessage(ChatColor.RED + "Usage: /elo <set|get|reset> <player> [elo score]");
			return true;
		}
		if(args[0].equals("set")) {
			if(args.length != 3) {
				sender.sendMessage(ChatColor.RED + "Usage: /elo <set> <player> <elo score>");
				return true;
			}
			Player target = Bukkit.getPlayer(args[1]);
			if(target == null) {
				sender.sendMessage(ChatColor.RED + "Player not found.");
				return true;
			}
			double setElo;
			try {
				setElo = Double.parseDouble(args[2]);
			} catch (NumberFormatException e) {
				sender.sendMessage(ChatColor.RED + "Third argument must be a number!");
				return true;
			}
			plugin.getEloCalculator().setElo(target, setElo);
			sender.sendMessage(ChatColor.YELLOW + "" + args[1] + "'s Elo has been set to " + setElo);
			return true;
		}
		if(args[0].equals("get")) {
			Player target = Bukkit.getPlayer(args[1]);
			if(target == null) {
				sender.sendMessage(ChatColor.RED + "Player not found.");
				return true;
			}
			double elo = plugin.getEloCalculator().getElo(target);
			sender.sendMessage(ChatColor.YELLOW + "" + args[1] + "'s Elo is " + elo);
			return true;
		}
		if(args[0].equals("reset")) {
			Player target = Bukkit.getPlayer(args[1]);
			if(target == null) {
				sender.sendMessage(ChatColor.RED + "Player not found.");
				return true;
			}
			plugin.getEloCalculator().setElo(target, plugin.getConfigManager().getDefaultElo());
			sender.sendMessage(ChatColor.YELLOW + "" + args[1] + "'s Elo has been reset to " + plugin.getConfigManager().getDefaultElo());
			return true;
		}
		sender.sendMessage(ChatColor.RED + "Usage: /elo <set|get|reset> <player> [elo score]");
		return true;
	}
}
