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
    // do i test convert ot string or the get method?
    void testGetConvertStatusToString() {
        assertEquals("want to watch", testShow.getConvertedStatus());
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
