package com.curiousapps.keepnote.data

import java.util.Date

class SampleDataProvider {

    companion object {
        private val sampleText1 = "A simple note!"
        private val sampleText2 = "A simple note\n with line feed!"
        private val sampleText3 = """
            Bacon ipsum dolor amet dolor pork belly swine quis beef. Shank leberkas est commodo pork fugiat shankle. Sint brisket tri-tip, incididunt consequat short ribs irure jerky landjaeger hamburger. Venison cow pancetta, aute ipsum voluptate dolore cupidatat nostrud brisket t-bone. Swine ground round bacon ham enim irure short ribs tongue dolor. Turducken frankfurter sed consectetur do nulla, strip steak spare ribs adipisicing tenderloin aliqua ullamco sint.

            Leberkas burgdoggen ut, veniam ground round bacon ball tip ullamco tenderloin andouille. Ut boudin laboris nostrud tongue chicken chuck shoulder. Shank officia lorem velit. Ad pastrami commodo aliqua porchetta salami, occaecat sunt beef duis corned beef enim. Deserunt tongue id anim tri-tip aute magna proident est buffalo in kielbasa tail laborum ut.
        """.trimIndent()

        private fun getDate(diff: Long): Date {
            return Date(Date().time + diff)
        }

        fun getNotes() = arrayListOf(
            NoteEntity( getDate(0), sampleText1),
            NoteEntity( getDate(1), sampleText2),
            NoteEntity( getDate(2), sampleText3)
        )
    }
}