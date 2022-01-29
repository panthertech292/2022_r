// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class AutoEncoderDriveForBack extends CommandBase {
  private final DriveSubsystem DriveSubsystem;
  private final double v_encoderDistance;
  private final double v_autoSpeed;
  
  /** Creates a new AutoEncoderDriveForBack. */
  public AutoEncoderDriveForBack(DriveSubsystem s_DriveSubsystem, double distance, double speed) {
    DriveSubsystem = s_DriveSubsystem;
    v_encoderDistance = distance;
    v_autoSpeed = speed;
    addRequirements(s_DriveSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    DriveSubsystem.zeroLeftMotorEncoderPosition();
    DriveSubsystem.driveAuto(0, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    DriveSubsystem.driveAuto(v_autoSpeed, v_autoSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    DriveSubsystem.driveAuto(0, 0);
    DriveSubsystem.zeroLeftMotorEncoderPosition();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (DriveSubsystem.getLeftMotorEncoderPosition() > v_encoderDistance);
  }
}