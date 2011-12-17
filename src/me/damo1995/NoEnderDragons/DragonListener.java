package me.damo1995.NoEnderDragons;

import org.bukkit.entity.EnderDragon;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityListener;

public class DragonListener extends EntityListener {

	public static NoEnderDragons plugin;
	
	public DragonListener (NoEnderDragons instance)
	{
		plugin = instance;
	}
	
	public void onEntityExplode(EntityExplodeEvent event) {
	if(event.getEntity() instanceof EnderDragon) {
		if(plugin.config.getBooliean("block-damage") == true){
			event.setCancelled(true);
		}
		else
			event.setCancelled(false);
	}
	
	}
	public void onCreatureSpawn(CreatureSpawnEvent event){
		if(event.getEntity() instanceof EnderDragon){
			if(plugin.config.getBooliean("block-dragons") == true){
				event.setCancelled(true);	
				if(event.getLocation().getWorld().getName().contains("the_end") && plugin.config.getBooliean("allow-end") == true){
					event.setCancelled(false);
				}
				else
					event.setCancelled(true);
			}
			else
				event.setCancelled(false);
		}
		
	}
	

}
