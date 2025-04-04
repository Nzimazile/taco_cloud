package sia.taco_cloud;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class Taco {
    
    private String name ;

    private List<Ingredient> ingredients;
    
}
