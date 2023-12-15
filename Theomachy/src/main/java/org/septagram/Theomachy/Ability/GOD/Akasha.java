package org.septagram.Theomachy.Ability.GOD;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import org.septagram.Theomachy.Ability.ENUM.AbilityCase;
import org.septagram.Theomachy.Ability.ENUM.AbilityInfo;
import org.septagram.Theomachy.Ability.ENUM.AbilityRank;
import org.septagram.Theomachy.Theomachy;
import org.septagram.Theomachy.Ability.Ability;
import org.septagram.Theomachy.Utility.CoolTimeChecker;
import org.septagram.Theomachy.Utility.EventFilter;
import org.septagram.Theomachy.Utility.GetPlayerList;
import org.septagram.Theomachy.Utility.PlayerInventory;
import org.septagram.Theomachy.Utility.Skill;

public class Akasha extends Ability{
	private final static String[] des= {
			AbilityInfo.Akasha.getName() + "는 고통과 향락의 여신입니다.",
			ChatColor.AQUA+"【일반】 "+ChatColor.WHITE+"향락",
			"주변에 있는 아군에게 기쁨을 주어 15초간 속도와 재생을 부여 합니다.",
			ChatColor.RED+"【고급】 "+ChatColor.WHITE+"고통",
			"주변에 있는 적군에게 고통을 주어 6초간 혼란하게 합니다."};

	private final int enemyDuration;
	private final int teamDuration;
	public Akasha(String playerName)
	{
		super(playerName, AbilityInfo.Akasha, true, false, false ,des);
		Theomachy.log.info(playerName+abilityName);
		this.firstSkillCoolTime =60;
		this.firstSkillStack =10;
		this.secondSkillCoolTime =100;
		this.secondSkillStack =20;
		this.teamDuration = 15;
		this.enemyDuration = 6;
		this.rank= AbilityRank.S;
	}
	
	public void activeSkill(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		if (PlayerInventory.InHandItemCheck(player, Material.BLAZE_ROD))
		{
            switch (EventFilter.PlayerInteract(event)) {
				case LEFT_CLICK_AIR,LEFT_CLICK_BLOCK -> leftAction(player);
				case RIGHT_CLICK_AIR,RIGHT_CLICK_BLOCK -> rightAction(player);
            }
		}
	}

	private void leftAction(Player player) {
		if(CoolTimeChecker.Check(player, AbilityCase.NORMAL)&&PlayerInventory.ItemCheck(player, material, firstSkillStack)){
			Skill.Use(player, material,  AbilityCase.NORMAL,firstSkillStack, firstSkillCoolTime);
			List<Player> nearPlayers = GetPlayerList.getNearByTeamMembers(player, 20, 20, 20);
			for(Player nearPlayer : nearPlayers){
				nearPlayer.sendMessage(ChatColor.DARK_PURPLE+"향락"+ChatColor.WHITE+"이 당신을 즐겁게합니다!");
				nearPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,teamDuration * 20, 0));
				nearPlayer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,teamDuration * 20, 0));
			}
		}
		
	}
	
	private void rightAction(Player player) {
		
		if(CoolTimeChecker.Check(player, AbilityCase.RARE)&&PlayerInventory.ItemCheck(player,material, secondSkillStack)){
			List<Player> entityList = GetPlayerList.getNearByNotTeamMembers(player, 15, 15, 15);
			if(entityList.isEmpty()){
				player.sendMessage(ChatColor.RED +"주변에 상대팀이 없습니다");
				return;
			}
			Skill.Use(player,material,AbilityCase.RARE, secondSkillStack, secondSkillCoolTime);
			for(Player e:entityList){
				e.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,20 * enemyDuration, 0));
				e.setHealth(e.getHealth()-4);
			}
			
		}
	}
	
}
