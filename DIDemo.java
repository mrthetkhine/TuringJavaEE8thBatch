class Engine
{
    void start()
    {
        System.out.println("Engine started");
    }
}
class ElectricEngine extends Engine
{
    void start()
    {
        System.out.println("Electric Engine started");
    }
}
class DieselEngine extends Engine
{
    void start()
    {
        System.out.println("Diesel Engine started");
    }
}
class Car
{
    Engine engine;

    public Car(Engine engine)
    {
        this.engine = engine;
    }

    void start()
    {
        System.out.println("Car started");
        engine.start();
        
    }
   
}
public class DIDemo
{
    public static void main(String[]args)
    {
        System.out.println("Hello world");
        Engine engine = new DieselEngine();
        Car car = new Car(engine);
        car.start();
    }
}