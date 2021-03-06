// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BeltSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ReloadBelts extends CommandBase {
  private final BeltSubsystem BeltSubsystem;
  private final ShooterSubsystem ShooterSubsystem;
  private double v_frontBeltSpeed;
  private double v_backBeltSpeed;
  private double v_timer;
  private boolean v_transiting;
  //private int v_mode;
  /** Creates a new ReloadBelts. */
  public ReloadBelts(BeltSubsystem s_BeltSubsystem, ShooterSubsystem s_ShooterSubsystem, double frontbeltspeed, double backbeltspeed) {
    BeltSubsystem =  s_BeltSubsystem;
    ShooterSubsystem = s_ShooterSubsystem;
    v_frontBeltSpeed = frontbeltspeed;
    v_backBeltSpeed = backbeltspeed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_BeltSubsystem, s_ShooterSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    BeltSubsystem.setFrontBelts(0);
    BeltSubsystem.setBackBelts(0);
    v_transiting = false;
    v_timer = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (v_transiting == true){
      BeltSubsystem.setFrontBelts(v_frontBeltSpeed);
      BeltSubsystem.setBackBelts(v_backBeltSpeed);
      v_timer = v_timer + 1;
    }
    else{
      BeltSubsystem.setFrontBelts(0);
      BeltSubsystem.setBackBelts(0);
    }
    if (BeltSubsystem.getBackBeltBallSensor() == false && BeltSubsystem.getFrontBeltBallSensor() == true){
      v_transiting = true;
    }
    if (BeltSubsystem.getBackBeltBallSensor() == true){
      v_transiting = false;
    }
    if (v_timer > 300){
      v_transiting = false;
      v_timer = 0;
    }
      
    if (BeltSubsystem.getBackBeltBallSensor() == true){
      ShooterSubsystem.setShooterMotorHighSpeed(.30);
      ShooterSubsystem.setShooterMotorLowSpeed(.30);
    }
    else{
      ShooterSubsystem.setShooterMotorHighSpeed(0);
      ShooterSubsystem.setShooterMotorLowSpeed(0);
    }
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    BeltSubsystem.setFrontBelts(0);
    BeltSubsystem.setBackBelts(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}