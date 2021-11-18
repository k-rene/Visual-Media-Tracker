package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

// represents a media that has a name, watch status, streaming platform and type (movie/show)
public abstract class Media implements Writable {
    protected String name;
    protected Integer status;
    /*  status meaning:
     *   0 = to watch
     *   1 = watching
     *   2 = watched
     */
    protected String platform;
    protected String type;

    public Media(String mediaName, Integer watchStatus, String streamingPlatform) {
        this.name = mediaName;
        this.status = watchStatus;
        this.platform = streamingPlatform;
    }

    public Media(String mediaName, String watchStatus, String streamingPlatform) {
        this.name = mediaName;
        this.status = convertStatusToInt(watchStatus);
        this.platform = streamingPlatform;
    }

    public String getName() {
        return name;
    }

    public Integer getStatus() {
        return status;
    }

    public String getConvertedStatus(Integer s) {
        return convertStatusToString(s);
    }

    public String getPlatform() {
        return platform;
    }

    public String getType() {
        return type;
    }

    /* REQUIRES: s be a valid status integer
     *  EFFECT: converts status integer into its corresponding string
     * */
    private String convertStatusToString(Integer s) {
        String msg = null;

        if (s == 0) {
            msg = "to watch";
        } else if (s == 1) {
            msg = "watching";
        } else {
            msg = "watched";
        }

        return msg;
    }

    // EFFECTS: converts string status received form input form into integer
    public Integer convertStatusToInt(String s) {
        Integer stat = -1;

        if (s.equals("To Watch")) {
            stat = 0;
        } else if (s.equals("Watching")) {
            stat = 1;
        } else {
            stat = 2;
        }

        return stat;
    }

    // MODIFIES: this
    // EFFECTS: updates the watch status to newStatus
    public void updateStatus(Integer newStatus) {
        status = newStatus;
    }

    // MODIFIES: this
    // EFFECTS: updates the watch status to newStatus -- called when given input from GUI form
    public void updateStatus(String newStatus) {
        status = convertStatusToInt(newStatus);
    }
}
