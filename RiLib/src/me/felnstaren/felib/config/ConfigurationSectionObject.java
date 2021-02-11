package me.felnstaren.felib.config;

import org.bukkit.configuration.ConfigurationSection;

public interface ConfigurationSectionObject {

	public ConfigurationSectionObject load(ConfigurationSection data);
	public ConfigurationSectionObject template();
	
}
