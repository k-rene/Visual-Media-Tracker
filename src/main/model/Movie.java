package model;

// Represents a movie having a name, status, and platform
public class Movie {
    private String name;
    private Integer status;
    /*  status meaning:
     *   0 = to watch
     *   1 = watching
     *   2 = watched
     */
    private String platform;

     /* REQUIRES: movieName is not an empty string
     * EFFECTS: name of movie is set to movieName; status is an integer that corresponds to a status
     */
    public Movie(String movieName, Integer watchStatus, String streamingPlatform) {
        this.name = movieName;
        this.status = watchStatus;
        this.platform = streamingPlatform;
    }

    public String getName() {
        return name;
    }

    public Integer getStatus() {
        return status;
    }

    public String getConvertedStatus(Integer s) {
        return convertStatusToString(status);
    }

    public String getPlatform() {
        return platform;
    }

    /* REQUIRES: s be a valid status integer
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

    protected void updateStatus(Integer newStatus) {
        status = newStatus;
    }

}
