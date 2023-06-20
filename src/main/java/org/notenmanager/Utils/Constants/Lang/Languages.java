package org.notenmanager.Utils.Constants.Lang;

import org.notenmanager.UI.Compenents.LabledField.LabeledComboBoxField;

import java.awt.event.ActionListener;
import java.lang.reflect.Field;

public class Languages {
    public static final String GERMAN = "Deutsch";
    public static final String ENGLISH = "English";

    public static LanguageConstants PickLanguage(String language) {
        if (language == null) language = ENGLISH;
        switch (language) {
            case Languages.GERMAN:
                return new GermanConstants();
            default:
                return new LanguageConstants();
        }
    }

    public static void setupLanguagePicker(LabeledComboBoxField languagePicker, ActionListener consumer) {
        languagePicker.comboBox.addActionListener(consumer);
        Field[] fields = Languages.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                languagePicker.comboBox.addItem(field.get(null).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
