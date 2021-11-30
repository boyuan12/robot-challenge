package com.team2073.robot.triggers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class JoystickTrigger extends Trigger {

    private Joystick controller;

    public JoystickTrigger(Joystick controller) {
        this.controller = controller;
    }

    @Override
    public boolean get(){
        return Math.abs(controller.getY()) > .2;
    }
}
