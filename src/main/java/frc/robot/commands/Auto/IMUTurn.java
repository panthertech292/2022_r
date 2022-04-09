// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class IMUTurn extends CommandBase {
  private final DriveSubsystem DriveSubsystem;
  private double v_error;
  private double v_targetAngle;
  private double v_minSpeed;
  private double v_p;
  /** Creates a new IMUTurn. */
  public IMUTurn(DriveSubsystem s_DriveSubsystem, double angle, double p, double minSpeed) {
    DriveSubsystem = s_DriveSubsystem;
    v_targetAngle = angle;
    v_minSpeed = minSpeed;
    v_p = p;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_DriveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    DriveSubsystem.differentialTankDrive(0, 0);
    DriveSubsystem.resetGyro();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    v_error = (v_targetAngle - DriveSubsystem.getRobotAngle())*v_p;
    if (Math.abs(v_error) < Math.abs(v_minSpeed)){
      if (v_targetAngle > 0){
        v_error = v_minSpeed;
      }
      else{
        v_error = -v_minSpeed;
      }
      
    }
    DriveSubsystem.differentialTankDrive(v_error, -v_error);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    DriveSubsystem.differentialTankDrive(0, 0);
    DriveSubsystem.resetGyro();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(DriveSubsystem.getRobotAngle()) > Math.abs(v_targetAngle)){
      return true;
    }
    else{
      return false;
    }
  }
}
