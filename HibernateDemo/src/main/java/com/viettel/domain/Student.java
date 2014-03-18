/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author khangpn
 */
@Entity(name="student")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="Student_ID")
    private Long id;
    @Column(name="Name", nullable=false)
    private String name;
    @ManyToMany(fetch= FetchType.LAZY, mappedBy="students")
    private Set<TblClass> classes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<TblClass> getClasses() {
        return classes;
    }

    public void setClasses(Set<TblClass> classes) {
        this.classes = classes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
