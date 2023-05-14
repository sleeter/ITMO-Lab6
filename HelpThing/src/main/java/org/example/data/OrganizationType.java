package org.example.data;

import java.io.Serializable;

/**
 * Enumeration with organization types.
 */
public enum OrganizationType implements Serializable {
    COMMERCIAL("COMMERCIAL"),
    PUBLIC("PUBLIC"),
    TRUST("TRUST"),
    PRIVATE_LIMITED_COMPANY("PRIVATE_LIMITED_COMPANY");
    private final String title;

    OrganizationType(String title){
        this.title = title;
    }
    public static OrganizationType getOrganizationType(String string){
        if(string.equals(OrganizationType.PUBLIC.title))
            return OrganizationType.PUBLIC;
        else if(string.equals(OrganizationType.COMMERCIAL.title))
            return OrganizationType.COMMERCIAL;
        else if(string.equals(OrganizationType.TRUST.title))
            return OrganizationType.TRUST;
        else if(string.equals(OrganizationType.PRIVATE_LIMITED_COMPANY.title))
            return OrganizationType.PRIVATE_LIMITED_COMPANY;
        else return null;
    }
    /**
     * This function returns a comma separated list of the names of all the values in the enum
     *
     * @return The types of the organization.
     */
    public static String nameList() {
        StringBuffer nameList = new StringBuffer();
        for (OrganizationType category : values()) {
            nameList.append(category.name()).append(", ");
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
