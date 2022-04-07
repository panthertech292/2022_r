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
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class PickupSubsystem extends SubsystemBase {
  /** Creates a new PickupSubsystem. */
  private final CANSparkMax PickupMotor;
  private final DoubleSolenoid PickupSolenoidUp;
  private final DoubleSolenoid PickupSolenoidDown;

  //Variable
  private double v_pickupSpeed;

  public PickupSubsystem() {
    //Motors
    PickupMotor = new CANSparkMax(PickupConstants.kPickupMotor, MotorType.kBrushless);
    PickupMotor.restoreFactoryDefaults();
    PickupMotor.setIdleMode(IdleMode.kCoast);
    PickupMotor.setInverted(true);
    PickupMotor.setSmartCurrentLimit(PickupConstants.kPickup550CurrentLimit);
    PickupMotor.burnFlash();

    //Pneumatics

    PickupSolenoidUp = new DoubleSolenoid(PneumaticsModuleType.REVPH, PickupConstants.kPickupSolenoidUpRun, PickupConstants.kPickupSolenoidUpVent);
    PickupSolenoidDown = new DoubleSolenoid(PneumaticsModuleType.REVPH, PickupConstants.kPickupSolenoidDownRun, PickupConstants.kPickupSolenoidDownVent);
  }

  //Motors
  public void setPickupMotorSpeed(double pickupspeed) {
    v_pickupSpeed = pickupspeed;
    PickupMotor.set(v_pickupSpeed);
  }
  public void setPickupArmDown(){
    PickupSolenoidDown.set(Value.kForward);
    PickupSolenoidUp.set(Value.kReverse);
  }
  public void setPickupArmIdle(){
    PickupSolenoidDown.set(Value.kReverse);
    PickupSolenoidUp.set(Value.kReverse);
  }
  public void setPickupArmUp(){
    PickupSolenoidDown.set(Value.kReverse);
    PickupSolenoidUp.set(Value.kForward);
  }
  //Encoder & Limit Switches
  @Override
  public void periodic() {
  }
}
