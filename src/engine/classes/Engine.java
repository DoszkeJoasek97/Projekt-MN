package engine.classes;


import engine.alert_generator.AlertGenerator;
import engine.interfaces.JavaFxUpdater;
import integrator.classes.ChybaToTrzebaInaczejCalkowac;
import integrator.classes.FuelAnalyzer;
import integrator.classes.VerletIntegrator;
import integrator.intefaces.CalculateAcceleration;
import integrator.intefaces.ODEUpdate;

import java.time.LocalTime;

public class Engine implements JavaFxUpdater, Runnable { //się zastanawiam teraz czy ten updater powinien tu być
    //TODO implement me- instancja tej klasy ląduje w Controller

    private VerletIntegrator integrator = new VerletIntegrator(1);

    //TODO skopiowałem z mojego projektu cała klase do generowania alertów
    private AlertGenerator alertGenerator = new AlertGenerator();

    private Thread thread = new Thread();
    private volatile boolean isRunning = false;
    private LocalTime time = LocalTime.of(0, 0, 0);
    private CalculateAcceleration fuelAnalyzer = new FuelAnalyzer();

    private ODEUpdate chybaToTrzebaInaczejCalkowac = new ChybaToTrzebaInaczejCalkowac();

    /* zegar */
    public void start(){
        thread.start();
    }

    public void stop(){
        isRunning = false;
    }

    public void interrupt(){
        isRunning = false;
        thread.interrupt();
    }

    @Override
    public void run() {
        isRunning = true;

        double m = 1730.14;
        double v = 150;
        double h = 50000;

        double u = 1; //TODO to ma być pobierane

        while(isRunning){
            try{
                time = time.plusSeconds(1);//zwiększam licznik sekund o 1

                //TODO tu bedzie update prędkości spalania paliwa

                integrator.integrateStep(fuelAnalyzer, chybaToTrzebaInaczejCalkowac, u, m, v, h);
                Thread.sleep(1000);//co sekundę
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }



    /* pozostała funkcjonalność*/

    private void init(/* tu trafiają obiekty javaFX które bedziemy aktualizować */){
        //TODO implement me- nie można pakować obiektów javaFx do pola klasy- wywali błąd że nulle wsadzamy
    }

    @Override
    public void initUpdate() {
        //TODO tu ustawianie wartości obiektom FX
    }
    //    runLater(); //TODO to odpali metode wyżej- otoczoną już Platform.runLater()


}
