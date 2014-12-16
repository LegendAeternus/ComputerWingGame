/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

/**
 *
 * @author Michael
 */
public class ConnectionPacket {
    
    char source; //0=host, 1=remote
    final static char HOST = 0;
    final static char REMOTE = 1;
    
    public ConnectionPacket(char source) {
        this.source = source;
    }
    
    public ConnectionPacket(SmartByteArray buffer) {
        
        source = (char)buffer.readByte(); 
        
    }
    
    public SmartByteArray getByteArray() {
        
        byte buffer[] = new byte[2];
        buffer[0] = PacketParser.byteFromEnum(PacketParser.PacketTypes.ConnectionEstablishedPacket);
        buffer[1] = (byte)source;
        
        return new SmartByteArray(buffer);
        
    }
    
    
}
