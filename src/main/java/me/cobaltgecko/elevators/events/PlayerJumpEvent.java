package me.cobaltgecko.elevators.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerJumpEvent implements Listener {

    @EventHandler
    public void jumpEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        // Check if the player is jumping. If not, return
        if(!(player.getVelocity().getY() >= 0)) {
            return;
        }

        Block blockBelowPlayer = getBlockBelowPlayer(player);

        if (isValidElevator(blockBelowPlayer)) {

        }

    }

    public Block getBlockBelowPlayer(Player player) {
        Location playerLocation = player.getLocation();
        int yPositionBelowPlayer = playerLocation.getBlockY() - 1;
        Location locationBelowPlayer = new Location(
                player.getWorld(),
                playerLocation.getBlockX(),
                yPositionBelowPlayer,
                playerLocation.getBlockZ());
        return locationBelowPlayer.getBlock();
    }

    public boolean isValidElevator(Block block) {
        if (block.getType() == Material.IRON_BLOCK) {
            return true;
        } else {
            return false;
        }
    }

    public Block getBlockAbovePlayer(Player player) {
        for (int i = player.getLocation().getBlockY(); i < player.getWorld().getMaxHeight(); i++) {

        }
    }
}
