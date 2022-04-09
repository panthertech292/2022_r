// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveStraight extends CommandBase {
  private final DriveSubsystem DriveSubsystem;
  private double v_distance;
  private double v_speed;
  private double v_adjustedSpeed;
  private double v_error;
  private double v_p;
  /** Creates a new DriveStraight. */
  public DriveStraight(DriveSubsystem s_DriveSubsystem, double distance, double speed, double p) {
    DriveSubsystem = s_DriveSubsystem;
    v_distance = distance;
    v_speed = speed;
    v_p = p;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_DriveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    DriveSubsystem.differentialTankDrive(0, 0);
    DriveSubsystem.resetGyro();
    DriveSubsystem.zeroLeftMotorEncoderPosition();
    DriveSubsystem.zeroRightMotorEncoderPosition();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    v_adjustedSpeed = (DriveSubsystem.getLeftMotorEncoderVelocity() - DriveSubsystem.getRightMotorEncoderPosition())*v_p;
    if (Math.abs(v_adjustedSpeed) < v_speed){
      v_adjustedSpeed = v_speed;
    }
    DriveSubsystem.differentialTankDrive(v_speed, v_adjustedSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    DriveSubsystem.differentialTankDrive(0, 0);
    DriveSubsystem.resetGyro();
    DriveSubsystem.zeroLeftMotorEncoderPosition();
    DriveSubsystem.zeroRightMotorEncoderPosition();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (Math.abs(DriveSubsystem.getLeftMotorEncoderPosition()) > Math.abs(v_distance));
  }
}
