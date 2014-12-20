/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;


import java.net.DatagramPacket;
import static xwing.Parser.PacketTypes.ConnectionEstablishedPacket;
import static xwing.Parser.getPacketType;



/**
 *
 * @author Michael
 */
public class ConnectionPacket extends Parser {
    
    static PacketTypes thisType = ConnectionEstablishedPacket;
    char source; //0=host, 1=remote
    final static char HOST = 0;
    final static char REMOTE = 1;
    
    
    static public Parser parsePacket(DatagramPacket packet) {

        SmartByteArray buffer = new SmartByteArray(packet.getData());
        
        if(getPacketType(buffer) == thisType) {
            ConnectionPacket data = new ConnectionPacket(buffer);

            if(data.source == ConnectionPacket.HOST)
                System.out.println("FROM HOST");
            else if(data.source == ConnectionPacket.REMOTE)
                System.out.println("FROM REMOTE");

            
            data.ipSource = packet.getAddress().getHostAddress();
            data.portSource = packet.getPort();
            
            
            return data;
        }
        return null;
    }
    
    public ConnectionPacket(char source) {
        this.source = source;
    }
    
    public ConnectionPacket(SmartByteArray buffer) {
        
        source = (char)buffer.readByte(); 
        
    }
    
    public SmartByteArray getByteArray() {
        
        byte buffer[] = new byte[2];
        buffer[0] = Parser.byteFromEnum(Parser.PacketTypes.ConnectionEstablishedPacket);
        buffer[1] = (byte)source;
        
        return new SmartByteArray(buffer);
        
    }
    
    
}
