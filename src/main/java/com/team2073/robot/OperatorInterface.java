package com.team2073.robot;

import com.team2073.robot.commands.HalfPowerCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OperatorInterface {
    private final ApplicationContext appCtx = ApplicationContext.getInstance();
    private final Joystick controller = appCtx.getController();
    private final JoystickButton a = new JoystickButton(controller, 1);

    public void init() {
        a.whileHeld(new HalfPowerCommand());
    }

    public double getYAxis() {
        return controller.getY();
    }


}
