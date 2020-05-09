package br.com.btg.cegardenal.jokenpo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.btg.cegardenal.jokenpo.dto.JogadaResponse;
import br.com.btg.cegardenal.jokenpo.dto.JogadorResponse;
import br.com.btg.cegardenal.jokenpo.dto.JokenpoResponse;
import br.com.btg.cegardenal.jokenpo.enumeration.EnumException;
import br.com.btg.cegardenal.jokenpo.enumeration.EnumMovement;
import br.com.btg.cegardenal.jokenpo.exception.JokenpoException;
import br.com.btg.cegardenal.jokenpo.service.JokenpoService;
import br.com.btg.cegardenal.jokenpo.util.JogadaSingleton;
import br.com.btg.cegardenal.jokenpo.util.JogadorSingleton;

@Service
public class JokenpoServiceImpl implements JokenpoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JokenpoServiceImpl.class);

    private static final String ZERO_WINS = "Ninguém venceu!";
    private static final String ONE_WINS = " é o vencedor";
    private static final String MANY_WINS = "vencedores : ";
    private static final String MANY_WINS_SEPARATOR = " / ";

    private JogadorServiceImpl jogadorService;
    private JogadaServiceImpl jogadaService;

    @Autowired
    public JokenpoServiceImpl(JogadorServiceImpl jogadorService,
                              JogadaServiceImpl jogadaService){
        this.jogadorService = jogadorService;
        this.jogadaService = jogadaService;
    }

    public List<JogadorResponse> iniciar() throws JokenpoException {
        LOGGER.debug("Erasing all data");
        JogadaSingleton.iniciar();
        JogadorSingleton.iniciar();
        LOGGER.debug("Data erased");
        return this.jogadorService.getAll();
    }

    public JokenpoResponse jokenpo() throws JokenpoException {
        this.checkRequirements();
        List<String> winners = new ArrayList<>();

        this.jogadaService.getAll()
                .forEach(obj -> {
                    try {
                        if(checkIfIsTheWinner(obj.getJogada().getWeakness())){
                            winners.add(obj.getJogador().getName());
                        }
                    } catch (JokenpoException e) {
                        LOGGER.error("Error detecting winners - Jogador Name : {} - Error Message : {}",
                                obj.getJogador().getName(), e.getMessage());
                    }
                });

        JokenpoResponse gameResult = new JokenpoResponse(this.getWinnersMessage(winners),
                this.getHistoryFromJogadas(this.jogadaService.getAll()));
        JogadaSingleton.iniciar();

        return gameResult;
    }

    private void checkRequirements() throws JokenpoException {
        if(this.jogadorService.getAll().size() == 0){
            throw new JokenpoException(EnumException.NOBODY_PLAYING);
        } else if (this.jogadorService.getAll().size() <= 1){
            throw new JokenpoException(EnumException.INSUFFICIENT_PLAYERS);
        } else if (this.jogadaService.getAll().size() <= 1){
            throw new JokenpoException(EnumException.INSUFFICIENT_MOVEMENTS);
        } else if (this.jogadaService.getAll().size() != this.jogadorService.getAll().size()){
            throw new JokenpoException(EnumException.PLAYERS_PENDING);
        }
    }

    private Boolean checkIfIsTheWinner(List<EnumMovement> weakness) throws JokenpoException {
        for (EnumMovement enumMovement : weakness) {
            for(JogadaResponse resp : this.jogadaService.getAll()){
                if(resp.getJogada().getName().compareTo(enumMovement.getName()) == 0){
                    return false;
                }
            }
        }

        return true;
    }

    private String getWinnersMessage(List<String> winners){
        String message = "";
        if(winners.size() == 0){
            message = ZERO_WINS;
        } else if(winners.size() == 1) {
            message = winners.get(0).toUpperCase().trim() + ONE_WINS;
        } else {
            message = MANY_WINS;
            int counter = 0;
            for(String name : winners){
                counter++;
                if(counter == winners.size()){
                    message = message + name;
                } else {
                    message = message + name + MANY_WINS_SEPARATOR;
                }
            }
        }
        return message;
    }

    private List<String> getHistoryFromJogadas(List<JogadaResponse> list) {
        List<String> result = new ArrayList<>();
        for(JogadaResponse resp : list){
            String message = resp.getJogador().getName() + " (" + resp.getJogada().getName() + ")";
            result.add(message);
        }
        return result;
    }

}