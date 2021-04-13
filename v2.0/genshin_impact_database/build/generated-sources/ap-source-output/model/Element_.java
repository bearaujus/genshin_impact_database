package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.GenshinCharacter;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-13T07:15:43")
@StaticMetamodel(Element.class)
public class Element_ { 

    public static volatile SingularAttribute<Element, String> name;
    public static volatile SingularAttribute<Element, String> description;
    public static volatile SingularAttribute<Element, Integer> id;
    public static volatile CollectionAttribute<Element, GenshinCharacter> genshinCharacterCollection;
    public static volatile SingularAttribute<Element, String> picture;

}