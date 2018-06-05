package ru.cft.task.moneyservice.service.file;

import org.junit.Assert;
import org.junit.Test;
import ru.cft.task.moneyservice.dto.RequestInfoDto;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;

public class FileExportServiceImplTest {
    private final static String TARGET_FOLDER = ".";
    private final static String REQUEST_ID = "12345";
    private FileExportService fileExportService = new FileExportServiceImpl(TARGET_FOLDER, "CP1251");

    @Test
    public void exportDataShouldCreateCorrectFile() throws IOException {
        File file = new File(buildFileName(REQUEST_ID));
        if (file.exists()) {
            file.delete();
        }
        RequestInfoDto dto = buildRequestInfoDto(
                REQUEST_ID,
                "Счет Иванова",
                "Счет Петрова",
                new BigDecimal("100.00"));

        fileExportService.exportData(dto);

        String content = String.join("\n", Files.readAllLines(Paths.get(buildFileName(REQUEST_ID)), Charset.forName("CP1251")));
        Assert.assertThat(content, is("{\n" +
                "  \"id\" : \"12345\",\n" +
                "  \"payerAccount\" : \"Счет Петрова\",\n" +
                "  \"payeeAccount\" : \"Счет Иванова\",\n" +
                "  \"sum\" : 100.00\n" +
                "}"));
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
