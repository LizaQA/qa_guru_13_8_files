package guru.qa;


import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Selenide;
import com.codeborne.xlstest.XLS;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import guru.qa.domain.Example;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static javax.print.DocFlavor.URL.PDF;
import static org.assertj.core.api.Assertions.assertThat;

public class FileParseTest {

    ClassLoader classLoader = FileParseTest.class.getClassLoader();

    @Disabled
    @Test
    void pdfTest() throws Exception {
        Selenide.open("https://junit.org/junit5/docs/current/user-guide/");
        File file = $("a[href*='junit-user-guide-5.9.0.pdf']").download();
        com.codeborne.pdftest.PDF pdf = new PDF(file);
        assertThat(pdf.author).isEqualTo("Stefan Bechtold, Sam Brannen, Johannes Link, Matthias Merdes, Marc Philipp, Juliette de Rancourt, Christian Stein");
    }

    @Disabled
    @Test
    void xlsTest() throws Exception {
        Selenide.open("http://romashka2008.ru/price");
        File file = $(".site-content__right a[href*='/f/prajs_ot_1008.xls']").download();
        XLS xls = new XLS(file);
        assertThat(
                xls.excel.getSheetAt(0)
                        .getRow(22)
                        .getCell(2)
                        .getStringCellValue()
        ).contains("Бумага для цветной печати");
    }
    @Disabled
    @Test
    void csvTest() throws Exception {

        InputStream is = classLoader.getResourceAsStream("example.csv");
        CSVReader csvReader = new CSVReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        List<String[]> csv = csvReader.readAll();
        assertThat(csv).contains(
                new String[]{"teacher", "lesson", "date"},
                new String[]{"Tuchs", "junit", "03.06"},
                new String[]{"Eroshenko", "allure", "07.06"}
        );
    }
    @Disabled
    @Test
    void zipTest() throws Exception {
        InputStream is = classLoader.getResourceAsStream("example.zip");
        ZipInputStream zis = new ZipInputStream(is);
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            assertThat(entry.getName()).isEqualTo("sample.txt");
        }
    }

    @Test
   public void jsonTestWithJackson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Example example = objectMapper.readValue(classLoader.getResource("example.json"),Example.class);
      //  System.out.println(example.getProduct());
       String name = example.getPerson().getEmergencyContacts().get(1).getName();
       String email = example.getPerson().getEmail().get(0);
       Boolean demo = example.getDemo();

       String home = example.getPerson().getPhones().getHome();
        System.out.println(home);
        System.out.println(demo);
        System.out.println(name);
        System.out.println(email);

        Assertions.assertEquals("Justin Doe", name, "Имя не соответсвует ожидаемому");
        Assertions.assertEquals(true, demo);
    }

}
