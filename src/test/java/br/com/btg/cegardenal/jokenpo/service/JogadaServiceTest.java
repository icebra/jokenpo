package br.com.btg.cegardenal.jokenpo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.btg.cegardenal.jokenpo.dto.JogadaRequest;
import br.com.btg.cegardenal.jokenpo.dto.JogadorRequest;
import br.com.btg.cegardenal.jokenpo.dto.JogadorResponse;
import br.com.btg.cegardenal.jokenpo.enumeration.EnumMovement;
import br.com.btg.cegardenal.jokenpo.exception.JokenpoException;
import br.com.btg.cegardenal.jokenpo.service.impl.JogadaServiceImpl;
import br.com.btg.cegardenal.jokenpo.service.impl.JogadorServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class JogadaServiceTest {

    @Autowired
    private JogadorServiceImpl jogadorService;

    @Autowired
    private JogadaServiceImpl jogadaService;

    @Test
    public void insertManyPlayersForTestWithSucess() throws JokenpoException {
        this.jogadorService.clearAll();
        this.jogadaService.clearAll();
        List<String> nomess = new ArrayList<>(Arrays.asList("P1", "P2", "P3", "P4", "P5", "P6"));
        List<JogadorResponse> jogadorResponse = this.insertManyDifferentPlayers(nomess);
        Assert.assertEquals(nomess.size(), jogadorResponse.size());
    }

    @Test
    public void playersWithoutMovements() throws JokenpoException {
        this.insertManyPlayersForTestWithSucess();
        int playersCounter = this.jogadorService.getAll().size();
        int movementsCounter = this.jogadaService.getAll().size();
        Assert.assertEquals(0, movementsCounter);
        Assert.assertNotEquals(0, playersCounter);
    }

    @Test(expected = JokenpoException.class)
    public void insertDuplicatedMovementForSamePlayerWithException() throws JokenpoException {
        this.insertManyPlayersForTestWithSucess();
        this.jogadaService.insert(new JogadaRequest("P1", EnumMovement.PAPER.getName()));
        this.jogadaService.insert(new JogadaRequest("P1", EnumMovement.STONE.getName()));
    }

    @Test(expected = JokenpoException.class)
    public void insertDuplicatedMovementForSamePlayerAndSameMovementWithException() throws JokenpoException {
        this.insertManyPlayersForTestWithSucess();
        this.jogadaService.insert(new JogadaRequest("P1", EnumMovement.LIZARD.getName()));
        this.jogadaService.insert(new JogadaRequest("P1", EnumMovement.LIZARD.getName()));
    }

    @Test
    public void insertMovementForDifferentPlayersWithSucess() throws JokenpoException {
        this.insertManyPlayersForTestWithSucess();
        this.jogadaService.insert(new JogadaRequest("P1", EnumMovement.SPOCK.getName()));
        this.jogadaService.insert(new JogadaRequest("P2", EnumMovement.SCISSORS.getName()));
        this.jogadaService.insert(new JogadaRequest("P3", EnumMovement.LIZARD.getName()));
        this.jogadaService.insert(new JogadaRequest("P4", EnumMovement.SPOCK.getName()));
        this.jogadaService.insert(new JogadaRequest("P5", EnumMovement.PAPER.getName()));
        Assert.assertEquals(5, this.jogadaService.getAll().size());
    }

    @Test
    public void clearAllMovementsWithSucess() throws JokenpoException {
        this.insertManyPlayersForTestWithSucess();
        this.jogadaService.insert(new JogadaRequest("P1", EnumMovement.STONE.getName()));
        this.jogadaService.insert(new JogadaRequest("P2", EnumMovement.PAPER.getName()));
        this.jogadaService.insert(new JogadaRequest("P3", EnumMovement.SCISSORS.getName()));
        Assert.assertNotEquals(0, this.jogadaService.getAll().size());
        this.jogadaService.clearAll();
        Assert.assertEquals(0, this.jogadaService.getAll().size());
    }

    private List<JogadorResponse> insertManyDifferentPlayers(List<String> playerNames) throws JokenpoException {
        List<JogadorResponse> list = new ArrayList<>();
        for(String name : playerNames){
        	JogadorResponse playerResponse = this.jogadorService.insert(new JogadorRequest(name));
            list.add(playerResponse);
        }
        return list;
    }

}