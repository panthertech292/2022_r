// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.AutoConstants;
import frc.robot.subsystems.DriveSubsystem;

//AutoTurn2 is dead! Long live AutoTurn3! (Please work, please)

public class AutoTurn3 extends CommandBase {
  private final DriveSubsystem DriveSubsystem;
  private double v_turnSpeed;
  private double v_turnDegrees;
  private double v_driveDistance;
  /** Creates a new AutoTurn3. */
  public AutoTurn3(DriveSubsystem s_DriveSubsystem, double speed, double degrees) {
    DriveSubsystem = s_DriveSubsystem;
    v_turnSpeed = speed;
    v_turnDegrees = degrees;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_DriveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    DriveSubsystem.differentialTankDrive(0, 0);
    DriveSubsystem.zeroLeftMotorEncoderPosition();
    DriveSubsystem.zeroRightMotorEncoderPosition();
    v_driveDistance = (2*3.14159265359*AutoConstants.kRobotRadius*2*(v_turnDegrees/360)); //2 x PI x Robot Radius * 2 x Degrees/360
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    DriveSubsystem.differentialTankDrive(0, v_turnSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    DriveSubsystem.differentialTankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(DriveSubsystem.getRightMotorEncoderPosition()) > Math.abs(v_driveDistance)){
      return true;
    }
    else{
      return false;
    }
  }
}
