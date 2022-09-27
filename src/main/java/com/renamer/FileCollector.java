package com.renamer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

public interface FileCollector {
    File[] showPath() throws IOException;

    void collectFromPath() throws IOException;

    HashMap<Integer, List<File>> getFullFilePath();

    void setFullFilePath(HashMap<Integer, List<File>> fullFilePath);

    Path getDirectory();

    void setDirectory(Path directoryPath);
}
