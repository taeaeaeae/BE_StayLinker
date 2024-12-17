package com.yoong.sunnyside.infra.email

import com.yoong.sunnyside.common.exception.InternalServerException
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeMessage
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class EmailUtils(
    @Value("\${spring.mail.username}") private val username: String,
    val javaMailSender: JavaMailSender,
){

    val logFactory = LoggerFactory.getLogger("emailUtils")

    fun sendEmail(
        toEmail: String,
        subject: String,
    ): String{
        val code = createCode()
        val mailForm = createVerifyMailForm(username, toEmail, subject, code)
        kotlin.runCatching {
            javaMailSender.send(mailForm)
            return code
        }.onFailure {
            logFactory.info("failed to send email: $it")
            throw InternalServerException("Email sending failed")
        }

        return code
    }

    fun createVerifyMailForm(setFrom: String, toMail: String, title: String, code: String): MimeMessage {
        val message = javaMailSender.createMimeMessage()

        message.setFrom(InternetAddress(setFrom))
        message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(toMail))
        message.subject = title
        message.setContent(
            """<div>
                <p>This is your StayLinker Email verification code.<p>
                <p>verification code : $code</p>
               </div>""".trimIndent(),
            "text/html; charset=utf-8"
        )

        return message
    }


    fun createCode(): String {
        val length = 6
        val charSet: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..length)
            .map { charSet.random() }
            .joinToString("")
    }

}