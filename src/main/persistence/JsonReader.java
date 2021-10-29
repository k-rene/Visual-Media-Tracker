package persistence;

import model.Media;
import model.MediaList;
import model.Movie;
import model.Show;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

// This class references code from this JsonSerializationDemo
// Json Demo Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// represents a reader that reads tracker from JSON data stored in a file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads MediaTracker from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MediaList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMediaList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses medialist from JSON object and returns it
    private MediaList parseMediaList(JSONObject jsonObject) {
        MediaList ml = new MediaList();
        addMedias(ml, jsonObject);
        return ml;
    }


    // MODIFIES: ML
    // EFFECTS: parses medias' type from JSON object and adds them to medialist
    private void addMedias(MediaList ml, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("mediaList");

        for (Object json : jsonArray) {
            JSONObject nextMedia = (JSONObject) json;

            String type = nextMedia.getString("type");

            if (Objects.equals(type, "show")) {
                addShow(ml, nextMedia);
            } else {
                addMovie(ml, nextMedia);
            }
        }
    }

    // MODIFIES: ??
    // EFFECTS: parses movie from JSON object and adds it to medialist
    private void addMovie(MediaList ml, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Integer status = jsonObject.getInt("status");
        String platform = jsonObject.getString("platform");
        Movie m = new Movie(name, status, platform);
        ml.addMedia(m);
    }

    // MODIFIES: ??
    // EFFECTS: parses show from JSON object and adds it to medialist
    private void addShow(MediaList ml, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Integer status = jsonObject.getInt("status");
        String platform = jsonObject.getString("platform");
        Integer bookmark = jsonObject.getInt("bookmark");

        Show m = new Show(name, status, platform, bookmark);
        ml.addMedia(m);
    }

}
