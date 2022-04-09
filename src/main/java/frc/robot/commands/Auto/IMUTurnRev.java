// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.commands.RunShooter;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class IMUTurnRev extends ParallelRaceGroup {
  private final DriveSubsystem DriveSubsystem;
  private final ShooterSubsystem ShooterSubsystem;
  private double v_lowSpeed;
  private double v_highSpeed;
  private double v_angle;
  private double v_P;
  private double v_minSpeed;
  /** Creates a new IMUTurnRev. */
  public IMUTurnRev(DriveSubsystem s_DriveSubsystem, ShooterSubsystem s_ShooterSubsystem, double lowSpeed, double highSpeed, double angle, double p, double minSpeed) {
    DriveSubsystem = s_DriveSubsystem;
    ShooterSubsystem = s_ShooterSubsystem;
    v_lowSpeed = lowSpeed;
    v_highSpeed = highSpeed;
    v_angle = angle;
    v_P = p;
    v_minSpeed = minSpeed;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new IMUTurn(s_DriveSubsystem, v_angle, v_P, v_minSpeed),
      new RunShooter(s_ShooterSubsystem, v_lowSpeed, v_highSpeed)
    );
  }
}
