package com.groovy.ankur

import java.nio.file.Files

def COMMA_SEPARATOR = ","
def NEW_LINE = "\n"

// This will represent the root folder i.e 'groovy-demos'.
// The script will search for XML files inside this folder.
def folder = new File("../../../../")

// The csv file will be created inside the root folder (groovy-demos)
def fileToWrite = new File("../../../../books.csv")

def books

//The file writer will automatically perform the file close operation.
//There is no need to explicitly call close() on file.
fileToWrite.withWriter {

    // listFiles() will give all files inside root folder but the filter operation
    // below will pick only those files which start with 'books-' prefix and are of type xml.
    // The writer then will write the contents of XML files to a CSV file called books,csv.
    folder.listFiles().findAll { file ->
        file.getName().startsWith("books-") &&
                Files.probeContentType(file.toPath()).endsWith("xml")
    }.each { file ->
        books = new XmlParser().parse(file)
        books.children().each { row ->
            it.append(COMMA_SEPARATOR)
            it.append(row.name.text())
            it.append(COMMA_SEPARATOR)
            it.append(row.author.text())
            it.append(COMMA_SEPARATOR)
            it.append(row.version.text())
            it.append(NEW_LINE)

        }
    }
    println("Success")
}






