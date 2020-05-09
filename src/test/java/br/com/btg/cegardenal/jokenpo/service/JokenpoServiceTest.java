package br.com.btg.cegardenal.jokenpo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.btg.cegardenal.jokenpo.dto.JogadaRequest;
import br.com.btg.cegardenal.jokenpo.dto.JogadaResponse;
import br.com.btg.cegardenal.jokenpo.dto.JogadorRequest;
import br.com.btg.cegardenal.jokenpo.dto.JogadorResponse;
import br.com.btg.cegardenal.jokenpo.dto.JokenpoResponse;
import br.com.btg.cegardenal.jokenpo.enumeration.EnumMovement;
import br.com.btg.cegardenal.jokenpo.exception.JokenpoException;
import br.com.btg.cegardenal.jokenpo.service.impl.JogadaServiceImpl;
import br.com.btg.cegardenal.jokenpo.service.impl.JogadorServiceImpl;
import br.com.btg.cegardenal.jokenpo.service.impl.JokenpoServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class JokenpoServiceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private JogadorServiceImpl jogadorService;

    @Autowired
    private JogadaServiceImpl jogadaService;

    @Autowired
    private JokenpoServiceImpl jokenpoService;

    @Before
    public void setup(){
        this.clearAllData();
    }

    @Test
    public void clearAllDataWithSucess() throws JokenpoException {
        insertSomePlayers(Arrays.asList("P1", "P2", "P3", "P4", "P5", "P6"));
        insertSomeMovements(
            Arrays.asList(
                new JogadaRequest("P1", EnumMovement.LIZARD.getName()),
                new JogadaRequest("P2", EnumMovement.SCISSORS.getName()),
                new JogadaRequest("P3", EnumMovement.STONE.getName())
            )
        );
        Assert.assertNotEquals(0, this.jogadorService.getAll().size());
        Assert.assertNotEquals(0, this.jogadaService.getAll().size());
        this.jokenpoService.iniciar();
        Assert.assertEquals(0, this.jogadorService.getAll().size());
        Assert.assertEquals(0, this.jogadaService.getAll().size());
    }

    @Test
    public void paperVersusScissorsPlaying() throws JokenpoException {
        // Players insert
        this.insertSomePlayers(Arrays.asList("P1", "P2"));
        // Movements insert
        insertSomeMovements(
            Arrays.asList(
                new JogadaRequest("P1", EnumMovement.STONE.getName()),
                new JogadaRequest("P2", EnumMovement.PAPER.getName())
            )
        );
        // Action
        JokenpoResponse response = this.jokenpoService.jokenpo();
        // Assertments check
        Assert.assertNotNull(response.getGameResult());
        String expected = "jogador 2 vencedor".toUpperCase().trim();
        Assert.assertEquals(expected, response.getGameResult());
    }

    @Test
    public void paperVersusScissorsVersusStonePlaying() throws JokenpoException {
        // Players insert
        insertSomePlayers(Arrays.asList("P1", "P2", "P3"));
        // Movements insert
        insertSomeMovements(
            Arrays.asList(
                new JogadaRequest("P1", EnumMovement.STONE.getName()),
                new JogadaRequest("P2", EnumMovement.PAPER.getName()),
                new JogadaRequest("P3", EnumMovement.SCISSORS.getName())
            )
        );
        // Action
        JokenpoResponse response = this.jokenpoService.jokenpo();
        // Assertments check
        Assert.assertNotNull(response.getGameResult());
        String expected = "NOBODY WON!".toUpperCase().trim();
        Assert.assertEquals(expected, response.getGameResult());
    }

    @Test
    public void lizardVersusScissorsVersusPaperPlaying() throws JokenpoException {
        insertSomePlayers(Arrays.asList("P1", "P2", "P3"));
        insertSomeMovements(
            Arrays.asList(
                new JogadaRequest("P1", EnumMovement.SCISSORS.getName()),
                new JogadaRequest("P2", EnumMovement.SPOCK.getName()),
                new JogadaRequest("P3", EnumMovement.STONE.getName())
            )
        );
        JokenpoResponse response = this.jokenpoService.jokenpo();
        Assert.assertNotNull(response.getGameResult());
    }

    private List<JogadorResponse> insertSomePlayers(List<String> playerNameList) {
        List<JogadorResponse> list = new ArrayList<>();
        playerNameList.stream()
            .forEach(playerName -> {
                try {
                    list.add(this.jogadorService.insert(new JogadorRequest(playerName)));
                } catch (JokenpoException e){
                    e.printStackTrace();
                }
            }
        );
        return list;
    }

    private List<JogadaResponse> insertSomeMovements(List<JogadaRequest> movementList) throws JokenpoException {
        List<JogadaResponse> list = new ArrayList<>();
        for(JogadaRequest movement : movementList)
            list.add(this.jogadaService.insert(movement));
        return list;
    }

    private void clearAllData() {
        this.jogadorService.clearAll();
        this.jogadaService.clearAll();
    }

}