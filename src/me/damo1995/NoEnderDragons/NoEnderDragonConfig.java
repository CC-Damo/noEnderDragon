package me.damo1995.NoEnderDragons;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;



public class NoEnderDragonConfig {
	
	private YamlConfiguration config;
	private HashMap<String, Object> configDefaults = new HashMap<String, Object>();

	public NoEnderDragonConfig(File configFile){
		this.config = new YamlConfiguration();
		
		this.configDefaults.put("block-damage", true);
		this.configDefaults.put("block-dragons", true);
		this.configDefaults.put("allow-end", true);
		
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
		
		
}
	
