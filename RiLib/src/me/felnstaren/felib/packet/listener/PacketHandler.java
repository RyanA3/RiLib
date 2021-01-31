package me.felnstaren.felib.packet.listener;

import org.bukkit.entity.Player;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

/**
 * INTERNAL
 * Handles packets sent/received from a specefic connection (player)
 * Creates a PacketEvent object containing the player involved in the
 * event and passes it to the PacketEventManager to be distributed to
 * all the registered PacketListeners
 */
public class PacketHandler extends ChannelDuplexHandler {
	
	private Player player;
	private PacketEventManager pman;
	
	public PacketHandler(PacketEventManager pman, Player player) {
		this.player = player;
		this.pman = pman;
	}
	
	
	
	/**
	 * Called when a packet is sent to the player
	 */
	@Override
	public void write(ChannelHandlerContext context, Object what, ChannelPromise promise) throws Exception {
		PacketEvent event = new PacketEvent(player, what);
		pman.callSendEvent(event);
		if(event.isCancelled() && !event.getPacket().getClass().getName().contains("Disconnect")) return;
		super.write(context, what, promise);
	}
	
	/**
	 * Called when a packet is received from the player
	 */
	@Override
	public void channelRead(ChannelHandlerContext context, Object what) throws Exception {
		PacketEvent event = new PacketEvent(player, what);
		pman.callReceiveEvent(event);
		if(event.isCancelled()) return;
		super.channelRead(context, what);
	}
	
}
