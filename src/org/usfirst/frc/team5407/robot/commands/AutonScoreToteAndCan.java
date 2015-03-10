/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team5407.robot.commands;


import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author robotics
 */
public class AutonScoreToteAndCan extends CommandGroup {

	public AutonScoreToteAndCan() {
		// Add Commands here:
		addSequential(new WinchAuton(2));
		addSequential(new DriveStraightFull(1));
		addSequential(new DriveDoNothing(1));
		addSequential(new DrivePivotRight(1));
		addSequential(new ExtendClawBack(.5));
		addSequential(new ClawBackGrab(1));
		addSequential(new ClawBackRetract(.5));
		addSequential(new DriveStraightFull(3));
		addSequential(new DriveTurn90Degrees(.5));
		//addSequential(new LeftTurn(2));

		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.
		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
	}
}
