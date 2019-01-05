package engine.interfaces;

import javafx.application.Platform;

//TODO implementowane przez klasy, które muszą update-owac obeikty JFX
public interface JavaFxUpdater {
    //TODO ta metoda ma updateować pola klasy, przy użyciu
    void initUpdate();
    //TODO tej metody, która deleguje wykonanie update'u do wątku FX
    default void runLater(){
        Platform.runLater(()-> initUpdate());
    }

}
