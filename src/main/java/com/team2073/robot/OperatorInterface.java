package com.team2073.robot;


import com.team2073.common.trigger.ControllerTriggerTrigger;
import com.team2073.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OperatorInterface {
    private final ApplicationContext appCtx = ApplicationContext.getInstance();
    private final Joystick controller = appCtx.getController();
    private final JoystickButton a = new JoystickButton(controller, 1);
    private final JoystickButton lb = new JoystickButton(controller, 5);
    private final JoystickButton y = new JoystickButton(controller, 4);
    private final JoystickButton x = new JoystickButton(controller, 3);
    private final JoystickButton b = new JoystickButton(controller, 2);
    // private final JoystickButton yAxis = new JoystickButton(controller, 9);

    private final ControllerTriggerTrigger yAxis= new ControllerTriggerTrigger(controller, 0);

    public void init() {
        y.toggleWhenPressed(new CruiseCommand()); // toggleWhenPressed
        x.whileHeld(new BackCommand());
        a.whileHeld(new HalfPowerCommand());
        yAxis.whenActive(new AxesCommand());
        b.whileHeld(new PulseCommand());
    }


}
