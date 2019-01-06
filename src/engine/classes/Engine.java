package engine.classes;


import engine.alert_generator.AlertGenerator;
import engine.interfaces.JavaFxUpdater;
import integrator.classes.Integrator;
import integrator.intefaces.ODEUpdate;

import java.time.LocalTime;

public class Engine implements JavaFxUpdater, Runnable, ODEUpdate { //się zastanawiam teraz czy ten updater powinien tu być
    //TODO implement me- instancja tej klasy ląduje w Controller

    private Integrator integrator = new Integrator(1);

    //TODO skopiowałem z mojego projektu cała klase do generowania alertów
    private AlertGenerator alertGenerator = new AlertGenerator();

    private Thread thread;
    private volatile boolean isRunning = false;
    private LocalTime time = LocalTime.of(0, 0, 0);  //to chyba zbedne

    //volatile by były aktualizowane na bieżąco
    private double currentM = 1704.30; //TODO poprawic
    private double currentV = 150;
    private double currentH = 50000;


    /* zegar */
    public void start(){
        thread = new Thread(this, "myThread");
        System.out.println("app started");
        thread.start();
    }

    public void stop(){
        isRunning = false;
    }

    public void interrupt(){
        isRunning = false;
        thread.interrupt();
    }

    //TODO nie wiem dlaczego, ale nie odpala tej metody jak użyje start(), schematycznie wg AtomicClock1 robione


    @Override
    public void run() {
        System.out.println("app running");
        isRunning = true;

        double u = 1; //TODO to ma być pobierane z GUI

        while(isRunning){
            try{

                time = time.plusSeconds(1);//zwiększam licznik sekund o 1

                //TODO tu bedzie update prędkości spalania paliwa

                integrator.integrateStep(this, u, currentM, currentV, currentH);

                //zdefiniowałem toString wyswietlajacy czas i trzy liczone składowe
                System.out.println(this);

                Thread.sleep(1000);//co sekundę
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("interrupted");
            }
        }
    }


    @Override
    public String toString() {
        return "Engine{" +
                "time=" + time +
                ", currentM=" + currentM +
                ", currentV=" + currentV +
                ", currentH=" + currentH +
                '}';
    }

    /* pozostała funkcjonalność*/

    private void init(/* tu trafiają obiekty javaFX które bedziemy aktualizować */){
        //TODO implement me- nie można pakować obiektów javaFx do pola klasy- wywali błąd że nulle wsadzamy
    }

    @Override
    public void initUpdate() {
        //TODO tu ustawianie wartości obiektom FX
    }

    @Override
    public void update(double t, double x, double v) {
        currentM = t;
        currentV = x;
        currentH = v;
    }
    //    runLater(); //TODO to odpali metode initUpdate()- otoczoną już Platform.runLater()


}
