package org.chat;



/**
 *
 * Created by A.V.Tsaplin on 25.07.2016.
 */

public class BigDataReceiver {

    private static int index;
    private int bufferSize;
    private char[] circularBuffer;

    public BigDataReceiver(int size) {
        index = 0;
        this.bufferSize = size;
        circularBuffer = new char[bufferSize];
    }

    public class StringIndexPare {

        private int lastIndexValue;
        private char[] data;

        public StringIndexPare(int index, char[] data) {
            this.lastIndexValue = index;
            this.data = data;
        }

        public int getLastIndexValue() {
            return lastIndexValue;
        }

        public char[] getData() {
            return data;
        }
    }

    public StringIndexPare bigDataReceiver(String data) {
        int length = data.length();
        char[] string = data.toCharArray();
        if ((index + length) < (bufferSize - 1)) {
            int j = 0;
            for (int i = index; i < index + length; i++) {
                circularBuffer[i] = string[j];
                j++;
            }
            index = index + length;
        } else {
            int rest = (index + length) - (bufferSize - 1);
            int j = 0;
            for (int i = index; i < (bufferSize - 1); i++) {
                circularBuffer[i] = string[j];
                j++;
            }
            for (int i = 0; i < rest; i++) {
                circularBuffer[i] = string[j];
                j++;
            }
            index = rest;
        }
        return new StringIndexPare(index, circularBuffer);
    }

    public String toParse(StringIndexPare stringIndexPare) {
        char[] bufferData = stringIndexPare.getData();
        int length = stringIndexPare.getData().length;
        int lastIndex = stringIndexPare.getLastIndexValue();
        if ((lastIndex >= 6) && (new String(bufferData, lastIndex - 5, 5)).equals("^end^")) {
            for (int index = stringIndexPare.getLastIndexValue() - 6; index >= 0; index--) {
                if (bufferData[index] == '^') {
                    switch (index) {
                        case 4:
                            if ((new String(bufferData, 0, 5)).equals("^end^")) {
//                                System.out.println("1");
                                return new String(bufferData, 5, lastIndex - index - 6);
                            }
                            break;
                        case 3:
                            if ((new String(bufferData, 0, 4)).equals("end^") && (bufferData[length-1] == '^')) {
//                                System.out.println("2");
                                return new String(bufferData, 4, lastIndex - index - 6);
                            }
                            break;
                        case 2:
                            if ((new String(bufferData, 0, 3)).equals("nd^") && ((new String(bufferData, length - 2, 2)).equals("^e"))) {
//                                System.out.println("3");
                                return new String(bufferData, 3, lastIndex - index - 6);
                            }
                            break;
                        case 1:
                            if ((new String(bufferData, 0, 2)).equals("d^") && ((new String(bufferData, length - 3, 3)).equals("^en"))) {
//                                System.out.println("4");
                                return new String(bufferData, 2, lastIndex - index - 6);
                            }
                            break;
                        case 0:
                            if ((bufferData[0] == '^') && ((new String(bufferData, length - 4, 4)).equals("^end"))) {
//                                System.out.println("5");
                                return new String(bufferData, 1, lastIndex - index - 6);
                            }
                            break;
                        default:
                            if ((new String(bufferData, index - 4, 5)).equals("^end^")) {
//                                System.out.println("6");
                                return new String(bufferData, index + 1, lastIndex - index - 6);
                            }
                            break;
                    }
                }
                if ((index == 0) && (bufferData[lastIndex + 1] == '\u0000')) {
//                    System.out.println("7");
                    return new String(bufferData, 0, lastIndex - 5);
                }
            }
            for (int index = (length - 1); (index - 4) > lastIndex; index--) {
                if ((bufferData[index] == '^') && (new String(bufferData, index - 4, 5)).equals("^end^")) {
//                    System.out.println("8");
                    return ((new String(bufferData, index + 1, (length - index - 2))) + (new String(bufferData, 0, lastIndex - 5)));
                }
            }
//            System.out.println("10");
            return null;
        } else if ((lastIndex < 6) && ((new String(bufferData, (length - (5 - lastIndex) - 1), (5 - lastIndex)) + new String(bufferData, 0, lastIndex)).equals("^end^"))){
            for (int i = (length - (6-lastIndex)); i >= 5; i--) {
                if ((new String(bufferData, i - 5, 5)).equals("^end^")) {
//                    System.out.println("9");
                    return new String(bufferData, i, (length - (6-lastIndex)) - i);
                }
            }
        }
//        System.out.println("11");
        return null;
    }
}
