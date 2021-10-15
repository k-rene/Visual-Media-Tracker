package model;

// Represents a movie having a name, status, and platform
public class Movie extends Media {

    /* REQUIRES: movieName is not an empty string
     * EFFECTS: name of movie is set to movieName; status is an integer that corresponds to a status
     */
    public Movie(String movieName, Integer watchStatus, String streamingPlatform) {
        super(movieName, watchStatus, streamingPlatform);
    }

}
