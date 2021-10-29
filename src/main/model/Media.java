package model;

import org.json.JSONObject;
import persistence.Writable;

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

    public void updateStatus(Integer newStatus) {
        status = newStatus;
    }

    @Override //stub
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("status", status);
        json.put("platform", platform);
        return json;
    }
}
