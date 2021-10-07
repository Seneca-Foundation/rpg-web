package com.senecafoundation;
import java.util.UUID;
import java.util.List;
import com.senecafoundation.DataHandler.ChoiceDataHandler;
import com.senecafoundation.Scene.Choice;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("choice")
public class ChoiceController 
{
	UUID id;
    @Autowired
    ChoiceDataHandler<Choice> dataHandler;

    @GetMapping("/createform")
    public String showForm(Model model) {
        Choice choice = new Choice();
        model.addAttribute("choice", choice);
        return "create_choice";
    }
    

    @RequestMapping(value = "/createform", method = RequestMethod.POST)
    public String submit(@ModelAttribute("choice") Choice choice, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandler.Create(choice);
        //repo.save(shadowElf);
        model.addAttribute("choice", choice);
        return "choice";
    }

	@RequestMapping(value = "/{id}",  method = RequestMethod.GET)
    public String submitRead(@PathVariable("id") String id, ModelMap model) {
		try {
		Choice choice = (Choice) dataHandler.Read(UUID.fromString(id));
		model.addAttribute("choice", choice);
		} catch(Exception e) {
		}
		return "choice";

	}

    @RequestMapping(value ="/updateform/{id}", method = RequestMethod.PUT)
    public String change(@ModelAttribute("id") String id, BindingResult result, Model model) {
		try {
        Choice choice = (Choice) dataHandler.Read(UUID.fromString(id));
		model.addAttribute("choice", choice);
		} catch(Exception e) {
		}
		
		return "choice";
	}

    @GetMapping("/deleteform")
    public String showFormDelete(Model model) {
        List<Choice> choiceList = dataHandler.ReadAll();
        model.addAttribute("choice", choiceList);
        return "delete_Choice";//connects to html file
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