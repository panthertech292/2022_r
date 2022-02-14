// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.LiftConstants;
import frc.robot.subsystems.LiftSubsystem;

public class LiftExtend extends CommandBase {
  private final LiftSubsystem LiftSubsystem;
  /** Creates a new LiftExtend. */
  public LiftExtend(LiftSubsystem s_LiftSubsystem) {
    LiftSubsystem = s_LiftSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_LiftSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    LiftSubsystem.setBothArmMotors(LiftConstants.kArmExtendSpeed);
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    LiftSubsystem.setBothArmMotors(LiftConstants.kArmExtendSpeed);
    //LiftSubsystem.setRotationArmMotor(RobotContainer.getOperRightSpeedY());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    LiftSubsystem.setBothArmMotors(0);
    //LiftSubsystem.setRotationArmMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
