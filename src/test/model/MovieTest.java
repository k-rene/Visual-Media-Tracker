package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {
    private Movie testMovieInt;
    private Movie testMovieStr;

    private String name = "Toy Story";
    private Integer statusInt = 0;
    private String statusStr = "To Watch";
    private String platform = "Netflix";

    @BeforeEach
    void runBefore() {
        testMovieInt = new Movie(name, statusInt, platform);
        testMovieStr = new Movie(name, statusStr, platform);
    }

    @Test
    void testConstructorIntegerStatus() {
        assertEquals(name, testMovieInt.getName());
        assertEquals(statusInt, testMovieInt.getStatus());
        assertEquals(platform, testMovieInt.getPlatform());
    }

    @Test
    void testConstructorStringStatus() {
        assertEquals(name, testMovieStr.getName());
        assertEquals(statusInt, testMovieStr.getStatus());
        assertEquals(platform, testMovieStr.getPlatform());
    }

    @Test
    void testGetConvertStatusToString() {
        assertEquals("to watch", testMovieInt.getConvertedStatus(0));
        assertEquals("watching", testMovieInt.getConvertedStatus(1));
        assertEquals("watched", testMovieInt.getConvertedStatus(2));
    }

    @Test
    void testConvertStatusToInt() {
        assertEquals(0, testMovieInt.convertStatusToInt("To Watch"));
        assertEquals(1, testMovieInt.convertStatusToInt("Watching"));
        assertEquals(2, testMovieInt.convertStatusToInt("Watched"));
    }

    @Test
    void testUpdateStatus() {
        testMovieInt.updateStatus(1);
        assertEquals(1, testMovieInt.getStatus());
    }
}
