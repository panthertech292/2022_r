// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//Motors
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BeltConstants;

public class BeltSubsystem extends SubsystemBase {
  //Motors
  private final CANSparkMax FrontBeltMotor;
  private final CANSparkMax BackBeltMotor;

  //Variables
  private double v_frontBeltSpeed;
  private double v_backBeltSpeed;
  /** Creates a new BeltSubsystem. */
  public BeltSubsystem() {
    //Motors
    FrontBeltMotor = new CANSparkMax(BeltConstants.kFrontBeltMotor, MotorType.kBrushless);
    BackBeltMotor = new CANSparkMax(BeltConstants.kBackBeltMotor, MotorType.kBrushless);
    FrontBeltMotor.restoreFactoryDefaults();
    BackBeltMotor.restoreFactoryDefaults();
    FrontBeltMotor.setIdleMode(IdleMode.kCoast);
    BackBeltMotor.setIdleMode(IdleMode.kCoast);
  }
  public void setFrontBelts(double frontbeltspeed){
    v_frontBeltSpeed = frontbeltspeed;
    FrontBeltMotor.set(v_frontBeltSpeed);
  }
  public void setBackBelts(double backbeltspeed){
    v_backBeltSpeed = backbeltspeed;
    BackBeltMotor.set(v_backBeltSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
