package ua.edmko.core.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ua.edmko.core.ui.R

val Cabin = FontFamily(
    Font(R.font.cabin_regular, FontWeight.W400),
    Font(R.font.cabin_medium, FontWeight.W500),
    Font(R.font.cabin_semi_bold, FontWeight.W600),
    Font(R.font.cabin_bold, FontWeight.W700),
)

val Typography = Typography(
    defaultFontFamily = Cabin,
    h1 = TextStyle(fontFamily = Cabin, fontWeight = FontWeight.W600, fontSize = 96.sp),
    h2 = TextStyle(fontFamily = Cabin, fontWeight = FontWeight.W600, fontSize = 60.sp),
    h3 = TextStyle(fontFamily = Cabin, fontWeight = FontWeight.W600, fontSize = 48.sp),
    h4 = TextStyle(fontFamily = Cabin, fontWeight = FontWeight.W600, fontSize = 34.sp),
    h5 = TextStyle(fontFamily = Cabin, fontWeight = FontWeight.W600, fontSize = 24.sp),
    subtitle1 = TextStyle(fontFamily = Cabin, fontWeight = FontWeight.W500, fontSize = 16.sp),
    h6 = TextStyle(fontFamily = Cabin, fontWeight = FontWeight.W400, fontSize = 20.sp),
    body1 = TextStyle(fontFamily = Cabin, fontWeight = FontWeight.W400, fontSize = 16.sp),
    body2 = TextStyle(fontFamily = Cabin, fontWeight = FontWeight.W400, fontSize = 14.sp),
    button = TextStyle(fontFamily = Cabin, fontWeight = FontWeight.W600, fontSize = 14.sp),
    caption = TextStyle(fontFamily = Cabin, fontWeight = FontWeight.W400, fontSize = 12.sp),
)
