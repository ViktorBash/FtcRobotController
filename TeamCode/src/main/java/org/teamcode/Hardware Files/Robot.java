import com.qualcomm.*;
import org.firstinspires.ftc.*;
import java.util.*;

public abstract class Robot{
    /* Public OpMode members. */
    public final double mmPerIn = 25.4, inPerMm = 1/25.4; //just to convert

    public static Motor2 frDrive, flDrive, brDrive, blDrive, shooter, shotAngler, shotLoader, rollerIntake;
    public static Servo2 intakeScissor1, intakeSwivel1, intakeClaw1, intakeScissor2, intakeSwivel2, intakeClaw2, 
                         intakeScissor3, intakeSwivel3, intakeClaw3, wobbleScissor, wobbleClaw, shotTurret; 
    public static DistSen2 frDist, flDist, brDist, blDist, fruDist, bruDist, fluDist, bluDist;
    public static Gyro2 robotGyro, frGyro, flGyro, brGyro, blGyro;
    public static Accel2 fAccel, rAccel, lAccel, bAccel;
    
    public static ArrayList<Motor2> motors = new ArrayList<Motor2>();
    public static ArrayList<Servo2> servos = new ArrayList<Servo2>();
    public static ArrayList<DistSen2> distSens = new ArrayList<DistSen2>();
    public static ArrayList<Gyro2> gyros = new ArrayList<Gyro2>();
    public static ArrayList<Accel2> accels = new ArrayList<Accel2>();


    /* local OpMode members. */
    public static HardwareMap hwMap;
    public static ElapsedTime timer = new ElapsedTime();

    /* Initialize standard Hardware interfaces */
    public static void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        frDrive = new Motor2("frDrive", 537.6, 312);
        brDrive = new Motor2("brDrive", 537.6, 312);
        flDrive = new Motor2("flDrive", 537.6, 312);
            flDrive.motor.setDirection(DCMotor.Direction.REVERSE);
        blDrive = new Motor2("blDrive", 537.6, 312);
            blDrive.motor.setDirection(DCMotor.Direction.REVERSE);
        shooter = new Motor2("shooter"); //6000 rpm
        shotAngler = new Motor2("shotAngler"); //30 rpm
            shotAngler.includeRevs=true;
        shotLoader = new Motor2("shotLoader"); //1150 rpm
        rollerIntake = new Motor2("rollerIntake");
        
        // Define and Initialize Servos
        
        // Define and Initialize Gyros

        // Define and Initialize Accels

        // Define and Initialize DistSens
    }
}