package com.epam.javacore2019.steveclient.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompositeView extends BaseView {
    private List<BaseView> children = new ArrayList<>();

    public void addChild(BaseView child) {
        children.add(child);
    }

    public void addChildren(BaseView... children) {
        Collections.addAll(this.children, children);
    }
}
