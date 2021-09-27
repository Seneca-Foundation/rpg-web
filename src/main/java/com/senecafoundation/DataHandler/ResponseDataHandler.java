package com.senecafoundation.DataHandler;

import java.util.List;
import java.util.UUID;
import com.senecafoundation.Repository.ResponseRepository;
import com.senecafoundation.Scene.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseDataHandler<T extends Response> {

    @Autowired
    private ResponseRepository<T> responseRepository;

    public boolean Create(Response response) {
        this.responseRepository.save((T) response);
        return true;
    }

    public Response Read(UUID id) throws Exception {
        return this.responseRepository.findById(id).orElseThrow();
    }

    public List<Response> ReadAll() {
        return (List<Response>) this.responseRepository.findAll();
    }

    public Response Update(Response response) {
        this.responseRepository.save((T) response);
        return response;
    }

    public boolean Delete(UUID id) throws Exception {
        this.responseRepository.deleteById(id);
        return false;
    }

    
}
