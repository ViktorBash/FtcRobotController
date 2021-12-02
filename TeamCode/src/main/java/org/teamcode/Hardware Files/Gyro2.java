class Gyro2{
    public final IntegratingGyroscope gyro;
    public final ModernRoboticsI2cGyro basicGyro;
    public Gyro2(String n){
        basicGyro = hardwareMap.get(ModernRoboticsI2cGyro.class,n);
        gyro = (IntegratingGyroscope) basicGyro;
        Hardware.gyros.add(this);
    }
}