package SomePackage;

/**
 * Created by A.V.Tsaplin on 15.04.2016.
 */

import java.io.*;
import java.util.ArrayList;

public class FileParser {

    String FileDirectory = "";

    public FileParser(String FileDirectory) {
        super();
        this.FileDirectory = FileDirectory;
    }

    public ArrayList<ParePointValue> parseFile() {

        ArrayList<ParePointValue> mapFromFile = new ArrayList<>();

        try(FileReader reader = new FileReader(FileDirectory))
        {
            int xAxis = 0;
            int yAxis = 0;
            int c;
            int [] somePointArray = {2,0};
            Point somePointFromFile;
            ParePointValue someSqlPare;
            while((c=reader.read())!=-1){
                if ((char) c == '\n') {
                    yAxis++;
                    xAxis = -1;
                }
                if (((char)c == '0') || ((char)c == '2')) {
                    somePointArray[0] = xAxis;
                    somePointArray[1] = yAxis;
                    somePointFromFile = new Point (2, somePointArray);
                    someSqlPare = new ParePointValue(somePointFromFile, c);
                    mapFromFile.add(someSqlPare);
                }
                xAxis++;
//                System.out.print((char)c);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        finally {
//            for(int i = 0; i < mapFromFile.size(); i++) {
//                System.out.print("\n"+"Coordinate X: ");
//                System.out.print(mapFromFile.get(i).getSomePoint().getAxis(0));
//                System.out.print(" Y: ");
//                System.out.print(mapFromFile.get(i).getSomePoint().getAxis(1));
//            }
        }
        return mapFromFile;
    }
}