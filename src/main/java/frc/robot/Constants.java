// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //Controller Mapping
    static final int kDriverController = 0; // USB
    static final int kOperController = 1; // USB
    static final double kControllerDeadZone = 0.19;


    public static final class DriveConstants {
        //Motor Mapping
        public static final int kFrontLeftMotor = 1;
        public static final int kFrontRightMotor = 2;
        public static final int kBackLeftMotor = 3;
        public static final int kBackRightMotor = 4;
        public static final int kDriveCurrentLimit = 60;
    }
    public static final class PickupConstants{
        //Motors
        public static final int kPickupMotor = 12;
        public static final int kPickupMotorArm = 10; 
        public static final int kPickupCurrentLimit = 60;
        public static final int kPickup550CurrentLimit = 30;
        //Encoders & Switches
        public static final int kPickupArmUpSwitch = 0; //DIO
        public static final int kPickupArmDownSwitch = 1; //DIO
        //Speeds
        public static final double kPickupArmSpeedUp = -0.65;
        public static final double kPickupArmSpeedDown = 0.65; 
        public static final double kPickupMotorSpeed = 0.72;
    }
    public static final class ShooterConstants{
        //Motors
        public static final int kShooterMotorLow = 40; 
        public static final int kShooterMotorHigh = 41; 
        public static final int kShooterCurrentLimit = 60;
        //Speeds
        public static final double kShooterLowSpeed = 0.15; // place holder value
        public static final double kShooterHighSpeed = 0.15; // place holder value
    }
    public static final class LiftConstants{
        //Motors
        public static final int kRotationArmMotor = 31; 
        public static final int kLeftArmMotor = 32; 
        public static final int kRightArmMotor = 30; 
        public static final int kLiftCurrentLimit = 60;
        
        //Speeds
        public static final double kArmExtendSpeed = 1;
        public static final double kArmRetractSpeed = -1;
    }
    public static final class BeltConstants{
        //Motors
        public static final int kFrontBeltMotor = 21; //CAN
        public static final int kBackBeltMotor = 20; //CAN
        public static final int kBeltCurrentLimit = 60;
        //Sensors
        public static final int kFrontBeltSensor = 3; //DIO
        public static final int kBackBeltSensor = 2; //DIO
        //Speeds
        public static final double kFrontBeltSpeed = 0.35;
        public static final double kBackBeltSpeed = 0.35;
    }
    public static final class AutoConstants{
        public static final double kRobotRadius = 11.5; //place holder
    }
}
