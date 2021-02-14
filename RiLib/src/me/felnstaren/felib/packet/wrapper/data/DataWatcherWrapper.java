package me.felnstaren.felib.packet.wrapper.data;

import me.felnstaren.felib.reflect.Reflector;

public class DataWatcherWrapper {

	private Object watcher;
	
	public DataWatcherWrapper(Object entity) {
		this.watcher = Reflector.newInstanceOf("DataWatcher", new String[]{ "Entity" }, new Object[] { entity });
	}
	
	
}
