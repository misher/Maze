package org.maze;

/**
 * Created by A.V.Tsaplin on 15.04.2016.
 */

import java.io.*;
import java.util.ArrayList;

public class FileParser {

    InputStream someFileStream;

    public FileParser(InputStream someFileStream) {
        super();
        this.someFileStream = someFileStream;
    }

    public ArrayList<ParePointValue> parseFile() {

        ArrayList<ParePointValue> mapFromFile = new ArrayList<>();

        try( Reader readerIO = new InputStreamReader(someFileStream))
        {
            int xAxis = 0;
            int yAxis = 0;
            int c;
            int [] somePointArray = {2,0};
            Point somePointFromFile;
            ParePointValue someSqlPare;

            while((c=readerIO.read())!=-1){
                if ((char) c == '\n') {
                    yAxis++;
                    xAxis = -1;
                }
                if (((char)c == '0') || ((char)c == '2') || ((char)c == '3')) {
                    somePointArray[0] = xAxis;
                    somePointArray[1] = yAxis;
                    somePointFromFile = new Point (2, somePointArray);
                    someSqlPare = new ParePointValue(somePointFromFile, c);
                    mapFromFile.add(someSqlPare);
                }
                xAxis++;
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return mapFromFile;
    }
}