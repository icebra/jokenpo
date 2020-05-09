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

import br.com.btg.cegardenal.jokenpo.dto.JogadorRequest;
import br.com.btg.cegardenal.jokenpo.dto.JogadorResponse;
import br.com.btg.cegardenal.jokenpo.exception.JokenpoException;
import br.com.btg.cegardenal.jokenpo.service.impl.JogadorServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class JogadorServiceTest {

	@Autowired
	private JogadorServiceImpl playerService;

	@Test
	public void oneInsertWithSucess() throws Exception {
		this.playerService.clearAll();
		String expectedJogadorName = "NOME DO JOGADOR";
		JogadorResponse playerResponse = this.playerService.insert(new JogadorRequest(expectedJogadorName));
		Assert.assertEquals(expectedJogadorName, playerResponse.getName());
	}

	@Test(expected = JokenpoException.class)
	public void duplicateInsertForExceptionGenerate() throws Exception {
		this.playerService.clearAll();
		String sameJogadorName = "Já existe jogador com esse nome";
		this.playerService.insert(new JogadorRequest(sameJogadorName));
		this.playerService.insert(new JogadorRequest(sameJogadorName));
	}

	@Test(expected = JokenpoException.class)
	public void getEntityByNameWithException() throws Exception {
		this.playerService.clearAll();
		List<String> playerNames = new ArrayList<>(Arrays.asList("P1", "P2", "P3", "P4", "P5"));
		this.insertManyDifferentPlayers(playerNames);
		this.playerService.getEntityByName("INEXISTENTE");
	}

	@Test
	public void deleteByNameWithSucess() throws Exception {
		this.playerService.clearAll();
		List<String> playerNames = new ArrayList<>(Arrays.asList("P1", "P2", "P3", "P4", "P5"));
		List<JogadorResponse> playerResponse = this.insertManyDifferentPlayers(playerNames);
		int expected1 = playerNames.size() - 1;
		int expected2 = playerResponse.size() - 1;
		List<JogadorResponse> list = this.playerService.deleteByName("P1");
		// Assertments check
		Assert.assertEquals(expected1, list.size());
		Assert.assertEquals(expected2, list.size());
	}

	@Test(expected = JokenpoException.class)
	public void deleteByNameWithException() throws Exception {
		this.playerService.clearAll();
		List<String> playerNames = new ArrayList<>(Arrays.asList("P1", "P2", "P3", "P4", "P5"));
		this.insertManyDifferentPlayers(playerNames);
		this.playerService.deleteByName("INEXISTENTE");
	}

	@Test
	public void clearAllWithSucess() throws Exception {
		this.playerService.clearAll();
		List<String> playerNames = new ArrayList<>(Arrays.asList("P1", "P2", "P3", "P4", "P5"));
		List<JogadorResponse> playerResponse = this.insertManyDifferentPlayers(playerNames);
		Assert.assertEquals(playerNames.size(), playerResponse.size());
		this.playerService.clearAll();
		playerResponse = this.playerService.getAll();
		Assert.assertEquals(0, playerResponse.size());
	}

	private List<JogadorResponse> insertManyDifferentPlayers(List<String> playerNames) throws JokenpoException {
		List<JogadorResponse> list = new ArrayList<>();
		for (String name : playerNames) {
			JogadorResponse playerResponse = this.playerService.insert(new JogadorRequest(name));
			list.add(playerResponse);
		}
		return list;
	}

}