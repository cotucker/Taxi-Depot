package Cars;

public class SUV extends Car{
    private double clearance;
    public SUV(){
        type = "SUV";
        this.setSpecs();
    }
    @Override
    public void setSpecs() {
        super.setSpecs();
        System.out.println("Clearance...");
        clearance = input.nextDouble();
    }

    @Override
    public String _getSpecs() {
        return super._getSpecs() + ", '" + clearance + "'";
    }

    @Override
    public String getSpecs() {
        return super.getSpecs() + ", clearance: " + clearance;
    }
}
