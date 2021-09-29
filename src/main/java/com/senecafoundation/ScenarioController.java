package com.senecafoundation;
import java.util.UUID;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
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

    @GetMapping("/readform")
    public String showFormRead(Model model) {
        List<Scenario> scenarioList = dataHandler.ReadAll();
        model.addAttribute("scenario", scenarioList);
        return "read_Scenario";//connects to html file
    }

    @RequestMapping(value = "/readform/{id}", method = RequestMethod.GET)
    public String read(@PathVariable("id") String Id, ModelMap model) {
        try {
            dataHandler.Read(UUID.fromString(Id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("Id", Id);
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

    @GetMapping("/deleteform")
    public String showFormDelete(Model model) {
        List<Scenario> scenarioList = dataHandler.ReadAll();
        model.addAttribute("scenario", scenarioList);
        return "delete_Scenario";//connects to html file
    }

    @RequestMapping(value = "/deleteform/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") String Id, ModelMap model) {
        try {
            dataHandler.Delete(UUID.fromString(Id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("Id", Id);
        return "itemdelete";
    }

}
