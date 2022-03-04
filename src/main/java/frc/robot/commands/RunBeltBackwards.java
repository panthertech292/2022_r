// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.BeltConstants;
import frc.robot.subsystems.BeltSubsystem;

public class RunBeltBackwards extends CommandBase {
  private final BeltSubsystem BeltSubsystem;
  /** Creates a new RunBelt. */
  public RunBeltBackwards(BeltSubsystem s_BeltSubsystem) {
    BeltSubsystem =  s_BeltSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_BeltSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    BeltSubsystem.setFrontBelts(BeltConstants.kFrontBeltSpeed*-1);
    BeltSubsystem.setBackBelts(BeltConstants.kBackBeltSpeed*-1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    BeltSubsystem.setFrontBelts(0);
    BeltSubsystem.setBackBelts(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
