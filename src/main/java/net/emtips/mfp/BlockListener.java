package net.emtips.mfp;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;

public class BlockListener  implements Listener{
    @EventHandler
    private void onPlayerDropItem(PlayerDropItemEvent event)
    {
            Bukkit.broadcastMessage("§c[ArknCraft-OS]你乱丢垃圾，罚款100，赶紧分好类再扔！");
    }
    @EventHandler
    private void onBlockBreak(BlockBreakEvent event)
    {
            Bukkit.broadcastMessage("§c[ArknCraft-OS]你破坏了一个方块！");
    }
    @EventHandler
    private void onBlockDamage(BlockDamageEvent event)
    {
            Bukkit.broadcastMessage("§c[ArknCraft-OS]你开始破坏方块了！");
    }
}