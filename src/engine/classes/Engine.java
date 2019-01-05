package engine.classes;

import engine.alert_generator.AlertGenerator;
import engine.interfaces.JavaFxUpdater;
import integrator.classes.Integrator;

public class Engine implements JavaFxUpdater {

    //TODO implement me- instancja tej klasy ląduje w Controller
    private Integrator integrator = new Integrator();

    //TODO skopiowałem z mojego projektu cała klase do generowania alertów
    private AlertGenerator alertGenerator = new AlertGenerator();

    private void init(/* tu trafiają obiekty javaFX które bedziemy aktualizować */){
        //TODO implement me- nie można pakować obiektów javaFx do pola klasy- wywali błąd że nulle wsadzamy
    }


    @Override
    public void update() {
        //TODO tu ustawianie wartości obiektom FX
    }

    void sth(){
        runLater(); //TODO to odpali metode wyżej
    }

}
