package com.senecafoundation.Controllers;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.UUID;
import com.senecafoundation.DataHandler.ResponseDataHandler;
import com.senecafoundation.Scene.Response;
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
    ResponseDataHandler<Response> dataHandler;

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
        dataHandler.Create(response);
        //repo.save(shadowElf);
        model.addAttribute("response", response);
        return "response";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showFormRead(@PathVariable("id") String Id, ModelMap model) throws Exception {
        Response response = (Response) dataHandler.Read(UUID.fromString(Id));
        model.addAttribute("response",response);
        return "response";
    }

    @RequestMapping(value ="/updateform/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") String Id, Model model) throws Exception {
        Response response = (Response) dataHandler.Read(UUID.fromString(Id));
        model.addAttribute("response", response);
        return "create_response"; 
    }
    @RequestMapping(value="/updateform", method = RequestMethod.POST)
    public String change(Response response, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        dataHandler.Update(response);
        model.addAttribute("response",response);
        return "response";   
    }

    @GetMapping("/deleteform")
    public String showFormDelete(Model model) {
        List<Response> responseList = dataHandler.ReadAll();
        model.addAttribute("response", responseList);
        return "delete_Response";//connects to html file
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
