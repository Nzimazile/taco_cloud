package sia.taco_cloud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
 @GetMapping("/")
  public String home(){

    return "home";
  }  
    
  @PostMapping("/")
public String handlePostRequest() {
    return "home"; // Redirects back to home
  }

}
