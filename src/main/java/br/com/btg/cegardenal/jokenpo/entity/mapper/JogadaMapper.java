package br.com.btg.cegardenal.jokenpo.entity.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.btg.cegardenal.jokenpo.dto.JogadaRequest;
import br.com.btg.cegardenal.jokenpo.dto.JogadaResponse;
import br.com.btg.cegardenal.jokenpo.entity.JogadaEntity;

public class JogadaMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(JogadaMapper.class);

    public static JogadaEntity requestToEntity(JogadaRequest moveRequest){
        LOGGER.debug("Converting: request object to entity object");
        //return map(moveRequest, JogadaEntity.class);
        return null;
    }

    public static JogadaResponse entityToResponse(JogadaEntity entity) {
        LOGGER.debug("Converting: entity object to response object");
        JogadaResponse response = null; //map(entity, JogadaResponse.class);
        // response.setJogada(entity.getEnumMovement());
        return response;
    }

}