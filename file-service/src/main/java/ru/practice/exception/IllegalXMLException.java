package ru.practice.exception;

public class IllegalXMLException extends Exception{

    public static final String MESSAGE = "Ошибка при разборе XML файла";

    public IllegalXMLException(String message){
        super((MESSAGE + message ));
    }

}
