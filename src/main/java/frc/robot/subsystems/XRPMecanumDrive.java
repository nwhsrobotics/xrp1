// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.xrp.XRPMotor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class XRPMecanumDrive extends SubsystemBase {
  private static final double kGearRatio =
      (30.0 / 14.0) * (28.0 / 16.0) * (36.0 / 9.0) * (26.0 / 8.0); // 48.75:1
  private static final double kCountsPerMotorShaftRev = 12.0;
  private static final double kCountsPerRevolution = kCountsPerMotorShaftRev * kGearRatio; // 585.0
  private static final double kWheelDiameterInch = 2.3622; // 60 mm

  // The XRP has the left and right motors set to
  // channels 0 and 1 respectively
  private final XRPMotor m_leftFrontMotor = new XRPMotor(0);
  private final XRPMotor m_rightFrontMotor = new XRPMotor(1);
  private final XRPMotor m_leftBackMotor = new XRPMotor(2);
  private final XRPMotor m_rightBackMotor = new XRPMotor(3);

  // The XRP has onboard encoders that are hardcoded
  // to use DIO pins 4/5 and 6/7 for the left and right
  private final Encoder m_leftFrontEncoder = new Encoder(4, 5);
  private final Encoder m_rightFrontEncoder = new Encoder(6, 7);
  private final Encoder m_leftBackEncoder = new Encoder(8, 9);
  private final Encoder m_rightBackEncoder = new Encoder(10, 11);


  // Set up the differential drive controller
  private final MecanumDrive m_mecanumDrive =
      new MecanumDrive(m_leftFrontMotor, m_leftBackMotor, m_rightFrontMotor, m_rightBackMotor);

  /** Creates a new XRPDrivetrain. */
  public XRPMecanumDrive() {
    // Use inches as unit for encoder distances
    m_leftFrontEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
    m_rightFrontEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
    m_leftBackEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
    m_rightBackEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
    resetEncoders();

    // Invert right side since motor is flipped
    m_rightFrontMotor.setInverted(true);
    m_rightBackMotor.setInverted(true);
  }

  public void arcadeDrive(double xaxisSpeed, double yaxisSpeed, double zaxisRotate) {
    m_mecanumDrive.driveCartesian(xaxisSpeed, yaxisSpeed, zaxisRotate);
  }  

  public void resetEncoders() {
    m_leftFrontEncoder.reset();
    m_rightFrontEncoder.reset();
    m_leftBackEncoder.reset();
    m_rightBackEncoder.reset();
  }

  // TODO-DW : Figure out what these are used for.  Do we need back encoder get methods?
  public double getLeftFrontDistanceInch() {
    return m_leftFrontEncoder.getDistance();
  }

  public double getRightFrontDistanceInch() {
    return m_rightFrontEncoder.getDistance();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
