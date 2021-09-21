package com.senecafoundation;
import java.util.UUID;
import com.senecafoundation.Scene.Response;
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
@RequestMapping("response")
public class ResponseController 
{
    @Autowired
    RepoDataHandler dataHandler;

    @GetMapping("/createform")
    public String showForm(Model model) {
        Response response = new Response();
        model.addAttribute("response", response);
        return "create_response";
    }

    @RequestMapping(value = "/createform", method = RequestMethod.POST)
    public String submit(@ModelAttribute("response") Response response, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandler.CreateResponse(response);
        //repo.save(shadowElf);
        model.addAttribute("response", response);
        return "response";
    }

    /* Place Holder for Read
        @RequestMapping(value = "/readform", method = RequestMethod.GET)
    public String GetCharacter(@ModelAttribute("response") Response response, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error"; 
        }
        dataHandler.ReadResponse(id)
        return "response";
    }
	 */

    @RequestMapping(value ="/updateform", method = RequestMethod.PUT)
    public String change(@ModelAttribute("response") Response response, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandler.UpdateResponse(response);
        return "response"; 
    }

    @RequestMapping(value ="/deleteform", method = RequestMethod.DELETE)
    public String erase(@ModelAttribute("response") Response response, UUID id, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandler.DeleteResponse(id);
        return "response";
    }

}
