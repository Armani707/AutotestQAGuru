package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.As;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.opencsv.CSVReader;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.model.StudentJSON;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.*;

public class SelenideFilesTest {

    private ClassLoader classLoader = SelenideFilesTest.class.getClassLoader();
    private static final Gson gson = new Gson();

   //Все проверяемые исключения - наследники класса Exception - Их нужно обрабатывать, а не писать в голом виде
   //Все непроверяемые исключения - наследники классов RuntimeException и Error

    // Использовать try/catch в тестах НЕЛЬЗЯ и НЕ рекомендуется!
    // А нужно просто наследовать ошибку, типо прописать throws и ошибку метода ту же приписываешь к тесту.
    @Test
    void DownloadFileTest () throws IOException {
     open("https://github.com/junit-team/junit5/blob/main/README.md");
     //File это путь файла, куда сохранили файл    //ОБЯЗАТЕЛЬНОЕ ТРЕБОВАНИЕ ДЛЯ СКАЧИВАНИЯ ФАЙЛА!!! ДОЛЖЕН БЫТЬ href
     File downloaded = $(".react-blob-header-edit-and-raw-actions [href*='main/README.md']").download();

     //Существует 2 способа прочитать файл побайтово.
     //1ый способ ниже - это все, что происходит под капотом, все действия
        try (InputStream is = new FileInputStream(downloaded)) {
            byte [] data = is.readAllBytes();
            String dataAsString = new String(data, StandardCharsets.UTF_8);
            Assertions.assertTrue(dataAsString.contains("Contributions"));
        }

        //2ой способ ниже - краткая идентичная версия 1ого способа
        String dataAsString = FileUtils.readFileToString(downloaded, StandardCharsets.UTF_8);
        Assertions.assertTrue(dataAsString.contains("Contributions"));



    }

    //Что делать если нет href в кнопке загрузки? (из строчки 23) НЕ РЕКОМЕНДУЕТСЯ, ТОЛЬКО ЕСЛИ НЕТ ВАРИАНТОВ
   /* static {
        Configuration.fileDownload = FileDownloadMode.PROXY;
    }*/


    //Загружаем файл обычный куда-то. Селектор - для загрузки всегда этот - единый. clothes.png - фото здесь в папке resources
    @Test
    void fileUploadTest () {
        open("https://ru.imgbb.com/");
        $("input[type='file']").uploadFromClasspath("clothes.png");
        sleep(10000);
    }


    //Считываем PDF файл - если хотим просмотреть внутреннее файла - Пишем System.out.println(); и на этой строке ставим точку и ДЕБАЖИМ. Дальше отобразится pdf в Debug терминале - нажать Evaluate Expression - далее ввести pdf. Покажет то, что написано внутри.
    @Test
    void pdfFileParsingTest () throws IOException {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File downloaded = $("[href*='junit-user-guide-6.0.1.pdf']").download();
        PDF pdf = new PDF(downloaded);
        Assertions.assertEquals("Stefan Bechtold, Sam Brannen, Johannes Link, Matthias Merdes, Marc Philipp, Juliette de Rancourt, Christian Stein", pdf.author);
        //System.out.println();
    }


    //Считываем эксель файл
    //sheet - таблица (номер странички), row - колонка, cell - строчка

    @Test
    void xlsFileParsingTest() throws FileNotFoundException {
        open("https://excelvba.ru/programmes/Teachers?ysclid=lfcu77j9j9951587711");
        File downloaded = $("[href='https://ExcelVBA.ru/sites/default/files/teachers.xls']").download();
        XLS xls = new XLS(downloaded);
        String actualValue = xls.excel.getSheetAt(0).getRow(3).getCell(2).getStringCellValue();
        Assertions.assertEquals("1. Суммарное количество часов планируемое на штатную по всем разделам плана  должно \n" +
                "составлять примерно 1500 час в год.  ", actualValue);

    }


    //Прочитать и протестировать файл csv
    @Test
    void csvFileParsingTest() throws Exception{
        try (InputStream is = classLoader.getResourceAsStream("test_data/textTests.csv");
             CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {
            List <String[]> data = csvReader.readAll();
            Assertions.assertEquals(2, data.size());
            Assertions.assertArrayEquals(
                    new String[] {"Arman","9armo9@mail.ru"},
                    data.get(0)
            );
            Assertions.assertArrayEquals(
                    new String[] {"Gukas","lav-63@mail.ru"},
                    data.get(1)
            );
        }
    }


    //Прочить файл zip и проверить через ассерт
    @Test
    void zipFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                classLoader.getResourceAsStream("archive.zip")
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null ) {
                Assertions.assertEquals(4682, entry.getSize());
            }
        }
    }


    //Есть 2 вида теста. Простой и продвинутый с Model. Я использую Model.
    @Test
    void jsonFileParsingTest() throws Exception {
       try (Reader reader = new InputStreamReader(
               classLoader.getResourceAsStream("myJson.json")
       )) {
           StudentJSON actual = gson.fromJson(reader, StudentJSON.class);

           Assertions.assertEquals("John Doe", actual.getName());

           Assertions.assertEquals("mma", actual.getCourses().getSport());
       }
    }
}