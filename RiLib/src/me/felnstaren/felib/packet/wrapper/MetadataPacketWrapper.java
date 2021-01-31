package me.felnstaren.felib.packet.wrapper;

import java.util.List;

import me.felnstaren.felib.reflect.Reflector;

public class MetadataPacketWrapper extends PacketWrapper {

	public MetadataPacketWrapper(Object packet) {
		super(packet);
	}
	
	public List<?> getData() {
		return (List<?>) Reflector.getDeclaredField(packet, "b");
	}
	
	public Object getDataItem() {
		List<?> data = getData();
		for(Object o : data) {
			try { 
				Object item_object = Reflector.getDeclaredField(o, "b");
				if(item_object instanceof Byte) return o;
			} catch (Exception e) { continue; }
		}
			//return data.get(0);
		return null;
	}
	
	public byte getDataItemByte() {
		Object item = getDataItem();
		if(item == null) return -1;
		return (byte) Reflector.getDeclaredField(item, "b");
	}
	
	public void setDataItemByte(byte value) {
		Object item = getDataItem();
		Reflector.setDeclaredField(item, "b", value);
	}
	
	public void setByteProperty(int shift, int value) {
		byte properties = getDataItemByte();
		if(properties == -1) return;
		properties += (byte) (value << shift);
		setDataItemByte(properties);
	}
	
	//https://wiki.vg/Entity_metadata#Entity
	public void addByteProperty(byte property) {
		byte properties = getDataItemByte();
		if(properties == -1) return;
		properties += property;
		setDataItemByte(properties);
	}

}
