package me.damo1995.NoEnderDragons;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NoEnderDragons extends JavaPlugin {
	
	public final Logger log = Logger.getLogger("Minecraft");
	public final DragonListener DragonListen = new DragonListener(this);
	
	public String pluginDirPath;
	public File configFile;
	public NoEnderDragonConfig config;
	
	public void onEnable(){
		//Register the events.//
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.ENTITY_EXPLODE, this.DragonListen, Priority.Highest, this);
		pm.registerEvent(Event.Type.CREATURE_SPAWN, this.DragonListen, Priority.Highest, this);
		
		//Setup Configuration. //
		this.pluginDirPath = this.getDataFolder().getAbsolutePath();
		this.configFile = new File(this.pluginDirPath + File.separator + "config.yml");
		this.config = new NoEnderDragonConfig(this.configFile);
		
		if(this.config.getBooliean("block-damage") == false){
			this.logMessage("Block Damage damage is Disabled");
		}
		else
		{
			this.logMessage("Block Damage is Enabled!");
		}
		if(this.config.getBooliean("block-dragons") == true){
			this.logMessage("EnderDragons are Blocked from spawning");
		}
		else
		{
			this.logMessage("EnderDragons can spawn as normal");
		}
		if(this.config.getBooliean("allow-end") == true){
			this.logMessage("EnderDragons can spawn in the end!");
		}
		else
		{
			this.logMessage("EnderDragons are Disabled in the end");
		}
		this.logMessage("Enabled");
		
	}
	
	public void onDisable(){
		this.logMessage("Disabled");
		
	}
	
	protected void logMessage(String msg){
		PluginDescriptionFile pdFile = this.getDescription();
		this.log.info(pdFile.getName() + " " + pdFile.getVersion() + ": " +msg);
		
	}

}
