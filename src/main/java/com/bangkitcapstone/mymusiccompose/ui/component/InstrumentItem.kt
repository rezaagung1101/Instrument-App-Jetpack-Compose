package com.bangkitcapstone.mymusiccompose.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkitcapstone.mymusiccompose.R
import com.bangkitcapstone.mymusiccompose.ui.theme.MyMusicComposeTheme
import com.bangkitcapstone.mymusiccompose.ui.theme.Shapes

@Composable
fun InstrumentItem(
    image: Int,
    title: String,
    price: Double,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp)) // Add border radius here
            .border(width = 3.dp, color = MaterialTheme.colors.primary, shape = RectangleShape)
            .background(MaterialTheme.colors.primary)
            .padding(0.dp)
    ) {
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(10.dp)) // Add border radius here
                .border(width = 1.dp, color = MaterialTheme.colors.primary, shape = RectangleShape)
                .padding(7.dp)
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .clip(Shapes.medium)
            )
            Text(
                text = title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
            Text(
                text = stringResource(R.string.required_money, price),
                style = MaterialTheme.typography.subtitle2.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.White
                )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun InstrumentItemPreview() {
    MyMusicComposeTheme() {
        InstrumentItem(R.drawable.gitar, "Gitar", 4.000)
    }
}