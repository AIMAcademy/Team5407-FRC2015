/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team5407.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

import org.usfirst.frc.team5407.robot.OI;
import org.usfirst.frc.team5407.robot.Robot;
import org.usfirst.frc.team5407.robot.RobotMap;
import org.usfirst.frc.team5407.robot.commands.DriveWithJoystick;
import org.usfirst.frc.team5407.robot.commands.UltraSonic;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author robotics
 */
public class Chassis extends Subsystem {
    private AnalogInput ultraSonic = RobotMap.ultraSonic;
	RobotDrive drive;
	Talon talonLeft, talonRight;
	Solenoid solenoid_gear_shift = new Solenoid(0);
	boolean reverseState = false;
	double speed = 0;
	double turn = 0;
	public boolean isFinished;
	

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveWithJoystick());
		solenoid_gear_shift.set(false);
	}

	public Chassis() {
		talonLeft = new Talon(0);
		talonRight = new Talon(1);
		drive = new RobotDrive(talonLeft, talonRight);// defines rearLeft,rearRight Motors
		drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
		drive.setSafetyEnabled(false);
	}
	
	public void driveRobot(double speed, double turn) {
		drive.arcadeDrive(speed, turn);
	}

	public void straightFull() {
		drive.arcadeDrive(-1, 0.0);
	}
	
	public void straight75() {
		drive.arcadeDrive(-0.75, 0.0);
	}
	
	public void pivotRight() {
		drive.tankDrive(-.25, -.5);
	}

	public void turnLeft() {
		drive.arcadeDrive(0.0, -0.5);
	}

	public void turnRight() {
		drive.arcadeDrive(0.0, 0.5);
	}

	public void driveBackwards75() {
		drive.arcadeDrive(0.75, 0.0);
	}
	
	public void driveTurn90() {
		drive.arcadeDrive(0, -1);
	}
	
	public void doNothing() {
		drive.arcadeDrive(0, 0.0);
	}
	
	public void reverseDrive(boolean state) {
		if(state == false){ // can also be (!state)
			reverseState = false;
			drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
			drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
		}
		else if(state == true){  // can also be (state)
			reverseState = true;
			drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);
			drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);
		}
		else {
			reverseState = false;
			drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
			drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
		}
	}

	public void driveWithJoystick(Joystick stick0) {
		// Initialize UltraSonic Sensor
		int ultraSonicValue = ultraSonic.getAverageValue();
	    SmartDashboard.putNumber("ultraSonic Value", ultraSonicValue);
	    
		if(reverseState==true) {
			drive.arcadeDrive(stick0.getY(), -stick0.getX());
		}
		else {
			drive.arcadeDrive(stick0);
		}
	}

	boolean lockLowGear = false; // Allows other systems to override gear selection and force to use low gear.

	public void setGear(boolean state) {
		solenoid_gear_shift.set(lockLowGear ? true : state);
	}
	
//	public int ultraSonic() {
//		int ultraSonicValue = ultraSonic.getAverageValue();
//	    SmartDashboard.putNumber("ultraSonic Value", ultraSonicValue);
//		return ultraSonicValue;
//	}
	
	public void ultraSonicFindCan() {
		int ultraSonicValue = ultraSonic.getAverageValue();
	    SmartDashboard.putNumber("ultraSonic Value", ultraSonicValue);
		if(ultraSonicValue>500 && ultraSonicValue<700) {
			drive.arcadeDrive(0.0, 0);
			isFinished = true;
		}
		else {
			drive.arcadeDrive(0.0, -0.5);
		}
	}

}
