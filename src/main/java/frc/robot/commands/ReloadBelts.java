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
    BeltSubsystem.setFrontBelts(0);
    BeltSubsystem.setBackBelts(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //This could maybe me running all the time? Not sure need to consult with team.
    if (BeltSubsystem.getFrontBeltBallSensor() == true){
    BeltSubsystem.setFrontBelts(v_frontBeltSpeed);
    BeltSubsystem.setBackBelts(v_backBeltSpeed);
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
    if (BeltSubsystem.getBackBeltBallSensor() == true){
      return true;
    }
    else{
      return false;
    }
    
  }
}
