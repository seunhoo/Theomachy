package org.Theomachy.Ability.JUJUTSU_KAISEN;

import com.google.common.util.concurrent.AtomicDouble;
import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.EffectType;
import de.slikey.effectlib.effect.*;
import org.Theomachy.Ability.Ability;
import org.Theomachy.Enum.AbilityCase;
import org.Theomachy.Enum.AbilityInfo;
import org.Theomachy.Enum.AbilityRank;
import org.Theomachy.Handler.Module.PlayerModule;
import org.Theomachy.Theomachy;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Objects;

public class Satoru extends Ability {
    private final static String[] des = {
            AbilityInfo.Satoru.getName(),
            ChatColor.AQUA + "【일반】 " + ChatColor.WHITE + "허식 「자」",
            "전방에 구체를 보냅니다.",
            ChatColor.RED + "【고급】 " + ChatColor.WHITE + "영역 전개 | 무량공처 (無量空処)",
            "영역에 상대방을 가두고 무수한 정보를 보냅니다. 자신은 면역입니다."
    };
    private final int normalDamage;
    private final double normalDistance;
    private final long normalDuration;
    private final int rareDistance;
    private final int rareDamage;

    public Satoru(String playerName) {
        super(playerName, AbilityInfo.Satoru, true, true, false, des);
        Theomachy.log.info(playerName + abilityName);
        this.normalSkillCoolTime = 80;
        this.normalSkillStack = 16;
        this.normalDistance = 0.5;
        this.normalDuration = 10L;
        this.normalDamage = 5;

        this.rareSkillCoolTime = 120;
        this.rareSkillStack = 50;
        this.rareDistance = 40;
        this.rareDamage = 20;
        this.rank = AbilityRank.S;
    }

    public void activeSkill(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (playerModule.InHandItemCheck(player, skillMaterial)) {
            switch (event.getAction()) {
                case LEFT_CLICK_AIR, LEFT_CLICK_BLOCK -> leftAction(player);
                case RIGHT_CLICK_AIR, RIGHT_CLICK_BLOCK -> rightAction(player);
            }
        }
    }

    private void leftAction(Player player) {
        if (skillHandler.Check(player, AbilityCase.NORMAL) && playerModule.ItemCheck(player, Material.COBBLESTONE, normalSkillStack)) {
            skillHandler.Use(player, Material.COBBLESTONE, AbilityCase.NORMAL, normalSkillStack, normalSkillCoolTime);
            makeSphereParticle(player);
        }
    }

    private void rightAction(Player player) {
        if (skillHandler.Check(player, AbilityCase.RARE) && playerModule.ItemCheck(player, Material.COBBLESTONE, rareSkillStack)) {
            skillHandler.Use(player, Material.COBBLESTONE, AbilityCase.RARE, rareSkillStack, rareSkillCoolTime);
        }
    }

    private void makeSphereParticle(Player player){
        Location playerLocation = player.getLocation();
        World world = player.getWorld();
        Location center = playerLocation.add(playerLocation.getDirection().multiply(1)).add(0,player.getEyeHeight(),0);
        AtomicDouble distance = new AtomicDouble();

        AtomEffect atomEffect = new AtomEffect(effectManage);
        atomEffect.particleOrbital = Particle.REDSTONE;
        atomEffect.particleNucleus = Particle.REDSTONE;
        atomEffect.particlesOrbital = 30;
        atomEffect.particlesNucleus = 30;
        atomEffect.colorOrbital = Color.PURPLE;
        atomEffect.colorNucleus = Color.PURPLE;
        atomEffect.rotation = 80;
        atomEffect.radius = 2;
        atomEffect.particleCount = 1;
        atomEffect.setLocation(center);
        atomEffect.start();
        BukkitTask bukkitTask = Bukkit.getScheduler().runTaskTimer(Theomachy.getPlugin(), () -> {
            Location particleLocation = center.add(center.getDirection().multiply(distance.getAndAdd(0.015)));
            atomEffect.setLocation(particleLocation);
            world.playSound(particleLocation,Sound.ITEM_TOTEM_USE,0.2f,10f);
            playerModule.damageNearEntity(player,particleLocation,normalDamage,5,5,5);
        }, 0L, 1L);

        Bukkit.getScheduler().runTaskLater(Theomachy.getPlugin(),()->{
            Bukkit.getScheduler().cancelTask(bukkitTask.getTaskId());
            atomEffect.cancel();
        },normalDuration * 20L);
    }
}