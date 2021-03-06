package com.hust.edu.scope;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * locate com.hust.edu.scope
 * Created by MasterTj on 2019/1/11.
 */
public class BetterAttributeStore {
    private final Map<String,String> attributes =new HashMap<>();

    public boolean userLocationMatches(String name,String regexp){
        String key="users."+name+".location";
        String location=null;
        synchronized (attributes) {
            location = attributes.get(key);
        }
        if(location==null){
            return false;
        }else
            return Pattern.matches(regexp,location);
    }
}
