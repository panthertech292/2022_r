// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto.Modes;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.BeltConstants;
import frc.robot.commands.PickupArmDownBelts;
import frc.robot.commands.PickupArmUp;
import frc.robot.commands.RunShooterBelt;
import frc.robot.commands.Auto.AutoEncoderDriveForBack;
import frc.robot.commands.Auto.DrivePickup;
import frc.robot.subsystems.BeltSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.PickupSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DualShot extends SequentialCommandGroup {
  private final DriveSubsystem DriveSubsystem;
  private final PickupSubsystem PickupSubsystem;
  private final ShooterSubsystem ShooterSubsystem;
  private final BeltSubsystem BeltSubsystem;
  /** Creates a new DualShot. */
  public DualShot(DriveSubsystem s_DriveSubsystem, PickupSubsystem s_PickupSubsystem, ShooterSubsystem s_ShooterSubsystem, BeltSubsystem s_BeltSubsystem) {
    DriveSubsystem = s_DriveSubsystem;
    PickupSubsystem = s_PickupSubsystem;
    ShooterSubsystem = s_ShooterSubsystem;
    BeltSubsystem = s_BeltSubsystem;

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
    new PickupArmDownBelts(s_PickupSubsystem, s_BeltSubsystem, BeltConstants.kFrontBeltSpeed, BeltConstants.kBackBeltSpeed).withTimeout(0.5),
    new DrivePickup(s_DriveSubsystem, s_PickupSubsystem, s_BeltSubsystem, 38, .35), //Drive with pickup down to get ball
    new PickupArmDownBelts(s_PickupSubsystem, s_BeltSubsystem, BeltConstants.kFrontBeltSpeed, BeltConstants.kBackBeltSpeed).withTimeout(1),
    new RunShooterBelt(s_ShooterSubsystem, s_BeltSubsystem, 0.28, 0.28, 1570, BeltConstants.kFrontBeltSpeed, BeltConstants.kBackBeltSpeed).withTimeout(4), //Run the shooter
    new PickupArmUp(s_PickupSubsystem).withTimeout(2), //Raise arm
    new AutoEncoderDriveForBack(s_DriveSubsystem, 10, .30) //Drive back to get off line
    );
  }
}























































/* ASCII Art to help improve success of auto
⠄⠄⠄⠄⢠⣿⣿⣿⣿⣿⢻⣿⣿⣿⣿⣿⣿⣿⣿⣯⢻⣿⣿⣿⣿⣆⠄⠄⠄
⠄⠄⣼⢀⣿⣿⣿⣿⣏⡏⠄⠹⣿⣿⣿⣿⣿⣿⣿⣿⣧⢻⣿⣿⣿⣿⡆⠄⠄
⠄⠄⡟⣼⣿⣿⣿⣿⣿⠄⠄⠄⠈⠻⣿⣿⣿⣿⣿⣿⣿⣇⢻⣿⣿⣿⣿⠄⠄
⠄⢰⠃⣿⣿⠿⣿⣿⣿⠄⠄⠄⠄⠄⠄⠙⠿⣿⣿⣿⣿⣿⠄⢿⣿⣿⣿⡄⠄
⠄⢸⢠⣿⣿⣧⡙⣿⣿⡆⠄⠄⠄⠄⠄⠄⠄⠈⠛⢿⣿⣿⡇⠸⣿⡿⣸⡇⠄
⠄⠈⡆⣿⣿⣿⣿⣦⡙⠳⠄⠄⠄⠄⠄⠄⢀⣠⣤⣀⣈⠙⠃⠄⠿⢇⣿⡇⠄
⠄⠄⡇⢿⣿⣿⣿⣿⡇⠄⠄⠄⠄⠄⣠⣶⣿⣿⣿⣿⣿⣿⣷⣆⡀⣼⣿⡇⠄
⠄⠄⢹⡘⣿⣿⣿⢿⣷⡀⠄⢀⣴⣾⣟⠉⠉⠉⠉⣽⣿⣿⣿⣿⠇⢹⣿⠃⠄
⠄⠄⠄⢷⡘⢿⣿⣎⢻⣷⠰⣿⣿⣿⣿⣦⣀⣀⣴⣿⣿⣿⠟⢫⡾⢸⡟⠄.
⠄⠄⠄⠄⠻⣦⡙⠿⣧⠙⢷⠙⠻⠿⢿⡿⠿⠿⠛⠋⠉⠄⠂⠘⠁⠞⠄⠄⠄
⠄⠄⠄⠄⠄⠈⠙⠑⣠⣤⣴⡖⠄⠿⣋⣉⣉⡁⠄⢾⣦⠄⠄⠄⠄⠄⠄⠄⠄
*/ 