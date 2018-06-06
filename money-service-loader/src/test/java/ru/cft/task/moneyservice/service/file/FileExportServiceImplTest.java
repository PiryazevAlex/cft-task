package ru.cft.task.moneyservice.service.file;

import org.junit.Assert;
import org.junit.Test;
import ru.cft.task.moneyservice.dto.RequestInfoDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;

public class FileExportServiceImplTest {
    private static final String TARGET_FOLDER = ".";
    private static final String REQUEST_ID = "12345";
    private static final String CHARSET_NAME = "CP1251";
    private FileExportService fileExportService = new FileExportServiceImpl(TARGET_FOLDER, CHARSET_NAME);

    @Test
    public void exportDataShouldCreateCorrectFile() throws IOException {
        deleteFile(REQUEST_ID);
        RequestInfoDto dto = buildRequestInfoDto(
                REQUEST_ID,
                "Счет Иванова",
                "Счет Петрова",
                new BigDecimal("100.00"));

        fileExportService.exportData(dto);

        String content = String.join("\n", Files.readAllLines(Paths.get(buildFileName(REQUEST_ID)),
                Charset.forName(CHARSET_NAME)));
        Assert.assertThat(content, is("{\n" +
                "  \"id\" : \"12345\",\n" +
                "  \"payerAccount\" : \"Счет Петрова\",\n" +
                "  \"payeeAccount\" : \"Счет Иванова\",\n" +
                "  \"sum\" : 100.00\n" +
                "}"));
    }

    private void deleteFile(String requestId) throws IOException {
        try {
            Files.delete(Paths.get(buildFileName(requestId)));
        } catch (NoSuchFileException e) {

        }
    }

    private static String buildFileName(String requestId) {
        return TARGET_FOLDER + "/" + requestId + "_request.json";
    }

    private static RequestInfoDto buildRequestInfoDto(String requestId,
                                                      String payeeAccount,
                                                      String payerAccount,
                                                      BigDecimal sum) {
        RequestInfoDto dto = new RequestInfoDto();
        dto.setId(requestId);
        dto.setPayeeAccount(payeeAccount);
        dto.setPayerAccount(payerAccount);
        dto.setSum(sum);
        return dto;
    }
}
