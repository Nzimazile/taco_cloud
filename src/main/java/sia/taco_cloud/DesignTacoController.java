package sia.taco_cloud;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import sia.taco_cloud.Ingredient;
import sia.taco_cloud.Ingredient.Type;
import sia.taco_cloud.Taco;


@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("TacoOrder")
public class DesignTacoController {

    @ModelAttribute
    public void addIngredientsToModel(Model model)
    {
        List<Ingredient> ingredients = Arrays.asList(
        new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
        new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
        new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
        new Ingredient("CARN", "Carnitas", Type.PROTEIN),
        new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
        new Ingredient("LETC", "Lettuce", Type.VEGGIES),
        new Ingredient("CHED", "Cheddar", Type.CHEESE),
        new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
        new Ingredient("SLSA", "Salsa", Type.SAUCE),
        new Ingredient("SRCR", "Sour Cream", Type.SAUCE)); 

        Type[] types = Ingredient.Type.values();
        for(Type type: types)
        {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

    }

    @ModelAttribute("TacoOrder")
    public TacoOrder tacoOrder(){
        return new TacoOrder();
    }

    @ModelAttribute("taco")
    public Taco taco()
    {
        return new Taco();
    }
    

    @GetMapping 
    public String showDesignForm( Model model)
    {
        
        return "design";
    }

    @PostMapping
    public String processTaco( @ModelAttribute TacoOrder tacoOrder, @Valid Taco taco, Errors errors ){
        if (errors.hasErrors()) {
            return "design";
        }
              tacoOrder.addTaco(taco);
              log.info("Processing taco: {}", taco);
              return "redirect:/orders/Current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type)
    {
        return ingredients.stream().filter(x->x.getType().equals(type)).collect(Collectors.toList());
    }
}
