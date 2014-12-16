/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

/**
 *
 * @author Michael
 */
public class SmartByteArray {
    
    private byte buffer[];
    int currentByte = 0;
    
    
    public SmartByteArray(int size) {
        buffer = new byte[size];
    }
    public SmartByteArray(byte inputBuffer[]) {
        buffer = inputBuffer;
    }
    
    public byte readByte() {
        ++currentByte;
        return buffer[currentByte-1];
    }
    
    public int size() {
        return buffer.length;
    }
    
    public byte at(int i){
        return buffer[i];
    }
    
    public void appendBytes(SmartByteArray bytes) {
        byte expandedBuffer[] = new byte[size() + bytes.size()];
        
        for(int i=0; i<size(); i++) {
            expandedBuffer[i] = at(i);
        }
        for(int i=0; i<bytes.size(); i++) {
            expandedBuffer[i+size()] = bytes.at(i);
        }
    }
    
    public byte[] getRawBuffer() {
        return buffer;
    }
    
    public void setCurrentByte(int index) {
        currentByte = index;
    }
    public int getCurrentByte() {
        return currentByte;
    }
    
    
    
}
