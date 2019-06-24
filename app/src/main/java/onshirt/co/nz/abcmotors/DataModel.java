package onshirt.co.nz.abcmotors;

public class DataModel {
    private String product_id, model, price, image;


    public String getProduct_id(){
        return "List ID: "+product_id;
    }
    public void setProduct_id(String product_id){
        this.product_id = product_id;
    }


    public String getModel(){
        return model;
    }
    public void setModel(String model){
        this.model = model;
    }


    public String getPrice(){ return "NZ$"+price; }
    public void setPrice(String price){
        this.price = price;
    }


    public String getImage(){
        return  image;
    }
    public void setImage(String image){
        this.image = image;
    }
}
