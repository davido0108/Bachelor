package chat.services.rest;

import chat.domain.Spectacol;
import chat.repository.SpectacolRepo;
import chat.repository.dbrepos.SpectacolDBRepo;

import chat.repository.dbrepos.SpectacolDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat/users")

public class SpectacolController {

    private static final String template = "Hello, %s!";

    @Autowired
    private SpectacolDBRepo spectacolRepo;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return String.format(template, name);
    }

    @RequestMapping( method=RequestMethod.GET)
    public Iterable<Spectacol> getAll(){
        System.out.println("Get all tasks...");
        return spectacolRepo.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable String id){

        Spectacol user= spectacolRepo.findOne(Long.parseLong(id));
        if (user==null)
            return new ResponseEntity<String>("User not found",HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Spectacol>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Spectacol create(@RequestBody Spectacol user){
        spectacolRepo.save(user);
        return user;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Spectacol update(@RequestBody Spectacol user) {
        System.out.println("Updating user ...");
        spectacolRepo.update(user);
        return user;

    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable String id){
        System.out.println("Deleting user ... " + id);
        try {
            spectacolRepo.delete(Long.parseLong(id));
            return new ResponseEntity<Spectacol>(HttpStatus.OK);
        }catch (Exception ex){
            System.out.println("Ctrl Delete user exception");
            return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


//    @RequestMapping("/{user}/name")
//    public String name(@PathVariable String user){
//        User result=userRepository.findBy(user);
//        System.out.println("Result ..."+result);
//
//        return result.getName();
//    }
//
//
//
//    @ExceptionHandler(RepositoryException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String userError(RepositoryException e) {
//        return e.getMessage();
//    }


}
