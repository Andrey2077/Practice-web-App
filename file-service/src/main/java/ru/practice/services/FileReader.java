package ru.practice.services;

import generated.Data;
import ru.practice.exception.IllegalXMLException;

import java.io.File;

public interface FileReader {

    Data downloadFile(File file) throws IllegalXMLException;

}
