package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// represents a list of media
public class MediaList {
    private List<Media> mediaList = new ArrayList<Media>();

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

    // REQUIRES: mediaName is a media in mediaList
    // EFFECTS: helper method to retrieve the retrieve the index in medialist of the given media
    public Integer getIndex(String mediaName) {
        Integer index = 0;
        for (Media m: mediaList) {
            if (m.getName().equals(mediaName)) {
                return index;
            } else {
                index++;
            }
        }
        return -1;
    }

    // REQUIRES: index be a valid index
    // EFFECTS: return the media stored at index i
    public Media getMedia(int i) {
        return mediaList.get(i);
    }

    //@Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("mediaList", mediasToJson());
        return json;
    }

    // EFFECTS: returns things in this medialist as a JSON array
    private JSONArray mediasToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Media ml : mediaList) {
            String type = ml.getType();
            if (Objects.equals(type, "show")) {
                Show s = (Show) ml;
                jsonArray.put(s.toJson());
            } else {
                Movie m = (Movie) ml;
                jsonArray.put(m.toJson());
            }
        }

        return jsonArray;
    }
}
