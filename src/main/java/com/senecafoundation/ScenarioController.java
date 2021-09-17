package com.senecafoundation;

import com.senecafoundation.Scene.Scenario;
import com.senecafoundation.DataHandler.RepoDataHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("scenario")
public class ScenarioController 
{
    @Autowired
    RepoDataHandler dataHandler;
    
    @GetMapping("/createform")
    public String showForm(Model model) {
        Scenario scenario = new Scenario();
        model.addAttribute("scenario", scenario);
        return "create_scenario";
    }

    @RequestMapping(value = "/createform", method = RequestMethod.POST)
    public String submit(@ModelAttribute("scenario") Scenario scenario, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandler.CreateScenario(scenario);
        //repo.save(shadowElf);
        model.addAttribute("scenario", scenario);
        return "scenario";
    }
}
