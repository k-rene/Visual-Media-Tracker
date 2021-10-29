package model;

import org.json.JSONObject;

// Represents a movie having a name, status, streaming platform and type (movie)
public class Movie extends Media {

    /* REQUIRES: movieName is not an empty string
     * EFFECTS: name of movie is set to movieName; status is an integer that corresponds to a status
     */
    public Movie(String movieName, Integer watchStatus, String streamingPlatform) {
        super(movieName, watchStatus, streamingPlatform);
        type = "movie";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("status", status);
        json.put("platform", platform);
        json.put("type", type);

        return json;
    }

}