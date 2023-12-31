package org.Theomachy.Ability.HUMAN;

import org.bukkit.ChatColor;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import org.Theomachy.Ability.Ability;
import org.Theomachy.Enum.AbilityInfo;
import org.Theomachy.Enum.AbilityRank;
import org.Theomachy.Data.GameData;

public class Anorexia extends Ability {

    private final static String[] des = {
            AbilityInfo.Anorexia.getName() + "은 신경성 식욕부진증이라고도 합니다.",
            ChatColor.YELLOW + "【패시브】 " + ChatColor.WHITE + "단식",
            "배고픔이 절반으로 유지됩니다."
    };

    public Anorexia(String playerName) {
        super(playerName, AbilityInfo.Anorexia, false, true, false, des);
        this.rank = AbilityRank.B;
    }

    public void initialize() {
        GameData.onlinePlayer.get(playerName).setFoodLevel(10);
    }

    public void passiveSkill(FoodLevelChangeEvent event) {
        event.setFoodLevel(10);
    }

}
