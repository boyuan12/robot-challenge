package com.team2073.robot.subsystems;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANError;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.team2073.common.periodic.AsyncPeriodicRunnable;
import com.team2073.common.util.Timer;
import com.team2073.robot.ApplicationContext;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;


public class SimpleSubsystem implements AsyncPeriodicRunnable {

    private final ApplicationContext appCtx = ApplicationContext.getInstance();
    private CANSparkMax motor = appCtx.getMotor();

    private SimpleSubsystemState currentState = SimpleSubsystemState.STOP;
    private double output = 0;

    private double startPos = motor.getEncoder().getPosition();

    private double cc = 0;

    private Timer t = new Timer();

    public SimpleSubsystem() {
        autoRegisterWithPeriodicRunner();
    }

    @Override
    public void onPeriodicAsync() {
        output = appCtx.getController().getRawAxis(1) * -1;
        t.start();
        switch (currentState) {
            case STOP:
                output = 0;
                break;
            case HALF_POWER:
                output = 0.5;
                break;
            case AXES:
                double yVal = appCtx.getController().getRawAxis(1) * -1;
                output = yVal;
                output = output * (1 - appCtx.getController().getRawAxis(2) + appCtx.getController().getRawAxis(3));
                break;
            case CRUISE:
                output = motor.get();

                if (cc == 0) {
                    cc = output;
                }

                if (appCtx.getController().getRawAxis(1) * -1 > output) {
                    output = appCtx.getController().getRawAxis(1) * -1;
                } else {
                    output = cc;
                }

                // System.out.println("Y Input: " + appCtx.getController().getRawAxis(1) * -1);
                // System.out.println("Cruise Output: " + motor.getAppliedOutput());
                // System.out.println("Cruise Control Speed: ");

                break;
            case BACK:
                while ((int)motor.getEncoder().getPosition() != (int)startPos) {
                    if ((int)motor.getEncoder().getPosition() > (int)startPos) {
                        output = -Math.abs(appCtx.getController().getRawAxis(1));
                    } else {
                        output = Math.abs(appCtx.getController().getRawAxis(1));;
                    }
                    motor.set(output);
                    System.out.println((int)startPos + " " + (int)motor.getEncoder().getPosition());
                }
                break;
            case PULSE:
                output = 0.25;
                System.out.println("Had 1 second passed: " + t.hasWaited(1000));
                break;
            default:
                yVal = appCtx.getController().getRawAxis(1);
                output = yVal;
                output = output * (1 - appCtx.getController().getRawAxis(2));
                output = output * (1 + appCtx.getController().getRawAxis(3));
                break;
        }

        if (output > 0.8 || output < -0.8) {
            output = 0.8 * (Math.abs(output)/output); // math.abs(output)/output give the sign
        } else if (output < 0.2 && output >= 0) {
            output = 0;
        } else if (output > -0.2 && output < 0)  {
            output = 0;
        }
        System.out.println(output);
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
        BACK,
        PULSE,
    }

}
