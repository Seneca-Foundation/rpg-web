package com.senecafoundation;
import java.util.Optional;
import java.util.UUID;
import java.util.List;
import com.senecafoundation.CharacterTypes.Character;
import com.senecafoundation.NPCTypes.NPC;
import com.senecafoundation.DataHandler.RepoDataHandler;


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
        RepoDataHandler dataHandler;

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
            dataHandler.CreateCharacter(Npc);
            //repo.save(shadowElf);
            model.addAttribute("Npc", Npc);
            return "Npc";
        }

        @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	 public Optional<Character> getCharacter(@PathVariable UUID id) {
	 	return dataHandler.ReadCharacter(id);
	 }

        /* Place Holder for Read*/
        @RequestMapping(value = "/readform", method = RequestMethod.GET)
    public String GetCharacter(@ModelAttribute("Npc") UUID id, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error"; 
        }
        dataHandler.ReadCharacter(id);
        return "Npc";
    }
	 

    @RequestMapping(value ="/updateform", method = RequestMethod.PUT)
    public String change(@ModelAttribute("Npc") NPC Npc, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandler.UpdateCharacter(Npc);
        return "Npc"; 
    }

    @GetMapping("/deleteform")
        public String showFormDelete(Model model) {
            NPC Npc = new NPC();
            //List<Character> npcList = dataHandler.listAll();
            model.addAttribute("Npc", Npc);
            return "delete_Npc";
        }

    @RequestMapping(value = "/deleteform/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") String Id, ModelMap model) {
        try {
            dataHandler.DeleteCharacter(UUID.fromString(Id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("id", Id);
        return "itemdeleted";
        }

}