// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PickupConstant;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


import edu.wpi.first.wpilibj.DigitalInput;
import com.revrobotics.RelativeEncoder;

public class PickupSubsystem extends SubsystemBase {
  /** Creates a new PickupSubsystem. */
  private final CANSparkMax pickupMotor;
  private final CANSparkMax pickupMotorArm;

  //Encoders & Switches
  private DigitalInput upArmSwitch;
  private RelativeEncoder pickupMotorArmEncoder;

  //Variable
  private double v_pickupSpeed;
  private double v_pickupSpeedArm;

  public PickupSubsystem() {
    //Motors
    pickupMotor = new CANSparkMax(PickupConstant.kPickupMotor, MotorType.kBrushless);
    pickupMotorArm = new CANSparkMax(PickupConstant.kPickupMotorArm, MotorType.kBrushless);
    

    //Encoders & Switches
    upArmSwitch = new DigitalInput(PickupConstant.kPickupArmUpSwitch);
    pickupMotorArmEncoder = pickupMotorArm.getEncoder();

  }
  //Motors
  public void ChangePickupMotor(double pickupspeed) {
    v_pickupSpeed = pickupspeed;
  }
  public void ChangePickupArmMotor(double armpickupspeed){
    v_pickupSpeedArm = armpickupspeed;
    
  }
  //Encoder & Limit Switches
  public double getArmEncoderVelocity(){ //This is a placeholder function, don't know what Encoder they are using
    return pickupMotorArmEncoder.getVelocity();
  }
  public double getArmEncoderPosition(){
    return pickupMotorArmEncoder.getPosition();
  }
  public boolean getArmUpLimitSwitch(){
    return upArmSwitch.get();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //pickupMotorArm.set(v_pickupSpeedArm);
    //pickupMotorArm.set(v_pickupSpeedArm);
    //System.out.println(pickupMotorArmEncoder.getVelocity());
    //System.out.println(pickupMotorArmEncoder.getPosition());
    
    
      
  }
}
