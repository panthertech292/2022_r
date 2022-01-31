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

//Encoders & Sensors
import com.revrobotics.RelativeEncoder;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DriveSubsystem. */
  private final CANSparkMax FrontLeftMotor;
  private final CANSparkMax FrontRightMotor;
  private final CANSparkMax BackLeftMotor;
  private final CANSparkMax BackRightMotor;

  private final MotorControllerGroup LeftSide;
  private final MotorControllerGroup RightSide;

  //Encoders
  private RelativeEncoder FrontLeftMotorEncoder;
  private RelativeEncoder FrontRightMotorEncoder;

  private final DifferentialDrive DifDrive;
  private double v_leftSpeed;
  private double v_rightSpeed;
  private double v_leftXSpeed;
  private double v_rightYSpeed;

  //Drive Modes
  private boolean v_arcadeDrive;

  //Network Tables
  private NetworkTableEntry v_networkTableDriveMode;

  public DriveSubsystem() {
    FrontLeftMotor = new CANSparkMax(DriveConstants.kFrontLeftMotor, MotorType.kBrushless);
    FrontRightMotor = new CANSparkMax(DriveConstants.kFrontRightMotor, MotorType.kBrushless);
    BackLeftMotor = new CANSparkMax(DriveConstants.kBackLeftMotor, MotorType.kBrushless);
    BackRightMotor = new CANSparkMax(DriveConstants.kBackRightMotor, MotorType.kBrushless);

    LeftSide = new MotorControllerGroup(FrontLeftMotor, BackLeftMotor);
    RightSide = new MotorControllerGroup(FrontRightMotor, BackRightMotor);

    DifDrive = new DifferentialDrive(LeftSide,RightSide);

    //Drive Modes
    v_arcadeDrive = true;

    //Encoders & Sensors
    FrontLeftMotorEncoder = FrontLeftMotor.getEncoder();
    FrontLeftMotorEncoder.setPositionConversionFactor(2.4); //3.168 is math, not sure why so off
    
    FrontRightMotorEncoder = FrontRightMotor.getEncoder();
    //FrontRightMotorEncoder.setPositionConversionFactor(3);
    zeroLeftMotorEncoderPosition();
    zeroRightMotorEncoderPosition();
    

    //Netowrk Tables
    ShuffleboardTab MainTab = Shuffleboard.getTab("Main Tab");
    v_networkTableDriveMode = MainTab.add("Arcade Drive Enabled", v_arcadeDrive).withWidget(BuiltInWidgets.kToggleButton).getEntry();

  }
  //Encoders!
  public double getLeftMotorEncoderVelocity(){
    return -FrontLeftMotorEncoder.getVelocity();
  }
  public double getRightMotorEncoderVelocity(){
    return -FrontRightMotorEncoder.getVelocity();
  }
  public double getLeftMotorEncoderPosition(){
    return -FrontLeftMotorEncoder.getPosition();
  }
  public double getRightMotorEncoderPosition(){
    return -FrontRightMotorEncoder.getPosition();
  }
  public void zeroLeftMotorEncoderPosition(){
    FrontLeftMotorEncoder.setPosition(0);
  }
  public void zeroRightMotorEncoderPosition(){
    FrontRightMotorEncoder.setPosition(0);
  }

  //Teleop
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
  public void driveTeleop() {
    if(v_arcadeDrive == true){
      differentialArcadeDrive(RobotContainer.getLeftSpeedX(), RobotContainer.getRightSpeed());
    }
    else{
      differentialTankDrive((RobotContainer.getLeftSpeed()), RobotContainer.getRightSpeed());
    }
  }
  //Auto
  public void driveAuto(double autoLeftSpeed, double autoRightSpeed) {
    differentialTankDrive(autoLeftSpeed, autoRightSpeed);
  }

  //Shuffleboard Handler
  public void updateShuffleBoard(){
    v_arcadeDrive = v_networkTableDriveMode.getBoolean(true);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //Update Shuffleboard(Maybe I should stop making useless comments like these?)
    updateShuffleBoard();
    System.out.println(getLeftMotorEncoderVelocity());
  }
}
