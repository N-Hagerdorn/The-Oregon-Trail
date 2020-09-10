package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        final int NUM_BUTTONS = 4;
        final int TRAIL_LENGTH = 2170;

        // Constants used for setting pace and rations
        final int NONE = 0;
        final int LOW = 1;
        final int MEDIUM = 2;
        final int HIGH = 3;

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        // Initializing UI elements.
        Label distanceLabel = (Label) root.lookup("#distanceLabel");
        Label statusLabel = (Label) root.lookup("#statusLabel");
        Label inputLabel = (Label) root.lookup("#distanceLabel");

        Button[] buttons = new Button[NUM_BUTTONS];
        for (int i = 0; i < NUM_BUTTONS; i++) {
            buttons[i] = (Button) root.lookup(String.format("#button%d", i));
        }

        TextField inputField = (TextField) root.lookup("#inputField");
        ComboBox optionBox = (ComboBox) root.lookup("#optionBox");


        // Importing asset data from CSV files.

        // Create the list of items a pioneer would take on the journey to Oregon.
        // Importing the valid Items.
        ArrayList<Item> items = new ArrayList<Item>();

        // Create an InputStreamReader to read in the data file for Items.
        InputStreamReader itemIsr = null;
        try {
            itemIsr = new InputStreamReader(this.getClass().getResourceAsStream("/assets/Items.csv"));
        } catch(Exception e) {System.exit(1);}

        // Create a Scanner to read in individual data for setting up Items.
        Scanner itemScr = new Scanner(itemIsr);

        // Take the first line of the text file and discard it because it is just a header.
        String itemHeader = itemScr.nextLine();

        // Read from the Items file until it runs out of data.
        while (itemScr.hasNext()) {

            // Create a Scanner to process the individual line of data.
            Scanner itemData = new Scanner(itemScr.nextLine());
            itemData.useDelimiter(",");
            String type = itemData.next();
            double price = itemData.nextDouble();

            // Create a new Item based on the file data and add it to the list of existing Items.
            Item newItem = new Item(type, price, 0);
            items.add(newItem);
            itemData.close();
        }
        itemScr.close();

        // Create the many Forts a pioneer would visit along the Trail.
        // Importing the Forts
        ArrayList<Landmark> forts = new ArrayList<Landmark>();

        // Create an InputStreamReader to read in the data file for Forts.
        InputStreamReader fortIsr = null;
        try {
            fortIsr = new InputStreamReader(this.getClass().getResourceAsStream("/assets/Forts.csv"));
        } catch(Exception e) {System.exit(2);}

        // Create a Scanner to read in individual data for setting up Forts.
        Scanner fortScr = new Scanner(fortIsr);

        // Take the first line of the text file and discard it because it is just a header.
        String fortHeader = fortScr.nextLine();

        // Read from the Forts file until it runs out of data.
        while (fortScr.hasNext()) {

            // Create a Scanner to process the individual line of data.
            Scanner fortData = new Scanner(fortScr.nextLine());
            fortData.useDelimiter(",");
            String name = fortData.next();
            int location = fortData.nextInt();

            // Create a new Fort based on the file data and add it to the list of existing Forts.
            Fort newFort = new Fort(name, location);
            forts.add(newFort);
            fortData.close();
        }
        fortScr.close();

        // Create all the Obstacles a pioneer would have to cross to get to Oregon.
        // Importing the Obstacles.
        ArrayList<Landmark> obstacles = new ArrayList<Landmark>();

        // Create an InputStreamReader to read in the data file for Obstacles.
        InputStreamReader obstacleIsr = null;
        try {
            obstacleIsr = new InputStreamReader(this.getClass().getResourceAsStream("/assets/Obstacles.csv"));
        } catch(Exception e) {System.exit(3);}

        // Create a Scanner to read in individual data for setting up Obstacles.
        Scanner obstacleScr = new Scanner(obstacleIsr);

        // Take the first line of the text file and discard it because it is just a header.
        String obstacleHeader = obstacleScr.nextLine();

        // Read from the Obstacles file until it runs out of data.
        while (obstacleScr.hasNext()) {

            // Create a Scanner to process the individual line of data.
            Scanner obstacleData = new Scanner(obstacleScr.nextLine());
            obstacleData.useDelimiter(",");
            String name = obstacleData.next();
            int location = obstacleData.nextInt();
            int weightLimit = obstacleData.nextInt();

            // Create a new Obstacle based on the file data and add it to the list of existing Obstacles.
            Obstacle newObstacle = new Obstacle(name, location, weightLimit);
            obstacles.add(newObstacle);
            obstacleData.close();
        }
        obstacleScr.close();

        // Create all the people a pioneer would travel with.
        // Importing the People.
        ArrayList<Person> people = new ArrayList<Person>();

        // Create an InputStreamReader to read in the Person data file.
        InputStreamReader personIsr = null;
        try {
            personIsr = new InputStreamReader(this.getClass().getResourceAsStream("/assets/People.csv"));
        } catch(Exception e) {System.exit(4);}

        // Create a Scanner to read in individual data for setting up a Person.
        Scanner personScr = new Scanner(personIsr);

        // Take the first line of the text file and discard it because it is just a header.
        String personHeader = personScr.nextLine();

        // Read from the Person file until it runs out of data.
        while (personScr.hasNext()) {

            // Create a Scanner to process the individual line of data.
            Scanner personData = new Scanner(personScr.nextLine());
            personData.useDelimiter(",");
            String name = personData.next();
            String relation = personData.next();
            boolean isDiseased = Boolean.parseBoolean(personData.next());

            // Create a new Person based on the file data and add it to the list of existing People.
            Person newPerson = new Person(name, relation, isDiseased);
            people.add(newPerson);
            personData.close();
        }
        personScr.close();

        Wagon wagon = new Wagon();

        primaryStage.setTitle("Oregon Trail");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();

        // Setting up the UI
        setButtons(buttons, "Buy Items");

        // Top left Button EventHandler
        buttons[0].setOnAction(event -> {
            String buttonText = buttons[0].getText();
            setButtons(buttons, buttonText);
            switch (buttonText) {
                case "Move":
                    // Find the next Fort and Obstacle the Wagon will arrive at.
                    Landmark nextFort = findNext(wagon.getLocation(), forts);
                    Landmark nextObstacle = findNext(wagon.getLocation(), obstacles);

                    // Determine if the Wagon will reach the Fort or the Obstacle first and move the Wagon accordingly.
                    if (nextObstacle.getLocation() < nextFort.getLocation()) {
                        wagon.move(nextObstacle.getLocation());
                    }
                    else {
                        wagon.move(nextFort.getLocation());
                    }

                    distanceLabel.setText("Distance to Oregon: " + (TRAIL_LENGTH - wagon.getLocation()) + " miles");

                    // Check if the Wagon moved to an Obstacle and update the Buttons accordingly.
                    if (wagon.getLocation() == nextObstacle.getLocation()) {
                        // Assume the Wagon moved to an Obstacle to save code.
                        setButtons(buttons, "Stopped at Obstacle");
                    }
                    // Check if the Wagon moved to a Fort and update the Buttons accordingly.
                    else if (wagon.getLocation() == nextFort.getLocation()) {
                        setButtons(buttons, "Stopped at Fort");
                    }
                    break;
                case "Buy Items": break;
                case "Cross over":
                    // Find the Obstacle the wagon is at and try to cross it.
                    Obstacle thisObstacle = (Obstacle) findNext(wagon.getLocation() - 1, obstacles);            // Subtracted 1 from wagon location so it doesn't skip to the next Obstacle.
                    if (thisObstacle.crossObstacle(items)) {
                        statusLabel.setText("You made the crossing successfully");
                    }
                    else {
                        statusLabel.setText("You underestimated the " + nextObstacle.getName() + " and lost some supplies");
                    }
                    break;
                case "Slow":
                    // Set a slow pace and keep moving.
                    setButtons(buttons, "Moving");
                    wagon.setPace(LOW);
                    break;
                case "Meager":
                    // Set meager rations and keep moving
                    setButtons(buttons, "Moving");
                    Person.setRations(LOW);
                    break;
            }
        });

        // Top right Button EventHandler
        buttons[1].setOnAction(event -> {
            String buttonText = buttons[1].getText();
            setButtons(buttons, buttonText);
            switch (buttonText) {
                case "Set Pace": break;
                case "Meet the Locals":
                    statusLabel.setText("Your social anxiety prevents you from interacting with the locals");
                    break;
                case "Check Weight Limit": break;
                    // Find the Obstacle the wagon is at and find its weight limit
                    Obstacle thisObstacle = (Obstacle) findNext(wagon.getLocation() - 1, obstacles);            // Subtracted 1 from wagon location so it doesn't skip to the next Obstacle.
                    statusLabel.setText("The " + thisObstacle.getName() + " can be crossed with " +
                                        thisObstacle.getWeightLimit());
                case "Medium":
                    // Set a medium pace and keep moving.
                    setButtons(buttons, "Moving");
                    wagon.setPace(MED_PACE);
                    break;
                case "Healthy": break;
                    // Set healthy rations and keep moving.
                    setButtons(buttons, "Moving");
                    Persion.setRations(MED);
            }
        });

        // Bottom left Button EventHandler
        buttons[2].setOnAction(event -> {
            String buttonText = buttons[2].getText();
            setButtons(buttons, buttonText);
            switch (buttonText) {
                case "Set Rations": break;
                case "Check Supplies": break;
                case "Discard Supplies": break;
                case "Fast":
                    // Set a high pace and keep moving.
                    setButtons(buttons, "Moving");
                    wagon.setPace(HIGH);
                    break;
                case "Generous": break;
                    // Set generous rations and keep moving.
                    setButtons(buttons, "Moving");
                    Persion.setRations(MED);
            }
        });

        // Bottom right Button EventHandler
        buttons[3].setOnAction(event -> {
            String buttonText = buttons[3].getText();
            setButtons(buttons, buttonText);
            switch (buttonText) {
                case "Check the Map": break;
                case "Keep Moving": break;
                case "Leave the Shop": break;
                case "Pay Someone to Help": break;
                case "Stop":
                    setButtons(buttons, "Moving");
                    wagon.setPace(STOP_PACE);
                    break;
                case "Nothing": break;

            }
        });
        /* This isn't finished...
        inputField.setOnAction(event -> {
            switch () {
                case "Set Rations":

            }
        });

        buttons[3].setOnAction(event -> {
            switch () {
                case "Set Rations":

            }
        });
        */
    }

    public void setButtons(Button[] buttons, String state) {
        switch (state) {
            case "Moving":
                //inputLabel.setText("");
                buttons[0].setText("Move");
                buttons[1].setText("Set Pace");
                buttons[2].setText("Set Rations");
                buttons[3].setText("Check the Map");
                break;
            case "Stopped at Fort":
                //inputLabel.setText("");
                buttons[0].setText("Buy Items");
                buttons[1].setText("Meet the Locals");
                buttons[2].setText("Check Supplies");
                buttons[3].setText("Keep Moving");
                break;
            case "Buy Items":
                //inputLabel.setText("Select which item and enter how many to buy:");
                buttons[0].setText("");
                buttons[1].setText("");
                buttons[2].setText("");
                buttons[3].setText("Leave the Shop");
                //optionBox.setDisable(false);
                break;
            case "Stopped at Obstacle":
                buttons[0].setText("Cross Over");
                buttons[1].setText("Check Weight Limit");
                buttons[2].setText("Discard Supplies");
                buttons[3].setText("Pay Someone to Help");
                break;
            case "Set Pace":
                buttons[0].setText("Slow");
                buttons[1].setText("Medium");
                buttons[2].setText("Fast");
                buttons[3].setText("Stop");
                break;
            case "Set Rations":
                buttons[0].setText("Meager");
                buttons[1].setText("Healthy");
                buttons[2].setText("Generous");
                buttons[3].setText("Nothing");
                break;
            default: return;
        }
    }

    public Landmark findNext(int wagonLoc, ArrayList<Landmark> landmarks) {
        for (int i = 0; i < landmarks.size(); i++) {
            if (landmarks.get(i).getLocation() > wagonLoc) {
                return landmarks.get(i);
            }
        }
        return landmarks.get(landmarks.size() - 1);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
