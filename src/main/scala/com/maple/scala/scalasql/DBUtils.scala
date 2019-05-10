package com.maple.scala.scalasql

import java.security.SecureRandom

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource
import com.typesafe.config.{Config, ConfigFactory}
import javax.crypto.{Cipher, KeyGenerator}
import javax.crypto.spec.SecretKeySpec
import javax.sql.DataSource
import javax.xml.bind.DatatypeConverter

object DBUtils {
    val config: Config = ConfigFactory.load()

    val scalaSqlDB: DataSource = buildDataSource()

    /**
      * getDataSources
      *
      */
    private def buildDataSource(): DataSource = {
        val ds = new MysqlDataSource
        ds.setUrl(config.getString("scala.sql.db.url"))
        ds.setUser(config.getString("scala.sql.db.username"))
        ds.setPassword(config.getString("scala.sql.db.password"))
        ds
    }

    private def decryptAES(secret: String) = {
        val saltKey = config.getString("scala.sql.db.key")
        val kgen = KeyGenerator.getInstance("AES")
        val secureRandom = SecureRandom.getInstance("SHA1PRNG")
        secureRandom.setSeed(saltKey.getBytes)
        kgen.init(128, secureRandom)

        val secretKey = kgen.generateKey
        val enCodeFormat = secretKey.getEncoded
        val key = new SecretKeySpec(enCodeFormat, "AES")
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.DECRYPT_MODE, key)
        new String(cipher.doFinal(DatatypeConverter.parseBase64Binary(secret)))
    }

}
