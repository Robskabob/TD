package TD.Net;

import TD.Net.Lib.Client;
import processing.core.PApplet;

import java.nio.ByteBuffer;

public class TDClient extends PApplet {

    Client c;
    String input;
    int data[];

    public static void main(String[] args) {
        PApplet.main("TD.Net.TDClient");
    }

    public void settings() {
        size(450, 255);
    }
    public void setup(){
        background(204);
        stroke(0);
        frameRate(5); // Slow it down a little
        // Connect to the server’s IP address and port­
        c = new Client(this, "161.97.25.40", 12345); // Replace with your server’s IP and port
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

    public void draw() {
        if (mousePressed == true) {
            // Draw our line
            stroke(255);
            line(pmouseX, pmouseY, mouseX, mouseY);
            // Send mouse coords to other person
            c.write(convertIntArrayToByteArray(new int[]{pmouseX , pmouseY , mouseX , mouseY}));
        }

        // Receive data from server
        if (c.available() > 0) {
            data = convertByteArrayToIntArray(c.readBytes());
            // Draw line using received coords
            stroke(0);
            line(data[0], data[1], data[2], data[3]);
        }
    }
}
