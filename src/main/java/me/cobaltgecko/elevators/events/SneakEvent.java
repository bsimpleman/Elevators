package me.cobaltgecko.elevators.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class SneakEvent implements Listener {

    @EventHandler
    public void playerSneakEvent(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();

        if (!player.isSneaking()) {
            return;
        }

        Block blockBelowPlayer = getBlockBelowPlayer(player);

        if (isValidElevator(blockBelowPlayer)) {
            Block blockAbovePlayer = getElevatorBelowPlayer(player);
            if (blockAbovePlayer != null) {
                teleportPlayerOntoBlock(player, blockAbovePlayer);
            }
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

    /**
     * This function looks to see if there is a block above the player that matches the desired type
     *
     * @param player Player that the function is searching for a block above
     * @return Block of matching material that is located above the player
     */
    public Block getElevatorBelowPlayer(Player player) {
        for (int yValue = player.getLocation().getBlockY() - 2; yValue > player.getWorld().getMinHeight(); yValue--) {
            Location currentLocation = new Location(
                    player.getWorld(),
                    player.getLocation().getBlockX(),
                    yValue,
                    player.getLocation().getBlockZ());

            Block blockAtCurrentLocation = currentLocation.getBlock();
            if (blockAtCurrentLocation.getType() == Material.IRON_BLOCK) {
                return blockAtCurrentLocation;
            }
        }
        return null;
    }

    public void teleportPlayerOntoBlock(Player player, Block block) {
        Location teleportLocation = new Location (
                player.getWorld(),
                player.getLocation().getX(),
                block.getY() + 1,
                player.getLocation().getZ(),
                player.getLocation().getYaw(),
                player.getLocation().getPitch());
        player.teleport(teleportLocation);
    }

    /**
     * Returns whether a block could be an elevator
     *
     * @param block Block to check if it is an elevator block
     * @return True if the block is of the correct type
     */
    public boolean isValidElevator(Block block) {
        return block.getType() == Material.IRON_BLOCK;
    }
}
