package model;

import java.util.ArrayList;
import java.util.List;

// A list of Media
public class MediaList {
    private List<Media> mediaList = new ArrayList<Media>();

    //!!DO WE NEED A CONSTRUCTOR?

    /*   MODIFIES: this
     *   EFFECTS: adds Media m to mediaList
     */
    public void addMedia(Media m) {
        mediaList.add(m);
    }

    /*   MODIFIES: this
     *   EFFECTS: removes Media m from movieList
     */
    public void removeMedia(Media m) {
        mediaList.remove(m);
    }

    /*   MODIFIES: this
     *   EFFECTS: removes Media m from movieList
     */
    public List<Media> getList() {
        return mediaList;
    }

    /*  EFFECTS: returns the length of showList
     */
    public Integer length() {
        return mediaList.size();
    }
}
