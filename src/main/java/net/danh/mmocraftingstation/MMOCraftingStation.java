package net.danh.mmocraftingstation;

import net.danh.mmocraftingstation.CMD.CMD;
import net.danh.mmocraftingstation.Listeners.StationOpen;
import net.danh.mmocraftingstation.Manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MMOCraftingStation extends JavaPlugin {

    ConfigManager configManager = new ConfigManager(this);

    @Override
    public void onEnable() {
        configManager.getConfig().load();
        new CMD(this, "mmocraftingstation", configManager);
        Bukkit.getPluginManager().registerEvents(new StationOpen(configManager), this);
    }

    @Override
    public void onDisable() {
        configManager.getConfig().save();
    }
}
