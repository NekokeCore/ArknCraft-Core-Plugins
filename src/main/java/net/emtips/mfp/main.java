package net.emtips.mfp;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class main extends JavaPlugin {
    static Connection connection;
    @Override
    public void onEnable(){
        //生成配置文件
        FileConfiguration config = getConfig();
        this.saveDefaultConfig();
        config.options().copyDefaults(true);
        saveConfig();
        //注册事件监听器
        if (config.getBoolean("PlayerListener.Enable")) {
            getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        } else {
            //
        }

        if (config.getBoolean("BlockListener.Enable")) {
            getServer().getPluginManager().registerEvents(new BlockListener(), this);
        } else {
            //
        }

        if (config.getBoolean("WorldListener.Enable")) {
            getServer().getPluginManager().registerEvents(new WorldListener(), this);
        } else {
            //
        }
        //注册指令监听器
        this.getCommand("ac").setExecutor(new CommandSet(this));
        this.getCommand("ac2").setExecutor(new CommandSet(this));
        this.getCommand("ac3").setExecutor(new CommandSet(this));
        //注册MYSQL
        if (config.getBoolean("MySql.Enable")) {
            getLogger().info("已开启MYSQL模块！");
            getLogger().info("开始尝试连接MYSQL数据库！");
            getLogger().info("数据库用户为:" + config.getString("MySql.User"));
            getLogger().info("数据库为:" + config.getString("MySql.Database"));
            getLogger().info("数据库地址为:" + config.getString("MySql.Address"));
            final String username= config.getString("MySql.User");
            final String password= config.getString("MySql.Passwords");
            final String url = "jdbc:mysql://" + config.getString("MySql.Address") + "/" + config.getString("MySql.Database") + "?useSSL=" + config.getString("MySql.UseSSL");
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.err.println("jdbc driver unavailable!");
                return;
            }
            try {
                connection = DriverManager.getConnection(url,username,password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            getLogger().info("数据库连接成功！");
        } else {
            //
        }

        //若标准表不存在，则MYSQL自动建表
        String sql = "CREATE TABLE IF NOT EXISTS pluginstest(Player text,Time text);";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getLogger().info("插件已启动！");

    }

    @Override
    public void onDisable(){
        //设置配置监听器
        FileConfiguration config = getConfig();
        if (config.getBoolean("MySql.Enable")) {
            getLogger().info("数据库断开连接中！");
            try {
                if (connection!=null && !connection.isClosed()){

                    connection.close();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            getLogger().info("数据库断开连接成功！");
        } else {
            //
        }
        getLogger().info("插件已卸载，可能是关服！");
    }
}
