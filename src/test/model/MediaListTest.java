package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MediaListTest {
    private MediaList testMediaList;
    private Media m1 = new Movie("Star Wars: Visions", 0,"Disney+");
    private Media m2 = new Show("Flying Tigers", 2,"Viu", 13);

    @BeforeEach
    void runBefore() {
        testMediaList = new MediaList();
    }

    @Test
    void testAddMedia() {
        testMediaList.addMedia(m1);
        testMediaList.addMedia(m2);
        assertEquals(2, testMediaList.length());
    }

    @Test
    void testRemoveMedia() {
        testMediaList.addMedia(m1);
        testMediaList.addMedia(m2);
        testMediaList.removeMedia(m1);
        assertEquals(1, testMediaList.length());
    }
}
