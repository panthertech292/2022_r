// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.BeltSubsystem;



public class RunShooterBelt extends CommandBase {
  private final ShooterSubsystem ShooterSubsystem;
  private final BeltSubsystem BeltSubsystem;

  private double v_shooterLowSpeed;
  private double v_shooterHighSpeed;
  private double v_targetRPM;
  private double v_frontBeltSpeed;
  private double v_backBeltSpeed;
  /** Creates a new RunShooter. */
  public RunShooterBelt(ShooterSubsystem s_ShooterSubsystem, BeltSubsystem s_BeltSubsystem, double shooterlowspeed, double shooterhighspeed, double targetRPM, double frontbeltspeed, double backbeltspeed) {
    ShooterSubsystem = s_ShooterSubsystem;
    BeltSubsystem = s_BeltSubsystem;
    v_shooterLowSpeed = shooterlowspeed;
    v_shooterHighSpeed = shooterhighspeed;
    v_targetRPM = targetRPM;
    v_frontBeltSpeed = frontbeltspeed;
    v_backBeltSpeed = backbeltspeed;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_ShooterSubsystem, s_BeltSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ShooterSubsystem.setShooterMotorLowSpeed(v_shooterLowSpeed);
    ShooterSubsystem.setShooterMotorHighSpeed(v_shooterHighSpeed);
    BeltSubsystem.setFrontBelts(0);
    BeltSubsystem.setBackBelts(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Need to do IRL testing here. Find the target RPM for the set speeds and make a method(or PID :( ) that makes the shooter run quicker to get to it's target RPM.
    ShooterSubsystem.setShooterMotorLowSpeed(v_shooterLowSpeed);
    ShooterSubsystem.setShooterMotorHighSpeed(v_shooterHighSpeed);
    if (ShooterSubsystem.getShooterMotorHighEncoderVelocity() > v_targetRPM){
      BeltSubsystem.setFrontBelts(v_frontBeltSpeed);
      BeltSubsystem.setBackBelts(v_backBeltSpeed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ShooterSubsystem.setShooterMotorLowSpeed(0);
    ShooterSubsystem.setShooterMotorHighSpeed(0);
    BeltSubsystem.setFrontBelts(0);
    BeltSubsystem.setBackBelts(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
