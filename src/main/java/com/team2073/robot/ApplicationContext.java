package com.team2073.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.team2073.robot.subsystems.PulseSubsystem;
import com.team2073.robot.subsystems.SimpleSubsystem;
import edu.wpi.first.wpilibj.Joystick;

public class ApplicationContext {
    private static ApplicationContext instance;
    private static CANSparkMax motor;
    private static Joystick controller;
    private static SimpleSubsystem simpleSubsystem;
    private static PulseSubsystem pulseSubsystem;

    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    public CANSparkMax getMotor() {
        if(motor == null) {
            motor = new CANSparkMax(7, CANSparkMaxLowLevel.MotorType.kBrushless);
        }
        return motor;
    }

    public Joystick getController() {
        if(controller == null) {
            controller = new Joystick(0);
        }
        return controller;
    }

    public SimpleSubsystem getSimpleSubsystem() {
        if (simpleSubsystem == null) {
            simpleSubsystem = new SimpleSubsystem();
        }
        return simpleSubsystem;
    }

    public double getYValueForController() {
        getController();
        return controller.getRawAxis(0);
    }

    public double getLTriggerValue() {
        return controller.getRawAxis(2);
    }

    public double getRTriggerValue() {
        return controller.getRawAxis(3);
    }

    public boolean getLButton() {
        return controller.getRawButton(5);
    }


    public PulseSubsystem PulseSubsystem() {
        if (pulseSubsystem == null) {
            pulseSubsystem = new PulseSubsystem();
        }
        return pulseSubsystem;
    }
}