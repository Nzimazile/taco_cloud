package sia.taco_cloud;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import sia.taco_cloud.ingredient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Taco {


    private Date createdAt = new Date();

    @NotNull
    @Size(min=5 ,message = "Name must be atleast five characters long")
    private String name ;

    @NotNull
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<Ingredient> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(taco.toIngredient(ingredient));
        }
    
}
