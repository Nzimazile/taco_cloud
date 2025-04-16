package sia.taco_cloud;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import sia.taco_cloud.TacoOrder;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("TacoOrder")
public class OrderController {


 @GetMapping("/Current")
 public String orderForm(@ModelAttribute TacoOrder tacoOrder)
 {

    return "orderForm";
 }  

 @PostMapping
 public String processOrder( @Valid @ModelAttribute("TacoOrder")  TacoOrder order, Errors errors2 ,SessionStatus sessionStatus,BindingResult result){
   if (errors2.hasErrors()) {
      result.getAllErrors().forEach(error -> log.info("Error Type: {}", error));
    return "orderForm";
     }
    log.info("Order Submitted: {}", order);
    sessionStatus.setComplete();
    return "redirect:/";
 }
    
}
