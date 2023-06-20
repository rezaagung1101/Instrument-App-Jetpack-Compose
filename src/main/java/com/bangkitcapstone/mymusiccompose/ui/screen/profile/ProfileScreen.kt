package com.bangkitcapstone.mymusiccompose

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkitcapstone.mymusiccompose.ui.theme.MyMusicComposeTheme


@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(0.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            Box(
                modifier = Modifier
                    .sizeIn(minHeight = 350.dp)
                    .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                    .background(MaterialTheme.colors.primary)
                    .fillMaxWidth()
            )

            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.hem),
                        contentDescription = stringResource(id = R.string.description),
                        modifier = Modifier
                            .size(130.dp)
                            .clip(CircleShape)
                            .border(
                                width = 3.dp,
                                color = Color.White,
                                shape = CircleShape
                            )
                    )
                    Spacer(modifier = Modifier.padding(18.dp))
                    Text(
                        text = stringResource(id = R.string.name_value),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h5
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = stringResource(id = R.string.email_value),
                        color = Color.White,
                    )
                }
            }
        }
        Spacer(modifier = modifier.padding(14.dp))
        Text(
            text = stringResource(id = R.string.about),
            style = MaterialTheme.typography.h2,
            fontSize = 30.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Medium,
            modifier = modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = modifier.padding(6.dp))
        Divider(color = MaterialTheme.colors.primaryVariant, thickness = 1.dp)
        Spacer(modifier = modifier.padding(16.dp))

        Text(
            text = stringResource(id = R.string.about_value),
            lineHeight = 30.sp,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
        Spacer(modifier = modifier.padding(16.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MyMusicComposeTheme() {
        ProfileScreen()
    }
}