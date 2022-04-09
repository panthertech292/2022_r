// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto.Modes;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

//Subsystems
import frc.robot.subsystems.BeltSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.PickupSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.Constants.BeltConstants;
//Commands
import frc.robot.commands.*;
import frc.robot.commands.Auto.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Auto4Ball extends SequentialCommandGroup {
  private final DriveSubsystem DriveSubsystem;
  private final PickupSubsystem PickupSubsystem;
  private final ShooterSubsystem ShooterSubsystem;
  private final BeltSubsystem BeltSubsystem;
  /** Creates a new Auto4Ball. */
  public Auto4Ball(DriveSubsystem s_DriveSubsystem, PickupSubsystem s_PickupSubsystem, ShooterSubsystem s_ShooterSubsystem, BeltSubsystem s_BeltSubsystem) {
    DriveSubsystem = s_DriveSubsystem;
    PickupSubsystem = s_PickupSubsystem;
    ShooterSubsystem = s_ShooterSubsystem;
    BeltSubsystem = s_BeltSubsystem;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new DrivePickup(s_DriveSubsystem, s_PickupSubsystem, s_BeltSubsystem, 50, 0.4),
      new PickupArmDownBelts(s_PickupSubsystem, s_BeltSubsystem, BeltConstants.kFrontBeltSpeed, BeltConstants.kBackBeltSpeed).withTimeout(1),
      new IMUTurn(s_DriveSubsystem, (-15), .004, 0.15),
      new RunShooterBelt(s_ShooterSubsystem, s_BeltSubsystem, 0.28, 0.28, 1580, BeltConstants.kFrontBeltSpeed, BeltConstants.kBackBeltSpeed).withTimeout(3),
      new IMUTurn(s_DriveSubsystem, 19, .004, 0.15),
      new DrivePickupStraight(s_DriveSubsystem, s_PickupSubsystem, s_BeltSubsystem, 130, 0.45, 0.065),
      new DrivePickupStraight(s_DriveSubsystem, s_PickupSubsystem, s_BeltSubsystem, 15, 0.20, 0.035),
      new PickupArmDownBelts(s_PickupSubsystem, s_BeltSubsystem, BeltConstants.kFrontBeltSpeed, BeltConstants.kBackBeltSpeed).withTimeout(1)
      //drive 
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