package de.niklashere.hidenseek.listener;

import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    public void onBreak(BlockBreakEvent e) {
        e.setCancelled(true);
    }
}
