package com.iljasstan.Enums;

public enum Welders {
    WELDER1("Иванов Иван Иванович"),
    WELDER2("Иванов Петр Иванович"),
    WELDER3("Иванов Максим Алексеевич");

    String desc;

    Welders(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
