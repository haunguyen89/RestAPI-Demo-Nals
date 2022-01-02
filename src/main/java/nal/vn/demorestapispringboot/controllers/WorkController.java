package nal.vn.demorestapispringboot.controllers;

import nal.vn.demorestapispringboot.models.ResponseObject;
import nal.vn.demorestapispringboot.models.Work;
import nal.vn.demorestapispringboot.repositories.WorkRepositories;
import nal.vn.demorestapispringboot.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/v1/works")
public class WorkController {
    @Autowired
    private WorkService service;
    @Autowired
    private WorkRepositories repositories;

    // Get all work
    @GetMapping
    public ResponseEntity<List<Work>> getAllWork(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "1000") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String desc) {
        List<Work> list = service.getAllWork(pageNo, pageSize, sortBy, desc);
        return new ResponseEntity<List<Work>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    // Get only work with id
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<Work> foundWork = repositories.findById(id);
        return foundWork.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Query work successfully", foundWork)) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("False", "Cannot find work with id = " + id, ""));
    }

    // Insert work
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertWork(@RequestBody Work newWork) {
        List<Work> foundWorks = repositories.findByWorkName(newWork.getWorkName().trim());
        return foundWorks.size() > 0 ? ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("False", "Work name already taken", "")) : ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Insert work successfully", repositories.save(newWork)));
    }

    // Update work
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateWork(@RequestBody Work newWork, @PathVariable Long id) {
        Work updatedWork = repositories.findById(id).map(work -> {
            work.setWorkName(newWork.getWorkName());
            work.setStartingDate(newWork.getStartingDate());
            work.setEndingDate(newWork.getEndingDate());
            work.setStatus(newWork.getStatus());
            return repositories.save(work);
        }).orElseGet(() -> {
            newWork.setId(id);
            return repositories.save(newWork);
        });
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Update work successfully", updatedWork));
    }

    // Delete work
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteWork(@PathVariable Long id) {
        boolean exits = repositories.existsById(id);
        if (exits) {
            repositories.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Delete work successfully", ""));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("False", "Can not find work to delete", ""));
    }
}
