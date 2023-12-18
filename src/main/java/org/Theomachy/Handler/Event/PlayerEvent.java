package org.Theomachy.Handler.Event;

import org.Theomachy.Ability.Ability;
import org.Theomachy.Data.GameData;
import org.Theomachy.Enum.AbilityInfo;
import org.Theomachy.Handler.Command.StartStopCommand;
import org.Theomachy.Theomachy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.*;

import java.util.ArrayList;

public class PlayerEvent implements Listener {


    public static ArrayList<Ability> PlayerDeathEventList = new ArrayList<Ability>();

    @EventHandler
    public static void onPlayerDeath(PlayerDeathEvent event) {
        if (StartStopCommand.Start) {
            for (Ability e : PlayerDeathEventList)
                e.passiveSkill(event);
            Player player = event.getEntity();
            Ability ability = GameData.PlayerAbility.get(player.getName());
            if (ability != null)
                if (ability.abilityCode == AbilityInfo.Creeper.getIndex() ||
                        ability.abilityCode == AbilityInfo.Hades.getIndex() ||
                        ability.abilityCode == AbilityInfo.Snow.getIndex()) {
                    ability.passiveSkill(event);
                }
        }
    }

    @EventHandler
    public static void onPlayerRespawn(PlayerRespawnEvent event) {
        if (StartStopCommand.Start) {
            Player player = event.getPlayer();
            if (Theomachy.IGNORE_BED) {
                if (GameData.PlayerTeam.containsKey(player.getName())) {
                    String teamName = GameData.PlayerTeam.get(player.getName());
                    Location respawnLocation = GameData.SpawnArea.get(teamName);
                    if (respawnLocation != null)
                        event.setRespawnLocation(respawnLocation);
                }
            } else {
                if (!event.isBedSpawn() && GameData.PlayerTeam.containsKey(player.getName())) {
                    String teamName = GameData.PlayerTeam.get(player.getName());
                    Location respawnLocation = GameData.SpawnArea.get(teamName);
                    if (respawnLocation != null)
                        event.setRespawnLocation(respawnLocation);
                }
            }
            Ability ability = GameData.PlayerAbility.get(player.getName());
            if (ability != null) {
                if (ability.buffType)
                    ability.buff();
                if (ability.abilityCode == AbilityInfo.Hades.getIndex() || ability.abilityCode == AbilityInfo.Goldspoon.getIndex())
                    ability.passiveSkill(event);
            }
        }
    }

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        GameData.OnlinePlayer.put(player.getName(), player);
        if (StartStopCommand.Start) {
            Ability ability = GameData.PlayerAbility.get(player.getName());
            if (ability != null && (ability.abilityCode == AbilityInfo.Poseidon.getIndex() || ability.abilityCode == AbilityInfo.Hephastus.getIndex())) {
                ability.initialize();
            }
        }
    }

    @EventHandler
    public static void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        GameData.OnlinePlayer.remove(player.getName());
    }

    @EventHandler
    public static void onPlayerKick(PlayerKickEvent event) {
        Theomachy.log.info(event.getReason());
    }

    @EventHandler
    public static void onPlayerMove(PlayerMoveEvent event) {
        if (StartStopCommand.Start) {
            Ability ability = GameData.PlayerAbility.get(event.getPlayer().getName());
            if (ability != null && ability.abilityCode == AbilityInfo.PokeGo.getIndex())
                ability.passiveSkill(event);
        }
    }

    @EventHandler
    public static void onProjectileLaunch(ProjectileLaunchEvent event) {
        Bukkit.getScheduler().runTask(Theomachy.getPlugin(), () -> {
            if (event.getEntity() instanceof Arrow arrow) {
                if (arrow.getShooter() instanceof Player player) {
                    Ability ability = GameData.PlayerAbility.get(player.getName());
                    if (ability != null && ability.abilityCode == AbilityInfo.Sniper.getIndex())
                        ability.passiveSkill(event, player);
                }
            }
        });
    }


    @EventHandler
    public static void onPlayerInteractEvent(PlayerInteractEvent event) {
        if (StartStopCommand.Start) {
            String playerName = event.getPlayer().getName();
            Ability ability = GameData.PlayerAbility.get(playerName);
            if (ability != null && ability.activeType) {
                ability.activeSkill(event);
            }
        }
    }

    @EventHandler
    public static void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (StartStopCommand.Start) {
            if (event.getEntity() instanceof Player) {
                String playerName = ((Player) event.getEntity()).getName();
                if (GameData.PlayerAbility.containsKey(playerName))
                    GameData.PlayerAbility.get(playerName).passiveSkill(event);
            }
        }
    }

    @EventHandler
    public static void onEntityRegainHealth(EntityRegainHealthEvent event) {
        if (StartStopCommand.Start) {
            if (event.getEntity() instanceof Player) {
                String playerName = ((Player) event.getEntity()).getName();
                if (GameData.PlayerAbility.containsKey(playerName))
                    GameData.PlayerAbility.get(playerName).passiveSkill(event);
            }
        }
    }


}