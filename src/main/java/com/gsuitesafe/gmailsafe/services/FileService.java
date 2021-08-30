package com.gsuitesafe.gmailsafe.services;

import com.gsuitesafe.gmailsafe.services.gmail.models.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
@Service
public class FileService {

    public byte[] createZipFile(final List<Message> messages) {
        final ByteArrayOutputStream bo = new ByteArrayOutputStream();
        final ZipOutputStream zipOut = new ZipOutputStream(bo);

        messages.forEach(message -> {
            final String fileName = String.format("email-%s.txt", message.getId());
            try {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.write(message.getRaw().getBytes());
                zipOut.closeEntry();
            } catch (final IOException ioEx) {
                log.error("Error creating text file {}.", fileName, ioEx);
            }
        });

        try {
            zipOut.close();
        } catch (final IOException ioEx) {
            log.error("Error creating zip file.", ioEx);
        }
        return bo.toByteArray();
    }
}
