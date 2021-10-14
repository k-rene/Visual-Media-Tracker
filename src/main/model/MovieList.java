package model;

import java.util.ArrayList;
import java.util.List;

public class MovieList {
    private List<Movie> movieList = new ArrayList<Movie>();

    //!!DO WE NEED A CONSTRUCTOR?

    /*  EFFECTS: retrieve Movie m based on its position in movieList
     */
    protected Movie getMovie(Integer index) {
        return movieList.get(index);
    }

    /*  MODIFIES: this
    *   EFFECTS: add Movie m into movieList
    */
    protected void addMovie(Movie m) {
        movieList.add(m);
    }

    /*   MODIFIES: this
     *   EFFECTS: removes Movie m from movieList
     */
    protected void removeMovie(Movie m) {
        movieList.remove(m);
    }

    /*  EFFECTS: returns the length of movieList
     */
    protected Integer length() {
        return movieList.size();
    }
}
