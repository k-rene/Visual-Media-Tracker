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

    @Test
    void testGetConvertStatusToString() {
        assertEquals("to watch", testMovie.getConvertedStatus(0));
        assertEquals("watching", testMovie.getConvertedStatus(1));
        assertEquals("watched", testMovie.getConvertedStatus(2));
    }

    @Test
    void testConvertStatusToInt() {
        assertEquals(0, testMovie.convertStatusToInt("To Watch"));
        assertEquals(1, testMovie.convertStatusToInt("Watching"));
        assertEquals(2, testMovie.convertStatusToInt("Watched"));
    }

    @Test
    void testUpdateStatus() {
        testMovie.updateStatus(1);
        assertEquals(1, testMovie.getStatus());
    }
}
