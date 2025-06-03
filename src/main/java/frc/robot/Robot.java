// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with tank
 * steering and an Xbox controller.
 */
public class Robot extends TimedRobot {
  private final PWMSparkMax leftMotor1 = new PWMSparkMax(0);
  private final PWMSparkMax leftMotor2 = new PWMSparkMax(1);
  private final PWMSparkMax rightMotor1 = new PWMSparkMax(2);
  private final PWMSparkMax rightMotor2 = new PWMSparkMax(3);
  private final DifferentialDrive drivetrain =
      new DifferentialDrive(leftMotor1, rightMotor1);
  private final XboxController m_driverController = new XboxController(0);

  /** Called once at the beginning of the robot program. */
  public Robot() {
    SendableRegistry.addChild(drivetrain, leftMotor1);
    SendableRegistry.addChild(drivetrain, rightMotor1);

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.fur
    rightMotor1.setInverted(true);
    rightMotor2.setInverted(true);
  }

  @Override
  public void teleopPeriodic() {
    double speed=1.0;
    leftMotor1.set(speed);
    leftMotor2.set(speed);
    rightMotor1.set(speed);
    rightMotor2.set(speed);

    drivetrain.arcadeDrive(m_driverController.getLeftY(), m_driverController.getRightX());
  }
}