
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
  private final DifferentialDrive drivetrain =new DifferentialDrive(leftMotor1, rightMotor1);
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
   
    double speed=m_driverController.getRawAxis(1);
    System.out.println("Speed: " + speed);
    System.out.println("axis 0: " + m_driverController.getRawAxis(0));
    System.out.println("axis 1: " + m_driverController.getRawAxis(1));
    System.out.println("axis 2: " + m_driverController.getRawAxis(2));
    System.out.println("axis 3: " + m_driverController.getRawAxis(3));
    System.out.println("axis 4: " + m_driverController.getRawAxis(4));
    System.out.println("axis 5: " + m_driverController.getRawAxis(5));
    System.out.println("axis 6: " + m_driverController.getRawAxis(6));
    // leftMotor1.set(speed);
    // rightMotor1.set(speed);
    // leftMotor2.set(speed);
    // rightMotor2.set(speed);


    drivetrain.arcadeDrive(-speed, m_driverController.getRawAxis(4));
    rightMotor1.set(0);
    leftMotor2.set(leftMotor1.get());
    //rightMotor2.set(rightMotor1.get());
  }
}


