// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.PickupConstants;
import frc.robot.subsystems.BeltSubsystem;
import frc.robot.subsystems.PickupSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class PickupArmDownBeltsRev extends CommandBase {
  private final PickupSubsystem PickupSubsystem;
  private final BeltSubsystem BeltSubsystem;
  private final ShooterSubsystem ShooterSubsystem;
  private double v_frontBeltSpeed;
  private double v_backBeltSpeed;
  private double v_shooterLowSpeed;
  private double v_shooterHighSpeed;
  /** Creates a new PickupArmDownBeltsRev. */
  public PickupArmDownBeltsRev(PickupSubsystem s_PickupSubsystem, BeltSubsystem s_BeltSubsystem, ShooterSubsystem s_ShooterSubsystem, double frontbeltspeed, double backbeltspeed, double shooterLowSpeed, double shooterHighSpeed) {
    PickupSubsystem = s_PickupSubsystem;
    BeltSubsystem = s_BeltSubsystem;
    ShooterSubsystem = s_ShooterSubsystem;
    v_frontBeltSpeed = frontbeltspeed;
    v_backBeltSpeed = backbeltspeed;
    v_shooterLowSpeed = shooterLowSpeed;
    v_shooterHighSpeed = shooterHighSpeed;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_PickupSubsystem, s_BeltSubsystem, s_ShooterSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    PickupSubsystem.setPickupArmDown();
    PickupSubsystem.setPickupMotorSpeed(PickupConstants.kPickupMotorSpeed);
    BeltSubsystem.setFrontBelts(0);
    BeltSubsystem.setBackBelts(0);
    ShooterSubsystem.setShooterMotorLowSpeed(0);
    ShooterSubsystem.setShooterMotorHighSpeed(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    PickupSubsystem.setPickupMotorSpeed(PickupConstants.kPickupMotorSpeed);
    //Shooter rev
    if (BeltSubsystem.getBackBeltBallSensor() == true){
      ShooterSubsystem.setShooterMotorHighSpeed(v_shooterHighSpeed);
      ShooterSubsystem.setShooterMotorLowSpeed(v_shooterLowSpeed);
    }
    else{
      ShooterSubsystem.setShooterMotorHighSpeed(0);
      ShooterSubsystem.setShooterMotorLowSpeed(0);
    }
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
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    PickupSubsystem.setPickupMotorSpeed(0);
    BeltSubsystem.setFrontBelts(0);
    BeltSubsystem.setBackBelts(0);
    ShooterSubsystem.setShooterMotorLowSpeed(0);
    ShooterSubsystem.setShooterMotorHighSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
