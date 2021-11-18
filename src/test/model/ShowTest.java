package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowTest {
    private Show testShow;

    private String name = "Toy Story";
    private Integer status = 0;
    private String platform = "Netflix";
    private Integer bookmark = 12;

    @BeforeEach
    void runBefore() {
        testShow = new Show(name, status, platform, bookmark);
    }

    @Test
    void testConstructor() {
        assertEquals(name, testShow.getName());
        assertEquals(status, testShow.getStatus());
        assertEquals(platform, testShow.getPlatform());
        assertEquals(bookmark, testShow.getBookmark());
    }


    @Test
    void testGetConvertStatusToString() {
        assertEquals("to watch", testShow.getConvertedStatus(0));
        assertEquals("watching", testShow.getConvertedStatus(1));
        assertEquals("watched", testShow.getConvertedStatus(2));
    }

    @Test
    void testConvertStatusToInt() {
        assertEquals(0, testShow.convertStatusToInt("To Watch"));
        assertEquals(1, testShow.convertStatusToInt("Watching"));
        assertEquals(2, testShow.convertStatusToInt("Watched"));
    }

    @Test
    void testUpdateStatus() {
        testShow.updateStatus(1);
        assertEquals(1, testShow.getStatus());
    }

    @Test
    void testUpdateBookmark() {
        testShow.updateBookmark(13);
        assertEquals(13, testShow.getBookmark());
    }
}
