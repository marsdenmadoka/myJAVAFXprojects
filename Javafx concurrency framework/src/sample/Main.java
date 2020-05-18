package sample;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import static javafx.concurrent.Worker.State.READY;
import static javafx.concurrent.Worker.State.RUNNING;

public class Main extends Application {

    //Using task Class
    Button startButton = new Button("Start");
    Button cancelButton = new Button("Cancel");
    Button exitButton = new Button("Exit");
    // Create the task
    PrimeFinderTask task = new PrimeFinderTask();
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(final Stage stage)
    {
// Create the Event-Handlers for the Buttons
        startButton.setOnAction(new EventHandler <ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                startTask();
            }
        });
        exitButton.setOnAction(new EventHandler <ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                stage.close();
            }
        });
        cancelButton.setOnAction(new EventHandler <ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                task.cancel();
            }
        });
// Enable/Disable the Start and Cancel buttons
        startButton.disableProperty().bind(task.stateProperty().isNotEqualTo(READY)
);
        cancelButton.disableProperty().bind(task.stateProperty().isNotEqualTo(RUNNING));
// Create the GridPane
        GridPane pane = new WorkerStateGUI(task);
// Create the ButtonBox
        HBox buttonBox = new HBox(5, startButton, cancelButton, exitButton);
// Create the BorderPane
        BorderPane root = new BorderPane();
        root.setCenter(pane);
        root.setBottom(buttonBox);
// Set the Style-properties of the BorderPane
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");
// Create the Scene
        Scene scene = new Scene(root,500,400);
// Add the scene to the Stage
        stage.setScene(scene);
// Set the title of the Stage
        stage.setTitle("A Prime Number Finder Task");
// Display the Stage
        stage.show();
    }
    public void startTask()
    {
// Schedule the task on a background thread
        Thread backgroundThread = new Thread(task);
        backgroundThread.setDaemon(true);
        backgroundThread.start();
    }
}
/*
USING SERVICES


 Button startButton = new Button("Start");
    Button cancelButton = new Button("Cancel");
    Button exitButton = new Button("Exit");
    Button resetButton = new Button("Reset");
    boolean onceStarted = false;
    public static void main(String[] args)
    {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage)
    {
// Create the Event-Handlers for the Buttons
        startButton.setOnAction(new EventHandler <ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                if (onceStarted)
                {
                    service.restart();
                }
                else
                {
                    service.start();
                    onceStarted = true;
                    startButton.setText("Restart");
                }
            }
        });
        exitButton.setOnAction(new EventHandler <ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                Platform.exit();
            }
        });
        cancelButton.setOnAction(new EventHandler <ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                service.cancel();
            }
        });
        resetButton.setOnAction(new EventHandler <ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                service.reset();
            }
        });
// Enable/Disable the Reset and Cancel buttons
        cancelButton.disableProperty().bind(service.stateProperty().isNotEqualTo(
                RUNNING));
        resetButton.disableProperty().bind(Bindings.or(service.stateProperty().
                        isEqualTo(RUNNING),
                service.stateProperty().isEqualTo(SCHEDULED)));
// Create the GridPane
        GridPane pane = new WorkerStateGUI(service);
// Create the ButtonBox
        HBox buttonBox = new HBox(5, startButton, cancelButton, resetButton,
                exitButton);
// Create the BorderPane
        BorderPane root = new BorderPane();
        root.setCenter(pane);
        root.setBottom(buttonBox);
// Set the Style-properties of the BorderPane
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");
// Create the Scene
        Scene scene = new Scene(root,500,400);
// Add the scene to the Stage
        stage.setScene(scene);
// Set the title of the Stage
        stage.setTitle("A Prime Number Finder Task");
// Display the Stage
        stage.show();
    }
    // Create the service
    Service<ObservableList<Long>> service = new Service<ObservableList<Long>>()
    {
        @Override
        protected Task<ObservableList<Long>> createTask()
        {
            return new PrimeFinderTask();
        }
    };

 */


