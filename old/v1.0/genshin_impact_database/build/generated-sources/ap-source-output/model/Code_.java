package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.ReedemCodeHistory;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-09T08:21:28")
@StaticMetamodel(Code.class)
public class Code_ { 

    public static volatile CollectionAttribute<Code, ReedemCodeHistory> reedemCodeHistoryCollection;
    public static volatile SingularAttribute<Code, String> code;
    public static volatile SingularAttribute<Code, Integer> quota;
    public static volatile SingularAttribute<Code, Date> expireDate;
    public static volatile SingularAttribute<Code, Integer> id;
    public static volatile SingularAttribute<Code, Integer> version;
    public static volatile SingularAttribute<Code, Date> postedDate;

}