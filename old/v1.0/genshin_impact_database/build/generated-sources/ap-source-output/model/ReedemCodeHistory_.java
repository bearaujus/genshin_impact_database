package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Code;
import model.Dbuser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-09T08:21:28")
@StaticMetamodel(ReedemCodeHistory.class)
public class ReedemCodeHistory_ { 

    public static volatile SingularAttribute<ReedemCodeHistory, Dbuser> idUser;
    public static volatile SingularAttribute<ReedemCodeHistory, Code> idCode;
    public static volatile SingularAttribute<ReedemCodeHistory, Integer> id;
    public static volatile SingularAttribute<ReedemCodeHistory, Date> reedemDate;

}