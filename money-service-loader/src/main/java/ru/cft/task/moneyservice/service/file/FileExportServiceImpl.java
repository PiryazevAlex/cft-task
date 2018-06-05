package ru.cft.task.moneyservice.service.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.cft.task.moneyservice.dto.RequestInfoDto;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.MessageFormat;

@Service
public class FileExportServiceImpl implements FileExportService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileExportServiceImpl.class);
    private static final String FILENAME_TEMPLATE = "{0}/{1}_request.json";
    private static final ObjectWriter OBJECT_WRITER = new ObjectMapper().writerWithDefaultPrettyPrinter();

    private final String targetFolder;
    private final Charset encoding;

    public FileExportServiceImpl(@Value("${json.folder}") String targetFolder,
                                 @Value("${json.charset}") String encoding) {
        this.targetFolder = targetFolder;
        this.encoding = Charset.forName(encoding);
    }

    @Override
    public void exportData(RequestInfoDto dto) throws IOException {
        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(buildFileName(dto.getId())),
                encoding)) {
            OBJECT_WRITER.writeValue(writer, dto);
        } catch (Exception e) {
            LOGGER.error("File creating error", e);
        }

    }

    private String buildFileName(String requestId) {
        return MessageFormat.format(FILENAME_TEMPLATE, targetFolder, requestId);
    }
}
