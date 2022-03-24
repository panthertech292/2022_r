// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants.BeltConstants;
import frc.robot.Constants.PickupConstants;
import frc.robot.commands.PickupArmDownBelts;
import frc.robot.commands.RunShooter;
import frc.robot.subsystems.PickupSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.BeltSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class PickupRev extends ParallelCommandGroup {
  private final PickupSubsystem PickupSubsystem;
  private final BeltSubsystem BeltSubsystem;
  private final ShooterSubsystem ShooterSubsystem;
  private double v_lowSpeed;
  private double v_highSpeed;
  /** Creates a new PickupRev. */
  public PickupRev(PickupSubsystem s_PickupSubsystem , BeltSubsystem s_BeltSubsystem, ShooterSubsystem s_ShooterSubsystem, double lowSpeed, double highSpeed) {
    PickupSubsystem = s_PickupSubsystem;
    BeltSubsystem = s_BeltSubsystem;
    ShooterSubsystem = s_ShooterSubsystem;
    v_lowSpeed = lowSpeed;
    v_highSpeed = highSpeed;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new PickupArmDownBelts(s_PickupSubsystem, s_BeltSubsystem, PickupConstants.kPickupArmSpeedDown, BeltConstants.kFrontBeltSpeed, BeltConstants.kBackBeltSpeed),
      new RunShooter(s_ShooterSubsystem, v_lowSpeed, v_highSpeed)
    );
  }
}
