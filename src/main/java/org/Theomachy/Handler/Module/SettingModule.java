package org.Theomachy.Handler.Module;

import org.Theomachy.Message.TheomachyMessage;
import org.Theomachy.Theomachy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class SettingModule {
    static int settingSize = 2 * 9;

    public static void openSettingInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, settingSize, TheomachyMessage.SETTING.getMessage());
        inventory.setItem(0, CommonModule.setItem(Theomachy.STARTING_INVENTORY_CLEAR ? Material.WHITE_WOOL : Material.RED_WOOL, 1, TheomachyMessage.SETTING_STARTING_INVENTORY_CLEAR.getMessage()));
        inventory.setItem(2, CommonModule.setItem(Theomachy.STARTING_GIVE_ITEM ? Material.WHITE_WOOL : Material.RED_WOOL, 1, TheomachyMessage.SETTING_STARTING_GIVE_ITEM.getMessage()));
        inventory.setItem(4, CommonModule.setItem(Theomachy.STARTING_ENTITY_CLEAR ? Material.WHITE_WOOL : Material.RED_WOOL, 1, TheomachyMessage.SETTING_STARTING_ENTITY_CLEAR.getMessage()));
        inventory.setItem(6, CommonModule.setItem(Theomachy.IGNORE_BED ? Material.WHITE_WOOL : Material.RED_WOOL, 1, TheomachyMessage.SETTING_IGNORE_BED.getMessage()));
        inventory.setItem(8, CommonModule.setItem(Theomachy.FAST_START ? Material.WHITE_WOOL : Material.RED_WOOL, 1, TheomachyMessage.SETTING_FAST_START.getMessage()));
        inventory.setItem(9, CommonModule.setItem(Theomachy.SERVER_AUTO_SAVE ? Material.WHITE_WOOL : Material.RED_WOOL, 1, TheomachyMessage.SETTING_SERVER_AUTO_SAVE.getMessage()));
        inventory.setItem(11, CommonModule.setItem(Theomachy.ANIMAL_SPAWN ? Material.WHITE_WOOL : Material.RED_WOOL, 1, TheomachyMessage.SETTING_ANIMAL_SPAWN.getMessage()));
        inventory.setItem(13, CommonModule.setItem(Theomachy.MONSTER_SPAWN ? Material.WHITE_WOOL : Material.RED_WOOL, 1, TheomachyMessage.SETTING_MONSTER_SPAWN.getMessage()));
        inventory.setItem(15, CommonModule.setItem(Theomachy.GAMBLING ? Material.WHITE_WOOL : Material.RED_WOOL, 1, TheomachyMessage.SETTING_GAMBLING_ACCEPT.getMessage()));
        player.openInventory(inventory);
    }

    public static void serverSetting(ItemStack wool) {
        TheomachyMessage byMessage = TheomachyMessage.getByMessage(Objects.requireNonNull(wool.getItemMeta()).getDisplayName());
        String broadcastMessage = byMessage.getMessage();
        switch (Objects.requireNonNull(byMessage)) {
            case SETTING_STARTING_INVENTORY_CLEAR -> {
//                broadcastMessage += check(Theomachy.STARTING_INVENTORY_CLEAR, wool);
                if (wool.getType() == Material.WHITE_WOOL) {
                    Theomachy.STARTING_INVENTORY_CLEAR = false;
                    wool.setType(Material.RED_WOOL);
                } else {
                    Theomachy.STARTING_INVENTORY_CLEAR = true;
                    wool.setType(Material.WHITE_WOOL);
                }
            }
            case SETTING_STARTING_GIVE_ITEM -> {
//                broadcastMessage += check(Theomachy.STARTING_GIVE_ITEM, wool);
                if (wool.getType() == Material.WHITE_WOOL) {
                    Theomachy.STARTING_GIVE_ITEM = false;
                    wool.setType(Material.RED_WOOL);
                } else {
                    Theomachy.STARTING_GIVE_ITEM = true;
                    wool.setType(Material.WHITE_WOOL);
                }
            }
            case SETTING_STARTING_ENTITY_CLEAR -> {
//                broadcastMessage += check(Theomachy.STARTING_ENTITY_CLEAR, wool);
                if (wool.getType() == Material.WHITE_WOOL) {
                    Theomachy.STARTING_ENTITY_CLEAR = false;
                    wool.setType(Material.RED_WOOL);
                } else {
                    Theomachy.STARTING_ENTITY_CLEAR = true;
                    wool.setType(Material.WHITE_WOOL);
                }
            }
            case SETTING_IGNORE_BED -> {
//                broadcastMessage += check(Theomachy.IGNORE_BED, wool);
                if (wool.getType() == Material.WHITE_WOOL) {
                    Theomachy.IGNORE_BED = false;
                    wool.setType(Material.RED_WOOL);
                } else {
                    Theomachy.IGNORE_BED = true;
                    wool.setType(Material.WHITE_WOOL);
                }
            }
            case SETTING_FAST_START -> {
//                broadcastMessage += check(Theomachy.FAST_START, wool);
                if (wool.getType() == Material.WHITE_WOOL) {
                    Theomachy.FAST_START = false;
                    wool.setType(Material.RED_WOOL);
                } else {
                    Theomachy.FAST_START = true;
                    wool.setType(Material.WHITE_WOOL);
                }
            }
            case SETTING_SERVER_AUTO_SAVE -> {
//                broadcastMessage += check(Theomachy.SERVER_AUTO_SAVE, wool);
                if (wool.getType() == Material.WHITE_WOOL) {
                    Theomachy.SERVER_AUTO_SAVE = false;
                    wool.setType(Material.RED_WOOL);
                } else {
                    Theomachy.SERVER_AUTO_SAVE = true;
                    wool.setType(Material.WHITE_WOOL);
                }
            }
            case SETTING_ANIMAL_SPAWN -> {
//                broadcastMessage += check(Theomachy.ANIMAL_SPAWN, wool);
                if (wool.getType() == Material.WHITE_WOOL) {
                    Theomachy.ANIMAL_SPAWN = false;
                    wool.setType(Material.RED_WOOL);
                } else {
                    Theomachy.ANIMAL_SPAWN = true;
                    wool.setType(Material.WHITE_WOOL);
                }
            }
            case SETTING_MONSTER_SPAWN -> {
//                broadcastMessage += check(Theomachy.MONSTER_SPAWN, wool);
                if (wool.getType() == Material.WHITE_WOOL) {
                    Theomachy.MONSTER_SPAWN = false;
                    wool.setType(Material.RED_WOOL);
                } else {
                    Theomachy.MONSTER_SPAWN = true;
                    wool.setType(Material.WHITE_WOOL);
                }
            }
            case SETTING_GAMBLING_ACCEPT -> {
//                broadcastMessage += check(Theomachy.GAMBLING, wool);
                if (wool.getType() == Material.WHITE_WOOL) {
                    Theomachy.GAMBLING = false;
                    wool.setType(Material.RED_WOOL);
                } else {
                    Theomachy.GAMBLING = true;
                    wool.setType(Material.WHITE_WOOL);
                }
            }
        }
        Bukkit.broadcastMessage(broadcastMessage + TheomachyMessage.INFO_SET_MESSAGE.getMessage());
    }

    public static String check(boolean setting, ItemStack itemStack) {
        if (itemStack.getType() == Material.WHITE_WOOL) {
            setting = false;
            itemStack.setType(Material.RED_WOOL);
        } else {
            setting = true;
            itemStack.setType(Material.WHITE_WOOL);
        }
        return setting ? TheomachyMessage.INFO_ABLE.getMessage() : TheomachyMessage.INFO_DISABLED.getMessage();
    }
}
