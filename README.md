# 2021-2022 Robotic Rams Team 10243 Freight Frenzy Code

## Setup:

- Navigate to teamcode folder to find modified code (TeamCode/src/main/java/org/teamcode)

  - `Auto_PX.java` - Autonomous program for positions as defined in the graphic below
  - `MecanumTeleop.java` - Teleop program
  - `HardwareMecanum.java` - Definitions of motor names to be set in configuration of app

- Ensure the configuration of the app is set as defined in `HardwareMecanum.java` and as shown below.

- Reference [this guide](https://www.firstinspires.org/sites/default/files/uploads/resource_library/ftc/android-studio-guide.pdf) to learn how to set the software / electrical hardware up.

### Positions Reference (For Autonomous )

    (Parking area on top) [ Note locations marked 1, 2, 3, 4 ]
    ______________
    |  _|    |_  |
    |[1]      [2]|
    |   O    O   |
    |[3]      [4]|
    |____________|

### Motor Configuration

Names of motors in “Configure Robot” menu on phones:

- Front Left Motor: “FL”
- Front Right Motor: “FR”
- Back Left Motor: “BL”
- Back Right Motor: “BR”
- Intake Motor: "AD"
- Lift Motor Left: "LDL"
- Lift Motor Right: "LDR"

---

#### About The Code

The code is broken up into two parts: FtcRobotController and TeamCode. TeamCode is where you write code (most of the time you will be here). FtcRobotController contains samples for programs that can be implemented in `FtcRobotController\FtcRobotController\src\main\java\org\firstinspires\ftc\robotcontroller\external\samples`.

#### Code Architecture

FTC Robotics has 2 types of programs made for the robot: Autonomous and TeleOp. These correspond to the times in the game when the teams can use controllers to drive the robots and when they can only spectate.

- **TeleOp** code is ran when teams can **use a controller to drive around** the robot. This is hooking up the controller to the motors and servos of the robot.
- **Autonomous** code is ran when teams cannot do anything, and the **robot moves around on it's own**. This is programming the robot to move around and do things on it's own.

`FtcRobotController\TeamCode\src\main\java\org\teamcode` contains the TeleOp and Autonomous code.

#### Important Program Files:

[Hardware Mecanum](TeamCode/src/main/java/org/teamcode/HardwareMecanum.java)

- Defines the hardware that is then used in the MecanumTeleop and AutonomousProgram classes
- For instance, any motors or servos are defined here

[Mecanum Teleop](TeamCode/src/main/java/org/teamcode/MecanumTeleop.java)

- Opmode that allows for driving the robot via the left joystick
- Trig is used to rotate the input so that when somebody pushes a joystick left, the robot moves left
- Also handles spinning the robot with the right joystick

[AutonomousProgram](TeamCode/src/main/java/org/teamcode/AutonomousProgram.java)

- Program controls the robot without any input

#### Info

This GitHub repository contains the source code that is used to build an Android app to control a _FIRST_ Tech Challenge competition robot. To use this SDK, download/clone the entire project to your local computer.
