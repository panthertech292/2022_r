// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.AutoConstants;
import frc.robot.subsystems.DriveSubsystem;

public class AutoTurn2 extends CommandBase {
  private final DriveSubsystem DriveSubsystem;
  private double v_turnSpeed;
  private double v_leftTurnSpeed;
  private double v_rightTurnSpeed;
  private double v_turnDegrees;
  private double v_driveDistance;
  /** Creates a new AutoTurn2. */
  public AutoTurn2(DriveSubsystem s_DriveSubsystem, double speed, double degrees) {
    DriveSubsystem = s_DriveSubsystem;
    v_turnSpeed = speed;
    v_turnDegrees = degrees * 1.15;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_DriveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    DriveSubsystem.differentialTankDrive(0, 0);
    DriveSubsystem.zeroLeftMotorEncoderPosition();
    DriveSubsystem.zeroRightMotorEncoderPosition();
    v_driveDistance = (2*3.14159265359*AutoConstants.kRobotRadius*(v_turnDegrees/360)); //2 x PI x Robot Radius x Degrees/360
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Math.abs(DriveSubsystem.getLeftMotorEncoderPosition()) < v_driveDistance){
      v_leftTurnSpeed = v_turnSpeed;
      //if (Math.abs(DriveSubsystem.getRightMotorEncoderVelocity()) > (Math.abs(DriveSubsystem.getLeftMotorEncoderVelocity() + 200.0))){
      //  v_leftTurnSpeed = v_turnSpeed + 0.1;
      //  System.out.println("BOOSTING LEFT SPEED");
      //}
      //else{
      //  v_leftTurnSpeed = v_turnSpeed;
      //}
    }
    else{
      v_leftTurnSpeed = 0;
    }

    if (Math.abs(DriveSubsystem.getRightMotorEncoderPosition()) < v_driveDistance){
      v_rightTurnSpeed = v_turnSpeed + 0.1;
      //if (Math.abs(DriveSubsystem.getLeftMotorEncoderVelocity()) > (Math.abs(DriveSubsystem.getRightMotorEncoderVelocity() + 200.0))){
      //  v_rightTurnSpeed = v_turnSpeed + 0.1;
      //  System.out.println("BOOSTING RIGHT SPEED");
      //}
      //else{
      //  v_rightTurnSpeed = v_turnSpeed;
      //}
    }
    else{
      v_rightTurnSpeed = 0;
    }
    
    //IF OVERSHOT
    /*
    if (Math.abs(DriveSubsystem.getLeftMotorEncoderPosition()) > v_driveDistance + 3){
      v_leftTurnSpeed = -v_turnSpeed/2;
      System.out.println("WE OVERSHOT, UPPING LEFT");
    }
    if (Math.abs(DriveSubsystem.getRightMotorEncoderPosition()) > v_driveDistance + 3){
      v_rightTurnSpeed = -v_turnSpeed/2;
      System.out.println("WE OVERSHOT, UPPING RIGHT");
    }
    */
    DriveSubsystem.differentialTankDrive(0, -v_rightTurnSpeed);
    System.out.println("Target distanve: " + v_driveDistance);
    System.out.println("Left Encoder" + (Math.abs(DriveSubsystem.getLeftMotorEncoderPosition()) + "Right Encoder: " + Math.abs(DriveSubsystem.getRightMotorEncoderPosition())));
    System.out.println("Left Speed" + (Math.abs(DriveSubsystem.getLeftMotorEncoderVelocity()) + "Right Speed: " + Math.abs(DriveSubsystem.getRightMotorEncoderVelocity())));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    DriveSubsystem.differentialTankDrive(0, 0);
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
