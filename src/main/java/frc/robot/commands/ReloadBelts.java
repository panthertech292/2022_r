// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BeltSubsystem;

public class ReloadBelts extends CommandBase {
  private final BeltSubsystem BeltSubsystem;
  private double v_frontBeltSpeed;
  private double v_backBeltSpeed;
  private boolean v_transiting;
  //private int v_mode;
  /** Creates a new ReloadBelts. */
  public ReloadBelts(BeltSubsystem s_BeltSubsystem, double frontbeltspeed, double backbeltspeed) {
    BeltSubsystem =  s_BeltSubsystem;
    v_frontBeltSpeed = frontbeltspeed;
    v_backBeltSpeed = backbeltspeed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_BeltSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //v_mode = 0;
    BeltSubsystem.setFrontBelts(0);
    BeltSubsystem.setBackBelts(0);
    v_transiting = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (v_transiting == true){
      BeltSubsystem.setFrontBelts(v_frontBeltSpeed);
      BeltSubsystem.setBackBelts(v_backBeltSpeed);
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