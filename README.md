# docx-to-word-map
takes in a docx file and counts each occurrence of the words in the file. 

# how it works
you can fork the repo and run mvn:package to create a fat jar that you can run.
- remember the project is made in jdk 21. 

# usecase
java -jar *.jar \[path to .docx file\] \[path to output file\]

# arguments
- the second output file argument is optional and the output will instead be printed to the stdout if is absent

