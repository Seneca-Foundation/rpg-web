package com.senecafoundation.Controllers;

import java.util.UUID;
import java.util.List;
import com.senecafoundation.DataHandler.ResponseDataHandler;
import com.senecafoundation.Scene.Response;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class ResponseRESTController 
{
    @Autowired
    ResponseDataHandler<Response> dataHandler;

    @PostMapping("/responses")
    Response newResponse(@RequestBody Response newResponse) 
    {
        dataHandler.Create(newResponse);
        return newResponse;
    }

    @GetMapping("/responses/{Id}")
    Response findResponse(@PathVariable String Id) throws Exception 
    {
        Response foundResponse = (Response) dataHandler.Read(UUID.fromString(Id));
        return foundResponse;
    }

    @GetMapping("/responses")
    List<Response> responseList() 
    {
        return dataHandler.ReadAll();
    }

    @PutMapping("/responses/{Id}")
    Response updateResponse(@RequestBody Response newResponse, @PathVariable String Id) throws Exception
    {
        Response response = (Response) dataHandler.Read(UUID.fromString(Id));
        if(response != null)
        {
            newResponse.setId(response.getId());
            return dataHandler.Update(newResponse);
        }
        else
        {
            throw new Exception("No Response found with id: " + Id);
        }
    }

    @DeleteMapping("/responses/{Id}")
    void deleteResponse(@PathVariable String Id) throws Exception 
    {
        dataHandler.Delete(UUID.fromString(Id));
    }
    
}
