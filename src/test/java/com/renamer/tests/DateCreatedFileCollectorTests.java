package com.renamer.tests;

import com.renamer.DateCreatedFileCollector;
import com.renamer.FileCollector;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateCreatedFileCollectorTests {
    @TempDir
    Path tempDir;

    @BeforeEach
    public void setUp() throws IOException{
        try {
            Path tempFile = Files.createFile(tempDir.resolve("tempFile.txt"));
            Path tempFile2 = Files.createFile(tempDir.resolve("tempFile2.jpg"));
            Path tempFile3 = Files.createFile(tempDir.resolve("tempFile3.mp4"));
        } catch (InvalidPathException ipe) {
            System.err.println(
                    "error creating temporary test file in " +
                            this.getClass().getSimpleName());

        }

    }

    @Test
    public void PathShouldBeADirectoryAndNotBeNull(){
        FileCollector fileCollector = new DateCreatedFileCollector(tempDir);
        assertNotNull(fileCollector.getDirectory());
        assertTrue(fileCollector.getDirectory().toFile().isDirectory());
    }

    @Test
    public void collectFromPathShouldNotCollectEmptyDirectories(@TempDir Path emptyDir) throws IOException {
        Path emptyDirectory = emptyDir.resolve("emptyDir");
        FileCollector fileCollector = new DateCreatedFileCollector(emptyDirectory);
        assertThrows(RuntimeException.class, ()->fileCollector.collectFromPath(), "Directory is empty, no renaming can be done.");

    }

    @Test
    public void collectFromPathShouldOnlyCollectFiles(@TempDir Path testDir) throws IOException{
        Path tempSubDir = Files.createDirectory(tempDir.resolve("mySubDir"));
        FileCollector fileCollector = new DateCreatedFileCollector(tempDir);
        String[] files = tempDir.toFile().list();

        fileCollector.collectFromPath();
        assertEquals(1, fileCollector.getFullFilePath().size());


    }

    @Test
    public void collectFromPathShouldCollectAllFilesInDirectory() throws IOException{
        FileCollector fileCollector = new DateCreatedFileCollector(tempDir);
        fileCollector.collectFromPath();

        assertEquals(tempDir.toFile().listFiles().length, fileCollector.getFullFilePath().get(0).size());
    }

    @Test
    public void collectFromPathShouldReturnDictionarySameLengthAsNumberOfNonEmptyDirectories() throws IOException{
        Path tempSubDir = Files.createDirectory(tempDir.resolve("mySubDir"));
        Path tempSubDir2 = Files.createDirectory(tempDir.resolve("mySubDir2"));
        Path tempFile = Files.createFile(tempSubDir2.resolve("test.txt"));

        FileCollector fileCollector = new DateCreatedFileCollector(tempDir);
        fileCollector.collectFromPath();

        int nonEmptyDirectoryCount = 0;
        Path[] directories = new Path[]{tempDir,tempSubDir, tempSubDir2};
        for(Path path: directories){
            if(path.toFile().listFiles().length > 0){
                nonEmptyDirectoryCount++;
            }
        }

        System.out.println(fileCollector.getFullFilePath());
        assertEquals(nonEmptyDirectoryCount, fileCollector.getFullFilePath().size());

    }
}



