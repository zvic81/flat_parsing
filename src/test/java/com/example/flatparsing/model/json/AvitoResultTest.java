package com.example.flatparsing.model.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
public class AvitoResultTest {
    @Test
    public void parseAvitoResult() {
        try {
            AvitoResult avitoResult = new ObjectMapper().readValue(getJsonFromFile("payload.json"), AvitoResult.class);
            assertNotNull(avitoResult.items);
            assertNotNull(avitoResult.items[0]);
            assertNotNull(avitoResult.items[0].geo);
            assertNotNull(avitoResult.items[0].geo.formattedAddress);
            assertEquals("Мурманская область, Апатиты, улица Ферсмана, 54, подъезд 1", avitoResult.items[0].geo.formattedAddress);
        } catch (Exception e) {
//            throw new RuntimeException(e);
            fail();

        }

    }
    private String getJsonFromFile(String pFileName) throws Exception {
        final URL url = getClass().getClassLoader().getResource(pFileName);
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(url.getPath());
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        scanner.close();
        return stringBuilder.toString();

    }
}
//