package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {
    private Movie testMovie;

    private String name = "Toy Story";
    private Integer status = 0;
    private String platform = "Netflix";

    @BeforeEach
    void runBefore() {
        testMovie = new Movie(name, status, platform);
    }

    @Test
    void testConstructor() {
        assertEquals(name, testMovie.getName());
        assertEquals(status, testMovie.getStatus());
        assertEquals(platform, testMovie.getPlatform());
    }

    // how do you test exception handler (for handling if empty string is name)?

    @Test
    // do i test convert ot string or the get method?
    void testGetConvertStatusToString() {
        assertEquals("want to watch", testMovie.getConvertedStatus());
    }

    @Test
    void testUpdateStatus() {
        testMovie.updateStatus(1);
        assertEquals(1, testMovie.getStatus());
    }
}
