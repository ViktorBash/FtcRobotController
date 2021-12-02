class Motor2{
    public final double tpr, rpm; //ticks per rotation, max rotations per minute
    public double angle = 0, velocity = 0; //degrees, deg/s
    public final DCMotor motor;
    public final boolean includeRevs; //includes number of revolutions in angle variable

    public Motor2(String n){
        this(n, 537.6, 312);
    }
    public Motor2(String n, double tpr, double rpm){
        motor = hwMap.DCMotor.get(DCMotor.class, n);
        motor.setPower(0);
        motor.setDirection(DCMotor.Direction.FORWARD);
        motor.setZeroPowerBehavior(DCMotor.ZeroPowerBehavior.BRAKE);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.tpr=tpr;
        this.rpm=rpm;
        includeRevs = false;
        Hardware.motors.add(this);
    }
    public void turn(double deg){}
    public void turnTo(double deg){}
}