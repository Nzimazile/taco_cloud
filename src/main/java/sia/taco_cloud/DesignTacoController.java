package sia.taco_cloud;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
import sia.taco_cloud.Ingredient.Type;


@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("TacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignTacoController(
        IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model)
    {
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        List<Ingredient> contents = new ArrayList<Ingredient>();
        ingredients.forEach(contents::add);
        Type[] types = Ingredient.Type.values();
        for (Type type : types) 
        {
        model.addAttribute(type.toString().toLowerCase(),
        filterByType(contents, type));
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
