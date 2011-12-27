package me.damo1995.NoEnderDragons;

import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NoEnderDragons extends JavaPlugin {
	
	//Setup Listeners//
	public final Logger log = Logger.getLogger("Minecraft"); //Logger//
	public final DragonListener DragonListen = new DragonListener(this);//Listener for dragon spawning & block damage Events(ENTITY_EXPLODE)//
	public final DragonTarget DragonTarget = new DragonTarget(this); //Listener for the dragon targeting players//
	
	//Config Defaults//
//	public boolean block_damage = true; //Sets the block_damage Config variable to default to true// //
//	public boolean block_dragons = true;//Sets the Block_Dragons Config Variable to default to true// //
//	public boolean allow_end = true;    //Sets the allow_end Config Variable to default to true// //
//	public boolean allow_targetplayer = false; //Sets the allow_targetplayer Config Variable to default to false// //
	
	
	public static NoEnderDragons plugin;
	private NoEnderDragonCommandExec CommandExec;
	
	//ENABLE//
	public void onEnable(){
		
		//Register the events.//
		PluginManager pm = getServer().getPluginManager(); //get plugin manager//
		pm.registerEvent(Event.Type.ENTITY_EXPLODE, this.DragonListen, Priority.Highest, this); //register enderdragon block damage//
		pm.registerEvent(Event.Type.CREATURE_SPAWN, this.DragonListen, Priority.Highest, this);// register enderdragon spawn//
		pm.registerEvent(Event.Type.ENTITY_TARGET, this.DragonTarget, Priority.Highest, this);// register enderdragon target player//
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, new EntityListener() { //register enderdragon damage player//

			public void onEntityDamage(EntityDamageEvent event) {
                if (event instanceof EntityDamageByEntityEvent) {
                    Entity playerA = ((EntityDamageByEntityEvent) event).getDamager();
                        if (playerA instanceof EnderDragon) {
                        	if(getConfig().getBoolean("allow-targetplayer") == false){
                            if (event.getEntity() instanceof Player)
                            if (event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof Player))
                                return;
                            event.setDamage(0);
                        }
                        	else{return;}

                    }
                }
			}
        }, Priority.Highest, this);
		plugin = this;
		this.CommandExec = new NoEnderDragonCommandExec(this);
		
		//Setup Configuration. //
		final FileConfiguration cfg = getConfig();
		FileConfigurationOptions cfgOptions = cfg.options();
		getConfig().addDefault("block-damage", true);
		getConfig().addDefault("block-dragons", true);
		getConfig().addDefault("allow-end", true);
		getConfig().addDefault("allow-targetplayer", true);
		cfgOptions.copyDefaults(true);
		cfgOptions.header("Default Config for noEnderDragon \n use the values below to change what you wish");
		cfgOptions.copyHeader(true);
		saveConfig();
		
		//Setup Command executors//
		this.getCommand("ned").setExecutor(this.CommandExec);
		
		//Show config settings in console on startup//
		if(getConfig().getBoolean("block-damage") == false){
			this.logMessage("Block Damage damage is Disabled");
		}
		else
		{
			this.logMessage("Block Damage is Enabled!");
		}
		if(getConfig().getBoolean("block-dragons") == true){
			this.logMessage("EnderDragons are Blocked from spawning");
		}
		else
		{
			this.logMessage("EnderDragons can spawn as normal");
		}
		if(getConfig().getBoolean("allow-end") == true){
			this.logMessage("EnderDragons can spawn in the end!");
		}
		else
		{
			this.logMessage("EnderDragons are Disabled in the end");
		}
		this.logMessage("Enabled");
		
	}
	//DISABLE//
	public void onDisable(){
		this.logMessage("Disabled");
		
	}
	
	//Setup the this.logMessage Feature//
	protected void logMessage(String msg){
		PluginDescriptionFile pdFile = this.getDescription();
		this.log.info(pdFile.getName() + " " + pdFile.getVersion() + ": " +msg);
		
	}

}
