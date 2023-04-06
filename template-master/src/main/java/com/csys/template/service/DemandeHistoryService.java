package com.csys.template.service;
import com.csys.template.domain.DemandeHistory;
import com.csys.template.dto.DemandeHistoryDTO;
import com.csys.template.factory.DemandeHistoryFactory;
import com.csys.template.repository.DemandeHistoryRepository;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DemandeHistoryService {
    private final DemandeHistoryRepository demandeHistoryRepository;

    public DemandeHistoryService(DemandeHistoryRepository demandeHistoryRepository) {
        this.demandeHistoryRepository = demandeHistoryRepository;
    }

    @Transactional(readOnly = true)
    public List<DemandeHistoryDTO> findAll(){
        return DemandeHistoryFactory.DemandesHistoryToDemandeHistorysDTO(demandeHistoryRepository.findAll());
    }
    @Transactional(readOnly = true)
    public DemandeHistoryDTO findStockByCode(String code) {

        DemandeHistory demande = demandeHistoryRepository.findByCode(code);
        com.csys.template.util.Preconditions.checkBusinessLogique(demande != null, "error.couldn't-find-demande");
        DemandeHistoryDTO demandeDTO = DemandeHistoryFactory.demandeHistoryToDemandeHistoryDTO(demande);

        return demandeDTO;
    }
    @Transactional
    public DemandeHistoryDTO addDemandeHistory(DemandeHistoryDTO demandeDTO){
        Preconditions.checkArgument (demandeDTO != null, "Demande added!");
        DemandeHistory d = demandeHistoryRepository.save(DemandeHistoryFactory.demandeHistoryDTOToDemandeHistory(demandeDTO));
        return DemandeHistoryFactory.demandeHistoryToDemandeHistoryDTO(d);

    }
}
