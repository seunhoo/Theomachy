package org.Theomachy.Handler.Manager;


import org.Theomachy.Handler.Event.*;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;

public class EventManager {
    public void settingEvent(Server server , Plugin plugin){
        server.getPluginManager().registerEvents(new BlackListEvent(), plugin);
        server.getPluginManager().registerEvents(new MenuEvent(), plugin);
        server.getPluginManager().registerEvents(new BlockEvent(), plugin);
        server.getPluginManager().registerEvents(new DamageEvent(), plugin);
        server.getPluginManager().registerEvents(new ExplodeEvent(), plugin);
        server.getPluginManager().registerEvents(new PlayerEvent(), plugin);
    }
}
