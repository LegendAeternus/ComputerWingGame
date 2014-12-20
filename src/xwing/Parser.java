/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;


import java.net.DatagramPacket;
import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author Michael
 */
public abstract class Parser {

    
    String ipSource;
    int portSource;
    
    
    enum PacketTypes {
        ConnectionEstablishedPacket,
        ShipCreationPacket,
        ShipMovePacket
    }
    
    

    static public Parser parsePacket(DatagramPacket packet) {
        
        SmartByteArray buffer = new SmartByteArray(packet.getData());
        
        switch(getPacketType(buffer)) {
            
            
            case ConnectionEstablishedPacket:
                ConnectionPacket data = new ConnectionPacket(buffer);
                if(data.source == ConnectionPacket.HOST)
                    System.out.println("FROM HOST");
                else if(data.source == ConnectionPacket.REMOTE)
                    System.out.println("FROM REMOTE");
                break;
            default:
                
                break;
            
        }
        return null;
        
        
    }
    
    static public PacketTypes getPacketType(SmartByteArray buffer) {
        return enumFromByte(buffer.readByte());
    }
    
    private static final Map<PacketTypes, Integer> typeToIntMap = new HashMap<PacketTypes, Integer>();
    static {
        for (PacketTypes type : PacketTypes.values()) {
          typeToIntMap.put(type,type.ordinal());
        }
    }
    
    private static final Map<Integer, PacketTypes> intToTypeMap = new HashMap<Integer, PacketTypes>();
    static {
        for (PacketTypes type : PacketTypes.values()) {
          intToTypeMap.put(type.ordinal(),type);
        }
    }
    public static PacketTypes enumFromByte(byte enumType) {
        PacketTypes type = intToTypeMap.get((int)enumType);
        return type;
    }
    public static byte byteFromEnum(PacketTypes enumType) {
        int type = typeToIntMap.get(enumType);
        return (byte)type;
    }

    
}
