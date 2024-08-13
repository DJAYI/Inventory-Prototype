package logic;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logic.Equipo;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-08-12T22:29:30", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Baja.class)
public class Baja_ { 

    public static volatile SingularAttribute<Baja, Equipo> equipoID;
    public static volatile SingularAttribute<Baja, String> fechaBaja;
    public static volatile SingularAttribute<Baja, String> motivo;
    public static volatile SingularAttribute<Baja, String> observaciones;
    public static volatile SingularAttribute<Baja, Integer> id;

}