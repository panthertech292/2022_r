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
    static final double kControllerDeadZone = 0.18;

    public static final class DriveConstants {
        //Motor Mapping
        public static final int kFrontLeftMotor = 1;
        public static final int kFrontRightMotor = 2;
        public static final int kBackLeftMotor = 3;
        public static final int kBackRightMotor = 4;
    }
    public static final class PickupConstants{
        //Motors
        public static final int kPickupMotorLow = 9; // place holder value
        public static final int kPickupMotorHigh = 99; //place holder value
        public static final int kPickupMotorArm = 43; // place holder value //3 for test bot
        
        //Encoders & Switches
        public static final int kPickupArmUpSwitch = 0; //DIO - place holder value
        //Speeds
        public static final double kPickupMotorSpeed = 0.52;
    }
    public static final class ShooterConstants{
        //Motors
        public static final int kShooterMotorLow = 11; // place holder value
        public static final int kShooterMotorHigh = 12; // place holder value

        //Speeds
        public static final double kShooterLowSpeed = 0.5; // place holder value
        public static final double kShooterHighSpeed = 0.5; // place holder value
    }
    public static final class LiftConstants{
        //Motors
        public static final int kRotationArmMotor = 21; // place holder value
        public static final int kLeftArmMotor = 20; //placeholder
        public static final int kRightArmMotor = 22; //placeholder
        
        //Speeds
        public static final double kArmExtendSpeed = 0.4;
        public static final double kArmRetractSpeed = -0.4;
    }
    public static final class BeltConstants{
        //Motors
        public static final int kFrontBeltMotor = 50; //placeholder
        public static final int kBackBeltMotor = 51;
    }
}
