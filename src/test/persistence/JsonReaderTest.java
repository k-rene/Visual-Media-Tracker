package persistence;

import model.Media;
import model.MediaList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// This class references code from this JsonSerializationDemo
// Json Demo Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MediaList wr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMediaList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMediaList.json");
        try {
            MediaList ml = reader.read();
            assertEquals(0, ml.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMediaList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMediaList.json");
        try {
            MediaList ml = reader.read();
            List<Media> mediaList = ml.getList();

            assertEquals(2, ml.length());
            checkMedia("venom", 1,"netflix","movie", mediaList.get(0));
            checkMedia("gakuen babysitters", 2, "youtube", "show", mediaList.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
