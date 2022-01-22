// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

//Motor&Drive
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

//Shuffleboard & Network Tables
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.networktables.NetworkTableEntry;

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

  //Auto
  private double v_setPointLeft;
  private double v_setPointRight;

  //Drive Modes
  private boolean v_driveModeTeleop;
  private boolean v_arcadeDrive;

  //Network Tables
  private NetworkTableEntry v_networkTableDriveMode;

  public DriveSubsystem() {
    FrontLeftMotor = new CANSparkMax(DriveConstants.kFrontLeftMotor, MotorType.kBrushless);
    FrontRightMotor = new CANSparkMax(DriveConstants.kFrontRightMotor, MotorType.kBrushless);
    //BackLeftMotor = new CANSparkMax(DriveConstants.kBackLeftMotor, MotorType.kBrushless);
    //BackRightMotor = new CANSparkMax(DriveConstants.kBackRightMotor, MotorType.kBrushless);

    //LeftSide = new MotorControllerGroup(FrontLeftMotor, BackLeftMotor);
    //RightSide = new MotorControllerGroup(FrontRightMotor, BackRightMotor);

    //DifDrive = new DifferentialDrive(LeftSide,RightSide);
    DifDrive = new DifferentialDrive(FrontLeftMotor,FrontRightMotor);

    //Drive Modes
    v_driveModeTeleop = true;
    v_arcadeDrive = true;

    //Netowrk Tables
    ShuffleboardTab MainTab = Shuffleboard.getTab("Main Tab");
    v_networkTableDriveMode = MainTab.add("Arcade Drive Enabled", v_arcadeDrive).withWidget(BuiltInWidgets.kToggleButton).getEntry();

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
    v_driveModeTeleop = false;
  }
  public void driveAuto() {
    differentialTankDrive(-v_setPointLeft, -v_setPointRight);
  }

  // Teleop
  public void driveModeTeleop() {
    v_driveModeTeleop = true;
  }

  public void driveTeleop() {
    if(v_arcadeDrive == true){
      differentialArcadeDrive(RobotContainer.getLeftSpeedX(), RobotContainer.getRightSpeed());
    }
    else{
      differentialTankDrive((RobotContainer.getLeftSpeed()), RobotContainer.getRightSpeed());
    }
  }
  //Shuffleboard Handler
  public void updateShuffleBoard(){
    v_arcadeDrive = v_networkTableDriveMode.getBoolean(true);
  }
  public void driveMain(){
    if (v_driveModeTeleop == true){
      driveTeleop();
    }
    else{
      driveAuto();
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    //Sets drive mode
    driveMain();
    //Update Shuffleboard(Maybe I should stop making useless comments like these?)
    updateShuffleBoard();
  }
}
