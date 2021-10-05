package com.senecafoundation;

import java.util.UUID;
import java.util.List;
import com.senecafoundation.CharacterTypes.ICharacter;
import com.senecafoundation.DataHandler.NpcDataHandler;
import com.senecafoundation.NPCTypes.NPC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("Npc")
public class NPCController 
{
    @Autowired
    NpcDataHandler dataHandler;

    @GetMapping("/createform")
    public String showForm(Model model) {
        NPC Npc = new NPC();
        model.addAttribute("Npc", Npc);
        return "create_Npc";
    }

    @RequestMapping(value = "/createform", method = RequestMethod.POST)
    public String submit(@ModelAttribute("Npc") NPC Npc, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandler.Create(Npc);
        //repo.save(shadowElf);
        model.addAttribute("Npc", Npc);
        return "Npc";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showFormRead(@PathVariable("id") String Id, ModelMap model) throws Exception {
        NPC npc = (NPC) dataHandler.Read(UUID.fromString(Id));
        model.addAttribute("Npc",npc);
        return "Npc";
    }

    @RequestMapping(value ="/updateform/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") String Id, Model model) throws Exception {
        NPC npc = (NPC) dataHandler.Read(UUID.fromString(Id));
        dataHandler.Update(npc);
        model.addAttribute("Npc", npc);
        return "create_Npc"; 
    }
    // @RequestMapping(value="/updateform", method = RequestMethod.PUT)
    // public String change(@ModelAttribute("Npc") NPC npc, BindingResult result, ModelMap model) {
    //     if (result.hasErrors()) {
    //         return "Error";
    //     }
    //     dataHandler.Update(npc);
    //     return "Npc";   
    // }

    @GetMapping("/deleteform")
    public String showFormDelete(Model model) {
        List<ICharacter> npcList = dataHandler.ReadAll();
        model.addAttribute("Npc", npcList);
        return "delete_Npc";
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