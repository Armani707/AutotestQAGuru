package pages.data;

public enum Language {   // Это enum - Перечисление несколько видов того, что ты 100% знаешь (что они будут ограничены, и что именно это будет), например дни недели, время года, пол.
    EN("WHAT IS SELENIDE?"),
    RU("ЧТО ТАКОЕ SELENIDE?");

    public final String description;

    Language(String description) {
        this.description = description;
    }
}
