// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LiftConstants;

//Motors
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class LiftSubsystem extends SubsystemBase {
  //Motors
  private final CANSparkMax LeftArmMotor;
  private final CANSparkMax RightArmMotor;
  //Pneumatics
  private final DoubleSolenoid RotationCylinder;

  //Variables
  private double v_armSpeed;


  /** Creates a new LiftSubsystem. */
  public LiftSubsystem() {
    //Motors
    LeftArmMotor = new CANSparkMax(LiftConstants.kLeftArmMotor, MotorType.kBrushless);
    RightArmMotor = new CANSparkMax(LiftConstants.kRightArmMotor, MotorType.kBrushless);

    LeftArmMotor.restoreFactoryDefaults();
    RightArmMotor.restoreFactoryDefaults();

    LeftArmMotor.setIdleMode(IdleMode.kBrake);
    RightArmMotor.setIdleMode(IdleMode.kBrake);

    LeftArmMotor.setInverted(false);
    RightArmMotor.setInverted(true);

    LeftArmMotor.setSmartCurrentLimit(LiftConstants.kLiftCurrentLimit);
    RightArmMotor.setSmartCurrentLimit(LiftConstants.kLiftCurrentLimit);

    RotationCylinder = new DoubleSolenoid(PneumaticsModuleType.REVPH, LiftConstants.kLiftSolenoidExtend, LiftConstants.kLiftSolenoidRetract);
  }
  public void setBothArmMotors(double armspeed){
    v_armSpeed = armspeed;
    LeftArmMotor.set(v_armSpeed);
    RightArmMotor.set(v_armSpeed);
  }
  public void setRotationCylinderExtended(){
    RotationCylinder.set(Value.kForward);
  }
  public void setRotationCylinderRetracted(){
    RotationCylinder.set(Value.kReverse);
  }
  public void setRotationCylinderOff(){
    RotationCylinder.set(Value.kOff);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}