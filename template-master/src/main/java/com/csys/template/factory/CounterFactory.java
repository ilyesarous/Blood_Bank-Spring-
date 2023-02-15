package com.csys.template.factory;

import com.csys.template.domain.Counter;
import com.csys.template.dto.CounterDTO;

import java.util.ArrayList;
import java.util.List;

public class CounterFactory {

    public static CounterDTO counterToCounterDTO(Counter counter){
        CounterDTO counterDTO = new CounterDTO();
        counterDTO.setPrefix(counter.getPrefix());
        counterDTO.setSuffix(counter.getSuffix());
        counterDTO.setType(counter.getType());

        return counterDTO;
    }

    public static Counter counterDTOToCounter(CounterDTO counterDTO){
        Counter counter = new Counter();
        counter.setPrefix(counterDTO.getPrefix());
        counter.setSuffix(counterDTO.getSuffix());
        counter.setType(counterDTO.getType());

        return counter;
    }

    public static List<CounterDTO> countersToCountersDTO(List<Counter> counters){
        List<CounterDTO> counterDTOS = new ArrayList<CounterDTO>();
        for(Counter counter : counters){
            counterDTOS.add(counterToCounterDTO(counter));
        }
        return counterDTOS;
    }

    public static List<Counter> countersDTOToCounters(List<CounterDTO> counters){
        List<Counter> counter = new ArrayList<Counter>();
        for(CounterDTO counterDTO : counters){
            counter.add(counterDTOToCounter(counterDTO));
        }
        return counter;
    }

}
