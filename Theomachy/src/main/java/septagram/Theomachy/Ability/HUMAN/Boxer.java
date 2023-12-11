package septagram.Theomachy.Ability.HUMAN;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;

public class Boxer extends Ability
{
	
	private final static String[] des= {
			   "복서는 빠른 주먹을 사용하는 능력입니다.",
			   ChatColor.YELLOW+"【패시브】 "+ChatColor.WHITE+"속성 가격",
			   "맨손 데미지의 딜레이가 사라집니다."};
	
	public Boxer(String playerName)
	{
		super(playerName,"복서", 5, false, true, false, des);
		Theomachy.log.info(playerName+abilityName);
		
		this.rank=4;
	}
	
	public void T_Passive(EntityDamageByEntityEvent event)
	{
		Player player = (Player) event.getDamager();
		if (player.getItemInHand().getType()== Material.AIR && player.getName().equals(this.playerName))
		{
			Player target = (Player) event.getEntity();
			if (!target.isBlocking())
				target.setNoDamageTicks(0);
		}
	}
	
}
