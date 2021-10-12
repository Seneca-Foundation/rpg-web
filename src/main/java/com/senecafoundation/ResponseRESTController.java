package com.senecafoundation;

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

    @PostMapping("/response")
    Response newResponse(@RequestBody Response newResponse) 
    {
        dataHandler.Create(newResponse);
        return newResponse;
    }

    @GetMapping("/response/{id}")
    Response findResponse(@PathVariable String Id) throws Exception 
    {
        Response foundResponse = (Response) dataHandler.Read(UUID.fromString(Id));
        return foundResponse;
    }

    @GetMapping("/response")
    List<Response> responseList() 
    {
        return dataHandler.ReadAll();
    }

    @PutMapping("/response/{id}")
    Response updateResponse(@RequestBody Response newResponse, @PathVariable String Id) throws Exception
    {
        newResponse = (Response) dataHandler.Read(UUID.fromString(Id));
        return (Response)dataHandler.Update(newResponse);
    }

    @DeleteMapping("/response/{id}")
    void deleteResponse(@PathVariable String Id) throws Exception 
    {
        dataHandler.Delete(UUID.fromString(Id));
    }
    
}
