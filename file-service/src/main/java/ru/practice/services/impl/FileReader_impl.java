package ru.practice.services.impl;


import generated.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import ru.practice.exception.IllegalXMLException;
import ru.practice.services.FileReader;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

@Service
@Slf4j
public class FileReader_impl implements FileReader {

   private static final String XSD_PATH = "app/src/main/resources/xsd/client_data_schema.xsd";


   @Override
   public Data downloadFile(File file) throws IllegalXMLException {
      File xsdFile = new File(XSD_PATH);

      try {
         JAXBContext context = JAXBContext.newInstance(Data.class);
         Unmarshaller unmarshaller = context.createUnmarshaller();

         // Добавляем схему для валидации
         SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
         Schema schema = sf.newSchema(new File(XSD_PATH));
         unmarshaller.setSchema(schema);

         // Чтение и валидация
         Data data = (Data) unmarshaller.unmarshal(file);
         System.out.println("XML успешно прочитан и валиден!");

         data.getAccounts().getAccount().forEach(account -> log.info(Integer.toString(account.getAccountId())));
         return data;

      } catch (JAXBException | SAXException e) {
         log.error("Ошибка при обработке XML: " + e.getMessage(), e);
         throw new IllegalXMLException("Ошибка при обработке XML: " + e.getMessage()) ;
      }

   }
}
