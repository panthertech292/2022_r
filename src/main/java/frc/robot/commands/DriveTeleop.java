// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;

public class DriveTeleop extends CommandBase {
  private final DriveSubsystem DriveSubsystem;
  /** Creates a new DriveTeleop. */
  public DriveTeleop(DriveSubsystem s_DriveSubsystem) {
    DriveSubsystem = s_DriveSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_DriveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //DriveSubsystem.setLimeLightDriverCam();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //if (DriveSubsystem.isDriveModeArcade() == true){
    DriveSubsystem.differentialArcadeDrive(RobotContainer.getDriverLeftSpeedX(), RobotContainer.getDriverRightSpeed());
    //}
    //else{
    //  DriveSubsystem.differentialTankDrive(RobotContainer.getDriverLeftSpeed(), RobotContainer.getDriverRightSpeed());
    //}
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    DriveSubsystem.differentialTankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
