package ui;

import model.Media;
import model.MediaList;
import model.Movie;
import model.Show;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// This class references code from this TellerApp & this JsonSerializationDemo
// TellerApp Link: https://github.students.cs.ubc.ca/CPSC210/TellerApp
// Json Demo Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Media Tracker Application
public class MediaTrackerApp {
    private MediaList mediaList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/medialist.json";

    public MediaTrackerApp() {
        runMediaTracker();
    }

    //  MODIFIES: this
    //  EFFECTS: processes user input
    private void runMediaTracker() {
        boolean keepGoing = true;
        String command = null;

        startUp();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processMenuCommand(command);
            }
        }
    }

    //  MODIFIES: this
    //  EFFECTS: initiates Tracker application
    private void startUp() {
        mediaList = new MediaList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nChoose one:");
        System.out.println("\ta -> Add Media");
        System.out.println("\tu -> Update Media");
        System.out.println("\tv -> View ALl Media");
        System.out.println("\ts -> save media list to file");
        System.out.println("\tl -> load media list from file");
        System.out.println("\tq -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command from main menu
    private void processMenuCommand(String command) {
        switch (command) {
            case "a":
                addMedia();
                break;
            case "u":
                updateViewingStatus();
                break;
            case "v":
                quickView();
                break;
            case "s":
                saveMediaList();
                break;
            case "l":
                loadMediaList();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: loads medialist from file
    private void loadMediaList() {
        try {
            mediaList = jsonReader.read();
            System.out.println("Loaded data from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the medialist to file
    private void saveMediaList() {
        try {
            jsonWriter.open();
            jsonWriter.write(mediaList);
            jsonWriter.close();
            System.out.println("Saved data to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: displays main menu of options to user
    private void mediaOptionsMenu() {
        System.out.println("\nChoose a media type:");
        System.out.println("\tm -> Movie");
        System.out.println("\ts -> Show");
        System.out.println("\tq -> Quit");
    }

    //   MODIFIES: this
    //   EFFECTS: initiates the process to add a new media
    private void addMedia() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            mediaOptionsMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processAddMediaCommand(command);
            }
        }
    }

    //   MODIFIES: this
    //   EFFECTS: processes addMedia user input
    private void processAddMediaCommand(String command) {
        if (command.equals("m")) {
            addMovie();
        } else if (command.equals("s")) {
            addShow();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // REQUIERS: status be a valid status
    // MODIFIES: this
    // EFFECTS: adds a movie to the media list
    private void addMovie() {
        // prompt for name, status etc, instantiate, then add to list
        String name;
        int status;
        String platform;

        // prompt for movie name
        System.out.println("Please enter the name of the movie:");
        name = input.next();

        status = this.getStatus();

        // prompt for movie streaming platform
        System.out.println("Please enter the streaming platform:");
        platform = input.next();

        Movie m1 = new Movie(name, status, platform);
        mediaList.addMedia(m1);
    }

    // REQUIERS: status be a valid status
    // MODIFIES: this
    // EFFECTS: adds a show to the media list
    private void addShow() {
        // prompt for name, status etc, instantiate, then add to list
        String name;
        int status;
        String platform;
        int bookmark;

        // prompt for show name
        System.out.println("Please enter the name of the show:");
        name = input.next();

        status = this.getStatus();

        // CHECK FOR VALID STATUS INPUT
        if (status == 1) {
            // prompt for show bookmark
            System.out.println("Please enter which episode of the show you're on:");
            bookmark = input.nextInt();
        } else {
            bookmark = 0;
        }

        // prompt for show platform
        System.out.println("Please enter the streaming platform:");
        platform = input.next();

        Show s1 = new Show(name, status, platform, bookmark);
        mediaList.addMedia(s1);
    }

    // EFFECTS: retrieves the watch status
    private int getStatus() {
        int status = -1;

        while (!(status == 0 || status == 1 || status == 2)) {
            // prompt for show watch status
            System.out.println("Please enter the watch status:");
            System.out.println("0 - to watch");
            System.out.println("1 - watching");
            System.out.println("2 - watched");
            status = input.nextInt();
        }

        return status;
    }

    // MODIFIES: this
    // EFFECTS: updates the viewing status of a selected media
    private void updateViewingStatus() {
        int index = -1;
        int newStatus = -1;

        quickView();
        System.out.println("Please select the index of the media to update");
        index = input.nextInt();
        newStatus = getStatus();

        if (index >= 0) {
            List<Media> list = mediaList.getList();
            list.get(index).updateStatus(newStatus);
        }

    }

    // EFFECTS: displays all Media in mediaList
    private void quickView() {
        List<Media> list = mediaList.getList();

        System.out.println("index" + "| title");
        for (int i = 0; i < mediaList.length(); i++) {
            System.out.println(i + "|" + list.get(i).getName());
        }
    }
}
