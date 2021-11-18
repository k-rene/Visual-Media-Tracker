package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowTest {
    private Show testShowInt;
    private Show testShowStr;

    private String name = "Toy Story";
    private Integer statusInt = 0;
    private String statusStr = "To Watch";
    private String platform = "Netflix";
    private Integer bookmark = 12;

    @BeforeEach
    void runBefore() {
        testShowInt = new Show(name, statusInt, platform, bookmark);
        testShowStr = new Show(name, statusStr, platform, bookmark);
    }

    @Test
    void testConstructorIntegerStatus() {
        assertEquals(name, testShowInt.getName());
        assertEquals(statusInt, testShowInt.getStatus());
        assertEquals(platform, testShowInt.getPlatform());
        assertEquals(bookmark, testShowInt.getBookmark());
    }

    @Test
    void testConstructorStringStatus() {
        assertEquals(name, testShowStr.getName());
        assertEquals(statusInt, testShowStr.getStatus());
        assertEquals(platform, testShowStr.getPlatform());
        assertEquals(bookmark, testShowStr.getBookmark());
    }

    @Test
    void testGetConvertStatusToString() {
        assertEquals("to watch", testShowInt.getConvertedStatus(0));
        assertEquals("watching", testShowInt.getConvertedStatus(1));
        assertEquals("watched", testShowInt.getConvertedStatus(2));
    }

    @Test
    void testConvertStatusToInt() {
        assertEquals(0, testShowStr.convertStatusToInt("To Watch"));
        assertEquals(1, testShowStr.convertStatusToInt("Watching"));
        assertEquals(2, testShowStr.convertStatusToInt("Watched"));
    }

    @Test
    void testUpdateStatus() {
        testShowInt.updateStatus(1);
        assertEquals(1, testShowInt.getStatus());
        testShowStr.updateStatus("Watching");
        assertEquals(1, testShowStr.getStatus());
    }

    @Test
    void testUpdateBookmark() {
        testShowInt.updateBookmark(13);
        assertEquals(13, testShowInt.getBookmark());
    }
}
