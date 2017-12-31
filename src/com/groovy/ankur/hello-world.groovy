package com.groovy.ankur

def COMMA_SEPARATOR = ","
def NEW_LINE = "\n"
def folder = new File("C:\\Users\\ankur\\IdeaProjects\\groovy-demos")
def fileToWrite = new File("C:\\Users\\ankur\\IdeaProjects\\groovy-demos\\books.csv")
def books
fileToWrite.withWriter {
    folder.listFiles().findAll { file -> file.getName().startsWith("books-") }.each { file ->
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
}





