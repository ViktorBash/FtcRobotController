class DistSen2{
    public double xPos, yPos, angle; //All of these are relative to the center of the robot. 3:00 of the robot is 0 degrees. 12:00 is 90. 9:00 is 180, 6:00 is 270.
    //public final Rev2mDistanceSensor distSensor;
    public final DistanceSensor distSen;
    public DistSen2(Stirng n){
        this(n,0,0,0);
    }
    public DistSen2(String n, double x, double y, double a){
        distSen = hwMap.DCMotor.get(DistanceSensor.class, n);
        xPos = x;
        yPos = y;
        angle = a;
        Hardware.distSens.add(this);
    }
    public void updateLocation(){} //use the gyro
}