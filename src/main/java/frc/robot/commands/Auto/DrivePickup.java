// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.PickupSubsystem;
import frc.robot.Constants.BeltConstants;
import frc.robot.Constants.PickupConstants;
import frc.robot.commands.PickupArmDownBelts;
import frc.robot.subsystems.BeltSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DrivePickup extends ParallelCommandGroup {
  private final DriveSubsystem DriveSubsystem;
  private final PickupSubsystem PickupSubsystem;
  private final BeltSubsystem BeltSubsystem;
  /** Creates a new DrivePickup. */
  public DrivePickup(DriveSubsystem s_DriveSubsystem, PickupSubsystem s_PickupSubsystem , BeltSubsystem s_BeltSubsystem) {
    DriveSubsystem = s_DriveSubsystem;
    PickupSubsystem = s_PickupSubsystem;
    BeltSubsystem = s_BeltSubsystem;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
    new AutoEncoderDriveForBack(s_DriveSubsystem, 13, .4),
    new PickupArmDownBelts(s_PickupSubsystem, s_BeltSubsystem, PickupConstants.kPickupArmSpeedDown, BeltConstants.kFrontBeltSpeed, BeltConstants.kBackBeltSpeed).withTimeout(3)
    );
  }
}
