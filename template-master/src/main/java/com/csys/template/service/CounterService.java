package com.csys.template.service;

import com.csys.template.domain.Counter;
import com.csys.template.dto.CounterDTO;
import com.csys.template.factory.CounterFactory;
import com.csys.template.repository.CounterRepository;
import com.csys.template.util.Preconditions;
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
        com.csys.template.util.Preconditions.checkBusinessLogique(counter!=null,"error type counteur does not exist");
        return CounterFactory.counterToCounterDTO(counter);
    }
    public CounterDTO addCounter(CounterDTO counterDTO) {
        Counter counter = CounterFactory.counterDTOToCounter(counterDTO);
        counter = counterRepository.save(counter);
        return CounterFactory.counterToCounterDTO(counter);
    }
    public CounterDTO updateCounter(CounterDTO counterDTO){
        Counter counterInDB = counterRepository.findByType(counterDTO.getType());
        Preconditions.checkBusinessLogique(counterInDB!=null,"error counteur does not found");
        Counter counter = counterRepository.save(CounterFactory.counterDTOToCounter(counterDTO));
        return CounterFactory.counterToCounterDTO(counter);
    }

}
