package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieListTest {
    private MovieList testMovieList;
    private Movie m1 = new Movie("Avengers: Endgame", 0,"Disney+");
    private Movie m2 = new Movie("Avengers: Infinity War", 2,"Disney+");

    @BeforeEach
    void runBefore() {
        testMovieList = new MovieList();
    }

    @Test
    void testAddMovie() {
        testMovieList.addMovie(m1);
        testMovieList.addMovie(m2);
        assertEquals(2, testMovieList.length());
    }

    @Test
    void testRemoveMovie() {
        testMovieList.addMovie(m1);
        testMovieList.addMovie(m2);
        testMovieList.removeMovie(m1);
        assertEquals(1, testMovieList.length());
    }
}
