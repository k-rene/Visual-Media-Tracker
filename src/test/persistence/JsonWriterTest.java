package persistence;

import model.Media;
import model.MediaList;
import model.Movie;
import model.Show;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// This class references code from this JsonSerializationDemo
// Json Demo Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            MediaList ml = new MediaList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMediaList() {
        try {
            MediaList ml = new MediaList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMediaList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMediaList.json");
            ml = reader.read();
            assertEquals(0, ml.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralMediaList() {
        try {
            MediaList ml = new MediaList();
            ml.addMedia(new Movie("venom", 1, "netflix"));
            ml.addMedia(new Show("gakuen babysitters", 2, "youtube", 0));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMediaList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMediaList.json");
            ml = reader.read();
            List<Media> mediaList = ml.getList();
            assertEquals(2, mediaList.size());
            checkMedia("venom", 1,"netflix","movie", mediaList.get(0));
            checkMedia("gakuen babysitters", 2, "youtube", "show", mediaList.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
