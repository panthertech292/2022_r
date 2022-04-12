// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Vision;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.BeltSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.commands.RunShooterBelt;
import frc.robot.commands.Vision.VisionAngleAlign;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class VisionShoot extends SequentialCommandGroup {
  private final DriveSubsystem DriveSubsystem;
  private final ShooterSubsystem ShooterSubsystem;
  private final BeltSubsystem BeltSubsystem;
  private double v_minSpeed;
  private double v_angleP;
  private double v_distanceP;
  private double v_shooterLowSpeed;
  private double v_shooterHighSpeed;
  private double v_targetHighRPM;
  private double v_frontBeltSpeed;
  private double v_backBeltSpeed;
  private double v_distance;
  /** Creates a new VisionShoot. */
  public VisionShoot(DriveSubsystem s_DriveSubsystem, ShooterSubsystem s_ShooterSubsystem, BeltSubsystem s_BeltSubsystem, double minSpeed, double angleP, double distanceP, double shooterLowSpeed, double shooterHighSpeed, double targetRPM, double frontBeltSpeed, double backBeltSpeed, double distance) {
    DriveSubsystem = s_DriveSubsystem;
    ShooterSubsystem = s_ShooterSubsystem;
    BeltSubsystem = s_BeltSubsystem;

    v_minSpeed = minSpeed;
    v_angleP = angleP;
    v_distanceP = distanceP;
    v_shooterLowSpeed = shooterLowSpeed;
    v_shooterHighSpeed = shooterHighSpeed;
    v_targetHighRPM = targetRPM;
    v_frontBeltSpeed = frontBeltSpeed;
    v_backBeltSpeed = backBeltSpeed;
    v_distance = distance;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new VisionAngleAlign(s_DriveSubsystem, v_minSpeed, v_angleP),
      new VisionDistanceAlign(s_DriveSubsystem, v_minSpeed, v_distanceP, v_distance),
      new VisionAngleAlign(s_DriveSubsystem, v_minSpeed, v_angleP),
      new RunShooterBelt(s_ShooterSubsystem, s_BeltSubsystem, v_shooterLowSpeed, v_shooterHighSpeed, v_targetHighRPM, v_frontBeltSpeed, v_backBeltSpeed)
    );
  }
}
