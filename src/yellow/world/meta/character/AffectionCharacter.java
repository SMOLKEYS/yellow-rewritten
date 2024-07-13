package yellow.world.meta.character;

import arc.struct.*;
import java.util.*;
import yellow.world.meta.*;

/** A character with special data related to affection. */
public class AffectionCharacter extends GameCharacter{
    /** Various relationship ranks the player can achieve with this character. */
    public RelationshipRank[] relationshipRanks;

    public AffectionCharacter(String name){
        super(name);
    }

    @SuppressWarnings("ClassCanBeRecord") //shut the fuck up java
    public static class RelationshipRank{
        public final String name;
        public final int requiredAffinity;

        public static Seq<RelationshipRank> registeredRanks = new Seq<>();

        public RelationshipRank(String name, int requiredAffinity){
            this.name = name;
            this.requiredAffinity = requiredAffinity;

            if(registeredRanks.find(e -> Objects.equals(e.name, name)) != null){
                throw new RuntimeException("Cannot have two relationship ranks of the same name!");
            }else{
                registeredRanks.add(this);
            }
        }

        public static RelationshipRank getRank(String name){
            return registeredRanks.find(e -> Objects.equals(e.name, name));
        }
    }
}
