package org.multiTable;

/**
 *
 * Created by A.V.Tsaplin on 12.08.2016.
 */

public enum FieldToSearchEnum {

    LOGIN, NAME, SURNAME;

    public String getSearchString(FieldToSearchEnum fieldToSearchEnum) {
        if (fieldToSearchEnum == FieldToSearchEnum.NAME) {
            return "name";
        }
        if (fieldToSearchEnum == FieldToSearchEnum.SURNAME) {
            return "surname";
        }
        if (fieldToSearchEnum == FieldToSearchEnum.LOGIN) {
            return "login";
        }
        return null;
    }
}