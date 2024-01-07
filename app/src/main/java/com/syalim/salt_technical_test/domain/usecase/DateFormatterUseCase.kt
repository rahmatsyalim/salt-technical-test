/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.domain.usecase

import dagger.hilt.android.scopes.ViewModelScoped
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@ViewModelScoped
class DateFormatterUseCase @Inject constructor() {

    private val fromPattern = "yyyy-MM-dd'T'HH:mm:ss"
    private val toPattern = "dd MMMM yyyy, HH:mm"
    private val locale = Locale("ID")

    operator fun invoke(stringDate: String): String {
        return try {
            SimpleDateFormat(fromPattern, locale)
                .parse(stringDate)?.let { date ->
                    SimpleDateFormat(toPattern, locale).format(date)
                } ?: "-"
        } catch (e: Exception) {
            "-"
        }
    }

}