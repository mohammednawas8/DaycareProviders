package com.loc.daycareproviders.presentation.screens.choose_accounts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.loc.daycareproviders.R
import com.loc.daycareproviders.domain.model.AccountType
import com.loc.daycareproviders.presentation.common.HalfCircle
import com.loc.daycareproviders.presentation.navigation.Screen
import com.loc.daycareproviders.ui.Dimens.LARGE_PADDING
import com.loc.daycareproviders.ui.Dimens.MEDIUM_PADDING
import com.loc.daycareproviders.ui.theme.Teal

@Composable
fun ChooseAccountsScreen(
    navigate: (String) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HalfCircle(
            color = Teal,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.20f)
                .scale(1.2f),
        )

        Spacer(modifier = Modifier.height(LARGE_PADDING))
        Spacer(modifier = Modifier.height(LARGE_PADDING))

        Text(
            text = stringResource(id = R.string.choose_your_option),
            fontSize = MaterialTheme.typography.titleMedium.fontSize
        )

        Spacer(modifier = Modifier.height(LARGE_PADDING))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.75f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                UserOptionItem(
                    iconId = R.drawable.ic_student,
                    text = stringResource(id = R.string.student),
                    onClick = {
                        navigate(Screen.LoginScreen.navigate(AccountType.STUDENT.name))
                    }
                )

                UserOptionItem(
                    iconId = R.drawable.ic_teacher,
                    text = stringResource(id = R.string.teacher),
                    onClick = {
                        navigate(Screen.LoginScreen.navigate(AccountType.TEACHER.name))
                    }
                )
            }

            Spacer(modifier = Modifier.height(MEDIUM_PADDING))

            UserOptionItem(
                iconId = R.drawable.ic_parent,
                text = stringResource(id = R.string.parent),
                onClick = {
                   navigate(Screen.LoginScreen.navigate(AccountType.PARENT.name))
                }
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ChooseAccountsScreenPreview() {
    ChooseAccountsScreen(
        navigate = {}
    )
}