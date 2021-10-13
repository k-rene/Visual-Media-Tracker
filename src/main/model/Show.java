package model;

// Represents a movie having a name, status, platform, and episode bookmark
public class Show extends Media {
    private Integer bookmark;
    // do we want to deal with total episodes?

    /*
     * REQUIRES: showName is not an empty string
     * EFFECTS: name of movie is set to movieName; status is an integer that corresponds to a status
     */
    public Show(String showName, Integer watchStatus, String streamingPlatform, Integer epBookmark) {
        this.name = showName;
        this.status = watchStatus;
        this.platform = streamingPlatform;
        this.bookmark = epBookmark;
    }

    public Integer getBookmark() {
        return bookmark;
    }

    protected void updateBookmark(Integer newBookmark) {
        //stub
    }
}
