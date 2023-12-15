package org.septagram.Theomachy.Handler.CommandModule;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.septagram.Theomachy.Ability.Ability;
import org.septagram.Theomachy.Ability.ENUM.AbilityInfo;
import org.septagram.Theomachy.Ability.GOD.*;
import org.septagram.Theomachy.Ability.HUMAN.*;
import org.septagram.Theomachy.DB.GameData;
import org.septagram.Theomachy.Utility.CodeHelper;
import org.septagram.Theomachy.Utility.PermissionChecker;
import org.septagram.Theomachy.Utility.RandomNumberConstructor;

import java.util.ArrayList;
import java.util.List;

public class AbilitySet
{
	public static void Module(CommandSender sender, Command command, String label, String[] data)
	{
		if (PermissionChecker.Sender(sender))
		{
			if (!GameHandler.Ready)
			{
				if (data.length<=1)
				{
					sender.sendMessage("/t a help   모든 능력의 코드를 확인합니다.");
					sender.sendMessage("/t a random 현재 접속한 모든 플레이어에게 랜덤으로 능력을 할당합니다.");
					sender.sendMessage("/t a remove <Player> 해당 플레이어의 능력을 삭제합니다.");
					sender.sendMessage("/t a reset  모든 능력을 초기화 합니다");
					sender.sendMessage("/t a <AbilityCode> <Player>  플레이어에게 해당 능력을 적용합니다.");
				}
				else if (data[1].equalsIgnoreCase("help"))
					CodeHelper.ShowAbilityCodeNumber(sender);
				else if (data[1].equalsIgnoreCase("remove"))//삭제
				{
					if (data[2] != null)
						Remove(sender, data[2]);
					else
						sender.sendMessage("능력을 삭제 할 플레이어의 이름을 적어주세요.");
				}
				else if (data[1].equalsIgnoreCase("reset"))//리셋
					Reset();
				else if (data[1].equalsIgnoreCase("random"))//랜덤
					RandomAssignment(sender);
				else if (data.length >= 3)
					forceAssignment(sender, data);	
				else
				{
					sender.sendMessage("잘못된 입력입니다.");
					sender.sendMessage("/t a 로 명령어를 확인하세요.");
				}
			}
			else
				sender.sendMessage("게임 시작 후에는 능력을 변경 할 수 없습니다.");
		}
	}
	
	public static void Remove(CommandSender sender, String playerName)
	{
		Ability ability = GameData.PlayerAbility.get(playerName);
		if (ability != null)
		{
			GameData.PlayerAbility.remove(playerName);
			sender.sendMessage("플레이어의 능력을 삭제하였습니다.");
		}
		else
			sender.sendMessage("플레이어의 능력이 없습니다.");
	}
	
	public static void Reset()
	{
		GameData.PlayerAbility.clear();
		Bukkit.broadcastMessage(ChatColor.AQUA+"관리자가 모두의 능력을 초기화 하였습니다.");
	}
	
	private static void RandomAssignment(CommandSender sender)
	{

        if (!GameData.PlayerAbility.isEmpty())
		{
			Bukkit.broadcastMessage("모든 능력을 삭제한 후 재 추첨합니다.");
			GameData.PlayerAbility.clear();
		}
		List<Player> playerlist= new ArrayList<>( Bukkit.getOnlinePlayers());
		Bukkit.broadcastMessage(ChatColor.DARK_AQUA+"인식된 플레이어 목록");
		for(Player e : playerlist)
			Bukkit.broadcastMessage(ChatColor.GOLD+"  "+e.getName());
		int[] rn = RandomNumberConstructor.nonDuplicate();
		int length;
		length = Math.min(playerlist.size(), Blacklist.availableList);
		int i = 0;
		for (Player player: playerlist)
		{
			String playerName = player.getName();
			abilityAssignment(rn[i++],playerName, (Player) sender);
		}
		
		Bukkit.broadcastMessage("모두에게 능력이 적용되었습니다.");
		Bukkit.broadcastMessage("/t help 로 확인해보세요.");
		if (length!=playerlist.size())
			Bukkit.broadcastMessage("인원이 너무 많습니다. 전부에게 능력을 할당하지 못했을수도 있습니다.");
	}
	
	private static void forceAssignment(CommandSender sender, String[] data)
	{
		
		Player p=(Player) sender;
		
		for (int i=2; i<data.length; i++)
		{
		String abilityName = data[1];
		String playerName=data[i];
		if (GameData.OnlinePlayer.containsKey(playerName))
		{
			try{
				int abilityCode = Integer.parseInt(abilityName);
				abilityAssignment(abilityCode, playerName, p);
				Player player = GameData.OnlinePlayer.get(playerName);
				Bukkit.broadcastMessage("관리자가 "+ChatColor.RED+playerName+ChatColor.WHITE+" 에게 능력을 할당하였습니다.");
				player.sendMessage("능력이 할당되었습니다. /t help로 능력을 확인해보세요.");
			}
			catch (NumberFormatException e)
			{sender.sendMessage("능력코드는 정수를 입력해 주세요");}
		}
		else
			sender.sendMessage(playerName+" 에 해당하는 온라인 유저가 없습니다.");
		}
	}
	
	public static void abilityAssignment(int abilityNumber, String playerName, CommandSender p)
	{
		if (abilityNumber == AbilityInfo.Zeus.getIndex())
			GameData.PlayerAbility.put(playerName, new Zeus(playerName));
		else if (abilityNumber == AbilityInfo.Poseidon.getIndex())
			GameData.PlayerAbility.put(playerName, new Poseidon(playerName));
		else if (abilityNumber == AbilityInfo.Hades.getIndex())
			GameData.PlayerAbility.put(playerName, new Hades(playerName));
		else if (abilityNumber == AbilityInfo.Demeter.getIndex())
			GameData.PlayerAbility.put(playerName, new Demeter(playerName));
		else if (abilityNumber == AbilityInfo.Athena.getIndex())
			GameData.PlayerAbility.put(playerName, new Athena(playerName));
		else if (abilityNumber == AbilityInfo.Apollon.getIndex())
			GameData.PlayerAbility.put(playerName, new Apollon(playerName));
		else if (abilityNumber == AbilityInfo.Artemis.getIndex())
			GameData.PlayerAbility.put(playerName, new Artemis(playerName));
		else if (abilityNumber == AbilityInfo.Ares.getIndex())
			GameData.PlayerAbility.put(playerName, new Ares(playerName));
		else if (abilityNumber == AbilityInfo.Hephastus.getIndex())
			GameData.PlayerAbility.put(playerName, new Hephaestus(playerName));
		else if (abilityNumber == AbilityInfo.Asclepius.getIndex())
			GameData.PlayerAbility.put(playerName, new Asclepius(playerName));
		else if (abilityNumber == AbilityInfo.Hermes.getIndex())
			GameData.PlayerAbility.put(playerName, new Hermes(playerName));
		else if (abilityNumber == AbilityInfo.Dionysus.getIndex())
			GameData.PlayerAbility.put(playerName, new Dionysus(playerName));
		else if (abilityNumber == AbilityInfo.Aprodite.getIndex())
			GameData.PlayerAbility.put(playerName, new Aprodite(playerName));
		else if (abilityNumber == AbilityInfo.Eris.getIndex())
			GameData.PlayerAbility.put(playerName, new Eris(playerName));
		else if (abilityNumber == AbilityInfo.Morpious.getIndex())
			GameData.PlayerAbility.put(playerName, new Morpious(playerName));
		else if (abilityNumber == AbilityInfo.Aeolus.getIndex())
			GameData.PlayerAbility.put(playerName, new Aeolus(playerName));
		else if (abilityNumber == AbilityInfo.Akasha.getIndex())
			GameData.PlayerAbility.put(playerName, new Akasha(playerName));
		else if (abilityNumber == AbilityInfo.Horeundal.getIndex())
			GameData.PlayerAbility.put(playerName, new Horeundal(playerName));
		else if (abilityNumber == AbilityInfo.Sans.getIndex())
			GameData.PlayerAbility.put(playerName, new Sans(playerName));
		
		else if (abilityNumber == AbilityInfo.Archer.getIndex())
			GameData.PlayerAbility.put(playerName, new Archer(playerName));
		else if (abilityNumber == AbilityInfo.Miner.getIndex())
			GameData.PlayerAbility.put(playerName, new Miner(playerName));
		else if (abilityNumber == AbilityInfo.Stance.getIndex())
			GameData.PlayerAbility.put(playerName, new Stance(playerName));
		else if (abilityNumber == AbilityInfo.Teleporter.getIndex())
			GameData.PlayerAbility.put(playerName, new Teleporter(playerName));
		else if (abilityNumber == AbilityInfo.Bomber.getIndex())
			GameData.PlayerAbility.put(playerName, new Bomber(playerName));
		else if (abilityNumber == AbilityInfo.Creeper.getIndex())
			GameData.PlayerAbility.put(playerName, new Creeper(playerName));
		else if (abilityNumber == AbilityInfo.Wizard.getIndex())
			GameData.PlayerAbility.put(playerName, new Wizard(playerName));
		else if (abilityNumber == AbilityInfo.Assasin.getIndex())
			GameData.PlayerAbility.put(playerName, new Assasin(playerName));
		else if (abilityNumber == AbilityInfo.Reflection.getIndex())
			GameData.PlayerAbility.put(playerName, new Reflection(playerName));
		else if (abilityNumber == AbilityInfo.Blinder.getIndex())
			GameData.PlayerAbility.put(playerName, new Blinder(playerName));
		else if (abilityNumber == AbilityInfo.Invincibility.getIndex())
			GameData.PlayerAbility.put(playerName, new Invincibility(playerName));
		else if (abilityNumber == AbilityInfo.Clocking.getIndex())
			GameData.PlayerAbility.put(playerName, new Clocking(playerName));
		else if (abilityNumber == AbilityInfo.BlackSmith.getIndex())
			GameData.PlayerAbility.put(playerName, new Blacksmith(playerName));
		else if (abilityNumber == AbilityInfo.Boxer.getIndex())
			GameData.PlayerAbility.put(playerName, new Boxer(playerName));
		else if (abilityNumber == AbilityInfo.Priest.getIndex())
			GameData.PlayerAbility.put(playerName, new Priest(playerName));
		else if (abilityNumber == AbilityInfo.Witch.getIndex())
			GameData.PlayerAbility.put(playerName, new Witch(playerName));
		else if (abilityNumber == AbilityInfo.Meteor.getIndex())
			GameData.PlayerAbility.put(playerName, new Meteor(playerName));
		else if (abilityNumber == AbilityInfo.Sniper.getIndex())
			GameData.PlayerAbility.put(playerName, new Sniper(playerName));
		else if (abilityNumber == AbilityInfo.Voodoo.getIndex())
			GameData.PlayerAbility.put(playerName, new Voodoo(playerName));
		else if (abilityNumber == AbilityInfo.Anorexia.getIndex())
			GameData.PlayerAbility.put(playerName, new Anorexia(playerName));
		else if (abilityNumber == AbilityInfo.Bulter.getIndex())
			GameData.PlayerAbility.put(playerName, new Bulter(playerName));
		else if (abilityNumber == AbilityInfo.Midoriya.getIndex())
			GameData.PlayerAbility.put(playerName, new Midoriya(playerName));
		else if (abilityNumber == AbilityInfo.Goldspoon.getIndex())
			GameData.PlayerAbility.put(playerName, new Goldspoon(playerName));
		else if (abilityNumber == AbilityInfo.Bee.getIndex())
			GameData.PlayerAbility.put(playerName, new Bee(playerName));
		else if (abilityNumber == AbilityInfo.Snow.getIndex())
			GameData.PlayerAbility.put(playerName, new Snow(playerName));
		else if (abilityNumber == AbilityInfo.Tajja.getIndex())
			GameData.PlayerAbility.put(playerName, new Tajja(playerName));
		else if (abilityNumber == AbilityInfo.AGirl.getIndex())
			GameData.PlayerAbility.put(playerName, new AGirl(playerName));
		else if (abilityNumber == AbilityInfo.PokeGo.getIndex())
			GameData.PlayerAbility.put(playerName, new PokeGo(playerName));
		else if (abilityNumber == AbilityInfo.Tanker.getIndex())
			GameData.PlayerAbility.put(playerName, new Tanker(playerName));
		else if (abilityNumber == AbilityInfo.Gasolin.getIndex())
			GameData.PlayerAbility.put(playerName, new Gasolin(playerName));
		else if (abilityNumber == AbilityInfo.Zet.getIndex())
			GameData.PlayerAbility.put(playerName, new Zet(playerName));
		else
		{
			p.sendMessage("능력 혹은 능력 코드 번호를 잘못 입력하셨습니다.");
			p.sendMessage("/t a help 명령어로 능력 코드를 확인하실 수 있습니다.");
		}
	}
}