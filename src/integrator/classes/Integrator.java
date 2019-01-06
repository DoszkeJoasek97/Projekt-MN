package integrator.classes;

import integrator.intefaces.ODEUpdate;

public class Integrator {

    private double dt;
    private final double G = 2.04; //nie pamietam ile bylo, robie bez neta

    public Integrator(double dt){
        this.dt = dt;
    }

    public void integrateStep(ODEUpdate update, double u, double m, double v, double h){

        double newM, newV, newH;

        newM = m - u * dt;//u jest dodatnie, zakładam
        newH = h - v*dt;
        newV = v - G*dt + v*u/m*dt;//zły wzór na pewno

        update.update(newM, newV, newH);
    }

}
