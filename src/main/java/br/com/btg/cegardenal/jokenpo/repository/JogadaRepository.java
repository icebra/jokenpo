package br.com.btg.cegardenal.jokenpo.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import br.com.btg.cegardenal.jokenpo.entity.JogadaEntity;
import br.com.btg.cegardenal.jokenpo.enumeration.EnumException;
import br.com.btg.cegardenal.jokenpo.exception.JokenpoException;
import br.com.btg.cegardenal.jokenpo.util.JogadaSingleton;

@Repository
@NoRepositoryBean
public class JogadaRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(JogadaRepository.class);

	public JogadaEntity save(JogadaEntity entity) throws JokenpoException {
		if (JogadaSingleton.getInstance() != null && JogadaSingleton.getInstance().add(entity))
			return entity;
		LOGGER.error("Erro ao salvar");
		throw new JokenpoException(EnumException.MOVEMENT_SAVE_ERROR);
	}

	public boolean delete(JogadaEntity entity) throws JokenpoException {
		if (JogadaSingleton.getInstance() == null) {
			LOGGER.error("Erro ao deletar");
			throw new JokenpoException(EnumException.MOVEMENT_DELETE_ERROR);
		}
		return JogadaSingleton.getInstance().remove(entity);
	}

	public List<JogadaEntity> findAll() throws JokenpoException {
		if (JogadaSingleton.getInstance() == null) {
			LOGGER.error("Erro ao buscar as jogadas");
			throw new JokenpoException(EnumException.MOVEMENT_FIND_ALL_ERROR);
		}
		return JogadaSingleton.getInstance();
	}

	public JogadaEntity findByName(String nome) throws JokenpoException {
		List<JogadaEntity> list = findAll().stream()
				.filter(elem -> (elem.getJogador().getName().compareToIgnoreCase(nome) == 0))
				.collect(Collectors.toList());
		Optional<JogadaEntity> opt = list.stream().findFirst();
		if (opt.isPresent()) {
			return opt.get();
		}
		LOGGER.error("Jogada não encontrada : {}", nome);
		throw new JokenpoException(EnumException.MOVEMENT_NOT_FOUND);
	}

}