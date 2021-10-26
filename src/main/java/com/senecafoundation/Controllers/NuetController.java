package com.senecafoundation.Controllers;

import java.util.UUID;
import com.senecafoundation.CharacterTypes.ICharacter;
import com.senecafoundation.CharacterTypes.Nuet;
import com.senecafoundation.DataHandler.NuetDataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("Nuet")
public class NuetController {
    @Autowired
    NuetDataHandler dataHandler;
    
    @GetMapping("/createform")
    public String showForm(Model model) {
        Nuet nuet = new Nuet();
        model.addAttribute("nuet", nuet);
        return "create_nuet";
    }

    @RequestMapping(value = "/{id}")
	 public ICharacter getCharacter(@PathVariable UUID id) throws Exception {
	 	return dataHandler.Read(id);
	 }

    @RequestMapping(value = "/createform", method = RequestMethod.POST)
    public String submit(@ModelAttribute("shadowelf") Nuet nuet, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandler.Create(nuet);
        model.addAttribute("nuet", nuet);
        return "nuet";
    }
}