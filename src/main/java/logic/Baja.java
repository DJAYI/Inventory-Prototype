/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author danil
 */
@Entity
@Table(name = "baja")
@NamedQueries({
        @NamedQuery(name = "Baja.findAll", query = "SELECT b FROM Baja b"),
        @NamedQuery(name = "Baja.findById", query = "SELECT b FROM Baja b WHERE b.id = :id") })
public class Baja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Lob
    @Column(name = "FechaBaja")
    private String fechaBaja;
    @Lob
    @Column(name = "Motivo")
    private String motivo;
    @Lob
    @Column(name = "Observaciones")
    private String observaciones;
    @JoinColumn(name = "EquipoID", referencedColumnName = "ID")
    @OneToOne
    private Equipo equipoID;

    public Baja() {
    }

    public Baja(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Equipo getEquipoID() {
        return equipoID;
    }

    public void setEquipoID(Equipo equipoID) {
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
        if (!(object instanceof Baja)) {
            return false;
        }
        Baja other = (Baja) object;
        if (this.id != null || other.id == null && (this.id == null || this.id.equals(other.id))) {
        } else {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logic.Baja[ id=" + id + " ]";
    }

}
