package org.chat.common;

/**
 *
 * Created by A.V.Tsaplin on 28.07.2016.
 */

public class IncomingDataContainer {

    private final int bufferSize;
    private static char[] buffer = null;
    private static int bufferLastIndex;

    public IncomingDataContainer(int bufferSize) {
        this.bufferSize = bufferSize;
        buffer = new char[bufferSize];
        bufferLastIndex = 0;
    }

    public boolean incomingDataContainer(String incomingData) {
        if ((bufferLastIndex + incomingData.length()) <= bufferSize) {
            String resString = new String(buffer, 0, bufferLastIndex) + incomingData;
            buffer = resString.toCharArray();
            bufferLastIndex = resString.length();
        } else {
            return false;
        }
        return true;
    }

    public String toParse(String markerWord) {
        int length = markerWord.length();
        if (bufferSize > length) {
            if (new String(buffer, buffer.length - length, length).equals(markerWord)) {
                return new String(buffer, 0, buffer.length - length);
            }
        }
        return null;
    }

    public void clearBuffer() {
        buffer = new char[bufferSize];
        bufferLastIndex = 0;
    }
}
