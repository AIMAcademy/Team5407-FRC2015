// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc.team5407.robot.subsystems;

import org.usfirst.frc.team5407.robot.OI;
import org.usfirst.frc.team5407.robot.Robot;
import org.usfirst.frc.team5407.robot.RobotMap;
import org.usfirst.frc.team5407.robot.commands.WinchDrive;
import org.usfirst.frc.team5407.robot.subsystems.Winch;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Winch extends PIDSubsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private AnalogPotentiometer potentiometer = RobotMap.winchPotentiometer;
    private SpeedController victor = RobotMap.winchVictor;
    DigitalInput winchLimitTop = RobotMap.winchLimitTop;
    DigitalInput winchLimitBottom = RobotMap.winchLimitBottom;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
        
    // Initialize your subsystem here
    public Winch() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID
        super("Winch", 3.0, 0.0, 0.01);
        setAbsoluteTolerance(0.05);
        getPIDController().setContinuous(false);
        LiveWindow.addActuator("Winch", "PIDSubsystem Controller", getPIDController());
        LiveWindow.addSensor("Winch", "Pot", (AnalogPotentiometer) potentiometer);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID

        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		// setDefaultCommand(new WinchDoNothing());
    	setDefaultCommand(new WinchDrive());
    }
    
	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
//    public void log() {
//        SmartDashboard.putData("Winch Pot", (AnalogPotentiometer) victor);
//    }
    
    public double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SOURCE
        // return potentiometer.get();
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SOURCE
        return potentiometer.get();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=OUTPUT
        // victor.pidWrite(output);
        victor.set(output);
	    SmartDashboard.putNumber("Potentiometer Value Button", potentiometer.get());
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=OUTPUT
    }
    
    public double getPotPoint() {
    	return potentiometer.get();
    }
    
	public void driveWinch(double speed) {
		    SmartDashboard.putNumber("Potentiometer Value Drive", potentiometer.get());
			if(OI.getStickY() < -0.1){
				if(RobotMap.winchLimitBottom.get() == false) {
					victor.set(0);
				}
				else {
					victor.set(speed/2);
				}
			}
			else if(OI.getStickY() > 0.1){
				if(RobotMap.winchLimitTop.get() == false) {
					victor.set(0);
				}
				else {
					victor.set(speed/2);
				}
			}
			else {
				victor.set(speed/2);
			}
	}
	
	public void doNothing() {
		// victor.set(0);
        // Robot.winch.enable();
        new WinchDrive();
	}
	
}
