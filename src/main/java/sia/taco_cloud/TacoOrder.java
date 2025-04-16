package sia.taco_cloud;

import java.util.List;
import java.util.Date;

import org.hibernate.validator.constraints.CreditCardNumber;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.ArrayList;
import lombok.Data;

@Data
public class TacoOrder implements Serializable{

    private static final long serialVersionUID = 1L;
    private Date placedAt;
    private Long id;
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
