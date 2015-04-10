package org.usfirst.frc.team5407.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonGrabTwoCans extends CommandGroup {
    
    public  AutonGrabTwoCans() {
    	
    	// XXXXXXXXXXXXXXXX
    	
    	
    	// Grab first can
    	addSequential(new ClawBackOpenClose(true));		// open claw
    	addSequential(new DriveRobot(.2, 0, 0));		// pause for .2 seconds
//    	addSequential(new DriveRobot(0.1, 1, 0));		// drive backwards to totes
    	addSequential(new ClawBackAuton(1.8, -1));		// extend claw back
    	addSequential(new ClawBackOpenClose(false));	// close claw to grab can
    	addSequential(new DriveRobot(.2, 0, 0));		// pause for .2 seconds
    	addSequential(new ClawBackAuton(1.7, 1));		// retract claw back
    	addSequential(new ClawBackOpenClose(true));		// open claw to drop can
    	addSequential(new DriveRobot(0.25, -1, 0));		// drive forwards away from totes

    	// Turn, drive, and grab second can
    	addSequential(new DriveRobot(1.5, 0, -0.57));	// 90 degree turn left
    	addSequential(new DriveRobot(1.50, 1, 0));		// drive backward to other can (approximate timing)
    	addSequential(new DriveRobot(1.5, 0, 0.57));	// 90 degree turn right
//    	addSequential(new UltraSonic());				// turn left until find can
    	addSequential(new DriveRobot(0.1, 1, 0));		// drive backwards to totes
    	addSequential(new DriveRobot(.2, 0, 0));		// pause for .2 seconds
    	addSequential(new ClawBackAuton(1.75, -1));		// extend claw back
    	addSequential(new ClawBackOpenClose(false));	// close claw to grab can
    	addSequential(new ClawBackAuton(1.75, 1));		// retract claw back
    	
    	// Move into scoring position
    	addSequential(new DriveRobot(1.5, -.75, 0));		// drive forwards to scoring position
		addSequential(new DriveRobot(1.5, 0, 0.55));	// 90 degree turn right

        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
