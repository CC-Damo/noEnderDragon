package me.damo1995.NoEnderDragons;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;
//Obsolete//


public class NoEnderDragonConfig {
	
	private YamlConfiguration config;
	private HashMap<String, Object> configDefaults = new HashMap<String, Object>();

	public NoEnderDragonConfig(File configFile){
		this.config = new YamlConfiguration();
		
		this.configDefaults.put("block-damage", true);
		this.configDefaults.put("block-dragons", true);
		this.configDefaults.put("allow-end", true);
		this.configDefaults.put("allow-targetplayer", false);
		
		if(configFile.exists() == false){
			for (String key : this.configDefaults.keySet()){
				this.config.set(key, this.configDefaults.get(key));
			}
			
			try {
				this.config.save(configFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				this.config.load(configFile);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		public boolean getBooliean(String key){
			if(this.configDefaults.containsKey(key) == false){
				return false;
			}
			return this.config.getBoolean(key, (Boolean) this.configDefaults.get(key));
		}
		
		public static void start(){
			NoEnderDragons.plugin.getConfig().options().header("###############################\n" + 
					"Default Configuration File for noEnderDragon \n" +
					"All the values generated below are default and are fully editable.\n" +
					"Feel free to edit them as you please.\n" +
					"###############################\n");
			if(NoEnderDragons.plugin.getConfig().get("block-damage") == null){
				
				NoEnderDragons.plugin.getConfig().set("block-damage", true);
				NoEnderDragons.plugin.saveConfig();
				NoEnderDragons.plugin.logMessage("Created config Variable(block-damage)");
			}
			if(NoEnderDragons.plugin.getConfig().get("block-dragons", null) == null){
				NoEnderDragons.plugin.getConfig().set("block-dragons", true);
				NoEnderDragons.plugin.saveConfig();
				NoEnderDragons.plugin.logMessage("Created Config Variable(block-dragons)");
			}
			if(NoEnderDragons.plugin.getConfig().get("allow-end", null) == null){
				NoEnderDragons.plugin.getConfig().set("allow-end", true);
				NoEnderDragons.plugin.saveConfig();
				NoEnderDragons.plugin.logMessage("Created Config Variable(allow-end)");
			}
			if(NoEnderDragons.plugin.getConfig().get("alow-targetplayer", null) == null){
				NoEnderDragons.plugin.getConfig().set("allow-targetplayer", false);
				NoEnderDragons.plugin.saveConfig();
				NoEnderDragons.plugin.logMessage("Created Config Variable(allow-targetplayer)");
			}
		}
		
		
}
	
