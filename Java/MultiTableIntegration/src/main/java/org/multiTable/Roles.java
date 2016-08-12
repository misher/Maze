package org.multiTable;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;


/**
 *
 * Created by A.V.Tsaplin on 12.08.2016.
 */

@Entity
@Table(name = "t_roles", catalog = "multitablebase")
public class Roles {

    private int id;
    private String role;

    public Roles() {

    }

    public Roles(int id, String role) {
        this.id = id;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", length = 3, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Column(name = "role", length = 25, nullable = false)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
