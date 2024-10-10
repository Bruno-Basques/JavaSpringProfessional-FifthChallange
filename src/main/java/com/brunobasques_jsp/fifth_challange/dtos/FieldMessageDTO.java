package com.brunobasques_jsp.fifth_challange.dtos;

public class FieldMessageDTO {

    private String fieldName;
    private String message;

	public FieldMessageDTO() {	
	}
	
    public FieldMessageDTO(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}
