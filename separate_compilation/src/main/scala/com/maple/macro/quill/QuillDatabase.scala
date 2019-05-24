package com.maple.`macro`.quill

import io.getquill.{MysqlJdbcContext, QueryProbing, SnakeCase}

object QuillDatabase extends MysqlJdbcContext(SnakeCase, "ctx") with TestEntities with QueryProbing {

}
