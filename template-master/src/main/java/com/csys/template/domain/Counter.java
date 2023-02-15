package com.csys.template.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "counter")
public class Counter {

    @Column(name = "type", nullable = false)
    @Id
    private String type;
    @Column(name = "prefix", nullable = false)
    private String prefix;
    @Column(name = "suffix", nullable = false)
    private Long suffix;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Long getSuffix() {
        return suffix;
    }

    public void setSuffix(Long suffix) {
        this.suffix = suffix;
    }
}
