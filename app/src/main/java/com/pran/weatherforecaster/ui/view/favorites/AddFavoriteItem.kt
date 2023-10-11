package com.pran.weatherforecaster.ui.view.favorites

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pran.weatherforecaster.R
import com.pran.weatherforecaster.ui.view.common.IconWithText

@Composable
fun AddFavoriteItem() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        color = Color.LightGray
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconWithText(
                icon = R.drawable.ic_add_fav,
                text = stringResource(id = R.string.add_favorite),
                iconSize = 16.dp,
                textSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun AddFavoriteItemPreview() {
    AddFavoriteItem()
}
