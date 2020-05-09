package br.com.btg.cegardenal.jokenpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.btg.cegardenal.jokenpo.dto.api.ApiResponse;
import br.com.btg.cegardenal.jokenpo.exception.JokenpoException;
import br.com.btg.cegardenal.jokenpo.service.impl.JokenpoServiceImpl;

@RestController
@RequestMapping("/jokenpo")
public class JokenpoController {
    private JokenpoServiceImpl jokenpoService;

    @Autowired
    public JokenpoController(JokenpoServiceImpl jokenpoService) {
        this.jokenpoService = jokenpoService;
    }

    @DeleteMapping(value = "")
    public ResponseEntity<Object> iniciar() throws JokenpoException {
        return ResponseEntity.ok(new ApiResponse<>(this.jokenpoService.iniciar()));
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> jokenpo() throws JokenpoException {
        return ResponseEntity.ok(new ApiResponse<>(this.jokenpoService.jokenpo()));
    }
}