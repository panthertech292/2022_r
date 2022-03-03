// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;




public class RunShooterPID extends CommandBase {
  private final ShooterSubsystem ShooterSubsystem;
  /** Creates a new RunShooter. */
  public RunShooterPID(ShooterSubsystem s_ShooterSubsystem, double lowP, double lowI, double lowD, double lowFF, double lowtargetRPM) {
    ShooterSubsystem = s_ShooterSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_ShooterSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ShooterSubsystem.setShooterMotorLowSpeed(0);
    ShooterSubsystem.setShooterMotorHighSpeed(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Need to do IRL testing here. Find the target RPM for the set speeds and make a method(or PID :( ) that makes the shooter run quicker to get to it's target RPM.
    //ShooterSubsystem.setShooterLowPID(lowP, lowI, lowD, lowFF, lowtargetRPM);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ShooterSubsystem.setShooterMotorLowSpeed(0);
    ShooterSubsystem.setShooterMotorHighSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
