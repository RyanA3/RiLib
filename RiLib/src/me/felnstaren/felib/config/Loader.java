package me.felnstaren.felib.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.felnstaren.felib.logger.Level;
import me.felnstaren.felib.logger.Logger;

public class Loader {
	
	private JavaPlugin plugin;
	private Logger logger;
	private File data_folder;
	
	public Loader(JavaPlugin plugin, Logger logger) {
		this.plugin = plugin;
		this.logger = logger;
		this.data_folder = plugin.getDataFolder();
	}
	
	
	
	/**
	 * Reads raw data from a file
	 * @param file
	 * @return
	 */
	private byte[] read(File file) {
		if(!file.exists()) create(file);
		
		byte[] buffer;
		try {
			InputStream initial_stream = new FileInputStream(file);
			buffer = new byte[initial_stream.available()];
			initial_stream.read(buffer);
			initial_stream.close();
			logger.log(Level.DEBUG, "File.READ " + file.getPath());
		} catch (Exception e) {
			e.printStackTrace();
			buffer = new byte[] {};
			logger.log(Level.WARNING, "ERROR - Read File " + file.getPath());
		}
		
		return buffer;
	}
	
	/**
	 * Writes raw data to a file
	 * @param file
	 * @param data
	 */
	private void write(File file, byte[] data) {
		if(!file.exists()) create(file);
		
		try {
			FileOutputStream out_stream = new FileOutputStream(file);
			out_stream.write(data);
			out_stream.close();
			logger.log(Level.DEBUG, "File.WRITE " + file.getPath());
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.WARNING, "ERROR - Write File " + file.getPath());
		}
	}
	
	
	
	/**
	 * Copy one file's data to another's
	 * @param The file copied to
	 * @param The file copied from
	 * @return
	 */
	public File copy(File copy, File template) {
		write(copy, read(template));		
		logger.log(Level.DEBUG, "COPY " + copy.getPath() + " TO " + template);
		return copy;
	}
	
	
	
	/**
	 * Safely attempt to create a file
	 * @param file
	 * @return
	 */
	public boolean create(File file) {
		if(file.exists()) return true;
		
		try { 
			file.createNewFile(); 
			logger.log(Level.DEBUG, "CREATE " + file.getPath());
			return true;
		} 
		catch (IOException e) { 
			e.printStackTrace(); 
			logger.log(Level.SEVERE, "ERROR - Creating File " + file.getPath());
			return false;
		}
	}
	
	/**
	 * Safely attempt to delete a file
	 * @param file
	 * @return
	 */
	public boolean delete(File file) {
		if(!file.exists()) return true;
		
		try {
			file.delete();
			logger.log(Level.DEBUG, "DELETE " + file.getPath());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "ERROR - Deleting File " + file.getPath());
			return false;
		}
	}
	
	
	
	/**
	 * Safely attempt to write a raw string to a file
	 * @param file
	 * @param data
	 */
	public void save(File file, String data) {
		write(file, data.getBytes());
	}
	
	/**
	 * Safely attempt to write a yaml config to a file
	 * @param file
	 * @param config
	 */
	public void save(File file, YamlConfiguration config) {
		try { 
			config.save(file); 
			logger.log(Level.DEBUG, "SAVED " + file.getPath());
		} 
		catch (IOException e) { 
			e.printStackTrace(); 
			logger.log(Level.SEVERE, "ERROR - saving YAML " + file.getPath());
		}
	}
	
	
	
	/**
	 * Create a file if it doesn't exist
	 * @param file
	 * @return
	 */
	public File mark(File file) {
		if(!file.exists()) create(file);
		return file;
	}
	
	/**
	 * Create a file from a template if it doesn't exist
	 * @param file
	 * @param template
	 * @return
	 */
	public File mark(File file, File template) {
		if(!file.exists()) copy(file, template);
		return file;
	}
	
	
	
	/**
	 * Get a raw resource file from within the plugin jar
	 * @param relative_path
	 * @return
	 */
	public File resource(String relative_path) {
		return new File(plugin.getClass().getResource(relative_path).getPath());
	}
	
	/**
	 * Get a raw file from the plugin data folder
	 * @param relative_path
	 * @return
	 */
	public File datafile(String relative_path) {
		return new File(data_folder, relative_path);
	}
	
	
	
	
	
	
	
	
	/* ============================
	 * | INFERRED IMPLEMENTATIONS |
	 * ============================
	 * These methods use files from directories inferred by their function
	 * 
	 * Definition:
	 *  Data File     - A file found in the plugin's data folder, these files
	 *                  are meant to be edited and changed
	 *  Resource File - A file found in the plugin's source code or jar folder,
	 *                  these files are not meant to be edited, but rather used
	 *                  as templates for creating other files, or for other uses
	 *                  
	 * Example: 
	 *   You usually only need to save data to a plugin data file, so
	 *  the save function will load a data file instead of a resource
	 *  file, which is in contrast meant to act as a template file and
	 *  not meant to be saved to
	 */
	
	
	
	/**
	 * Save raw string data to a plugin data file
	 * @param path     - inferred data file
	 * @param data to save
	 */
	public void save(String path, String data) {
		save(mark(datafile(path)), data);
	}
	
	/**
	 * Save YAML config to a plugin data file
	 * @param path     - inferred data file
	 * @param config to save
	 */
	public void save(String path, YamlConfiguration config) {
		save(mark(datafile(path)), config);
	}
	
	
	
	/**
	 * Gets a plugin data file, creates it if it doesn't exist
	 * @param path     - inferred data file
	 * @return
	 */
	public File load(String path) {
		return mark(datafile(path));
	}
	
	/**
	 * Gets a plugin data file, creates it from a plugin resource file if it doesn't exist
	 * @param path     - inferred data file
	 * @param template - inferred resouce file
	 * @return
	 */
	public File load(String path, String template) {
		return mark(datafile(path), resource(template));
	}
	
	
	
	/**
	 * Deletes a plugin data file
	 * @param path     - inferred data file
	 * @return
	 */
	public boolean delete(String path) {
		return delete(datafile(path));
	}
	
	/**
	 * Creates a plugin data file
	 * @param path     - inferred data file
	 * @return
	 */
	public boolean create(String path) {
		return create(datafile(path));
	}
	
	
	
	/**
	 * Create the specified folders if they do not exist
	 * @param paths    - inferred data files
	 */
	public void mkDirs(String... paths) {
		logger.log(Level.DEBUG, "MARK " + paths.toString());
		
		if (!data_folder.exists()) data_folder.mkdirs(); 
		for(String path : paths) {
			File file = new File(data_folder, path);
			if(!file.exists()) file.mkdirs();
		}
	}
	
	
	
	/**
	 * Safely load a YAML config from a data file, or create the data file from a specified template resource
	 * @param name     - inferred data file
	 * @param template - inferred resource file
	 * @return
	 */
	public YamlConfiguration readConfig(String name, String template) {
		File file;
		if(template == null) file = mark(datafile(name));
		else file = mark(datafile(name), resource(template));
		return YamlConfiguration.loadConfiguration(file);
	}
	
	/**
	 * Safely read a raw string from a data file, or create the data file from a specified template resource
	 * @param name     - inferred data file
	 * @param template - inferred resource file
	 * @return
	 */
	public String readString(String name, String template) {
		File file;
		if(template == null) file = mark(datafile(name));
		else file = mark(datafile(name), resource(template));
		return new String(read(file), StandardCharsets.US_ASCII);
	}
	
}
