package org.multiTable;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 * Created by A.V.Tsaplin on 12.08.2016.
 */

@Entity
@Table(name = "t_actions", catalog = "multitablebase")
public class Actions {

    private int id;
    private String action;

    public Actions() {
    }

    public Actions(int id, String action) {
        this.id = id;
        this.action = action;
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

    @Column(name = "action", length = 25, nullable = false)
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
