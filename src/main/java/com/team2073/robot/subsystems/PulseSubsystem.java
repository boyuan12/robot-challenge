package com.team2073.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.team2073.common.periodic.AsyncPeriodicRunnable;
import com.team2073.robot.ApplicationContext;
import edu.wpi.first.wpilibj.Timer;

public class PulseSubsystem implements AsyncPeriodicRunnable {
    private final ApplicationContext appCtx = ApplicationContext.getInstance();
    private CANSparkMax motor = appCtx.getMotor();

    private PulseSubsystem.PulseSubsystemState currentState = PulseSubsystem.PulseSubsystemState.STOP;
    private double output = 0;

    public PulseSubsystem() {
        autoRegisterWithPeriodicRunner();
    }

    @Override
    public void onPeriodicAsync() {
        switch (currentState) {
            case PULSE:
                System.out.println("PULSE COMMAND ACTIVATE!");
                motor.set(0.5);
                Timer.delay(1.5);
                motor.set(0.0);
                break;
            case STOP:
                output = 0;
                break;
        }
        motor.set(output);
    }

    public void setCurrentState(PulseSubsystem.PulseSubsystemState currentState) {
        this.currentState = currentState;
    }

    public enum PulseSubsystemState {
        STOP,
        PULSE
    }

}
