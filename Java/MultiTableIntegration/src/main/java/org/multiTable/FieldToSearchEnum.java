package org.multiTable;

/**
 *
 * Created by A.V.Tsaplin on 12.08.2016.
 */

public enum FieldToSearchEnum {

    LOGIN("login"), NAME("name"), SURNAME("surname");

    private String columnName;

    FieldToSearchEnum(String cn) {
        this.columnName = cn;
    }

    public String getSearchString() {
        return columnName;
    }
}