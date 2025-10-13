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
// PhotonVision imports: if you add the PhotonVision library to your Gradle dependencies,
// restore these imports:
// import org.photonvision.PhotonCamera;
// import org.photonvision.PhotonPipelineResult;

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
  //private final DifferentialDrive drivetrain =new DifferentialDrive(leftMotor1, rightMotor1);
  private final Joystick m_driverController = new Joystick(0);
  private final PhotonCamera camera = new PhotonCamera("photonvision"); // Initialize PhotonCamera

  /** Called once at the beginning of the robot program. */
  public Robot() {
    // SendableRegistry.addChild(drivetrain, leftMotor1);
    // SendableRegistry.addChild(drivetrain, leftMotor2);
    // SendableRegistry.addChild(drivetrain, rightMotor1);
    // SendableRegistry.addChild(drivetrain, rightMotor2);

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    // rightMotor1.setInverted(true);
  }

  @Override
  public void teleopPeriodic() {
    System.out.println("Speed: ");
    System.out.println("axis 0: " + m_driverController.getRawAxis(0));
    System.out.println("axis 1: " + m_driverController.getRawAxis(1));
    System.out.println("axis 2: " + m_driverController.getRawAxis(2));
    System.out.println("axis 3: " + m_driverController.getRawAxis(3));
    System.out.println("axis 4: " + m_driverController.getRawAxis(4));
    System.out.println("axis 5: " + m_driverController.getRawAxis(5));
    System.out.println("axis 6: " + m_driverController.getRawAxis(6));
    System.out.println("left1: " + leftMotor1.get());
    System.out.println("left2: " + leftMotor2.get());
    System.out.println("right1: " + rightMotor1.get());
    System.out.println("right2: " + rightMotor2.get());

    double speed = 0.5;
    // Estes Drive =========================================================================================
    double speedL = m_driverController.getRawAxis(1); 
    speedL *= speed;
    if (speedL > 1.0) {
      speedL = 1.0;
    } else if (speedL < -1.0) {
      speedL = -1.0;
    }

    double speedR = m_driverController.getRawAxis(5); 
    speedR *= speed; 
    if (speedR > 1.0) {
      speedR = 1.0;
    } else if (speedR < -1.0) {
      speedR = -1.0;
    }

    double thresh = 0.1; 

    if (Math.abs(m_driverController.getRawAxis(1)) > thresh 
        || Math.abs(m_driverController.getRawAxis(5)) > thresh) {

      if (Math.abs(m_driverController.getRawAxis(1)) > thresh) {
        leftMotor1.set(speedL);
        leftMotor2.set(speedL);
      }

      if (Math.abs(m_driverController.getRawAxis(5)) > thresh) {
        rightMotor1.set(-speedR);
        rightMotor2.set(-speedR);
      }
    } else {
      leftMotor1.set(0);
      leftMotor2.set(0);
      rightMotor1.set(0);
      rightMotor2.set(0);
    }

    // PhotonVision target processing
    PhotonPipelineResult result = camera.getLatestResult();
    PhotonPipelineResult result = camera.getLatestResult();
    if (result.hasTargets()) {
      var target = result.getBestTarget();
      double yaw = target.getYaw();
      double pitch = target.getPitch();
      double area = target.getArea();
      double skew = target.getSkew();

      System.out.println("Target Info:");
      System.out.println("Yaw: " + yaw);
      System.out.println("Pitch: " + pitch);
    }
}

// Minimal PhotonVision stubs to allow compiling without the PhotonVision dependency.
// Replace these with the real PhotonVision library (and restore the imports above)
// by adding the PhotonVision dependency to your build.gradle when ready.

class PhotonCamera {
  private final String name;
  public PhotonCamera(String name) {
    this.name = name;
  }
  public PhotonPipelineResult getLatestResult() {
    return new PhotonPipelineResult();
  }
}

class PhotonPipelineResult {
  public boolean hasTargets() {
    return false;
  }
  public PhotonTrackedTarget getBestTarget() {
    return new PhotonTrackedTarget();
  }
}

class PhotonTrackedTarget {
  public double getYaw() { return 0.0; }
  public double getPitch() { return 0.0; }
  public double getArea() { return 0.0; }
  public double getSkew() { return 0.0; }
}
    }
}

