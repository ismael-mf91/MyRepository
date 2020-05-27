package com.example.myrestapplication.data.model;

import java.util.HashMap;
import java.util.Map;

public class Identity {

    private Map viewableIdentityAttributes = new HashMap();

    public Identity(){

    }

    public Identity(Map viewableIdentityAttributes){
        this.viewableIdentityAttributes = viewableIdentityAttributes;
    }


    public void setViewableIdentityAttributes(Map viewableIdentityAttributes) {
        this.viewableIdentityAttributes = viewableIdentityAttributes;
    }

    public Map getViewableIdentityAttributes() {
        return viewableIdentityAttributes;
    }
}
