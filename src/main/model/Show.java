package model;

import org.json.JSONObject;

import java.util.Objects;

// Represents a show having a name, status, platform, episode bookmark,and type (show)
public class Show extends Media {
    private Integer bookmark;

    /*
     * REQUIRES: showName is not an empty string; watchStatus is a valid status integer
     * EFFECTS: name of show is set to showName; status is an integer that corresponds to a status
     */
    public Show(String showName, Integer watchStatus, String streamingPlatform, Integer epBookmark) {
        super(showName, watchStatus, streamingPlatform);
        this.bookmark = epBookmark;
        type = "show";
    }

    public Show(String showName, String watchStatus, String streamingPlatform, Integer epBookmark) {
        super(showName, watchStatus, streamingPlatform);
        this.bookmark = epBookmark;
        type = "show";
    }

    public Integer getBookmark() {
        return bookmark;
    }

     /*  MODIFIES: this
     *   EFFECTS: sets this.bookmark to newBookmark
     */
    public void updateBookmark(Integer newBookmark) {
        this.bookmark = newBookmark;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("status", status);
        json.put("platform", platform);
        json.put("type", type);
        json.put("bookmark", bookmark);
        return json;
    }
}
