/*
 * Salesforce DTO generated by camel-salesforce-maven-plugin
 * Generated on: Sat Feb 04 11:54:18 EST 2017
 */
package org.apache.camel.salesforce.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Salesforce Enumeration DTO for picklist Salutation
 */
public enum Contact_SalutationEnum {

    // Dr.
    DR_("Dr."),
    // Mr.
    MR_("Mr."),
    // Mrs.
    MRS_("Mrs."),
    // Ms.
    MS_("Ms."),
    // Prof.
    PROF_("Prof.");

    final String value;

    private Contact_SalutationEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static Contact_SalutationEnum fromValue(String value) {
        for (Contact_SalutationEnum e : Contact_SalutationEnum.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException(value);
    }

}
