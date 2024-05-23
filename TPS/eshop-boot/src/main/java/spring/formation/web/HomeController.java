package spring.formation.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String defaut() {
		return "forward:/accueil";
	}

	@GetMapping("/accueil")
	public String welcome(@RequestParam(name = "username", required = false) String username, Model model) {
		if(username != null && !username.isEmpty()) {
			model.addAttribute("username", username);
		}
		
		return "home";
	}
	
}
