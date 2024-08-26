/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author danil
 */
@Entity
@Table(name = "laboratorio")
@NamedQueries({
        @NamedQuery(name = "Laboratorio.findAll", query = "SELECT l FROM Laboratorio l"),
        @NamedQuery(name = "Laboratorio.findById", query = "SELECT l FROM Laboratorio l WHERE l.id = :id") })
public class Laboratorio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(name = "C\u00f3digo")
    private String código;
    @Basic(optional = false)
    @Lob
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(mappedBy = "laboratorioID")
    private ArrayList<Equipo> equipoID;

    public Laboratorio() {
    }

    public Laboratorio(Integer id) {
        this.id = id;
    }

    public Laboratorio(Integer id, String código, String nombre) {
        this.id = id;
        this.código = código;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCódigo() {
        return código;
    }

    public void setCódigo(String código) {
        this.código = código;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Equipo> getEquipoID() {
        return equipoID;
    }

    public void setEquipoID(ArrayList<Equipo> equipoID) {
        this.equipoID = equipoID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Laboratorio)) {
            return false;
        }
        Laboratorio other = (Laboratorio) object;
        if (this.id != null || other.id == null && (this.id == null || this.id.equals(other.id))) {
        } else {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Laboratorio{" + "id=" + id + ", c\u00f3digo=" + código + ", nombre=" + nombre + '}';
    }

}
