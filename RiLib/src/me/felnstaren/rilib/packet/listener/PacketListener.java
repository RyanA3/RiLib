package me.felnstaren.rilib.packet.listener;

/**
 * EXTERNAL
 * Contains the method you want to invoke when a packet is sent/received
 * Register to PacketEventManager
 * Make sure to add the proper annotations to the class
 *  @PacketInListener and/or @PacketOutListener)
 *  
 *  How to use PacketListener API
 *  1. Create a PacketInjector instance in your plugin's onEnable()
 *  2. Create a PacketListener to register
 *  3. Register it with PacketInjector#getManager()#register(PacketListener)
 *  4. ez
 */
public interface PacketListener {
	
	public void onEvent(PacketEvent event);
	
}
