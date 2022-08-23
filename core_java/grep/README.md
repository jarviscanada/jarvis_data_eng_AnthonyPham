# Java Grep App
The Java Grep Application emulates the functionality of the Linux Grep command, searching files in the directory andregex patter matching with strings within files. Two methods of implementation are shown within this project, one using a BufferReader and FileOutputStream and the other using Lambda and Stream API's. This application is built and run using Maven.

# Quick Start
The following variables are required to run the program:

- ```regex_pattern```: the regex pattern to be searched
- ```src_dir```: the source directory path
- ```outfile```: the output file name

The application can be run in two ways

1. Using the Jar file:
```mvn clean package
java -jar target/grep-1.0-SNAPSHOT.jar ${regex_pattern} ${src_dir} ./out/${outfile}```

2. Using the Docker image:

```docker pull anthonypham017/grep
docker run --rm -v `pwd`/data:/data -v `pwd`/out:/out julngyn/grep ${regex_pattern} ${src_dir} /out/${outfile}```

After the program is run, the output file can be displayed using:
```cat out/$outfile```

#Implementation Pseudocode

``` matchedLines = []
for file in listFilesRecursively(rootDir)
  for line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
writeToFile(matchedLines)```

#Performance Issue 
The application outputs an OutOfMemoryError exception if the file size is larger than the heap of the JVM. The List data structure can easily get extremely large if a file contains too many lines to process. This can be amended by using Stream API's, rather than Lists as Streams do not store data and allows for elements to be computed on demand. 

#Test
The app was tested by inputting sample data into the arguments of the program. The sample data was input with multiple regex strings, root directory paths and out filenames. Functionality was tested against the expected Linux Grep behaviour and adjusted accordingly. 

#Deployment
The project was deployed on Docker Hub where the docker image used for the project was uploaded. It can be viewed publically at https://hub.docker.com/repository/docker/anthonypham017/grep or using the following command: ```docker pull anthonypham017/grep```

#Improvement 
- Implement a GUI for a better user experience
- Fix performance issue using a more memory-efficient implementation
- Allow user to manipulate files

