// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with tank
 * steering and an Xbox controller.
 */
public class Robot extends TimedRobot {
  private final SparkMax leftMotor1 = new SparkMax(2, MotorType.kBrushed);
  private final SparkMax leftMotor2 = new SparkMax(3, MotorType.kBrushed);
  private final SparkMax rightMotor1 = new SparkMax(4, MotorType.kBrushed);
  private final SparkMax rightMotor2 = new SparkMax(5, MotorType.kBrushed);
  //private final motorgro leftGroup= leftMotor2.follow(leftMotor1);
  private final DifferentialDrive drivetrain = new DifferentialDrive(leftMotor1, rightMotor1);
  private final Joystick m_driverController = new Joystick(0);

  /** Called once at the beginning of the robot program. */
  public Robot() {
    SendableRegistry.addChild(drivetrain, leftMotor1);
    SendableRegistry.addChild(drivetrain, rightMotor1);

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.fur
    // rightMotor1.setInverted(true);
  }

  @Override
  public void teleopPeriodic() {
    // Define deadband to prevent joystick drift
    double deadband = 0.1;
    
    // Get joystick values with deadband
    double leftStick = applyDeadband(m_driverController.getRawAxis(1), deadband);
    double rightStick = applyDeadband(m_driverController.getRawAxis(5), deadband); // Usually axis 5 for right stick
    
    // Set motor speeds
    leftMotor1.set(leftStick);  // Negative because forward stick is negative
    leftMotor2.set(leftStick);
    rightMotor1.set(-rightStick);
    rightMotor2.set(-rightStick);
  }

  // Helper method for deadband
  private double applyDeadband(double value, double deadband) {
    if (Math.abs(value) < deadband) {
      return 0.0;
    }
    return value;
  }
  double Speed = 0.5;
}
// ...existing code...