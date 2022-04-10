package com.example.registerstartupproject.userConnectedAcctionsExceptLogin.InsertIdeaByUser;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "${frontEndLink}", allowedHeaders = "*")
public class InsertIdeaController {
    private final InsertIdeaService insertIdeaService;

    @PostMapping("/insertIdea")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void insert(@RequestBody @Valid InsertDTOOuter dtoOuter) {
        InsertDTOInner insertDTOInner = new InsertDTOInner(dtoOuter.getContent(), dtoOuter.getEmail(), LocalDateTime.now());
        insertIdeaService.setContentByUser(insertDTOInner);
    }
}
