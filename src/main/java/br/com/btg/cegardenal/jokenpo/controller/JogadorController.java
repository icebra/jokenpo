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

import br.com.btg.cegardenal.jokenpo.dto.JogadorRequest;
import br.com.btg.cegardenal.jokenpo.dto.api.ApiResponse;
import br.com.btg.cegardenal.jokenpo.exception.JokenpoException;
import br.com.btg.cegardenal.jokenpo.service.impl.JogadorServiceImpl;

@RestController
@RequestMapping("/jogador")
public class JogadorController {
	private JogadorServiceImpl jogadorService;

	@Autowired
	public JogadorController(JogadorServiceImpl jogadorService) {
		this.jogadorService = jogadorService;
	}

	@PostMapping(value = "")
	public ResponseEntity<Object> insert(@Valid @RequestBody JogadorRequest jogadorRequest) throws JokenpoException {
		return ResponseEntity.ok(new ApiResponse<>(this.jogadorService.insert(jogadorRequest)));
	}

	@DeleteMapping(value = "")
	public ResponseEntity<Object> delete(@PathParam("nome") String nome) throws JokenpoException {
		return ResponseEntity.ok(new ApiResponse<>(this.jogadorService.deleteByName(nome)));
	}

	@GetMapping(value = "")
	public ResponseEntity<Object> getAll() throws JokenpoException {
		return ResponseEntity.ok(new ApiResponse<>(this.jogadorService.getAll()));
	}

}