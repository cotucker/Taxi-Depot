package Cars;

import java.util.Scanner;

abstract public class Car{
    Scanner input = new Scanner(System.in);
     protected String brand;
     protected String color;
     protected String modelID;
     protected double mass;
     protected  double hp;
     protected String type;
     protected double fuelСonsumption;
     protected double price;
     protected double maxSpeed;

     public String getSpecs(){
         return "Brand: " + brand + ", Model: " + modelID + ", type: " + type + ", price: " + price + ", perfomance: " + hp + ", fuel consumption: " + fuelСonsumption + ", max speed: " + maxSpeed;
     }
    public String _getSpecs(){
        return "'" + brand + "', '" + modelID + "', '" + type + "', '" + mass + "', '" + hp + "', '" + fuelСonsumption + "', '"  + maxSpeed + "', '" + price + "', '" + color + "'";
    }
     public void setSpecs(){
         System.out.println("Enter values\nBrand...");
         brand = input.nextLine();
         System.out.println("Color...");
         color= input.nextLine();
         System.out.println("Model...");
         modelID = input.nextLine();
         System.out.println("Mass...");
         mass = input.nextDouble();
         System.out.println("Performance...");
         hp = input.nextDouble();
         System.out.println("Fuel consumption...");
         fuelСonsumption = input.nextDouble();
         System.out.println("Max speed...");
         maxSpeed = input.nextDouble();
         System.out.println("Price...");
         price = input.nextDouble();
     }
     public String getType(){
         return type;
     }
}
