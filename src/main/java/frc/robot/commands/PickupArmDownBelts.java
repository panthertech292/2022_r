// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.PickupConstants;
import frc.robot.subsystems.BeltSubsystem;
import frc.robot.subsystems.PickupSubsystem;

public class PickupArmDownBelts extends CommandBase {
  private final PickupSubsystem PickupSubsystem;
  private final BeltSubsystem BeltSubsystem;
  private double v_pickupSpeedArmDown;
  private double v_frontBeltSpeed;
  private double v_backBeltSpeed;
  /** Creates a new PickupArmUp. */
  public PickupArmDownBelts(PickupSubsystem s_PickupSubsystem, BeltSubsystem s_BeltSubsystem, double downSpeed, double frontbeltspeed, double backbeltspeed) {
    PickupSubsystem = s_PickupSubsystem;
    BeltSubsystem = s_BeltSubsystem;
    v_pickupSpeedArmDown = downSpeed;
    v_frontBeltSpeed = frontbeltspeed;
    v_backBeltSpeed = backbeltspeed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_PickupSubsystem, s_BeltSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    PickupSubsystem.setPickupArmMotorSpeed(0);
    PickupSubsystem.setPickupMotorSpeed(PickupConstants.kPickupMotorSpeed);
    BeltSubsystem.setFrontBelts(0);
    BeltSubsystem.setBackBelts(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    PickupSubsystem.setPickupMotorSpeed(PickupConstants.kPickupMotorSpeed);
    //Back Belts
    if (BeltSubsystem.getBackBeltBallSensor() == true){
      BeltSubsystem.setBackBelts(0);
    }
    else{
      BeltSubsystem.setBackBelts(v_backBeltSpeed);
    }
    //Front Belts
    if (BeltSubsystem.getFrontBeltBallSensor() == true && BeltSubsystem.getBackBeltBallSensor() == true){
      BeltSubsystem.setFrontBelts(0);
    }
    else{
      BeltSubsystem.setFrontBelts(v_frontBeltSpeed);
    }
    //Arm Control
    if (PickupSubsystem.getArmDownLimitSwitch() == false){
      PickupSubsystem.setPickupArmMotorSpeed(v_pickupSpeedArmDown);
    } 
    else{
      PickupSubsystem.setPickupArmMotorSpeed(0);
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    PickupSubsystem.setPickupArmMotorSpeed(0);
    PickupSubsystem.setPickupMotorSpeed(0);
    BeltSubsystem.setFrontBelts(0);
    BeltSubsystem.setBackBelts(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
