package engine.alert_generator;

import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * Class used for generating pop-up alert messages. Since, the generator bases on <code>Alert</code> class, which is an obect of JavaFx library,
 * the generator checks which thread called method from this class in order to use delegate object update to JavaFx thread.
 */
public class AlertGenerator {

    /**
     * Constructor used for creating an instance of class <code>AlertGenerator</code>.
     */
    public AlertGenerator(){ }

    /**
     * Method used for showing an alert with intended type, header and content. If the thread calling this method is not the JavaFx thread,
     * the build of the alert will be executed in <code>Platform.runLater(Runnable runnable)</code> method.
     * @param alertType Enum constant of enum type <code>Alert.AlertType</code>.
     * @param headerText String representation of text shown in the header of the alert.
     * @param contentText String that will be shown in the content section of the alert.
     */
    void showAlert(Alert.AlertType alertType, String headerText, String contentText){
        if ((Thread.currentThread().toString().contains("Weather updater"))) {
            Platform.runLater(() -> buildAlert(alertType, headerText, contentText));
        } else {
            buildAlert(alertType, headerText, contentText);
        }
    }

    /**
     * Method used for alert creation. It sets the title to "Message" and other parameters to appropriate argument values.
     * @param alertType Enum constant of enum type <code>Alert.AlertType</code>.
     * @param headerText String representation of text shown in the header of the alert.
     * @param contentText String that will be shown in the content section of the alert.
     */
    private void buildAlert(Alert.AlertType alertType, String headerText, String contentText){
        Alert alert = new Alert(alertType);
        alert.setTitle("Message");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

}