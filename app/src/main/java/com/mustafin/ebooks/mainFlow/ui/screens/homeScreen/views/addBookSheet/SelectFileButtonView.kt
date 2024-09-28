package com.mustafin.ebooks.mainFlow.ui.screens.homeScreen.views.addBookSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mustafin.ebooks.R
import com.mustafin.ebooks.core.domain.APP_DEFAULT_FONT

@Composable
fun SelectFileButtonView(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RectangleShape,
        contentPadding = PaddingValues(12.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.secondary_background))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.download_icon),
                contentDescription = null,
                tint = colorResource(id = R.color.secondary_background),
                modifier = Modifier
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.text))
                    .padding(10.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = stringResource(id = R.string.select_file),
                color = colorResource(id = R.color.text),
                fontSize = 18.sp,
                fontFamily = APP_DEFAULT_FONT,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Icon(
                painter = painterResource(id = R.drawable.arrow_right_icon),
                contentDescription = null,
                tint = colorResource(id = R.color.text),
                modifier = Modifier.size(21.dp)
            )
        }
    }
}