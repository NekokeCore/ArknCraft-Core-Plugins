package net.emtips.mfp;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.bukkit.Bukkit.getLogger;

public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Bukkit.broadcastMessage("§c[ArknCraft-OS]欢迎 " + event.getPlayer().getName() + " 来到ArknCraft服务器");
        Bukkit.broadcastMessage("§c[ArknCraft-OS]注意本服只是 明日方舟的周边服 并不是官方！");
        Bukkit.broadcastMessage("§c[ArknCraft-OS]在服务器中玩耍时请注意不要将重要的账号/密码告诉别人！");
        //将玩家的名字,登录时间戳记录入数据库
        String sql = "INSERT INTO pluginstest(Player,Time) VALUES ('" + event.getPlayer().getName() + "'" + "," + "'" + System.currentTimeMillis() + "');";
        try {
            PreparedStatement stmt = main.connection.prepareStatement(sql);
            stmt.executeUpdate();
            getLogger().info("§c[ArknCraft-OS]已将" + event.getPlayer().getName() + "登录事件记录入数据库");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player p = (Player)event.getWhoClicked();
        if(event.getWhoClicked() instanceof Player == false) { return;}
        if (event.getView().getTitle().equalsIgnoreCase("GUI测试") )
        {
            event.setCancelled(true);
            p.updateInventory();
            if(event.getRawSlot() == 1 )
            {
                Bukkit.broadcastMessage("§c[ArknCraft-OS]OwO");
            }
        }
    }
}