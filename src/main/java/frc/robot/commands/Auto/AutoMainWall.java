// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.BeltConstants;
import frc.robot.Constants.PickupConstants;
import frc.robot.commands.PickupArmDownBelts;
import frc.robot.commands.PickupArmUp;
import frc.robot.commands.RunBelt;
import frc.robot.commands.RunShooterBelt;
import frc.robot.subsystems.BeltSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.PickupSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoMainWall extends SequentialCommandGroup {
  private final DriveSubsystem DriveSubsystem;
  private final PickupSubsystem PickupSubsystem;
  private final ShooterSubsystem ShooterSubsystem;
  private final BeltSubsystem BeltSubsystem;
  /** Creates a new AutoMain. */
  public AutoMainWall(DriveSubsystem s_DriveSubsystem, PickupSubsystem s_PickupSubsystem, ShooterSubsystem s_ShooterSubsystem, BeltSubsystem s_BeltSubsystem) {
    DriveSubsystem = s_DriveSubsystem;
    PickupSubsystem = s_PickupSubsystem;
    ShooterSubsystem = s_ShooterSubsystem;
    BeltSubsystem = s_BeltSubsystem;

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
    new AutoEncoderDriveForBack(s_DriveSubsystem, 20, .4), //Drive to shooting position
    new RunShooterBelt(s_ShooterSubsystem, s_BeltSubsystem, 0.29, 0.29, 1660, BeltConstants.kFrontBeltSpeed, -BeltConstants.kBackBeltSpeed).withTimeout(3), //Run the shooter
    new PickupArmDownBelts(s_PickupSubsystem, s_BeltSubsystem, PickupConstants.kPickupArmSpeedDown, BeltConstants.kFrontBeltSpeed, BeltConstants.kBackBeltSpeed).withTimeout(0.5), //Put the arm down
    new DrivePickup(s_DriveSubsystem, s_PickupSubsystem, s_BeltSubsystem), //Drive with pickup down to get ball
    new RunShooterBelt(s_ShooterSubsystem, s_BeltSubsystem, 0.30, 0.30, 1700, BeltConstants.kFrontBeltSpeed, -BeltConstants.kBackBeltSpeed).withTimeout(3), //Run the shooter
    new PickupArmUp(s_PickupSubsystem, PickupConstants.kPickupArmSpeedUp).withTimeout(2) //Raise arm
    //new AutoEncoderDriveForBack(s_DriveSubsystem, 25, .4) //Drive back to get off line
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









