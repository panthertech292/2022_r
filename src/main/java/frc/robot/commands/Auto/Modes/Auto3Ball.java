// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto.Modes;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.subsystems.BeltSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.PickupSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
//Commands
import frc.robot.commands.*;
import frc.robot.commands.Auto.AutoEncoderDriveForBack;
import frc.robot.commands.Auto.AutoTurn2;
import frc.robot.commands.Auto.DrivePickup;
import frc.robot.commands.Auto.Modes.*;
//Constants
import frc.robot.Constants.BeltConstants;
import frc.robot.Constants.PickupConstants;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Auto3Ball extends SequentialCommandGroup {
  private final DriveSubsystem DriveSubsystem;
  private final PickupSubsystem PickupSubsystem;
  private final ShooterSubsystem ShooterSubsystem;
  private final BeltSubsystem BeltSubsystem;
  /** Creates a new Auto3Ball. */
  public Auto3Ball(DriveSubsystem s_DriveSubsystem, PickupSubsystem s_PickupSubsystem, ShooterSubsystem s_ShooterSubsystem, BeltSubsystem s_BeltSubsystem) {
    DriveSubsystem = s_DriveSubsystem;
    PickupSubsystem = s_PickupSubsystem;
    ShooterSubsystem = s_ShooterSubsystem;
    BeltSubsystem = s_BeltSubsystem;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      //new RunShooterBelt(s_ShooterSubsystem, s_BeltSubsystem, 0.26, 0.26, 1460, BeltConstants.kFrontBeltSpeed, BeltConstants.kBackBeltSpeed).withTimeout(3), //Run the shooter
      //new PickupArmDownBelts(s_PickupSubsystem, s_BeltSubsystem, PickupConstants.kPickupArmSpeedDown, BeltConstants.kFrontBeltSpeed, BeltConstants.kBackBeltSpeed).withTimeout(0.5), //Put the arm down
      //new DrivePickup(s_DriveSubsystem, s_PickupSubsystem, s_BeltSubsystem), //Drive with pickup down to get ball
      //new RunShooterBelt(s_ShooterSubsystem, s_BeltSubsystem, 0.28, 0.28, 1550, BeltConstants.kFrontBeltSpeed, BeltConstants.kBackBeltSpeed).withTimeout(3), //Run the shooter
      //new PickupArmUp(s_PickupSubsystem, PickupConstants.kPickupArmSpeedUp).withTimeout(2), //Raise arm
      new AutoTurn2(s_DriveSubsystem, -.90, 360)
      //new AutoEncoderDriveForBack(s_DriveSubsystem, 40, 0.4)

      //drive pickup 80
    );
  }
}
