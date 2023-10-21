package org.example.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<Rating, String> {
    @Override
    public String convertToDatabaseColumn(Rating attribute) {
        return attribute.getValue();
    }

    @Override
    public Rating convertToEntityAttribute(String dbData) {
        Rating[] list = Rating.values();
        for (Rating rating : list) {
            if(rating.getValue().equals(dbData)) {
                return rating;
            }
        }
        return null;
    }
}
