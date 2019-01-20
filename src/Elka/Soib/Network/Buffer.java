package Elka.Soib.Network;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Buffer {
    public static int bufferSize;
    public List<Integer> buffers;
    public Buffer(int amount){
        this.generateBuffers(amount);
    }
    public void generateBuffers(int amount){
        buffers = new ArrayList<Integer>(Collections.nCopies(amount, 0));
        for(int buffer : buffers){
            buffer = 0;
        }
    }
}
