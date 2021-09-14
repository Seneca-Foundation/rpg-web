package com.senecafoundation;

import com.senecafoundation.CharacterTypes.ShadowElf;
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
@RequestMapping("shadowelf")
public class ShadowElfController {
    @Autowired
    RepoDataHandler dataHandler;
    
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
        //repo.save(shadowElf);
        model.addAttribute("shadowelf", shadowElf);
        return "shadowelf";
    }
}