package ru.practice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practice.services.FileMonitor;
import ru.practice.services.FileReader;

import javax.annotation.PostConstruct;
import java.io.File;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileMonitor_Impl implements FileMonitor {
    private static final String XML_PATH = "C:/java/Practice_Web_App/app/src/main/resources/";

    private final FileReader fileReader;

    @PostConstruct
    public void start() throws Exception {
        this.monitorFile();
    }

    @Override
    public void monitorFile() throws Exception {
        log.info("Монитор файл запущен");

        FileAlterationObserver observer = new FileAlterationObserver(XML_PATH);
        FileAlterationMonitor monitor = new FileAlterationMonitor(5000);
        FileAlterationListener listener = new FileAlterationListenerAdaptor() {
            @Override
            public void onFileCreate(File file) {
                log.info("Началось чтение файла: " + file.getName());
                fileReader.downloadFile(file);
            }

            @Override
            public void onFileChange(File file) {
                log.info("Началось чтение файла: " + file.getName());
                fileReader.downloadFile(file);
            }
        };
        observer.addListener(listener);
        monitor.addObserver(observer);
        monitor.start();
    }


}
