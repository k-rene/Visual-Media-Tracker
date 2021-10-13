package model;

// abstract class representing a Media having a name, status, and platform. Extended by Movie and Show
public abstract class Media {
    protected String name;
    protected Integer status;
    /*  status meaning:
     *   0 = to watch
     *   1 = watching
     *   2 = watched
     */
    protected String platform;

    public String getName() {
        return name;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getConvertedStatus() {
        return status;
    }

    public String getPlatform() {
        return platform;
    }

    private String convertStatusToString() {
        return " "; //stub
    }

    protected void updateStatus(Integer status) {
        //stub
    }

    public void viewMedia() {
        //stub
    }
}
