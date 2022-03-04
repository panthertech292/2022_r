// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LiftConstants;

//Motors
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

//Sensors & Encoders
//import com.revrobotics.RelativeEncoder;

public class LiftSubsystem extends SubsystemBase {
  //Motors
  private final CANSparkMax RotationArmMotor;
  private final CANSparkMax LeftArmMotor;
  private final CANSparkMax RightArmMotor;

  //Variables
  private double v_rotationSpeed;
  private double v_armSpeed;

  /** Creates a new LiftSubsystem. */
  public LiftSubsystem() {
    //Motors
    RotationArmMotor = new CANSparkMax(LiftConstants.kRotationArmMotor, MotorType.kBrushed); //This is not a NEO
    LeftArmMotor = new CANSparkMax(LiftConstants.kLeftArmMotor, MotorType.kBrushless);
    RightArmMotor = new CANSparkMax(LiftConstants.kRightArmMotor, MotorType.kBrushless);

    RotationArmMotor.restoreFactoryDefaults();
    LeftArmMotor.restoreFactoryDefaults();
    RightArmMotor.restoreFactoryDefaults();

    RotationArmMotor.setIdleMode(IdleMode.kBrake);
    LeftArmMotor.setIdleMode(IdleMode.kBrake);
    RightArmMotor.setIdleMode(IdleMode.kBrake);

    LeftArmMotor.setInverted(false);
    RightArmMotor.setInverted(true);

    RotationArmMotor.setSmartCurrentLimit(LiftConstants.kLiftCurrentLimit);
    LeftArmMotor.setSmartCurrentLimit(LiftConstants.kLiftCurrentLimit);
    RightArmMotor.setSmartCurrentLimit(LiftConstants.kLiftCurrentLimit);
  }
  public void setRotationArmMotor(double rotationspeed){
    v_rotationSpeed = rotationspeed;
    RotationArmMotor.set(v_rotationSpeed);
  }
  public void setBothArmMotors(double armspeed){
    v_armSpeed = armspeed;
    LeftArmMotor.set(v_armSpeed);
    RightArmMotor.set(v_armSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}