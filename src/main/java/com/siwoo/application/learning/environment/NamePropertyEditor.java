package com.siwoo.application.learning.environment;

import com.siwoo.application.learning.common.FullName;

import java.beans.PropertyEditorSupport;

public class NamePropertyEditor extends PropertyEditorSupport{

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] name = text.split("\\s");
        setValue(new FullName(name[1],name[0]));
    }
}
