# StudentCourseRegistration

-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in studentCoursesBackup/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile studentCoursesBackup/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile studentCoursesBackup/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile studentCoursesBackup/src/build.xml run -Darg0=<input_file.txt> -Darg1=<delete_file.txt> -Darg2=<output1_file.txt> -Darg3=<output2_file.txt> -Darg4=<output3_file.txt>

## Replace <fileName.txt> with real file names. For example, if the files are available in the path,
## you can run it in the following manner:

ant -buildfile studentCoursesBackup/src/build.xml run -Darg0=input_file.txt -Darg1=delete_file.txt -Darg2=output1_file.txt -Darg3=output2_file.txt -Darg4=output3_file.txt

Note: Arguments accept the absolute path of the files.

-----------------------------------------------------------------------
## Description:


-----------------------------------------------------------------------
