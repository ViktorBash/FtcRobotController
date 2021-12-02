class Servo2{
    public final Servo servo;
    public Servo2(String n){
        this(n, 0, 180);
    }
    public Servo2(String n, double min, double max){
        servo = hwMap.DCMotor.get(DCMotor.class, n);
        servo.scaleRange(min,max);
        servo.setPosition(0);
        Hardware.servos.add(this);
    }
    public void turn(double deg){}
    public void turnTo(double deg){}
}