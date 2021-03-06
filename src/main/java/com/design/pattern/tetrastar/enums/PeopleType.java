package com.design.pattern.tetrastar.enums;
/**
 * 
 * @author Akshata, Rachna and Shweta.
 *   Enum to represent characters mandatory according to game rules(2 heroes, 2 rovers and one vader)
 */
public enum PeopleType {

    HERO1("hero1"),
    HERO2("hero2"),
    ROVER1("rover1"),
    ROVER2("rover2"),
    VADER("vader");

    private String type;

    private PeopleType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static PeopleType getPeopleTypeFromTypeString(String type) {
        if(type != null) {
            for(PeopleType p : values()) {
                if(p.getType().equalsIgnoreCase(type)) {
                    return p;
                }
            }
        }
        return null;
    }
}
