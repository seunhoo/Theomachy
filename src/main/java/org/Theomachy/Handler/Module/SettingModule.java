package org.Theomachy.Handler.Module;

import org.Theomachy.Enum.CommonMessage;
import org.Theomachy.Theomachy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class SettingModule {
    static int settingSize = 2 * 9;

    public static void openSettingInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, settingSize, CommonMessage.SETTING.getMessage());
        inventory.setItem(0, CommonModule.setItem(Theomachy.STARTING_INVENTORY_CLEAR ? Material.WHITE_WOOL : Material.RED_WOOL, 1, CommonMessage.STARTING_INVENTORY_CLEAR.getMessage()));
        inventory.setItem(2, CommonModule.setItem(Theomachy.STARTING_GIVE_ITEM ? Material.WHITE_WOOL : Material.RED_WOOL, 1, CommonMessage.STARTING_GIVE_BASIC_ITEM.getMessage()));
        inventory.setItem(4, CommonModule.setItem(Theomachy.STARTING_ENTITY_CLEAR ? Material.WHITE_WOOL : Material.RED_WOOL, 1, CommonMessage.STARTING_ENTITY_CLEAR.getMessage()));
        inventory.setItem(6, CommonModule.setItem(Theomachy.IGNORE_BED ? Material.WHITE_WOOL : Material.RED_WOOL, 1, CommonMessage.IGNORE_BED.getMessage()));
        inventory.setItem(8, CommonModule.setItem(Theomachy.FAST_START ? Material.WHITE_WOOL : Material.RED_WOOL, 1, CommonMessage.FAST_START.getMessage()));
        inventory.setItem(9, CommonModule.setItem(Theomachy.AUTO_SAVE ? Material.WHITE_WOOL : Material.RED_WOOL, 1, CommonMessage.SERVER_AUTO_SAVE.getMessage()));
        inventory.setItem(11, CommonModule.setItem(Theomachy.ANIMAL_SPAWN ? Material.WHITE_WOOL : Material.RED_WOOL, 1, CommonMessage.ANIMAL_SPAWN.getMessage()));
        inventory.setItem(13, CommonModule.setItem(Theomachy.MONSTER_SPAWN ? Material.WHITE_WOOL : Material.RED_WOOL, 1, CommonMessage.MONSTER_SPWAN.getMessage()));
        inventory.setItem(15, CommonModule.setItem(Theomachy.GAMBLING ? Material.WHITE_WOOL : Material.RED_WOOL, 1, CommonMessage.GAMBLING_ACCEPT.getMessage()));
    }

    public static void guiListener(ItemStack wool) {

        switch (ChatColor.stripColor(Objects.requireNonNull(Objects.requireNonNull(wool.getItemMeta()).getDisplayName()))) {
            case "게임 시작 시 인벤토리 클리어" -> {
                if (wool.getType() == Material.WHITE_WOOL) {
                    Theomachy.STARTING_INVENTORY_CLEAR = false;
                    wool.setType(Material.RED_WOOL);
                } else {
                    Theomachy.STARTING_INVENTORY_CLEAR = true;
                    wool.setType(Material.WHITE_WOOL);
                }
            }
            case "게임 시작 시 스카이블럭 아이텝 지급" -> {
                if (wool.getType() == Material.WHITE_WOOL) {
                    Theomachy.STARTING_GIVE_ITEM = false;
                    wool.setType(Material.RED_WOOL);
                } else {
                    Theomachy.STARTING_GIVE_ITEM = true;
                    wool.setType(Material.WHITE_WOOL);
                }
            }
            case "게임 시작 시 엔티티 제거" -> {
                if (wool.getType() == Material.WHITE_WOOL) {
                    Theomachy.STARTING_ENTITY_CLEAR = false;
                    wool.setType(Material.RED_WOOL);
                } else {
                    Theomachy.STARTING_ENTITY_CLEAR = true;
                    wool.setType(Material.WHITE_WOOL);
                }
            }
            case "침대 무시" -> {
                if (wool.getType() == Material.WHITE_WOOL) {
                    Theomachy.IGNORE_BED = false;
                    wool.setType(Material.RED_WOOL);
                } else {
                    Theomachy.IGNORE_BED = true;
                    wool.setType(Material.WHITE_WOOL);
                }
            }
            case "빠른 시작" -> {
                if (wool.getType() == Material.WHITE_WOOL) {
                    Theomachy.FAST_START = false;
                    wool.setType(Material.RED_WOOL);
                } else {
                    Theomachy.FAST_START = true;
                    wool.setType(Material.WHITE_WOOL);
                }
            }
            case "서버 자동 저장" -> {
                if (wool.getType() == Material.WHITE_WOOL) {
                    Theomachy.AUTO_SAVE = false;
                    wool.setType(Material.RED_WOOL);
                } else {
                    Theomachy.AUTO_SAVE = true;
                    wool.setType(Material.WHITE_WOOL);
                }
            }
            case "동물 스폰" -> {
                if (wool.getType() == Material.WHITE_WOOL) {
                    Theomachy.ANIMAL_SPAWN = false;
                    wool.setType(Material.RED_WOOL);
                } else {
                    Theomachy.ANIMAL_SPAWN = true;
                    wool.setType(Material.WHITE_WOOL);
                }
            }
            case "몬스터 스폰" -> {
                if (wool.getType() == Material.WHITE_WOOL) {
                    Theomachy.MONSTER_SPAWN = false;
                    wool.setType(Material.RED_WOOL);
                } else {
                    Theomachy.MONSTER_SPAWN = true;
                    wool.setType(Material.WHITE_WOOL);
                }
            }
            case "뽑기 허용" -> {
                if (wool.getType() == Material.WHITE_WOOL) {
                    Theomachy.GAMBLING = false;
                    wool.setType(Material.RED_WOOL);
                } else {
                    Theomachy.GAMBLING = true;
                    wool.setType(Material.WHITE_WOOL);

                }
            }
        }
    }
}