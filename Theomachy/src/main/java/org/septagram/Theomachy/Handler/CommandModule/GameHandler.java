package org.septagram.Theomachy.Handler.CommandModule;

import java.util.Collection;
import java.util.List;
import java.util.Timer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

import org.septagram.Theomachy.Ability.Ability;
import org.septagram.Theomachy.DB.GameData;
import org.septagram.Theomachy.DB.ServerSetting;
import org.septagram.Theomachy.Theomachy;
import org.septagram.Theomachy.Timer.CoolTime;
import org.septagram.Theomachy.Timer.GameReadyTimer;
import org.septagram.Theomachy.Timer.TipTimer;
import org.septagram.Theomachy.Utility.PermissionChecker;

public class GameHandler
{
	public static boolean Ready=false;
	public static boolean Start=false;
	
	public static void GameReady(CommandSender sender)
	{
		if (PermissionChecker.Sender(sender))
		{
			if (!Ready)
			{
				Ready=true;
				Bukkit.broadcastMessage(ChatColor.GOLD+"관리자("+sender.getName()+") 가 게임을 시작하였습니다.");
				Timer t = new Timer();
				if(!Theomachy.FAST_START)
					t.schedule(new GameReadyTimer(), 0,1000);
				else 
					t.schedule(new GameReadyTimer(), 0,100);
				t.schedule(new TipTimer(), 0,1000);
				t.schedule(new CoolTime(), 0,1000);
			}
			else
				sender.sendMessage("게임이 이미 시작되었습니다.");
		}
	}
	
	public static void GameStop(CommandSender sender)
	{
		if (PermissionChecker.Sender(sender))
		{
			if (Ready)
			{
				Collection<Ability> playerAbilityList = GameData.PlayerAbility.values();
				for (Ability e : playerAbilityList)
					e.conditionReSet();
				Ready=false;
				Start=false;
				CoolTime.ini=true;
				List<World> worlds = Bukkit.getWorlds();
				for (World world : worlds)
				{
					world.setPVP(ServerSetting.PVP);
					world.setSpawnFlags(ServerSetting.MONSTER, ServerSetting.ANIMAL);
					world.setAutoSave(ServerSetting.AUTO_SAVE);
					world.setDifficulty(ServerSetting.DIFFICULTY);
				}
				Bukkit.broadcastMessage(ChatColor.RED+"관리자("+sender.getName()+") 가 게임을 종료하였습니다.");
			}
			else
				sender.sendMessage("게임이 시작되지 않았습니다.");
		}
	}
	
	
	
	
	

}
