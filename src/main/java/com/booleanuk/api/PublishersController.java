package com.booleanuk.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("publisher")
public class PublishersController {
    private ArrayList<Publisher> publisher;

    public PublishersController() {
        this.publisher = new ArrayList<>();

        this.publisher.add(new Publisher("JRR Tolkien", "Gothenburg"));
        this.publisher.add(new Publisher("Jane Austen", "Gothenburg"));
    }

    @GetMapping
    public ArrayList<Publisher> getAll() {
        return this.publisher;
    }
    @GetMapping("/{id}")
    public Publisher getOne(@PathVariable(name = "id") int id) {
        if (id < this.publisher.size()) {
            return this.publisher.get(id);
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher create(@RequestBody Publisher publisher){
        this.publisher.add(publisher);
        return publisher;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher update(@PathVariable int id, @RequestBody Publisher publisher){
        // if id inside the array list, take the name from arg then setName to this id
        if(id <this.publisher.size()){
            this.publisher.get(id).setName(publisher.getName());
            this.publisher.get(id).setCity(publisher.getCity());
            return this.publisher.get(id);
        }
        return null;

    }
    @DeleteMapping("/{id}")
    public Publisher delete(@PathVariable int id){
        if(id < this.publisher.size()){
            return this.publisher.remove(id);
        }
        return null;
    }
}