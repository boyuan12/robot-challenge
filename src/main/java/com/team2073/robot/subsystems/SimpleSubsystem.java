package com.team2073.robot.subsystems;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANError;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.team2073.common.periodic.AsyncPeriodicRunnable;
import com.team2073.robot.ApplicationContext;
import edu.wpi.first.wpilibj.Timer;

import javax.swing.*;


public class SimpleSubsystem implements AsyncPeriodicRunnable {

    private final ApplicationContext appCtx = ApplicationContext.getInstance();
    private CANSparkMax motor = appCtx.getMotor();

    private SimpleSubsystemState currentState = SimpleSubsystemState.STOP;
    private double output = 0;

    boolean breakInfLoop = false;

    private double startPos = motor.getEncoder().getPosition();

    public SimpleSubsystem() {
        autoRegisterWithPeriodicRunner();
    }

    @Override
    public void onPeriodicAsync() {
        Timer t = new Timer();
        switch (currentState) {
            case STOP:
                output = 0;
                break;
            case HALF_POWER:
                output = 0.5;
                break;
            case AXES:

                double yVal = appCtx.getYValueForController() * -1;

                // System.out.println(yVal);
                // System.out.println(motor.getEncoder().getPosition());
                output = yVal;

                if (output > 0.8 || output < -0.8) {
                    output = 0.8 * (Math.abs(output)/output); // math.abs(output)/output give the sign
                } else if (output < 0.2 && output >= 0) {
                    output = 0;
                } else if (output > -0.2 && output < 0)  {
                    output = 0;
                }

                output = output * (1 - appCtx.getLTriggerValue());
                output = output * (1 + appCtx.getRTriggerValue());

                System.out.println(output);

                break;
            case CRUISE:
                output = motor.get();
                System.out.println("Cruise Output: " + output);
                break;
//            case REVOLUTION:
//                // motor.getEncoder().setPosition(0);
//                CANError err = motor.getEncoder().setPosition(3000);
//                System.out.println(motor.getEncoder().getPosition());
//                break;
            case BACK:
                while ((int)motor.getEncoder().getPosition() != (int)startPos) {
                    if ((int)motor.getEncoder().getPosition() > (int)startPos) {
                        output = -0.1;
                    } else {
                        output = 0.1;
                    }
                    motor.set(output);
                    System.out.println((int)startPos + " " + (int)motor.getEncoder().getPosition());
                }
                break;
            case PULSE:
                output = 0.25;
                t.start();
                System.out.println("Current Time Elapsed: " + t.hasElapsed(1));
                break;
            default:
                output = 0;
                break;
        }
        // System.out.println("current motor position:" + motor.getEncoder().getPosition());
        motor.set(output);
    }

    public void setCurrentState(SimpleSubsystemState currentState) {
        this.currentState = currentState;
    }

    public enum SimpleSubsystemState {
        STOP,
        HALF_POWER,
        AXES,
        CRUISE,
//        REVOLUTION,
        BACK,
        PULSE,
    }

}
