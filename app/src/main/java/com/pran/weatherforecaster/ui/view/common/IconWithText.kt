package com.pran.weatherforecaster.ui.view.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pran.weatherforecaster.R

@Composable
fun IconWithText(
    @DrawableRes icon: Int,
    text: String,
    iconSize: Dp,
    textSize: TextUnit
) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "",
            modifier = Modifier.size(iconSize)
        )
        Spacer(modifier = Modifier.width(2.dp))
        CustomText(text = text, fontSize = textSize)
    }
}

@Preview(showBackground = true)
@Composable
private fun IconWithTextPreview() {
    IconWithText(
        icon = R.drawable.ic_wind,
        text = stringResource(id = R.string.wind_value, 1.2f),
        iconSize = 10.dp,
        textSize = 12.sp
    )
}
