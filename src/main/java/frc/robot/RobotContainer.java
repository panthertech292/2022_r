// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
//Constants
import frc.robot.Constants.BeltConstants;
import frc.robot.Constants.PickupConstants;
import frc.robot.Constants.ShooterConstants;
//Commands
import frc.robot.commands.*;
import frc.robot.commands.Auto.AutoTurn;
import frc.robot.commands.Auto.AutoTurn2;
import frc.robot.commands.Auto.Modes.*;

//Subsystems
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.PickupSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.BeltSubsystem;



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
  private final PickupSubsystem s_PickupSubsystem = new PickupSubsystem();
  private final ShooterSubsystem s_ShooterSubsystem = new ShooterSubsystem();
  private final LiftSubsystem s_LiftSubsystem = new LiftSubsystem();
  private final BeltSubsystem s_BeltSubsystem = new BeltSubsystem();

  // Drive Commands
  private final Command z_DriveTeleop = new DriveTeleop(s_DriveSubsystem);

  // Pickup Commands
  private final Command z_PickupArmUp = new PickupArmUp(s_PickupSubsystem, PickupConstants.kPickupArmSpeedUp);
  //private final Command z_PickupArmDown = new PickupArmDown(s_PickupSubsystem, PickupConstants.kPickupArmSpeedDown);
  private final Command z_PickUpArmDownBelts = new PickupArmDownBelts(s_PickupSubsystem, s_BeltSubsystem, PickupConstants.kPickupArmSpeedDown, BeltConstants.kFrontBeltSpeed, BeltConstants.kBackBeltSpeed);

  // Shooter Commands
  private final Command z_RunShooter = new RunShooter(s_ShooterSubsystem, ShooterConstants.kShooterLowSpeed, ShooterConstants.kShooterHighSpeed);
  //private final Command z_RunShooterBelt = new RunShooterBelt(s_ShooterSubsystem, s_BeltSubsystem, ShooterConstants.kShooterLowSpeed, ShooterConstants.kShooterHighSpeed, 0, BeltConstants.kFrontBeltSpeed, BeltConstants.kBackBeltSpeed);
  private final Command z_RunShooterBeltFenderLow = new RunShooterBelt(s_ShooterSubsystem, s_BeltSubsystem, .15, .15, 800, BeltConstants.kFrontBeltSpeed+0.05, BeltConstants.kBackBeltSpeed+0.05);
  private final Command z_RunShooterBeltTarmacHigh = new RunShooterBelt(s_ShooterSubsystem, s_BeltSubsystem, .30, .30, 1645, BeltConstants.kFrontBeltSpeed, BeltConstants.kBackBeltSpeed);

  //Lift Commands
  private final Command z_LiftExtend = new LiftExtend(s_LiftSubsystem);
  private final Command z_LiftRetract = new LiftRetract(s_LiftSubsystem);
  private final Command z_LiftRotate = new LiftRotate(s_LiftSubsystem);

  //Belt Commands
  private final Command z_ReloadBelts = new ReloadBelts(s_BeltSubsystem, s_ShooterSubsystem, BeltConstants.kFrontBeltSpeed, BeltConstants.kBackBeltSpeed);
  private final Command z_RunBelt = new RunBelt(s_BeltSubsystem);
  private final Command z_RunBeltBackwards = new RunBeltBackwards(s_BeltSubsystem);
  
  //Auto Commands
  private final Command z_AutoMain = new AutoMain(s_DriveSubsystem, s_PickupSubsystem, s_ShooterSubsystem, s_BeltSubsystem);
  private final Command z_AutoMainDualShot = new AutoMainDualShot(s_DriveSubsystem, s_PickupSubsystem, s_ShooterSubsystem, s_BeltSubsystem);
  private final Command z_Auto3Ball = new Auto3Ball(s_DriveSubsystem, s_PickupSubsystem, s_ShooterSubsystem, s_BeltSubsystem);
  private final Command z_AutoOffLine = new AutoOffLine(s_DriveSubsystem);
  private final Command z_AutoDead = new AutoDead();
  private final Command z_AutoTurn = new AutoTurn(s_DriveSubsystem, 0.9, 360);
  private final Command z_AutoTurn2 = new AutoTurn2(s_DriveSubsystem, 0.9, 360);

  SendableChooser<Command> o_AutoChooser = new SendableChooser<>();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    CameraServer.startAutomaticCapture();
    CameraServer.startAutomaticCapture();

    s_DriveSubsystem.setDefaultCommand(z_DriveTeleop);
    s_PickupSubsystem.setDefaultCommand(z_PickupArmUp);
    s_LiftSubsystem.setDefaultCommand(z_LiftRotate);
    s_BeltSubsystem.setDefaultCommand(z_ReloadBelts);
    
    //Auto Command Selector
    o_AutoChooser.setDefaultOption("Main Auto", z_AutoMain);
    o_AutoChooser.setDefaultOption("Main Auto Dual Shot", z_AutoMainDualShot);
    o_AutoChooser.addOption("3 Ball Auto", z_Auto3Ball);
    o_AutoChooser.addOption("Drive off line Auto", z_AutoOffLine);
    o_AutoChooser.addOption("Auto Dead", z_AutoDead);
    SmartDashboard.putData(o_AutoChooser);
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
    //final JoystickButton d_xButton = new JoystickButton(io_drivercontroller, Button.kX.value);
    final JoystickButton d_startButton = new JoystickButton(io_drivercontroller, Button.kStart.value);
    //final JoystickButton d_backButton = new JoystickButton(io_drivercontroller, Button.kBack.value);
    //final JoystickButton d_yButton = new JoystickButton(io_drivercontroller, Button.kY.value);
    final JoystickButton d_rightBumper = new JoystickButton(io_drivercontroller, Button.kRightBumper.value);
    final JoystickButton d_leftBumper = new JoystickButton(io_drivercontroller, Button.kLeftBumper.value);

    //Operator Controller
    final JoystickButton o_aButton = new JoystickButton(io_opercontroller, Button.kA.value);
    final JoystickButton o_bButton = new JoystickButton(io_opercontroller, Button.kB.value);
    final JoystickButton o_xButton = new JoystickButton(io_opercontroller, Button.kX.value);
    final JoystickButton o_yButton = new JoystickButton(io_opercontroller, Button.kY.value);
    final JoystickButton o_startButton = new JoystickButton(io_opercontroller, Button.kStart.value);
    final JoystickButton o_backButton = new JoystickButton(io_opercontroller, Button.kBack.value);
    final JoystickButton o_leftBumper = new JoystickButton(io_opercontroller, Button.kLeftBumper.value);
    final JoystickButton o_rightBumper = new JoystickButton(io_opercontroller, Button.kRightBumper.value);
    final JoystickButton o_leftStickClick = new JoystickButton(io_opercontroller, Button.kLeftStick.value);

    //Driver Controller Binds
    d_rightBumper.toggleWhenPressed(z_PickUpArmDownBelts);
    d_leftBumper.whileHeld(z_RunBeltBackwards);
    d_startButton.whileHeld(z_RunShooter);
    //Operator Controller Binds
    o_leftBumper.whileHeld(z_LiftRetract);
    o_rightBumper.whileHeld(z_LiftExtend);
    o_startButton.whileHeld(z_RunBelt);
    o_backButton.whileHeld(z_RunBeltBackwards);
    o_aButton.whileHeld(z_RunShooterBeltFenderLow);
    o_bButton.whileHeld(z_RunShooterBeltTarmacHigh);
    o_leftStickClick.whileHeld(z_RunShooter);
  }

  public static double deadZoneCheck(double rawControllerInput){
    if (rawControllerInput > Constants.kControllerDeadZone || rawControllerInput < -Constants.kControllerDeadZone){
      return rawControllerInput;
    }
    else{
      return 0;
    }
  }
  public static double getDriverLeftSpeed(){
    return deadZoneCheck(io_drivercontroller.getLeftY());
  }
  public static double getDriverRightSpeed() {
    return deadZoneCheck(io_drivercontroller.getRightY());
  }
  public static double getDriverLeftSpeedX(){
    return deadZoneCheck(io_drivercontroller.getLeftX());
  }
  public static double getDriverRightSpeedX(){
    return deadZoneCheck(io_drivercontroller.getRightX());
  }
  public static double getOperRightSpeedY(){
    return deadZoneCheck(io_opercontroller.getRightY());
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    if (o_AutoChooser != null){
      return o_AutoChooser.getSelected();
    }
    else{
      return z_AutoMain;
    }
  }
}
