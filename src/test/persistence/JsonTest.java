package persistence;

// This class references code from this JsonSerializationDemo
// Json Demo Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

import model.Media;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkMedia(String name, Integer status, String platform, String type, Media m) {
        assertEquals(name, m.getName());
        assertEquals(status, m.getStatus());
        assertEquals(platform, m.getPlatform());
        assertEquals(type, m.getType());
    }
}
