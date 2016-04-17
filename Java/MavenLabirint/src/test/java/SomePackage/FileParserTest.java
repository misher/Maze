package SomePackage;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by A.V.Tsaplin on 15.04.2016.
 */
public class FileParserTest {


    @Test
    public void pointTestRecPass() throws IOException {
        Path path = Paths.get("C:/Maze/Java/testFile.txt");
        Files.createDirectories(path.getParent());
        try {
            Files.createFile(path);
        } catch (FileAlreadyExistsException e) {
            System.err.println("File already exists: " + e.getMessage());
            Files.delete(path);
            Files.createFile(path);
            System.out.println("File was recreated");
        } finally {
            FileWriter writer = new FileWriter("C:/Maze/Java/testFile.txt", true);
            try {
                // write all string
                writer.append("1111011101\n" + "1111011111\n" + "1111011111\n" + "0211111111\n");
                writer.flush();
            } catch(IOException ex){
                System.out.println(ex.getMessage());
                System.out.println("Input/Out exception, try again");
            } finally {
                FileParser someFileParser = new FileParser("C:/Maze/Java/testFile.txt");
                ArrayList<ParePointValue> parePointValueMap = someFileParser.parseFile();
                // First point
                assertEquals("First point X-Axis match", 4, parePointValueMap.get(0).getSomePoint().getAxis(0));
                assertEquals("First point Y-Axis match", 0, parePointValueMap.get(0).getSomePoint().getAxis(1));
                assertEquals("First point value match", 0, parePointValueMap.get(0).getSomeValue()-48);
                // Second point
                assertEquals("Second point X-Axis match", 8, parePointValueMap.get(1).getSomePoint().getAxis(0));
                assertEquals("Second point Y-Axis match", 0, parePointValueMap.get(1).getSomePoint().getAxis(1));
                assertEquals("Second point value match", 0, parePointValueMap.get(1).getSomeValue()-48);
                // Third point
                assertEquals("Third point X-Axis match", 4, parePointValueMap.get(2).getSomePoint().getAxis(0));
                assertEquals("Third point Y-Axis match", 1, parePointValueMap.get(2).getSomePoint().getAxis(1));
                assertEquals("Third point value match", 0, parePointValueMap.get(2).getSomeValue()-48);
                // Fourth point
                assertEquals("Fourth point X-Axis match", 4, parePointValueMap.get(3).getSomePoint().getAxis(0));
                assertEquals("Fourth point Y-Axis match", 2, parePointValueMap.get(3).getSomePoint().getAxis(1));
                assertEquals("Fourth point value match", 0, parePointValueMap.get(3).getSomeValue()-48);
                // Fifth point
                assertEquals("Fourth point X-Axis match", 0, parePointValueMap.get(4).getSomePoint().getAxis(0));
                assertEquals("Fourth point Y-Axis match", 3, parePointValueMap.get(4).getSomePoint().getAxis(1));
                assertEquals("Fourth point value match", 0, parePointValueMap.get(4).getSomeValue()-48);
                // Sixth point
                assertEquals("Fourth point X-Axis match", 1, parePointValueMap.get(5).getSomePoint().getAxis(0));
                assertEquals("Fourth point Y-Axis match", 3, parePointValueMap.get(5).getSomePoint().getAxis(1));
                assertEquals("Fourth point value match", 2, parePointValueMap.get(5).getSomeValue()-48);
            }
        }
    }
}
