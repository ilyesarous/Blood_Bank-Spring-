package com.csys.template.service;

import com.csys.template.domain.State;
import com.csys.template.dto.StateDTO;
import com.csys.template.factory.StateFactory;
import com.csys.template.repository.StateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.csys.template.TemplateApplication.log;

@Service
@Transactional
public class StateService {

    private final StateRepository stateRepository;

    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }


    @Transactional(readOnly = true)
    public StateDTO findByName(String name){
        log.debug("*** find By Name ***");
        State state;
        switch (name){
            case "1":
                 state= stateRepository.findByName(name);
                break;
            case "2":
                 state= stateRepository.findByName(name);
                break;
            default:
            case "3":
                 state= stateRepository.findByName(name);
                break;
        }

       return StateFactory.stateToStateDTO(state);
    }
    public State addState(StateDTO stateDTO) {
        log.debug("*** add State ***");
        State state = StateFactory.stateDTOToState(stateDTO);
        state = stateRepository.save(state);
        return state;
    }

}
