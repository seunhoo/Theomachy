package org.Theomachy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.Theomachy.Handler.Module.BlacklistModule;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.Theomachy.Data.AbilityData;
import org.Theomachy.Data.GameData;
import org.Theomachy.Data.PluginData;
import org.Theomachy.Manager.CommandManager;
import org.Theomachy.Manager.EventManager;

public class Theomachy extends JavaPlugin {
    private static Plugin plugin;
    public static boolean STARTING_INVENTORY_CLEAR = true;
    public static boolean STARTING_GIVE_ITEM = true;
    public static boolean STARTING_ENTITY_CLEAR = true;
    public static boolean IGNORE_BED = true;
    public static boolean AUTO_SAVE = false;
    public static boolean ANIMAL_SPAWN = true;
    public static boolean MONSTER_SPAWN = true;
    public static boolean FAST_START = false;
    public static int DIFFICULTY = 1;
    public static boolean GAMBLING = true;

    public static Logger log = Bukkit.getLogger();

    public File file = new File(getDataFolder(), "blacklist.yml");

    public static Plugin getPlugin() {
        return plugin;
    }

    private BukkitAudiences adventure;
    public @NonNull BukkitAudiences adventure() {
        if(this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return this.adventure;
    }
    public void onEnable() {
        this.adventure = BukkitAudiences.create(this);

        plugin = this;
        UpdateChecker.check("5.0");

        log.info("[신들의 전쟁] 플러그인이 활성화되었습니다.   " + PluginData.buildnumber + "  " + PluginData.version);
        log.info("[신들의 전쟁] 플러그인의 기본 설정을 적용 중입니다.");

        saveResource("blacklist.yml", true);

        // command 등록
        CommandManager commandManager = new CommandManager(this);

        // recipe 등록
        NamespacedKey customBlazeRodRecipe = new NamespacedKey(this, "custom_blaze_rod_recipe");
        ShapedRecipe recipe = new ShapedRecipe(customBlazeRodRecipe, new ItemStack(Material.BLAZE_ROD)).shape("|", "|", "|").setIngredient('|', Material.STICK);
        getServer().addRecipe(recipe);

        // event 등록
        EventManager.setEvent(getServer(),this);

        // blacklist
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        try {
            fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                BlacklistModule.blacklist.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            log.info(e.toString());
        }
        for (int i = 1; i <= AbilityData.GOD_ABILITY_NUMBER; i++) {
            if (!BlacklistModule.blacklist.contains(i)) BlacklistModule.godCanlist.add(i);
        }
        for (int i = 101; i <= AbilityData.HUMAN_ABILITY_NUMBER + 100; i++) {
            if (!BlacklistModule.blacklist.contains(i)) BlacklistModule.humanCanlist.add(i);
        }
        for (int i = 301; i <= AbilityData.JUJUTSU_KAISEN_ABILITY_NUMBER + 300; i++) {
            if (!BlacklistModule.blacklist.contains(i)) BlacklistModule.jujutsuCanList.add(i);
        }
        for (int i = 401; i <= AbilityData.KIMETSU_NO_YAIBA_ABILITY_NUMBER + 400; i++) {
            if (!BlacklistModule.blacklist.contains(i)) BlacklistModule.kimetsuCanlist.add(i);
        }

        // 기본 정보
        log.info("[신들의 전쟁] 등록된 능력");
        log.info("[신들의 전쟁] 신: " + BlacklistModule.godCanlist.size());
        log.info("[신들의 전쟁] 인간: " + BlacklistModule.humanCanlist.size());
        log.info("[신들의 전쟁] 주술 회전: " + BlacklistModule.jujutsuCanList.size());
        log.info("[신들의 전쟁] 귀멸의 칼날: " + BlacklistModule.kimetsuCanlist.size());
        log.info("[신들의 전쟁] 총합: " + String.valueOf(
                BlacklistModule.godCanlist.size() + BlacklistModule.humanCanlist.size() +
                        BlacklistModule.jujutsuCanList.size() + BlacklistModule.kimetsuCanlist.size()));

        log.info("[신들의 전쟁] 게임의 설정 불러오는 중입니다.");
        getConfig().options().copyDefaults(true);
        saveConfig();
        STARTING_INVENTORY_CLEAR = getConfig().getBoolean("인벤토리 클리어");
        STARTING_GIVE_ITEM = getConfig().getBoolean("스카이블럭 아이템 제공");
        STARTING_ENTITY_CLEAR = getConfig().getBoolean("엔티티 제거");
        IGNORE_BED = getConfig().getBoolean("침대 무시");
        AUTO_SAVE = getConfig().getBoolean("서버 자동 저장");
        ANIMAL_SPAWN = getConfig().getBoolean("동물 생성");
        MONSTER_SPAWN = getConfig().getBoolean("몬스터 생성");
        DIFFICULTY = getConfig().getInt("난이도");
        FAST_START = getConfig().getBoolean("빠른 시작");
        GAMBLING = getConfig().getBoolean("도박 허용");

        log.info("[신들의 전쟁] ========================================");
        log.info("[신들의 전쟁] 게임 시작 시 인벤토리 클리어 : " + String.valueOf(STARTING_INVENTORY_CLEAR));
        log.info("[신들의 전쟁] 게임 시작 시 스카이블럭 기본 아이템 지급 : " + String.valueOf(STARTING_GIVE_ITEM));
        log.info("[신들의 전쟁] 게임 시작 시 몬스터,동물,아이템삭제 : " + String.valueOf(STARTING_ENTITY_CLEAR));
        log.info("[신들의 전쟁] 리스폰 시 침대 무시 : " + String.valueOf(IGNORE_BED));
        log.info("[신들의 전쟁] 빠른 시작 : " + String.valueOf(FAST_START));
        log.info("[신들의 전쟁] 도박 허용 : " + String.valueOf(GAMBLING));
        log.info("[신들의 전쟁] 서버 자동저장 : " + String.valueOf(AUTO_SAVE));
        log.info("[신들의 전쟁] 동물 스폰 : " + String.valueOf(ANIMAL_SPAWN));
        log.info("[신들의 전쟁] 몬스터 스폰 : " + String.valueOf(MONSTER_SPAWN));
        log.info("[신들의 전쟁] 난이도 : " + String.valueOf(DIFFICULTY));
        log.info("[신들의 전쟁] ========================================");

        Bukkit.getConsoleSender().sendMessage(
                "\n====원작자: " + ChatColor.WHITE + "칠각별(septagram)====\n"
                        + ChatColor.GRAY + "====2차수정자: " + ChatColor.AQUA + "플로리아(humint2003)====\n"
                        + ChatColor.YELLOW + "====3차수정자: " + ChatColor.GREEN + "프덧(foodust)====\n");

        try {
            for (Player p : Bukkit.getOnlinePlayers()) {
                GameData.OnlinePlayer.put(p.getName(), p);
            }
        } catch (NullPointerException ignored) {
        }
    }

    public void onDisable() {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(file));

            for (int i : BlacklistModule.blacklist) {
                bw.write(String.valueOf(i));
                bw.newLine();
            }

            bw.close();
        } catch (IOException ignored) {
        }
        log.info("[신들의 전쟁] 블랙리스트가 파일로 저장되었습니다. 절대로 플러그인 폴더 내에 blacklist.yml을 건들지 마십시오.");
        if(this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
    }
}
