package net.emtips.mfp;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class CommandSet implements CommandExecutor {
    private final main plugin;

    public CommandSet(main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("ac")) {
            if (!(sender instanceof Player)) {
                Bukkit.broadcastMessage("§c[ArknCraft-OS]必须玩家输入命令");
            } else {
                Player player = (Player) sender;
                Bukkit.broadcastMessage("§c[ArknCraft-OS]闪电打击成功！");
                player.getWorld().strikeLightning(player.getTargetBlock(null, 200).getLocation());
                player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 5, 20));
                return true;
            }
        }
        if (cmd.getName().equalsIgnoreCase("ac2")) {
                if (!(sender instanceof Player)) {
                    Bukkit.broadcastMessage("§c[ArknCraft-OS]必须玩家输入命令");
                } else {
                    Player p = (Player) sender;
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 20));
                    Bukkit.broadcastMessage("§c[ArknCraft-OS]给予药水效果成功！");
                }
                return true;
            }
        if (cmd.getName().equalsIgnoreCase("ac3")) {
                if (!(sender instanceof Player)) {
                    Bukkit.broadcastMessage("§c[ArknCraft-OS]必须玩家输入命令");
                } else {
                    //设置简写
                    Player p = (Player)sender;
                    Inventory gui = Bukkit.createInventory(null, 54, "GUI测试");
                    //设置GUI物品
                    ItemStack ref1 = new ItemStack(Material.BOOK);
                    ItemMeta metaref1 = ref1.getItemMeta();
                    //设置物品属性
                    ArrayList<String> lore= new ArrayList<String>();
                    lore.add("这是第一行");
                    lore.add("这是第二行");
                    lore.add("这是第三行");
                    lore.add("这是第四行");
                    lore.add("这是第五行");
                    lore.add("这是第六行");
                    lore.add("这是第七行");
                    lore.add("这是第N行");
                    lore.add("再多也没用");
                    metaref1.setLore(lore);
                    //设置物品显示名称
                    metaref1.setDisplayName("§6§l试试就逝世");
                    ref1.setItemMeta(metaref1);
                    //设置物品在哪
                    gui.setItem(1, ref1);
                    //打开GUI
                    p.closeInventory(); //防止刷物品
                    p.openInventory(gui);
                }
                return true;
            }
            return false;
        }
    }