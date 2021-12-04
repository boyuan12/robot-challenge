package com.team2073.robot;


import com.team2073.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import com.team2073.robot.triggers.JoystickTrigger;


public class OperatorInterface {
    private final ApplicationContext appCtx = ApplicationContext.getInstance();
    private final Joystick controller = appCtx.getController();
    private final JoystickButton a = new JoystickButton(controller, 1);
    private final JoystickButton lb = new JoystickButton(controller, 5);
    private final JoystickButton y = new JoystickButton(controller, 4);
    private final JoystickButton x = new JoystickButton(controller, 3);
    private final JoystickButton b = new JoystickButton(controller, 2);

    private final JoystickTrigger yAxis = new JoystickTrigger(controller);

    public void init() {
        yAxis.whenActive(new AxesCommand());
        y.toggleWhenPressed(new CruiseCommand()); // toggleWhenPressed
        x.whileHeld(new BackCommand());
        a.whileHeld(new HalfPowerCommand());
        b.whenActive(new RevolutionCommand());
        lb.whileHeld(new PulseCommand());
    }

}
