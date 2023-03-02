package com.csys.template.service;

import com.csys.template.domain.Counter;
import com.csys.template.dto.CounterDTO;
import com.csys.template.factory.CounterFactory;
import com.csys.template.repository.CounterRepository;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CounterService {

    private final CounterRepository counterRepository;


    public CounterService(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Transactional(readOnly = true)
    public List<CounterDTO> findAll(){
        return CounterFactory.countersToCountersDTO(counterRepository.findAll());
    }
    @Transactional(readOnly = true)
    public CounterDTO findCounterByType(String type){
        Counter counter = counterRepository.findByType(type);
        return CounterFactory.counterToCounterDTO(counter);
    }
    public CounterDTO addCounter(CounterDTO counterDTO) {
        Counter counter = CounterFactory.counterDTOToCounter(counterDTO);
        counter = counterRepository.save(counter);
        return CounterFactory.counterToCounterDTO(counter);
    }
    public CounterDTO updateCounter(CounterDTO counterDTO){
        Counter counterInDB = counterRepository.findByType(counterDTO.getType());
        Preconditions.checkArgument (counterInDB != null, "Counter has been deleted");
        Counter counter = counterRepository.save(CounterFactory.counterDTOToCounter(counterDTO));
        return CounterFactory.counterToCounterDTO(counter);
    }

}
