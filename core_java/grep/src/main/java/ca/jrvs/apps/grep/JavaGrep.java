package ca.jrvs.apps.grep;

import java.io.*;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public interface JavaGrep {

    void process() throws IOException;

    List<File> listFiles(String rootDir);

    List<String> readLines(File inputFile) throws IllegalArgumentException, IOException;

    boolean containsPattern(String line);

    void writeToFile(List<String> lines) throws IOException;

    String getRootPath();

    void setRootPath(String rootPath);

    String getRegex();

    void setRegex(String regex);

    String getOutFile();

    void setOutFile(String outFile);
}
