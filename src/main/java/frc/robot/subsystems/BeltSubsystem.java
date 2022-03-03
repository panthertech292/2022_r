// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BeltConstants;

//Motors
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//Sensors
import edu.wpi.first.wpilibj.DigitalInput;

public class BeltSubsystem extends SubsystemBase {
  //Motors
  private final CANSparkMax FrontBeltMotor;
  private final CANSparkMax BackBeltMotor;

  //Variables
  private double v_frontBeltSpeed;
  private double v_backBeltSpeed;

  //Sensors
  private DigitalInput frontBeltSensor;
  private DigitalInput backBeltSensor;

  /** Creates a new BeltSubsystem. */
  public BeltSubsystem() {
    //Motors
    FrontBeltMotor = new CANSparkMax(BeltConstants.kFrontBeltMotor, MotorType.kBrushless);
    BackBeltMotor = new CANSparkMax(BeltConstants.kBackBeltMotor, MotorType.kBrushless);
    FrontBeltMotor.restoreFactoryDefaults();
    BackBeltMotor.restoreFactoryDefaults();
    FrontBeltMotor.setIdleMode(IdleMode.kCoast);
    BackBeltMotor.setIdleMode(IdleMode.kCoast);
    FrontBeltMotor.setSmartCurrentLimit(BeltConstants.kBeltCurrentLimit);
    BackBeltMotor.setSmartCurrentLimit(BeltConstants.kBeltCurrentLimit);
    BackBeltMotor.setInverted(true);

    //Sensors
    frontBeltSensor = new DigitalInput(BeltConstants.kFrontBeltSensor);
    backBeltSensor = new DigitalInput(BeltConstants.kBackBeltSensor);
  }
  public void setFrontBelts(double frontbeltspeed){
    v_frontBeltSpeed = frontbeltspeed;
    FrontBeltMotor.set(v_frontBeltSpeed);
  }
  public void setBackBelts(double backbeltspeed){
    v_backBeltSpeed = backbeltspeed;
    BackBeltMotor.set(v_backBeltSpeed);
  }
  public boolean getFrontBeltBallSensor(){
    return frontBeltSensor.get();
  }
  public boolean getBackBeltBallSensor(){
    return backBeltSensor.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
