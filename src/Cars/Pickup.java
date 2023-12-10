package Cars;

public class Pickup extends Car{
    private double payload;
    public Pickup(){
        type = "Pickup";
        this.setSpecs();
    }
    @Override
    public void setSpecs(){
        super.setSpecs();
        System.out.println("Payload...");
        payload = input.nextDouble();
    }

    @Override
    public String _getSpecs() {
        return super._getSpecs() + ", '" + payload + "'";
    }

    @Override
    public String getSpecs() {
        return super.getSpecs() + ", payload: " + payload;
    }
}
