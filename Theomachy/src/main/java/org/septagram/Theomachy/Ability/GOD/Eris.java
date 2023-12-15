package org.septagram.Theomachy.Ability.GOD;

import java.util.Random;

import org.bukkit.Bukkit;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import org.septagram.Theomachy.Ability.Ability;
import org.septagram.Theomachy.Ability.ENUM.AbilityInfo;
import org.septagram.Theomachy.Ability.ENUM.AbilityRank;
import org.septagram.Theomachy.Theomachy;

public class Eris extends Ability {

    private final static String[] des = {
            AbilityInfo.Eris.getName() + "는 불화의 여신입니다.",
            NamedTextColor.YELLOW + "【패시브】 " + NamedTextColor.WHITE + "시기",
            "자신을 공격한 플레이어를 20% 확률로 밀쳐냅니다."};

    public Eris(String playerName) {

        super(playerName, AbilityInfo.Eris, false, true, false, des);

        this.rank = AbilityRank.A;

    }

    public void passiveSkill(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getEntity();
        Player enemy = (Player) event.getDamager();
        Random random = new Random();
        int rn = random.nextInt(5);
        if (player.getName().equals(playerName)) {
            if (rn == 0) {
                Location playerLocation = player.getLocation();
                playerLocation.setX(playerLocation.getX() + 5);
                playerLocation.setZ(playerLocation.getZ() + 5);
                Bukkit.getScheduler().runTask(Theomachy.getPlugin(), () -> {
                    enemy.teleport(playerLocation);
                });
                player.sendMessage(NamedTextColor.RED + "상대를 밀쳤습니다!");
                enemy.sendMessage(NamedTextColor.RED + AbilityInfo.Eris.getName() +"에 의해 밀쳐졌습니다.");
            }
        }
    }

}
