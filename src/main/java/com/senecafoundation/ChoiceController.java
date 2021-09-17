package com.senecafoundation;

import com.senecafoundation.Scene.Choice;
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
@RequestMapping("choice")
public class ChoiceController 
{
    @Autowired
    RepoDataHandler dataHandler;

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
        dataHandler.CreateChoice(choice);
        //repo.save(shadowElf);
        model.addAttribute("choice", choice);
        return "choice";
    }
}