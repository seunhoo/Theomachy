package org.septagram.Theomachy.Ability.HUMAN;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import org.septagram.Theomachy.Ability.Ability;
import org.septagram.Theomachy.DB.GameData;
import org.septagram.Theomachy.Theomachy;
import org.septagram.Theomachy.Utility.BlockFilter;
import org.septagram.Theomachy.Utility.CoolTimeChecker;
import org.septagram.Theomachy.Utility.EventFilter;
import org.septagram.Theomachy.Utility.PlayerInventory;
import org.septagram.Theomachy.Utility.Skill;

public class Teleporter extends Ability
{
	private String abilityTarget;
	private final static String[] des= {
			"텔레포터는 순간이동을 돕는 마법사입니다.",
		   	ChatColor.AQUA+"【일반】 "+ChatColor.WHITE+"텔레포팅",
			"25칸 이내의 목표 지점으로 텔레포트합니다." ,
			ChatColor.RED+"【고급】 "+ChatColor.WHITE+"치환",
			"타겟에 등록해 둔 자신의 팀원과 위치를 치환합니다.",
			"목표 지정: /x <대상>"};
	
	public Teleporter(String playerName)
	{
		super(playerName,"텔레포터", 104, true, false, false, des);
		Theomachy.log.info(playerName+abilityName);
		
		this.firstSkillCoolTime =25;
		this.secondSkillCoolTime =30;
		this.firstSkillStack =15;
		this.secondSkillStack =25;
		
		this.rank=2;
	}
	
	public void activeSkill(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		if (PlayerInventory.InHandItemCheck(player, Material.BLAZE_ROD))
		{
            switch (EventFilter.PlayerInteract(event)) {
                case 0, 1 -> leftAction(player);
                case 2, 3 -> rightAction(player);
            }
		}
	}

	private void leftAction(Player player)
	{
		if (CoolTimeChecker.Check(player, 1)&&PlayerInventory.ItemCheck(player, Material.COBBLESTONE, firstSkillStack))
		{
			Block block = player.getTargetBlock(null, 25);
			if (BlockFilter.AirToFar(player, block))
			{
				Location location0 = block.getLocation();
				Location location1 = block.getLocation();
				location0.setY(location0.getY()+1);
				location1.setY(location1.getY()+2);
				Block block0 = location0.getBlock();
				Block block1 = location1.getBlock();

				if ((block0.getType()==Material.AIR || block1.getType() == Material.SNOW)&&block1.getType()==Material.AIR)
				{
					Skill.Use(player, Material.COBBLESTONE, firstSkillStack, 1, firstSkillCoolTime);
					Location plocation = player.getLocation();
					Location tlocation = block.getLocation();
					tlocation.setPitch(plocation.getPitch());
					tlocation.setYaw(plocation.getYaw());
					tlocation.setY(tlocation.getY()+1);
					tlocation.setX(tlocation.getX()+0.5);
					tlocation.setZ(tlocation.getZ()+0.5);
					Bukkit.getScheduler().runTask(Theomachy.getPlugin(),()->{player.teleport(tlocation);});
				}
				else
					player.sendMessage("텔레포트 할 수 있는 공간이 없어 텔레포트에 실패 했습니다.");
			}
		}
	}
	
	private void rightAction(Player player)
	{
		if (CoolTimeChecker.Check(player, 2)&&PlayerInventory.ItemCheck(player, Material.COBBLESTONE, secondSkillStack))
		{
			if (abilityTarget != null)
			{
				Player target = GameData.OnlinePlayer.get(abilityTarget);
				if (target != null)
				{
					Location location = player.getLocation();
					location.setY(location.getY()-1);
					Skill.Use(player, Material.COBBLESTONE, secondSkillStack, 2, secondSkillCoolTime);
					Location tloc = target.getLocation();
					Location ploc = player.getLocation();
					Bukkit.getScheduler().runTask(Theomachy.getPlugin(),()->{target.teleport(ploc);});
					Bukkit.getScheduler().runTask(Theomachy.getPlugin(),()->{player.teleport(tloc);});
					target.sendMessage("텔레포터의 능력에 의해 위치가 텔레포터의 위치로 변경되었습니다.");
				}
				else
					player.sendMessage("플레이어가 온라인이 아닙니다.");
			}
			else
				player.sendMessage("타겟이 지정되지 않았습니다. (타겟 등록법 : /x <Player>)");
		}
	}
	
	public void targetSet(CommandSender sender, String targetName)
	{
		String playerTeamName = GameData.PlayerTeam.get(playerName);
		String targetTeamName = GameData.PlayerTeam.get(targetName);
		if (playerTeamName != null && playerTeamName.equals(targetTeamName))
		{
			if (!playerName.equals(targetName))
			{
				this.abilityTarget = targetName;
				sender.sendMessage("타겟을 등록했습니다.   "+ChatColor.GREEN+targetName);
			}
			else
				sender.sendMessage("자기 자신을 타겟으로 등록 할 수 없습니다.");
		}
		else
			sender.sendMessage("자신의 팀의 멤버가 아닙니다.");		
	}
}
