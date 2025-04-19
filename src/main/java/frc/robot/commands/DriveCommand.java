// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.XRPMecanumDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class DriveCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final XRPMecanumDrive m_driveSubsystem;
  private final Joystick m_joystick;

  /**
   * Creates a new DriveCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveCommand(XRPMecanumDrive subsystem, Joystick js) {
    m_driveSubsystem = subsystem;
    m_joystick = js;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_joystick.setXChannel(0); // Joystick's X is left stick's left/right
    m_joystick.setYChannel(1); // Joystick's Y is left stick's up/down (which is inverted btw)
    m_joystick.setZChannel(4); // Joystick's Z is right stick's left/right.
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // read joysticks
    double xaxisSpeed = -m_joystick.getY();  // robot X, forward, is left js Y, which is negative for forward
    double yaxisSpeed = m_joystick.getX();   // robot Y, left, is left js X
    double zaxisRotate = m_joystick.getZ();  // robot Z, cw about up, is right stick X

    System.out.printf("Driving x:%f, y:%f, z:%f\n", xaxisSpeed, yaxisSpeed, zaxisRotate);

    // tell drive subsystem what to do
    m_driveSubsystem.arcadeDrive(xaxisSpeed, yaxisSpeed, zaxisRotate);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
