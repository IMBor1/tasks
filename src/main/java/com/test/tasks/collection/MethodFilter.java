package com.test.tasks.collection;

public class MethodFilter {
    public Object filter(Object[] objects, FilterCollection filterCollection) {
        Object[] newObject = new Object[objects.length];
        for (int i = 0; i < objects.length; i++) {
            newObject[i] = filterCollection.apply(objects[i]);
        }
        return newObject;
    }
}
