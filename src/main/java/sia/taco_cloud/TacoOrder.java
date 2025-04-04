package sia.taco_cloud;

import java.util.List;
import java.util.ArrayList;
import lombok.Data;

@Data
public class TacoOrder {

    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;

    public List<Taco> tacos = new ArrayList<>();


    public void addTaco(Taco taco){

        this.tacos.add(taco);
    }

    
}
