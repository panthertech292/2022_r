// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;

//Subsystems
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final static XboxController io_drivercontroller = new XboxController(Constants.kDriverController);
  private final static XboxController io_opercontroller = new XboxController(Constants.kOperController);

  // Subsystems
  private final DriveSubsystem s_DriveSubsystem = new DriveSubsystem();

  // Drive Commands
  private final Command z_DriveTeleop = new DriveTeleop(s_DriveSubsystem);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    s_DriveSubsystem.setDefaultCommand(z_DriveTeleop);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //Driver Controller
    final JoystickButton d_aButton = new JoystickButton(io_drivercontroller, Button.kA.value);
    final JoystickButton d_bButton = new JoystickButton(io_drivercontroller, Button.kB.value);
    final JoystickButton d_xButton = new JoystickButton(io_drivercontroller, Button.kX.value);
    final JoystickButton d_startButton = new JoystickButton(io_drivercontroller, Button.kStart.value);
    final JoystickButton d_backButton = new JoystickButton(io_drivercontroller, Button.kBack.value);
    final JoystickButton d_yButton = new JoystickButton(io_drivercontroller, Button.kY.value);

    //Operator Controller
    final JoystickButton o_aButton = new JoystickButton(io_opercontroller, Button.kA.value);
    final JoystickButton o_bButton = new JoystickButton(io_opercontroller, Button.kB.value);
    final JoystickButton o_xButton = new JoystickButton(io_opercontroller, Button.kX.value);
    final JoystickButton o_yButton = new JoystickButton(io_opercontroller, Button.kY.value);
    final JoystickButton o_startButton = new JoystickButton(io_opercontroller, Button.kStart.value);
    final JoystickButton o_backButton = new JoystickButton(io_opercontroller, Button.kBack.value);
  }
  public static double getLeftSpeed() {
    //return io_drivercontroller.getY(GenericHID.Hand.kLeft);
    return io_drivercontroller.getLeftY();
  }

  public static double getRightSpeed() {
    return io_drivercontroller.getRightY();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null; //m_autoCommand; Returning null for now by the virture of their being no auto commands.
  }
}
