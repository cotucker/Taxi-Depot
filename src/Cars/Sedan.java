package Cars;

public class Sedan extends Car{

    public Sedan(){
        type = "Sedan";
        this.setSpecs();
    }
    @Override
    public String getSpecs(){
       return super.getSpecs();
    }

    @Override
    public void setSpecs() {
        super.setSpecs();
    }
}
