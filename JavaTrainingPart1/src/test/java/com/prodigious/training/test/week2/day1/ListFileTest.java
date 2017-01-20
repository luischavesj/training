package com.prodigious.training.test.week2.day1;

import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Luis Chaves on 1/20/2017.
 * To test certain File capabilities
 */
public class ListFileTest {

    private final static String FILE_PATH = "./Temp.txt";
    private final static String CONTENT = "This is just a little example for testing";
    private final static String DATE_FORMAT = "dd-MM-yyyy";

    @Test
    public void listAllFilesInCDrive(){
        File folder = new File("C:\\");
        File[] files = folder.listFiles();
        StringBuilder builder = new StringBuilder();
        builder.append("List of files with modification date and permissions(r = read. w = write. x = execution).\n");
        for(File currentFile:files) {
            if (currentFile.isFile() && !currentFile.isHidden()) {
                builder.append("File Name = \"");
                builder.append(currentFile.getName());
                builder.append("\" Modified Date = ");
                builder.append(new Date(currentFile.lastModified()));
                builder.append(" Permissions = ");
                builder.append((currentFile.canRead() ? "r" : "-"));
                builder.append((currentFile.canRead() ? "w" : "-"));
                builder.append((currentFile.canRead() ? "x" : "-"));
                builder.append("\n");
            }
        }
        System.out.println(builder);
    }

    @Test
    public void countNumberOfCharacterInAFile(){
        File file = new File(ListFileTest.FILE_PATH);
        OutputStream  stream = null;
        try {
            stream = new FileOutputStream(file);

            stream.write(ListFileTest.CONTENT.getBytes());

            InputStream inputStream = new FileInputStream(ListFileTest.FILE_PATH);

            System.out.println(inputStream.available());
            assert (ListFileTest.CONTENT.length() == inputStream.available());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void writeCurrentTimeInFile(){
        File file = new File(ListFileTest.FILE_PATH);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(ListFileTest.DATE_FORMAT);
        try(OutputStream outputStream = new FileOutputStream(file)){
            outputStream.write(dateFormat.format(date).getBytes());

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        //To test the right value was saved
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(ListFileTest.FILE_PATH));
            assert (new String(encoded, Charset.defaultCharset()).equals(dateFormat.format(date)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void keywordSearchTest(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String line;
        String searchValue;

        try (Reader fileReader = new FileReader(ListFileTest.FILE_PATH);
             BufferedReader bufferedReader = new BufferedReader(fileReader);){
            line = bufferedReader.readLine();
            searchValue = String.valueOf(calendar.get(Calendar.YEAR));

            assert (line.contains(searchValue));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
