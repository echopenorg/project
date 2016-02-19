package com.echopen.asso.echopen.model.Data;

import android.app.Activity;

import com.echopen.asso.echopen.preproc.ScanConversion;
import com.echopen.asso.echopen.ui.MainActionController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ProcessTCPTask extends AbstractDataTask {
    private Socket s;

    private String ip;
    private int port;

    public ProcessTCPTask(Activity activity, MainActionController mainActionController, ScanConversion scanConversion, String ip, int port) throws IOException {
        super(activity, mainActionController, scanConversion);
        this.ip = ip;
        this.port = port;
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    protected Void doInBackground(Void... Voids) {
        int msgLen;
        byte[] msgLenBytes;
        byte[] message;
        InputStream stream;

        try {
            s = new Socket(ip, port);
            stream = s.getInputStream();

            while (true) {
                try {
                    msgLenBytes = readBytes(4, stream);
                    msgLen = java.nio.ByteBuffer.wrap(msgLenBytes).order(java.nio.ByteOrder.LITTLE_ENDIAN).getInt();
                    message = readBytes(msgLen, stream);

                    ScanConversion scnConv = ScanConversion.getInstance(message);
                    scnConv.setTcpData();
                    refreshUI(scnConv);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private byte[] readBytes(int len, InputStream stream) throws IOException {
        byte[] buffer = new byte[len];
        int totalLenRead = 0;
        int lenRead = 0;

        while(totalLenRead < len) {
            byte[] tmpBuffer = new byte[len - totalLenRead];
            lenRead = stream.read(tmpBuffer);
            System.arraycopy(tmpBuffer, 0, buffer, totalLenRead, lenRead);
            totalLenRead += lenRead;
        }

        return buffer;
    }
}