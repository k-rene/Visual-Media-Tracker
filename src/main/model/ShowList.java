package model;

import java.util.ArrayList;
import java.util.List;

public class ShowList {
    private List<Show> showList = new ArrayList<Show>();

    //!!DO WE NEED A CONSTRUCTOR?

    /*   MODIFIES: this
     *   EFFECTS: adds Show s from showList
     */
    protected void addShow(Show s) {
        showList.add(s);
    }

    /*   MODIFIES: this
     *   EFFECTS: removes Show s from showList
     */
    protected void removeShow(Show s) {
        showList.remove(s);
    }

    /*  EFFECTS: returns the length of showList
     */
    protected Integer length() {
        return showList.size();
    }
}
