package com.csys.template.factory;

import com.csys.template.domain.State;
import com.csys.template.dto.StateDTO;

import java.util.ArrayList;
import java.util.List;

public class StateFactory {
    public static StateDTO stateToStateDTO(State state){
        StateDTO stateDTO = new StateDTO();
        stateDTO.setId(state.getCode());
        stateDTO.setUser(state.getUser());
        stateDTO.setName(state.getName());

        return stateDTO;
    }

    public static State stateDTOToState(StateDTO stateDTO){
        State state = new State();

        state.setCode(stateDTO.getId());
        state.setName(stateDTO.getName());
        state.setUser(stateDTO.getUser());

        return state ;
    }

    public static List<StateDTO> statesToStatesDTO(List<State> states){
        List<StateDTO> stateDTOS = new ArrayList<StateDTO>();
        for(State patient : states){
            stateDTOS.add(stateToStateDTO(patient));
        }
        return stateDTOS;
    }

    public static List<State> statesDTOToStates(List<StateDTO> stateDTOS){
        List<State> states = new ArrayList<State>();
        for(StateDTO stateDTO : stateDTOS){
            states.add(stateDTOToState(stateDTO));
        }
        return states;
    }
}
