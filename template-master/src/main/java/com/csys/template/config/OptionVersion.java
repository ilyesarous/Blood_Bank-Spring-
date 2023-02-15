package com.csys.template.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:OptionVersion.yml")
@ConfigurationProperties

public class OptionVersion {

    private int tailleCodeFournisseur;
    private String  medicationsNamingStrategy;
    private String medicalNamingStrategy;

    public int getTailleCodeFournisseur() {
        return tailleCodeFournisseur;
    }

    public void setTailleCodeFournisseur(int tailleCodeFournisseur) {
        this.tailleCodeFournisseur = tailleCodeFournisseur;
    }

    public String getMedicationsNamingStrategy() {
        return medicationsNamingStrategy;
    }

    public void setMedicationsNamingStrategy(String medicationsNamingStrategy) {
        this.medicationsNamingStrategy = medicationsNamingStrategy;
    }

    public String getMedicalNamingStrategy() {
        return medicalNamingStrategy;
    }

    public void setMedicalNamingStrategy(String medicalNamingStrategy) {
        this.medicalNamingStrategy = medicalNamingStrategy;
    }


}
