package org.Theomachy.Timer;

import java.util.TimerTask;

import org.Theomachy.Handler.Module.GameModule;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.Theomachy.Theomachy;

public class TipTimer extends TimerTask
{
	int count=1;

	@Override
	public void run()
	{
		if (!GameModule.Ready){
			this.cancel();
		}
		if (count%600 == 0)
		{
			if (Theomachy.SERVER_AUTO_SAVE)
			{
				Bukkit.getServer().savePlayers();
				Theomachy.log.info("[신들의전쟁] 오토세이브 완료");
			}
			count = 1;
		}
		if (count % 300 == 0)
		{
			Bukkit.broadcastMessage("[신들의전쟁] 오토세이브 완료");
		}
		if (count % 60 == 0)
		{
			long max = (int) (Runtime.getRuntime().maxMemory() / 1048576);
			long free = (int) (Runtime.getRuntime().freeMemory() / 1048576);
			long use = max - free;
			Bukkit.broadcastMessage(ChatColor.WHITE+"메모리(MB)   "+ChatColor.AQUA+String.valueOf(use)+ChatColor.WHITE+" / "+ChatColor.YELLOW+String.valueOf(max));
			
			if (free < 3750)
			{
				Theomachy.log.info("메모리 부족, 메모리 청소중...");
				System.gc();
				long free2 = (Runtime.getRuntime().freeMemory() / 1048576);
				Theomachy.log.info("현재 사용 가능 메모리 : "+free2+"MB");
			}
		}
		count++;
	}
}
