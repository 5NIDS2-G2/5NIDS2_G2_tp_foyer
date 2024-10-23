package tn.esprit.tpfoyer.entity;

import jakarta.persistence.*;
import javax.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;


@javax.persistence.Entity
@Getter
@Setter
@AllArgsConstructor
//test git
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservation {

    @Id
    String idReservation;


    Date anneeUniversitaire;
    boolean estValide;


    @ManyToMany
    Set<Etudiant> etudiants;


    /*@ToString.Exclude
    @JsonIgnore*/

}
