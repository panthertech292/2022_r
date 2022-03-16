// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.AutoConstants;
import frc.robot.subsystems.DriveSubsystem;
import java.lang.Math;

public class AutoTurn extends CommandBase {
  private final DriveSubsystem DriveSubsystem;
  private double v_turnSpeed;
  private double v_turnDegrees;
  private double v_driveDistance;
  /** Creates a new AutoTurn. */
  public AutoTurn(DriveSubsystem s_DriveSubsystem, double speed, double degrees) {
    DriveSubsystem = s_DriveSubsystem;
    v_turnSpeed = speed;
    v_turnDegrees = degrees;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_DriveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    DriveSubsystem.zeroLeftMotorEncoderPosition();
    DriveSubsystem.zeroRightMotorEncoderPosition();
    DriveSubsystem.driveAuto(0, 0);
    v_driveDistance = (2*3.14159265359*AutoConstants.kRobotRadius*(v_turnDegrees/360)); //2 x PI x Robot Radius x Degrees/360
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    DriveSubsystem.driveAuto(v_turnSpeed, -v_turnSpeed);
    System.out.println("Target distanve: " + v_driveDistance);
    System.out.println("Left Encoder" + (Math.abs(DriveSubsystem.getLeftMotorEncoderPosition()) + "Right Encoder: " + Math.abs(DriveSubsystem.getRightMotorEncoderPosition())));
    System.out.println("Left Speed" + (Math.abs(DriveSubsystem.getLeftMotorEncoderVelocity()) + "Right Speed: " + Math.abs(DriveSubsystem.getRightMotorEncoderVelocity())));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    DriveSubsystem.driveAuto(0, 0);
    DriveSubsystem.zeroLeftMotorEncoderPosition();
    DriveSubsystem.zeroRightMotorEncoderPosition();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(DriveSubsystem.getLeftMotorEncoderPosition()) > v_driveDistance || Math.abs(DriveSubsystem.getRightMotorEncoderPosition()) > v_driveDistance){
      return true;
    }
    else{
      return false;
    }

  }
}
