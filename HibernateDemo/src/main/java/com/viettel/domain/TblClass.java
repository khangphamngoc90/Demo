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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author khangpn
 */
@Entity(name="class")
public class TblClass implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="Class_ID", nullable=false, unique=true)
    private Long id;
    @ManyToMany(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinTable(name="class_student", 
            joinColumns={@JoinColumn(name="Class_ID", nullable=false, updatable=false)},
            inverseJoinColumns={@JoinColumn(name="Student_ID", nullable=false, updatable=false)})
    private Set<Student> students;

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}
