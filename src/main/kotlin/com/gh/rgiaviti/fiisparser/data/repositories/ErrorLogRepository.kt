package com.gh.rgiaviti.fiisparser.data.repositories

import com.gh.rgiaviti.fiisparser.data.domains.ErrorLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ErrorLogRepository : JpaRepository<ErrorLog, Int> {

}