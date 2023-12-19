package org.Theomachy.Handler.Event;

import org.Theomachy.Enum.TheomachyMessage;
import org.Theomachy.Handler.Module.GamblingModule;
import org.Theomachy.Handler.Module.SettingModule;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class MenuEvent implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(TheomachyMessage.SETTING_MENU.getMessage())) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack wool = event.getCurrentItem();
            assert wool != null;
            String menuName = ChatColor.stripColor(Objects.requireNonNull(Objects.requireNonNull(wool.getItemMeta()).getDisplayName()));
            if (menuName.equals(TheomachyMessage.SETTING_GAMBLING.getMessage())) {
                GamblingModule.gambling(player);
            }
        }
        else if (event.getView().getTitle().equals(TheomachyMessage.SETTING.getMessage())) {
            event.setCancelled(true);
            ItemStack wool = event.getCurrentItem();
            assert wool != null;
            if (event.getView().getTitle().equals(TheomachyMessage.SETTING.getMessage())) {
                SettingModule.serverSetting(wool);
            }
        }
    }
}
