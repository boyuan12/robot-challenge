package com.team2073.robot.commands;

import com.team2073.common.command.AbstractLoggingCommand;
import com.team2073.robot.ApplicationContext;
import com.team2073.robot.subsystems.SimpleSubsystem;

public class BackCommand extends AbstractLoggingCommand {
    private ApplicationContext appCtx = new ApplicationContext().getInstance();
    private SimpleSubsystem simpleSubsystem = appCtx.getSimpleSubsystem();

    @Override
    protected void initializeDelegate() {
        simpleSubsystem.setCurrentState(SimpleSubsystem.SimpleSubsystemState.BACK);
    }

    @Override
    protected void endDelegate() {
        simpleSubsystem.setCurrentState(SimpleSubsystem.SimpleSubsystemState.AXES);
    }

    @Override
    protected boolean isFinishedDelegate() {
        return false;
    }
}
