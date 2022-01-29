// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PickupSubsystem;

public class PickupArmUp extends CommandBase {
  private final PickupSubsystem PickupSubsystem;
  /** Creates a new PickupArmUp. */
  public PickupArmUp(PickupSubsystem s_PickupSubsystem) {
    PickupSubsystem = s_PickupSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_PickupSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    PickupSubsystem.ChangePickupArmMotor(0);
    PickupSubsystem.ChangePickupMotor(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // if (PickupSubsystem.getArmUpLimitSwitch() == true){
    //  PickupSubsystem.ChangePickupArmMotor(.05);
    //}
    //else{
    //  PickupSubsystem.ChangePickupArmMotor(.05);
    //}
    if (PickupSubsystem.getArmEncoderVelocity() < 260){
      //System.out.println("Fast run");
      PickupSubsystem.ChangePickupArmMotor(.20);
    }
    else{
      //System.out.println("Slow run");
      PickupSubsystem.ChangePickupArmMotor(.05);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    PickupSubsystem.ChangePickupArmMotor(0);
    PickupSubsystem.ChangePickupMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
