package me.felnstaren.rilib.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.felnstaren.rilib.logger.Level;
import me.felnstaren.rilib.logger.Logger;

public class Loader {
	
	private JavaPlugin plugin;
	private Logger logger;
	private File data_folder;
	
	public Loader(JavaPlugin plugin, Logger logger) {
		this.plugin = plugin;
		this.logger = logger;
		this.data_folder = plugin.getDataFolder();
	}
	
	public YamlConfiguration readConfig(String name, String defalt) {
		File file = new File(data_folder, name);
		if(file.exists()) return YamlConfiguration.loadConfiguration(file);

		if(!create(file)) return null;
		if(defalt != null) copy(file, defalt);
		
		return YamlConfiguration.loadConfiguration(file);
	}
	
	public String readData(String name, String defalt) {
		File file = new File(data_folder, name);
		if(!file.exists()) {
			if(!create(file)) return null;
			copy(file, defalt);
		}
		
		try {
			InputStream initial_stream = new FileInputStream(file);
			byte[] buffer = new byte[initial_stream.available()];
			initial_stream.read(buffer);
			initial_stream.close();
			return new String(buffer, StandardCharsets.US_ASCII);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public File load(String name) {
		File file = new File(data_folder, name);
		if(!file.exists()) create(file);
		return file;
	}
		
	private File copy(File copy, String original) {
		if(original == null) return copy;

		try {
			InputStream initial_stream = plugin.getClass().getResourceAsStream("resources/" + original);
			byte[] buffer = new byte[initial_stream.available()];
			initial_stream.read(buffer);
			initial_stream.close();
				
			FileOutputStream out_stream = new FileOutputStream(copy);
			out_stream.write(buffer);
			out_stream.close();
				
			logger.log(Level.DEBUG, "Copied file; " + copy.getPath() + " from plugin resource file; " + original);
		} catch (Exception e) { 
			e.printStackTrace(); 
			logger.log(Level.SEVERE, "An error occured while copying to this file; " + copy.getPath());
			return null;
		}
		
		return copy;
	}
	
	
	
	public boolean create(File file) {
		try { 
			file.createNewFile(); 
			logger.log(Level.DEBUG, "Created file; " + file.getPath());
			return true;
		} 
		catch (IOException e) { 
			e.printStackTrace(); 
			logger.log(Level.SEVERE, "A fatal error occured while creating this file; " + file.getPath());
			return false;
		}
	}
	
	public boolean delete(String path) {
		try {
			File file = new File(data_folder, path);
			file.delete();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "A fatal error occured while deleting this file; " + path);
			return false;
		}
	}
	
	
	
	public void save(String data, String name) {
		save(data, new File(data_folder, name));
	}
	
	public void save(YamlConfiguration config, String name) {
		save(config, new File(data_folder, name));
	}
	
	public void save(String data, File file) {
		try {
			FileOutputStream out_stream = new FileOutputStream(file);
			out_stream.write(data.getBytes());
			out_stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save(YamlConfiguration config, File file) {
		try { 
			config.save(file); 
			logger.log(Level.DEBUG, "Saved file; " + file.getPath());
		} 
		catch (IOException e) { 
			e.printStackTrace(); 
			logger.log(Level.WARNING, "An error occured saving this file; " + file.getPath());
		}
	}
	
	
	
	public boolean exists(String path) {
		return new File(data_folder, path).exists();
	}
	
	public void mkDirs(String... paths) {
		logger.log(Level.DEBUG, "Marking non-existant directories");
		
		if (!data_folder.exists()) data_folder.mkdirs(); 
		for(String path : paths) {
			File file = new File(data_folder, path);
			if(!file.exists()) file.mkdirs();
		}
	}
	
}
