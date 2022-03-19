// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

//Motor&Drive
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.CANSparkMax.IdleMode;

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
  private RelativeEncoder BackLeftMotorEncoder;
  private RelativeEncoder BackRightMotorEncoder;

  private final DifferentialDrive DifDrive;
  private double v_leftSpeed;
  private double v_rightSpeed;
  private double v_leftXSpeed;
  private double v_rightYSpeed;

  private boolean v_arcadeDrive;

  //Network Tables
  private NetworkTableEntry v_networkTableDriveMode;

  public DriveSubsystem() {
    //FROM THE BACK LOOKING FRONT
    FrontLeftMotor = new CANSparkMax(DriveConstants.kFrontLeftMotor, MotorType.kBrushless);
    FrontRightMotor = new CANSparkMax(DriveConstants.kFrontRightMotor, MotorType.kBrushless);
    BackLeftMotor = new CANSparkMax(DriveConstants.kBackLeftMotor, MotorType.kBrushless);
    BackRightMotor = new CANSparkMax(DriveConstants.kBackRightMotor, MotorType.kBrushless);
    FrontLeftMotor.restoreFactoryDefaults();
    FrontRightMotor.restoreFactoryDefaults();
    BackLeftMotor.restoreFactoryDefaults();
    BackRightMotor.restoreFactoryDefaults();
    BackLeftMotor.setInverted(true);
    FrontLeftMotor.setInverted(true);
    FrontRightMotor.setInverted(true);
    BackRightMotor.setInverted(true);
    FrontLeftMotor.setIdleMode(IdleMode.kBrake);
    FrontRightMotor.setIdleMode(IdleMode.kBrake);
    BackLeftMotor.setIdleMode(IdleMode.kBrake);
    BackRightMotor.setIdleMode(IdleMode.kBrake);
    FrontLeftMotor.setSmartCurrentLimit(DriveConstants.kDriveCurrentLimit);
    FrontRightMotor.setSmartCurrentLimit(DriveConstants.kDriveCurrentLimit);
    BackLeftMotor.setSmartCurrentLimit(DriveConstants.kDriveCurrentLimit);
    BackRightMotor.setSmartCurrentLimit(DriveConstants.kDriveCurrentLimit);
    LeftSide = new MotorControllerGroup(FrontLeftMotor, BackLeftMotor);
    RightSide = new MotorControllerGroup(FrontRightMotor, BackRightMotor);
    DifDrive = new DifferentialDrive(LeftSide,RightSide);

    //Drive Modes
    v_arcadeDrive = true;

    //Encoders & Sensors
    FrontLeftMotorEncoder = FrontLeftMotor.getEncoder();
    FrontRightMotorEncoder = FrontRightMotor.getEncoder();
    BackLeftMotorEncoder = BackLeftMotor.getEncoder();
    BackRightMotorEncoder = BackRightMotor.getEncoder();
    FrontLeftMotorEncoder.setPositionConversionFactor(2.3);
    FrontRightMotorEncoder.setPositionConversionFactor(2.3);
    BackLeftMotorEncoder.setPositionConversionFactor(2.3);
    BackRightMotorEncoder.setPositionConversionFactor(2.3);
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
    return -(FrontLeftMotorEncoder.getPosition());
  }
  public double getRightMotorEncoderPosition(){
    return (FrontRightMotorEncoder.getPosition());
  }
  public void zeroLeftMotorEncoderPosition(){
    FrontLeftMotorEncoder.setPosition(0);
    BackLeftMotorEncoder.setPosition(0);
  }
  public void zeroRightMotorEncoderPosition(){
    FrontRightMotorEncoder.setPosition(0);
    BackRightMotorEncoder.setPosition(0);
  }

  //Teleop
  public void differentialTankDrive(double leftspeed, double rightspeed){
    v_leftSpeed = -leftspeed;
    v_rightSpeed = rightspeed;
    DifDrive.tankDrive(v_leftSpeed,v_rightSpeed);
  }
  //Telop Drive
  public void differentialArcadeDrive(double leftXspeedTurn, double rightYspeed){
    v_leftXSpeed = -leftXspeedTurn;
    v_rightYSpeed = rightYspeed;
    DifDrive.arcadeDrive(v_leftXSpeed, v_rightYSpeed);
  }
  //Shuffleboard Handler
  public boolean isDriveModeArcade(){
    return v_networkTableDriveMode.getBoolean(true);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //System.out.println("LEFT" + getLeftMotorEncoderPosition());
    //System.out.println("RIGHT" + getRightMotorEncoderPosition());
  }
}
