package net.kevinmendoza.igneouspack.main;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.igneouspack.geology.intrusive.IntrusiveFactoryHub;

public class IgneousPackCommand implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args)
			throws CommandException {
		if(src instanceof Player) {
			Player player = (Player) src;
			long seed = player.getWorld().getWorldStorage().getWorldProperties().getSeed();
			Vector3i vec=  player.getLocation().getBlockPosition();
			String message= IgneousPackMain.PluginMain.getGeology(seed).getLocationData(vec);
			IgneousPackMain.PluginMain.GetLog().info("player " + player.getName() + " tried to find out about geology at" + player.getLocation().toString());
			IgneousPackMain.PluginMain.GetLog().info(message);
			player.sendMessage(Text.of(message));
		}
		
		return CommandResult.success();
	}


}
