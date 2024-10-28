package com.yoong.sunnyside.infra.security.jwt

import com.yoong.sunnyside.infra.security.MemberRole
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

@Component
class JwtHelper(
    @Value("\${auth.jwt.issuer}") private val issuer: String,
    @Value("\${auth.jwt.accessTokenSecret}") private val accessTokenSecret: String,
    @Value("\${auth.jwt.accessTokenExpirationHour}") private val accessTokenExpirationHour: Long
) {
    private val key = Keys.hmacShaKeyFor(accessTokenSecret.toByteArray())

    fun generateToken(userId: Long, role: MemberRole): String {
        val now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"))

        val claims = Jwts.claims().add(mapOf("role" to role)).build()

        return Jwts
            .builder()
            .signWith(key)
            .subject(userId.toString())
            .issuer(issuer)
            .issuedAt(Date.from(now.toInstant()))
            .expiration(Date.from(now.plusHours(accessTokenExpirationHour).toInstant()))
            .claims(claims)
            .compact()!!
    }

    fun validateTokenAndGetClaims(token: String): Result<Jws<Claims>> {
        return runCatching {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token)
        }
    }
}