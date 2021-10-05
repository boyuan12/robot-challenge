package com.team2073.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.team2073.common.periodic.AsyncPeriodicRunnable;
import com.team2073.robot.ApplicationContext;

public class SimpleSubsystem implements AsyncPeriodicRunnable {
    private final ApplicationContext appCtx = ApplicationContext.getInstance();
    private CANSparkMax motor = appCtx.getMotor();

    private SimpleSubsystemState currentState = SimpleSubsystemState.STOP;
    private double output = 0;


    public SimpleSubsystem() {
        autoRegisterWithPeriodicRunner();
    }

    @Override
    public void onPeriodicAsync() {
        switch (currentState) {
            case STOP:
                output = 0;
                break;
            case HALF_POWER:
                output = 0.5;
                break;
            case AXES:
                output = 0.5;
                break;
            default:
                output = 0;
                break;
        }
        motor.set(output);
    }

    public void setCurrentState(SimpleSubsystemState currentState) {
        this.currentState = currentState;
    }

    public enum SimpleSubsystemState {
        STOP,
        HALF_POWER,
        AXES,
    }


}
