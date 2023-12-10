package Cars;

public class Sportcar extends Car{
    private double time1_100;
    public Sportcar(){
        type = "Sportcar";
        this.setSpecs();
    }
    @Override
    public void setSpecs(){
        super.setSpecs();
        System.out.println("Time 1 to 100 km/h...");
        time1_100 = input.nextDouble();
    }
    @Override
    public String _getSpecs() {
        return super._getSpecs() + ", '" + time1_100 + "'";
    }
    @Override
    public String getSpecs() {
        return super.getSpecs() + ", 1 - 100: " + time1_100;
    }
}
