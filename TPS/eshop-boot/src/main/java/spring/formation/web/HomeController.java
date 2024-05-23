package spring.formation.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/hi")
	public String home(Model model) {
		model.addAttribute("nom", "SULTAN");
		
		return "home";
	}
}
