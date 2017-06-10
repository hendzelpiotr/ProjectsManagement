package com.project.java.prz.common.core.converter;

import com.project.java.prz.common.core.domain.general.SettingName;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

/**
 * Created by Piotr on 09.06.2017.
 */
@Converter
public class SettingNameConverter implements AttributeConverter<SettingName, String> {

    @Override
    public String convertToDatabaseColumn(SettingName attribute) {
        return attribute.getSettingName();
    }

    @Override
    public SettingName convertToEntityAttribute(String dbData) {
        SettingName[] values = SettingName.values();
        return Arrays.stream(values)
                .filter(settingName -> settingName.getSettingName().equals(dbData))
                .findFirst()
                .get();
    }

}
