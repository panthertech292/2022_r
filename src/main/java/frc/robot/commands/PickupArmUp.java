// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PickupSubsystem;

public class PickupArmUp extends CommandBase {
  private final PickupSubsystem PickupSubsystem;
  private double v_pickupSpeedArmUp;
  /** Creates a new PickupArmUp. */
  public PickupArmUp(PickupSubsystem s_PickupSubsystem, double upSpeed) {
    PickupSubsystem = s_PickupSubsystem;
    v_pickupSpeedArmUp = upSpeed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_PickupSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    PickupSubsystem.setPickupArmMotorSpeed(0);
    PickupSubsystem.setPickupMotorSpeed(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (PickupSubsystem.getArmUpLimitSwitch() == false){
      PickupSubsystem.setPickupArmMotorSpeed(v_pickupSpeedArmUp);
    }
    else{
      PickupSubsystem.setPickupArmMotorSpeed(0.0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    PickupSubsystem.setPickupArmMotorSpeed(0);
    PickupSubsystem.setPickupMotorSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //return (PickupSubsystem.getArmUpLimitSwitch());
    return false;
  }
}
