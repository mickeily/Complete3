package com.example.complete;

public class Contenido
{
    private int orden;
    private int status;
    private int point;
    private int attempt;
    private String date;
    private String example;
    private String definition;
    private int book;
    private int unit;
    private int lesson;
    private String word;
    private String type;
    private String path;
    private int difficulty;

    public Contenido(int orden, int status, int point, int attempt, String date, String
            example, String definition, int book, int unit, int lesson, String word,
                     String type, String path, int difficulty) {
        this.orden = orden;
        this.status = status;
        this.point = point;
        this.attempt = attempt;
        this.date = date;
        this.example = example;
        this.definition = definition;
        this.book = book;
        this.unit = unit;
        this.lesson = lesson;
        this.word = word;
        this.type = type;
        this.path = path;
        this.difficulty = difficulty;
    }
    Contenido(){};

    public Contenido(String[] datos) {
        this.orden = Integer.parseInt(datos[0]);
        this.status = Integer.parseInt(datos[1]);
        this.point = Integer.parseInt(datos[2]);
        this.attempt = Integer.parseInt(datos[3]);
        this.date = datos[4];
        this.example = datos[5];
        this.definition = datos[6];
        this.book = Integer.parseInt(datos[7]);
        this.unit = Integer.parseInt(datos[8]);
        this.lesson = Integer.parseInt(datos[9]);
        this.word = datos[10];
        this.type = datos[11];
        this.path = datos[12];
        this.difficulty = Integer.parseInt(datos[13]);
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getLesson() {
        return lesson;
    }

    public void setLesson(int lesson) {
        this.lesson = lesson;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int translation) {
        this.difficulty = difficulty;
    }


}
