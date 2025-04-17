package sia.taco_cloud;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import sia.taco_cloud.Taco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import lombok.Data;

@Data
@Document
public class TacoOrder implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private Date placedAt;

    @NotBlank(message = "delivery name required")
    private String deliveryName;

    @NotBlank(message="Street is required")
    private String deliveryStreet;

    @NotBlank(message="City is required")
    private String deliveryCity;

    @NotBlank(message="Zip code is required")
    private String deliveryZip;

    @Size(min = 16 ,message="Not a valid credit card number")
    private String ccNumber;
    
    @Size(min=3, message="Must be formatted MM/YY")
    private String ccExpiration;

    @Size(min=3, message="Invalid CVV")
    private String ccCVV;

    private List<Taco> tacos = new ArrayList<>();


    public void addTaco(Taco taco){

        this.tacos.add(taco);
    }

    
}
