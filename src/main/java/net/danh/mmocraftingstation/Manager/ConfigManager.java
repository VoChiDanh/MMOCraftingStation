package net.danh.mmocraftingstation.Manager;

import net.danh.dcore.Resource.Files;
import net.danh.mmocraftingstation.MMOCraftingStation;

public class ConfigManager {

    private final MMOCraftingStation mmoCraftingStation;

    public ConfigManager(MMOCraftingStation mmoCraftingStation) {
        this.mmoCraftingStation = mmoCraftingStation;
    }

    public Files getConfig() {
        return new Files(mmoCraftingStation, "config");
    }
}
