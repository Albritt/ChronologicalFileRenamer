package com.renamer.tests;

import com.renamer.UnsortedFileCollector;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class UnsortedFileCollectorTests {
    @TempDir
    Path tempDir;

    @BeforeEach
    public void setUp() {
        try {
            Path tempFile = Files.createFile(tempDir.resolve("tempFile.txt"));
            Path tempFile2 = Files.createFile(tempDir.resolve("tempFile2.jpg"));
            Path tempFile3 = Files.createFile(tempDir.resolve("tempFile3.mp4"));
        } catch (InvalidPathException ipe) {
            System.err.println(
                    "error creating temporary test file in " +
                            this.getClass().getSimpleName());

        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }

    }

    @Test
    public void PathShouldBeADirectoryAndNotBeNull(){
        UnsortedFileCollector unsortedFileCollector = new UnsortedFileCollector(tempDir);
        assertNotNull(unsortedFileCollector.getDirectory());
        assertTrue(unsortedFileCollector.getDirectory().toFile().isDirectory());
    }

    @Test
    public void collectFromPathShouldNotCollectFromGivenNonDirectory(@TempDir Path file) throws IOException{
        Path fileAsPath = Files.createFile(file.resolve("test.txt"));
        UnsortedFileCollector unsortedFileCollector = new UnsortedFileCollector(fileAsPath);
        assertThrows(RuntimeException.class, ()-> unsortedFileCollector.collectFromPath(),
                unsortedFileCollector.getDirectory().toString() +
                        " is empty or not a directory, no renaming can be done.");
    }

    @Test
    public void collectFromPathShouldNotCollectEmptyDirectories(@TempDir Path emptyDir) throws IOException {
        Path emptyDirectory = emptyDir.resolve("emptyDir");
        UnsortedFileCollector unsortedFileCollector = new UnsortedFileCollector(emptyDirectory);
        assertThrows(RuntimeException.class, ()-> unsortedFileCollector.collectFromPath(),
                unsortedFileCollector.getDirectory().toString() +
                        " is empty or not a directory, no renaming can be done.");

    }

    @Test
    public void collectFromPathShouldOnlyCollectFiles(@TempDir Path testDir) throws IOException{
        Path emptySubDir = Files.createDirectory(tempDir.resolve("mySubDir"));
        UnsortedFileCollector unsortedFileCollector = new UnsortedFileCollector(tempDir);

        unsortedFileCollector.collectFromPath();
        assertEquals(1, unsortedFileCollector.getFullFilePath().size());


    }

    @Test
    public void collectFromPathShouldCollectAllFilesInDirectory() throws IOException{
        UnsortedFileCollector unsortedFileCollector = new UnsortedFileCollector(tempDir);
        unsortedFileCollector.collectFromPath();

        assertEquals(tempDir.toFile().listFiles().length, unsortedFileCollector.getFullFilePath().get(0).size());
    }

    @Test
    public void collectFromPathShouldReturnDictionarySameLengthAsNumberOfNonEmptyDirectories() throws IOException{
        Path emptySubDir = Files.createDirectory(tempDir.resolve("mySubDir"));
        Path nonEmptySubDir = Files.createDirectory(tempDir.resolve("mySubDir2"));
        Path tempFile = Files.createFile(nonEmptySubDir.resolve("test.txt"));

        UnsortedFileCollector unsortedFileCollector = new UnsortedFileCollector(tempDir);
        unsortedFileCollector.collectFromPath();

        int nonEmptyDirectoryCount = 0;
        Path[] directories = new Path[]{tempDir,emptySubDir, nonEmptySubDir};
        for(Path path: directories){
            if(path.toFile().listFiles().length > 0){
                nonEmptyDirectoryCount++;
            }
        }

        assertEquals(nonEmptyDirectoryCount, unsortedFileCollector.getFullFilePath().size());

    }
}



