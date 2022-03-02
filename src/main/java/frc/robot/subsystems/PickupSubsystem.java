// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PickupConstants;

//Motors
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

//Senors & Encoders
import edu.wpi.first.wpilibj.DigitalInput;

public class PickupSubsystem extends SubsystemBase {
  /** Creates a new PickupSubsystem. */
  private final CANSparkMax PickupMotor;
  private final CANSparkMax PickupMotorArm;

  //Encoders & Switches
  private DigitalInput upArmSwitch;
  private DigitalInput downArmSwitch;

  //Variable
  private double v_pickupSpeed;
  private double v_pickupSpeedArm;

  public PickupSubsystem() {
    //Motors
    PickupMotor = new CANSparkMax(PickupConstants.kPickupMotor, MotorType.kBrushless);
    PickupMotorArm = new CANSparkMax(PickupConstants.kPickupMotorArm, MotorType.kBrushed);
    PickupMotor.restoreFactoryDefaults();
    PickupMotorArm.restoreFactoryDefaults();
    PickupMotor.setIdleMode(IdleMode.kCoast);
    PickupMotorArm.setIdleMode(IdleMode.kBrake);
    PickupMotor.setInverted(true);
    PickupMotorArm.setInverted(true);


    //Encoders & Switches
    upArmSwitch = new DigitalInput(PickupConstants.kPickupArmUpSwitch);
    downArmSwitch = new DigitalInput(PickupConstants.kPickupArmDownSwitch);
    //PickupMotorArmEncoder = PickupMotorArm.getEncoder();
  }

  //Motors
  public void setPickupMotorSpeed(double pickupspeed) {
    v_pickupSpeed = pickupspeed;
    PickupMotor.set(v_pickupSpeed);
  }
  public void setPickupArmMotorSpeed(double armpickupspeed){
    v_pickupSpeedArm = armpickupspeed;
    PickupMotorArm.set(v_pickupSpeedArm);
  }
  //Encoder & Limit Switches
  public boolean getArmUpLimitSwitch(){
    return upArmSwitch.get();
  }
  public boolean getArmDownLimitSwitch(){
    return downArmSwitch.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //System.out.println("Up switch reads: " + getArmUpLimitSwitch());
    //System.out.println("Down switch reads: " + getArmDownLimitSwitch());
  }
}
