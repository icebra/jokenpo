package br.com.btg.cegardenal.jokenpo.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import br.com.btg.cegardenal.jokenpo.entity.JogadorEntity;
import br.com.btg.cegardenal.jokenpo.enumeration.EnumException;
import br.com.btg.cegardenal.jokenpo.exception.JokenpoException;
import br.com.btg.cegardenal.jokenpo.util.JogadorSingleton;

@Repository
@NoRepositoryBean
public class JogadorRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(JogadorRepository.class);

	public JogadorEntity save(JogadorEntity entity) throws JokenpoException {
		if (JogadorSingleton.getInstance() != null && JogadorSingleton.getInstance().add(entity))
			return entity;
		LOGGER.error("Erro ao salvar");
		throw new JokenpoException(EnumException.PLAYER_SAVE_ERROR);
	}

	public boolean delete(JogadorEntity entity) throws JokenpoException {
		if (JogadorSingleton.getInstance() == null) {
			LOGGER.error("Erro ao deletarr");
			throw new JokenpoException(EnumException.PLAYER_DELETE_ERROR);
		}
		return JogadorSingleton.getInstance().remove(entity);
	}

	public List<JogadorEntity> findAll() throws JokenpoException {
		if (JogadorSingleton.getInstance() == null) {
			LOGGER.error("Erro ao localizar oos kpgadores");
			throw new JokenpoException(EnumException.PLAYER_FIND_ALL_ERROR);
		}
		return JogadorSingleton.getInstance();
	}

	public JogadorEntity findByName(String name) throws JokenpoException {
		List<JogadorEntity> list = findAll().stream().filter(elem -> (elem.getName().compareToIgnoreCase(name) == 0))
				.collect(Collectors.toList());
		Optional<JogadorEntity> opt = list.stream().findFirst();
		if (opt.isPresent()) {
			return opt.get();
		}
		LOGGER.info("Jogador não encontrado : {}", name);
		throw new JokenpoException(EnumException.PLAYER_NOT_FOUND);
	}
}