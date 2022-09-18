package net.danh.mmocraftingstation.Listeners;

import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.gui.CraftingStationView;
import net.danh.mmocraftingstation.Manager.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Objects;

public class StationOpen implements Listener {

    private final ConfigManager configManager;

    public StationOpen(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onStationOpen(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String m = e.getMessage();
        Objects.requireNonNull(configManager.getConfig().getConfig().getConfigurationSection("stations")).getKeys(false).forEach(s -> {
            String cmd = configManager.getConfig().getConfig().getString("stations." + s + ".command");
            String crafting = configManager.getConfig().getConfig().getString("stations." + s + ".station");
            String type = configManager.getConfig().getConfig().getString("stations." + s + ".type");
            String permission = configManager.getConfig().getConfig().getString("stations." + s + ".permission");
            CraftingStationView craftingStationView = new CraftingStationView(p, MMOItems.plugin.getCrafting().getStation(crafting), 1);
            if (cmd != null && type != null) {
                if (cmd.equals(m)) {
                    if (type.equalsIgnoreCase("default")) {
                        craftingStationView.open();
                    }
                    if (type.equalsIgnoreCase("permission")) {
                        if (permission != null) {
                            if (p.hasPermission(permission)) {
                                craftingStationView.open();
                            } else {
                                net.danh.dcore.Utils.Player.sendPlayerMessage(p, configManager.getConfig().getConfig().getString("message.permission", "&cYou need permission #p# to open it")
                                        .replace("#p#", permission));
                            }
                        } else {
                            craftingStationView.open();
                        }
                    }
                    e.setCancelled(true);
                }
            }
        });
    }
}
