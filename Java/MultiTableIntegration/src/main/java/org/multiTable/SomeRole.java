package org.multiTable;

import java.util.List;

/**
 *
 * Created by A.V.Tsaplin on 10.08.2016.
 */

public class SomeRole implements IActs {

    private String roleName;
    private List<String> acts;

    public List<String> getActs() {
        return acts;
    }

    public void setActs(List<String> acts) {
        this.acts = acts;
    }

    public SomeRole(String roleName, List<String> acts) {
        this.roleName = roleName;
        this.acts = acts;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Object check(List<String> someActs) {
        boolean state = false;
        for (int i = 0; i < someActs.size(); i++) {
            if (acts.contains(someActs.get(i))) {
                state = true;
            } else {
                state = false;
            }
        }
        return state;
    }
}













//@Entity
//@Table(name = "roles", catalog = "multitablebase")



//
//    @Id
//    @GeneratedValue(strategy = IDENTITY)
//    @Column(name = "role", length = 11, nullable = false)
//    public String getRoleName() {
//        return roleName;
//    }
//
//    public void setRoleName(String roleName) {
//        this.roleName = roleName;
//    }
//
//
//    @Column(name = "actionsjson", length = 512, nullable = false)
//    public String getActsJSon() {
//        return actsJSon;
//    }
//
//    public void setActsJSon(String actsJSon) {
//        this.actsJSon = actsJSon;
//    }
//
//
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "users",
//            joinColumns = @JoinColumn(name = "role"),
//            inverseJoinColumns = @JoinColumn(name = "rolename")
//    )
//    public Set<SomeUser> getSomeUsers() {
//        return this.someUsers;
//    }
//
//    public void setEmployees(Set<SomeUser> someUsers) {
//        this.someUsers = someUsers;
//    }
//
