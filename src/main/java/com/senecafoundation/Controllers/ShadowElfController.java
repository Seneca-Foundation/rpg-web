package com.senecafoundation.Controllers;

import java.util.UUID;
import java.util.List;
import com.senecafoundation.CharacterTypes.ICharacter;
import com.senecafoundation.CharacterTypes.ShadowElf;
import com.senecafoundation.DataHandler.ShadowElfDataHandler;
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
@RequestMapping("shadowelf")
public class ShadowElfController {
    @Autowired
    ShadowElfDataHandler dataHandler;
    
    @GetMapping("/createform")
    public String showForm(Model model) {
        ShadowElf shadowElf = new ShadowElf();
        model.addAttribute("shadowelf", shadowElf);
        return "create_shadowelf";
    }

    @RequestMapping(value = "/createform", method = RequestMethod.POST)
    public String submit(@ModelAttribute("shadowelf") ShadowElf shadowElf, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandler.Create(shadowElf);
        model.addAttribute("shadowelf", shadowElf);
        return "shadowelf";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showFormRead(@PathVariable("id") String Id, ModelMap model) throws Exception {
        ShadowElf shadowelf = (ShadowElf) dataHandler.Read(UUID.fromString(Id));
        model.addAttribute("shadowelf",shadowelf);
        return "shadowelf";
    }

    @RequestMapping(value ="/updateform/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") String Id, Model model) throws Exception {
        ShadowElf shadowelf = (ShadowElf) dataHandler.Read(UUID.fromString(Id));
        model.addAttribute("shadowelf", shadowelf);
        return "create_shadowelf"; 
    }
    @RequestMapping(value="/updateform", method = RequestMethod.POST)
    public String change(ShadowElf shadowelf, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandler.Update(shadowelf);
        model.addAttribute("shadowelf",shadowelf);
        return "shadowelf";   
    }

    @GetMapping("/deleteform")
    public String showFormDelete(Model model) {
        List<ICharacter> shadowElfList = dataHandler.ReadAll();
        model.addAttribute("shadowelf", shadowElfList);
        return "delete_Shadowelf";
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