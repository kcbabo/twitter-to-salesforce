/*
 * Salesforce Query DTO generated by camel-salesforce-maven-plugin
 * Generated on: Sat Feb 04 11:54:18 EST 2017
 */
package org.apache.camel.salesforce.dto;

import com.thoughtworks.xstream.annotations.XStreamImplicit;
import org.apache.camel.component.salesforce.api.dto.AbstractQueryRecordsBase;

import java.util.List;

/**
 * Salesforce QueryRecords DTO for type Contact
 */
public class QueryRecordsContact extends AbstractQueryRecordsBase {

    @XStreamImplicit
    private List<Contact> records;

    public List<Contact> getRecords() {
        return records;
    }

    public void setRecords(List<Contact> records) {
        this.records = records;
    }
}
