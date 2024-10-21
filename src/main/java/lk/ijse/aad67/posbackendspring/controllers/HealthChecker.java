package lk.ijse.aad67.posbackendspring.controllers;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/health")
public class HealthChecker {
    @GetMapping
    public String heathTest(){
        return "Pos System is working";
    }
}
