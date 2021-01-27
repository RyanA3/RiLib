package me.felnstaren.rilib.packet.listener;

import java.util.ArrayList;

/**
 * EXTERNAL
 * Register your PacketListeners here
 * Keeps track of all PacketListeners and feeds them the
 * appropriate PacketEvents when a PacketHandler invokes
 * a send/receive method due to its player sending/being
 * sent a packet.
 */
public class PacketEventManager {

	private final ArrayList<PacketListener> PACKET_OUT_LISTENER;
	private final ArrayList<PacketListener> PACKET_IN_LISTENER;
	
	public PacketEventManager() {
		PACKET_OUT_LISTENER = new ArrayList<PacketListener>();
		PACKET_IN_LISTENER = new ArrayList<PacketListener>();
	}
	
	
	
	public void registerInOut(PacketListener listener) {
		PACKET_OUT_LISTENER.add(listener);
		PACKET_IN_LISTENER.add(listener);
	}
	
	public void registerIn(PacketListener listener) {
		PACKET_IN_LISTENER.add(listener);
	}
	
	public void registerOut(PacketListener listener) {
		PACKET_OUT_LISTENER.add(listener);
	}
	
	public void unregister(PacketListener listener) {
		PACKET_OUT_LISTENER.remove(listener);
		PACKET_IN_LISTENER.remove(listener);
	}
	
	public void clear() {
		PACKET_OUT_LISTENER.clear();
		PACKET_IN_LISTENER.clear();
	}
	
	
	
	/**
	 * Called when a packet is sent to a player
	 */
	public void callSendEvent(PacketEvent event) {
		for(PacketListener l : PACKET_OUT_LISTENER) 
			l.onEvent(event);
	}
	
	/**
	 * Called when a packet is received from a player
	 */
	public void callReceiveEvent(PacketEvent event) {
		for(PacketListener l : PACKET_IN_LISTENER) 
			l.onEvent(event);
	}
	
}
