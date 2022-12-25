package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class HwPdfParse {


    ClassLoader cl = FilesParsingTest.class.getClassLoader();

    @DisplayName("ZIP EXTRACT")
    @Test
    void zipParseTest() throws Exception {
        try (
                InputStream resource = cl.getResourceAsStream("task.zip");
                ZipInputStream zis = new ZipInputStream(resource)
        ) {
            ZipEntry entry;
            while((entry = zis.getNextEntry()) != null) {

                if (entry.getName().contains(".pdf")) {
                    PDF content = new PDF(zis);
                    assertThat(content.text).contains("Valdo");
                    System.out.println("PDF PARSING FINISHED SUCCESSFULLY");
                }
                if (entry.getName().contains(".csv")) {
                    CSVReader reader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = reader.readAll();
                    assertThat(content.get(0)[1]).contains("lesson");
                    System.out.println("CSV PARSING FINISHED SUCCESSFULLY");
                }

                if (entry.getName().contains(".xlsx")) {
                    XLS content = new XLS(zis);
                    assertThat(content.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue()).contains("company_id");
                    System.out.println("XLSX PARSING FINISHED SUCCESSFULLY");
                }
            }
        }
    }
}

