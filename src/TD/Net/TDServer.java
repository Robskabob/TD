package TD.Net;

import processing.core.PApplet;
import TD.Net.Lib.*;

import java.nio.ByteBuffer;

public class TDServer extends PApplet {
    Server s;
    Client c;
    String input;
    int data[];

    public static void main(String[] args) {
        PApplet.main("TD.Net.TDServer");
    }

    private byte[] convertIntArrayToByteArray(int[] data) {
        if (data == null) return null;
        // ----------
        ByteBuffer bb = ByteBuffer.allocate(data.length*4);
        for (int i = 0; i < data.length; i++)
            bb.putInt(i);
        return bb.array();
    }

    private int[] convertByteArrayToIntArray(byte[] data) {
        if (data == null) return null;
        int[] out = new int[data.length/4];
        ByteBuffer bb = ByteBuffer.wrap(data);
        for (int i = 0; i < data.length/4; i++)
            out[i] = bb.get(i);
        return out;
    }

    public void settings() {
        size(450, 255);
    }
    public void setup(){
        background(100);
        stroke(0);
        frameRate(5); // Slow it down a little
        s = new Server(this, 12345);  // Start a simple server on a port
    }
    public void draw() {
        if (mousePressed == true) {
            // Draw our line
            stroke(255);
            line(pmouseX, pmouseY, mouseX, mouseY);
            // Send mouse coords to other person
            s.write(convertIntArrayToByteArray(new int[]{pmouseX , pmouseY , mouseX , mouseY}));
        }

        // Receive data from client
        c = s.available();
        if (c != null) {
            data = convertByteArrayToIntArray(c.readBytes());
            // Draw line using received coords
            stroke(0);
            line(data[0], data[1], data[2], data[3]);
        }
    }
}