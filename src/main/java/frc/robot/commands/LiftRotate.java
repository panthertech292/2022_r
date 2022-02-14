// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.LiftSubsystem;

public class LiftRotate extends CommandBase {
  private final LiftSubsystem LiftSubsystem;
  /** Creates a new LiftRotate. */
  public LiftRotate(LiftSubsystem s_LiftSubsystem) {
    LiftSubsystem = s_LiftSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_LiftSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    LiftSubsystem.setRotationArmMotor(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    LiftSubsystem.setRotationArmMotor(RobotContainer.getOperRightSpeedY());

    //This code is deprecated, but kept in case we need it.
    /*if (LiftSubsystem.getRotationArmRPM() < 80 && LiftSubsystem.getRotationArmRPM() > -80){
      //Run with more power because we are slow
      LiftSubsystem.setRotationArmMotor(RobotContainer.getOperRightSpeedY());
      System.out.println("Fast running @" + RobotContainer.getOperRightSpeedY());
    }
    else{
      //Run slower with less power
      LiftSubsystem.setRotationArmMotor(RobotContainer.getOperRightSpeedY()*0.1);
      System.out.println("Slow running @" + RobotContainer.getOperRightSpeedY()*0.1);
    }
    */

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    LiftSubsystem.setRotationArmMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
