package com.senecafoundation;
import java.util.UUID;

import com.senecafoundation.DataHandler.RepoDataHandler;
import com.senecafoundation.DataHandler.SceneDataHandler;
import com.senecafoundation.Scene.Scenario;
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
    SceneDataHandler<Scenario> dataHandler;
    
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
        dataHandler.Create(scenario);
        model.addAttribute("scenario", scenario);
        return "scenario";
    }

    /* Place Holder for Read
        @RequestMapping(value = "/readform", method = RequestMethod.GET)
    public String GetCharacter(@ModelAttribute("scenario") Scenario scenario, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error"; 
        }
        dataHandler.ReadCharacter(id)
        return "scenario";
    }
	 */

    @RequestMapping(value ="/updateform", method = RequestMethod.PUT)
    public String change(@ModelAttribute("scenario") Scenario scenario, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandler.Update(scenario);
        return "scenario"; 
    }

    @RequestMapping(value ="/deleteform", method = RequestMethod.DELETE)
    public String erase(@ModelAttribute("scenario") Scenario scenario, UUID id, BindingResult result, ModelMap model) throws Exception {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandler.Delete(id);
        return "scenario";
    }

}
