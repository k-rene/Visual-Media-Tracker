package model;

// Represents a movie having a name, status, platform, and episode bookmark
public class Show {
    protected String name;
    protected Integer status;
    /*  status meaning:
     *   0 = to watch
     *   1 = watching
     *   2 = watched
     */
    protected String platform;
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

    public String getName() {
        return name;
    }

    public Integer getStatus() {
        return status;
    }

    public String getPlatform() {
        return platform;
    }

    public Integer getBookmark() {
        return bookmark;
    }

    /*  REQUIRES: s be a valid status integer
     *  EFFECT: converts status integer into its corresponding string
     * */
    private String convertStatusToString(Integer s) {
        if (s == 0) {
            return "to watch";
        } else if (s == 1) {
            return "watching";
        } else if (s == 2) {
            return "watched";
        } else {
            return "";
        }
    }

    public String getConvertedStatus(Integer s) {
        return convertStatusToString(s);
    }

    /*  REQUIRES: newStatus be a valid status
    *   MODIFIES: this
    *   EFFECTS: sets this.status to newStatus
    */
    protected void updateStatus(Integer newStatus) {
        status = newStatus;
    }

     /*  MODIFIES: this
     *   EFFECTS: sets this.bookmark to newBookmark
     */
    protected void updateBookmark(Integer newBookmark) {
        this.bookmark = newBookmark;
    }
}
