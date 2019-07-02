package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        return when (fullName) {
            null -> null to null
            "" -> null to null
            " " -> null to null
            else -> {
                val parts: List<String>? = fullName.split(" ")
                val firstName = parts?.getOrNull(0)
                val lastName = parts?.getOrNull(1)

                firstName to lastName
            }
        }
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var transliteration = ""

        for (letter in payload){
            val isUpperLetter = letter.isUpperCase()

            var letterTransliteration = when(letter.toLowerCase()){
                'а'-> "a"
                'б'-> "b"
                'в'-> "v"
                'г'-> "g"
                'д'-> "d"
                'е'-> "e"
                'ё'-> "e"
                'ж'-> "z"
                'з'-> "z"
                'и'-> "i"
                'й'-> "i"
                'к'-> "k"
                'л'-> "l"
                'м'-> "m"
                'н'-> "n"
                'о'-> "o"
                'п'-> "p"
                'р'-> "r"
                'с'-> "s"
                'т'-> "t"
                'у'-> "u"
                'ф'-> "f"
                'х'-> "h"
                'ц'-> "c"
                'ч'-> "ch"
                'ш'-> "sh"
                'щ'-> "sh"
                'ъ'-> ""
                'ы'-> "i"
                'ь'-> ""
                'э'-> "e"
                'ю'-> "yu"
                'я'-> "ya"
                ' '-> divider
                else -> letter.toString()
            }

            if(isUpperLetter){
                letterTransliteration = when {
                    letterTransliteration.length == 1 -> letterTransliteration.toUpperCase()
                    letterTransliteration.length > 1 -> letterTransliteration.substring(0,1).toUpperCase() + letterTransliteration.substring(1)
                    else -> ""
                }
            }

            transliteration+=letterTransliteration
        }

        return  transliteration
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val initialFirstName: String? = when (firstName) {
            null -> ""
            "" -> ""
            " " -> ""
            else -> {
                firstName.substring(0, 1).toUpperCase()
            }
        }
        val initialLastName: String? = when (lastName) {
            null -> ""
            "" -> ""
            " " -> ""
            else -> {
                lastName.substring(0, 1).toUpperCase()
            }
        }

        return when ("$initialFirstName$initialLastName"){
            "" -> null
            else -> "$initialFirstName$initialLastName"
        }

    }

}