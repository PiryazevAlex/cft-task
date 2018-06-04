package ru.cft.task.moneyservice.service;

import org.junit.Test;
import ru.cft.task.moneyservice.dto.RequestInfoDto;

import java.io.IOException;
import java.math.BigDecimal;

public class FileExportServiceImplTest {
private FileExportService fileExportService = new FileExportServiceImpl("C:/temp/test", "UTF-8");
    @Test
    public void exportData() throws IOException {
        RequestInfoDto dto = new RequestInfoDto();

        dto.setSum(new BigDecimal("23.45"));
        dto.setPayerAccount("123456");
        dto.setPayeeAccount("654321");
        dto.setId("121212");
        fileExportService.exportData(dto);
    }
}