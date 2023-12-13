package org.septagram.Theomachy.Ability.GOD;

import java.util.Timer;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import org.septagram.Theomachy.Theomachy;
import org.septagram.Theomachy.Ability.Ability;
import org.septagram.Theomachy.Timer.Skill.HoreunTimer;
import org.septagram.Theomachy.Utility.CoolTimeChecker;
import org.septagram.Theomachy.Utility.EventFilter;
import org.septagram.Theomachy.Utility.PlayerInventory;
import org.septagram.Theomachy.Utility.Skill;

public class Horeundal extends Ability{
	
	private final static String[] des= {
			"호른달은 시간과 공간의 신입니다.",
			ChatColor.AQUA+"【일반】 "+ChatColor.WHITE+"시공 초월",
			"위치 기억 후 10초 뒤 되돌아옵니다. 되돌아 온 뒤에 잠시 투명해집니다."};
	
	public Horeundal(String playerName)
	{
		super(playerName,"호른달", 18, true, false, false ,des);
		Theomachy.log.info(playerName+abilityName);
		
		
		this.cool1=120;
		this.sta1=32;

		this.rank=4;
	}
	
	public void T_Active(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		if (PlayerInventory.InHandItemCheck(player, Material.BLAZE_ROD))
		{
			switch(EventFilter.PlayerInteract(event))
			{
			case 0:case 1:
				leftAction(player);
				break;
			}
		}
	}

	private void leftAction(Player player) {
		
		if(CoolTimeChecker.Check(player, 0)&&PlayerInventory.ItemCheck(player, Material.COBBLESTONE, sta1)){
			
			Skill.Use(player, Material.COBBLESTONE, sta1, 0, cool1);
			player.sendMessage("위치를 기억했습니다! 10초 뒤에 여기로 올 것입니다.");
			
			Timer t=new Timer();
			t.schedule(new HoreunTimer(player, player.getLocation()), 7000, 1000);
		}
		
	}
	
}
