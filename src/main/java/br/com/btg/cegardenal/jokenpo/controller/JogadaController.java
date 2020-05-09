package br.com.btg.cegardenal.jokenpo.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.btg.cegardenal.jokenpo.dto.JogadaRequest;
import br.com.btg.cegardenal.jokenpo.dto.api.ApiResponse;
import br.com.btg.cegardenal.jokenpo.exception.JokenpoException;
import br.com.btg.cegardenal.jokenpo.service.impl.JogadaServiceImpl;

@RestController
@RequestMapping("/jogada")
public class JogadaController {
    private JogadaServiceImpl jogadaService;

    @Autowired
    public JogadaController(JogadaServiceImpl jogadaService) {
        this.jogadaService = jogadaService;
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> insert(@Valid @RequestBody JogadaRequest jogadaRequest)
            throws JokenpoException {
        return ResponseEntity.ok(
                new ApiResponse<>(this.jogadaService.insert(jogadaRequest)));
    }

    @DeleteMapping(value = "")
    public ResponseEntity<Object> delete(@PathParam("nome") String nome) throws JokenpoException {
        return ResponseEntity.ok(new ApiResponse<>(this.jogadaService.deleteByName(nome)));
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> getAll() throws JokenpoException {
        return ResponseEntity.ok(new ApiResponse<>(this.jogadaService.getAll()));
    }

}