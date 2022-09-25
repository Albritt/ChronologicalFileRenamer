package com.renamer.tests;

import com.renamer.DateCreatedFileCollector;
import com.renamer.FileCollector;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DateCreatedFileCollectorTests {



    @TempDir
    Path testPath;
    @TempDir
    Path tempFile1;
    @TempDir
    Path tempFile2;

    @BeforeEach
    public void setUp(){
           Path file1 = tempFile1.resolve("test1.jpg");
           Path file2 = tempFile2.resolve("test2.mp4");

    }

    @Test
    public void PathShouldNotBeNull(){
        //Path path = Paths.get(String.valueOf(testPath));
        FileCollector fileCollector = new DateCreatedFileCollector(testPath);
        assertNotNull(fileCollector.getDirectory());
        }
    }


