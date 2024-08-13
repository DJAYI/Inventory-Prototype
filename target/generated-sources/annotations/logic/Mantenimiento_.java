package logic;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logic.Equipo;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-08-12T22:29:30", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Mantenimiento.class)
public class Mantenimiento_ { 

    public static volatile SingularAttribute<Mantenimiento, String> descripcion;
    public static volatile SingularAttribute<Mantenimiento, Equipo> equipoID;
    public static volatile SingularAttribute<Mantenimiento, String> fechaInicio;
    public static volatile SingularAttribute<Mantenimiento, String> observaciones;
    public static volatile SingularAttribute<Mantenimiento, Integer> id;
    public static volatile SingularAttribute<Mantenimiento, String> accionTomada;
    public static volatile SingularAttribute<Mantenimiento, String> fechaFin;

}