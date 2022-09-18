package net.danh.mmocraftingstation.CMD;

import net.danh.dcore.Commands.CMDBase;
import net.danh.mmocraftingstation.Manager.ConfigManager;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class CMD extends CMDBase {

    private final ConfigManager configManager;

    public CMD(JavaPlugin core, String name, ConfigManager configManager) {
        super(core, name);
        this.configManager = configManager;
    }

    @Override
    public void playerexecute(Player p, String[] args) {
        if (p.hasPermission("mmocs.admin")) {
            configManager.getConfig().save();
            configManager.getConfig().load();
            net.danh.dcore.Utils.Player.sendPlayerMessage(p, "&aReloaded");
        }
    }

    @Override
    public void consoleexecute(ConsoleCommandSender c, String[] args) {
        configManager.getConfig().save();
        configManager.getConfig().load();
        net.danh.dcore.Utils.Player.sendConsoleMessage(c, "&aReloaded");
    }

    @Override
    public List<String> TabComplete(CommandSender commandSender, String[] strings) {
        return null;
    }
}
