// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.RobotContainer;
import frc.robot.Constants.DriveConstants;


public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DriveSubsystem. */
  private final CANSparkMax FrontLeftMotor;
  private final CANSparkMax FrontRightMotor;
  //private final CANSparkMax BackLeftMotor;
  //private final CANSparkMax BackRightMotor;

  //private final MotorControllerGroup LeftSide;
  //private final MotorControllerGroup RightSide;

  private final DifferentialDrive DifDrive;
  private double v_leftSpeed;
  private double v_rightSpeed;
  private double v_leftXSpeed;
  private double v_rightYSpeed;
  private double v_setPointLeft;
  private double v_setPointRight;

  private int c_modeTeleop;
  private int v_driveMode;
  private int c_modeSetPoint;

  public DriveSubsystem() {
    FrontLeftMotor = new CANSparkMax(DriveConstants.kFrontLeftMotor, MotorType.kBrushless);
    FrontRightMotor = new CANSparkMax(DriveConstants.kFrontRightMotor, MotorType.kBrushless);
    //BackLeftMotor = new CANSparkMax(DriveConstants.kBackLeftMotor, MotorType.kBrushless);
    //BackRightMotor = new CANSparkMax(DriveConstants.kBackRightMotor, MotorType.kBrushless);

    //LeftSide = new MotorControllerGroup(FrontLeftMotor, BackLeftMotor);
    //RightSide = new MotorControllerGroup(FrontRightMotor, BackRightMotor);

    //DifDrive = new DifferentialDrive(LeftSide,RightSide);
    DifDrive = new DifferentialDrive(FrontLeftMotor,FrontRightMotor);

    c_modeTeleop = 0;
    c_modeSetPoint = 1;
    v_driveMode = c_modeTeleop;

    /*
    Not sure if I need these. Gonna leave them here just in case I need them.
    addChild("DifDrive", DifDrive);
    addChild("Left Side", LeftSide);
    addChild("Right Side", RightSide); */
  }
  public void differentialTankDrive(double leftspeed, double rightspeed) {
    v_leftSpeed = -leftspeed;
    v_rightSpeed = rightspeed;
    DifDrive.tankDrive(v_leftSpeed, v_rightSpeed);
  }
  public void differentialArcadeDrive(double leftXspeed, double rightYspeed) {
    v_leftXSpeed = leftXspeed;
    v_rightYSpeed = rightYspeed;
    DifDrive.arcadeDrive(v_leftXSpeed, v_rightYSpeed);
  }
  //Auto
  public void driveModePowerSetPoint() {
    v_driveMode = c_modeSetPoint;
  }
  public void driveAuto() {
    differentialTankDrive(-v_setPointLeft, -v_setPointRight);
  }

  // Teleop
  public void driveModeTeleop() {
    v_driveMode = c_modeTeleop;
  }

  public void driveTeleop() {
    differentialTankDrive((RobotContainer.getLeftSpeed()), RobotContainer.getRightSpeed());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //Sets drive mode
    if (v_driveMode == c_modeTeleop) {
      driveTeleop();
    } else {
      driveAuto();
    }
  }
}
