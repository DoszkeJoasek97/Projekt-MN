package integrator.classes;

import integrator.intefaces.CalculateAcceleration;
import integrator.intefaces.ODEUpdate;

public class VerletIntegrator {

    private double dt; //krok całkowania


    public VerletIntegrator(double dt) {
        this.dt = dt;
    }


    public void integrateStep(CalculateAcceleration calculateAcceleration, ODEUpdate odeUpdate, double tStart, double tStop, double x0, double v0) {

        double t = tStart;
        double x = x0;
        double v = v0;

        double vhalf;
        double xNew;
        double vNew;
        double a = calculateAcceleration.a(x);


             /*

             vNHalf = v + lastA/2;
             x = x + dt*vNHalf;
             lastA = calculateAcceleration.a(x);
             v = v + dt*lastA/2;
             t+= dt;
             */
        vhalf = v + dt * a / 2;
        xNew = x + dt * vhalf;

        a = calculateAcceleration.a(xNew);

        vNew = vhalf + dt * a / 2;

        x = xNew;
        v = vNew;

        odeUpdate.update(t, x, v);

        //H = L - L cos theta
        //E = 1/2 theta z kropką kwadrat - cos theta; ujemne to wibracje, dodatnie to libracje


    }


}
