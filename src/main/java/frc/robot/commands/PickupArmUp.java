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
    PickupSubsystem.setPickupArmMotorSpeed(0);
    PickupSubsystem.setPickupMotorSpeed(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (PickupSubsystem.getArmUpLimitSwitch() == true){
      //Stops arm when on limit switch.
      PickupSubsystem.setPickupArmMotorSpeed(0);
    }
    else{
      if (PickupSubsystem.getArmEncoderVelocity() < 260){
        //If arm is going slow, speed it up
        PickupSubsystem.setPickupArmMotorSpeed(.20); //Placeholder value
      }
      else{
        //Arm speed is fine, don't need to increase it
        PickupSubsystem.setPickupArmMotorSpeed(.05); //Placeholder value
      }
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
    return false;
  }
}
