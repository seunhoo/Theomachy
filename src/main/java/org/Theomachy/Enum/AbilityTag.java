package org.Theomachy.Enum;

public enum AbilityTag {
    BONEATTACK("boneAttack");
    private final String tag;
    AbilityTag(String tag) {
        this.tag = tag;
    }
    public  String getTag(){
        return tag;
    }
}
